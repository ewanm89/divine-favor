package aurocosh.divinefavor.common.item.talisman_tools.grimoire.capability

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager

object GrimoireDataHandler {
    // The Capability field. Used for checks and references.
    // Initialized when forge registers the capability.
    @CapabilityInject(IGrimoireHandler::class)
    private val capability: Capability<IGrimoireHandler>? = null

    val CAPABILITY_GRIMOIRE: Capability<IGrimoireHandler>
        get() = capability as Capability<IGrimoireHandler>

    // Handles all of the required registration for the capability.
    fun register() {
        CapabilityManager.INSTANCE.register(IGrimoireHandler::class.java, GrimoireStorage(), DefaultGrimoireHandler::class.java)
        MinecraftForge.EVENT_BUS.register(this)
    }
}