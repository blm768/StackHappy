package com.example.blm768.stackhappy.util

import android.content.res.Resources

/**
 * Utility functions for resources
 */

fun readRawResource(resources: Resources, id: Int): String {
    return resources.openRawResource(id).bufferedReader().use { it.readText() }
}
