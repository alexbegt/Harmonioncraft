package net.Harmonioncraft.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.Harmonioncraft.world.biomes.BiomeGenHarmonion;
import net.Harmonioncraft.world.genlayer.GenLayerHarmonion;
import net.minecraft.src.BiomeCache;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.GenLayer;
import net.minecraft.src.IntCache;
import net.minecraft.src.World;
import net.minecraft.src.WorldChunkManager;

public class WorldChunkManagerHarmonion extends WorldChunkManager
{
    private GenLayer field_34903_b;
    private GenLayer biomeIndexLayer;
    private GenLayer temperatureLayer;
    private GenLayer rainfallLayer;
    private BiomeCache biomeCache;
    private ArrayList biomesToSpawnIn;
    public BiomeGenBase[] field_4195_d;
    public float[] field_40541_b;

    protected WorldChunkManagerHarmonion()
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        this.biomesToSpawnIn.add(BiomeGenHarmonion.Harmonion);
    }

    public WorldChunkManagerHarmonion(World var1)
    {
        this();
        GenLayer[] var2 = GenLayerHarmonion.getGenLayers(var1.getSeed());
        this.field_34903_b = var2[0];
        this.biomeIndexLayer = var2[1];
        this.temperatureLayer = var2[2];
        this.rainfallLayer = var2[3];
    }

    /**
     * Gets the list of valid biomes for the player to spawn in.
     */
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair var1)
    {
        return this.getBiomeGenAt(var1.chunkXPos << 4, var1.chunkZPos << 4);
    }

    /**
     * Returns the BiomeGenBase related to the x, z position on the world.
     */
    public BiomeGenBase getBiomeGenAt(int var1, int var2)
    {
        return this.biomeCache.getBiomeGenAt(var1, var2);
    }

    /**
     * Returns a list of rainfall values for the specified blocks. Args: listToReuse, x, z, width, length.
     */
    public float[] getRainfall(float[] var1, int var2, int var3, int var4, int var5)
    {
        IntCache.resetIntCache();

        if (var1 == null || var1.length < var4 * var5)
        {
            var1 = new float[var4 * var5];
        }

        int[] var6 = this.biomeIndexLayer.getInts(var2, var3, var4, var5);

        for (int var7 = 0; var7 < var4 * var5; ++var7)
        {
            if (var6[var7] < 0)
            {
                System.out.println("-1 ERROR IN getRainfall()");
            }
            else
            {
                float var8 = (float)BiomeGenBase.biomeList[var6[var7]].getIntRainfall() / 65536.0F;

                if (var8 > 1.0F)
                {
                    var8 = 1.0F;
                }

                var1[var7] = var8;
            }
        }

        return var1;
    }

    /**
     * Return an adjusted version of a given temperature based on the y height
     */
    public float getTemperatureAtHeight(float var1, int var2)
    {
        return var1;
    }

    /**
     * Returns a list of temperatures to use for the specified blocks.  Args: listToReuse, x, y, width, length
     */
    public float[] getTemperatures(float[] var1, int var2, int var3, int var4, int var5)
    {
        IntCache.resetIntCache();

        if (var1 == null || var1.length < var4 * var5)
        {
            var1 = new float[var4 * var5];
        }

        int[] var6 = this.biomeIndexLayer.getInts(var2, var3, var4, var5);

        for (int var7 = 0; var7 < var4 * var5; ++var7)
        {
            if (var6[var7] < 0)
            {
                System.out.println("-1 ERROR IN getTemperatures()");
            }
            else
            {
                float var8 = (float)BiomeGenBase.biomeList[var6[var7]].getIntTemperature() / 65536.0F;

                if (var8 > 1.0F)
                {
                    var8 = 1.0F;
                }

                var1[var7] = var8;
            }
        }

        return var1;
    }

    /**
     * Returns an array of biomes for the location input.
     */
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] var1, int var2, int var3, int var4, int var5)
    {
        IntCache.resetIntCache();

        if (var1 == null || ((Object[])var1).length < var4 * var5)
        {
            var1 = new BiomeGenHarmonion[var4 * var5];
        }

        int[] var6 = this.field_34903_b.getInts(var2, var3, var4, var5);

        for (int var7 = 0; var7 < var4 * var5; ++var7)
        {
            ((Object[])var1)[var7] = BiomeGenHarmonion.hmcBiomeList[var6[var7]];
        }

        return (BiomeGenBase[])var1;
    }

    public BiomeGenBase[] loadRendererData(int var1, int var2, int var3, int var4)
    {
        if (var3 == 16 && var4 == 16 && (var1 & 15) == 0 && (var2 & 15) == 0)
        {
            return this.biomeCache.getCachedBiomes(var1, var2);
        }
        else
        {
            this.field_4195_d = this.loadBlockGeneratorData(this.field_4195_d, var1, var2, var3, var4);
            return this.field_4195_d;
        }
    }

    /**
     * Returns biomes to use for the blocks and loads the other data like temperature and humidity onto the
     * WorldChunkManager Args: oldBiomeList, x, z, width, depth
     */
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] var1, int var2, int var3, int var4, int var5)
    {
        return this.getBiomeGenAt(var1, var2, var3, var4, var5, true);
    }

    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x, y, width, length, cacheFlag (if false,
     * don't check biomeCache to avoid infinite loop in BiomeCacheBlock)
     */
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        IntCache.resetIntCache();

        if (var1 == null || ((Object[])var1).length < var4 * var5)
        {
            var1 = new BiomeGenHarmonion[var4 * var5];
        }

        if (var6 && var4 == 16 && var5 == 16 && (var2 & 15) == 0 && (var3 & 15) == 0)
        {
            BiomeGenBase[] var10 = this.biomeCache.getCachedBiomes(var2, var3);
            BiomeGenHarmonion[] var11 = new BiomeGenHarmonion[((Object[])var1).length];

            for (int var9 = 0; var9 < var10.length; ++var9)
            {
                var11[var9] = (BiomeGenHarmonion)var10[var9];
            }

            System.arraycopy(var10, 0, var1, 0, var4 * var5);
            return (BiomeGenBase[])var1;
        }
        else
        {
            int[] var7 = this.biomeIndexLayer.getInts(var2, var3, var4, var5);

            for (int var8 = 0; var8 < var4 * var5; ++var8)
            {
                if (var7[var8] < 0)
                {
                    System.out.println("-1 ERROR IN getBiomeGenAt()");
                }
                else
                {
                    ((Object[])var1)[var8] = BiomeGenHarmonion.hmcBiomeList[var7[var8]];
                }
            }

            return (BiomeGenBase[])var1;
        }
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    public boolean areBiomesViable(int var1, int var2, int var3, List var4)
    {
        int var5 = var1 - var3 >> 2;
        int var6 = var2 - var3 >> 2;
        int var7 = var1 + var3 >> 2;
        int var8 = var2 + var3 >> 2;
        int var9 = var7 - var5 + 1;
        int var10 = var8 - var6 + 1;
        int[] var11 = this.field_34903_b.getInts(var5, var6, var9, var10);

        for (int var12 = 0; var12 < var9 * var10; ++var12)
        {
            BiomeGenHarmonion var13 = BiomeGenHarmonion.hmcBiomeList[var11[var12]];

            if (!var4.contains(var13))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Finds a valid position within a range, that is in one of the listed biomes. Searches {par1,par2} +-par3 blocks.
     * Strongly favors positive y positions.
     */
    public ChunkPosition findBiomePosition(int var1, int var2, int var3, List var4, Random var5)
    {
        int var6 = var1 - var3 >> 2;
        int var7 = var2 - var3 >> 2;
        int var8 = var1 + var3 >> 2;
        int var9 = var2 + var3 >> 2;
        int var10 = var8 - var6 + 1;
        int var11 = var9 - var7 + 1;
        int[] var12 = this.field_34903_b.getInts(var6, var7, var10, var11);
        ChunkPosition var13 = null;
        int var14 = 0;

        for (int var15 = 0; var15 < var12.length; ++var15)
        {
            int var16 = var6 + var15 % var10 << 2;
            int var17 = var7 + var15 / var10 << 2;
            BiomeGenHarmonion var18 = BiomeGenHarmonion.hmcBiomeList[var12[var15]];

            if (var4.contains(var18) && (var13 == null || var5.nextInt(var14 + 1) == 0))
            {
                var13 = new ChunkPosition(var16, 0, var17);
                ++var14;
            }
        }

        return var13;
    }

    /**
     * Calls the WorldChunkManager's biomeCache.cleanupCache()
     */
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}