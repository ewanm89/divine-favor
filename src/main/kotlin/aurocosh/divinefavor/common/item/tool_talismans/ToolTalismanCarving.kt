package aurocosh.divinefavor.common.item.tool_talismans

import aurocosh.divinefavor.common.constants.BlockPosConstants
import aurocosh.divinefavor.common.item.spell_talismans.context.TalismanContext
import aurocosh.divinefavor.common.item.tool_talismans.base.ItemToolTalisman
import aurocosh.divinefavor.common.lib.extensions.getBlock
import aurocosh.divinefavor.common.spirit.base.ModSpirit
import aurocosh.divinefavor.common.util.UtilBlock
import net.minecraft.block.Block
import net.minecraft.init.Blocks

open class ToolTalismanCarving(name: String, spirit: ModSpirit, favorCost: Int, private val block: Block, private val predicate: (Block) -> Boolean) : ItemToolTalisman(name, spirit, favorCost) {
    override fun performActionServer(context: TalismanContext) {
        val world = context.world
        if (world.getBlockState(context.pos).block !== block)
            return

        val stateAir = Blocks.AIR.defaultState
        if (!UtilBlock.replaceBlock(context.player, world, context.pos, stateAir))
            return

        val blocksToFreeze = BlockPosConstants.DIRECT_NEIGHBOURS
                .map { pos -> context.pos.add(pos) }
                .filter { pos -> predicate.invoke(world.getBlock(pos)) }

        val stateBlock = block.defaultState
        for (pos in blocksToFreeze)
            UtilBlock.replaceBlock(context.player, world, pos, stateBlock)
    }
}