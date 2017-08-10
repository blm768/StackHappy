package com.example.blm768.stackhappy

import java.math.BigDecimal

/**
 *
 */
class Stack {
    // TODO: see if there's a native Kotlin collection that we want to consider using.
    private var entries: java.util.Stack<StackItem> = java.util.Stack()
}

class StackItem(var value: BigDecimal) {

    fun text(): String {
        return value.toString()
    }
}

// TODO: return an algebraic type with an error message?
fun add(a: StackItem, b: StackItem): StackItem? {
    return StackItem(a.value + b.value)
}

