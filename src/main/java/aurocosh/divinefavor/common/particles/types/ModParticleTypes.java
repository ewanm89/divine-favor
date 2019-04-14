package aurocosh.divinefavor.common.particles.types;

import aurocosh.divinefavor.common.constants.ConstResources;
import aurocosh.divinefavor.common.particles.generic.ParticleManager;
import aurocosh.divinefavor.common.particles.generic.ParticleRenderer;
import aurocosh.divinefavor.common.particles.ParticleHandler;
import aurocosh.divinefavor.common.particles.particles.ParticleEtherealLight;
import net.minecraft.util.ResourceLocation;

public final class ModParticleTypes {
    public static ParticleType etherealLight;

    public static void preInit() {
        etherealLight = ParticleHandler.register(new ParticleManager<>(new ParticleRenderer<>(new ResourceLocation(ConstResources.TEX_PARTICLES)), new ParticleEtherealLight.EtherealLightParticleFactory()));
    }
}