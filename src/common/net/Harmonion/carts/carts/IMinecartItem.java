package net.Harmonion.carts.carts;

import net.minecraft.src.EntityMinecart;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public interface IMinecartItem
{
    boolean canBePlacedByNonPlayer(ItemStack var1);

    EntityMinecart placeCart(String var1, ItemStack var2, World var3, int var4, int var5, int var6);
}
