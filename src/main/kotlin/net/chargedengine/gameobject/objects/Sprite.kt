package net.chargedengine.gameobject.objects

import com.github.meo209.kevet.handler
import net.chargedengine.Engine
import net.chargedengine.events.GLFWInitializationEvent
import net.chargedengine.events.GLFWRenderEvent
import net.chargedengine.gameobject.GameObject
import org.lwjgl.opengl.GL11
import org.lwjgl.stb.STBImage

class Sprite(val resourcePath: String): GameObject() {

    var textureId = -1

    val initHandler = handler<GLFWInitializationEvent>({
        val w = IntArray(1)
        val h = IntArray(1)
        val comp = IntArray(1)

        val image = STBImage.stbi_load(resourcePath, w, h, comp, 4)
            ?: throw RuntimeException("Failed to load texture: " + STBImage.stbi_failure_reason())

        val textureId = GL11.glGenTextures()
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST)
        GL11.glTexImage2D(
            GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA,
            w[0], h[0], 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image
        )

        this.textureId = textureId
    })

    val renderHandler = handler<GLFWRenderEvent>({
        GL11.glEnable(GL11.GL_TEXTURE_2D)
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId)

        GL11.glPushMatrix()
        GL11.glLoadIdentity()
        GL11.glTranslatef(0.0f, Engine.game!!.window.height.toFloat(), 0.0f)
        GL11.glScalef(1.0f, -1.0f, 1.0f)

        GL11.glBegin(GL11.GL_QUADS)
        GL11.glTexCoord2f(0f, 0f)
        GL11.glVertex2f(position.x, position.y)
        GL11.glTexCoord2f(1f, 0f)
        GL11.glVertex2f((position.x + width), position.y)
        GL11.glTexCoord2f(1f, 1f)
        GL11.glVertex2f((position.x + width), (position.y + height))
        GL11.glTexCoord2f(0f, 1f)
        GL11.glVertex2f(position.x, (position.y + height))
        GL11.glEnd()

        GL11.glPopMatrix()
        GL11.glDisable(GL11.GL_TEXTURE_2D)
    })

}