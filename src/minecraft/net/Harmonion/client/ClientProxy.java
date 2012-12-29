package net.Harmonion.client;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.client.model.ModelHarmonionWolf;
import net.Harmonion.client.renderer.entity.RenderHarmonionWolf;
import net.Harmonion.client.renderer.tileentity.RenderHarmonion;
import net.Harmonion.entity.passive.EntityHarmonionWolf;
import net.Harmonion.util.CommonProxy;
import net.Harmonion.util.Reference;
import net.Harmonion.util.ThreadDownloadResourcesHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
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
public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerTickHander() {
		super.registerTickHander();
	}
    
    @Override
    public int getBlockTexture(Block var1, IBlockAccess var2, int var3, int var4, int var5, int var6)
    {
        return var1.getBlockTexture(var2, var3, var4, var5, var6);
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
    public void initEntitys() {
    	super.initEntitys();
    }
    
    @Override
    public void initEntitysClient() {
    	RenderingRegistry.registerEntityRenderingHandler(
    			EntityHarmonionWolf.class, new RenderHarmonionWolf(new ModelHarmonionWolf(),
    					new ModelHarmonionWolf(), 0.5F));
    }
    
    public String getCurrentLanguage()
    {
        return StringTranslate.getInstance().getCurrentLanguage();
    }
    
    public int getNextAvailableRenderId()
    {
        return RenderingRegistry.getNextAvailableRenderId();
    }
    
    @Override
    public void initRenderingAndTextures() {
    	
    	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
        MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
    	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.POWER_SPRITE_SHEET);
        
        RenderingRegistry.registerBlockHandler(new RenderHarmonion());
        
    }
    
    @Override
    public void initTileEntities() {
    }
    
    public int addArmor(String name)
    {
      return RenderingRegistry.addNewArmourRendererPrefix(name);
    }
}