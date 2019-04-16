package life.grass.hydrangea

import life.grass.hydrangea.hotbar.HotbarRegisterer
import life.grass.hydrangea.listener.PhantomHotbarListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author BlackBracken
 */
class Hydrangea : JavaPlugin() {

    companion object {
        lateinit var instance: Hydrangea
    }

    internal val hotbarRegistererSet: MutableSet<HotbarRegisterer> = mutableSetOf()

    override fun onEnable() {
        super.onEnable()
        instance = this

        server.pluginManager.registerEvents(PhantomHotbarListener(), this)
    }

    fun registerHotbar(hotbarRegisterer: HotbarRegisterer) {
        hotbarRegistererSet.add(hotbarRegisterer)
    }

    fun unregisterHotbar(hotbarRegisterer: HotbarRegisterer) {
        hotbarRegistererSet.remove(hotbarRegisterer)
    }

}