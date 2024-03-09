import net.chargedengine.Engine
import net.chargedengine.Game
import net.chargedengine.GameProperties
import net.chargedengine.Window

fun main() {
    val engine = Engine(
        Game(
            GameProperties("Example Game", "Example Game description", "1.0.0"),
            Window(height = 720, width = 1280, title = "Example Game Title", vsync = true)
        )
    )
        .initialize()
        .render()

    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            engine.shutdown()
        }
    })
}