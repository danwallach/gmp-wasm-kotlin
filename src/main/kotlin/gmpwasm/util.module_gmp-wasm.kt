@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)
@file:JsModule("gmp-wasm")
@file:JsNonModule

package gmpwasm

external fun isUint32(num: Number): Boolean

external fun assertUint32(num: Number)

external fun isInt32(num: Number): Boolean

external fun assertInt32(num: Number)

external fun assertArray(arr: Array<Any>)

external fun isValidRadix(radix: Number): Boolean

external fun assertValidRadix(radix: Number)