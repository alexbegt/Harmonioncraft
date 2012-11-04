package net.HarmonionCarts.carts;

import net.minecraft.src.EntityMinecart;
import net.minecraft.src.ItemStack;

public interface IMinecart
{
    boolean doesCartMatchFilter(ItemStack var1, EntityMinecart var2);

    float getCartMaxSpeed();

    void setTrainSpeed(float var1);
}
