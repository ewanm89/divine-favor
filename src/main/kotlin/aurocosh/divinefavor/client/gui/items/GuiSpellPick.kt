package aurocosh.divinefavor.client.gui.items

import aurocosh.divinefavor.common.constants.ConstResources
import aurocosh.divinefavor.common.item.talisman_tools.spell_pick.SpellPickContainer
import aurocosh.divinefavor.common.item.talisman_tools.spell_pick.capability.SpellPickDataHandler.CAPABILITY_SPELL_PICK
import aurocosh.divinefavor.common.lib.extensions.cap
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand
import net.minecraft.util.ResourceLocation

class GuiSpellPick(player: EntityPlayer, internal var stack: ItemStack, hand: EnumHand)
    : GuiContainer(SpellPickContainer(player, stack.cap(CAPABILITY_SPELL_PICK), hand)) {

    override fun initGui() {
        xSize = WIDTH
        ySize = HEIGHT
        super.initGui()
    }

    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        mc.textureManager.bindTexture(texture)
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize)
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    companion object {
        const val WIDTH = 175
        const val HEIGHT = 147
        private val texture = ResourceLocation(ConstResources.GUI_SPELL_PICK)
    }
}
