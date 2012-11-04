package net.HarmonionCarts.carts;

import net.minecraft.src.World;

public class EntityCartBasic extends CartBase
{
    public EntityCartBasic(World var1)
    {
        super(var1);
    }

    public EntityCartBasic(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4 + (double)this.yOffset, var6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = var2;
        this.prevPosY = var4;
        this.prevPosZ = var6;
    }

    protected double getDrag()
    {
        return (this.riddenByEntity != null ? 0.996999979019165D : 0.9599999785423279D);
    }

    public boolean isStorageCart()
    {
        return false;
    }

    public boolean canBeRidden()
    {
        return true;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 0;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "";
    }
}
