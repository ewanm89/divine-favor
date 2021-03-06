package aurocosh.divinefavor.common.potions.curses

import aurocosh.divinefavor.DivineFavor
import aurocosh.divinefavor.common.config.common.ConfigCurses
import aurocosh.divinefavor.common.potions.base.potion.ModPotion
import aurocosh.divinefavor.common.potions.common.ModCurses
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.util.math.Vec3d
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = DivineFavor.MOD_ID)
class PotionRoots : ModPotion("roots", 0x7FB8A4) {
    init {
        setIsCurse(true)
        registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "817e7bc4-8ad5-4323-9131-aa71236a1b83", (-ConfigCurses.roots.slownessForce).toDouble(), 2)
    }

    override fun performEffect(livingBase: EntityLivingBase, amplifier: Int) {
        if (livingBase.world.isRemote)
            return
        if (isLookingDown(livingBase))
            livingBase.removePotionEffect(ModCurses.roots)
    }

    private fun isLookingDown(livingBase: EntityLivingBase): Boolean {
        return DOWN_VECTOR.dotProduct(livingBase.lookVec) >= TOLERANCE
    }

    override fun isReady(duration: Int, amplifier: Int): Boolean {
        return true
    }

    companion object {
        private val TOLERANCE = 0.97f
        private val DOWN_VECTOR = Vec3d(0.0, -1.0, 0.0)
    }
}
