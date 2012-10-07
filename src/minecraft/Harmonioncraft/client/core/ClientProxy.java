package Harmonioncraft.client.core;

import net.minecraft.src.EnumRarity;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import Harmonioncraft.client.core.handlers.SoundHandler;
import Harmonioncraft.client.render.RenderHarmonionCable;
import Harmonioncraft.common.core.CommonProxy;
import Harmonioncraft.common.core.handlers.TickerHandler;
import Harmonioncraft.common.entity.TileEntityHarmonionWire;
import Harmonioncraft.common.lib.Reference;
import Harmonioncraft.common.lib.RenderIds;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.registry.TickRegistry;
import static Harmonioncraft.common.lib.CustomItemRarity.*;

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
		TickRegistry.registerTickHandler(new TickerHandler(), Side.CLIENT);
	}
	
    @Override
    public void registerKeyBindingHandler() {
        //KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
    }

    @Override
    public void setKeyBinding(String name, int value) {
        //KeyBindings.addKeyBinding(name, value);
        //KeyBindings.addIsRepeating(false);
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
    	super.initTileEntities();
    	
    	ClientRegistry.registerTileEntity(TileEntityHarmonionWire.class, "TileEntityHarmonionWireClient", new RenderHarmonionCable());
    	
    	//ClientRegistry.bindTileEntitySpecialRenderer(TileCalcinator.class, new RenderCalcinator());
    	
    }
    
    public int addArmor(String name)
    {
      return RenderingRegistry.addNewArmourRendererPrefix(name);
    }
}