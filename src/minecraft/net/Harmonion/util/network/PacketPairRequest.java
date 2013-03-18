package net.Harmonion.util.network;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.Harmonion.api.signals.AbstractPair;
import net.Harmonion.api.signals.IControllerTile;
import net.Harmonion.api.signals.IReceiverTile;
import net.Harmonion.api.signals.SignalController;
import net.Harmonion.api.signals.SignalReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class PacketPairRequest extends HarmonionPacket
{
    private AbstractPair pairing;
    private Player player;
    private HarmonionPacket$PacketType packetType;

    public PacketPairRequest(Player var1, HarmonionPacket$PacketType var2)
    {
        this.player = var1;
        this.packetType = var2;
    }

    public PacketPairRequest(AbstractPair var1)
    {
        this.pairing = var1;
    }

    public void writeData(DataOutputStream var1) throws IOException
    {
        TileEntity var2 = this.pairing.getTile();
        var1.writeInt(var2.worldObj.provider.dimensionId);
        var1.writeInt(var2.xCoord);
        var1.writeInt(var2.yCoord);
        var1.writeInt(var2.zCoord);
    }

    public void readData(DataInputStream var1) throws IOException
    {
        WorldServer var2 = DimensionManager.getWorld(var1.readInt());

        if (var2 != null)
        {
            int var3 = var1.readInt();
            int var4 = var1.readInt();
            int var5 = var1.readInt();
            TileEntity var6 = var2.getBlockTileEntity(var3, var4, var5);

            switch (PacketPairRequest$1.$SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[this.packetType.ordinal()])
            {
                case 1:
                    if (var6 instanceof IControllerTile)
                    {
                        this.pairing = ((IControllerTile)var6).getController();
                    }

                    break;

                case 2:
                    if (var6 instanceof IReceiverTile)
                    {
                        this.pairing = ((IReceiverTile)var6).getReceiver();
                    }
            }

            if (this.pairing != null && this.player != null)
            {
                PacketPairUpdate var7 = new PacketPairUpdate(this.pairing);
                PacketDispatcher.sendPacketToPlayer(var7.getPacket(), this.player);
            }
        }
    }

    public int getID()
    {
        return this.pairing instanceof SignalController ? HarmonionPacket$PacketType.CONTROLLER_REQUEST.ordinal() : (this.pairing instanceof SignalReceiver ? HarmonionPacket$PacketType.RECEIVER_REQUEST.ordinal() : -1);
    }
}
