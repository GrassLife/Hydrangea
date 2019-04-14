package life.grass.hydrangea

import life.grass.hydrangea.hotbar.PhantomHotbar
import life.grass.hydrangea.listener.PhantomHotbarListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author BlackBracken
 */
class Hydrangea : JavaPlugin() {

    companion object {
        lateinit var instance: Hydrangea
    }

    internal val hotbarSet: MutableSet<PhantomHotbar> = mutableSetOf()

    override fun onEnable() {
        super.onEnable()
        instance = this

        server.pluginManager.registerEvents(PhantomHotbarListener(), this)
    }

    fun registerHotbar(hotbar: PhantomHotbar) {
        hotbarSet.add(hotbar)
    }

    fun unregisterHotbar(hotbar: PhantomHotbar) {
        hotbarSet.remove(hotbar)
    }

}