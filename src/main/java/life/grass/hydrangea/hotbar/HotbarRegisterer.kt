package life.grass.hydrangea.hotbar

import org.bukkit.event.player.PlayerSwapHandItemsEvent

/**
 * @author BlackBracken
 *
 * TODO redesign
 */
class HotbarRegisterer(val filter: (PlayerSwapHandItemsEvent) -> Boolean,
                       val instantiate: (PlayerSwapHandItemsEvent) -> PhantomHotbar)