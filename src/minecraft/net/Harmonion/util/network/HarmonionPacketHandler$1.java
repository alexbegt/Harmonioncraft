package net.Harmonion.util.network;

import net.Harmonion.util.network.HarmonionPacket$PacketType;

class HarmonionPacketHandler$1
{
    static final int[] $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType = new int[HarmonionPacket$PacketType.values().length];

    static
    {
        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.TILE_ENTITY.ordinal()] = 1;
        }
        catch (NoSuchFieldError var10)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.GUI_RETURN.ordinal()] = 2;
        }
        catch (NoSuchFieldError var9)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.TILE_EXTRA_DATA.ordinal()] = 3;
        }
        catch (NoSuchFieldError var8)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.TILE_REQUEST.ordinal()] = 4;
        }
        catch (NoSuchFieldError var7)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.GUI_UPDATE.ordinal()] = 5;
        }
        catch (NoSuchFieldError var6)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.EFFECT.ordinal()] = 6;
        }
        catch (NoSuchFieldError var5)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.CONTROLLER_UPDATE.ordinal()] = 7;
        }
        catch (NoSuchFieldError var4)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.RECEIVER_UPDATE.ordinal()] = 8;
        }
        catch (NoSuchFieldError var3)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.CONTROLLER_REQUEST.ordinal()] = 9;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.RECEIVER_REQUEST.ordinal()] = 10;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
