package aurocosh.divinefavor.common.lib

import net.minecraft.util.ResourceLocation
import java.util.*

object EmptyConst {
    private val resourceLocation: ResourceLocation = ResourceLocation("")
    fun emptyLocation(): ResourceLocation = resourceLocation

    private val uuid = UUID(0,0)
    fun invalidUUID(): UUID = uuid
}