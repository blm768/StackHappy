package com.example.blm768.stackhappy

/**
 *
 */
class Stack {
   private var entries: java.util.Stack<StackItem> = java.util.Stack()
}

interface StackItem {
    fun text(): String
}

abstract class StackOperation {
    abstract fun operate(stack: Stack)
}
