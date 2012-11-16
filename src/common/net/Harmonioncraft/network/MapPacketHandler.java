package net.Harmonioncraft.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet131MapData;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.ITinyPacketHandler;

public class MapPacketHandler implements ITinyPacketHandler {

	@Override
	public void handle(NetHandler handler, Packet131MapData mapData) {
		int id = mapData.uniqueID;
	    if ((handler instanceof NetClientHandler))
	    {
	    	this.handleClientPacket((NetClientHandler)handler, mapData.uniqueID, mapData.itemData);
	    }
	  }
	
	@SideOnly(Side.CLIENT)
    public void handleClientPacket(NetClientHandler var1, short var2, byte[] var3)
    {
		DataInputStream dataStream = null;
	    if (var3 != null)
	    {
	    	dataStream = new DataInputStream(new ByteArrayInputStream(var3));
	    }
	    switch (var2)
	    {
	    	case 0:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 1:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 2:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 3:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 4:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 5:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 6:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 7:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 8:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 9:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 10:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 11:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    		
	    	case 12:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    	case 13:
	    		try
	    		{
	    			Minecraft.getMinecraft().ingameGUI.setRecordPlayingMessage(dataStream.readUTF());
	    		}
	    		catch (IOException var21)
	    		{
	    			var21.printStackTrace();
	    		}
	    		
	    		break;
	    }
    }
	
}
