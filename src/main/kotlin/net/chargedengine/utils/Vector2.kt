package net.chargedengine.utils


data class Vector2(var x: Float = 0.0f, var y: Float = 0.0f) {

    operator fun plus(other: Vector2): Vector2 {
        return Vector2(x + other.x, y + other.y)
    }

    operator fun minus(other: Vector2): Vector2 {
        return Vector2(x - other.x, y - other.y)
    }

    operator fun times(value: Float): Vector2 {
        return Vector2(x * value, y * value)
    }

    operator fun div(value: Float): Vector2 {
        require(value != 0f) { "Division by zero" }
        return Vector2(x / value, y / value)
    }

    fun length(): Float {
        return kotlin.math.sqrt(x * x + y * y)
    }

    fun normalized(): Vector2 {
        val len = length()
        require(len != 0f) { "Cannot normalize zero vector" }
        return this / len
    }

    fun dot(other: Vector2): Float {
        return x * other.x + y * other.y
    }

    fun cross(other: Vector2): Float {
        return x * other.y - y * other.x
    }
}
