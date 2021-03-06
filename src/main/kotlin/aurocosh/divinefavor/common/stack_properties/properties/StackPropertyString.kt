package aurocosh.divinefavor.common.stack_properties.properties

import aurocosh.divinefavor.common.stack_properties.properties.base.StackProperty
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class StackPropertyString(name: String, defaultValue: String, showInTooltip: Boolean = false, showInGui: Boolean = false, orderIndex: Int = 0) : StackProperty<String>(name, defaultValue, showInTooltip, showInGui, orderIndex) {
    override fun getValueFromTag(compound: NBTTagCompound): String {
        return compound.getString(tag)
    }

    override fun setValueToTag(compound: NBTTagCompound, value: String) {
        compound.setString(tag, value)
    }

    @SideOnly(Side.CLIENT)
    override fun toLocalString(stack: ItemStack): String {
        val value = getValue(stack)
        return I18n.format(displayKey, value)
    }
}
