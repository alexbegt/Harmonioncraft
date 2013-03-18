package net.Harmonion.util.misc;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.item.*;
import net.Harmonion.liquids.LiquidFilter;
import net.Harmonion.liquids.LiquidManager;
import net.Harmonion.util.Game;
import net.Harmonion.util.inventory.InvTools;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public abstract class MiscTools
{
    private static Random rand = new Random();

    public static Random getRand()
    {
        return rand;
    }

    public static int getItemBurnTime(ItemStack var0)
    {
        try
        {
            if (var0 == null)
            {
                return 0;
            }
            else
            {
                int var1 = var0.getItem().itemID;
                String var3;

                if (var0.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
                {
                    Block var2 = Block.blocksList[var1];
                    var3 = var2.getBlockName();

                    if (var3 != null && var3.contains("blockScaffold"))
                    {
                        return 0;
                    }
                }

                LiquidStack var5 = LiquidManager.getInstance().getLiquidInContainer(var0);

                if (LiquidFilter.LAVA.isLiquidEqual(var5))
                {
                    return var5.amount;
                }
                else if (var1 == Item.coal.itemID && var0.getItemDamage() == 0 && !InvTools.isSynthetic(var0))
                {
                    return 3200;
                }
                else if (var1 == Item.blazeRod.itemID)
                {
                    return 800;
                }
                else
                {
                    var3 = var0.getItem().getItemName();
                    return var3 != null && var3.contains("itemScrap") ? 0 : TileEntityFurnace.getItemBurnTime(var0);
                }
            }
        }
        catch (Exception var4)
        {
            Game.logError("Error in Fuel Handler! Is some mod creating items that are not compliant with standards?", var4);
            return 0;
        }
    }

    public static boolean isOreClass(ItemStack var0, String var1)
    {
        ArrayList var2 = OreDictionary.getOres(var1);
        Iterator var3 = var2.iterator();
        ItemStack var4;

        do
        {
            if (!var3.hasNext())
            {
                return false;
            }

            var4 = (ItemStack)var3.next();
        }
        while (!InvTools.isItemEqual(var4, var0));

        return true;
    }

    public static boolean isPlayerConnected(String var0)
    {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerForUsername(var0) != null;
    }

    public static void registerMinecart(Object var0, Class var1, String var2, int var3)
    {
        EntityRegistry.registerModEntity(var1, var2, var3, var0, 80, 3, true);
    }

    public static String cleanTag(String var0)
    {
        return var0.replace("hc.", "");
    }

    public static MovingObjectPosition collisionRayTrace(Vec3 var0, Vec3 var1, int var2, int var3, int var4)
    {
        var0 = var0.addVector((double)(-var2), (double)(-var3), (double)(-var4));
        var1 = var1.addVector((double)(-var2), (double)(-var3), (double)(-var4));
        Vec3 var5 = var0.getIntermediateWithXValue(var1, 0.0D);
        Vec3 var6 = var0.getIntermediateWithXValue(var1, 1.0D);
        Vec3 var7 = var0.getIntermediateWithYValue(var1, 0.0D);
        Vec3 var8 = var0.getIntermediateWithYValue(var1, 1.0D);
        Vec3 var9 = var0.getIntermediateWithZValue(var1, 0.0D);
        Vec3 var10 = var0.getIntermediateWithZValue(var1, 1.0D);

        if (!isVecInsideYZBounds(var5))
        {
            var5 = null;
        }

        if (!isVecInsideYZBounds(var6))
        {
            var6 = null;
        }

        if (!isVecInsideXZBounds(var7))
        {
            var7 = null;
        }

        if (!isVecInsideXZBounds(var8))
        {
            var8 = null;
        }

        if (!isVecInsideXYBounds(var9))
        {
            var9 = null;
        }

        if (!isVecInsideXYBounds(var10))
        {
            var10 = null;
        }

        Vec3 var11 = null;

        if (var5 != null && (var11 == null || var0.distanceTo(var5) < var0.distanceTo(var11)))
        {
            var11 = var5;
        }

        if (var6 != null && (var11 == null || var0.distanceTo(var6) < var0.distanceTo(var11)))
        {
            var11 = var6;
        }

        if (var7 != null && (var11 == null || var0.distanceTo(var7) < var0.distanceTo(var11)))
        {
            var11 = var7;
        }

        if (var8 != null && (var11 == null || var0.distanceTo(var8) < var0.distanceTo(var11)))
        {
            var11 = var8;
        }

        if (var9 != null && (var11 == null || var0.distanceTo(var9) < var0.distanceTo(var11)))
        {
            var11 = var9;
        }

        if (var10 != null && (var11 == null || var0.distanceTo(var10) < var0.distanceTo(var11)))
        {
            var11 = var10;
        }

        if (var11 == null)
        {
            return null;
        }
        else
        {
            byte var12 = -1;

            if (var11 == var5)
            {
                var12 = 4;
            }

            if (var11 == var6)
            {
                var12 = 5;
            }

            if (var11 == var7)
            {
                var12 = 0;
            }

            if (var11 == var8)
            {
                var12 = 1;
            }

            if (var11 == var9)
            {
                var12 = 2;
            }

            if (var11 == var10)
            {
                var12 = 3;
            }

            return new MovingObjectPosition(var2, var3, var4, var12, var11.addVector((double)var2, (double)var3, (double)var4));
        }
    }

    private static boolean isVecInsideYZBounds(Vec3 var0)
    {
        return var0 == null ? false : var0.yCoord >= 0.0D && var0.yCoord <= 1.0D && var0.zCoord >= 0.0D && var0.zCoord <= 1.0D;
    }

    private static boolean isVecInsideXZBounds(Vec3 var0)
    {
        return var0 == null ? false : var0.xCoord >= 0.0D && var0.xCoord <= 1.0D && var0.zCoord >= 0.0D && var0.zCoord <= 1.0D;
    }

    private static boolean isVecInsideXYBounds(Vec3 var0)
    {
        return var0 == null ? false : var0.xCoord >= 0.0D && var0.xCoord <= 1.0D && var0.yCoord >= 0.0D && var0.yCoord <= 1.0D;
    }

    public static ForgeDirection getCurrentMousedOverSide(EntityPlayer var0)
    {
        double var1 = var0.capabilities.isCreativeMode ? 5.0D : 4.5D;
        Vec3 var3 = Vec3.createVectorHelper(var0.posX, var0.posY, var0.posZ);
        Vec3 var4 = var0.getLook(1.0F);
        var3.yCoord += (double)var0.getEyeHeight();
        var4 = var3.addVector(var4.xCoord * var1, var4.yCoord * var1, var4.zCoord * var1);
        MovingObjectPosition var5 = var0.worldObj.rayTraceBlocks(var3, var4);
        return var5 != null ? ForgeDirection.getOrientation(var5.sideHit) : ForgeDirection.UNKNOWN;
    }

    public static ForgeDirection getSideClosestToPlayer(World var0, int var1, int var2, int var3, EntityLiving var4)
    {
        if (MathHelper.abs((float)var4.posX - (float)var1) < 2.0F && MathHelper.abs((float)var4.posZ - (float)var3) < 2.0F)
        {
            double var5 = var4.posY + 1.82D - (double)var4.yOffset;

            if (var5 - (double)var2 > 2.0D)
            {
                return ForgeDirection.UP;
            }

            if ((double)var2 - var5 > 0.0D)
            {
                return ForgeDirection.DOWN;
            }
        }

        int var7 = MathHelper.floor_double((double)(var4.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (var7)
        {
            case 0:
                return ForgeDirection.NORTH;

            case 1:
                return ForgeDirection.EAST;

            case 2:
                return ForgeDirection.SOUTH;

            default:
                return var7 != 3 ? ForgeDirection.DOWN : ForgeDirection.WEST;
        }
    }

    public static ForgeDirection getHorizontalSideClosestToPlayer(World var0, int var1, int var2, int var3, EntityLiving var4)
    {
        ForgeDirection var5 = ForgeDirection.NORTH;
        int var6 = MathHelper.floor_double((double)(var4.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (var6)
        {
            case 0:
                return ForgeDirection.NORTH;

            case 1:
                return ForgeDirection.EAST;

            case 2:
                return ForgeDirection.SOUTH;

            case 3:
                return ForgeDirection.WEST;

            default:
                return ForgeDirection.NORTH;
        }
    }

    public static ForgeDirection getOppositeSide(int var0)
    {
        int var1 = var0 % 2 == 0 ? var0 + 1 : var0 - 1;
        return ForgeDirection.getOrientation(var1);
    }

    public static int getYOnSide(int var0, ForgeDirection var1)
    {
        switch (MiscTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
        {
            case 1:
                return var0 + 1;

            case 2:
                return var0 - 1;

            default:
                return var0;
        }
    }

    public static int getXOnSide(int var0, ForgeDirection var1)
    {
        switch (MiscTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
        {
            case 3:
                return var0 + 1;

            case 4:
                return var0 - 1;

            default:
                return var0;
        }
    }

    public static int getZOnSide(int var0, ForgeDirection var1)
    {
        switch (MiscTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
        {
            case 5:
                return var0 - 1;

            case 6:
                return var0 + 1;

            default:
                return var0;
        }
    }

    public static boolean blockExistsOnSide(World var0, int var1, int var2, int var3, ForgeDirection var4)
    {
        return var0.blockExists(getXOnSide(var1, var4), getYOnSide(var2, var4), getZOnSide(var3, var4));
    }

    public static int getBlockMetadataOnSide(World var0, int var1, int var2, int var3, ForgeDirection var4)
    {
        return var0.getBlockMetadata(getXOnSide(var1, var4), getYOnSide(var2, var4), getZOnSide(var3, var4));
    }

    public static int getBlockIdOnSide(IBlockAccess var0, int var1, int var2, int var3, ForgeDirection var4)
    {
        return var0.getBlockId(getXOnSide(var1, var4), getYOnSide(var2, var4), getZOnSide(var3, var4));
    }

    public static TileEntity getBlockTileEntityOnSide(World var0, int var1, int var2, int var3, ForgeDirection var4)
    {
        return var0.getBlockTileEntity(getXOnSide(var1, var4), getYOnSide(var2, var4), getZOnSide(var3, var4));
    }

    public static void notifyBlocksOfNeighborChangeOnSide(World var0, int var1, int var2, int var3, int var4, ForgeDirection var5)
    {
        var0.notifyBlocksOfNeighborChange(getXOnSide(var1, var5), getYOnSide(var2, var5), getZOnSide(var3, var5), var4);
    }
}
