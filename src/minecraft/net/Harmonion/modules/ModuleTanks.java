package net.Harmonion.modules;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.block.tank.EnumMachineBeta;
import net.Harmonion.liquids.LiquidItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ModuleTanks extends RailcraftModule {
	
	public void preInit()
    {
		//LiquidItems.initialize();
    }
	
	public void initFirst()
    {
		EnumMachineBeta var14 = EnumMachineBeta.TANK_IRON_WALL;
        ModBlocks.registerBlockMachineBeta();
        Block var15 = ModBlocks.getBlockMachineBeta();

        if (var15 != null)
        {
        	ItemStack var4 = var14.getItem();
         }

        EnumMachineBeta var16 = EnumMachineBeta.TANK_IRON_GAUGE;
        
        ModBlocks.registerBlockMachineBeta();
        Block var17 = ModBlocks.getBlockMachineBeta();
        
        if (var17 != null)
        {
        	ItemStack var5 = var16.getItem();
        	//CraftingPlugin.addShapedRecipe(var16.getItem(4), new Object[] {"GPG", "PGP", "GPG", 'P', RailcraftPartItems.getPlateIron(), 'G', new ItemStack(Block.thinGlass)});
        }

        EnumMachineBeta var18 = EnumMachineBeta.TANK_IRON_VALVE;
        
        ModBlocks.registerBlockMachineBeta();
        Block var19 = ModBlocks.getBlockMachineBeta();
        if (var19 != null)
        {
        	ItemStack var6 = var18.getItem();
        	//CraftingPlugin.addShapedRecipe(var18.getItem(4), new Object[] {"GPG", "PLP", "GPG", 'P', RailcraftPartItems.getPlateIron(), 'L', new ItemStack(Block.lever), 'G', new ItemStack(Block.fenceIron)});
        }
    }
	
	public void initSecond()
    {
		
    }
	
	public void postInit()
    {
		
    }
	
}
