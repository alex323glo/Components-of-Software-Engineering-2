package com.alex323glo.tutorials.contexts

import com.alex323glo.tutorials.contexts.protexted_context.superPuperPrivateFoo
import com.alex323glo.tutorials.contexts.public_context.superPuperBar
import com.alex323glo.tutorials.contexts.public_context.superPuperFoo

fun main(args: Array<String>) {
    // Test inter-package context operations:
    superPuperFoo()
    superPuperPrivateFoo()

    // Test current-package context operations:
    foo()
}


// Global context (shared from this package):
private val superBar = "Hi from local context!"

fun foo() {
    // foo() function's context:
    val bar = "Hi from foo() context!"

    fun fooFoo() {
        // fooFoo() function's context:
        val barBar = "Hi from fooFoo() context!"

        fun fooFooFoo() {
            // fooFooFoo() function's context:
            val barBarBar = "Hi from fooFooFoo() context!"

            /*  Here could be placed more "deep" contexts...
                They will have access to:
                    * global context
                    * foo2's context
                    * fooFoo's context
                    * fooFooFoo's context
                    * their own contexts
            */

            println("fooFooFoo(): $superPuperBar")
            println("fooFooFoo(): $superBar")
            println("fooFooFoo(): $bar")
            println("fooFooFoo(): $barBar")
            println("fooFooFoo(): $barBarBar")

        }

        println("fooFoo(): $superPuperBar")
        println("fooFoo(): $superBar")
        println("fooFoo(): $bar")
        println("fooFoo(): $barBar")
        // println("fooFoo(): $barBarBar")      // will cause an error!
        fooFooFoo()

    }

    println("foo(): $superPuperBar")
    println("foo(): $superBar")
    println("foo(): $bar")
    // println("foo2(): $barBar")        // will cause an error!
    // println("foo2(): $barBarBar")     // will cause an error!
    fooFoo()

}