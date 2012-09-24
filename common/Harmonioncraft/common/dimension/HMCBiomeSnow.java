package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeSnow extends HMCBiomeBase
{
    public HMCBiomeSnow(int var1)
    {
        super(var1);
        this.getHMCBiomeDecorator().setTreesPerChunk(7);
        this.getHMCBiomeDecorator().setGrassPerChunk(1);
        this.temperature = 0.125F;
        this.rainfall = 0.9F;
        this.getHMCBiomeDecorator().canopyPerChunk = -999;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random var1)
    {
        return (WorldGenerator)(var1.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(true));
    }

    /**
     * Returns true if the biome have snowfall instead a normal rain.
     */
    public boolean getEnableSnow()
    {
        return true;
    }

    /**
     * Return true if the biome supports lightning bolt spawn, either by have the bolts enabled and have rain enabled.
     */
    public boolean canSpawnLightningBolt()
    {
        return false;
    }
}