package aurocosh.divinefavor.common.item.talisman_tools.spell_bow.capability

import aurocosh.divinefavor.common.item.talisman_tools.DefaultTalismanHandler
import aurocosh.divinefavor.common.item.talisman_tools.spell_bow.ItemSpellBow
import aurocosh.divinefavor.common.item.talismans.arrow.base.ItemArrowTalisman
import net.minecraft.item.ItemStack

// The default implementation of the capability. Holds all the logic.
class DefaultSpellBowHandler : DefaultTalismanHandler(ItemSpellBow.SLOT_COUNT), ISpellBowHandler {
    override fun isItemValid(stack: ItemStack): Boolean {
        return stack.item is ItemArrowTalisman
    }

    override fun getSelectedTalisman(): ItemArrowTalisman? {
        val talismanStack = getSelectedStack()
        return if (!talismanStack.isEmpty) talismanStack.item as ItemArrowTalisman else null
    }
}
