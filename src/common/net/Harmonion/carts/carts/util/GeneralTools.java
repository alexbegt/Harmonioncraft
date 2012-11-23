package net.Harmonion.carts.carts.util;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.TileEntity;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class GeneralTools
{
    private static Random rand = new Random();

    public static Random getRand()
    {
        return rand;
    }

    public static int getItemBurnTime(ItemStack var0)
    {
        if (var0 == null)
        {
            return 0;
        }
        else
        {
            int var1 = var0.getItem().shiftedIndex;

            if (var0.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var2 = Block.blocksList[var1];

                if (var2 == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (var2.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }

            return var1 == Item.coal.shiftedIndex ? (var0.getItemDamage() == 0 ? 3200 : 1600) : (var1 == Item.stick.shiftedIndex ? 100 : (var1 == Item.bucketLava.shiftedIndex ? 2000 : (var1 == Block.sapling.blockID ? 100 : (var1 == Item.blazeRod.shiftedIndex ? 2400 : GameRegistry.getFuelValue(var0)))));
        }
    }

    public static void registerMinecart(Object var0, Class var1, String var2, int var3)
    {
        EntityRegistry.registerModEntity(var1, var2, var3, var0, 80, 3, true);
    }

    public static String cleanTag(String var0)
    {
        return var0.replace("HMCC.", "");
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
        return var5 != null ? ForgeDirection.values()[var5.sideHit] : ForgeDirection.UNKNOWN;
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
        return var7 == 0 ? ForgeDirection.NORTH : (var7 == 1 ? ForgeDirection.EAST : (var7 == 2 ? ForgeDirection.SOUTH : (var7 != 3 ? ForgeDirection.DOWN : ForgeDirection.WEST)));
    }

    public static ForgeDirection getHorizontalSideClosestToPlayer(World var0, int var1, int var2, int var3, EntityLiving var4)
    {
        ForgeDirection var5 = ForgeDirection.NORTH;
        int var6 = MathHelper.floor_double((double)(var4.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            var5 = ForgeDirection.NORTH;
        }

        if (var6 == 1)
        {
            var5 = ForgeDirection.EAST;
        }

        if (var6 == 2)
        {
            var5 = ForgeDirection.SOUTH;
        }

        if (var6 == 3)
        {
            var5 = ForgeDirection.WEST;
        }

        return var5;
    }

    public static ForgeDirection getOppositeSide(int var0)
    {
        int var1 = var0 % 2 == 0 ? var0 + 1 : var0 - 1;
        return ForgeDirection.values()[var1];
    }

    public static int getYOnSide(int var0, ForgeDirection var1)
    {
        switch (GeneralTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
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
        switch (GeneralTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
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
        switch (GeneralTools$1.$SwitchMap$net$minecraftforge$common$ForgeDirection[var1.ordinal()])
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
