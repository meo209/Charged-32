import com.github.meo209.kevet.handler
import net.chargedengine.Game
import net.chargedengine.GameProperties
import net.chargedengine.Window
import net.chargedengine.events.GLFWInitializationEvent
import net.chargedengine.events.GLFWRenderEvent
import net.chargedengine.gameobject.objects.Sprite
import net.chargedengine.utils.Vector2

class ExampleGame(properties: GameProperties, window: Window) : Game(properties, window) {

    private fun renderShit(event: GLFWRenderEvent) {
        println("Rendering awkward children via custom method")
    }

    val testSprite = Sprite("awkward.png")
        .withProperties(Vector2(10f, 10f), 0f, 50f, 50f)
        .child(
            Sprite("awkward.png")
                .withProperties(Vector2(10f, 10f), 0f, 25f, 25f)
                .withHandler<GLFWRenderEvent>({ renderShit(it) })
        )
        .withHandler<GLFWRenderEvent>({
            println("Rendered awkward parent")
        })

    val initHandler = handler<GLFWInitializationEvent>({

    })

    val renderHandler = handler<GLFWRenderEvent>({

    })

}