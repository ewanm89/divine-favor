package aurocosh.divinefavor.common.item.arrow_talismans.base

import aurocosh.divinefavor.DivineFavor
import aurocosh.divinefavor.common.entity.projectile.EntitySpellArrow
import aurocosh.divinefavor.common.item.talisman.ItemTalisman
import aurocosh.divinefavor.common.item.spell_talismans.base.CastType
import aurocosh.divinefavor.common.item.spell_talismans.context.CastContext
import aurocosh.divinefavor.common.spirit.base.ModSpirit
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.projectile.EntityArrow
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.awt.Color
import java.util.*

open class ItemArrowTalisman(name: String, spirit: ModSpirit, favorCost: Int, val color: Color, val arrowDamage: Double, val options: EnumSet<ArrowOptions>, val arrowType: ArrowType) : ItemTalisman("arrow_talisman_$name", "arrow_talismans/$name", spirit, favorCost) {

    override fun preValidate(context: CastContext): Boolean {
        return context.castType == CastType.BowCast && super.preValidate(context)
    }

    var gravityType: GravityType
        protected set

    val isBreakOnHit: Boolean
        get() = options.contains(ArrowOptions.BreakOnHit)

    init {
        gravityType = GravityType.NORMAL
        creativeTab = DivineFavor.TAB_ARROW_TALISMANS
    }

    // Talisman functions
    fun createArrow(context: CastContext): EntityArrow {
        val player = context.player
        val spellArrow = getArrow(context.world, player)
        spellArrow.setSpell(this, player)
        preInit(spellArrow, player)
        spellArrow.setNoGravity(gravityType == GravityType.NO_GRAVITY || gravityType == GravityType.ANTIGRAVITY)
        spellArrow.setHasAntiGravity(gravityType == GravityType.ANTIGRAVITY)
        spellArrow.damage = arrowDamage
        return spellArrow
    }

    protected open fun getArrow(world: World, shooter: EntityLivingBase): EntitySpellArrow {
        return EntitySpellArrow(world, shooter)
    }

    fun cast(target: EntityLivingBase?, shooter: EntityLivingBase, spellArrow: EntitySpellArrow, blockPos: BlockPos?, sideHit: EnumFacing?): Boolean {
        if (options.contains(ArrowOptions.RequiresTarget) && target == null)
            return true
        return if (spellArrow.world.isRemote)
            performActionClient(target, shooter, spellArrow, blockPos, sideHit)
        else
            performActionServer(target, shooter, spellArrow, blockPos, sideHit)
    }

    protected open fun performActionServer(target: EntityLivingBase?, shooter: EntityLivingBase, spellArrow: EntitySpellArrow, blockPos: BlockPos?, sideHit: EnumFacing?): Boolean {
        return true
    }

    protected open fun performActionClient(target: EntityLivingBase?, shooter: EntityLivingBase, spellArrow: EntitySpellArrow, blockPos: BlockPos?, sideHit: EnumFacing?): Boolean {
        return true
    }

    open fun onUpdate(spellArrow: EntitySpellArrow) {}

    open fun onCollideWithPlayer(spellArrow: EntitySpellArrow, player: EntityPlayer): Boolean {
        return true
    }

    protected open fun preInit(spellArrow: EntitySpellArrow, shooter: EntityLivingBase) {}

    open fun postInit(spellArrow: EntityArrow) {}

    @SideOnly(Side.CLIENT)
    open fun spawnParticles(spellArrow: EntitySpellArrow) {
    }

    override fun isConsumeCharge(context: CastContext): Boolean {
        return false
    }
}
