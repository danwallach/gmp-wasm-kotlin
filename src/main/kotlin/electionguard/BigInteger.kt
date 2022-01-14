package electionguard

import FinalizationRegistry
import gmpwasm.GMPInterface
import gmpwasm.GMPLib
import gmpwasm.mpz_ptr
import kotlinext.js.Object
import kotlinext.js.asJsObject
import kotlinx.coroutines.await
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get
import org.khronos.webgl.set
import kotlin.js.Promise

/**
 * Call first. Returns the `GmpContext` instance which has everything
 * needed for the computation. Suspending because the internals require
 * working around JavaScript promises.
 */
suspend fun getGmpContext(): GmpContext {
    console.info("starting getGmpContext()")
//    js("console.trace()")  // oddly, not exported to Kotlin
    val gmpP = js("eval('require')('gmp-wasm')") // is this really necessary?
    console.info("getGmpContext: got package")
    val gmpI = gmpP.init().unsafeCast<Promise<GMPLib>>().await()
    console.info("getGmpContext: got lib: keys(${Object.keys(gmpI.asJsObject())})")
    val gmp = gmpI.binding
    console.info("getGmpContext: got binding")
    return GmpContext(gmp)
}

/**
 * This class wraps the `GMPInterface` provided by `gmp-wasm` and gives us a
 * simple BigInteger abstraction that efficiently uses the underlying WASM
 * implementation, and leverages the latest JavaScript "finalization" features
 * to help us figure out when it's time to free the underlying `mpz_ptr` values
 * living in the WASM memory.
 */
class GmpContext(val gmp: GMPInterface) {
    private val registry = FinalizationRegistry<mpz_ptr> {
        console.info("freeing: $it")
        gmp.mpz_clear(it) // frees GnuMP internal memory
        gmp.mpz_t_free(it) // frees the mpz_t wrapper
    }

    /** Helper function: allocates an `mpz` and initializes it to zero. */
    internal fun newEmpty(): mpz_ptr {
        console.info("newEmpty")
        val tmp = gmp.mpz_t()
        gmp.mpz_init(tmp)
        return tmp
    }

    /**
     * Creates a BigInteger wrapper around the passed mpz_ptr, ensuring that
     * when the wrapper becomes garbage, the contained mpz_ptr will be freed.
     */
    internal fun wrap(mpz: mpz_ptr): BigInteger {
        val result = BigInteger(mpz, this)
        registry.register(result, mpz)
        return result
    }

    /** Converts the given small, positive number to a BigInteger. */
    fun numberToBigInteger(i: Number): BigInteger {
        console.info("loading number: $i")
        val iInt = i.toInt()
        if (iInt < 0) {
            throw IllegalArgumentException("only non-negative numbers are supported")
        }

        val result = byteArrayToBigInteger(iInt.toUInt().toByteArray())
        console.info("numberToBigInteger complete")
        return result
    }

    /** Converts the given big-endian byte array representation to a BigInteger. */
    fun byteArrayToBigInteger(byteArray: ByteArray) = bytesToBigInteger(byteArray.asUint8Array())

    /** Converts the given big-endian byte array representation to a BigInteger. */
    fun bytesToBigInteger(byteArray: Uint8Array): BigInteger {
        console.info("converting ${byteArray.length} bytes to a BigInteger")
        val m: mpz_ptr = gmp.mpz_t()
        console.info("-- mpz_t")

        val wasmBuf = gmp.malloc(byteArray.length)
        console.info("-- malloc")
        gmp.mem.set(byteArray, offset = wasmBuf as Int)
        console.info("-- mem.set")
        gmp.mpz_import(m, byteArray.length, 1, 1, 1, 0, wasmBuf)
        console.info("-- mpz_import")
        gmp.free(wasmBuf)
        console.info("-- free")
        val result = wrap(m)

        console.info("bytesToBigInteger complete")
        return result
    }
}

/** A minimal BigInteger-style wrapper around GMP-Wasm. */
class BigInteger(val mpz: mpz_ptr, val context: GmpContext): Comparable<BigInteger> {
    operator fun plus(other: BigInteger): BigInteger {
        val result = context.newEmpty()
        context.gmp.mpz_add(result, this.mpz, other.mpz)
        return context.wrap(result)
    }

    operator fun minus(other: BigInteger): BigInteger {
        val result = context.newEmpty()
        context.gmp.mpz_sub(result, this.mpz, other.mpz)
        return context.wrap(result)
    }

    operator fun times(other: BigInteger): BigInteger {
        val result = context.newEmpty()
        context.gmp.mpz_mul(result, this.mpz, other.mpz)
        return context.wrap(result)
    }

    operator fun rem(other: BigInteger): BigInteger {
        val result = context.newEmpty()
        context.gmp.mpz_mod(result, this.mpz, other.mpz)
        return context.wrap(result)
    }

    fun modPow(exp: BigInteger, modulus: BigInteger): BigInteger {
        val result = context.newEmpty()

        // there's a "secure" version of this, but we're not worried about timing attacks
        context.gmp.mpz_powm(result, this.mpz, exp.mpz, modulus.mpz)
        return context.wrap(result)
    }

    fun modInverse(modulus: BigInteger): BigInteger {
        val result = context.newEmpty()
        context.gmp.mpz_invert(result, this.mpz, modulus.mpz)
        return context.wrap(result)
    }

    /** Return the position of the most significant bit. (LSB = 0) */
    fun msbPosition(): Int = (context.gmp.mpz_sizeinbase(this.mpz, 2) as Int) - 1

    /** Returns a big-endian byte array as JavaScript's native `Uint8Array`. */
    fun toBytes(): Uint8Array {
        // This won't work correctly with negative numbers, but we don't care.
        val countPtr = context.gmp.malloc(4)
        val startPtr = context.gmp.mpz_export(0, countPtr, 1, 1, 1, 0, mpz)
        val size = context.gmp.memView.getUint32(countPtr as Int, true)

        val result = Uint8Array(size)
        for (i in 0..size - 1) result[i] = context.gmp.mem[startPtr as Int + i]

        context.gmp.free(startPtr)
        context.gmp.free(countPtr)

        return result
    }

    /** Returns a big-endian `ByteArray` in Kotlin's native `ByteArray`. */
    fun toByteArray() = toBytes().asByteArray()

    override fun compareTo(other: BigInteger): Int {
        // Happily, mpz's idea of comparison is exactly the same as Java / Kotlin,
        // so we get all the usual comparison operators for very little work.
        return context.gmp.mpz_cmp(this.mpz, other.mpz) as Int
    }

    override fun toString(): String {
        // Here's the original TypeScript solution to this. Sadly, the indexOf()
        // method isn't defined in the Kotlin version of Uint8Array (why?) and
        // neither is the string decoder. Since we don't care about the performance,
        // just the correctness, we're going to load the bytes, one by one, into
        // a mutable array, and just use Kotlin's joinToString() method instead.

        //      const strptr = gmp.mpz_get_str(0, radix, this.mpz_t);
        //      const endptr = gmp.mem.indexOf(0, strptr);
        //      const str = decoder.decode(gmp.mem.subarray(strptr, endptr));
        //      gmp.free(strptr);

        val strptr = context.gmp.mpz_get_str(0, 10, this.mpz)
        var offset = strptr as Int
        val strBytes = mutableListOf<Byte>()
        while(context.gmp.mem[offset] != 0.toByte()) {
            strBytes.add(context.gmp.mem[offset])
            offset++
        }

        val result = strBytes.joinToString(separator = "")
        context.gmp.free(strptr)

        return result
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun equals(other: Any?) = when (other) {
        is BigInteger -> this.compareTo(other) == 0
        else -> false
    }
}