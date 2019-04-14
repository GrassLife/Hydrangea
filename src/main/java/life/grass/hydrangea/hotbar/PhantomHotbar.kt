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
class PhantomHotbar(private val slotMap: Map<Int, PhantomHotbarSlot>,
                    private val unregisteredSlot: PhantomHotbarSlot = defaultUnregisteredSlot,
                    val filter: (ItemStack) -> Boolean) {

    companion object {
        private val defaultUnregisteredSlot = PhantomHotbarSlot(ItemStack(Material.BLACK_STAINED_GLASS_PANE)) { }

        private val protocolManager: ProtocolManager = ProtocolLibrary.getProtocolManager()
    }

    fun openBy(player: Player) {
        val packet = protocolManager.createPacket(PacketType.Play.Server.WINDOW_ITEMS)

        packet.itemListModifier.writeDefaults()

        val hotbarItemList = mutableListOf<ItemStack>()
        repeat(36) { hotbarItemList += ItemStack(Material.AIR) }
        repeat(9) { index -> hotbarItemList += slotMap[index]?.icon ?: unregisteredSlot.icon }

        packet.itemListModifier.write(0, hotbarItemList)

        protocolManager.sendServerPacket(player, packet)
    }

    fun chooseBy(player: Player, slot: Int) {
        val hotbarSlot = slotMap[slot] ?: unregisteredSlot

        hotbarSlot.action(player)
    }

    fun closeBy(player: Player) {
        player.updateInventory()
    }

}