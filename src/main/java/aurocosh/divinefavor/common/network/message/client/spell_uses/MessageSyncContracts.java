package aurocosh.divinefavor.common.network.message.client.spell_uses;

import aurocosh.divinefavor.DivineFavor;
import aurocosh.divinefavor.common.custom_data.player.PlayerData;
import aurocosh.divinefavor.common.custom_data.player.data.favor.FavorData;
import aurocosh.divinefavor.common.network.base.NetworkWrappedClientMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncContracts extends NetworkWrappedClientMessage {
    private static String TAG_CONTRACTS = "Contracts";

    public NBTTagCompound tag;

    public MessageSyncContracts() {
    }

    public MessageSyncContracts(FavorData data) {
        tag = getNbtTagCompound(data);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void handleSafe() {
        EntityPlayer player = DivineFavor.proxy.getClientPlayer();
        FavorData data = PlayerData.get(player).getFavorData();
        setDataFromNBT(data, tag);
    }

    public static NBTTagCompound getNbtTagCompound(FavorData instance) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setTag(TAG_CONTRACTS, instance.serializeContract());
        return tag;
    }

    public static void setDataFromNBT(FavorData instance, NBTTagCompound nbt) {
        instance.deserializeContract(nbt.getCompoundTag(TAG_CONTRACTS));
    }
}