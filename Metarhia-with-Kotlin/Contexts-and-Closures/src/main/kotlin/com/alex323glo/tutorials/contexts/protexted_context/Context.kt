package com.alex323glo.tutorials.contexts.protexted_context

import com.alex323glo.tutorials.contexts.public_context.superPuperBar

private val superPuperPrivateBar = "Hi from protected context!"

fun superPuperPrivateFoo() {
    println("superPuperPrivateFoo(): $superPuperBar")
    println("superPuperPrivateFoo(): $superPuperPrivateBar")
}