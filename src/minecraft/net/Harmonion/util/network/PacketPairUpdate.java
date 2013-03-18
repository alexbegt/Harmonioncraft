package net.Harmonion.util.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import net.Harmonion.api.core.WorldCoordinate;
import net.Harmonion.api.signals.AbstractPair;
import net.Harmonion.api.signals.IControllerTile;
import net.Harmonion.api.signals.IReceiverTile;
import net.Harmonion.api.signals.SignalController;
import net.Harmonion.api.signals.SignalReceiver;
import net.Harmonion.util.Game;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketPairUpdate extends HarmonionPacket
{
    private AbstractPair pairing;
    private HarmonionPacket$PacketType packetType;

    public PacketPairUpdate(HarmonionPacket$PacketType var1)
    {
        this.packetType = var1;
    }

    public PacketPairUpdate(AbstractPair var1)
    {
        this.pairing = var1;
    }

    public void writeData(DataOutputStream var1) throws IOException
    {
        var1.writeInt(this.pairing.getTile().xCoord);
        var1.writeInt(this.pairing.getTile().yCoord);
        var1.writeInt(this.pairing.getTile().zCoord);
        Collection var2 = this.pairing.getPairs();
        var1.writeByte(var2.size());
        Iterator var3 = var2.iterator();

        while (var3.hasNext())
        {
            WorldCoordinate var4 = (WorldCoordinate)var3.next();
            var1.writeInt(var4.x);
            var1.writeInt(var4.y);
            var1.writeInt(var4.z);
        }
    }

    @SideOnly(Side.CLIENT)
    public void readData(DataInputStream var1) throws IOException
    {
        World var2 = Game.getWorld();
        int var3 = var1.readInt();
        int var4 = var1.readInt();
        int var5 = var1.readInt();
        TileEntity var6 = var2.getBlockTileEntity(var3, var4, var5);

        if (this.packetType == HarmonionPacket$PacketType.CONTROLLER_UPDATE)
        {
            if (var6 instanceof IControllerTile)
            {
                this.pairing = ((IControllerTile)var6).getController();
            }
        }
        else if (this.packetType == HarmonionPacket$PacketType.RECEIVER_UPDATE && var6 instanceof IReceiverTile)
        {
            this.pairing = ((IReceiverTile)var6).getReceiver();
        }

        if (this.pairing != null)
        {
            try
            {
                this.pairing.clearPairings();
            }
            catch (Throwable var9)
            {
                Game.logErrorAPI("Railcraft", var9);
            }

            byte var7 = var1.readByte();

            for (int var8 = 0; var8 < var7; ++var8)
            {
                this.pairing.addPair(var1.readInt(), var1.readInt(), var1.readInt());
            }
        }
    }

    public int getID()
    {
        return this.pairing instanceof SignalController ? HarmonionPacket$PacketType.CONTROLLER_UPDATE.ordinal() : (this.pairing instanceof SignalReceiver ? HarmonionPacket$PacketType.RECEIVER_UPDATE.ordinal() : -1);
    }
}
