package net.Harmonion.util.network;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.Harmonion.util.Game;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class HarmonionPacketHandler implements IPacketHandler
{
    private static HarmonionPacket$PacketType[] packetTypes = HarmonionPacket$PacketType.values();

    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        DataInputStream var4 = new DataInputStream(new ByteArrayInputStream(var2.data));

        try
        {
            Object var5 = null;
            byte var6 = var4.readByte();

            if (var6 < 0)
            {
                return;
            }

            HarmonionPacket$PacketType var7 = packetTypes[var6];

            switch (HarmonionPacketHandler$1.$SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[var7.ordinal()])
            {
                case 1:
                    var5 = new PacketTileEntity();
                    break;

                case 2:
                    var5 = new PacketGuiReturn();
                    break;

                case 3:
                    var5 = new PacketTileExtraData();
                    break;

                case 4:
                    var5 = new PacketTileRequest(var3);
                    break;

                case 5:
                    var5 = new PacketGuiUpdate();
                    break;

                case 6:

                case 7:
                case 8:
                    var5 = new PacketPairUpdate(var7);
                    break;

                case 9:
                case 10:
                    var5 = new PacketPairRequest(var3, var7);
                    break;

                default:
                    return;
            }

            ((HarmonionPacket)var5).readData(var4);
        }
        catch (IOException var8)
        {
            Game.log(Level.SEVERE, "Exception in PacketHandler.onPacketData {0}", new Object[] {var8});
            var8.printStackTrace();
        }
    }
}
