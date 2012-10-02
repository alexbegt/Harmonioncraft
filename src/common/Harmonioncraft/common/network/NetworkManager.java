package Harmonioncraft.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import Harmonioncraft.common.lib.Reference;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * PacketHandler
 * 
 * Handles the dispatch and receipt of packets for the mod
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class NetworkManager implements IPacketHandler {
    
    public void announceBlockUpdate(World var1, int var2, int var3, int var4)
    {
        Packet250CustomPayload var5 = null;
        Iterator var6 = var1.playerEntities.iterator();

        while (var6.hasNext())
        {
            Object var7 = var6.next();
            EntityPlayerMP var8 = (EntityPlayerMP)var7;
            int var9 = Math.min(Math.abs(var2 - (int)var8.posX), Math.abs(var4 - (int)var8.posZ));

            if (var9 <= MinecraftServer.getServer().getConfigurationManager().getEntityViewDistance() + 16)
            {
                if (var5 == null)
                {
                    try
                    {
                        ByteArrayOutputStream var10 = new ByteArrayOutputStream();
                        DataOutputStream var11 = new DataOutputStream(var10);
                        var11.writeByte(3);
                        var11.writeInt(var1.provider.dimensionId);
                        var11.writeInt(var2);
                        var11.writeInt(var3);
                        var11.writeInt(var4);
                        var11.close();
                        var5 = new Packet250CustomPayload();
                        var5.channel = Reference.CHANNEL_NAME;
                        var5.isChunkDataPacket = true;
                        var5.data = var10.toByteArray();
                        var5.length = var10.size();
                    }
                    catch (IOException var12)
                    {
                        throw new RuntimeException(var12);
                    }
                }

                var8.playerNetServerHandler.sendPacketToPlayer(var5);
            }
        }
    }

	@Override
	public void onPacketData(net.minecraft.src.NetworkManager manager, Packet250CustomPayload packet, Player player) {
		
	}

}