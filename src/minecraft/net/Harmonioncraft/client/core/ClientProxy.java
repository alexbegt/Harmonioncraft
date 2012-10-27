package net.Harmonioncraft.client.core;

import net.Harmonioncraft.ClientTickHandler;
import net.Harmonioncraft.client.core.handlers.SoundHandler;
import net.Harmonioncraft.client.core.handlers.ThreadDownloadResourcesHandler;
import static net.Harmonioncraft.lib.CustomItemRarity.*;
import net.Harmonioncraft.core.CommonProxy;
import net.Harmonioncraft.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.IBlockAccess;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.registry.TickRegistry;

/**
 * ClientProxy
 * 
 * Client specific functionality that cannot be put into CommonProxy
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void registerTickHander() {
		super.registerTickHander();
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}
	
	//hello
	
    @Override
    public void registerKeyBindingHandler() {
        //KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
    }
    
    @Override
    public int getBlockTexture(Block var1, IBlockAccess var2, int var3, int var4, int var5, int var6)
    {
        return var1.getBlockTexture(var2, var3, var4, var5, var6);
    }

    @Override
    public void setKeyBinding(String name, int value) {
        //KeyBindings.addKeyBinding(name, value);
        //KeyBindings.addIsRepeating(false);
    }
    
    @Override
    public void initSounds()
    {
    	try
    	{
    		(new ThreadDownloadResourcesHandler(Minecraft.getMinecraftDir(), Minecraft.getMinecraft())).start();
    	}
    	catch (Exception var2)
    	{
    	}
    }

    @Override
    public void registerSoundHandler() {
        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }

    @Override
    public void initCustomRarityTypes() {
        EnumHelperClient.addRarity(JUNK, COLOR_JUNK, DISPLAY_NAME_JUNK);
        EnumHelperClient.addRarity(NORMAL, COLOR_NORMAL, DISPLAY_NAME_NORMAL);
        EnumHelperClient.addRarity(MAGICAL, COLOR_MAGICAL, DISPLAY_NAME_MAGICAL);
        EnumHelperClient.addRarity(RARE, COLOR_RARE, DISPLAY_NAME_RARE);
        EnumHelperClient.addRarity(LEGENDARY, COLOR_LEGENDARY, DISPLAY_NAME_LEGENDARY);
    }

    @Override
    public EnumRarity getCustomRarityType(String customRarity) {
        for (EnumRarity rarity : EnumRarity.class.getEnumConstants()) {
            if (rarity.name().equals(customRarity))
                return rarity;
        }
        return EnumRarity.common;
    }
    
    @Override
    public void initRenderingAndTextures() {
    	//RenderIds.calcinatorRenderId = RenderingRegistry.getNextAvailableRenderId();
    	
    	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
        MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
        
        //FMLClientHandler.instance().getClient().renderEngine.registerTextureFX(new TextureRedWaterFX());
       // FMLClientHandler.instance().getClient().renderEngine.registerTextureFX(new TextureRedWaterFlowFX());
    }
    
    @Override
    public void initTileEntities() {
    	//super.initTileEntities();
    	
    	//ClientRegistry.bindTileEntitySpecialRenderer(TileCalcinator.class, new RenderCalcinator());
    	
    }
    
    public int addArmor(String name)
    {
      return RenderingRegistry.addNewArmourRendererPrefix(name);
    }
}