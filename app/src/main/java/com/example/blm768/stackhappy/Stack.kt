package com.example.blm768.stackhappy

import java.math.BigDecimal

// TODO: make this configurable?
val STACK_VALUE_SCALE = 10
val STACK_VALUE_ROUNDING = BigDecimal.ROUND_HALF_UP

/**
 *
 */
// TODO: make this parcelable.
class Stack {
    val items: MutableList<StackItem> = mutableListOf()

    fun push(item: StackItem) = items.add(item)
    fun pop(): StackItem = items.removeAt(items.lastIndex)
}

class StackItem(var value: BigDecimal) {
    fun text(): String = value.toString()

    companion object {
        fun fromString(str: String): StackItem {
            try {
                val value = BigDecimal(str)
                // TODO: figure out how to actually make this work like I want it to.
                value.setScale(STACK_VALUE_SCALE, STACK_VALUE_ROUNDING)
                return StackItem(value)
            } catch(ex: NumberFormatException) {
                throw InvalidStackItemException(ex)
            }
        }
    }
}

class InvalidStackItemException : Exception {
    constructor(ex: Exception) : super(ex)
    constructor() : super()
}

class StackUnderflowException : Exception()

fun binaryOp(operation: (StackItem, StackItem) -> StackItem): (Stack) -> Unit {
    return { stack: Stack ->
        if (stack.items.size < 2) throw StackUnderflowException()
        val b = stack.pop()
        val a = stack.pop()
        stack.push(operation(a, b))
    }
}

fun add(a: StackItem, b: StackItem): StackItem {
    return StackItem(a.value + b.value)
}

fun subtract(a: StackItem, b: StackItem): StackItem {
    return StackItem(a.value - b.value)
}

fun multiply(a: StackItem, b: StackItem): StackItem {
    return StackItem(a.value * b.value)
}

fun divide(a: StackItem, b: StackItem): StackItem {
    return StackItem(a.value / b.value)
}

// TODO: change case?
// TODO: eliminate global state?
val operations: Map<String, (Stack) -> Unit> = mapOf(
        "add" to binaryOp(::add),
        "subtract" to binaryOp(::subtract),
        "multiply" to binaryOp(::multiply),
        "divide" to binaryOp(::divide)
)

