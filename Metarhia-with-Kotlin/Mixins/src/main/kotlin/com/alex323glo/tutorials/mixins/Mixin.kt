package com.alex323glo.tutorials.mixins

/**
 * Simple mixin.
 *
 * Main idea - create standalone static private property,
 * which could be accessed ONLY from Source object (explicit get() and set()).
 *
 * Sounds like mixin!
 */
private var mixedInProperty = 0
var Source.prop2: Int
    get() = mixedInProperty
    set(value) {
        mixedInProperty = value
    }