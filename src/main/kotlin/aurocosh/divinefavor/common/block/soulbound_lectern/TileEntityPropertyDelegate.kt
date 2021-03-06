package aurocosh.divinefavor.common.block.soulbound_lectern

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import kotlin.reflect.KProperty

class TileEntityPropertyDelegate<T, K : TileEntity>(private val nbtProperty: NbtProperty<T>, val sync: (K) -> Unit) : INbtSerializable, IPropertyDelegate<T> {
    var value = nbtProperty.defaultValue

    override fun writeToNbt(compound: NBTTagCompound) {
        nbtProperty.setValue(compound, value)
    }

    override fun readFromNbt(compound: NBTTagCompound) {
        value = nbtProperty.getValue(compound)
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value
    }

    @Suppress("UNCHECKED_CAST")
    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (this.value == value)
            return
        this.value = value
        val lectern = thisRef as K
        sync.invoke(lectern)
    }
}