package net.Harmonion.core.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.Harmonion.core.block.ModBlocks;
import net.Harmonion.core.item.ModItems;
import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class HarmonionRecipe {
	
	public static void init()
    {
		addSmelting(new ItemStack(ModBlocks.HarmonionOre, 1, 4), ModItems.Harmonionpearl);
		addStorage(new ItemStack(ModBlocks.HarmonionOre, 1, 4), ModItems.Harmonionpearl);
		addInverseStorage(new ItemStack(ModItems.Harmonionpearl, 9), new ItemStack(ModBlocks.HarmonionOre, 1, 4));
    }
	
	private static void addSmelting(Object var0, Object var1)
    {
        ItemStack var2 = handleStackType(var0, new int[0]);
        ItemStack var3 = handleStackType(var1, new int[0]);
        FurnaceRecipes.smelting().addSmelting(var2.itemID, var2.getItemDamage(), var3, 0.0F);
    }
	
	private static ItemStack handleStackType(Object var0, int ... var1)
    {
        return var0 instanceof Block ? (var1.length > 0 ? new ItemStack((Block)var0, 1, var1[0]) : new ItemStack((Block)var0)) : (var0 instanceof Item ? (var1.length > 0 ? new ItemStack((Item)var0, 1, var1[0]) : new ItemStack((Item)var0)) : (var0 instanceof ItemStack ? (ItemStack)var0 : null));
    }
	
	private static void addStorage(ItemStack var0, Item var1)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(var0, new Object[] {new String[]{"###", "###", "###"}, '#', var1}));
    }

    private static void addInverseStorage(ItemStack var0, ItemStack var1)
    {
        GameRegistry.addRecipe(new ShapelessOreRecipe(var0, new Object[] {var1}));
    }
	
}
