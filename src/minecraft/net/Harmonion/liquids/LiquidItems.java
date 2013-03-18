package net.Harmonion.liquids;

import cpw.mods.fml.common.registry.GameRegistry;
import net.Harmonion.api.core.items.ItemRegistry;
import net.Harmonion.api.fuel.FuelManager;
import net.Harmonion.block.ModBlocks;
import net.Harmonion.util.LocalizationHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public abstract class LiquidItems
{
    private static LiquidStack liquidPhazon;
    private static Item itemPhazon;
    private static Item itemPhazonBucket;
    private static Item itemPhazonBottle;
    private static ItemLiquidContainer itemPhazonCell;
    private static ItemLiquidContainer itemPhazonCan;
    private static ItemLiquidContainer itemPhazonWax;
    private static ItemLiquidContainer itemPhazonRefactory;

    public static void initialize()
    {
        initPhazon();
    }

    public static ItemStack getPhazon()
    {
        initPhazon();
        return new ItemStack(itemPhazon);
    }

    private static void initPhazon()
    {
        if (itemPhazon == null)
        {
            String var0 = "rc.liquid.creosote.liquid";
            int var1 = LocalizationHandler.getItemID(var0);
            Item var2 = (new ItemLiquid(var1)).setRarity(1).setIconIndex(0).setItemName(var0);
            GameRegistry.registerItem(var2, var0);
            //RailcraftLanguage.getInstance().registerItemName(var2, var0);
            liquidPhazon = LiquidDictionary.getOrCreateLiquid("Creosote Oil", new LiquidStack(var2.itemID, 0));
            ItemRegistry.registerItem(var0, new ItemStack(var2));
            LiquidStack var3 = liquidPhazon.copy();
            var3.amount = 1000;
            FuelManager.addBoilerFuel(var3, 3200);
            itemPhazon = var2;
        }
    }

    public static LiquidStack getPhazonLiquid(int var0)
    {
        initPhazon();
        LiquidStack var1 = liquidPhazon.copy();
        var1.amount = var0;
        return var1;
    }

    public static ItemStack getPhazonBucket()
    {
        Item var0 = itemPhazonBucket;

        if (var0 == null)
        {
            String var1 = "rc.liquid.creosote.bucket";
            int var2 = LocalizationHandler.getItemID(var1);
            var0 = (new ItemLiquidContainer(var2)).setIconIndex(1).setItemName(var1).setMaxStackSize(1).setContainerItem(Item.bucketEmpty).setCreativeTab(ModBlocks.tabHarmonioncraftB);
            GameRegistry.registerItem(var0, var1);
            //RailcraftLanguage.getInstance().registerItemName(var0, var1);
            LiquidManager.getInstance().registerBucket(getPhazonLiquid(1000), new ItemStack(var0));
            ItemRegistry.registerItem(var1, new ItemStack(var0));
            itemPhazonBucket = var0;
        }

        return new ItemStack(var0);
    }

    public static ItemStack getPhazonBottle()
    {
        return getPhazonBottle(1);
    }

    public static ItemStack getPhazonBottle(int var0)
    {
        Item var1 = itemPhazonBottle;

        if (var1 == null)
        {
            String var2 = "rc.liquid.creosote.bottle";
            int var3 = LocalizationHandler.getItemID(var2);

            if (var3 <= 0)
            {
                return null;
            }

            var1 = (new ItemLiquidContainer(var3)).setIconIndex(2).setItemName(var2).setCreativeTab(ModBlocks.tabHarmonioncraftB);
            GameRegistry.registerItem(var1, var2);
            //RailcraftLanguage.getInstance().registerItemName(var1, var2);
            LiquidManager.getInstance().registerBottle(getPhazonLiquid(1000), new ItemStack(var1));
            ItemRegistry.registerItem(var2, new ItemStack(var1));
            itemPhazonBottle = var1;
        }

        return new ItemStack(var1, var0);
    }

}
