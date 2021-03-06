package aurocosh.divinefavor.common.potions.curses

import aurocosh.divinefavor.common.config.common.ConfigCurses
import aurocosh.divinefavor.common.config.data.DoubleInterval
import aurocosh.divinefavor.common.lib.extensions.divineLivingData
import aurocosh.divinefavor.common.network.message.client.syncing.MessageSyncWindLeash
import aurocosh.divinefavor.common.potions.base.potion.ModPotion
import aurocosh.divinefavor.common.potions.common.ModCurses
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.Vec3d

class PotionWindLeash : ModPotion("wind_leash", 0x7FB8A4) {
    init {
        setIsCurse(true)
    }

    override fun onPotionAdded(livingBase: EntityLivingBase) {
        super.onPotionAdded(livingBase)

        val x = directionInterval.random()
        val z = directionInterval.random()
        val vec3d = Vec3d(x, 0.0, z)

        var vector = vec3d.normalize().scale(ConfigCurses.windLeash.motionSpeed.toDouble())
        if (livingBase is EntityPlayer)
            vector = vector.scale(ConfigCurses.windLeash.playerMultiplier.toDouble())
        livingBase.divineLivingData.windLeashData.vector = vector

        MessageSyncWindLeash(vector).sendTo(livingBase)
    }

    override fun performEffect(livingBase: EntityLivingBase, amplifier: Int) {
        val windLeash = livingBase.divineLivingData.windLeashData
        val vector = windLeash.vector

        livingBase.motionX = vector.x
        livingBase.motionZ = vector.z

        if (livingBase.world.isRemote)
            return
        if (isLookingInDirection(livingBase, windLeash.normalizedVector))
            livingBase.removePotionEffect(ModCurses.wind_leash)
    }

    private fun isLookingInDirection(livingBase: EntityLivingBase, vec3d: Vec3d): Boolean {
        return vec3d.dotProduct(livingBase.lookVec) >= ConfigCurses.windLeash.tolerance
    }

    override fun isReady(duration: Int, amplifier: Int): Boolean {
        return true
    }

    companion object {
        private val directionInterval = DoubleInterval(-1.0, 1.0)
    }
}
