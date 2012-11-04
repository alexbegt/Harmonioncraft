package net.HarmonionCarts.carts;

import cpw.mods.fml.common.registry.EntityRegistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockRail;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemMinecart;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class CartTools
{
    public static ILinkageManager serverLinkageManager;

    public static void registerMinecart(Object var0, Class var1, String var2, int var3)
    {
        EntityRegistry.registerModEntity(var1, var2, var3, var0, 80, 3, true);
    }
    
    public static ILinkageManager getLinkageManager(World var0)
    {
        return serverLinkageManager;
    }

    public static void setCartOwner(EntityMinecart var0, EntityPlayer var1)
    {
        var0.getEntityData().setString("owner", var1.username);
    }

    public static void setCartOwner(EntityMinecart var0, String var1)
    {
        var0.getEntityData().setString("owner", var1);
    }

    public static String getCartOwner(EntityMinecart var0)
    {
        return var0.getEntityData().getString("owner");
    }

    public static boolean doesCartMatchFilter(ItemStack var0, EntityMinecart var1)
    {
        if (var0 == null)
        {
            return false;
        }
        else if (var1 instanceof IMinecart)
        {
            return ((IMinecart)var1).doesCartMatchFilter(var0, var1);
        }
        else
        {
            ItemStack var2 = var1.getCartItem();
            return var2 != null && isItemEqual(var0, var2);
        }
    }

    private static boolean isItemEqual(ItemStack var0, ItemStack var1)
    {
        return var0 != null && var1 != null ? (var0.itemID != var1.itemID ? false : (var0.stackTagCompound != null && !var0.stackTagCompound.equals(var1.stackTagCompound) ? false : (var0.getHasSubtypes() && (var0.getItemDamage() == -1 || var1.getItemDamage() == -1) ? true : !var0.getHasSubtypes() || var0.getItemDamage() == var1.getItemDamage()))) : false;
    }

    public static EntityMinecart placeCart(String var0, ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (var1 == null)
        {
            return null;
        }
        else
        {
            var1 = var1.copy();

            if (var1.getItem() instanceof IMinecartItem)
            {
                IMinecartItem var9 = (IMinecartItem)var1.getItem();
                return var9.placeCart(var0, var1, var2, var3, var4, var5);
            }
            else
            {
                if (var1.getItem() instanceof ItemMinecart)
                {
                    try
                    {
                        boolean var6 = var1.getItem().onItemUse(var1, (EntityPlayer)null, var2, var3, var4, var5, 0, 0.0F, 0.0F, 0.0F);

                        if (var6)
                        {
                            List var7 = getMinecartsAt(var2, var3, var4, var5, 0.3F);

                            if (var7.size() > 0)
                            {
                                setCartOwner((EntityMinecart)var7.get(0), var0);
                                return (EntityMinecart)var7.get(0);
                            }
                        }
                    }
                    catch (Exception var8)
                    {
                        return null;
                    }
                }

                return null;
            }
        }
    }

    public static void offerOrDropItem(EntityMinecart var0, ItemStack var1)
    {
        EntityMinecart var2 = getLinkageManager(var0.worldObj).getLinkedCartA(var0);
        EntityMinecart var3 = getLinkageManager(var0.worldObj).getLinkedCartB(var0);

        if (var1 != null && var1.stackSize > 0 && var2 instanceof IItemTransfer)
        {
            var1 = ((IItemTransfer)var2).offerItem(var0, var1);
        }

        if (var1 != null && var1.stackSize > 0 && var3 instanceof IItemTransfer)
        {
            var1 = ((IItemTransfer)var3).offerItem(var0, var1);
        }

        if (var1 != null && var1.stackSize > 0)
        {
            var0.entityDropItem(var1, 1.0F);
        }
    }

    public static boolean isMinecartOnRailAt(World var0, int var1, int var2, int var3, float var4)
    {
        return isMinecartOnRailAt(var0, var1, var2, var3, var4, (Class)null, true);
    }

    public static boolean isMinecartOnRailAt(World var0, int var1, int var2, int var3, float var4, Class var5, boolean var6)
    {
        return BlockRail.isRailBlockAt(var0, var1, var2, var3) ? isMinecartAt(var0, var1, var2, var3, var4, var5, var6) : false;
    }

    public static boolean isMinecartOnAnySide(World var0, int var1, int var2, int var3, float var4)
    {
        return isMinecartOnAnySide(var0, var1, var2, var3, var4, (Class)null, true);
    }

    public static boolean isMinecartOnAnySide(World var0, int var1, int var2, int var3, float var4, Class var5, boolean var6)
    {
        ArrayList var7 = new ArrayList();

        for (int var8 = 0; var8 < 6; ++var8)
        {
            var7.addAll(getMinecartsOnSide(var0, var1, var2, var3, var4, ForgeDirection.getOrientation(var8)));
        }

        if (var5 == null)
        {
            return !var7.isEmpty();
        }
        else
        {
            Iterator var10 = var7.iterator();
            EntityMinecart var9;

            do
            {
                if (!var10.hasNext())
                {
                    return false;
                }

                var9 = (EntityMinecart)var10.next();
            }
            while ((!var6 || !var5.isInstance(var9)) && var9.getClass() != var5);

            return true;
        }
    }

    public static boolean isMinecartAt(World var0, int var1, int var2, int var3, float var4)
    {
        return isMinecartAt(var0, var1, var2, var3, var4, (Class)null, true);
    }

    public static boolean isMinecartAt(World var0, int var1, int var2, int var3, float var4, Class var5, boolean var6)
    {
        List var7 = getMinecartsAt(var0, var1, var2, var3, var4);

        if (var5 == null)
        {
            return !var7.isEmpty();
        }
        else
        {
            Iterator var8 = var7.iterator();
            EntityMinecart var9;

            do
            {
                if (!var8.hasNext())
                {
                    return false;
                }

                var9 = (EntityMinecart)var8.next();
            }
            while ((!var6 || !var5.isInstance(var9)) && var9.getClass() != var5);

            return true;
        }
    }

    public static List getMinecartsOnAllSides(World var0, int var1, int var2, int var3, float var4)
    {
        ArrayList var5 = new ArrayList();

        for (int var6 = 0; var6 < 6; ++var6)
        {
            var5.addAll(getMinecartsOnSide(var0, var1, var2, var3, var4, ForgeDirection.getOrientation(var6)));
        }

        return var5;
    }

    public static List getMinecartsOnAllSides(World var0, int var1, int var2, int var3, float var4, Class var5, boolean var6)
    {
        ArrayList var7 = new ArrayList();
        ArrayList var8 = new ArrayList();

        for (int var9 = 0; var9 < 6; ++var9)
        {
            var7.addAll(getMinecartsOnSide(var0, var1, var2, var3, var4, ForgeDirection.getOrientation(var9)));
        }

        Iterator var11 = var7.iterator();

        while (var11.hasNext())
        {
            EntityMinecart var10 = (EntityMinecart)var11.next();

            if (var6 && var5.isInstance(var10) || var10.getClass() == var5)
            {
                var8.add(var10);
            }
        }

        return var8;
    }

    private static int getYOnSide(int var0, ForgeDirection var1)
    {
        switch (CartTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
        {
            case 1:
                return var0 + 1;

            case 2:
                return var0 - 1;

            default:
                return var0;
        }
    }

    private static int getXOnSide(int var0, ForgeDirection var1)
    {
        switch (CartTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
        {
            case 3:
                return var0 + 1;

            case 4:
                return var0 - 1;

            default:
                return var0;
        }
    }

    private static int getZOnSide(int var0, ForgeDirection var1)
    {
        switch (CartTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
        {
            case 5:
                return var0 - 1;

            case 6:
                return var0 + 1;

            default:
                return var0;
        }
    }

    public static List getMinecartsOnSide(World var0, int var1, int var2, int var3, float var4, ForgeDirection var5)
    {
        return getMinecartsAt(var0, getXOnSide(var1, var5), getYOnSide(var2, var5), getZOnSide(var3, var5), var4);
    }

    public static boolean isMinecartOnSide(World var0, int var1, int var2, int var3, float var4, ForgeDirection var5)
    {
        return getMinecartOnSide(var0, var1, var2, var3, var4, var5) != null;
    }

    public static EntityMinecart getMinecartOnSide(World var0, int var1, int var2, int var3, float var4, ForgeDirection var5)
    {
        Iterator var6 = getMinecartsOnSide(var0, var1, var2, var3, var4, var5).iterator();

        if (var6.hasNext())
        {
            EntityMinecart var7 = (EntityMinecart)var6.next();
            return var7;
        }
        else
        {
            return null;
        }
    }

    public static boolean isMinecartOnSide(World var0, int var1, int var2, int var3, float var4, ForgeDirection var5, Class var6, boolean var7)
    {
        return getMinecartOnSide(var0, var1, var2, var3, var4, var5, var6, var7) != null;
    }

    public static EntityMinecart getMinecartOnSide(World var0, int var1, int var2, int var3, float var4, ForgeDirection var5, Class var6, boolean var7)
    {
        Iterator var8 = getMinecartsOnSide(var0, var1, var2, var3, var4, var5).iterator();
        EntityMinecart var9;

        do
        {
            if (!var8.hasNext())
            {
                return null;
            }

            var9 = (EntityMinecart)var8.next();
        }
        while (var6 != null && (!var7 || !var6.isInstance(var9)) && var9.getClass() != var6);

        return var9;
    }

    public static List getMinecartsAt(World var0, int var1, int var2, int var3, float var4)
    {
        var4 = Math.min(var4, 0.49F);
        List var5 = var0.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)((float)var1 + var4), (double)((float)var2 + var4), (double)((float)var3 + var4), (double)((float)(var1 + 1) - var4), (double)((float)(var2 + 1) - var4), (double)((float)(var3 + 1) - var4)));
        ArrayList var6 = new ArrayList();
        Iterator var7 = var5.iterator();

        while (var7.hasNext())
        {
            Object var8 = var7.next();
            var6.add((EntityMinecart)var8);
        }

        return var6;
    }

    public static List getMinecartsIn(World var0, int var1, int var2, int var3, int var4, int var5, int var6)
    {
        List var7 = var0.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)var1, (double)var2, (double)var3, (double)var4, (double)var5, (double)var6));
        ArrayList var8 = new ArrayList();
        Iterator var9 = var7.iterator();

        while (var9.hasNext())
        {
            Object var10 = var9.next();
            var8.add((EntityMinecart)var10);
        }

        return var8;
    }

    public static double getCartSpeedUncapped(EntityMinecart var0)
    {
        return Math.sqrt(var0.motionX * var0.motionX + var0.motionZ * var0.motionZ);
    }

    public static boolean cartVelocityIsLessThan(EntityMinecart var0, float var1)
    {
        return Math.abs(var0.motionX) < (double)var1 && Math.abs(var0.motionZ) < (double)var1;
    }
}
