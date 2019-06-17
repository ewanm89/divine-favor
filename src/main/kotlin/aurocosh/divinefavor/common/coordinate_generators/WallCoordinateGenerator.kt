package aurocosh.divinefavor.common.coordinate_generators

import aurocosh.divinefavor.common.lib.extensions.S
import aurocosh.divinefavor.common.lib.extensions.toBlockPos
import aurocosh.divinefavor.common.util.UtilPlayer
import net.minecraft.util.math.BlockPos

class WallCoordinateGenerator : CachedCoordinateGenerator() {
    fun getCoordinates(directions: UtilPlayer.RelativeDirections, blockPos: BlockPos, up: Int, down: Int, left: Int, right: Int, count: Int): List<BlockPos> {
        if (isCached(directions, blockPos, up, down, left, right))
            return cachedCoordinates

        val upVec = directions.up.directionVec.toBlockPos()
        val downVec = directions.down.directionVec.toBlockPos()
        val leftVec = directions.left.directionVec.toBlockPos()
        val rightVec = directions.right.directionVec.toBlockPos()

        val upPart = generateSequence(blockPos.add(upVec), upVec::add).take(up)
        val downPart = generateSequence(blockPos.add(downVec), downVec::add).take(down)

        val center = (upPart + downPart + sequenceOf(blockPos)).toList()

        val leftSide = generateSequence(center.map(leftVec::add)) { it.map(leftVec::add) }.take(right).flatten()
        val rightSide = generateSequence(center.map(rightVec::add)) { it.map(rightVec::add) }.take(left).flatten()

        cachedCoordinates = (center.S + leftSide + rightSide).take(count).toList()
        return cachedCoordinates
    }
}
