package Harmonioncraft.common.dimension;

import net.minecraft.src.GenLayer;
import net.minecraft.src.GenLayerRiverMix;
import net.minecraft.src.GenLayerSmooth;
import net.minecraft.src.GenLayerVoronoiZoom;
import net.minecraft.src.GenLayerZoom;

public abstract class GenLayerHMC extends GenLayer
{
    public GenLayerHMC(long var1)
    {
        super(var1);
    }

    public static GenLayer[] makeTheWorld(long var0)
    {
        boolean var2 = true;
        GenLayerHMCBiomes var3 = new GenLayerHMCBiomes(1L);
        GenLayerHMCMajorFeatures var4 = new GenLayerHMCMajorFeatures(1L);
        GenLayerZoom var7 = new GenLayerZoom(1000L, var3);
        var7 = new GenLayerZoom(1001L, var7);
        GenLayerHMCBiomeBorders var8 = new GenLayerHMCBiomeBorders(500L, var7);
        var7 = new GenLayerZoom(1002L, var8);
        var7 = new GenLayerZoom(1003L, var7);
        GenLayer var9 = GenLayerHMCFeatureZoom.multipleZoom(1000L, var4, 4);
        GenLayerHMCMinorFeatures var10 = new GenLayerHMCMinorFeatures(700, var7, var9);
        GenLayerHMCClearMajorFeatures var13 = new GenLayerHMCClearMajorFeatures(700L, var10);
        GenLayerHMCClearMinorFeatures var11 = new GenLayerHMCClearMinorFeatures(701L, var13);
        var7 = new GenLayerZoom(1004L, var7);
        var7 = new GenLayerZoom(1005L, var7);
        GenLayerHMCStream var5 = new GenLayerHMCStream(1L, var7);
        GenLayerSmooth var12 = new GenLayerSmooth(7000L, var5);
        GenLayerRiverMix var14 = new GenLayerRiverMix(100L, var7, var12);
        GenLayerVoronoiZoom var6 = new GenLayerVoronoiZoom(10L, var14);
        var9 = GenLayerHMCFeatureZoom.multipleZoom(1004L, var11, 4);
        GenLayerHMCReinsertFeatures var15 = new GenLayerHMCReinsertFeatures(100L, var6, var9);
        var14.initWorldGenSeed(var0);
        var15.initWorldGenSeed(var0);
        var9.initWorldGenSeed(var0);
        return new GenLayer[] {var14, var15, var9};
    }
}