package Harmonioncraft.common.dimension;

import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;

public class GenLayerHMCMajorFeatures extends GenLayer {

    public GenLayerHMCMajorFeatures(long var1)
    {
        super(var1);
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int var1, int var2, int var3, int var4)
    {
        int[] var5 = IntCache.getIntCache(var3 * var4);

        for (int var6 = 0; var6 < var5.length; ++var6)
        {
            var5[var6] = HMCBiomeBase.majorFeature.biomeID;
        }

        return var5;
    }
}