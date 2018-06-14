package com.alex323glo.tutorials.closures

fun main(args: Array<String>) {
    val barGetter = foo2()
    println("foo2() context: \"${barGetter()}\"")

    foo2.bar = "new bar"
    println("foo2() changed context: \"${barGetter()}\"")
}

object foo2 {

    var bar = "bar"

    operator fun invoke() = { bar }
}