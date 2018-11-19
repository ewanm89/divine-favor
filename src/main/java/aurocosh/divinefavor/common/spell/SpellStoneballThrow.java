package aurocosh.divinefavor.common.spell;

import aurocosh.divinefavor.api.spell.Spell;
import aurocosh.divinefavor.api.spell.SpellContext;
import aurocosh.divinefavor.common.entity.EntityStoneball;
import aurocosh.divinefavor.common.lib.LibSpellNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SpellStoneballThrow extends Spell {
    public SpellStoneballThrow() {
        super(LibSpellNames.STONEBALL_THROW);
    }

    @Override
    protected boolean performAction(SpellContext context) {
        throwStoneball(context.worldIn,context.playerIn);
        return true;
    }

    @Override
    protected boolean claimCost( SpellContext context) {
        return true;
    }

    public boolean throwStoneball(World worldIn, EntityPlayer playerIn) {
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (spellRand.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote)
        {
            EntityStoneball entityStoneball = new EntityStoneball(worldIn, playerIn);
            entityStoneball.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            worldIn.spawnEntity(entityStoneball);
        }
        return true;
    }
}