package Harmonioncraft.common.core;

import java.util.Iterator;
import Harmonioncraft.common.commands.CommandHMCV;
import Harmonioncraft.common.lib.GuiIds;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.CommandHandler;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ICommand;
import net.minecraft.src.World;

/**
 * CommonProxy
 * 
 * The common proxy class between client and server. Client proxy extends this
 * for further client specific functionality
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommonProxy implements IGuiHandler {

    public void registerKeyBindingHandler() {}

    public void setKeyBinding(String name, int value) {}

    public void registerSoundHandler() {}

    public void initCustomRarityTypes() {}

    public EnumRarity getCustomRarityType(String customRarity) {
        return null;
    }
    
    public void initRenderingAndTextures() {}
    
    public void initTileEntities() {
    	//GameRegistry.registerTileEntity(TileCalcinator.class, "tileCalcinator");
    }
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        /*if (ID == GuiIds.HARMONION_CRAFTING) {
            return new ContainerPortableCrafting(player.inventory, world, x, y, z);
        }*/
        
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        /*if (ID == GuiIds.PORTABLE_CRAFTING) {
            return new GuiPortableCrafting(player, world, x, y, z);
        }*/
        
        return null;
    }
    
    public void serverStarting(MinecraftServer var1)
    {
        CommandHandler var2 = (CommandHandler)var1.getCommandManager();
        
        ICommand[] var5 = this.getConsoleCommands();
        {
        	ICommand[] var6 = var5;
        	int var7 = var5.length;
        	for (int var8 = 0; var8 < var7; ++var8)
        	{
        		ICommand var9 = var6[var8];
        		var2.registerCommand(var9);
        	}
        }
    }
    
    public ICommand[] getConsoleCommands()
    {
        return new ICommand[] {new CommandHMCV()};
    }
}