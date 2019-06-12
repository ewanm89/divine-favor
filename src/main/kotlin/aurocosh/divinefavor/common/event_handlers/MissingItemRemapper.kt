package aurocosh.divinefavor.common.event_handlers

import aurocosh.divinefavor.DivineFavor
import aurocosh.divinefavor.common.core.ResourceNamer
import aurocosh.divinefavor.common.lib.extensions.S
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent

@Mod.EventBusSubscriber(modid = DivineFavor.MOD_ID)
object MissingItemRemapper {
    @SubscribeEvent
    fun onRemapItem(event: RegistryEvent.MissingMappings<Item>) {
        val myMappings = event.allMappings.filter { it.key.namespace == DivineFavor.MOD_ID }
        val spellTalismansMappings = myMappings.S.filter { it.key.path.substring(0..8) == "talisman_" }

        for (mapping in spellTalismansMappings) {
            val talismanName = mapping.key.path.substring(9)
            val fullName = ResourceNamer.getItemName("spell_talisman", talismanName)
            val item = Item.getByNameOrId(fullName.toString())
            if(item != null)
                mapping.remap(item)
        }
    }
}