package net.Harmonion.util.inventory;

import net.Harmonion.api.core.items.IItemType;
import net.Harmonion.util.misc.MiscTools;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecartRegistry;

public enum EnumItemType implements IItemType
{
    FUEL,
    TRACK,
    MINECART,
    BALLAST,
    FEED;

    public static void initialize()
    {
        EnumItemType[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2)
        {
            EnumItemType var3 = var0[var2];
            IItemType.types.put(var3.name(), var3);
        }
    }

    public boolean isItemType(ItemStack var1)
    {
        if (var1 == null)
        {
            return false;
        }
        else
        {
            switch ($SwitchMap$net$Harmonion$util$inventory$EnumItemType[this.ordinal()])
            {
                case 1:
                    return MiscTools.getItemBurnTime(var1) > 0;
                case 5:
                    return var1.getItem() instanceof ItemFood || var1.itemID == Item.wheat.itemID || var1.getItem() instanceof ItemSeeds;

                default:
                    return false;
            }
        }
    }
    
    static final int[] $SwitchMap$net$Harmonion$util$inventory$EnumItemType = new int[EnumItemType.values().length];

    static
    {
        try
        {
        	$SwitchMap$net$Harmonion$util$inventory$EnumItemType[EnumItemType.FUEL.ordinal()] = 1;
        }
        catch (NoSuchFieldError var5)
        {
            ;
        }

        try
        {
        	$SwitchMap$net$Harmonion$util$inventory$EnumItemType[EnumItemType.TRACK.ordinal()] = 2;
        }
        catch (NoSuchFieldError var4)
        {
            ;
        }

        try
        {
        	$SwitchMap$net$Harmonion$util$inventory$EnumItemType[EnumItemType.MINECART.ordinal()] = 3;
        }
        catch (NoSuchFieldError var3)
        {
            ;
        }

        try
        {
        	$SwitchMap$net$Harmonion$util$inventory$EnumItemType[EnumItemType.BALLAST.ordinal()] = 4;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        try
        {
        	$SwitchMap$net$Harmonion$util$inventory$EnumItemType[EnumItemType.FEED.ordinal()] = 5;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
