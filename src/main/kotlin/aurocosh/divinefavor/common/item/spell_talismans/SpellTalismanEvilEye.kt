package aurocosh.divinefavor.common.item.spell_talismans

import aurocosh.divinefavor.common.config.common.ConfigSpell
import aurocosh.divinefavor.common.item.spell_talismans.base.ItemSpellTalisman
import aurocosh.divinefavor.common.item.spell_talismans.base.SpellOptions
import aurocosh.divinefavor.common.item.spell_talismans.context.CastContext
import aurocosh.divinefavor.common.lib.extensions.divinePlayerData
import aurocosh.divinefavor.common.network.message.client.syncing.MessageSyncEvilEye
import aurocosh.divinefavor.common.potions.base.effect.ModEffect
import aurocosh.divinefavor.common.potions.common.ModCurses
import aurocosh.divinefavor.common.spirit.base.ModSpirit
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.MobEffects
import net.minecraft.potion.PotionEffect
import net.minecraft.util.DamageSource
import java.util.*

class SpellTalismanEvilEye(name: String, spirit: ModSpirit, favorCost: Int, options: EnumSet<SpellOptions>) : ItemSpellTalisman(name, spirit, favorCost, options) {

    override fun validate(context: CastContext): Boolean {
        return context.target != null
    }

    override fun performActionServer(context: CastContext) {
        val player = context.player

        val evilEyeData = player.divinePlayerData.evilEyeData
        if (player.isPotionActive(ModCurses.evil_eye))
            evilEyeData.increaseSeverity(ConfigSpell.evilEye.severityIncrease)
        else
            evilEyeData.severity = ConfigSpell.evilEye.startingSeverity

        val severity = evilEyeData.severity
        val target = context.target as EntityLivingBase
        target.attackEntityFrom(DamageSource.causePlayerDamage(player), (ConfigSpell.evilEye.damagePerSeverity * severity).toFloat())
        target.addPotionEffect(PotionEffect(MobEffects.SLOWNESS, ConfigSpell.evilEye.slownessTime, ConfigSpell.evilEye.slownessLevel))
        player.addPotionEffect(ModEffect(ModCurses.evil_eye, ConfigSpell.evilEye.evilEyeTime).setIsCurse())

        MessageSyncEvilEye(severity).sendTo(player)
    }
}
