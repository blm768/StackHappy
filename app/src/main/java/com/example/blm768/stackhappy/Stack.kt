package com.example.blm768.stackhappy

import java.math.BigDecimal

/**
 *
 */
// TODO: make this parcelable.
class Stack {
    val items: MutableList<StackItem> = mutableListOf()

    fun push(item: StackItem) {
        items.add(item)
    }
}

class StackItem(var value: BigDecimal) {

    fun text(): String {
        return value.toString()
    }

    companion object {
        fun fromString(str: String): StackItem {
            try {
                return StackItem(BigDecimal(str))
            } catch(ex: NumberFormatException) {
                throw InvalidStackItemException(ex)
            }
        }
    }
}

class InvalidStackItemException : Exception {
    constructor(ex: Exception) : super(ex) {}
    constructor() : super() {}
}

fun add(a: StackItem, b: StackItem): StackItem? {
    return StackItem(a.value + b.value)
}

