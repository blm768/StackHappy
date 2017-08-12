package com.example.blm768.stackhappy.ui

import com.example.blm768.stackhappy.Stack

/**
 *
 */
abstract class KeyEvent {
    abstract fun act(listener: Listener)

    interface Listener {
        fun enterText(text: String)
        fun doOperation(operation: (Stack) -> Unit)
    }
}

class TextKeyEvent(val text: String): KeyEvent() {
    override fun act(listener: Listener) {
        listener.enterText(text)
    }
}

class OperationEvent(val operation: (Stack) -> Unit) : KeyEvent() {
    override fun act(listener: Listener) {
        listener.doOperation(operation)
    }
}