package net.chargedengine.gameobject

import com.github.meo209.kevet.KevetEvent
import com.github.meo209.kevet.KevetListener
import com.github.meo209.kevet.handler
import com.github.meo209.kevet.register
import net.chargedengine.utils.Vector2

open class GameObject {

    var position: Vector2 = Vector2()
    var rotation: Float = 0f
    var width: Float = 0.0f
    var height: Float = 0.0f

    var parent: GameObject? = null

    fun withProperties(position: Vector2, rotation: Float, width: Float, height: Float): GameObject {
        this.position = position
        this.rotation = rotation
        this.width = width
        this.height = height
        return this
    }

    fun child(child: GameObject): GameObject {
        child.parent = this
        child.position = child.position.plus(position)
        return this
    }

    inline fun <reified T : Any> withHandler(crossinline callback: suspend (T) -> Unit, priority: Int = 0): GameObject {
        handler<T>(callback, priority)
        return this
    }

}