package Harmonioncraft.common.dimension;

import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerHMCStream extends GenLayer {
	
    public GenLayerHMCStream(long var1, GenLayer var3)
    {
        super(var1);
        super.parent = var3;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int var1, int var2, int var3, int var4)
    {
        int var5 = var1 - 1;
        int var6 = var2 - 1;
        int var7 = var3 + 2;
        int var8 = var4 + 2;
        int[] var9 = this.parent.getInts(var5, var6, var7, var8);
        int[] var10 = IntCache.getIntCache(var3 * var4);

        for (int var11 = 0; var11 < var4; ++var11)
        {
            for (int var12 = 0; var12 < var3; ++var12)
            {
                int var13 = var9[var12 + 0 + (var11 + 1) * var7];
                int var14 = var9[var12 + 2 + (var11 + 1) * var7];
                int var15 = var9[var12 + 1 + (var11 + 0) * var7];
                int var16 = var9[var12 + 1 + (var11 + 2) * var7];
                int var17 = var9[var12 + 1 + (var11 + 1) * var7];

                if (this.shouldStream(var17, var13, var15, var14, var16))
                {
                    var10[var12 + var11 * var3] = HMCBiomeBase.stream.biomeID;
                }
                else
                {
                    var10[var12 + var11 * var3] = -1;
                }
            }
        }

        return var10;
    }

    boolean shouldStream(int var1, int var2, int var3, int var4, int var5)
    {
        return this.shouldStream(var1, var2) ? true : (this.shouldStream(var1, var4) ? true : (this.shouldStream(var1, var3) ? true : this.shouldStream(var1, var5)));
    }

    boolean shouldStream(int var1, int var2)
    {
        return var1 == var2 ? false : (var1 == -var2 ? false : (var1 == HMCBiomeBase.glacier.biomeID && var2 == HMCBiomeBase.snow.biomeID ? false : (var1 == HMCBiomeBase.snow.biomeID && var2 == HMCBiomeBase.glacier.biomeID ? false : (var1 == HMCBiomeBase.deepMushrooms.biomeID && var2 == HMCBiomeBase.mushrooms.biomeID ? false : (var1 == HMCBiomeBase.mushrooms.biomeID && var2 == HMCBiomeBase.deepMushrooms.biomeID ? false : (var1 == HMCBiomeBase.swamp.biomeID && var2 == HMCBiomeBase.fireSwamp.biomeID ? false : (var1 == HMCBiomeBase.fireSwamp.biomeID && var2 == HMCBiomeBase.swamp.biomeID ? false : (var1 != HMCBiomeBase.HMCLake.biomeID && var2 != HMCBiomeBase.HMCLake.biomeID ? (var1 != HMCBiomeBase.clearing.biomeID && var2 != HMCBiomeBase.clearing.biomeID ? !HMCBiomeBase.isFeature(var1) && !HMCBiomeBase.isFeature(var2) : false) : false))))))));
    }
}
