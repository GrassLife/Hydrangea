package life.grass.hydrangea.hotbar

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author BlackBracken
 */
data class PhantomHotbarSlot(val icon: ItemStack,
                             val action: (Player) -> Unit)