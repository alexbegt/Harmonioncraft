package net.Harmonion.command;

import java.util.List;

import net.Harmonion.server.Harmonion;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.command.ICommand;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

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
        	sender.sendChatToPlayer("- About : Mod information.");
        	return;
        }

    	throw new WrongUsageException(this.getCommandUsage(sender));
	}

	private void commandVersion(ICommandSender sender, String[] arguments) {
    	sender.sendChatToPlayer(String.format("Harmonioncraft Was made by alexbegt."));
	}
	

}