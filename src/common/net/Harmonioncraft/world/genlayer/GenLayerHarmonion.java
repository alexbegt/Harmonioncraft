package net.Harmonioncraft.world.genlayer;

import net.minecraft.src.GenLayer;
import net.minecraft.src.GenLayerFuzzyZoom;
import net.minecraft.src.GenLayerSmooth;
import net.minecraft.src.GenLayerVoronoiZoom;
import net.minecraft.src.GenLayerZoom;

public abstract class GenLayerHarmonion extends GenLayer
{
    public static GenLayer[] getGenLayers(long var0)
    {
        GenLayerHarmonionIsland var2 = new GenLayerHarmonionIsland(1L);
        GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var2);
        GenLayerHarmonionExpand var10 = new GenLayerHarmonionExpand(1L, var9);
        GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
        var10 = new GenLayerHarmonionExpand(2L, var11);
        var11 = new GenLayerZoom(2002L, var10);
        var10 = new GenLayerHarmonionExpand(3L, var11);
        byte var3 = 2;
        GenLayer var4 = GenLayerZoom.func_75915_a(1000L, var10, 0);
        var4 = GenLayerZoom.func_75915_a(1000L, var4, var3 + 2);
        GenLayerHarmonionRiver var12 = new GenLayerHarmonionRiver(1L, var4);
        GenLayerSmooth var14 = new GenLayerSmooth(1000L, var12);
        GenLayerHarmonionAddBiomes var5 = new GenLayerHarmonionAddBiomes(200L, var10);
        GenLayerHarmonionAddRainforestBiomes var13 = new GenLayerHarmonionAddRainforestBiomes(100L, var5);
        GenLayerHarmonionSmallIslands var16 = new GenLayerHarmonionSmallIslands(100L, var13);
        GenLayerHarmonionLakes var15 = new GenLayerHarmonionLakes(100L, var16);
        Object var17 = GenLayerZoom.func_75915_a(1000L, var15, 2);
        Object var6 = new GenLayerTemperature((GenLayer)var17);
        Object var7 = new GenLayerDownfall((GenLayer)var17);

        for (int var8 = 0; var8 < var3; ++var8)
        {
            var17 = new GenLayerZoom((long)(1000 + var8), (GenLayer)var17);
            GenLayerSmoothZoom var18 = new GenLayerSmoothZoom((long)(1000 + var8), (GenLayer)var6);
            var6 = new GenLayerTemperatureMix(var18, (GenLayer)var17, var8);
            GenLayerSmoothZoom var20 = new GenLayerSmoothZoom((long)(1000 + var8), (GenLayer)var7);
            var7 = new GenLayerDownfallMix(var20, (GenLayer)var17, var8);
        }

        GenLayerSmooth var21 = new GenLayerSmooth(1000L, (GenLayer)var17);
        GenLayerHarmonionRiverMix var19 = new GenLayerHarmonionRiverMix(100L, var21, var14);
        GenLayerHarmonionBeach var24 = new GenLayerHarmonionBeach(1L, var19);
        GenLayer var22 = GenLayerSmoothZoom.func_35517_a(1000L, (GenLayer)var6, 2);
        GenLayer var23 = GenLayerSmoothZoom.func_35517_a(1000L, (GenLayer)var7, 2);
        GenLayerVoronoiZoom var25 = new GenLayerVoronoiZoom(10L, var24);
        var24.initWorldGenSeed(var0);
        var22.initWorldGenSeed(var0);
        var23.initWorldGenSeed(var0);
        var25.initWorldGenSeed(var0);
        return new GenLayer[] {var24, var25, var22, var23};
    }

    public GenLayerHarmonion(long var1)
    {
        super(var1);
    }
}
