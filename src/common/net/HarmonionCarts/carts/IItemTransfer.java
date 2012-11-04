package net.HarmonionCarts.carts;

import net.minecraft.src.ItemStack;

public interface IItemTransfer
{
    ItemStack offerItem(Object var1, ItemStack var2);

    ItemStack requestItem(Object var1);

    ItemStack requestItem(Object var1, ItemStack var2);

    ItemStack requestItem(Object var1, EnumItemType var2);
}
