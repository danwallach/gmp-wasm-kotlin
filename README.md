# gmp-wasm-kotlin

This repo is an attempt at building a Kotlin class that behaves similarly to Java's `BigInteger`, but which can
run efficiently in the Kotlin/JS universe by taking advantage of [gmp-wasm](https://github.com/Daninet/gmp-wasm), which
is a port of GMP (Gnu MultiPrecision) to WASM. This could be a real game-changer for doing cryptography in JavaScript
if it's fast and stable.

Right now, there are several bugs that seem to be exposed by this code. It's instrumented with numerous
calls to `console.info()`, which helps highlight the bugs.

In particular, if you run `./gradlew nodeTest --info`, which runs the unit tests with extra verbosity, it will output:

```
electionguard.BigIntegerTests.additionBasics STANDARD_OUT
    [info] testing additionBasics
starting getGmpContext()

electionguard.BigIntegerTests.multiplicationBasics STANDARD_OUT
    [info] testing multiplicationBasics
starting getGmpContext()
getGmpContext: got lib: keys(binding,calculate,getContext,reset)
getGmpContext: got binding
loading number: 0
converting 1 bytes to a BigInteger
-- mpz_t
-- malloc
-- mem.set
-- mpz_import
-- free
bytesToBigInteger complete
numberToBigInteger complete
converting 20 bytes to a BigInteger
-- mpz_t
-- malloc
-- mem.set
-- mpz_import
-- free
bytesToBigInteger complete
converting 20 bytes to a BigInteger
-- mpz_t
-- malloc
-- mem.set
:nodeTest (Thread[Execution worker for ':',5,main]) completed. Took 0.678 secs.
```

This seems to show two issues:
1) During the first unit test, `additionBasics`, it crashed inside `BigInteger.getGmpContext()`. In
   particular, it crashes while waiting for the library to initialize itself. It's possible that this
   is a Kotlin/JS problem, which might not be correctly handling the `Promise`. (The call to `await()`
   inside a `suspend` function is documented to exactly do what's required here.)
3) The unit test runner next went on to `multiplicationBasics`, and it started making `mpz_t` instances.
   The first two succeeded, and the third one crashed in `mpz_import`.