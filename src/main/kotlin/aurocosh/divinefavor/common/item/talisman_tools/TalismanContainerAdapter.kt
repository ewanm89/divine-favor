package aurocosh.divinefavor.common.item.talisman_tools

import aurocosh.divinefavor.common.item.talisman_tools.grimoire.ItemGrimoire
import aurocosh.divinefavor.common.item.talisman_tools.grimoire.capability.GrimoireDataHandler.CAPABILITY_GRIMOIRE
import aurocosh.divinefavor.common.item.talisman_tools.spell_blade.capability.SpellBladeDataHandler.CAPABILITY_SPELL_BLADE
import aurocosh.divinefavor.common.item.talisman_tools.spell_blade.ItemSpellBlade
import aurocosh.divinefavor.common.item.talisman_tools.spell_bow.ItemSpellBow
import aurocosh.divinefavor.common.item.talisman_tools.spell_bow.capability.SpellBowDataHandler.CAPABILITY_SPELL_BOW
import aurocosh.divinefavor.common.lib.extensions.capNull
import aurocosh.divinefavor.common.network.message.sever.MessageSyncTalismanContainerSlot
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

object TalismanContainerAdapter {
    fun isItemValid(item: Item): Boolean {
        return item is ItemGrimoire || item is ItemSpellBow || item is ItemSpellBlade
    }

    fun getTalismanContainer(stack: ItemStack): ITalismanContainer? {
        return stack.capNull(CAPABILITY_GRIMOIRE)
                ?: stack.capNull(CAPABILITY_SPELL_BOW)
                ?: stack.capNull(CAPABILITY_SPELL_BLADE)
    }

    fun selectSlot(playerSlot: Int, talismanSlot: Int) {
        MessageSyncTalismanContainerSlot(playerSlot, talismanSlot).send()
    }
}