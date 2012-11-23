package net.Harmonion.carts.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.Harmonion.carts.lib.Reference;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;

public enum PacketTypeHandler {
	TILE(PacketTileUpdate.class);

	private Class<? extends PacketHMCC> clazz;

	PacketTypeHandler(Class<? extends PacketHMCC> clazz) {
		this.clazz = clazz;
	}

	public static PacketHMCC buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		PacketHMCC packet = null;

		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}

	public static PacketHMCC buildPacket(PacketTypeHandler type) {
		PacketHMCC packet = null;

		try {
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return packet;
	}

	public static Packet populatePacket(PacketHMCC packetHMC) {
		byte[] data = packetHMC.populate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetHMC.isChunkDataPacket;

		return packet250;
	}
}