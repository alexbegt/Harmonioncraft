package net.Harmonion.core.command;

import java.util.List;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.Harmonion.core.main.helper.VersionHelper;
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
	
	Entity var5;
	
	public String getCommandUsage(ICommandSender var1)
	  {
	    return "/" + this.getCommandName() + " help";
	  }
	
	public List getCommandAliases()
    {
        return null;
    }
	
	public void processCommand(ICommandSender var1, String[] var2) {
		
		if (var2.length <= 0)
        {
            throw new WrongUsageException("Type \'" + this.getCommandUsage(var1) + "\' for help.", new Object[0]);
        }
        else if (var2[0].matches("about"))
        {
            this.commandAbout(var1, var2);
        }
        else if (var2[0].matches("version"))
        {
        	this.commandVersion(var1, var2);
        }
        else if (var2[0].matches("help"))
        {
            var1.sendChatToPlayer("Format: \'" + this.getCommandName() + " <command> <arguments>\'");
            var1.sendChatToPlayer("Available commands:");
            var1.sendChatToPlayer("- about : Mod Information.");
            var1.sendChatToPlayer("- version : Check Mod Version.");
        }
        else
        {
            throw new WrongUsageException(this.getCommandUsage(var1), new Object[0]);
        }
	}
	
	
	private void commandAbout(ICommandSender var1, String[] var2)
    {
        var1.sendChatToPlayer("Was Made By Alexbegt and DJ Pantheris.");
    }
	
	private void commandVersion(ICommandSender var1, String[] var2)
	{
		VersionHelper.checkVersion();
		if (VersionHelper.result == VersionHelper.OUTDATED) {
            FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(VersionHelper.getResultMessageForClient());
        }
	}
	
	public static String getMinecraftVersion()
	{
	    return "1.3.2";
	}

}
