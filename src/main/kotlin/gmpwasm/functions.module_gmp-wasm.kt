@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS"
)
@file:JsModule("gmp-wasm")
@file:JsNonModule

package gmpwasm

import kotlin.js.Promise
import org.khronos.webgl.DataView
import org.khronos.webgl.Uint8Array

external enum class mpfr_rnd_t {
    MPFR_RNDN /* = 0 */,
    MPFR_RNDZ /* = 1 */,
    MPFR_RNDU /* = 2 */,
    MPFR_RNDD /* = 3 */,
    MPFR_RNDA /* = 4 */,
    MPFR_RNDF /* = 5 */,
    MPFR_RNDNA /* = -1 */
}

external enum class mpfr_flags {
    MPFR_FLAGS_UNDERFLOW /* = 1 */,
    MPFR_FLAGS_OVERFLOW /* = 2 */,
    MPFR_FLAGS_NAN /* = 4 */,
    MPFR_FLAGS_INEXACT /* = 8 */,
    MPFR_FLAGS_ERANGE /* = 16 */,
    MPFR_FLAGS_DIVBY0 /* = 32 */,
    MPFR_FLAGS_ALL /* = 63 */
}

external enum class mpfr_free_cache_t {
    MPFR_FREE_LOCAL_CACHE /* = 1 */,
    MPFR_FREE_GLOBAL_CACHE /* = 2 */
}

external interface GMPInterface {
    var reset: () -> Promise<Unit>
    var malloc: (size: c_size_t) -> c_void_ptr
    var malloc_cstr: (str: String) -> Number
    var free: (ptr: c_void_ptr) -> Unit
    var mem: Uint8Array
    var memView: DataView
    var gmp_randinit_default: (state: gmp_randstate_t) -> Unit
    var gmp_randinit_lc_2exp: (
        state: gmp_randstate_t,
        a: mpz_srcptr,
        c: c_unsigned_long_int,
        m2exp: mp_bitcnt_t
    ) -> Unit
    var gmp_randinit_lc_2exp_size: (state: gmp_randstate_t, size: mp_bitcnt_t) -> c_int
    var gmp_randinit_mt: (state: gmp_randstate_t) -> Unit
    var gmp_randinit_set: (rop: gmp_randstate_t, op: __gmp_randstate_struct_ptr) -> Unit
    var gmp_randseed: (state: gmp_randstate_t, seed: mpz_srcptr) -> Unit
    var gmp_randseed_ui: (state: gmp_randstate_t, seed: c_unsigned_long_int) -> Unit
    var gmp_randclear: (state: gmp_randstate_t) -> Unit
    var gmp_urandomb_ui: (state: gmp_randstate_t, n: c_unsigned_long_int) -> c_unsigned_long_int
    var gmp_urandomm_ui: (state: gmp_randstate_t, n: c_unsigned_long_int) -> c_unsigned_long_int
    var mp_bits_per_limb: () -> Number
    var mpz_t: () -> mpz_ptr
    var mpz_t_free: (ptr: mpz_ptr) -> Unit
    var mpz_t_frees: (ptrs: mpz_ptr) -> Unit
    var mpz_abs: (rop: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_add: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_add_ui: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_unsigned_long_int) -> Unit
    var mpz_addmul: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_addmul_ui: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_unsigned_long_int) -> Unit
    var mpz_and: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_bin_ui: (rop: mpz_ptr, n: mpz_srcptr, k: c_unsigned_long_int) -> Unit
    var mpz_bin_uiui: (rop: mpz_ptr, n: c_unsigned_long_int, k: c_unsigned_long_int) -> Unit
    var mpz_cdiv_q: (q: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_cdiv_q_2exp: (q: mpz_ptr, n: mpz_srcptr, b: mp_bitcnt_t) -> Unit
    var mpz_cdiv_q_ui: (q: mpz_ptr, n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_cdiv_qr: (q: mpz_ptr, r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_cdiv_qr_ui: (
        q: mpz_ptr,
        r: mpz_ptr,
        n: mpz_srcptr,
        d: c_unsigned_long_int
    ) -> c_unsigned_long_int
    var mpz_cdiv_r: (r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_cdiv_r_2exp: (r: mpz_ptr, n: mpz_srcptr, b: mp_bitcnt_t) -> Unit
    var mpz_cdiv_r_ui: (r: mpz_ptr, n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_cdiv_ui: (n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_clear: (x: mpz_ptr) -> Unit
    var mpz_clears: (ptrs: mpz_ptr) -> Unit
    var mpz_clrbit: (rop: mpz_ptr, bit_index: mp_bitcnt_t) -> Unit
    var mpz_cmp: (op1: mpz_srcptr, op2: mpz_srcptr) -> c_int
    var mpz_cmp_d: (op1: mpz_srcptr, op2: c_double) -> c_int
    var mpz_cmp_si: (op1: mpz_srcptr, op2: c_signed_long_int) -> c_int
    var mpz_cmp_ui: (op1: mpz_srcptr, op2: c_unsigned_long_int) -> c_int
    var mpz_cmpabs: (op1: mpz_srcptr, op2: mpz_srcptr) -> c_int
    var mpz_cmpabs_d: (op1: mpz_srcptr, op2: c_double) -> c_int
    var mpz_cmpabs_ui: (op1: mpz_srcptr, op2: c_unsigned_long_int) -> c_int
    var mpz_com: (rop: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_combit: (rop: mpz_ptr, bitIndex: mp_bitcnt_t) -> Unit
    var mpz_congruent_p: (n: mpz_srcptr, c: mpz_srcptr, d: mpz_srcptr) -> c_int
    var mpz_congruent_2exp_p: (n: mpz_srcptr, c: mpz_srcptr, b: mp_bitcnt_t) -> c_int
    var mpz_congruent_ui_p: (n: mpz_srcptr, c: c_unsigned_long_int, d: c_unsigned_long_int) -> c_int
    var mpz_divexact: (q: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_divexact_ui: (q: mpz_ptr, n: mpz_srcptr, d: c_unsigned_long_int) -> Unit
    var mpz_divisible_p: (n: mpz_srcptr, d: mpz_srcptr) -> c_int
    var mpz_divisible_ui_p: (n: mpz_srcptr, d: c_unsigned_long_int) -> c_int
    var mpz_divisible_2exp_p: (n: mpz_srcptr, b: mp_bitcnt_t) -> c_int
    var mpz_even_p: (op: mpz_srcptr) -> Unit
    var mpz_export: (
        rop: c_void_ptr,
        countp: c_size_t_ptr,
        order: c_int,
        size: c_size_t,
        endian: c_int,
        nails: c_size_t,
        op: mpz_srcptr
    ) -> c_void_ptr
    var mpz_fac_ui: (rop: mpz_ptr, n: c_unsigned_long_int) -> Unit
    var mpz_2fac_ui: (rop: mpz_ptr, n: c_unsigned_long_int) -> Unit
    var mpz_mfac_uiui: (rop: mpz_ptr, n: c_unsigned_long_int, m: c_unsigned_long_int) -> Unit
    var mpz_primorial_ui: (rop: mpz_ptr, n: c_unsigned_long_int) -> Unit
    var mpz_fdiv_q: (q: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_fdiv_q_2exp: (q: mpz_ptr, n: mpz_srcptr, b: mp_bitcnt_t) -> Unit
    var mpz_fdiv_q_ui: (q: mpz_ptr, n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_fdiv_qr: (q: mpz_ptr, r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_fdiv_qr_ui: (
        q: mpz_ptr,
        r: mpz_ptr,
        n: mpz_srcptr,
        d: c_unsigned_long_int
    ) -> c_unsigned_long_int
    var mpz_fdiv_r: (r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_fdiv_r_2exp: (r: mpz_ptr, n: mpz_srcptr, b: mp_bitcnt_t) -> Unit
    var mpz_fdiv_r_ui: (r: mpz_ptr, n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_fdiv_ui: (n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_fib_ui: (fn: mpz_ptr, n: c_unsigned_long_int) -> Unit
    var mpz_fib2_ui: (fn: mpz_ptr, fnsub1: mpz_ptr, n: c_unsigned_long_int) -> Unit
    var mpz_fits_sint_p: (op: mpz_srcptr) -> c_int
    var mpz_fits_slong_p: (op: mpz_srcptr) -> c_int
    var mpz_fits_sshort_p: (op: mpz_srcptr) -> c_int
    var mpz_fits_uint_p: (op: mpz_srcptr) -> c_int
    var mpz_fits_ulong_p: (op: mpz_srcptr) -> c_int
    var mpz_fits_ushort_p: (op: mpz_srcptr) -> c_int
    var mpz_gcd: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_gcd_ui: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_gcdext: (g: mpz_ptr, s: mpz_ptr, t: mpz_ptr, a: mpz_srcptr, b: mpz_srcptr) -> Unit
    var mpz_get_d: (op: mpz_srcptr) -> c_double
    var mpz_get_d_2exp: (exp: c_signed_long_int_ptr, op: mpz_srcptr) -> c_double
    var mpz_get_si: (op: mpz_srcptr) -> c_signed_long_int
    var mpz_get_str: (str: c_str_ptr, base: c_int, op: mpz_srcptr) -> c_str_ptr
    var mpz_get_ui: (op: mpz_srcptr) -> c_unsigned_long_int
    var mpz_getlimbn: (op: mpz_srcptr, n: mp_size_t) -> mp_limb_t
    var mpz_hamdist: (op1: mpz_srcptr, op2: mpz_srcptr) -> mp_bitcnt_t
    var mpz_import: (
        rop: mpz_ptr,
        count: c_size_t,
        order: c_int,
        size: c_size_t,
        endian: c_int,
        nails: c_size_t,
        op: c_void_ptr
    ) -> Unit
    var mpz_init: (x: mpz_ptr) -> Unit
    var mpz_inits: (ptrs: mpz_ptr) -> Unit
    var mpz_init2: (x: mpz_ptr, n: mp_bitcnt_t) -> Unit
    var mpz_init_set: (rop: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_init_set_d: (rop: mpz_ptr, op: c_double) -> Unit
    var mpz_init_set_si: (rop: mpz_ptr, op: c_signed_long_int) -> Unit
    var mpz_init_set_str: (rop: mpz_ptr, str: c_str_ptr, base: c_int) -> c_int
    var mpz_init_set_ui: (rop: mpz_ptr, op: c_unsigned_long_int) -> Unit
    var mpz_invert: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> c_int
    var mpz_ior: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_jacobi: (a: mpz_srcptr, b: mpz_srcptr) -> c_int
    var mpz_kronecker: (a: mpz_srcptr, b: mpz_srcptr) -> c_int
    var mpz_kronecker_si: (a: mpz_srcptr, b: c_signed_long_int) -> c_int
    var mpz_kronecker_ui: (a: mpz_srcptr, b: c_unsigned_long_int) -> c_int
    var mpz_si_kronecker: (a: c_signed_long_int, b: mpz_srcptr) -> c_int
    var mpz_ui_kronecker: (a: c_unsigned_long_int, b: mpz_srcptr) -> c_int
    var mpz_lcm: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_lcm_ui: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_unsigned_long_int) -> Unit
    var mpz_legendre: (a: mpz_srcptr, p: mpz_srcptr) -> c_int
    var mpz_lucnum_ui: (ln: mpz_ptr, n: c_unsigned_long_int) -> Unit
    var mpz_lucnum2_ui: (ln: mpz_ptr, lnsub1: mpz_ptr, n: c_unsigned_long_int) -> Unit
    var mpz_millerrabin: (n: mpz_srcptr, reps: c_int) -> c_int
    var mpz_mod: (r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_mod_ui: (r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_mul: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_mul_2exp: (rop: mpz_ptr, op1: mpz_srcptr, op2: mp_bitcnt_t) -> Unit
    var mpz_mul_si: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_signed_long_int) -> Unit
    var mpz_mul_ui: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_unsigned_long_int) -> Unit
    var mpz_neg: (rop: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_nextprime: (rop: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_odd_p: (op: mpz_srcptr) -> Unit
    var mpz_perfect_power_p: (op: mpz_srcptr) -> c_int
    var mpz_perfect_square_p: (op: mpz_srcptr) -> c_int
    var mpz_popcount: (op: mpz_srcptr) -> mp_bitcnt_t
    var mpz_pow_ui: (rop: mpz_ptr, base: mpz_srcptr, exp: c_unsigned_long_int) -> Unit
    var mpz_powm: (rop: mpz_ptr, base: mpz_srcptr, exp: mpz_srcptr, mod: mpz_srcptr) -> Unit
    var mpz_powm_sec: (rop: mpz_ptr, base: mpz_srcptr, exp: mpz_srcptr, mod: mpz_srcptr) -> Unit
    var mpz_powm_ui: (
        rop: mpz_ptr,
        base: mpz_srcptr,
        exp: c_unsigned_long_int,
        mod: mpz_srcptr
    ) -> Unit
    var mpz_probab_prime_p: (n: mpz_srcptr, reps: c_int) -> c_int
    var mpz_random: (rop: mpz_ptr, maxSize: mp_size_t) -> Unit
    var mpz_random2: (rop: mpz_ptr, maxSize: mp_size_t) -> Unit
    var mpz_realloc2: (x: mpz_ptr, n: mp_bitcnt_t) -> Unit
    var mpz_remove: (rop: mpz_ptr, op: mpz_srcptr, f: mpz_srcptr) -> mp_bitcnt_t
    var mpz_root: (rop: mpz_ptr, op: mpz_srcptr, n: c_unsigned_long_int) -> c_int
    var mpz_rootrem: (root: mpz_ptr, rem: mpz_ptr, u: mpz_srcptr, n: c_unsigned_long_int) -> Unit
    var mpz_rrandomb: (rop: mpz_ptr, state: gmp_randstate_t, n: mp_bitcnt_t) -> Unit
    var mpz_scan0: (op: mpz_srcptr, startingBit: mp_bitcnt_t) -> mp_bitcnt_t
    var mpz_scan1: (op: mpz_srcptr, startingBit: mp_bitcnt_t) -> mp_bitcnt_t
    var mpz_set: (rop: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_set_d: (rop: mpz_ptr, op: c_double) -> Unit
    var mpz_set_q: (rop: mpz_ptr, op: mpq_srcptr) -> Unit
    var mpz_set_si: (rop: mpz_ptr, op: c_signed_long_int) -> Unit
    var mpz_set_str: (rop: mpz_ptr, str: c_str_ptr, base: c_int) -> c_int
    var mpz_set_ui: (rop: mpz_ptr, op: c_unsigned_long_int) -> Unit
    var mpz_setbit: (rop: mpz_ptr, bitIndex: mp_bitcnt_t) -> Unit
    var mpz_sgn: (op: mpz_ptr) -> c_int
    var mpz_size: (op: mpz_srcptr) -> c_size_t
    var mpz_sizeinbase: (op: mpz_srcptr, base: c_int) -> c_size_t
    var mpz_sqrt: (rop: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_sqrtrem: (rop1: mpz_ptr, rop2: mpz_ptr, op: mpz_srcptr) -> Unit
    var mpz_sub: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_sub_ui: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_unsigned_long_int) -> Unit
    var mpz_ui_sub: (rop: mpz_ptr, op1: c_unsigned_long_int, op2: mpz_srcptr) -> Unit
    var mpz_submul: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_submul_ui: (rop: mpz_ptr, op1: mpz_srcptr, op2: c_unsigned_long_int) -> Unit
    var mpz_swap: (rop1: mpz_ptr, rop2: mpz_ptr) -> Unit
    var mpz_tdiv_ui: (n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_tdiv_q: (q: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_tdiv_q_2exp: (q: mpz_ptr, n: mpz_srcptr, b: mp_bitcnt_t) -> Unit
    var mpz_tdiv_q_ui: (q: mpz_ptr, n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_tdiv_qr: (q: mpz_ptr, r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_tdiv_qr_ui: (
        q: mpz_ptr,
        r: mpz_ptr,
        n: mpz_srcptr,
        d: c_unsigned_long_int
    ) -> c_unsigned_long_int
    var mpz_tdiv_r: (r: mpz_ptr, n: mpz_srcptr, d: mpz_srcptr) -> Unit
    var mpz_tdiv_r_2exp: (r: mpz_ptr, n: mpz_srcptr, b: mp_bitcnt_t) -> Unit
    var mpz_tdiv_r_ui: (r: mpz_ptr, n: mpz_srcptr, d: c_unsigned_long_int) -> c_unsigned_long_int
    var mpz_tstbit: (op: mpz_srcptr, bitIndex: mp_bitcnt_t) -> c_int
    var mpz_ui_pow_ui: (rop: mpz_ptr, base: c_unsigned_long_int, exp: c_unsigned_long_int) -> Unit
    var mpz_urandomb: (rop: mpz_ptr, state: gmp_randstate_t, n: mp_bitcnt_t) -> Unit
    var mpz_urandomm: (rop: mpz_ptr, state: gmp_randstate_t, n: mpz_srcptr) -> Unit
    var mpz_xor: (rop: mpz_ptr, op1: mpz_srcptr, op2: mpz_srcptr) -> Unit
    var mpz_limbs_read: (x: mpz_srcptr) -> mp_srcptr
    var mpz_limbs_write: (x: mpz_ptr, n: mp_size_t) -> mp_ptr
    var mpz_limbs_modify: (x: mpz_ptr, n: mp_size_t) -> mp_ptr
    var mpz_limbs_finish: (x: mpz_ptr, s: mp_size_t) -> Unit
    var mpz_roinit_n: (x: mpz_ptr, xp: mp_srcptr, xs: mp_size_t) -> mpz_srcptr
    var mpq_t: () -> mpq_ptr
    var mpq_t_free: (mpq_ptr: Number) -> Unit
    var mpq_t_frees: (ptrs: mpq_ptr) -> Unit
    var mpq_abs: (rop: mpq_ptr, op: mpq_srcptr) -> Unit
    var mpq_add: (sum: mpq_ptr, addend1: mpq_srcptr, addend2: mpq_srcptr) -> Unit
    var mpq_canonicalize: (op: mpq_ptr) -> Unit
    var mpq_clear: (x: mpq_ptr) -> Unit
    var mpq_clears: (ptrs: mpq_ptr) -> Unit
    var mpq_cmp: (op1: mpq_srcptr, op2: mpq_srcptr) -> c_int
    var mpq_cmp_si: (op1: mpq_srcptr, num2: c_signed_long_int, den2: c_unsigned_long_int) -> c_int
    var mpq_cmp_ui: (op1: mpq_srcptr, num2: c_unsigned_long_int, den2: c_unsigned_long_int) -> c_int
    var mpq_cmp_z: (op1: mpq_srcptr, op2: mpz_srcptr) -> c_int
    var mpq_div: (quotient: mpq_ptr, dividend: mpq_srcptr, divisor: mpq_srcptr) -> Unit
    var mpq_div_2exp: (rop: mpq_ptr, op1: mpq_srcptr, op2: mp_bitcnt_t) -> Unit
    var mpq_equal: (op1: mpq_srcptr, op2: mpq_srcptr) -> c_int
    var mpq_get_num: (numerator: mpz_ptr, rational: mpq_srcptr) -> Unit
    var mpq_get_den: (denominator: mpz_ptr, rational: mpq_srcptr) -> Unit
    var mpq_get_d: (op: mpq_srcptr) -> c_double
    var mpq_get_str: (str: c_str_ptr, base: c_int, op: mpq_srcptr) -> c_str_ptr
    var mpq_init: (x: mpq_ptr) -> Unit
    var mpq_inits: (ptrs: mpq_ptr) -> Unit
    var mpq_inv: (inverted_number: mpq_ptr, number: mpq_srcptr) -> Unit
    var mpq_mul: (product: mpq_ptr, multiplier: mpq_srcptr, multiplicand: mpq_srcptr) -> Unit
    var mpq_mul_2exp: (rop: mpq_ptr, op1: mpq_srcptr, op2: mp_bitcnt_t) -> Unit
    var mpq_neg: (negated_operand: mpq_ptr, operand: mpq_srcptr) -> Unit
    var mpq_set: (rop: mpq_ptr, op: mpq_srcptr) -> Unit
    var mpq_set_d: (rop: mpq_ptr, op: c_double) -> Unit
    var mpq_set_den: (rational: mpq_ptr, denominator: mpz_srcptr) -> Unit
    var mpq_set_num: (rational: mpq_ptr, numerator: mpz_srcptr) -> Unit
    var mpq_set_si: (rop: mpq_ptr, op1: c_signed_long_int, op2: c_unsigned_long_int) -> Unit
    var mpq_set_str: (rop: mpq_ptr, str: c_str_ptr, base: c_int) -> c_int
    var mpq_set_ui: (rop: mpq_ptr, op1: c_unsigned_long_int, op2: c_unsigned_long_int) -> Unit
    var mpq_set_z: (rop: mpq_ptr, op: mpz_srcptr) -> Unit
    var mpq_sgn: (op: mpq_ptr) -> c_int
    var mpq_sub: (difference: mpq_ptr, minuend: mpq_srcptr, subtrahend: mpq_srcptr) -> Unit
    var mpq_swap: (rop1: mpq_ptr, rop2: mpq_ptr) -> Unit
    var mpfr_t: () -> mpfr_ptr
    var mpfr_t_free: (mpfr_ptr: Number) -> Unit
    var mpfr_t_frees: (ptrs: mpfr_ptr) -> Unit
    var mpfr_get_version: () -> c_str_ptr
    var mpfr_get_patches: () -> c_str_ptr
    var mpfr_buildopt_tls_p: () -> c_int
    var mpfr_buildopt_float128_p: () -> c_int
    var mpfr_buildopt_decimal_p: () -> c_int
    var mpfr_buildopt_gmpinternals_p: () -> c_int
    var mpfr_buildopt_sharedcache_p: () -> c_int
    var mpfr_buildopt_tune_case: () -> c_str_ptr
    var mpfr_get_emin: () -> mpfr_exp_t
    var mpfr_set_emin: (exp: mpfr_exp_t) -> c_int
    var mpfr_get_emin_min: () -> mpfr_exp_t
    var mpfr_get_emin_max: () -> mpfr_exp_t
    var mpfr_get_emax: () -> mpfr_exp_t
    var mpfr_set_emax: (exp: mpfr_exp_t) -> c_int
    var mpfr_get_emax_min: () -> mpfr_exp_t
    var mpfr_get_emax_max: () -> mpfr_exp_t
    var mpfr_set_default_rounding_mode: (rnd: mpfr_rnd_t) -> Unit
    var mpfr_get_default_rounding_mode: () -> mpfr_rnd_t
    var mpfr_print_rnd_mode: (rnd: mpfr_rnd_t) -> c_str_ptr
    var mpfr_clear_flags: () -> Unit
    var mpfr_clear_underflow: () -> Unit
    var mpfr_clear_overflow: () -> Unit
    var mpfr_clear_divby0: () -> Unit
    var mpfr_clear_nanflag: () -> Unit
    var mpfr_clear_inexflag: () -> Unit
    var mpfr_clear_erangeflag: () -> Unit
    var mpfr_set_underflow: () -> Unit
    var mpfr_set_overflow: () -> Unit
    var mpfr_set_divby0: () -> Unit
    var mpfr_set_nanflag: () -> Unit
    var mpfr_set_inexflag: () -> Unit
    var mpfr_set_erangeflag: () -> Unit
    var mpfr_underflow_p: () -> c_int
    var mpfr_overflow_p: () -> c_int
    var mpfr_divby0_p: () -> c_int
    var mpfr_nanflag_p: () -> c_int
    var mpfr_inexflag_p: () -> c_int
    var mpfr_erangeflag_p: () -> c_int
    var mpfr_flags_clear: (mask: mpfr_flags_t) -> Unit
    var mpfr_flags_set: (mask: mpfr_flags_t) -> Unit
    var mpfr_flags_test: (mask: mpfr_flags_t) -> mpfr_flags_t
    var mpfr_flags_save: () -> mpfr_flags_t
    var mpfr_flags_restore: (flags: mpfr_flags_t, mask: mpfr_flags_t) -> Unit
    var mpfr_check_range: (x: mpfr_ptr, t: c_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_init2: (x: mpfr_ptr, prec: mpfr_prec_t) -> Unit
    var mpfr_inits2: (prec: mpfr_prec_t, ptrs: mpfr_ptr) -> Unit
    var mpfr_init: (x: mpfr_ptr) -> Unit
    var mpfr_inits: (ptrs: mpfr_ptr) -> Unit
    var mpfr_clear: (x: mpfr_ptr) -> Unit
    var mpfr_clears: (ptrs: mpfr_ptr) -> Unit
    var mpfr_prec_round: (x: mpfr_ptr, prec: mpfr_prec_t, rnd: mpfr_rnd_t) -> c_int
    var mpfr_can_round: (
        b: mpfr_srcptr,
        err: mpfr_exp_t,
        rnd1: mpfr_rnd_t,
        rnd2: mpfr_rnd_t,
        prec: mpfr_prec_t
    ) -> c_int
    var mpfr_min_prec: (x: mpfr_srcptr) -> mpfr_prec_t
    var mpfr_get_exp: (x: mpfr_srcptr) -> mpfr_exp_t
    var mpfr_set_exp: (x: mpfr_ptr, e: mpfr_exp_t) -> c_int
    var mpfr_get_prec: (x: mpfr_srcptr) -> mpfr_prec_t
    var mpfr_set_prec: (x: mpfr_ptr, prec: mpfr_prec_t) -> Unit
    var mpfr_set_prec_raw: (x: mpfr_ptr, prec: mpfr_prec_t) -> Unit
    var mpfr_set_default_prec: (prec: mpfr_prec_t) -> Unit
    var mpfr_get_default_prec: () -> mpfr_prec_t
    var mpfr_set_d: (rop: mpfr_ptr, op: c_double, rnd: mpfr_rnd_t) -> c_int
    var mpfr_set_z: (rop: mpfr_ptr, op: mpz_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_set_z_2exp: (rop: mpfr_ptr, op: mpz_srcptr, e: mpfr_exp_t, rnd: mpfr_rnd_t) -> c_int
    var mpfr_set_nan: (x: mpfr_ptr) -> Unit
    var mpfr_set_inf: (x: mpfr_ptr, sign: c_int) -> Unit
    var mpfr_set_zero: (x: mpfr_ptr, sign: c_int) -> Unit
    var mpfr_set_si: (rop: mpfr_ptr, op: c_signed_long_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_set_ui: (rop: mpfr_ptr, op: c_unsigned_long_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_set_si_2exp: (
        rop: mpfr_ptr,
        op: c_signed_long_int,
        e: mpfr_exp_t,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_set_ui_2exp: (
        rop: mpfr_ptr,
        op: c_unsigned_long_int,
        e: mpfr_exp_t,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_set_q: (rop: mpfr_ptr, op: mpq_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_mul_q: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpq_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_div_q: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpq_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_add_q: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpq_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sub_q: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpq_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_cmp_q: (op1: mpfr_srcptr, op2: mpq_srcptr) -> c_int
    var mpfr_get_q: (rop: mpq_ptr, op: mpfr_srcptr) -> Unit
    var mpfr_set_str: (rop: mpfr_ptr, s: c_str_ptr, base: c_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_init_set: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> Any
    var mpfr_init_set_ui: (rop: mpfr_ptr, op: Number, rnd: mpfr_rnd_t) -> Any
    var mpfr_init_set_si: (rop: mpfr_ptr, op: Number, rnd: mpfr_rnd_t) -> Any
    var mpfr_init_set_d: (rop: mpfr_ptr, op: Number, rnd: mpfr_rnd_t) -> Any
    var mpfr_init_set_z: (rop: mpfr_ptr, op: mpz_srcptr, rnd: mpfr_rnd_t) -> Any
    var mpfr_init_set_q: (rop: mpfr_ptr, op: mpq_srcptr, rnd: mpfr_rnd_t) -> Any
    var mpfr_init_set_str: (x: mpfr_ptr, s: c_str_ptr, base: c_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_abs: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_set: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_neg: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_signbit: (op: mpfr_srcptr) -> c_int
    var mpfr_setsign: (rop: mpfr_ptr, op: mpfr_srcptr, s: c_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_copysign: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_get_z_2exp: (rop: mpz_ptr, op: mpfr_srcptr) -> mpfr_exp_t
    var mpfr_get_d: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_double
    var mpfr_get_d_2exp: (exp: c_long_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_double
    var mpfr_frexp: (exp: mpfr_exp_t_ptr, y: mpfr_ptr, x: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_get_si: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_signed_long_int
    var mpfr_get_ui: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_unsigned_long_int
    var mpfr_get_str_ndigits: (b: c_int, p: mpfr_prec_t) -> c_size_t
    var mpfr_get_str: (
        str: c_str_ptr,
        expptr: mpfr_exp_t_ptr,
        base: c_int,
        n: c_size_t,
        op: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_str_ptr
    var mpfr_get_z: (rop: mpz_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_free_str: (str: c_str_ptr) -> Unit
    var mpfr_urandom: (rop: mpfr_ptr, state: gmp_randstate_t, rnd: mpfr_rnd_t) -> c_int
    var mpfr_nrandom: (rop: mpfr_ptr, state: gmp_randstate_t, rnd: mpfr_rnd_t) -> c_int
    var mpfr_erandom: (rop: mpfr_ptr, state: gmp_randstate_t, rnd: mpfr_rnd_t) -> c_int
    var mpfr_urandomb: (rop: mpfr_ptr, state: gmp_randstate_t) -> c_int
    var mpfr_nextabove: (x: mpfr_ptr) -> Unit
    var mpfr_nextbelow: (x: mpfr_ptr) -> Unit
    var mpfr_nexttoward: (x: mpfr_ptr, y: mpfr_srcptr) -> Unit
    var mpfr_pow: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_pow_si: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_signed_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_pow_ui: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_ui_pow_ui: (
        rop: mpfr_ptr,
        op1: c_unsigned_long_int,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_ui_pow: (
        rop: mpfr_ptr,
        op1: c_unsigned_long_int,
        op2: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_pow_z: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpz_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sqrt: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sqrt_ui: (rop: mpfr_ptr, op: c_unsigned_long_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_rec_sqrt: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_add: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sub: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_mul: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_div: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_add_ui: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_sub_ui: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_ui_sub: (
        rop: mpfr_ptr,
        op1: c_unsigned_long_int,
        op2: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_mul_ui: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_div_ui: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_ui_div: (
        rop: mpfr_ptr,
        op1: c_unsigned_long_int,
        op2: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_add_si: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_signed_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_sub_si: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_signed_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_si_sub: (
        rop: mpfr_ptr,
        op1: c_signed_long_int,
        op2: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_mul_si: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_signed_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_div_si: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_signed_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_si_div: (
        rop: mpfr_ptr,
        op1: c_signed_long_int,
        op2: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_add_d: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: c_double, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sub_d: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: c_double, rnd: mpfr_rnd_t) -> c_int
    var mpfr_d_sub: (rop: mpfr_ptr, op1: c_double, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_mul_d: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: c_double, rnd: mpfr_rnd_t) -> c_int
    var mpfr_div_d: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: c_double, rnd: mpfr_rnd_t) -> c_int
    var mpfr_d_div: (rop: mpfr_ptr, op1: c_double, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sqr: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_const_pi: (rop: mpfr_ptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_const_log2: (rop: mpfr_ptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_const_euler: (rop: mpfr_ptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_const_catalan: (rop: mpfr_ptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_agm: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_log: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_log2: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_log10: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_log1p: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_log_ui: (rop: mpfr_ptr, op: c_unsigned_long_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_exp: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_exp2: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_exp10: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_expm1: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_eint: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_li2: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_cmp: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_cmp_d: (op1: mpfr_srcptr, op2: c_double) -> c_int
    var mpfr_cmp_ui: (op1: mpfr_srcptr, op2: c_unsigned_long_int) -> c_int
    var mpfr_cmp_si: (op1: mpfr_srcptr, op2: c_signed_long_int) -> c_int
    var mpfr_cmp_ui_2exp: (op1: mpfr_srcptr, op2: c_unsigned_long_int, e: mpfr_exp_t) -> c_int
    var mpfr_cmp_si_2exp: (op1: mpfr_srcptr, op2: c_signed_long_int, e: mpfr_exp_t) -> c_int
    var mpfr_cmpabs: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_cmpabs_ui: (op1: mpfr_srcptr, op2: c_unsigned_long_int) -> c_int
    var mpfr_reldiff: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> Unit
    var mpfr_eq: (op1: mpfr_srcptr, op2: mpfr_srcptr, op3: c_unsigned_long_int) -> c_int
    var mpfr_sgn: (op: mpfr_srcptr) -> c_int
    var mpfr_mul_2exp: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_div_2exp: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_mul_2ui: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_div_2ui: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_mul_2si: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_signed_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_div_2si: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: c_signed_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_rint: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_roundeven: (rop: mpfr_ptr, op: mpfr_srcptr) -> c_int
    var mpfr_round: (rop: mpfr_ptr, op: mpfr_srcptr) -> c_int
    var mpfr_trunc: (rop: mpfr_ptr, op: mpfr_srcptr) -> c_int
    var mpfr_ceil: (rop: mpfr_ptr, op: mpfr_srcptr) -> c_int
    var mpfr_floor: (rop: mpfr_ptr, op: mpfr_srcptr) -> c_int
    var mpfr_rint_roundeven: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_rint_round: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_rint_trunc: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_rint_ceil: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_rint_floor: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_frac: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_modf: (rop: mpfr_ptr, fop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_remquo: (
        r: mpfr_ptr,
        q: c_long_ptr,
        x: mpfr_srcptr,
        y: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_remainder: (rop: mpfr_ptr, x: mpfr_srcptr, y: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fmod: (rop: mpfr_ptr, x: mpfr_srcptr, y: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fmodquo: (
        rop: mpfr_ptr,
        q: c_long_ptr,
        x: mpfr_srcptr,
        y: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_fits_ulong_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fits_slong_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fits_uint_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fits_sint_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fits_ushort_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fits_sshort_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fits_uintmax_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fits_intmax_p: (op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_swap: (x: mpfr_ptr, y: mpfr_ptr) -> Unit
    var mpfr_dump: (op: mpfr_srcptr) -> Unit
    var mpfr_nan_p: (op: mpfr_srcptr) -> c_int
    var mpfr_inf_p: (op: mpfr_srcptr) -> c_int
    var mpfr_number_p: (op: mpfr_srcptr) -> c_int
    var mpfr_integer_p: (op: mpfr_srcptr) -> c_int
    var mpfr_zero_p: (op: mpfr_srcptr) -> c_int
    var mpfr_regular_p: (op: mpfr_srcptr) -> c_int
    var mpfr_greater_p: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_greaterequal_p: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_less_p: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_lessequal_p: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_lessgreater_p: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_equal_p: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_unordered_p: (op1: mpfr_srcptr, op2: mpfr_srcptr) -> c_int
    var mpfr_atanh: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_acosh: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_asinh: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_cosh: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sinh: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_tanh: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sinh_cosh: (sop: mpfr_ptr, cop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sech: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_csch: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_coth: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_acos: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_asin: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_atan: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sin: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sin_cos: (sop: mpfr_ptr, cop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_cos: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_tan: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_atan2: (rop: mpfr_ptr, y: mpfr_srcptr, x: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sec: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_csc: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_cot: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_hypot: (rop: mpfr_ptr, x: mpfr_srcptr, y: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_erf: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_erfc: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_cbrt: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_rootn_ui: (
        rop: mpfr_ptr,
        op: mpfr_srcptr,
        k: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_gamma: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_gamma_inc: (rop: mpfr_ptr, op: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_beta: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_lngamma: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_lgamma: (rop: mpfr_ptr, signp: c_int_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_digamma: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_zeta: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_zeta_ui: (rop: mpfr_ptr, op: c_unsigned_long_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_fac_ui: (rop: mpfr_ptr, op: c_unsigned_long_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_j0: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_j1: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_jn: (rop: mpfr_ptr, n: c_signed_long_int, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_y0: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_y1: (rop: mpfr_ptr, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_yn: (rop: mpfr_ptr, n: c_signed_long_int, op: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_ai: (rop: mpfr_ptr, x: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_min: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_max: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_dim: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_mul_z: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpz_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_div_z: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpz_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_add_z: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpz_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_sub_z: (rop: mpfr_ptr, op1: mpfr_srcptr, op2: mpz_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_z_sub: (rop: mpfr_ptr, op1: mpz_srcptr, op2: mpfr_srcptr, rnd: mpfr_rnd_t) -> c_int
    var mpfr_cmp_z: (op1: mpfr_srcptr, op2: mpz_srcptr) -> c_int
    var mpfr_fma: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: mpfr_srcptr,
        op3: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_fms: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: mpfr_srcptr,
        op3: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_fmma: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: mpfr_srcptr,
        op3: mpfr_srcptr,
        op4: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_fmms: (
        rop: mpfr_ptr,
        op1: mpfr_srcptr,
        op2: mpfr_srcptr,
        op3: mpfr_srcptr,
        op4: mpfr_srcptr,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_sum: (
        rop: mpfr_ptr,
        tab: mpfr_ptr_ptr,
        n: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_dot: (
        rop: mpfr_ptr,
        a: mpfr_ptr_ptr,
        b: mpfr_ptr_ptr,
        n: c_unsigned_long_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_free_cache: () -> Unit
    var mpfr_free_cache2: (way: mpfr_free_cache_t) -> Unit
    var mpfr_free_pool: () -> Unit
    var mpfr_mp_memory_cleanup: () -> c_int
    var mpfr_subnormalize: (x: mpfr_ptr, t: c_int, rnd: mpfr_rnd_t) -> c_int
    var mpfr_strtofr: (
        rop: mpfr_ptr,
        nptr: c_str_ptr,
        endptr: c_str_ptr_ptr,
        base: c_int,
        rnd: mpfr_rnd_t
    ) -> c_int
    var mpfr_custom_get_size: (prec: mpfr_prec_t) -> c_size_t
    var mpfr_custom_init: (significand: c_void_ptr, prec: mpfr_prec_t) -> Unit
    var mpfr_custom_get_significand: (x: mpfr_srcptr) -> c_void_ptr
    var mpfr_custom_get_exp: (x: mpfr_srcptr) -> mpfr_exp_t
    var mpfr_custom_move: (x: mpfr_ptr, new_position: c_void_ptr) -> Unit
    var mpfr_custom_init_set: (
        x: mpfr_ptr,
        kind: c_int,
        exp: mpfr_exp_t,
        prec: mpfr_prec_t,
        significand: c_void_ptr
    ) -> Unit
    var mpfr_custom_get_kind: (x: mpfr_srcptr) -> c_int
    var mpfr_total_order_p: (x: mpfr_srcptr, y: mpfr_srcptr) -> c_int
}