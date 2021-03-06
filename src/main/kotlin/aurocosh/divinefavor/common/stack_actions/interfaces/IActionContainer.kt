package aurocosh.divinefavor.common.stack_actions.interfaces

import aurocosh.divinefavor.common.stack_actions.StackAction
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

interface IActionContainer {
    val actions: IActionAccessor
    fun findAction(stack: ItemStack, item: Item, actionName: String): Pair<ItemStack, StackAction>? {
        if (item != this)
            return null
        val property = actions.get(actionName) ?: return null
        return Pair(stack, property)
    }
}