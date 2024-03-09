import net.chargedengine.Engine
import net.chargedengine.Game
import net.chargedengine.GameProperties
import net.chargedengine.Window

fun main() {
    Engine.game = ExampleGame(
        GameProperties("Example Game", "Example Game description", "1.0.0"),
        Window(height = 500, width = 500, title = "Example Game Title", vsync = true)
    )

    Engine.initialize()
    Engine.render()

    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            Engine.shutdown()
        }
    })
}