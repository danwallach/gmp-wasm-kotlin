package electionguard

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.khronos.webgl.Uint8Array

/** Convert an unsigned 64-bit long into a big-endian 8-byte array. */
fun ULong.toByteArray(): ByteArray =
    when {
        this <= UByte.MAX_VALUE -> byteArrayOf((this and 0xffU).toByte())
        this <= UShort.MAX_VALUE ->
            byteArrayOf(((this shr 8) and 0xffU).toByte(), (this and 0xffU).toByte())
        this <= UInt.MAX_VALUE ->
            byteArrayOf(
                ((this shr 24) and 0xffU).toByte(),
                ((this shr 16) and 0xffU).toByte(),
                ((this shr 8) and 0xffU).toByte(),
                (this and 0xffU).toByte())
        else ->
            byteArrayOf(
                ((this shr 56) and 0xffU).toByte(),
                ((this shr 48) and 0xffU).toByte(),
                ((this shr 40) and 0xffU).toByte(),
                ((this shr 32) and 0xffU).toByte(),
                ((this shr 24) and 0xffU).toByte(),
                ((this shr 16) and 0xffU).toByte(),
                ((this shr 8) and 0xffU).toByte(),
                (this and 0xffU).toByte()
            )
    }

/** Convert an unsigned 32-bit int into a big-endian 4-byte array. */
fun UInt.toByteArray(): ByteArray = this.toULong().toByteArray()

// Useful code borrowed from:
// https://github.com/rnett/kotlin-js-action/blob/main/kotlin-js-action/src/main/kotlin/com/rnett/action/Utils.kt

/** Non-copying conversion to a Kotlin [ByteArray]. */
fun ArrayBuffer.asByteArray(byteOffset: Int = 0, length: Int = this.byteLength): ByteArray =
    Int8Array(this, byteOffset, length).asByteArray()

/** Non-copying conversion to a Kotlin [ByteArray]. */
fun Int8Array.asByteArray(): ByteArray = this.unsafeCast<ByteArray>()

/** Non-copying conversion to a Kotlin [ByteArray]. */
fun Uint8Array.asByteArray(): ByteArray = Int8Array(buffer, byteOffset, length).asByteArray()

/** Non-copying conversion to a [Int8Array]. */
fun ByteArray.asInt8Array(): Int8Array = this.unsafeCast<Int8Array>()

/** Non-copying conversion to a [Uint8Array]. */
fun ByteArray.asUint8Array(): Uint8Array = this.unsafeCast<Uint8Array>()
