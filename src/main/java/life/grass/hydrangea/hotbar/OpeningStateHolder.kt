package life.grass.hydrangea.hotbar

import org.bukkit.entity.Player

/**
 * @author BlackBracken
 */
private object OpeningStateHolder {

    internal val openingPhantomHotbarMap = mutableMapOf<Player, PhantomHotbar>()

}

fun Player.openPhantomHotbar(phantomHotbar: PhantomHotbar) {
    if (this.isOpeningHotbarSlot()) {
        return
    }

    OpeningStateHolder.openingPhantomHotbarMap[this] = phantomHotbar
    phantomHotbar.openBy(this)
}

fun Player.choosePhantomHotbarSlot(slot: Int) {
    val phantomHotbar = this.getOpeningPhantomHotbar() ?: return

    phantomHotbar.chooseBy(this, slot)
    closePhantomHotbar()
}

fun Player.closePhantomHotbar() {
    OpeningStateHolder.openingPhantomHotbarMap[this]?.closeBy(this) ?: return
    OpeningStateHolder.openingPhantomHotbarMap.remove(this)
}

fun Player.getOpeningPhantomHotbar(): PhantomHotbar? = OpeningStateHolder.openingPhantomHotbarMap[this]

fun Player.isOpeningHotbarSlot(): Boolean = this.getOpeningPhantomHotbar() != null