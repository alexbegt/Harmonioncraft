package net.Harmonion.carts.carts;

import java.util.ArrayList;
import java.util.List;

import net.Harmonion.carts.carts.util.Game;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class EntityCartChest extends TransferCartBase
{
    public EntityCartChest(World var1)
    {
        super(var1);
    }

    public EntityCartChest(World var1, double var2, double var4, double var6)
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

    public List getItemsDropped()
    {
        ArrayList var1 = new ArrayList();
        
        var1.add(this.getCartItem());

        return var1;
    }

    protected double getDrag()
    {
        return 0.991999979019165D;
    }

    public boolean doInteract(EntityPlayer var1)
    {
        if (Game.isHost(this.worldObj))
        {
            var1.displayGUIChest(this);
        }

        return true;
    }

    public boolean isStorageCart()
    {
        return true;
    }

    public boolean canBeRidden()
    {
        return false;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 27;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Chest Cart";
    }
}
