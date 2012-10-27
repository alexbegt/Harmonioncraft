package net.Harmonioncraft.world;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.Harmonioncraft.world.biomes.BiomeGenHarmonion;
import net.Harmonioncraft.world.worldgen.WorldGenHarmonionTree;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldType;

public class HMCWorldGenerator implements IWorldGenerator
{
    private static final List rainBiomes = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.desert, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.taiga});

    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        this.generateSurface(var4, var1, var2, var3);
    }

    public void generateSurface(World var1, Random var2, int var3, int var4)
    {
        WorldType var10001 = var1.provider.terrainType;

        if (var1.provider.terrainType == WorldType.DEFAULT)
        {
            int var5 = var3 + var2.nextInt(16) + 8;
            int var6 = var2.nextInt(62) + 64;
            int var7 = var4 + var2.nextInt(16) + 8;
            int var8;

            if (var1.getWorldChunkManager().getBiomeGenAt(var5, var7) == BiomeGenBase.desert || var1.getWorldChunkManager().getBiomeGenAt(var5, var7) == BiomeGenBase.beach)
            {
                for (var8 = 0; var8 < 8; ++var8)
                {
                    var6 = var2.nextInt(62) + 64;

                    if (var2.nextInt(5) == 0)
                    {
                        (new WorldGenHarmonionTree(true)).generate(var1, var2, var5, var6, var7);
                    }
                    else if (var2.nextInt(5) != 1 && var2.nextInt(5) != 2)
                    {
                        (new WorldGenHarmonionTree(true)).generate(var1, var2, var5, var6, var7);
                    }
                }
            }
        }
    }

    private boolean isRainforest(World var1, int var2, int var3)
    {
        return var1.getWorldChunkManager().getBiomeGenAt(var2, var3) == BiomeGenHarmonion.rainforestHills || var1.getWorldChunkManager().getBiomeGenAt(var2, var3) == BiomeGenHarmonion.rainforestMountains || var1.getWorldChunkManager().getBiomeGenAt(var2, var3) == BiomeGenHarmonion.rainforestPlains;
    }
}
