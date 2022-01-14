@file:OptIn(ExperimentalCoroutinesApi::class)

package electionguard

import io.kotest.property.checkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BigIntegerTests {
    @Test
    fun additionBasics() {
        console.info("testing additionBasics")
        runTest {
            val gmp = getGmpContext()
            val zero = gmp.numberToBigInteger(0)
            var counter = 0
            checkAll(bigIntegers(gmp, 20), bigIntegers(gmp, 20), bigIntegers(gmp, 20)) { a, b, c ->
                console.info("test #${counter++}")
                assertEquals(a + b, b + a) // commutative
                assertEquals(a + (b + c), (a + b) + c) // associative
                assertEquals(a, a + zero) // identity
            }
        }
    }
    @Test
    fun multiplicationBasics() {
        console.info("testing multiplicationBasics")
        runTest {
            val gmp = getGmpContext()
            val one = gmp.numberToBigInteger(1)
            var counter = 0
            checkAll(bigIntegers(gmp, 20), bigIntegers(gmp, 20), bigIntegers(gmp, 20)) { a, b, c ->
                console.info("test #${counter++}")
                assertEquals(a + b, b + a) // commutative
                assertEquals(a * b, b * a) // commutative
                assertEquals(a * (b * c), (a * b) * c) // associative
                assertEquals(a, a * one) // identity
                assertEquals(a * (b + c), a * b + a * c) // distributive
            }
        }
    }
}