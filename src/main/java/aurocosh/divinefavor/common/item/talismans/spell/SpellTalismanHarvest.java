package aurocosh.divinefavor.common.item.talismans.spell;

import aurocosh.divinefavor.common.config.common.ConfigSpells;
import aurocosh.divinefavor.common.item.talismans.spell.base.ItemSpellTalisman;
import aurocosh.divinefavor.common.item.talismans.spell.base.SpellOptions;
import aurocosh.divinefavor.common.item.talismans.spell.base.TalismanContext;
import aurocosh.divinefavor.common.spirit.base.ModSpirit;
import aurocosh.divinefavor.common.util.UtilBlock;
import aurocosh.divinefavor.common.util.UtilCoordinates;
import aurocosh.divinefavor.common.util.UtilList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockReed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;

public class SpellTalismanHarvest extends ItemSpellTalisman {
    public static final ImmutableSet<Material> ALLOWED_MATERIALS =
            ImmutableSet.of(net.minecraft.block.material.Material.WEB,
                    net.minecraft.block.material.Material.LEAVES,
                    net.minecraft.block.material.Material.VINE,
                    net.minecraft.block.material.Material.GOURD,
                    net.minecraft.block.material.Material.CACTUS);

    public SpellTalismanHarvest(String name, ModSpirit spirit, int favorCost, EnumSet<SpellOptions> options) {
        super(name, spirit, favorCost, options);
    }

    @Override
    protected void performActionServer(TalismanContext context) {
        World world = context.world;
        EntityPlayer player = context.player;
        ItemStack stack = player.getHeldItem(context.hand);

        List<BlockPos> posList = UtilCoordinates.getBlocksInSphere(player.getPosition(), ConfigSpells.harvest.radius);
        List<BlockPos> plantList = UtilList.filterList(posList, element -> isValidCrop(element, world));
        for (BlockPos pos : plantList)
            UtilBlock.removeBlock(player, world, stack, pos, true, false, false);
    }

    private boolean isValidCrop(BlockPos pos, World world) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof BlockReed && !(world.getBlockState(pos.down()).getBlock() instanceof BlockReed))
            return true;
        if (block instanceof BlockCrops && ((BlockCrops) block).isMaxAge(state))
            return true;
        if (block instanceof BlockNetherWart && state.getValue(BlockNetherWart.AGE) == 3)
            return true;
        return ALLOWED_MATERIALS.contains(state.getMaterial());
    }
}
