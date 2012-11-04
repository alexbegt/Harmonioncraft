package net.HarmonionCarts.item;

import java.util.Iterator;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.HarmonionCarts.carts.EntityCartBasic;
import net.HarmonionCarts.carts.EntityCartChest;
import net.HarmonionCarts.carts.EntityCartFurnace;
import net.HarmonionCarts.carts.EnumCart;
import net.HarmonionCarts.carts.ItemCartVanilla;
import net.HarmonionCarts.carts.LinkageHandler;
import net.HarmonionCarts.carts.MinecartHooks;
import net.HarmonionCarts.carts.util.GeneralTools;
import net.HarmonionCarts.core.creativetabs.CreativeTabHarmonionCartI;
import net.HarmonionCarts.lang.LocalizationHelper;
import net.HarmonionCarts.mods.HarmonioncraftCarts;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.MinecartRegistry;
import net.minecraftforge.common.MinecraftForge;

public class ModItems {
	
public static final CreativeTabs tabHarmonioncraftCartI = new CreativeTabHarmonionCartI(CreativeTabs.getNextID(), "HarmonionCartI");
	
	public static void init() {
		MinecraftForge.EVENT_BUS.register(MinecartHooks.getInstance());
        //MinecraftForge.EVENT_BUS.register(LinkageHandler.getInstance());
        
        int var11 = Item.shovelSteel.shiftedIndex;
        int var12 = Item.minecartEmpty.shiftedIndex;
        int var13 = Item.minecartCrate.shiftedIndex;
        int var14 = Item.minecartPowered.shiftedIndex;
        Item.itemsList[var12] = null;
        Item.itemsList[var13] = null;
        Item.itemsList[var14] = null;
        ItemCartVanilla var5 = new ItemCartVanilla(var12 - var11, EnumCart.BASIC);
        ItemCartVanilla var6 = new ItemCartVanilla(var13 - var11, EnumCart.CHEST);
        ItemCartVanilla var7 = new ItemCartVanilla(var14 - var11, EnumCart.FURNACE);
        MinecartRegistry.removeMinecart(EntityMinecart.class, 0);
        MinecartRegistry.removeMinecart(EntityMinecart.class, 1);
        MinecartRegistry.removeMinecart(EntityMinecart.class, 2);
        MinecartRegistry.registerMinecart(EntityCartBasic.class, new ItemStack(var5));
        MinecartRegistry.registerMinecart(EntityCartChest.class, new ItemStack(var6));
        MinecartRegistry.registerMinecart(EntityCartFurnace.class, new ItemStack(var7));
        GeneralTools.registerMinecart(HarmonioncraftCarts.instance, EntityCartBasic.class, "cart.basic", EnumCart.BASIC.getId());
        GeneralTools.registerMinecart(HarmonioncraftCarts.instance, EntityCartChest.class, "cart.chest", EnumCart.CHEST.getId());
        GeneralTools.registerMinecart(HarmonioncraftCarts.instance, EntityCartFurnace.class, "cart.furnace", EnumCart.FURNACE.getId());
        ItemStack var9;
        ItemStack var10;
        ItemStack var15;
        var15 = new ItemStack(var5, 1);
        var9 = new ItemStack(var6, 1);
        var10 = new ItemStack(var7, 1);
        LocalizationHelper.getInstance().registerItemName(var15, EnumCart.BASIC.getTag());
        LocalizationHelper.getInstance().registerItemName(var9, EnumCart.CHEST.getTag());
        LocalizationHelper.getInstance().registerItemName(var10, EnumCart.FURNACE.getTag());
	}
	
}
