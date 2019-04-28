package aurocosh.divinefavor.common.potions.potions;

import aurocosh.divinefavor.common.config.common.ConfigSpells;
import aurocosh.divinefavor.common.constants.ConstMisc;
import aurocosh.divinefavor.common.potions.base.effect.ModEffect;
import aurocosh.divinefavor.common.potions.base.potion.ModPotion;
import aurocosh.divinefavor.common.potions.common.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ConstMisc.MOD_ID)
public class PotionWildCharge extends ModPotion {
    public PotionWildCharge() {
        super("wild_charge", true, 0x7FB8A4);
        registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "817e7bc4-8ad5-4323-9131-aa71236a1b83", -ConfigSpells.wildSprint.slownessForce, 2);
    }

    @Override
    protected void onPotionRemoved(EntityLivingBase livingBase) {
        super.onPotionRemoved(livingBase);
        livingBase.addPotionEffect(new ModEffect(ModPotions.wild_sprint, ConfigSpells.wildSprint.speedDuration, ConfigSpells.wildSprint.speedLevel).setIsCurse());
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
