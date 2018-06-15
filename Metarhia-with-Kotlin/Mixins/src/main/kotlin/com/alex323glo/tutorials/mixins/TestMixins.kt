package com.alex323glo.tutorials.mixins

fun main(args: Array<String>) {
    // println(mixedInProperty)     // will cause an error!
    println(Source.prop2)
    Source.prop2 = 10
    println(Source.prop2)
}