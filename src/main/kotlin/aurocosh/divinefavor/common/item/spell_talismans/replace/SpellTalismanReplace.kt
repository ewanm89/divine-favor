package aurocosh.divinefavor.common.item.spell_talismans.replace

import aurocosh.divinefavor.client.block_ovelay.BlockExchangeRendering
import aurocosh.divinefavor.common.coordinate_generators.SurfaceCoordinateGenerator
import aurocosh.divinefavor.common.item.spell_talismans.base.ItemSpellTalisman
import aurocosh.divinefavor.common.item.spell_talismans.base.SpellOptions
import aurocosh.divinefavor.common.item.spell_talismans.build.base.SpellTalismanBuild
import aurocosh.divinefavor.common.item.spell_talismans.context.TalismanContext
import aurocosh.divinefavor.common.item.spell_talismans.common_build_properties.BlockSelectPropertyWrapper
import aurocosh.divinefavor.common.item.spell_talismans.common_build_properties.PositionPropertyWrapper
import aurocosh.divinefavor.common.lib.extensions.get
import aurocosh.divinefavor.common.lib.extensions.isAirOrReplacable
import aurocosh.divinefavor.common.spirit.base.ModSpirit
import aurocosh.divinefavor.common.stack_properties.StackPropertyBool
import aurocosh.divinefavor.common.stack_properties.StackPropertyInt
import aurocosh.divinefavor.common.tasks.BlockPlacingTask
import aurocosh.divinefavor.common.util.UtilPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

abstract class SpellTalismanReplace(name: String, spirit: ModSpirit, favorCost: Int, options: EnumSet<SpellOptions>) : SpellTalismanBuild(name, spirit, favorCost, options) {
    protected val blockCount: StackPropertyInt = propertyHandler.registerIntProperty("block_count", 6, 1, 64)
    protected val isFuzzy: StackPropertyBool = propertyHandler.registerBoolProperty("fuzzy", false)
    override val positionPropertyWrapper: PositionPropertyWrapper = PositionPropertyWrapper(propertyHandler)

    override fun getRenderCoordinates(context: TalismanContext) = getCoordinates(context).filterNot{context.world.isAirBlock(it)}
    override fun getFinalCoordinated(context: TalismanContext) = getCoordinates(context).filterNot{context.world.isAirBlock(it)}.shuffled()

    override fun performActionServer(context: TalismanContext) {
        val (player, stack, world) = context.getCommon()
        val state = stack.get(selectPropertyWrapper.selectedBlock)
        val coordinates = getCoordinates(context).filterNot(world::isAirOrReplacable).shuffled()
        BlockPlacingTask(coordinates, state, player, 1).start()
    }

    @SideOnly(Side.CLIENT)
    override fun handleRendering(context: TalismanContext, lastEvent: RenderWorldLastEvent) {
        val coordinates = getCoordinates(context)
        val state = context.stack.get(selectPropertyWrapper.selectedBlock)
        BlockExchangeRendering.render(lastEvent, context.player, state, coordinates)
    }
}
