package net.chargedengine.events

import com.github.meo209.kevet.KevetEvent

class KeyInputEvent(val key: Int, val scancode: Int, val action: Int, val mods: Int): KevetEvent
class KeyPressEvent(val key: Int, val scancode: Int, val mods: Int): KevetEvent