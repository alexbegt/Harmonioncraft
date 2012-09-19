package Harmonioncraft.common.dimension;

import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class HMCGenRavine extends MapGenBase4096 {
	
    private float[] field_35627_a = new float[1024];

    protected void generateRavine(long var1, int var3, int var4, short[] var5, double var6, double var8, double var10, float var12, float var13, float var14, int var15, int var16, double var17)
    {
        Random var19 = new Random(var1);
        double var20 = (double)(var3 * 16 + 8);
        double var22 = (double)(var4 * 16 + 8);
        float var24 = 0.0F;
        float var25 = 0.0F;

        if (var16 <= 0)
        {
            int var26 = this.range * 16 - 16;
            var16 = var26 - var19.nextInt(var26 / 4);
        }

        boolean var62 = false;

        if (var15 == -1)
        {
            var15 = var16 / 2;
            var62 = true;
        }

        float var27 = 1.0F;

        for (int var28 = 0; var28 < HMCWorld.WORLDHEIGHT; ++var28)
        {
            if (var28 == 0 || var19.nextInt(3) == 0)
            {
                var27 = 1.0F + var19.nextFloat() * var19.nextFloat() * 1.0F;
            }

            this.field_35627_a[var28] = var27 * var27;
        }

        for (; var15 < var16; ++var15)
        {
            double var61 = 1.5D + (double)(MathHelper.sin((float)var15 * (float)Math.PI / (float)var16) * var12 * 1.0F);
            double var30 = var61 * var17;
            var61 *= (double)var19.nextFloat() * 0.25D + 0.75D;
            var30 *= (double)var19.nextFloat() * 0.25D + 0.75D;
            float var32 = MathHelper.cos(var14);
            float var33 = MathHelper.sin(var14);
            var6 += (double)(MathHelper.cos(var13) * var32);
            var8 += (double)var33;
            var10 += (double)(MathHelper.sin(var13) * var32);
            var14 *= 0.7F;
            var14 += var25 * 0.05F;
            var13 += var24 * 0.05F;
            var25 *= 0.8F;
            var24 *= 0.5F;
            var25 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 2.0F;
            var24 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 4.0F;

            if (var62 || var19.nextInt(4) != 0)
            {
                double var34 = var6 - var20;
                double var36 = var10 - var22;
                double var38 = (double)(var16 - var15);
                double var40 = (double)(var12 + 2.0F + 16.0F);

                if (var34 * var34 + var36 * var36 - var38 * var38 > var40 * var40)
                {
                    return;
                }

                if (var6 >= var20 - 16.0D - var61 * 2.0D && var10 >= var22 - 16.0D - var61 * 2.0D && var6 <= var20 + 16.0D + var61 * 2.0D && var10 <= var22 + 16.0D + var61 * 2.0D)
                {
                    int var42 = MathHelper.floor_double(var6 - var61) - var3 * 16 - 1;
                    int var43 = MathHelper.floor_double(var6 + var61) - var3 * 16 + 1;
                    int var44 = MathHelper.floor_double(var8 - var30) - 1;
                    int var45 = MathHelper.floor_double(var8 + var30) + 1;
                    int var46 = MathHelper.floor_double(var10 - var61) - var4 * 16 - 1;
                    int var47 = MathHelper.floor_double(var10 + var61) - var4 * 16 + 1;

                    if (var42 < 0)
                    {
                        var42 = 0;
                    }

                    if (var43 > 16)
                    {
                        var43 = 16;
                    }

                    if (var44 < 1)
                    {
                        var44 = 1;
                    }

                    if (var45 > HMCWorld.WORLDHEIGHT - 8)
                    {
                        var45 = HMCWorld.WORLDHEIGHT - 8;
                    }

                    if (var46 < 0)
                    {
                        var46 = 0;
                    }

                    if (var47 > 16)
                    {
                        var47 = 16;
                    }

                    boolean var48 = false;
                    int var49;
                    int var52;

                    for (var49 = var42; !var48 && var49 < var43; ++var49)
                    {
                        for (int var50 = var46; !var48 && var50 < var47; ++var50)
                        {
                            for (int var51 = var45 + 1; !var48 && var51 >= var44 - 1; --var51)
                            {
                                var52 = (var49 * 16 + var50) * HMCWorld.WORLDHEIGHT + var51;

                                if (var51 >= 0 && var51 < HMCWorld.WORLDHEIGHT)
                                {
                                    if (var5[var52] == Block.waterMoving.blockID || var5[var52] == Block.waterStill.blockID)
                                    {
                                        var48 = true;
                                    }

                                    if (var51 != var44 - 1 && var49 != var42 && var49 != var43 - 1 && var50 != var46 && var50 != var47 - 1)
                                    {
                                        var51 = var44;
                                    }
                                }
                            }
                        }
                    }

                    if (!var48)
                    {
                        for (var49 = var42; var49 < var43; ++var49)
                        {
                            double var63 = ((double)(var49 + var3 * 16) + 0.5D - var6) / var61;

                            for (var52 = var46; var52 < var47; ++var52)
                            {
                                double var53 = ((double)(var52 + var4 * 16) + 0.5D - var10) / var61;
                                int var55 = (var49 * 16 + var52) * HMCWorld.WORLDHEIGHT + var45;
                                boolean var56 = false;

                                if (var63 * var63 + var53 * var53 < 1.0D)
                                {
                                    for (int var57 = var45 - 1; var57 >= var44; --var57)
                                    {
                                        double var58 = ((double)var57 + 0.5D - var8) / var30;

                                        if ((var63 * var63 + var53 * var53) * (double)this.field_35627_a[var57] + var58 * var58 / 6.0D < 1.0D)
                                        {
                                            short var60 = var5[var55];

                                            if (var60 == Block.grass.blockID)
                                            {
                                                var56 = true;
                                            }

                                            if (var60 == Block.stone.blockID || var60 == Block.dirt.blockID || var60 == Block.grass.blockID)
                                            {
                                                var5[var55] = 0;

                                                if (var56 && var5[var55 - 1] == Block.dirt.blockID)
                                                {
                                                    var5[var55 - 1] = (short)this.worldObj.getBiomeGenForCoords(var49 + var3 * 16, var52 + var4 * 16).topBlock;
                                                }
                                            }
                                        }

                                        --var55;
                                    }
                                }
                            }
                        }

                        if (var62)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    protected void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, short[] var6)
    {
        if (this.rand.nextInt(127) == 0)
        {
            if (HMCFeature.getNearestFeature(var4, var5, var1).chunkDecorationsEnabled)
            {
                double var7 = (double)(var2 * 16 + this.rand.nextInt(16));
                double var9 = (double)(this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
                double var11 = (double)(var3 * 16 + this.rand.nextInt(16));
                byte var13 = 1;

                for (int var14 = 0; var14 < var13; ++var14)
                {
                    float var15 = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                    float var16 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                    float var17 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
                    this.generateRavine(this.rand.nextLong(), var4, var5, var6, var7, var9, var11, var17, var15, var16, 0, 0, 3.0D);
                }
            }
        }
    }
}
