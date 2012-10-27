package net.Harmonioncraft.world.mapgen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.MapGenBase;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class MapGenHMCCaves extends MapGenBase
{
    public List possibleTopWater = new ArrayList();

    public MapGenHMCCaves()
    {
        this.range = 10;
    }

    protected void generateCaveWaterfallNode(long var1, int var3, int var4, byte[] var5, double var6, double var8, double var10, float var12, float var13, float var14, int var15, int var16, double var17, int var19, double var20)
    {
        double var22 = var8;
        double var24 = (double)(var3 * 16 + 8);
        double var26 = (double)(var4 * 16 + 8);
        float var28 = 0.0F;
        float var29 = 0.0F;
        Random var30 = new Random(var1);

        do
        {
            if (var8 != var22 && var19 == 2)
            {
                var19 = 1;
            }

            double var31 = 1.5D + (double)(MathHelper.sin((float)var15 * (float)Math.PI / (float)var16) * var12 * 1.5F);
            double var33 = var31 * var17;
            float var35 = MathHelper.cos(var14);
            float var36 = MathHelper.sin(var14);

            if (Math.abs(var8 - var20) < 2.0D)
            {
                var8 -= 2.0D;
            }
            else
            {
                var8 -= var30.nextDouble() + 1.0D;
            }

            var14 *= 0.7F;
            var14 += var29 * 0.1F;
            var13 += var28 * 0.1F;
            var29 *= 0.9F;
            var28 *= 0.75F;
            var29 += (var30.nextFloat() - var30.nextFloat()) * var30.nextFloat() * 2.0F;
            var28 += (var30.nextFloat() - var30.nextFloat()) * var30.nextFloat() * 4.0F;
            double var37 = var6 - var24;
            double var39 = var10 - var26;
            double var41 = (double)(var16 - var15);
            double var43 = (double)(var12 + 2.0F + 16.0F);

            if (var37 * var37 + var39 * var39 - var41 * var41 > var43 * var43)
            {
                return;
            }

            if (var6 < var24 - 16.0D - var31 * 2.0D || var10 < var26 - 16.0D - var31 * 2.0D || var6 > var24 + 16.0D + var31 * 2.0D || var10 > var26 + 16.0D + var31 * 2.0D)
            {
                return;
            }

            int var45 = MathHelper.floor_double(var6 - var31) - var3 * 16 - 1;
            int var46 = MathHelper.floor_double(var6 + var31) - var3 * 16 + 1;
            int var47 = MathHelper.floor_double(var8 - var33) - 1;
            int var48 = MathHelper.floor_double(var8 + var33) + 1;
            int var49 = MathHelper.floor_double(var10 - var31) - var4 * 16 - 1;
            int var50 = MathHelper.floor_double(var10 + var31) - var4 * 16 + 1;

            if (var45 < 0)
            {
                var45 = 0;
            }

            if (var46 > 16)
            {
                var46 = 16;
            }

            if (var47 < 1)
            {
                var47 = 1;
            }

            if (var48 > 119)
            {
                var48 = 119;
            }

            if (var49 < 0)
            {
                var49 = 0;
            }

            if (var50 > 16)
            {
                var50 = 16;
            }

            boolean var51 = false;
            int var55;
            int var52;

            for (var52 = var45; !var51 && var52 < var46; ++var52)
            {
                for (int var53 = var49; !var51 && var53 < var50; ++var53)
                {
                    for (int var54 = var48 + 1; !var51 && var54 >= var47 - 1; --var54)
                    {
                        var55 = (var52 * 16 + var53) * 128 + var54;

                        if (var54 >= 0 && var54 < 128)
                        {
                            if (var5[var55] == Block.waterMoving.blockID || var5[var55] == Block.waterStill.blockID)
                            {
                                var51 = true;
                            }

                            if ((var54 == var48 + 1 || var54 == var48) && var19 == 2)
                            {
                                this.possibleTopWater.add(new int[] {var52 + var3 * 16, var54, var53 + var3 * 16});
                            }

                            if (var54 != var47 - 1 && var52 != var45 && var52 != var46 - 1 && var53 != var49 && var53 != var50 - 1)
                            {
                                var54 = var47;
                            }
                        }
                    }
                }
            }

            for (var52 = var45; var52 < var46; ++var52)
            {
                double var64 = ((double)(var52 + var3 * 16) + 0.5D - var6) / var31;

                for (var55 = var49; var55 < var50; ++var55)
                {
                    double var56 = ((double)(var55 + var4 * 16) + 0.5D - var10) / var31;
                    int var58 = (var52 * 16 + var55) * 128 + var48;
                    boolean var59 = false;

                    if (var64 * var64 + var56 * var56 < 1.0D)
                    {
                        for (int var60 = var48 - 1; var60 >= var47; --var60)
                        {
                            double var61 = ((double)var60 + 0.5D - var8) / var33;

                            if (var61 > -0.7D && var64 * var64 + var61 * var61 + var56 * var56 < 1.0D)
                            {
                                byte var63 = var5[var58];

                                if (var63 == Block.grass.blockID)
                                {
                                    var59 = true;
                                }

                                if (var63 == Block.stone.blockID || var63 == Block.dirt.blockID || var63 == Block.grass.blockID)
                                {
                                    var5[var58] = 0;

                                    if (var59 && var5[var58 - 1] == Block.dirt.blockID)
                                    {
                                        var5[var58 - 1] = this.worldObj.getWorldChunkManager().getBiomeGenAt(var52 + var3 * 16, var55 + var4 * 16).topBlock;
                                    }
                                }
                            }

                            --var58;
                        }
                    }
                }
            }
        }
        while (var22 - var8 < 2.0D);
    }

    protected void generateCaveNode(long var1, int var3, int var4, int var5, int var6, byte[] var7, double var8, double var10, double var12, float var14, float var15, float var16, int var17, int var18, double var19, int var21)
    {
        if (var21 <= 5)
        {
            double var22 = (double)(var5 * 16 + 8);
            double var24 = (double)(var6 * 16 + 8);
            float var26 = 0.0F;
            float var27 = 0.0F;
            Random var28 = new Random(var1);

            if (var18 <= 0)
            {
                var18 = this.rand.nextInt(70) + 60;
            }

            boolean var29 = false;

            if (var17 == -1)
            {
                var17 = var18 / 2;
                var29 = true;
            }

            int var30 = var28.nextInt(var18 / 2) + var18 / 6;

            for (boolean var31 = var28.nextInt(6) == 0; var17 < var18; ++var17)
            {
                double var32 = 1.5D + (double)(MathHelper.sin((float)var17 * (float)Math.PI / (float)var18) * var14 * 1.0F);
                double var34 = var32 * var19;
                float var36 = MathHelper.cos(var16);
                float var37 = MathHelper.sin(var16);
                var8 += (double)(MathHelper.cos(var15) * var36);
                var10 += (double)var37;
                var12 += (double)(MathHelper.sin(var15) * var36);

                if (var31)
                {
                    var16 *= 0.92F;
                }
                else
                {
                    var16 *= 0.7F;
                }

                var16 += var27 * 0.1F;
                var15 += var26 * 0.1F;
                var27 *= 0.9F;
                var26 *= 0.75F;
                var27 += (var28.nextFloat() - var28.nextFloat()) * var28.nextFloat() * 2.0F;
                var26 += (var28.nextFloat() - var28.nextFloat()) * var28.nextFloat() * 4.0F;

                if (!var29 && var17 == var30 && var14 > 0.8F && var18 > 0 && var21 < 5)
                {
                    this.generateCaveNode(var28.nextLong(), var3, var4, var5, var6, var7, var8, var10, var12, var28.nextFloat() * 0.5F + 0.5F, var15 - ((float)Math.PI / 2F), var16 / 3.0F, var17, var18, 1.0D, var21 + 1);
                    this.generateCaveNode(var28.nextLong(), var3, var4, var5, var6, var7, var8, var10, var12, var28.nextFloat() * 0.5F + 0.5F, var15 + ((float)Math.PI / 2F), var16 / 3.0F, var17, var18, 1.0D, var21 + 1);

                    if (var28.nextBoolean())
                    {
                        int var65 = var28.nextBoolean() ? 1 : -1;
                        this.generateCaveNode(var28.nextLong(), var3, var4, var5, var6, var7, var8, var10, var12, var28.nextFloat() * 0.5F + 0.5F, var15 - 4.770796F * (float)var65, var16 / 3.0F, var17, var18, 1.0D, var21 + 1);

                        if (var28.nextBoolean())
                        {
                            this.generateCaveNode(var28.nextLong(), var3, var4, var5, var6, var7, var8, var10, var12, var28.nextFloat() * 0.5F + 0.5F, var15 + 4.770796F * (float)var65, var16 / 3.0F, var17, var18, 1.0D, var21 + 1);
                        }
                    }

                    return;
                }

                if (var29 || var28.nextInt(8) != 0)
                {
                    double var38 = var8 - var22;
                    double var40 = var12 - var24;
                    double var42 = (double)(var18 - var17);
                    double var44 = (double)(var14 + 2.0F + 16.0F);

                    if (var38 * var38 + var40 * var40 - var42 * var42 > var44 * var44)
                    {
                        return;
                    }

                    if (var8 >= var22 - 16.0D - var32 * 2.0D && var12 >= var24 - 16.0D - var32 * 2.0D && var8 <= var22 + 16.0D + var32 * 2.0D && var12 <= var24 + 16.0D + var32 * 2.0D)
                    {
                        int var46 = MathHelper.floor_double(var8 - var32) - var5 * 16 - 1;
                        int var47 = MathHelper.floor_double(var8 + var32) - var5 * 16 + 1;
                        int var48 = MathHelper.floor_double(var10 - var34) - 1;
                        int var49 = MathHelper.floor_double(var10 + var34) + 1;
                        int var50 = MathHelper.floor_double(var12 - var32) - var6 * 16 - 1;
                        int var51 = MathHelper.floor_double(var12 + var32) - var6 * 16 + 1;

                        if (var46 < 0)
                        {
                            var46 = 0;
                        }

                        if (var47 > 16)
                        {
                            var47 = 16;
                        }

                        if (var48 < 1)
                        {
                            var48 = 1;
                        }

                        if (var49 > 120)
                        {
                            var49 = 120;
                        }

                        if (var50 < 0)
                        {
                            var50 = 0;
                        }

                        if (var51 > 16)
                        {
                            var51 = 16;
                        }

                        boolean var52 = false;
                        int var53;
                        int var56;

                        for (var53 = var46; !var52 && var53 < var47; ++var53)
                        {
                            for (int var54 = var50; !var52 && var54 < var51; ++var54)
                            {
                                for (int var55 = var49 + 1; !var52 && var55 >= var48 - 1; --var55)
                                {
                                    var56 = (var53 * 16 + var54) * 128 + var55;

                                    if (var55 >= 0 && var55 < 128)
                                    {
                                        if ((var7[var56] == Block.waterMoving.blockID || var7[var56] == Block.waterStill.blockID) && var55 > 34)
                                        {
                                            var52 = true;
                                        }

                                        if (var55 != var48 - 1 && var53 != var46 && var53 != var47 - 1 && var54 != var50 && var54 != var51 - 1)
                                        {
                                            var55 = var48;
                                        }
                                    }
                                }
                            }
                        }

                        if (!var52)
                        {
                            for (var53 = var46; var53 < var47; ++var53)
                            {
                                double var66 = ((double)(var53 + var5 * 16) + 0.5D - var8) / var32;

                                for (var56 = var50; var56 < var51; ++var56)
                                {
                                    double var57 = ((double)(var56 + var6 * 16) + 0.5D - var12) / var32;
                                    int var59 = (var53 * 16 + var56) * 128 + var49;
                                    boolean var60 = false;

                                    if (var66 * var66 + var57 * var57 < 1.0D)
                                    {
                                        for (int var61 = var49 - 1; var61 >= var48; --var61)
                                        {
                                            double var62 = ((double)var61 + 0.5D - var10) / var34;

                                            if (var62 > -0.7D && var66 * var66 + var62 * var62 + var57 * var57 < 1.0D)
                                            {
                                                byte var64 = var7[var59];

                                                if (var64 == Block.grass.blockID)
                                                {
                                                    var60 = true;
                                                }

                                                if (var64 == Block.stone.blockID || var64 == Block.dirt.blockID || var64 == Block.grass.blockID)
                                                {
                                                    if (var61 < 10)
                                                    {
                                                        var7[var59] = (byte)Block.lavaMoving.blockID;
                                                    }
                                                    else
                                                    {
                                                        var7[var59] = 0;

                                                        if (var60 && var7[var59 - 1] == Block.dirt.blockID)
                                                        {
                                                            var7[var59 - 1] = this.worldObj.getWorldChunkManager().getBiomeGenAt(var53 + var5 * 16, var56 + var6 * 16).topBlock;
                                                        }
                                                    }
                                                }
                                            }

                                            --var59;
                                        }
                                    }
                                }
                            }

                            if (var29)
                            {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, byte[] var6)
    {
        int var7 = this.rand.nextInt(8) + 4;

        if (this.rand.nextInt(20) != 0)
        {
            var7 = 0;
        }

        for (int var8 = 0; var8 < var7; ++var8)
        {
            double var9 = (double)(var2 * 16 + this.rand.nextInt(16));
            double var11 = (double)(this.rand.nextInt(var1.getHeight() - 32) + 8);
            double var13 = (double)(var3 * 16 + this.rand.nextInt(16));
            int var15 = 1;
            float var19;
            float var18;

            if (this.rand.nextInt(8) == 0)
            {
                double var16 = var11 + (double)this.rand.nextInt(12) + 10.0D;
                var18 = this.rand.nextFloat() * (float)Math.PI * 3.0F;
                var19 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float var20 = this.rand.nextFloat() + 0.8F;
                this.generateCaveWaterfallNode(this.rand.nextLong(), var4, var5, var6, var9, var16, var13, var20, var18, var19, 0, 0, 1.0D, 2, var11);
                var15 += this.rand.nextInt(4);
            }

            for (int var21 = 0; var21 < var15; ++var21)
            {
                float var17 = this.rand.nextFloat() * (float)Math.PI * 3.0F;
                var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                var19 = this.rand.nextFloat() + 0.8F;
                this.generateCaveNode(this.rand.nextLong(), var2, var3, var4, var5, var6, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D, 0);
            }
        }
    }

    public void generateWater(World var1, int var2, int var3)
    {
        int var4 = 0;
        Iterator var5 = this.possibleTopWater.iterator();

        while (var5.hasNext())
        {
            int[] var6 = (int[])var5.next();

            if (var6[0] >= var2 && var6[0] < var2 + 16 && var6[2] >= var3 && var6[2] < var3 + 16 && var1.getBlockId(var6[0], var6[1] + 1, var6[2]) == 1 && var1.getBlockId(var6[0], var6[1], var6[2]) == 0)
            {
                var1.setBlockWithNotify(var6[0], var6[1] + 1, var6[2], Block.waterStill.blockID);
                ++var4;

                if (var4 > 4)
                {
                    break;
                }
            }
        }
    }
}
