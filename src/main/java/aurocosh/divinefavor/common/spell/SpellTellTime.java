package aurocosh.divinefavor.common.spell;

import aurocosh.divinefavor.common.entity.EntityStoneball;
import aurocosh.divinefavor.common.spell.base.ModSpell;
import aurocosh.divinefavor.common.spell.base.SpellContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SpellTellTime extends ModSpell {
    public SpellTellTime() {
        super("tell_time");
    }

    @Override
    protected boolean performAction(SpellContext context) {
        long time = context.world.getWorldTime();
        context.player.sendMessage(new TextComponentString("Time: " + time));
        return true;
    }

    public boolean throwStoneball(World worldIn, EntityPlayer playerIn) {
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (spellRand.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote)
        {
            EntityStoneball entityStoneball = new EntityStoneball(worldIn, playerIn);
            entityStoneball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            worldIn.spawnEntity(entityStoneball);
        }
        return true;
    }
}
