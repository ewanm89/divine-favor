package aurocosh.divinefavor.common.item.spell_talismans

import aurocosh.divinefavor.common.entity.minions.base.IMinion
import aurocosh.divinefavor.common.entity.minions.base.MinionMode
import aurocosh.divinefavor.common.item.spell_talismans.base.ItemSpellTalisman
import aurocosh.divinefavor.common.item.spell_talismans.base.SpellOptions
import aurocosh.divinefavor.common.item.spell_talismans.context.CastContext
import aurocosh.divinefavor.common.lib.extensions.getDistanceSq
import aurocosh.divinefavor.common.spirit.base.ModSpirit
import aurocosh.divinefavor.common.util.UtilCoordinates
import net.minecraft.entity.EntityLivingBase
import java.util.*

class SpellTalismanTarget(name: String, spirit: ModSpirit, favorCost: Int, options: EnumSet<SpellOptions>) : ItemSpellTalisman(name, spirit, favorCost, options) {

    override fun validate(context: CastContext): Boolean {
        return context.target != null
    }

    override fun performActionServer(context: CastContext) {
        val origin = context.player.positionVector
        val radiusSq = RADIUS * RADIUS
        val boundingBox = UtilCoordinates.getBoundingBox(origin, RADIUS)
        val entities = context.world.getEntitiesWithinAABB(EntityLivingBase::class.java, boundingBox) { livingBase ->
            (livingBase is IMinion && livingBase.getDistanceSq(origin) < radiusSq)
        }

        for (entity in entities) {
            val minion = entity as IMinion
            val minionData = minion.minionData
            minionData.attackTarget = context.target
            minionData.mode = MinionMode.Normal
        }
    }

    companion object {
        private val RADIUS = 30.0
    }
}
