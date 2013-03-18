package net.Harmonion.util.network;

import net.Harmonion.util.network.HarmonionPacket$PacketType;

class PacketPairRequest$1
{
    static final int[] $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType = new int[HarmonionPacket$PacketType.values().length];

    static
    {
        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.CONTROLLER_REQUEST.ordinal()] = 1;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        try
        {
            $SwitchMap$railcraft$common$util$network$HarmonionPacket$PacketType[HarmonionPacket$PacketType.RECEIVER_REQUEST.ordinal()] = 2;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
