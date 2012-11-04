package net.HarmonionCarts.carts;

import net.minecraft.src.BlockRail;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntityFurnace;
import net.minecraftforge.common.MinecartRegistry;

public enum EnumItemType
{
    FUEL,
    RAIL,
    MINECART,
    BALLAST,
    FOOD;

    public static boolean isItemType(ItemStack var0, EnumItemType var1)
    {
        return var1.isItemType(var0);
    }

    public boolean isItemType(ItemStack var1)
    {
        if (var1 == null)
        {
            return false;
        }
        else
        {
            switch (EnumItemType$1.$SwitchMap$railcraft$common$api$core$items$EnumItemType[this.ordinal()])
            {
                case 1:
                    return TileEntityFurnace.isItemFuel(var1);

                case 2:
                    return MinecartRegistry.getCartClassForItem(var1) != null || var1.getItem() instanceof IMinecartItem;

                case 3:
                    return var1.getItem() instanceof ItemFood || var1.itemID == Item.wheat.shiftedIndex;

                default:
                    return false;
            }
        }
    }
}
