package com.dyn.server.packets.server;

import java.io.IOException;

import com.dyn.server.packets.AbstractMessage.AbstractServerMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;

public class MentorCommandMessage extends AbstractServerMessage<MentorCommandMessage> {
	private String command;

	// The basic, no-argument constructor MUST be included to use the new
	// automated handling
	public MentorCommandMessage() {
	}

	public MentorCommandMessage(String mentor_command) {
		command = mentor_command.replace("  ", " ");
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if (side.isServer()) {
			System.out.println(command);
			MinecraftServer.getServer().getCommandManager().executeCommand(MinecraftServer.getServer(), command);
		}
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		// basic Input/Output operations, very much like DataInputStream
		command = buffer.readStringFromBuffer(buffer.readableBytes());
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		// basic Input/Output operations, very much like DataOutputStream
		buffer.writeString(command);
	}
}
