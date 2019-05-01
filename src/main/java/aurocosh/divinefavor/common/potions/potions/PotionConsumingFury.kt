package aurocosh.divinefavor.common.potions.potions

import aurocosh.divinefavor.common.config.common.ConfigSpells
import aurocosh.divinefavor.common.constants.ConstMisc
import aurocosh.divinefavor.common.potions.base.potion.ModPotion
import aurocosh.divinefavor.common.potions.common.ModPotions
import aurocosh.divinefavor.common.util.UtilChat
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.DamageSource
import net.minecraft.util.EntityDamageSource
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.*

@Mod.EventBusSubscriber(modid = ConstMisc.MOD_ID)
class PotionConsumingFury : ModPotion("consuming_fury", true, 0x7FB8A4) {

    override fun onPotionRemoved(livingBase: EntityLivingBase) {
        super.onPotionRemoved(livingBase)
        if (livingBase !is EntityPlayer)
            return

        val killCount = killCounts.computeIfAbsent(livingBase) { 0 }
        killCounts.remove(livingBase)
        if (killCount < ConfigSpells.consumingFury.mobsToKill)
            livingBase.attackEntityFrom(DamageSource.MAGIC, ConfigSpells.consumingFury.punishmentDamage)
    }

    override fun isReady(duration: Int, amplifier: Int): Boolean {
        return true
    }

    companion object {
        private val killCounts = HashMap<EntityPlayer, Int>()

        @SubscribeEvent
        fun onEntityDamaged(event: LivingDamageEvent) {
            val source = event.source as? EntityDamageSource ?: return
            val entity = source.trueSource as? EntityPlayer ?: return
            if (!entity.isPotionActive(ModPotions.consuming_fury))
                return
            event.amount = event.amount + ConfigSpells.consumingFury.extraDamage
        }

        @SubscribeEvent
        fun onEntityDeath(event: LivingDeathEvent) {
            val source = event.source as? EntityDamageSource ?: return
            val entity = source.trueSource as? EntityPlayer ?: return
            if (!entity.isPotionActive(ModPotions.consuming_fury))
                return

            val killCount = killCounts.computeIfAbsent(entity) { 0 } + 1
            killCounts[entity] = killCount
            val killsLeft = ConfigSpells.consumingFury.mobsToKill - killCount
            if (killsLeft > 0)
                UtilChat.addChatMessage(entity, "Kills left: $killCount")
        }
    }
}