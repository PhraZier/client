package org.runestar.client.common

import java.awt.Image
import java.awt.Window

object AwtTaskbar {

    private val taskbarClass = catchToNull { Class.forName("java.awt.Taskbar") }

    @Suppress("UNCHECKED_CAST")
    private val taskBarStateClass = catchToNull { Class.forName("java.awt.Taskbar\$State") as Class<Enum<*>> }

    private val getTaskbar = taskbarClass?.let {
        catchToNull { it.getMethod("getTaskbar") }
    }

    private val taskbar = catchToNull { getTaskbar?.invoke(null) }

    private val setWindowProgressValue = taskbarClass?.let {
        catchToNull { it.getMethod("setWindowProgressValue", Window::class.java, Integer.TYPE) }
    }

    private val setWindowIconBadge = taskbarClass?.let {
        catchToNull { it.getMethod("setWindowIconBadge", Window::class.java, Image::class.java) }
    }

    private val setWindowProgressState = taskbarClass?.let {
        catchToNull { it.getMethod("setWindowProgressState", Window::class.java, taskBarStateClass) }
    }

    fun setWindowProgressValue(window: Window, value: Int) {
        catchToNull { setWindowProgressValue?.invoke(taskbar, window, value) }
    }

    fun setWindowIconBadge(window: Window, badge: Image?) {
        catchToNull { setWindowIconBadge?.invoke(taskbar, window, badge) }
    }

    fun setWindowProgressState(window: Window, state: State) {
        println(taskBarStateClass)
        val s = taskBarStateClass?.enumConstants?.firstOrNull { it.name == state.name }
        println(s)
        catchToNull { setWindowProgressState?.invoke(taskbar, window, s) }
    }

    enum class State {
        /**
         * Stops displaying the progress.
         */
        OFF,
        /**
         * The progress indicator displays with normal color and determinate
         * mode.
         */
        NORMAL,
        /**
         * Shows progress as paused, progress can be resumed by the user.
         * Switches to the determinate display.
         */
        PAUSED,
        /**
         * The progress indicator displays activity without specifying what
         * proportion of the progress is complete.
         */
        INDETERMINATE,
        /**
         * Shows that an error has occurred. Switches to the determinate
         * display.
         */
        ERROR
    }

    private inline fun <T> catchToNull(f: () -> T): T? {
        return try {
            f()
        } catch (e: Exception) {
            null
        }
    }
}