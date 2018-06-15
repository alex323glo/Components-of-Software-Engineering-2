package com.alex323glo.tutorials.wrappers

/**
 * Simple summarize function.
 */
fun sum(left: Int, right: Int): Int  = left + right

/**
 * Wrapper over sum() functions, which performs additional logging.
 */
fun loggedSum(left: Int, right: Int): Int {
    println("*** Starting sum($left,$right) calculation...")
    val result = sum(left, right)
    println("*** Successfully calculated sum: $left + $right = $result.")
    return result
}

/**
 * Extension function for Int, which uses for calculation
 * one of the functions from above, depending on 'isLogged' flag.
 * */
fun Int.sum(that: Int, isLogged: Boolean): Int = if (isLogged) {
    loggedSum(this, that)
} else {
    sum(this, that)
}