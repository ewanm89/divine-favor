package aurocosh.divinefavor.common.network.message.client.syncing

import aurocosh.divinefavor.DivineFavor
import aurocosh.divinefavor.common.lib.extensions.divinePlayerData
import aurocosh.divinefavor.common.network.message.base.DivineClientMessage
import net.minecraft.util.math.Vec3d
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class MessageSyncRedFury : DivineClientMessage {
    var vector: Vec3d = Vec3d.ZERO

    constructor()

    constructor(vector: Vec3d) {
        this.vector = vector
    }

    @SideOnly(Side.CLIENT)
    override fun handleSafe() {
        val player = DivineFavor.proxy.clientPlayer
        player.divinePlayerData.redFuryData.vector = vector
    }
}
