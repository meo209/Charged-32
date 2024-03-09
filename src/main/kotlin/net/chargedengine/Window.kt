package net.chargedengine

data class Window(var id: Long = -1, var width: Int, var height: Int, var title: String, var resizable: Boolean = false, var vsync: Boolean = false)