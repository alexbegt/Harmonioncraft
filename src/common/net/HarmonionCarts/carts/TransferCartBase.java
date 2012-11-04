package net.HarmonionCarts.carts;

import net.minecraft.src.EntityMinecart;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public abstract class TransferCartBase extends CartBase implements IItemTransfer
{
    protected boolean passThrough = false;

    public TransferCartBase(World var1)
    {
        super(var1);
    }

    public ItemStack offerItem(Object var1, ItemStack var2)
    {
        if (!this.passThrough && this.getSizeInventory() > 0)
        {
            var2 = this.moveItemStack(var2, this);

            if (var2 == null)
            {
                return null;
            }
        }

        ILinkageManager var3 = CartTools.getLinkageManager(this.worldObj);
        EntityMinecart var4 = var3.getLinkedCartA(this);

        if (var4 != var1 && var4 instanceof IItemTransfer)
        {
            var2 = ((IItemTransfer)var4).offerItem(this, var2);
        }

        if (var2 == null)
        {
            return null;
        }
        else
        {
            var4 = var3.getLinkedCartB(this);

            if (var4 != var1 && var4 instanceof IItemTransfer)
            {
                var2 = ((IItemTransfer)var4).offerItem(this, var2);
            }

            return var2;
        }
    }

    public ItemStack requestItem(Object var1)
    {
        ItemStack var2 = null;

        if (!this.passThrough && this.getSizeInventory() > 0)
        {
            var2 = this.removeOneItem(this);

            if (var2 != null)
            {
                return var2;
            }
        }

        ILinkageManager var3 = CartTools.getLinkageManager(this.worldObj);
        EntityMinecart var4 = var3.getLinkedCartA(this);

        if (var4 != var1 && var4 instanceof IItemTransfer)
        {
            var2 = ((IItemTransfer)var4).requestItem(this);
        }

        if (var2 != null)
        {
            return var2;
        }
        else
        {
            var4 = var3.getLinkedCartB(this);

            if (var4 != var1 && var4 instanceof IItemTransfer)
            {
                var2 = ((IItemTransfer)var4).requestItem(this);
            }

            return var2;
        }
    }

    public ItemStack requestItem(Object var1, ItemStack var2)
    {
        ItemStack var3 = null;

        if (!this.passThrough && this.getSizeInventory() > 0)
        {
            var3 = this.removeOneItem(this, var2);

            if (var3 != null)
            {
                return var3;
            }
        }

        ILinkageManager var4 = CartTools.getLinkageManager(this.worldObj);
        EntityMinecart var5 = var4.getLinkedCartA(this);

        if (var5 != var1 && var5 instanceof IItemTransfer)
        {
            var3 = ((IItemTransfer)var5).requestItem(this, var2);
        }

        if (var3 != null)
        {
            return var3;
        }
        else
        {
            var5 = var4.getLinkedCartB(this);

            if (var5 != var1 && var5 instanceof IItemTransfer)
            {
                var3 = ((IItemTransfer)var5).requestItem(this, var2);
            }

            return var3;
        }
    }

    public ItemStack requestItem(Object var1, EnumItemType var2)
    {
        ItemStack var3 = null;

        if (!this.passThrough && this.getSizeInventory() > 0)
        {
            var3 = this.removeOneItem(this, var2);

            if (var3 != null)
            {
                return var3;
            }
        }

        ILinkageManager var4 = CartTools.getLinkageManager(this.worldObj);
        EntityMinecart var5 = var4.getLinkedCartA(this);

        if (var5 != var1 && var5 instanceof IItemTransfer)
        {
            var3 = ((IItemTransfer)var5).requestItem(this, var2);
        }

        if (var3 != null)
        {
            return var3;
        }
        else
        {
            var5 = var4.getLinkedCartB(this);

            if (var5 != var1 && var5 instanceof IItemTransfer)
            {
                var3 = ((IItemTransfer)var5).requestItem(this, var2);
            }

            return var3;
        }
    }

    protected final ItemStack removeOneItem(IInventory var1)
    {
        for (int var2 = 0; var2 < var1.getSizeInventory(); ++var2)
        {
            ItemStack var3 = var1.getStackInSlot(var2);

            if (var3 != null)
            {
                return var1.decrStackSize(var2, 1);
            }
        }

        return null;
    }

    protected final ItemStack removeOneItem(IInventory var1, ItemStack var2)
    {
        for (int var3 = 0; var3 < var1.getSizeInventory(); ++var3)
        {
            ItemStack var4 = var1.getStackInSlot(var3);

            if (var4 != null && var2 != null && var4.isItemEqual(var2))
            {
                return var1.decrStackSize(var3, 1);
            }
        }

        return null;
    }

    protected final ItemStack removeOneItem(IInventory var1, EnumItemType var2)
    {
        for (int var3 = 0; var3 < var1.getSizeInventory(); ++var3)
        {
            ItemStack var4 = var1.getStackInSlot(var3);

            if (var4 != null && var2.isItemType(var4))
            {
                return var1.decrStackSize(var3, 1);
            }
        }

        return null;
    }

    protected final ItemStack moveItemStack(ItemStack var1, IInventory var2)
    {
        if (var1 == null)
        {
            return null;
        }
        else
        {
            var1 = var1.copy();

            if (var2 == null)
            {
                return var1;
            }
            else
            {
                boolean var3 = false;

                do
                {
                    var3 = false;
                    ItemStack var4 = null;
                    int var5;

                    for (var5 = 0; var5 < var2.getSizeInventory(); ++var5)
                    {
                        var4 = var2.getStackInSlot(var5);

                        if (var4 != null && var4.isItemEqual(var1))
                        {
                            int var6 = Math.min(var4.getMaxStackSize(), var2.getInventoryStackLimit());
                            int var7 = var6 - var4.stackSize;

                            if (var7 > 0)
                            {
                                int var8 = Math.min(var7, var1.stackSize);
                                var4.stackSize += var8;
                                var1.stackSize -= var8;

                                if (var1.stackSize <= 0)
                                {
                                    return null;
                                }

                                var3 = true;
                            }
                        }
                    }

                    if (!var3)
                    {
                        for (var5 = 0; var5 < var2.getSizeInventory(); ++var5)
                        {
                            var4 = var2.getStackInSlot(var5);

                            if (var4 == null)
                            {
                                if (var1.stackSize <= var2.getInventoryStackLimit())
                                {
                                    var2.setInventorySlotContents(var5, var1);
                                    return null;
                                }

                                var2.setInventorySlotContents(var5, var1.splitStack(var2.getInventoryStackLimit()));
                                var3 = true;
                            }
                        }
                    }
                }
                while (var3);

                return var1;
            }
        }
    }
}
