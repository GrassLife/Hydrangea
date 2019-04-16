package life.grass.hydrangea.hotbar

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author BlackBracken
 */
abstract class PhantomHotbar {

    companion object {
        private const val RIGHT_EDGE = 8

        private val protocolManager: ProtocolManager = ProtocolLibrary.getProtocolManager()
    }

    open val defaultSlot = PhantomHotbarSlot(ItemStack(Material.BLACK_STAINED_GLASS_PANE)) { }

    open val cancelSlot: PhantomHotbarSlot? = PhantomHotbarSlot(ItemStack(Material.BARRIER)) { }

    abstract fun getSlotAt(slotPosition: Int): PhantomHotbarSlot?

    internal fun openBy(player: Player) {
        val packet = protocolManager.createPacket(PacketType.Play.Server.WINDOW_ITEMS)

        packet.itemListModifier.writeDefaults()

        val hotbarItemList = mutableListOf<ItemStack>()
        repeat(36) { hotbarItemList += ItemStack(Material.AIR) }
        repeat(9) { hotbarItemList += getOverwrittenSlotAt(it).icon }

        packet.itemListModifier.write(0, hotbarItemList)

        protocolManager.sendServerPacket(player, packet)
    }

    internal fun chooseBy(player: Player, slotPosition: Int) {
        val hotbarSlot = getOverwrittenSlotAt(slotPosition)

        hotbarSlot.action(player)
    }

    internal fun closeBy(player: Player) {
        player.updateInventory()
    }

    // TODO: name better
    private fun getOverwrittenSlotAt(slotPosition: Int): PhantomHotbarSlot {
        if (slotPosition == RIGHT_EDGE) {
            cancelSlot?.run { return@getOverwrittenSlotAt this }
        }

        return getSlotAt(slotPosition) ?: defaultSlot
    }

}