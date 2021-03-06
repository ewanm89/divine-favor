package aurocosh.divinefavor.common.item.talisman

import aurocosh.divinefavor.DivineFavor
import aurocosh.divinefavor.common.item.base.ICastable
import aurocosh.divinefavor.common.item.base.ModItem
import aurocosh.divinefavor.common.item.spell_talismans.base.CastType
import aurocosh.divinefavor.common.item.spell_talismans.context.CastContext
import aurocosh.divinefavor.common.lib.extensions.divinePlayerData
import aurocosh.divinefavor.common.lib.interfaces.IBlockCatcher
import aurocosh.divinefavor.common.network.message.client.spirit_data.MessageSyncFavor
import aurocosh.divinefavor.common.spirit.base.ModSpirit
import aurocosh.divinefavor.common.stack_actions.StackActionHandler
import aurocosh.divinefavor.common.stack_actions.interfaces.IActionAccessor
import aurocosh.divinefavor.common.stack_actions.interfaces.IActionContainer
import aurocosh.divinefavor.common.stack_properties.StackPropertyHandler
import aurocosh.divinefavor.common.stack_properties.interfaces.IPropertyAccessor
import aurocosh.divinefavor.common.stack_properties.interfaces.IPropertyContainer
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.EnumRarity
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

abstract class ItemTalisman(val name: String, texturePath: String, val spirit: ModSpirit, protected val favorCost: Int) : ModItem(name, texturePath), ISelectedStackProvider, IPropertyContainer, IActionContainer, IBlockCatcher, ICastable {
    protected val propertyHandler: StackPropertyHandler = StackPropertyHandler("talisman $name")
    override val properties: IPropertyAccessor = propertyHandler
    protected val actionHandler: StackActionHandler = StackActionHandler("talisman $name")
    override val actions: IActionAccessor = actionHandler

    val spiritId: Int get() = spirit.id

    init {
        setMaxStackSize(1)
    }

    open fun getApproximateFavorCost(itemStack: ItemStack): Int = favorCost
    open fun getFinalFavorCost(context: CastContext): Int = favorCost

    override fun cast(context: CastContext): Boolean {
        if (!context.stackValid)
            return false
        if (!preValidate(context))
            return false
        if (!preProcess(context))
            return false
        if (!validate(context))
            return false
        if (isConsumeCharge(context) && !claimCost(context))
            return false
        if (context.world.isRemote)
            performActionClient(context)
        else
            performActionServer(context)
        return true
    }

    fun claimCost(context: CastContext): Boolean {
        val player = context.player
        val trueCost = getFinalFavorCost(context)
        if (trueCost == 0)
            return true
        val spiritData = player.divinePlayerData.spiritData
        if (!spiritData.consumeFavor(spirit.id, trueCost))
            return false
        if (player.world.isRemote)
            return true

        MessageSyncFavor(spirit, spiritData).sendTo(player)
        return true
    }

    @SideOnly(Side.CLIENT)
    fun getUseInfo(player: EntityPlayer, stack: ItemStack): String {
        val spiritData = player.divinePlayerData.spiritData
        val favorValue = spiritData.getFavor(spirit.id)
        val trueFavorCost = getApproximateFavorCost(stack)
        val infinite = spiritData.isFavorInfinite(spirit.id) || trueFavorCost == 0

        val useCount = if (infinite) -1 else favorValue / trueFavorCost
        val description: String
        if (useCount < 0)
            description = I18n.format("tooltip.divinefavor:talisman.infinite_use")
        else if (useCount == 0)
            description = I18n.format("tooltip.divinefavor:talisman.unusable")
        else
            description = I18n.format("tooltip.divinefavor:talisman.cost", trueFavorCost, useCount)
        return description
    }

    override fun getRarity(stack: ItemStack): EnumRarity {
        return EnumRarity.RARE
    }

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, world: World?, tooltip: MutableList<String>, flag: ITooltipFlag) {
        super.addInformation(stack, world, tooltip, flag)
        if (!DivineFavor.proxy.hasClientPlayer)
            return

        val player = DivineFavor.proxy.clientPlayer

        val talisman = stack.item as ItemTalisman
        val favorCost = talisman.getUseInfo(player, stack)
        tooltip.add(favorCost)

        val spirit = talisman.spirit
        val name = I18n.format(spirit.nameTranslationKey)
        val message = I18n.format("tooltip.divinefavor:talisman.spirit", name)
        tooltip.add(message)

        properties.getPropertyTooltip(stack).forEach { tooltip.add(it) }
    }

    protected open fun preValidate(context: CastContext): Boolean = !raycastBlock(context.stack, context.castType) || context.raycastValid
    protected open fun preProcess(context: CastContext): Boolean = true
    protected open fun validate(context: CastContext): Boolean = true
    protected open fun isConsumeCharge(context: CastContext): Boolean = true

    open fun raycastBlock(stack: ItemStack, castType: CastType): Boolean = false
    open fun raycastTarget(stack: ItemStack, castType: CastType): Boolean = false

    protected open fun performActionServer(context: CastContext) {}
    protected open fun performActionClient(context: CastContext) {}

    @SideOnly(Side.CLIENT)
    open fun shouldRender(context: CastContext) = true

    @SideOnly(Side.CLIENT)
    open fun handleRendering(context: CastContext, lastEvent: RenderWorldLastEvent) {
    }

    override fun catchDrops(stack: ItemStack, toolStack: ItemStack, event: BlockEvent.HarvestDropsEvent) {}

    override fun getSelectedStack(stack: ItemStack): ItemStack {
        return if (stack.item is ItemTalisman) stack else ItemStack.EMPTY
    }
}