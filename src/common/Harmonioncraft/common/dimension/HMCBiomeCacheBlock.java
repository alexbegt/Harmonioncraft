package Harmonioncraft.common.dimension;

import net.minecraft.src.BiomeCache;
import net.minecraft.src.BiomeGenBase;

public class HMCBiomeCacheBlock {
	
	/** An array of chunk temperatures saved by this cache. */
    public float[] temperatureValues;

    /** An array of chunk rainfall values saved by this cache. */
    public float[] rainfallValues;

    /** The array of biome types stored in this BiomeCacheBlock. */
    public BiomeGenBase[] biomes;

    /** The x coordinate of the BiomeCacheBlock. */
    public int xPosition;

    /** The z coordinate of the BiomeCacheBlock. */
    public int zPosition;

    /** The last time this BiomeCacheBlock was accessed, in milliseconds. */
    public long lastAccessTime;

    /** The BiomeCache object that contains this BiomeCacheBlock */
    final HMCBiomeCache theBiomeCache;

    public HMCBiomeCacheBlock(HMCBiomeCache par1BiomeCache, int par2, int par3)
    {
        this.theBiomeCache = par1BiomeCache;
        this.temperatureValues = new float[256];
        this.rainfallValues = new float[256];
        this.biomes = new BiomeGenBase[256];
        this.xPosition = par2;
        this.zPosition = par3;
        HMCBiomeCache.getChunkManager(par1BiomeCache).getTemperatures(this.temperatureValues, par2 << 4, par3 << 4, 16, 16);
        HMCBiomeCache.getChunkManager(par1BiomeCache).getRainfall(this.rainfallValues, par2 << 4, par3 << 4, 16, 16);
        HMCBiomeCache.getChunkManager(par1BiomeCache).getBiomeGenAt(this.biomes, par2 << 4, par3 << 4, 16, 16, false);
    }

    /**
     * Returns the BiomeGenBase related to the x, z position from the cache block.
     */
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.biomes[par1 & 15 | (par2 & 15) << 4];
    }
}
