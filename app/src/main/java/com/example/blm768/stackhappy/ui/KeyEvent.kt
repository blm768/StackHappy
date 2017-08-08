package com.example.blm768.stackhappy.ui

import com.example.blm768.stackhappy.Stack
import com.example.blm768.stackhappy.StackOperation

/**
 *
 */
abstract class KeyEvent {
    abstract fun act(listener: Listener)

    interface Listener {
        fun enterText(text: String)
        fun doOperation(operation: StackOperation)
    }
}

class NumberKeyEvent: KeyEvent() {
    private lateinit var digit: String

    override fun act(listener: Listener) {
        listener.enterText(digit)
    }
}