package net.Harmonion.client;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.block.tank.TileTankHarmonionGauge;
import net.Harmonion.block.tank.TileTankHarmonionValve;
import net.Harmonion.block.tank.TileTankHarmonionWall;
import net.Harmonion.client.model.ModelHarmonionWolf;
import net.Harmonion.client.renderer.entity.RenderHarmonionWolf;
import net.Harmonion.client.renderer.tileentity.RenderHarmonion;
import net.Harmonion.client.renderer.tileentity.RenderHarmonionTank;
import net.Harmonion.client.renderer.tileentity.tank.RenderBlockMachineBeta;
import net.Harmonion.client.textureFX.TexturePhazonFX;
import net.Harmonion.entity.passive.EntityHarmonionWolf;
import net.Harmonion.power.IHandlePackets;
import net.Harmonion.power.Packet211TileDesc;
import net.Harmonion.power.RenderBatteryBox;
import net.Harmonion.power.RenderChargingBench;
import net.Harmonion.power.RenderCustomBlock;
import net.Harmonion.power.RenderLib;
import net.Harmonion.power.RenderRedwire;
import net.Harmonion.power.RenderSolarPanel;
import net.Harmonion.util.random.CommonProxy;
import net.Harmonion.util.random.Reference;
import net.Harmonion.util.sounds.ThreadDownloadResourcesHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetClientHandler;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
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
import cpw.mods.fml.client.TextureFXManager;
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
public class ClientProxy extends CommonProxy implements ISimpleBlockRenderingHandler {
	
	@Override
	public void registerTickHander() {
		super.registerTickHander();
	}
	
	public int getRenderIdTank()
    {
        return RenderingRegistry.getNextAvailableRenderId();
    }
	
	public int getItemRarityColor(ItemStack var1)
    {
        return var1.getItem().getRarity(var1).rarityColor;
    }
	
	public String getItemDisplayName(ItemStack var1)
    {
        return var1.getItem().getItemDisplayName(var1);
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
    
    public void initClient()
    {
    	
    	RenderBlockMachineBeta var2 = new RenderBlockMachineBeta();

        if (var2.getBlock() != null)
        {
            RenderingRegistry.registerBlockHandler(var2);
            MinecraftForgeClient.registerItemRenderer(var2.getBlock().blockID, var2.getItemRenderer());
        }
    	
        TextureFXManager.instance().addAnimation(new TexturePhazonFX());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileTankHarmonionGauge.class, new RenderHarmonionTank());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTankHarmonionWall.class, new RenderHarmonionTank());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTankHarmonionValve.class, new RenderHarmonionTank());
    }
    
    @Override
    public void initEntitys() {
    	super.initEntitys();
    }
    
    @Override
    public void initEntitysClient() {
    	RenderLib.setDefaultRenderer(ModBlocks.blockPower, 8, RenderRedwire.class);
    	RenderLib.setRenderer(ModBlocks.blockMachine, 0, RenderBatteryBox.class);
    	RenderLib.setRenderer(ModBlocks.blockMachinePanel, 0, RenderSolarPanel.class);
    	RenderLib.setRenderer(ModBlocks.blockAppliance, 0, RenderChargingBench.class);
    	ModBlocks.customBlockModel = RenderingRegistry.getNextAvailableRenderId();
    	
    	RenderingRegistry.registerBlockHandler(ModBlocks.customBlockModel, new ClientProxy());
    	RenderingRegistry.registerEntityRenderingHandler(
    			EntityHarmonionWolf.class, new RenderHarmonionWolf(new ModelHarmonionWolf(),
    					new ModelHarmonionWolf(), 0.5F));
    }
    
    public void processPacket211(Packet211TileDesc var1, NetHandler var2)
    {
        if (var2 instanceof NetClientHandler)
        {
            NetClientHandler var3 = (NetClientHandler)var2;
            World var4 = var3.getPlayer().worldObj;

            if (var4.blockExists(var1.xCoord, var1.yCoord, var1.zCoord))
            {
                TileEntity var5 = var4.getBlockTileEntity(var1.xCoord, var1.yCoord, var1.zCoord);

                if (var5 instanceof IHandlePackets)
                {
                    ((IHandlePackets)var5).handlePacket(var1);
                    return;
                }
            }
        }
        else
        {
            super.processPacket211(var1, var2);
        }
    }
    
    public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4)
    {
        if (var3 == ModBlocks.customBlockModel)
        {
            RenderCustomBlock var5 = RenderLib.getInvRenderer(var1.blockID, var2);

            if (var5 == null)
            {
                System.out.printf("Bad Render at %d:%d\n", new Object[] {Integer.valueOf(var1.blockID), Integer.valueOf(var2)});
            }
            else
            {
                var5.renderInvBlock(var4, var2);
            }
        }
    }

    public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
    {
        if (var7.overrideBlockTexture >= 0)
        {
            return true;
        }
        else if (var6 != ModBlocks.customBlockModel)
        {
            return false;
        }
        else
        {
            int var8 = var1.getBlockMetadata(var2, var3, var4);
            RenderCustomBlock var9 = RenderLib.getRenderer(var5.blockID, var8);

            if (var9 == null)
            {
                System.out.printf("Bad Render at %d:%d\n", new Object[] {Integer.valueOf(var5.blockID), Integer.valueOf(var8)});
                return true;
            }
            else
            {
                var9.renderWorldBlock(var7, var1, var2, var3, var4, var8);
                return true;
            }
        }
    }

    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    public int getRenderId()
    {
        return ModBlocks.customBlockModel;
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
    	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.POWER_BLOCK_SPRITE_SHEET);
    	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.POWER_ITEM_SPRITE_SHEET);
    	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.TANK_BLOCK_SPRITE_SHEET);
    	
        
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