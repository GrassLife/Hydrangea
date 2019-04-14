package life.grass.hydrangea

import life.grass.hydrangea.hotbar.PhantomHotbar
import org.bukkit.entity.Player

/**
 * @author BlackBracken
 */
object PhantomHotbarOpeningStateHolder {

    internal val openingPhantomHotbarMap = mutableMapOf<Player, PhantomHotbar>()

}

fun Player.openPhantomHotbar(phantomHotbar: PhantomHotbar) {
    if (this.isOpeningHotbarSlot()) {
        return
    }

    PhantomHotbarOpeningStateHolder.openingPhantomHotbarMap[this] = phantomHotbar
    phantomHotbar.openBy(this)
}

fun Player.choosePhantomHotbarSlot(slot: Int) {
    val phantomHotbar = this.getOpeningPhantomHotbar() ?: return

    phantomHotbar.chooseBy(this, slot)
    closePhantomHotbar()
}

fun Player.closePhantomHotbar() {
    PhantomHotbarOpeningStateHolder.openingPhantomHotbarMap[this]?.closeBy(this) ?: return
    PhantomHotbarOpeningStateHolder.openingPhantomHotbarMap.remove(this)
}

fun Player.getOpeningPhantomHotbar(): PhantomHotbar? = PhantomHotbarOpeningStateHolder.openingPhantomHotbarMap[this]

fun Player.isOpeningHotbarSlot(): Boolean = this.getOpeningPhantomHotbar() != null