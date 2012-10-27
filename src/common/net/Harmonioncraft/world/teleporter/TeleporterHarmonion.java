package net.Harmonioncraft.world.teleporter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Teleporter;
import net.minecraft.src.TileEntityChest;
import net.minecraft.src.World;
import net.Harmonioncraft.block.ModBlocks;
import net.Harmonioncraft.world.WorldProviderHarmonion;

public class TeleporterHarmonion extends Teleporter
{
    private int portalBlockID;
    private int portalWallID;
    private static final List validBlockIDs = Arrays.asList(new Integer[] {Integer.valueOf(Block.grass.blockID), Integer.valueOf(Block.dirt.blockID)});

    public TeleporterHarmonion()
    {
        this.portalBlockID = ModBlocks.HarmonionPortal.blockID;
        this.portalWallID = ModBlocks.HarmonionBlock.blockID;
    }

    /**
     * Place an entity in a nearby portal, creating one if necessary.
     */
    public void placeInPortal(World var1, Entity var2)
    {
        if (!this.placeInExistingPortal(var1, var2))
        {
            this.createPortal(var1, var2);
            this.placeInExistingPortal(var1, var2);
        }
    }

    /**
     * Place an entity in a nearby portal which already exists.
     */
    public boolean placeInExistingPortal(World var1, Entity var2)
    {
        short var3 = 256;
        double var4 = -1.0D;
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;
        int var9 = MathHelper.floor_double(var2.posX);
        int var10 = MathHelper.floor_double(var2.posZ);
        double var18;

        for (int var11 = var9 - var3; var11 <= var9 + var3; ++var11)
        {
            double var12 = (double)var11 + 0.5D - var2.posX;

            for (int var14 = var10 - var3; var14 <= var10 + var3; ++var14)
            {
                double var15 = (double)var14 + 0.5D - var2.posZ;

                for (int var17 = var1.getHeight() - 1; var17 >= 0; --var17)
                {
                    if (var1.getBlockId(var11, var17, var14) == this.portalBlockID)
                    {
                        while (var1.getBlockId(var11, var17 - 1, var14) == this.portalBlockID)
                        {
                            --var17;
                        }

                        var18 = (double)var17 + 0.5D - var2.posY;
                        double var20 = var12 * var12 + var18 * var18 + var15 * var15;

                        if (var4 < 0.0D || var20 < var4)
                        {
                            var4 = var20;
                            var6 = var11;
                            var7 = var17;
                            var8 = var14;
                        }
                    }
                }
            }
        }

        if (var4 >= 0.0D)
        {
            double var24 = (double)var6 + 0.5D;
            double var16 = (double)var7 + 0.5D;
            var18 = (double)var8 + 0.5D;

            if (var1.getBlockId(var6 - 1, var7, var8) == this.portalBlockID)
            {
                var24 -= 0.5D;
            }

            if (var1.getBlockId(var6 + 1, var7, var8) == this.portalBlockID)
            {
                var24 += 0.5D;
            }

            if (var1.getBlockId(var6, var7, var8 - 1) == this.portalBlockID)
            {
                var18 -= 0.5D;
            }

            if (var1.getBlockId(var6, var7, var8 + 1) == this.portalBlockID)
            {
                var18 += 0.5D;
            }

            var2.setLocationAndAngles(var24, var16 + 2.0D, var18, var2.rotationYaw, 0.0F);
            int var25 = MathHelper.floor_double(var24) + ((new Random()).nextBoolean() ? 3 : -3);
            int var21 = MathHelper.floor_double(var18) + ((new Random()).nextBoolean() ? 3 : -3);
            int var22 = var1.getHeightValue(var25, var21) + 2;
            var1.getWorldInfo().setSpawnPosition(var25, var22, var21);
            var2.motionX = var2.motionY = var2.motionZ = 0.0D;

            if (var2 instanceof EntityPlayer)
            {
                EntityPlayer var23 = (EntityPlayer)var2;

                if (var1.provider instanceof WorldProviderHarmonion)
                {
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Create a new portal near an entity.
     */
    public boolean createPortal(World var1, Entity var2)
    {
        byte var3 = 32;
        double var4 = -1.0D;
        int var6 = MathHelper.floor_double(var2.posX);
        int var7 = MathHelper.floor_double(var2.posY);
        int var8 = MathHelper.floor_double(var2.posZ);
        int var9 = var6;
        int var10 = var7;
        int var11 = var8;

        for (int var12 = var6 - var3; var12 <= var6 + var3; ++var12)
        {
            double var13 = (double)var12 + 0.5D - var2.posX;
            label88:

            for (int var15 = var8 - var3; var15 <= var8 + var3; ++var15)
            {
                double var16 = (double)var15 + 0.5D - var2.posZ;
                int var18;

                for (var18 = var1.getHeight() - 1; var18 >= 62 && (var1.getBlockId(var12, var18, var15) == 0 || !Block.blocksList[var1.getBlockId(var12, var18, var15)].blockMaterial.isSolid()); --var18)
                {
                    ;
                }

                if (var18 <= 83 && var18 >= 63 && validBlockIDs.contains(Integer.valueOf(var1.getBlockId(var12, var18, var15))))
                {
                    for (int var19 = -2; var19 <= 2; ++var19)
                    {
                        for (int var20 = -2; var20 <= 2; ++var20)
                        {
                            int var21;

                            for (var21 = var1.getHeight() - 1; var21 >= 63 && (var1.getBlockId(var12 + var19, var21, var15 + var20) == 0 || !Block.blocksList[var1.getBlockId(var12 + var19, var21, var15 + var20)].blockMaterial.isSolid()); --var21)
                            {
                                ;
                            }

                            if (Math.abs(var18 - var21) >= 3 || !validBlockIDs.contains(Integer.valueOf(var1.getBlockId(var12 + var19, var21, var15 + var20))))
                            {
                                continue label88;
                            }
                        }
                    }

                    double var24 = (double)var18 + 0.5D - var2.posY;
                    double var25 = var13 * var13 + var24 * var24 + var16 * var16;

                    if (var4 < 0.0D || var25 < var4)
                    {
                        var4 = var25;
                        var9 = var12;
                        var10 = var18;
                        var11 = var15;
                    }
                }
            }
        }

        if (var4 < 0.0D)
        {
            Random var23 = new Random();
            var9 += var23.nextInt(16) - 8;
            var11 += var23.nextInt(16) - 8;
            var10 = 63;
        }

        this.buildTeleporterAt(var1, var9, var10, var11, var2);
        return true;
    }

    public void buildTeleporterAt(World var1, int var2, int var3, int var4, Entity var5)
    {
        var3 = var3 < 9 ? 9 : var3;
        var1.editingBlocks = true;
        int var6;
        int var7;
        int var8;
        int var9;
        int var10;
        int var11;

        for (var6 = 4; var6 >= -7; --var6)
        {
            for (var7 = -2; var7 <= 2; ++var7)
            {
                for (var8 = -2; var8 <= 2; ++var8)
                {
                    var9 = var2 + var8;
                    var10 = var3 + var6;
                    var11 = var4 + var7;
                    boolean var12;

                    if (var6 == -7)
                    {
                        var1.setBlockWithNotify(var9, var10, var11, this.portalWallID);
                    }
                    else if (var6 > 0)
                    {
                        var1.setBlockWithNotify(var9, var10, var11, 0);
                    }
                    else
                    {
                        var12 = var8 == -2 || var8 == 2 || var7 == -2 || var7 == 2;

                        if (var12)
                        {
                            var1.setBlockWithNotify(var9, var10, var11, this.portalWallID);
                        }
                        else
                        {
                            int var13 = var6 == -6 ? 8 : 0;
                            var1.setBlockAndMetadataWithNotify(var9, var10, var11, this.portalBlockID, var13);
                        }
                    }

                    var12 = (var8 == -2 || var8 == 2) && (var7 == -2 || var7 == 2);

                    if (var6 == 0 && var12)
                    {
                        var1.setBlockAndMetadataWithNotify(var9, var10 + 1, var11, Block.torchWood.blockID, 1);
                        var1.setBlockAndMetadataWithNotify(var9, var10 + 2, var11, Block.torchWood.blockID, 1);
                        var1.setBlockAndMetadataWithNotify(var9, var10 + 3, var11, Block.torchWood.blockID, 0);
                    }
                }
            }
        }

        var1.editingBlocks = false;

        if (var1.provider instanceof WorldProviderHarmonion)
        {
            var1.setBlockAndMetadataWithNotify(var2 + 2, var3 + 1, var4, Block.chest.blockID, 1);//TropicraftMod.bambooChest.blockID, 1);
            //TileEntityBambooChest var14 = (TileEntityBambooChest)var1.getBlockTileEntity(var2 + 2, var3 + 1, var4);
            TileEntityChest var14 = (TileEntityChest)var1.getBlockTileEntity(var2 + 2, var3 + 1, var4);

            if (var14 != null)
            {
                //var14.setIsUnbreakable(true);
            }
        }

        for (var6 = 5; var6 >= -7; --var6)
        {
            for (var7 = -2; var7 <= 2; ++var7)
            {
                for (var8 = -2; var8 <= 2; ++var8)
                {
                    var9 = var2 + var8;
                    var10 = var3 + var6;
                    var11 = var4 + var7;
                    var1.notifyBlocksOfNeighborChange(var9, var10, var11, var1.getBlockId(var9, var10, var11));
                }
            }
        }
    }
}
