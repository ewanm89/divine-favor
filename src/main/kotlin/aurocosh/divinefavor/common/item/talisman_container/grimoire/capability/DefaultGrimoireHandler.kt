package aurocosh.divinefavor.common.item.talisman_container.grimoire.capability

import aurocosh.divinefavor.common.item.talisman_container.DefaultTalismanHandler
import aurocosh.divinefavor.common.item.talisman_container.grimoire.ItemGrimoire
import aurocosh.divinefavor.common.item.talismans.spell.base.ItemSpellTalisman
import net.minecraft.item.ItemStack

// The default implementation of the capability. Holds all the logic.
class DefaultGrimoireHandler : DefaultTalismanHandler(ItemGrimoire.SLOT_COUNT), IGrimoireHandler {
    override fun isItemValid(stack: ItemStack): Boolean {
        return stack.item is ItemSpellTalisman
    }
}
