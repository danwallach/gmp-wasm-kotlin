@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS")
@file:JsModule("gmp-wasm")
@file:JsNonModule

package gmpwasm

import kotlin.js.Promise

external interface GMPLib {
    var binding: GMPInterface
    var reset: () -> Promise<Unit>
}

external fun init(): Promise<GMPLib>