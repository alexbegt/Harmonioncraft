package net.Harmonion.liquids;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.Harmonion.liquids.tanks.StandardTank;
import net.Harmonion.util.inventory.InvTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public final class LiquidManager implements ILiquidAdaptor
{
    public static final int BUCKET_FILL_TIME = 8;
    public static final int NETWORK_UPDATE_INTERVAL = 128;
    public static final int BUCKET_VOLUME = 1000;
    private static LiquidManager instance;
    private List adapters = new ArrayList();

    private LiquidManager()
    {
        this.adapters.add(ForgeLiquidAdapater.getInstance());
    }

    public static LiquidManager getInstance()
    {
        if (instance == null)
        {
            instance = new LiquidManager();
        }

        return instance;
    }

    public boolean handleRightClick(ITankContainer var1, int var2, EntityPlayer var3, boolean var4, boolean var5)
    {
        ItemStack var6 = var3.inventory.getCurrentItem();

        if (var6 != null)
        {
            LiquidStack var7 = this.getLiquidInContainer(var6);

            if (var4 && var7 != null)
            {
                int var10 = var1.fill(var2, var7, true);

                if (var10 > 0)
                {
                    var3.inventory.setInventorySlotContents(var3.inventory.currentItem, InvTools.depleteItem(var6));
                    return true;
                }
            }
            else if (var5)
            {
                LiquidStack var8 = var1.getTanks(ForgeDirection.UNKNOWN)[var2].getLiquid();

                if (var8 != null)
                {
                    ItemStack var9 = this.fillLiquidContainer(var8, var6);
                    var7 = this.getLiquidInContainer(var9);

                    if (var7 != null)
                    {
                        if (var6.stackSize > 1)
                        {
                            if (!var3.inventory.addItemStackToInventory(var9))
                            {
                                return false;
                            }

                            var3.inventory.setInventorySlotContents(var3.inventory.currentItem, InvTools.depleteItem(var6));
                        }
                        else
                        {
                            var3.inventory.setInventorySlotContents(var3.inventory.currentItem, InvTools.depleteItem(var6));
                            var3.inventory.setInventorySlotContents(var3.inventory.currentItem, var9);
                        }

                        var1.drain(var2, var7.amount, true);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void processContainers(StandardTank var1, IInventory var2, int var3, int var4)
    {
        this.processContainers(var1, var2, var3, var4, true, true);
    }

    public void processContainers(StandardTank var1, IInventory var2, int var3, int var4, boolean var5, boolean var6)
    {
        TankManager var7 = new TankManager();
        var7.addTank(var1);
        this.processContainers(var7, 0, var2, var3, var4, var5, var6);
    }

    public void processContainers(ITankContainer var1, int var2, IInventory var3, int var4, int var5)
    {
        this.processContainers(var1, var2, var3, var4, var5, true, true);
    }

    public void processContainers(ITankContainer var1, int var2, IInventory var3, int var4, int var5, boolean var6, boolean var7)
    {
        ItemStack var8 = var3.getStackInSlot(var4);
        ItemStack var9 = var3.getStackInSlot(var5);

        if (var8 != null)
        {
            LiquidStack var10 = getInstance().getLiquidInContainer(var8);
            ItemStack var11 = var8.getItem().getContainerItemStack(var8);

            if (var6 && var10 != null && (var11 == null || var9 == null || var9.stackSize < var9.getMaxStackSize() && InvTools.isItemEqual(var9, var11)))
            {
                int var15 = var1.fill(var2, var10, false);

                if (var15 >= var10.amount)
                {
                    var1.fill(var2, var10, true);

                    if (var11 != null)
                    {
                        if (var9 == null)
                        {
                            var3.setInventorySlotContents(var5, var11);
                        }
                        else
                        {
                            ++var9.stackSize;
                        }
                    }

                    var3.decrStackSize(var4, 1);
                }
            }
            else if (var7 && getInstance().isEmptyContainer(var8))
            {
                ItemStack var12 = getInstance().fillLiquidContainer(var1.getTanks(ForgeDirection.UNKNOWN)[var2].getLiquid(), var8);

                if (var12 != null && (var9 == null || var9.stackSize < var9.getMaxStackSize() && InvTools.isItemEqual(var12, var9)))
                {
                    LiquidStack var13 = getInstance().getLiquidInContainer(var12);
                    LiquidStack var14 = var1.drain(var2, var13.amount, false);

                    if (var14 != null && var14.amount > 0)
                    {
                        var1.drain(var2, var13.amount, true);

                        if (var9 == null)
                        {
                            var3.setInventorySlotContents(var5, var12);
                        }
                        else
                        {
                            ++var9.stackSize;
                        }

                        var3.decrStackSize(var4, 1);
                    }
                }
            }
        }
    }

    public boolean isBucket(ItemStack var1)
    {
        return LiquidContainerRegistry.isBucket(var1);
    }

    public boolean isContainer(ItemStack var1)
    {
        return LiquidContainerRegistry.isContainer(var1);
    }

    public boolean isFilledContainer(ItemStack var1)
    {
        return LiquidContainerRegistry.isFilledContainer(var1);
    }

    public boolean isEmptyContainer(ItemStack var1)
    {
        return LiquidContainerRegistry.isEmptyContainer(var1);
    }

    public ItemStack fillLiquidContainer(LiquidStack var1, ItemStack var2)
    {
        return var1 != null && var2 != null ? LiquidContainerRegistry.fillLiquidContainer(var1, var2) : null;
    }

    public ItemStack getFilledLiquidContainer(LiquidStack var1, ItemStack var2)
    {
        if (var1 != null && var2 != null)
        {
            var1 = var1.copy();
            var1.amount = Integer.MAX_VALUE;
            return LiquidContainerRegistry.fillLiquidContainer(var1, var2);
        }
        else
        {
            return null;
        }
    }

    public LiquidStack getLiquidInContainer(ItemStack var1)
    {
        return LiquidContainerRegistry.getLiquidForFilledItem(var1);
    }

    public boolean containsLiquid(ItemStack var1, LiquidStack var2)
    {
        return LiquidContainerRegistry.containsLiquid(var1, var2);
    }

    public boolean isLiquidEqual(LiquidStack var1, LiquidStack var2)
    {
        return var1 != null && var2 != null ? var1.isLiquidEqual(var2) : false;
    }

    public void registerBucket(LiquidStack var1, ItemStack var2)
    {
        ItemStack var3 = new ItemStack(Item.bucketEmpty);
        LiquidContainerData var4 = new LiquidContainerData(var1, var2, var3);
        this.registerContainer(var4);
    }
    
    public void registerBottle(LiquidStack var1, ItemStack var2)
    {
        ItemStack var3 = new ItemStack(Item.glassBottle);
        LiquidContainerData var4 = new LiquidContainerData(var1, var2, var3);
        this.registerContainer(var4);
    }

    public void registerContainer(LiquidContainerData var1)
    {
        Iterator var2 = this.adapters.iterator();

        while (var2.hasNext())
        {
            ILiquidAdaptor var3 = (ILiquidAdaptor)var2.next();
            var3.registerContainer(var1);
        }
    }
}
