package Harmonioncraft.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import Harmonioncraft.common.lib.Reference;
import Harmonioncraft.common.lib.Vector3;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
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
public class NetworkManager implements IPacketHandler, IPacketReceiver {
    
	public static void sendPacketToClients(Packet packet)
	{
		try
		{
	        if(FMLCommonHandler.instance().getMinecraftServerInstance() != null)
	        {
	        	FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendPacketToAllPlayers(packet);
	        }
		}
		catch (Exception e)
        {
			System.out.println("Sending packet to client failed.");
            e.printStackTrace();
        }
	}
	
	/**
	 * Sends packets to clients around a specific coordinate. A wrapper using Vector3.
	 * See {@PacketDispatcher} for detailed information.
	 */
	public static void sendPacketToClients(Packet packet, World worldObj, Vector3 position, double range)
	{
		try
		{
	        PacketDispatcher.sendPacketToAllAround(position.x, position.y, position.z, range, worldObj.provider.dimensionId, packet);
		}
		catch (Exception e)
        {
			System.out.println("Sending packet to client failed.");
            e.printStackTrace();
        }
	}
	
	/**
	 * Sends a packet to all the clients on this server.
	 */
	public static void sendPacketToClients(Packet packet, World worldObj)
	{
		try
		{
			PacketDispatcher.sendPacketToAllInDimension(packet, worldObj.provider.dimensionId);
		}
		catch (Exception e)
        {
			System.out.println("Sending packet to client failed.");
            e.printStackTrace();
        }
	}
	
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
	
	public static Packet getPacket(String channelName, Object... sendData)
    {
		return getPacketWithID(channelName, PacketType.UNSPECIFIED.ordinal(), sendData);
    }
	
	public enum PacketType
	{
		UNSPECIFIED, TILEENTITY;
		
	    public static PacketType get(int id)
		{
	    	if (id >= 0 && id < PacketType.values().length)
	        {
	            return PacketType.values()[id];
	        }
	        return UNSPECIFIED;
		}
	}
	
	/**
	 * Gets a packet for the tile entity.
	 * @return
	 */
	public static Packet getPacket(String channelName, TileEntity sender, Object... sendData)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeInt(PacketType.TILEENTITY.ordinal());
        	
            data.writeInt(sender.xCoord);
            data.writeInt(sender.yCoord);
            data.writeInt(sender.zCoord);
            data = encodeDataStream(data, sendData);
	        
	        Packet250CustomPayload packet = new Packet250CustomPayload();
	        packet.channel = channelName;
	        packet.data = bytes.toByteArray();
	        packet.length = packet.data.length;
	        
	        return packet;
        }
        catch (IOException e)
        {
        	System.out.println("Failed to create packet.");
            e.printStackTrace();
        }
        
		return null;
    }
	
	public static Packet getPacketWithID(String channelName, int id, Object... sendData)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);

        try
        {
            data.writeInt(id);
        	
            data = encodeDataStream(data, sendData);
	        
	        Packet250CustomPayload packet = new Packet250CustomPayload();
	        packet.channel = channelName;
	        packet.data = bytes.toByteArray();
	        packet.length = packet.data.length;
	        
	        return packet;
        }
        catch (IOException e)
        {
        	System.out.println("Failed to create packet.");
            e.printStackTrace();
        }
        
		return null;
    }
	public static DataOutputStream encodeDataStream(DataOutputStream data, Object... sendData)
	{
		try
		{
			for(Object dataValue : sendData)
	        {
	        	if(dataValue instanceof Integer)
	        	{
	        		data.writeInt((Integer)dataValue);
	        	}
	        	else if(dataValue instanceof Float)
	        	{
	        		data.writeFloat((Float)dataValue);
	        	}
	        	else if(dataValue instanceof Double)
	        	{
	        		data.writeDouble((Double)dataValue);
	        	}
	        	else if(dataValue instanceof Byte)
	        	{
	        		data.writeByte((Byte)dataValue);
	        	}
	        	else if(dataValue instanceof Boolean)
	        	{
	        		data.writeBoolean((Boolean)dataValue);
	        	}
	        	else if(dataValue instanceof String)
	        	{
	        		data.writeUTF((String)dataValue);
	        	}
	        	else if(dataValue instanceof Short)
	        	{
	        		data.writeShort((Short)dataValue);
	        	}
	        	else if(dataValue instanceof Long)
	        	{
	        		data.writeLong((Long)dataValue);
	        	}
	        }
			
			return data;
			
		}
		catch (IOException e)
        {
			System.out.println("Packet data encoding failed.");
            e.printStackTrace();
        }
		
		return data;
	}

	@Override
	public void onPacketData(net.minecraft.src.NetworkManager network, Packet250CustomPayload packet, Player player)
	{
		try
        {
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			
			int packetTypeID = data.readInt();
			
			PacketType packetType = PacketType.get(packetTypeID);
			
			if(packetType == PacketType.TILEENTITY)
			{
				int x = data.readInt();
				int y = data.readInt();
				int z = data.readInt();
				
				World world = ((EntityPlayer)player).worldObj;
				
				if(world != null)
				{
					TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
					
					if(tileEntity != null)
					{
						if(tileEntity instanceof IPacketReceiver)
						{
							((IPacketReceiver)tileEntity).handlePacketData(network, packetTypeID, packet, ((EntityPlayer)player), data);
						}
					}
				}
			}
			else
			{
				this.handlePacketData(network, packetTypeID, packet, ((EntityPlayer)player), data);
			}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void handlePacketData(net.minecraft.src.NetworkManager network, int packetType, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
	{

	}

}