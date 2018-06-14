package com.alex323glo.tutorials.contexts.public_context

// This import will cause an error:
//import com.alex323glo.tutorials.contexts_and_closures.contexts.superPuperPrivateBar

val superPuperBar = "Hi from global context!"

fun superPuperFoo() {
    println("superPuperFoo(): $superPuperBar")
    // println("superPuperFoo(): $superPuperPrivateBar")    // Will cause an error!
}