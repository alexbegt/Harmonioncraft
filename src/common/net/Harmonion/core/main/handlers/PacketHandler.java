package net.Harmonion.core.main.handlers;

import cpw.mods.fml.common.network.IPacketHandler;
import net.Harmonion.core.network.PacketHMC;
import net.Harmonion.core.network.PacketTypeHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet250CustomPayload;

/**
 * PacketHandler
 * 
 * Handles the dispatch and receipt of packets for the mod
 * 
 * @author alexbegt
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketHandler implements IPacketHandler {

	/***
	 * Handles Packet250CustomPayload packets that are registered to an Equivalent Exchange 3 network channel
	 * @param manager The NetworkManager associated with the current platform (client/server)
	 * @param packet The Packet250CustomPayload that was received
	 * @param player The Player associated with the packet
	 */
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
    	// Build a PacketEE object from the data contained within the Packet250CustomPayload packet
    	PacketHMC packethmc = PacketTypeHandler.buildPacket(packet.data);
    	
    	// Execute the appropriate actions based on the PacketEE type
    	packethmc.execute(manager, player);
    }

}