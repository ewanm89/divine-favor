package aurocosh.divinefavor.common.favor;

import aurocosh.divinefavor.common.config.FavorConfig;
import aurocosh.divinefavor.common.constants.ConstResources;
import aurocosh.divinefavor.common.core.ResourceNamer;
import aurocosh.divinefavor.common.lib.interfaces.IIndexedEntry;
import aurocosh.divinefavor.common.registry.ModRegistries;
import aurocosh.divinefavor.common.registry.mappers.ModMappers;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModFavor extends IForgeRegistryEntry.Impl<ModFavor> implements IIndexedEntry {
    private final int id;
    private final String name;
    private final ResourceLocation icon;
    private final ResourceLocation symbol;

    private final int favorMin;
    private final int favorMax;
    private final int favorRegen;

    // Talisman functions
    public ModFavor(String name, FavorConfig favorConfig) {
        this.name = name;
        icon = new ResourceLocation(ConstResources.PREFIX_FAVOR_ICONS + name + ".png");
        symbol = new ResourceLocation(ConstResources.PREFIX_FAVOR_SYMBOLS + name + ".png");

        favorMin = favorConfig.startingPlayerFavorMinimum;
        favorMax = favorConfig.startingPlayerFavorMaximum;
        favorRegen = favorConfig.startingPlayerFavorRegen;

        setRegistryName(ResourceNamer.getFullName("favor", name));
        id = ModMappers.favors.register(this);
        ModRegistries.favors.register(this);
    }

    public String getName() {
        return name;
    }

    public ResourceLocation getIcon() {
        return icon;
    }

    public ResourceLocation getSymbol() {
        return symbol;
    }

    public int getFavorMin() {
        return favorMin;
    }

    public int getFavorMax() {
        return favorMax;
    }

    public int getFavorRegen() {
        return favorRegen;
    }

    @Override
    public int getId() {
        return id;
    }
}