package net.Harmonion.util.inventory;

import com.google.common.collect.Iterators;
import java.util.Iterator;

import net.Harmonion.tanks.HarmonionTileEntity;
import net.Harmonion.util.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StandaloneInventory implements IInventory, Iterable
{
    private Callback callback;
    private final String name;
    private ItemStack[] contents;

    public StandaloneInventory(int var1, String var2, IInventory var3)
    {
        this.name = var2;
        this.contents = new ItemStack[var1];
        this.callback = var3 == null ? null : new InventoryCallback(this, var3);
    }

    public StandaloneInventory(int var1, String var2, HarmonionTileEntity var3)
    {
        this.name = var2;
        this.contents = new ItemStack[var1];
        this.callback = var3 == null ? null : new TileCallback(this, var3);
    }

    public StandaloneInventory(int var1, IInventory var2)
    {
        this(var1, (String)null, var2);
    }

    public StandaloneInventory(int var1, HarmonionTileEntity var2)
    {
        this(var1, (String)null, var2);
    }

    public StandaloneInventory(int var1, String var2)
    {
        this(var1, var2, (HarmonionTileEntity)null);
    }

    public StandaloneInventory(int var1)
    {
        this(var1, (String)null, (HarmonionTileEntity)null);
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.contents.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int var1)
    {
        return this.contents[var1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int var1, int var2)
    {
        if (this.contents[var1] != null)
        {
            ItemStack var3;

            if (this.contents[var1].stackSize <= var2)
            {
                var3 = this.contents[var1];
                this.contents[var1] = null;
                this.onInventoryChanged();
                return var3;
            }
            else
            {
                var3 = this.contents[var1].splitStack(var2);

                if (this.contents[var1].stackSize <= 0)
                {
                    this.contents[var1] = null;
                }

                this.onInventoryChanged();
                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int var1, ItemStack var2)
    {
        this.contents[var1] = var2;

        if (var2 != null && var2.stackSize > this.getInventoryStackLimit())
        {
            var2.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.name != null ? Config.translate(this.name) : (this.callback != null ? this.callback.getInvName() : this.invTypeName());
    }

    protected String invTypeName()
    {
        return "Standalone";
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged()
    {
        if (this.callback != null)
        {
            this.callback.onInventoryChanged();
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return this.callback != null ? this.callback.isUseableByPlayer(var1) : true;
    }

    public void openChest()
    {
        if (this.callback != null)
        {
            this.callback.openChest();
        }
    }

    public void closeChest()
    {
        if (this.callback != null)
        {
            this.callback.closeChest();
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int var1)
    {
        return null;
    }

    public ItemStack[] getContents()
    {
        return this.contents;
    }

    public void writeToNBT(String var1, NBTTagCompound var2)
    {
        InvTools.writeInvToNBT(this, var1, var2);
    }

    public void readFromNBT(String var1, NBTTagCompound var2)
    {
        InvTools.readInvFromNBT(this, var1, var2);
    }

    public Iterator iterator()
    {
        return Iterators.forArray(this.contents);
    }
    
    abstract class Callback
    {
        final StandaloneInventory this$0;

        private Callback(StandaloneInventory var1)
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

        Callback(StandaloneInventory var1, StandaloneInventory var2)
        {
            this(var1);
        }
    }
    
    class InventoryCallback extends Callback
    {
        private IInventory inv;

        final StandaloneInventory this$0;

        public InventoryCallback(StandaloneInventory var1, IInventory var2)
        {
            super(var1, (StandaloneInventory)null);
            this.this$0 = var1;
            this.inv = var2;
        }

        public boolean isUseableByPlayer(EntityPlayer var1)
        {
            return this.inv.isUseableByPlayer(var1);
        }

        public void openChest()
        {
            this.inv.openChest();
        }

        public void closeChest()
        {
            this.inv.closeChest();
        }

        public void onInventoryChanged()
        {
            this.inv.onInventoryChanged();
        }

        public String getInvName()
        {
            return this.inv.getInvName();
        }
    }
    
    class TileCallback extends Callback
    {
        private HarmonionTileEntity inv;

        final StandaloneInventory this$0;

        public TileCallback(StandaloneInventory var1, HarmonionTileEntity var2)
        {
            super(var1, (StandaloneInventory)null);
            this.this$0 = var1;
            this.inv = var2;
        }

        public void onInventoryChanged()
        {
            this.inv.onInventoryChanged();
        }

        public String getInvName()
        {
            return this.inv.getInvName();
        }
    }
    
}
