package aurocosh.divinefavor.common.jei;

import aurocosh.divinefavor.DivineFavor;
import aurocosh.divinefavor.common.constants.LibMisc;
import aurocosh.divinefavor.common.item.base.IDescriptionProvider;
import aurocosh.divinefavor.common.item.base.ModItems;
import aurocosh.divinefavor.common.receipes.ImmaterialMediumRecipe;
import aurocosh.divinefavor.common.receipes.ModRecipes;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.darkhax.bookshelf.util.GameUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;

@JEIPlugin
public class DivineFavorJEIPlugin implements IModPlugin {
    public static final String IMMATERIAL_MEDIUM = LibMisc.MOD_ID + ".immaterial_medium";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new ImmaterialMediumCategory(helper));
    }

    @Override
    public void register(IModRegistry registry) {
        registerDescription(registry);
        registerRecipes(registry);
    }

    private void registerRecipes(IModRegistry registry) {
        registry.handleRecipes(ImmaterialMediumRecipe.class, ImmaterialMediumWrapper::new, IMMATERIAL_MEDIUM);
        registry.addRecipes(ModRecipes.recipes.values(), IMMATERIAL_MEDIUM);
    }

    private void registerDescription(IModRegistry registry) {
        for (final Item item : ModItems.getItems()) {
            if(!(item instanceof IDescriptionProvider))
                continue;
            IDescriptionProvider descriptionProvider = (IDescriptionProvider)item;
            final String key = "jei." + descriptionProvider.getTranslationKey();
            registry.addIngredientInfo(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE), ItemStack.class, key);
            this.validateKey(key);
        }
    }

    private void validateKey (String key) {
        if (GameUtils.isClient())
            if (!I18n.canTranslate(key))
                DivineFavor.logger.info("Could not translate: " + key);
    }
}