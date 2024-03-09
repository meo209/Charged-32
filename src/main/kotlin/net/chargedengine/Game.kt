package net.chargedengine

import com.github.meo209.kevet.notify
import com.github.meo209.kevet.notifyAsync
import net.chargedengine.events.*
import net.chargedengine.gameobject.Scene
import net.chargedengine.utils.glfwUtilConvertBoolean
import net.chargedengine.utils.logger
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL


open class Game(val properties: GameProperties, val window: Window) {

    fun initialize() {
        logger.info { "Initializing game: ${properties.name}" }

        GLFWErrorCallback.createPrint(System.err).set()

        if (!glfwInit())
            throw IllegalStateException("Unable to initialize GLFW")

        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, glfwUtilConvertBoolean(window.resizable))

        window.id = glfwCreateWindow(window.width, window.height, window.title, NULL, NULL)

        if (window.id == NULL)
            throw IllegalStateException("Failed to create GLFW window")

        glfwMakeContextCurrent(window.id)

        glfwSetKeyCallback(window.id
        ) { _: Long, key: Int, scancode: Int, action: Int, mods: Int ->

            if (action == GLFW_PRESS) {
                notify(KeyPressEvent(key, scancode, mods)) // TODO: may have problems with not handling correctly
            } else {
                notify(KeyInputEvent(key, scancode, action, mods))
            }
        }

        stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            glfwGetWindowSize(window.id, pWidth, pHeight)

            val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())

            glfwSetWindowPos( // Center the window on the screen
                window.id,
                (vidmode!!.width() - pWidth[0]) / 2,
                (vidmode!!.height() - pHeight[0]) / 2
            )
        }

        glfwMakeContextCurrent(window.id)

        if (window.vsync)
            glfwSwapInterval(1)

        glfwShowWindow(window.id)
    }

    fun render() {
        GL.createCapabilities()

        glClearColor(0.05f, 0.05f, 0.05f, 0.0f)

        notify(GLFWInitializationEvent())

        while (!glfwWindowShouldClose(window.id)) {
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

            glMatrixMode(GL_PROJECTION)
            glLoadIdentity()
            glOrtho(0.0, Engine.game!!.window.width.toDouble(), 0.0, Engine.game!!.window.height.toDouble(), -1.0, 1.0)
            glMatrixMode(GL_MODELVIEW)

            notify(GLFWRenderEvent())

            glfwSwapBuffers(window.id)
            glfwPollEvents()
        }
    }

    fun shutdown() {
        logger.info { "Shutting down game: ${properties.name}" }
        notify(GameShutdownEvent())
        glfwDestroyWindow(window.id)
        glfwTerminate()
    }

}
