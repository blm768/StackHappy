package com.example.blm768.stackhappy

import java.math.BigDecimal

/**
 *
 */
// TODO: make this parcelable.
class Stack {
    // TODO: make this work.
    //private var entries: List<StackItem> = List()

    fun push(item: StackItem) {
        // TODO: implement.
    }
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

