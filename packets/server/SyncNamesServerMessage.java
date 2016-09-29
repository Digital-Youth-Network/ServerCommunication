package com.dyn.server.packets.server;

import java.io.IOException;

import com.dyn.server.packets.AbstractMessage.AbstractServerMessage;
import com.dyn.server.packets.PacketDispatcher;
import com.dyn.server.packets.client.SyncNamesMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;

public class SyncNamesServerMessage extends AbstractServerMessage<SyncNamesServerMessage> {

	// the info needed to increment a requirement
	private String dynName;
	private String playerName;

	// this packet should only be sent when a player is in the right dimension
	// so we shouldnt have to check for it ever

	// The basic, no-argument constructor MUST be included for
	// automated handling
	public SyncNamesServerMessage() {
	}

	// We need to initialize our data, so provide a suitable constructor:
	public SyncNamesServerMessage(String dyn_name, String mc_name) {
		dynName = dyn_name;
		playerName = mc_name;
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if (side.isServer()) {
			PacketDispatcher.sendToAll(new SyncNamesMessage(playerName, dynName));
		}
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		int totalBytes = buffer.readableBytes();
		dynName = buffer.readStringFromBuffer(totalBytes);
		playerName = buffer.readStringFromBuffer(totalBytes);
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeString(dynName);
		buffer.writeString(playerName);
	}
}