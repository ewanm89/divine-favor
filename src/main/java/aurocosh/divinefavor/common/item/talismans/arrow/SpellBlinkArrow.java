package aurocosh.divinefavor.common.item.talismans.arrow;

import aurocosh.divinefavor.common.item.talismans.base.arrow.ArrowSpell;
import aurocosh.divinefavor.common.util.UtilEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;

public class SpellBlinkArrow extends ArrowSpell {
    public SpellBlinkArrow() {
        super(false);
    }

    @Override
    protected void performActionServer(EntityLivingBase target, EntityLivingBase shooter, EntityArrow arrow) {
        UtilEntity.teleport(shooter,arrow.getPosition());
    }
}