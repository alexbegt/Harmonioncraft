package Harmonioncraft.common.worldgen;

import java.util.Random;
import Harmonioncraft.common.Harmonioncraft;
import Harmonioncraft.common.block.ModBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenHarmonionTree extends WorldGenerator
{
    public static final int maxHeight = 8;

    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        while (var4 > 0)
        {
            int var6;

            for (var6 = HarmonionWorldGenerator.getWorldHeight(var1) - 1; var1.getBlockId(var3, var6 - 1, var5) == 0 && var6 > 0; --var6)
            {
                ;
            }

            if (!this.grow(var1, var3, var6, var5, var2))
            {
                var4 -= 3;
            }

            var3 += var2.nextInt(15) - 7;
            var5 += var2.nextInt(15) - 7;
            --var4;
        }

        return true;
    }

    public boolean grow(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1 != null && ModBlocks.HarmonionLog != null)
        {
            int var6 = 25;
            int var7 = this.getGrowHeight(var1, var2, var3, var4);

            if (var7 < 2)
            {
                return false;
            }
            else
            {
                int var8 = var7 / 2;
                var7 -= var7 / 2;
                var8 += var5.nextInt(var7 + 1);
                int var9;

                for (var9 = 0; var9 < var8; ++var9)
                {
                    var1.setBlockWithNotify(var2, var3 + var9, var4, ModBlocks.HarmonionLog.blockID);

                    if (var5.nextInt(100) <= var6)
                    {
                        var6 -= 10;
                        var1.setBlockMetadata(var2, var3 + var9, var4, var5.nextInt(4) + 2);
                    }
                    else
                    {
                        var1.setBlockMetadata(var2, var3 + var9, var4, 1);
                    }

                    Harmonioncraft.network.announceBlockUpdate(var1, var2, var3 + var9, var4);

                    if (var8 < 4 || var8 < 7 && var9 > 1 || var9 > 2)
                    {
                        for (int var10 = var2 - 2; var10 <= var2 + 2; ++var10)
                        {
                            for (int var11 = var4 - 2; var11 <= var4 + 2; ++var11)
                            {
                                int var12 = var9 + 4 - var8;

                                if (var12 < 1)
                                {
                                    var12 = 1;
                                }

                                boolean var13 = var10 > var2 - 2 && var10 < var2 + 2 && var11 > var4 - 2 && var11 < var4 + 2 || var10 > var2 - 2 && var10 < var2 + 2 && var5.nextInt(var12) == 0 || var11 > var4 - 2 && var11 < var4 + 2 && var5.nextInt(var12) == 0;

                                if (var13 && var1.getBlockId(var10, var3 + var9, var11) == 0)
                                {
                                    var1.setBlockWithNotify(var10, var3 + var9, var11, ModBlocks.HarmonionLeaves.blockID);
                                }
                            }
                        }
                    }
                }

                for (var9 = 0; var9 <= var8 / 4 + var5.nextInt(2); ++var9)
                {
                    if (var1.getBlockId(var2, var3 + var8 + var9, var4) == 0)
                    {
                        var1.setBlockWithNotify(var2, var3 + var8 + var9, var4, ModBlocks.HarmonionLeaves.blockID);
                    }
                }

                return true;
            }
        }
        else
        {
            System.out.println("[ERROR] Had a null that shouldn\'t have been. Harmonion Tree did not spawn! w=" + var1 + " r=" + ModBlocks.HarmonionLog);
            return false;
        }
    }

    public int getGrowHeight(World var1, int var2, int var3, int var4)
    {
        if ((var1.getBlockId(var2, var3 - 1, var4) == Block.grass.blockID || var1.getBlockId(var2, var3 - 1, var4) == Block.dirt.blockID) && (var1.getBlockId(var2, var3, var4) == 0 || var1.getBlockId(var2, var3, var4) == ModBlocks.HarmonionSapling.blockID))
        {
            int var5;

            for (var5 = 1; var1.getBlockId(var2, var3 + 1, var4) == 0 && var5 < 8; ++var3)
            {
                ++var5;
            }

            return var5;
        }
        else
        {
            return 0;
        }
    }
}
