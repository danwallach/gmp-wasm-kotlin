// Full TypeScript interface
// https://fossies.org/linux/TypeScript/lib/lib.esnext.weakref.d.ts

//external interface FinalizationRegistryI {
//    fun register(target: Any, heldValue: Any)
//}

external class FinalizationRegistry<T>(cleanupCallback: (T) -> Unit) {
    fun register(target: Any, heldValue: T)
}

//external val FinalizationRegistry: (cleanupCallback: (Any) -> Unit) -> FinalizationRegistryI

// TODO: provide some kind of "check that the JavaScript engine is new enough" function?
//   Without finalization, on an older JavaScript VM, we *could* just never call free,
//   which might even kinda work for some in-browser applications. Maybe. Probably much
//   better to insist on the newer feature, which is supported as of Chrome 84 and Safari 14.1.

// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/FinalizationRegistry