package aurocosh.divinefavor.client.gui.items

import aurocosh.divinefavor.common.constants.ConstResources
import aurocosh.divinefavor.common.item.ritual_pouch.RitualBagContainer
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class GuiRitualPouch(player: EntityPlayer, pouch: ItemStack) : GuiContainer(RitualBagContainer(player, pouch)) {

    override fun initGui() {
        xSize = GuiWidth
        ySize = GuiHeight
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
        const val GuiWidth = 175
        const val GuiHeight = 165
        private val texture = ResourceLocation(ConstResources.GUI_RITUAL_POUCH)
    }
}
