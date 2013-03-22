package net.Harmonion.util.network;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.Harmonion.util.Game;
import net.Harmonion.util.network.HarmonionPacket.PacketType;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class HarmonionPacketHandler implements IPacketHandler
{
    private static PacketType[] packetTypes = PacketType.values();

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

            PacketType var7 = packetTypes[var6];

            switch (HarmonionPacketHandler.$SwitchMap$railcraft$common$util$network$PacketType[var7.ordinal()])
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

                case 9:
                case 10:

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
    
    static final int[] $SwitchMap$railcraft$common$util$network$PacketType = new int[PacketType.values().length];

    static
    {
        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.TILE_ENTITY.ordinal()] = 1;
        }
        catch (NoSuchFieldError var10)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.GUI_RETURN.ordinal()] = 2;
        }
        catch (NoSuchFieldError var9)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.TILE_EXTRA_DATA.ordinal()] = 3;
        }
        catch (NoSuchFieldError var8)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.TILE_REQUEST.ordinal()] = 4;
        }
        catch (NoSuchFieldError var7)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.GUI_UPDATE.ordinal()] = 5;
        }
        catch (NoSuchFieldError var6)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.EFFECT.ordinal()] = 6;
        }
        catch (NoSuchFieldError var5)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.CONTROLLER_UPDATE.ordinal()] = 7;
        }
        catch (NoSuchFieldError var4)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.RECEIVER_UPDATE.ordinal()] = 8;
        }
        catch (NoSuchFieldError var3)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.CONTROLLER_REQUEST.ordinal()] = 9;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$PacketType[PacketType.RECEIVER_REQUEST.ordinal()] = 10;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
