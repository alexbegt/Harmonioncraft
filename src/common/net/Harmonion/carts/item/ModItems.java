package net.Harmonion.carts.item;

import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.common.MinecartRegistry;
import net.minecraftforge.common.MinecraftForge;

import net.Harmonion.carts.carts.EntityCartBasic;
import net.Harmonion.carts.carts.EntityCartChest;
import net.Harmonion.carts.carts.EntityCartFurnace;
import net.Harmonion.carts.carts.EntityCartPumpkin;
import net.Harmonion.carts.carts.EntityCartTNT;
import net.Harmonion.carts.carts.EnumCart;
import net.Harmonion.carts.carts.ItemCart;
import net.Harmonion.carts.carts.ItemCartVanilla;
import net.Harmonion.carts.carts.LinkageHandler;
import net.Harmonion.carts.carts.MinecartHooks;
import net.Harmonion.carts.carts.util.Game;
import net.Harmonion.carts.carts.util.GeneralTools;
import net.Harmonion.carts.carts.util.ItemRegistry;
import net.Harmonion.carts.lang.LocalizationHelper;
import net.Harmonion.carts.lib.ConfigurationSettings;
import net.Harmonion.carts.main.creativetabs.CreativeTabHarmonionCartI;
import net.Harmonion.HarmonionCart;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ModItems {
	
	public static final CreativeTabs tabHarmonioncraftCartI = new CreativeTabHarmonionCartI(CreativeTabs.getNextID(), "HarmonionCartI");
	
	public static void init() {
		MinecraftForge.EVENT_BUS.register(MinecartHooks.getInstance());
		
		InitCart0();
	}
	
	public static void InitCart0() {
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
        GeneralTools.registerMinecart(HarmonionCart.instance, EntityCartBasic.class, "cart.basic", EnumCart.BASIC.getId());
        GeneralTools.registerMinecart(HarmonionCart.instance, EntityCartChest.class, "cart.chest", EnumCart.CHEST.getId());
        GeneralTools.registerMinecart(HarmonionCart.instance, EntityCartFurnace.class, "cart.furnace", EnumCart.FURNACE.getId());
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
