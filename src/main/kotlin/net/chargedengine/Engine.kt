package net.chargedengine

object Engine {

    var game: Game? = null

    fun initialize(): Engine {
        game?.initialize()
        return this
    }

    fun render(): Engine {
        game?.render()
        return this
    }

    fun shutdown(): Engine {
        game?.shutdown()

        return this
    }

}