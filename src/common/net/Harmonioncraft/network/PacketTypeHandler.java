package net.Harmonioncraft.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.Harmonioncraft.lib.Reference;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;

public enum PacketTypeHandler {
	TILE(PacketTileUpdate.class);

	private Class<? extends PacketHMC> clazz;

	PacketTypeHandler(Class<? extends PacketHMC> clazz) {
		this.clazz = clazz;
	}

	public static PacketHMC buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);

		PacketHMC packet = null;

		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		packet.readPopulate(dis);

		return packet;
	}

	public static PacketHMC buildPacket(PacketTypeHandler type) {
		PacketHMC packet = null;

		try {
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return packet;
	}

	public static Packet populatePacket(PacketHMC packetHMC) {
		byte[] data = packetHMC.populate();

		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetHMC.isChunkDataPacket;

		return packet250;
	}
}