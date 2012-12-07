package net.Harmonion.core.command;

import java.util.List;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

import net.Harmonion.Harmonion;
import net.Harmonion.core.main.helper.Version;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandBase;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ICommandSender;
import net.minecraft.src.ServerConfigurationManager;
import net.minecraft.src.World;
import net.minecraft.src.WrongUsageException;
import net.minecraft.src.ICommand;

public class CommandHMCV extends CommandBase{
	
	public int compareTo(Object var1)
    {
        return this.getCommandName().compareTo(((ICommand)var1).getCommandName());
    }
	
	public String getCommandName() {
		return "hmc";
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/" + this.getCommandName() + " help";
	}

	@Override public List getCommandAliases() { return null; }
	
	@Override
	public void processCommand(ICommandSender sender, String[] arguments) {
		
        if (arguments.length <= 0)
        	throw new WrongUsageException("Type '" + this.getCommandUsage(sender) + "' for help.");
        
        if(arguments[0].matches("version")) {
        	commandVersion(sender, arguments);
        	return;
        } else if(arguments[0].matches("help")) {
        	sender.sendChatToPlayer("Format: '"+ this.getCommandName() +" <command> <arguments>'");
        	sender.sendChatToPlayer("Available commands:");
        	sender.sendChatToPlayer("- version : Version information.");
        	return;
        }

    	throw new WrongUsageException(this.getCommandUsage(sender));
	}

	private void commandVersion(ICommandSender sender, String[] arguments) {
    	sender.sendChatToPlayer(String.format("BuildCraft %s for Minecraft %s (Latest: %s).", Version.getVersion(), Harmonion.proxy.getMinecraftVersion(), Version.getRecommendedVersion()));
	}
	

}