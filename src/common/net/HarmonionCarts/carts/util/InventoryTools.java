package net.HarmonionCarts.carts.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.HarmonionCarts.carts.EnumItemType;
import net.minecraft.src.EntityItem;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryLargeChest;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class InventoryTools
{
    public static List getAdjacentInventories(World var0, int var1, int var2, int var3)
    {
        return getAdjacentInventories(var0, var1, var2, var3, (Class)null);
    }

    public static List getAdjacentInventories(World var0, int var1, int var2, int var3, Class var4)
    {
        ArrayList var5 = new ArrayList(5);

        for (int var6 = 0; var6 < 6; ++var6)
        {
            IInventory var7 = getInventoryFromSide(var0, var1, var2, var3, ForgeDirection.values()[var6], var4);

            if (var7 != null)
            {
                var5.add(var7);
            }
        }

        return var5;
    }

    public static Map getAdjacentInventoryMap(World var0, int var1, int var2, int var3)
    {
        return getAdjacentInventoryMap(var0, var1, var2, var3, (Class)null);
    }

    public static Map getAdjacentInventoryMap(World var0, int var1, int var2, int var3, Class var4)
    {
        TreeMap var5 = new TreeMap();

        for (int var6 = 0; var6 < 6; ++var6)
        {
            IInventory var7 = getInventoryFromSide(var0, var1, var2, var3, ForgeDirection.values()[var6], var4);

            if (var7 != null)
            {
                var5.put(Integer.valueOf(var6), var7);
            }
        }

        return var5;
    }

    public static IInventory getInventoryFromSide(World var0, int var1, int var2, int var3, ForgeDirection var4, Class var5)
    {
        var1 = GeneralTools.getXOnSide(var1, var4);
        var2 = GeneralTools.getYOnSide(var2, var4);
        var3 = GeneralTools.getZOnSide(var3, var4);
        TileEntity var6 = var0.getBlockTileEntity(var1, var2, var3);

        if (var6 != null && var6 instanceof IInventory && (var5 == null || var5.isAssignableFrom(var6.getClass())))
        {
            Object var7 = (IInventory)var6;
            ForgeDirection var8 = var4.getOpposite();

            if (var7 instanceof TileEntityChest)
            {
                for (int var9 = 2; var9 < 6; ++var9)
                {
                    var6 = GeneralTools.getBlockTileEntityOnSide(var0, var1, var2, var3, ForgeDirection.values()[var9]);

                    if (var6 instanceof TileEntityChest)
                    {
                        IInventory var10 = (IInventory)var6;
                        return new InventoryLargeChest("Large Chest", (IInventory)var7, var10);
                    }
                }
            }

            return (IInventory)var7;
        }
        else
        {
            return null;
        }
    }

    public static ItemStack depleteItem(ItemStack var0)
    {
        if (var0.stackSize == 1)
        {
            return var0.getItem().getContainerItemStack(var0);
        }
        else
        {
            var0.splitStack(1);
            return var0;
        }
    }

    public static void dropItem(ItemStack var0, World var1, double var2, double var4, double var6)
    {
        if (var0 != null && var0.stackSize >= 1)
        {
            EntityItem var8 = new EntityItem(var1, var2, var4 + 1.5D, var6, var0);
            var8.delayBeforeCanPickup = 10;
            var1.spawnEntityInWorld(var8);
        }
    }

    public static void dropInventory(IInventory var0, World var1, int var2, int var3, int var4)
    {
        if (!Game.isNotHost(var1))
        {
            for (int var5 = 0; var5 < var0.getSizeInventory(); ++var5)
            {
                ItemStack var6 = var0.getStackInSlot(var5);

                if (var6 != null)
                {
                    float var7 = GeneralTools.getRand().nextFloat() * 0.8F + 0.1F;
                    float var8 = GeneralTools.getRand().nextFloat() * 0.8F + 0.1F;
                    float var9 = GeneralTools.getRand().nextFloat() * 0.8F + 0.1F;

                    while (var6.stackSize > 0)
                    {
                        int var10 = GeneralTools.getRand().nextInt(21) + 10;

                        if (var10 > var6.stackSize)
                        {
                            var10 = var6.stackSize;
                        }

                        var6.stackSize -= var10;
                        EntityItem var11 = new EntityItem(var1, (double)((float)var2 + var7), (double)((float)var3 + var8), (double)((float)var4 + var9), new ItemStack(var6.itemID, var10, var6.getItemDamage()));
                        float var12 = 0.05F;
                        var11.motionX = (double)((float)GeneralTools.getRand().nextGaussian() * var12);
                        var11.motionY = (double)((float)GeneralTools.getRand().nextGaussian() * var12 + 0.2F);
                        var11.motionZ = (double)((float)GeneralTools.getRand().nextGaussian() * var12);
                        var1.spawnEntityInWorld(var11);
                    }

                    var0.setInventorySlotContents(var5, (ItemStack)null);
                }
            }
        }
    }

    public static boolean isInventoryEmpty(IInventory var0)
    {
        ItemStack var1 = null;

        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
            var1 = var0.getStackInSlot(var2);

            if (var1 != null)
            {
                break;
            }
        }

        return var1 == null;
    }

    public static boolean isInventoryEmpty(IInventory var0, ForgeDirection var1)
    {

        ItemStack var4 = null;

        for (int var3 = 0; var3 < ((IInventory)var0).getSizeInventory(); ++var3)
        {
            var4 = ((IInventory)var0).getStackInSlot(var3);

            if (var4 != null)
            {
                break;
            }
        }

        return var4 == null;
    }

    public static boolean isInventoryFull(IInventory var0)
    {
        ItemStack var1 = null;

        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
            var1 = var0.getStackInSlot(var2);

            if (var1 == null)
            {
                break;
            }
        }

        return var1 != null;
    }

    public static boolean isInventoryFull(IInventory var0, ForgeDirection var1)
    {

        ItemStack var4 = null;

        for (int var3 = 0; var3 < ((IInventory)var0).getSizeInventory(); ++var3)
        {
            var4 = ((IInventory)var0).getStackInSlot(var3);

            if (var4 == null)
            {
                break;
            }
        }

        return var4 != null;
    }

    public static int countItems(IInventory var0)
    {
        int var1 = 0;
        ItemStack var2 = null;

        for (int var3 = 0; var3 < var0.getSizeInventory(); ++var3)
        {
            var2 = var0.getStackInSlot(var3);

            if (var2 != null)
            {
                var1 += var2.stackSize;
            }
        }

        return var1;
    }

    public static int countItems(IInventory var0, ForgeDirection var1)
    {

        return countItems((IInventory)var0);
    }

    public static int countItems(IInventory var0, ItemStack ... var1)
    {

        boolean var2 = false;
        ItemStack[] var3 = var1;
        int var4 = var1.length;
        int var5;

        for (var5 = 0; var5 < var4; ++var5)
        {
            ItemStack var6 = var3[var5];

            if (var6 != null)
            {
                var2 = true;
                break;
            }
        }

        if (!var2)
        {
            return countItems(var0);
        }
        else
        {
            int var10 = 0;
            ItemStack var11 = null;

            for (var5 = 0; var5 < var0.getSizeInventory(); ++var5)
            {
                var11 = var0.getStackInSlot(var5);

                if (var11 != null)
                {
                    ItemStack[] var12 = var1;
                    int var7 = var1.length;

                    for (int var8 = 0; var8 < var7; ++var8)
                    {
                        ItemStack var9 = var12[var8];

                        if (var9 != null && isItemEqual(var11, var9))
                        {
                            var10 += var11.stackSize;
                            break;
                        }
                    }
                }
            }

            return var10;
        }
    }

    public static int countItems(Collection var0, ItemStack ... var1)
    {
        int var2 = 0;
        IInventory var4;

        for (Iterator var3 = var0.iterator(); var3.hasNext(); var2 += countItems(var4, var1))
        {
            var4 = (IInventory)var3.next();
        }

        return var2;
    }

    public static boolean containsItem(IInventory var0, ItemStack var1)
    {
        return countItems(var0, new ItemStack[] {var1}) > 0;
    }

    public static Map getManifest(IInventory var0)
    {
        ItemStackMap var1 = new ItemStackMap();

        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
            ItemStack var3 = var0.getStackInSlot(var2);

            if (var3 != null)
            {
                Integer var4 = (Integer)var1.get(var3);

                if (var4 == null)
                {
                    var4 = Integer.valueOf(0);
                }

                var4 = Integer.valueOf(var4.intValue() + var3.stackSize);
                var1.put(var3, var4);
            }
        }

        return var1;
    }

    public static ItemStack moveOneItem(IInventory var0, IInventory var1)
    {
        ItemStack var3;
        
        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
        	var3 = var0.getStackInSlot(var2);
        	if (var3 != null)
        	{
        		ItemStack var4 = var3.copy();
        		var4.stackSize = 1;
        		ItemStack var5 = moveItemStack(var4, var1);
        		if (var5 == null)
        		{
        			var0.decrStackSize(var2, 1);
        			return var4;
        			}
        	}
        }
        return null;
    }

    public static ItemStack moveOneItem(IInventory var0, IInventory var1, ItemStack ... var2)
    {
        boolean var3 = false;
        ItemStack[] var4 = var2;
        int var5 = var2.length;
        ItemStack var7;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            var7 = var4[var6];

            if (var7 != null)
            {
                var3 = true;
                break;
            }
        }

        ItemStack var9;
        
        for (int var8 = 0; var8 < var0.getSizeInventory(); ++var8)
            {
                var9 = var0.getStackInSlot(var8);

                if (var9 != null && (!var3 || isItemEqual(var9, var2)))
                {
                    ItemStack var10 = var9.copy();
                    var10.stackSize = 1;
                    var7 = moveItemStack(var10, var1);

                    if (var7 == null)
                    {
                        var0.decrStackSize(var8, 1);
                        return var10;
                    }
                }
            }

            return null;
    }

    public static ItemStack moveOneItem(List var0, IInventory var1, ItemStack ... var2)
    {
        Iterator var3 = var0.iterator();
        ItemStack var5;

        do
        {
            if (!var3.hasNext())
            {
                return null;
            }

            IInventory var4 = (IInventory)var3.next();
            var5 = moveOneItem(var4, var1, var2);
        }
        while (var5 == null);

        return var5;
    }

    public static ItemStack moveOneItem(IInventory var0, List var1, ItemStack ... var2)
    {
        Iterator var3 = var1.iterator();
        ItemStack var5;

        do
        {
            if (!var3.hasNext())
            {
                return null;
            }

            IInventory var4 = (IInventory)var3.next();
            var5 = moveOneItem(var0, var4, var2);
        }
        while (var5 == null);

        return var5;
    }

    public static ItemStack moveOneItem(IInventory var0, IInventory var1, EnumItemType var2)
    {
        ItemStack var3 = removeOneItem(var0, var2);

        if (var3 != null)
        {
            var3 = moveItemStack(var3, var1);
        }

        return var3;
    }

    public static ItemStack moveOneItemExcept(IInventory var0, IInventory var1, ItemStack ... var2)
    {
        boolean var3 = false;
        ItemStack[] var4 = var2;
        int var5 = var2.length;
        ItemStack var7;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            var7 = var4[var6];

            if (var7 != null)
            {
                var3 = true;
                break;
            }
        }

        ItemStack var9;
            for (int var8 = 0; var8 < var0.getSizeInventory(); ++var8)
            {
                var9 = var0.getStackInSlot(var8);

                if (var9 != null && (!var3 || !isItemEqual(var9, var2)))
                {
                    ItemStack var10 = var9.copy();
                    var10.stackSize = 1;
                    var7 = moveItemStack(var10, var1);

                    if (var7 == null)
                    {
                        var0.decrStackSize(var8, 1);
                        return var10;
                    }
                }
            }

            return null;
    }

    public static ItemStack moveOneItemExcept(List var0, IInventory var1, ItemStack ... var2)
    {
        Iterator var3 = var0.iterator();
        ItemStack var5;

        do
        {
            if (!var3.hasNext())
            {
                return null;
            }

            IInventory var4 = (IInventory)var3.next();
            var5 = moveOneItemExcept(var4, var1, var2);
        }
        while (var5 == null);

        return var5;
    }

    public static ItemStack moveOneItemExcept(IInventory var0, List var1, ItemStack ... var2)
    {
        Iterator var3 = var1.iterator();
        ItemStack var5;

        do
        {
            if (!var3.hasNext())
            {
                return null;
            }

            IInventory var4 = (IInventory)var3.next();
            var5 = moveOneItemExcept(var0, var4, var2);
        }
        while (var5 == null);

        return var5;
    }

    public static boolean isItemEqual(ItemStack var0, ItemStack var1)
    {
        return var0 != null && var1 != null ? (var0.itemID != var1.itemID ? false : (var0.stackTagCompound != null && !var0.stackTagCompound.equals(var1.stackTagCompound) ? false : (var0.getHasSubtypes() && (var0.getItemDamage() == -1 || var1.getItemDamage() == -1) ? true : !var0.getHasSubtypes() || var0.getItemDamage() == var1.getItemDamage()))) : false;
    }

    public static boolean isItemEqual(ItemStack var0, ItemStack ... var1)
    {
        ItemStack[] var2 = var1;
        int var3 = var1.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            ItemStack var5 = var2[var4];

            if (isItemEqual(var0, var5))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isItemType(ItemStack var0, EnumItemType var1)
    {
        return EnumItemType.isItemType(var0, var1);
    }

    public static ItemStack moveItemStack(ItemStack var0, IInventory var1)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            var0 = var0.copy();

            if (var1 == null)
            {
                return var0;
            }
            else
            {
                boolean var2 = false;

                do
                {
                    var2 = false;
                    ItemStack var3 = null;
                    int var4;

                    for (var4 = 0; var4 < var1.getSizeInventory(); ++var4)
                    {
                        var3 = var1.getStackInSlot(var4);

                        if (var3 != null && var3.isItemEqual(var0))
                        {
                            int var5 = Math.min(var3.getMaxStackSize(), var1.getInventoryStackLimit());
                            int var6 = var5 - var3.stackSize;

                            if (var6 > 0)
                            {
                                int var7 = Math.min(var6, var0.stackSize);
                                var3.stackSize += var7;
                                var0.stackSize -= var7;

                                if (var0.stackSize <= 0)
                                {
                                    return null;
                                }

                                var2 = true;
                            }
                        }
                    }

                    if (!var2)
                    {
                        for (var4 = 0; var4 < var1.getSizeInventory(); ++var4)
                        {
                            var3 = var1.getStackInSlot(var4);

                            if (var3 == null)
                            {
                                if (var0.stackSize <= var1.getInventoryStackLimit())
                                {
                                    var1.setInventorySlotContents(var4, var0);
                                    return null;
                                }

                                var1.setInventorySlotContents(var4, var0.splitStack(var1.getInventoryStackLimit()));
                                var2 = true;
                            }
                        }
                    }
                }
                while (var2);

                return var0;
            }
        }
    }

    public static boolean isRoomForStack(ItemStack var0, IInventory var1)
    {
        if (var0 == null)
        {
            return false;
        }
        else if (var1 == null)
        {
            return false;
        }
        else
        {
            for (int var2 = 0; var2 < var1.getSizeInventory(); ++var2)
            {
                ItemStack var3 = var1.getStackInSlot(var2);

                if (var3 == null)
                {
                    return true;
                }

                if (var3.isItemEqual(var0))
                {
                    int var4 = Math.min(var3.getMaxStackSize(), var1.getInventoryStackLimit());
                    int var5 = var4 - var3.stackSize;

                    if (var5 >= var0.stackSize)
                    {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static ItemStack[] removeItems(IInventory var0, int var1)
    {
            StandaloneInventory var2 = new StandaloneInventory(27);

            for (int var3 = 0; var3 < var0.getSizeInventory() && var1 > 0; ++var3)
            {
                ItemStack var4 = var0.getStackInSlot(var3);

                if (var4 != null)
                {
                    ItemStack var5 = var0.decrStackSize(var3, var1);
                    var1 -= var5.stackSize;
                    ItemStack var6 = moveItemStack(var5, var2);

                    if (var6 != null)
                    {
                        moveItemStack(var6, var0);
                        int var10000 = var1 + var6.stackSize;
                        break;
                    }
                }
            }

            LinkedList var8 = new LinkedList();
            ItemStack[] var9 = var2.getContents();
            int var10 = var9.length;

            for (int var11 = 0; var11 < var10; ++var11)
            {
                ItemStack var7 = var9[var11];

                if (var7 != null)
                {
                    var8.add(var7);
                }
            }

            return (ItemStack[])var8.toArray(new ItemStack[0]);
    }

    public static ItemStack removeOneItem(IInventory var0)
    {
        for (int var1 = 0; var1 < var0.getSizeInventory(); ++var1)
        {
            ItemStack var2 = var0.getStackInSlot(var1);

            if (var2 != null)
            {
                return var0.decrStackSize(var1, 1);
            }
        }

        return null;
    }

    public static ItemStack removeOneItem(IInventory var0, ItemStack var1)
    {
        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
            ItemStack var3 = var0.getStackInSlot(var2);

            if (var3 != null && isItemEqual(var3, var1))
            {
                return var0.decrStackSize(var2, 1);
            }
        }

        return null;
    }

    public static ItemStack removeOneItem(IInventory var0, EnumItemType var1)
    {
        for (int var2 = 0; var2 < var0.getSizeInventory(); ++var2)
        {
            ItemStack var3 = var0.getStackInSlot(var2);

            if (var3 != null && isItemType(var3, var1))
            {
                return var0.decrStackSize(var2, 1);
            }
        }

        return null;
    }

    public static void writeInvToNBT(IInventory var0, String var1, NBTTagCompound var2)
    {
        NBTTagList var3 = new NBTTagList();

        for (byte var4 = 0; var4 < var0.getSizeInventory(); ++var4)
        {
            ItemStack var5 = var0.getStackInSlot(var4);

            if (var5 != null)
            {
                NBTTagCompound var6 = new NBTTagCompound();
                var6.setByte("Slot", var4);
                writeItemToNBT(var5, var6);
                var3.appendTag(var6);
            }
        }

        var2.setTag(var1, var3);
    }

    public static void readInvFromNBT(IInventory var0, String var1, NBTTagCompound var2)
    {
        NBTTagList var3 = var2.getTagList(var1);

        for (byte var4 = 0; var4 < var3.tagCount(); ++var4)
        {
            NBTTagCompound var5 = (NBTTagCompound)var3.tagAt(var4);
            byte var6 = var5.getByte("Slot");

            if (var6 >= 0 && var6 < var0.getSizeInventory())
            {
                ItemStack var7 = readItemFromNBT(var5);
                var0.setInventorySlotContents(var6, var7);
            }
        }
    }

    public static void writeItemToNBT(ItemStack var0, NBTTagCompound var1)
    {
        var1.setShort("id", (short)var0.itemID);
        var1.setShort("stackSize", (short)var0.stackSize);
        var1.setShort("Damage", (short)var0.getItemDamage());

        if (var0.stackTagCompound != null)
        {
            var1.setTag("tag", var0.stackTagCompound);
        }
    }

    public static ItemStack readItemFromNBT(NBTTagCompound var0)
    {
        short var1 = var0.getShort("id");
        short var2 = var0.getShort("stackSize");
        short var3 = var0.getShort("Damage");

        if (var2 <= 0)
        {
            var2 = var0.getByte("Count");
        }

        if (var1 > 0 && var2 > 0)
        {
            ItemStack var4 = new ItemStack(var1, var2, var3);

            if (var0.hasKey("tag"))
            {
                var4.stackTagCompound = var0.getCompoundTag("tag");
            }

            return var4.getItem() != null ? var4 : null;
        }
        else
        {
            return null;
        }
    }
}
