package aurocosh.divinefavor.common.item.spell_bow.capability;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class SpellBowDataHandler {
    // The Capability field. Used for checks and references.
    // Initialized when forge registers the capability.
    @CapabilityInject(ISpellBowHandler.class)
    public static final Capability<ISpellBowHandler> CAPABILITY_SPELL_BOW = null;

    // Handles all of the required registration for the capability.
    public static void register() {
        CapabilityManager.INSTANCE.register(ISpellBowHandler.class, new SpellBowStorage(), DefaultSpellBowHandler.class);
        MinecraftForge.EVENT_BUS.register(new SpellBowDataHandler());
    }

//    // Allows the provider to be attached to a target entity.
//    @SubscribeEvent
//    public void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
//        ItemStack object = event.getObject();
//        assert object != null;
//        if(object.getItem() == ModItems.grimoire)
//            event.addCapability(ResourceNamer.getFullName("grimoire_capability"), new SpellBowProvider());
//    }
}