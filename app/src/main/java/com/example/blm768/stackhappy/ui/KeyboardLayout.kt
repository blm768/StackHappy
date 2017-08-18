package com.example.blm768.stackhappy.ui

import com.example.blm768.stackhappy.operations
import org.json.JSONObject

data class KeyboardLayout(val columnCount: Int, val keys: List<KeySpec>) {
    companion object {
        // TODO: handle exceptions better?
        fun fromJSON(jsonText: String): KeyboardLayout {
            val json = JSONObject(jsonText)
            // TODO: validate range?
            val columnCount = json.getInt("columns")
            val keysArray = json.getJSONArray("keys")
            val keys = (0 until keysArray.length()).map { i ->
                val specObject = keysArray.getJSONObject(i)
                KeySpec.fromJSONObject(specObject)
            }
            return KeyboardLayout(columnCount, keys)
        }
    }

}

data class KeySpec(val label: String, val event: KeyEvent) {
    companion object {
        fun fromJSONObject(jsonObject: JSONObject): KeySpec {
            // TODO: handle missing keys properly.
            val label = jsonObject.getString("label")
            val type = jsonObject.getString("type")
            val event = when (type) {
                "text" -> TextKeyEvent(jsonObject.getString("text"))
                "push" -> PushKeyEvent()
            // TODO: handle missing operations more cleanly.
                "operation" -> OperationKeyEvent(operations[jsonObject.getString("op")]!!)
            // TODO: improve error message?
                else -> {
                    throw IllegalArgumentException("Invalid key type")
                }
            }
            return KeySpec(label, event)
        }
    }
}

class InvalidKeyboardSpecException(ex: Exception) : Exception(ex)
