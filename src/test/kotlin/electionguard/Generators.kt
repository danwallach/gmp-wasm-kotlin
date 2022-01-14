package electionguard

import io.kotest.property.Arb
import io.kotest.property.arbitrary.byte
import io.kotest.property.arbitrary.byteArray
import io.kotest.property.arbitrary.constant
import io.kotest.property.arbitrary.map

/** Generate an arbitrary BigInteger of the requested number of bytes. */
fun bigIntegers(gmp: GmpContext, numBytes: Int = 0): Arb<BigInteger> =
    Arb.byteArray(Arb.constant(numBytes), Arb.byte())
        .map { gmp.byteArrayToBigInteger(it) }

