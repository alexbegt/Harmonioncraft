package net.HarmonionCarts.carts.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import net.minecraft.src.ItemStack;

public final class ItemRegistry
{
    private static final Map registry = new TreeMap();

    public static ItemStack getItem(String var0, int var1)
    {
        ItemStack var2 = (ItemStack)registry.get(var0);

        if (var2 != null)
        {
            var2 = var2.copy();
            var2.stackSize = var1;
        }

        return var2;
    }

    public static void registerItem(String var0, ItemStack var1)
    {
        var0 = var0.replace("rc.", "");
        registry.put(var0, var1);
    }

    public static void printItemTags()
    {
        System.out.println();
        System.out.println("Printing all registered Railcraft items:");
        Iterator var0 = registry.keySet().iterator();

        while (var0.hasNext())
        {
            String var1 = (String)var0.next();
            System.out.println(var1);
        }

        System.out.println();
    }

    public static Map getItems()
    {
        return registry;
    }
}
