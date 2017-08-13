package com.example.blm768.stackhappy

import java.math.BigDecimal

/**
 *
 */
// TODO: make this parcelable.
class Stack {
    private var entries: MutableList<StackItem> = mutableListOf()

    fun push(item: StackItem) {
        entries.add(item)
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
                throw InvalidStackItemException()
            }
        }
    }
}

class InvalidStackItemException : Exception() {}

fun add(a: StackItem, b: StackItem): StackItem? {
    return StackItem(a.value + b.value)
}

