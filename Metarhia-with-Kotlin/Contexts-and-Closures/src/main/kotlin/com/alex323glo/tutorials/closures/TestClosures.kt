package com.alex323glo.tutorials.closures

fun main(args: Array<String>) {
    val getAndIncrement = foo()
    println("Context of foo(): \"${getAndIncrement()}\"")
    println("Updated context of foo(): \"${getAndIncrement()}\"")

    // Proof of creation of new frame for foo() each time when we call foo():
    val getAndIncrement2 = foo()
    println("Context of foo() after re-invoke of foo(): ${getAndIncrement()}")
    println("Context of foo(), captured from another (new) closure: ${getAndIncrement2()}")

    val sum = add(1)(2)
    println("1 + 2 = $sum")
}

// First closure example:
fun foo(): () -> Int {
    var barBar = 0
    return {barBar++}
}

// Second closure example:
val add = {a: Int -> {b: Int -> a + b}}