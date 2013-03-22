package net.Harmonion.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.Harmonion.creativetab.CreativeTabHarmonionI;
import net.Harmonion.item.power.ItemBattery;
import net.Harmonion.item.power.ItemTextured;
import net.Harmonion.server.Harmonion;
import net.Harmonion.util.Config;
import net.Harmonion.util.random.Reference;
import net.Harmonion.util.random.Strings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

/**
 * ModItems
 * 
 * Contains all relevant mod item instances
 * 
 * @author Alexbegt, DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModItems {
	
	/* Mod item instances */
	public static Item HarmonionSword;
	public static Item Refinedsoundstone;
	public static Item Harmonionpearl;
	public static Item Harmonionpick;
	public static Item Harmonionaxe;
	public static Item Harmonionshovel;
	public static Item Harmonionhoe;
	public static Item Harmonionhelmet;
	public static Item Harmonionchestplate;
	public static Item Harmonionlegs;
	public static Item Harmonionboots;
	public static Item HarmonionChip;
	public static Item HarmonionPowder;
	public static Item HarmonionDoor;
	
	public static Item itemBatteryEmpty;
    public static Item itemBatteryPowered;
	
	static EnumToolMaterial harmoniontool = EnumHelper.addToolMaterial("Harmonion_Tool", 3, 3000, 40.0F, 3, 9);
	static EnumArmorMaterial harmonionarmor = EnumHelper.addArmorMaterial("Harmonion_Armor", 30, new int[]{3, 8, 6, 3}, 9);
	
	public static final CreativeTabs tabHarmonionI = new CreativeTabHarmonionI(CreativeTabs.getNextID(), "HarmonionI");
	
	public static void init() {
		
        /* Initialize each mod item individually */
		HarmonionSword = new ItemHarmonionSword(Config.getItemID("items.tools.sword.id"), harmoniontool);
		Refinedsoundstone = new ItemHarmonionIngot(Config.getItemID("items.base.ingot.id"));
		Harmonionpearl = new ItemHarmonionPearl(Config.getItemID("items.base.pearl.id"));
		Harmonionpick = new ItemHarmonionPick(Config.getItemID("items.tools.pickaxe.id"), harmoniontool);
		Harmonionaxe = new ItemHarmonionAxe(Config.getItemID("items.tools.axe.id"), harmoniontool);
		Harmonionshovel = new ItemHarmonionShovel(Config.getItemID("items.tools.shovel.id"), harmoniontool);
		Harmonionhoe = new ItemHarmonionHoe(Config.getItemID("items.tools.hoe.id"), harmoniontool);
		Harmonionhelmet = new ItemHarmonionArmor(Config.getItemID("items.tools.helmet.id"), 6, harmonionarmor, Harmonion.proxy.addArmor("Harmonion/core/armor/soundstone"), 0).setIconIndex(3).setItemName(Strings.Sound_Stone_Helmet_Name);
		Harmonionchestplate = new ItemHarmonionArmor(Config.getItemID("items.tools.chestplate.id"), 7, harmonionarmor, Harmonion.proxy.addArmor("Harmonion/core/armor/soundstone"), 1).setIconIndex(4).setItemName(Strings.Sound_Stone_Chestplate_Name);
		Harmonionlegs = new ItemHarmonionArmor(Config.getItemID("items.tools.leggings.id"), 8, harmonionarmor, Harmonion.proxy.addArmor("Harmonion/core/armor/soundstone"), 2).setIconIndex(5).setItemName(Strings.Sound_Stone_Leggings_Name);
		Harmonionboots = new ItemHarmonionArmor(Config.getItemID("items.tools.boots.id"), 9, harmonionarmor, Harmonion.proxy.addArmor("Harmonion/core/armor/soundstone"), 3).setIconIndex(6).setItemName(Strings.Sound_Stone_Boots_Name);
		HarmonionDoor = new ItemHarmonionDoor(Config.getItemID("items.base.dooritem.id"));
		
		itemBatteryEmpty = new ItemTextured(Config.getItemID("items.power.batteryEmpty.id"), 8, Reference.SPRITE_SHEET_LOCATION + Reference.POWER_ITEM_SPRITE_SHEET);
        itemBatteryEmpty.setItemName("Harmonionbattery");
        itemBatteryPowered = new ItemBattery(Config.getItemID("items.power.batteryFull.id"));
        itemBatteryPowered.setItemName("Harmonionbattery");
		
		/* Item Recipes*/
		initItemRecipes();
		
		/* Item Smelting */
		initItemSmelting();
		
	}
	
	private static void initItemRecipes() {
		
		/* Harmonic Sword Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.HarmonionSword), 
				new Object[]{" h "," h "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Pick Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionpick), 
				new Object[]{"hhh"," s "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Axe Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionaxe), 
				new Object[]{"hh ","hs "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Hoe Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionhoe), 
				new Object[]{"hh "," s "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Shovel Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionshovel), 
				new Object[]{" h "," s "," s ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone,
			Character.valueOf('s'), Item.stick
		});
		
		/*Soundstone Armor Recipe*/
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionhelmet), 
				new Object[]{"hhh","h h","   ", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionchestplate), 
				new Object[]{"h h","hhh","hhh", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionlegs), 
				new Object[]{"hhh","h h","h h", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		GameRegistry.addRecipe(new ItemStack(ModItems.Harmonionboots), 
				new Object[]{"   ","h h","h h", 
			Character.valueOf('h'), ModItems.Refinedsoundstone
		});
		
	}
	
	private static void initItemSmelting() {
		
		FurnaceRecipes furnaceRecipes = FurnaceRecipes.smelting();
		
		/* Harmonion Pearl Smelting. */
		furnaceRecipes.addSmelting(ModItems.Harmonionpearl.itemID, new ItemStack(ModItems.Refinedsoundstone, 10), 10);
	}
}
