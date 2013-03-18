package net.Harmonion.util.network;

import net.Harmonion.api.signals.AbstractPair;
import net.Harmonion.api.signals.ISignalPacketBuilder;
import net.Harmonion.tanks.RailcraftTileEntity;
import net.minecraft.server.management.PlayerInstance;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PacketBuilder implements ISignalPacketBuilder
{
    private static PacketBuilder instance;

    public static PacketBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new PacketBuilder();
        }

        return instance;
    }

    public void sendTileEntityPacket(RailcraftTileEntity var1)
    {
        if (var1.worldObj instanceof WorldServer)
        {
            WorldServer var2 = (WorldServer)var1.worldObj;
            PlayerInstance var3 = var2.getPlayerManager().getOrCreateChunkWatcher(var1.xCoord >> 4, var1.zCoord >> 4, false);

            if (var3 != null)
            {
                PacketTileEntity var4 = new PacketTileEntity(var1);
                var3.sendToAllPlayersWatchingChunk(var4.getPacket());
            }
        }
    }

    public void sendPairPacketUpdate(AbstractPair var1)
    {
        PacketPairUpdate var2 = new PacketPairUpdate(var1);
        PacketDispatcher.sendPacketToAllInDimension(var2.getPacket(), var1.getTile().worldObj.provider.dimensionId);
    }

    public void sendPairPacketRequest(AbstractPair var1)
    {
        PacketPairRequest var2 = new PacketPairRequest(var1);
        PacketDispatcher.sendPacketToServer(var2.getPacket());
    }

    public void sendGuiReturnPacket(IGuiReturnHandler var1)
    {
        PacketGuiReturn var2 = new PacketGuiReturn(var1);
        PacketDispatcher.sendPacketToServer(var2.getPacket());
    }
}
