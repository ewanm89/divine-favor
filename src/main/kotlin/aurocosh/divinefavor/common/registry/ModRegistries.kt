package aurocosh.divinefavor.common.registry

import aurocosh.divinefavor.common.block.base.ModBlock
import aurocosh.divinefavor.common.item.base.ModItem
import aurocosh.divinefavor.common.item.base.ModItemArrow
import aurocosh.divinefavor.common.item.base.ModItemBlock
import aurocosh.divinefavor.common.item.base.ModItemPickaxe
import aurocosh.divinefavor.common.muliblock.ModMultiBlock
import aurocosh.divinefavor.common.potions.base.potion.ModPotion
import aurocosh.divinefavor.common.receipes.base.ModRecipe
import aurocosh.divinefavor.common.spirit.base.ModSpirit

object ModRegistries {
    var itemBlocks = RegistryAssistant<ModItemBlock>()
    var blocks = RegistryAssistant<ModBlock>()
    var items = RegistryAssistant<ModItem>()
    var recipes = RegistryAssistant<ModRecipe>()
    var pickaxes = RegistryAssistant<ModItemPickaxe>()
    var arrows = RegistryAssistant<ModItemArrow>()
    var multiBlocks = RegistryAssistant<ModMultiBlock>()
    var potions = RegistryAssistant<ModPotion>()
    var spirits = RegistryAssistant<ModSpirit>()
}
