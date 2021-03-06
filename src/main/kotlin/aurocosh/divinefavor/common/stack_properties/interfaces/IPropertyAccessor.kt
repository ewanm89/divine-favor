package aurocosh.divinefavor.common.stack_properties.interfaces

import aurocosh.divinefavor.common.stack_properties.properties.base.StackProperty
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

interface IPropertyAccessor {
    val list: List<StackProperty<out Any>>

    fun get(index: Int): StackProperty<out Any>
    fun get(name: String): StackProperty<out Any>?

    fun exist(index: Int): Boolean
    fun exist(name: String): Boolean
    fun exist(property: StackProperty<out Any>): Boolean

    fun getSelectedIndex(stack: ItemStack): Int
    fun setSelectedIndex(stack: ItemStack, index: Int)

    fun copy(from: ItemStack, to: ItemStack)

    @SideOnly(Side.CLIENT)
    fun getPropertyTooltip(stack: ItemStack): List<String>
}