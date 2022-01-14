package electionguard

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.khronos.webgl.Uint8Array

// Useful code borrowed from:
// https://github.com/rnett/kotlin-js-action/blob/main/kotlin-js-action/src/main/kotlin/com/rnett/action/Utils.kt

/**
 * Non-copying conversion to a Kotlin [ByteArray].
 */
fun ArrayBuffer.asByteArray(byteOffset: Int = 0, length: Int = this.byteLength): ByteArray =
    Int8Array(this, byteOffset, length).asByteArray()

/**
 * Non-copying conversion to a Kotlin [ByteArray].
 */
fun Int8Array.asByteArray(): ByteArray = this.unsafeCast<ByteArray>()

/**
 * Non-copying conversion to a Kotlin [ByteArray].
 */
fun Uint8Array.asByteArray(): ByteArray = Int8Array(buffer, byteOffset, length).asByteArray()

/**
 * Non-copying conversion to a [Int8Array].
 */
fun ByteArray.asInt8Array(): Int8Array = this.unsafeCast<Int8Array>()

/**
 * Non-copying conversion to a [Uint8Array].
 */
fun ByteArray.asUint8Array(): Uint8Array = this.unsafeCast<Uint8Array>()
