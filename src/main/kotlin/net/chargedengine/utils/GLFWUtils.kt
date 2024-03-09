package net.chargedengine.utils

import org.lwjgl.glfw.GLFW

fun glfwUtilConvertBoolean(boolean: Boolean): Int {
    return when (boolean) {
        false -> GLFW.GLFW_FALSE
        true -> GLFW.GLFW_TRUE
    }
}

fun glfwUtilConvertBoolean(int: Int): Boolean {
    return when (int) {
        0 -> false
        1 -> true
        else -> false
    }
}