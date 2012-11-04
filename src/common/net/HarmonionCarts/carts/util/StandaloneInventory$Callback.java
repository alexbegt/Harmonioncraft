package net.HarmonionCarts.carts.util;

import net.minecraft.src.EntityPlayer;

abstract class StandaloneInventory$Callback
{
    final StandaloneInventory this$0;

    private StandaloneInventory$Callback(StandaloneInventory var1)
    {
        this.this$0 = var1;
    }

    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return true;
    }

    public void openChest() {}

    public void closeChest() {}

    public abstract void onInventoryChanged();

    public abstract String getInvName();

    StandaloneInventory$Callback(StandaloneInventory var1, StandaloneInventory$1 var2)
    {
        this(var1);
    }
}
