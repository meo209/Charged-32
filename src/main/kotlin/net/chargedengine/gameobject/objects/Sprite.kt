package net.chargedengine.gameobjects

import net.chargedengine.gameobject.PositionableGameObject
import net.chargedengine.texture.Texture

class Sprite(val texture: Texture, x: Int, y: Int): PositionableGameObject(x, y)