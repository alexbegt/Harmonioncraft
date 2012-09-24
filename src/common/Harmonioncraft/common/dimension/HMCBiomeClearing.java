package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeClearing extends HMCBiomeBase
{
    public HMCBiomeClearing(int var1)
    {
        super(var1);
        this.temperature = 0.8F;
        this.rainfall = 0.4F;
        this.minHeight = 0.01F;
        this.maxHeight = 0.0F;
        this.getHMCBiomeDecorator().canopyPerChunk = -999;
        this.getHMCBiomeDecorator().setTreesPerChunk(-999);
        this.getHMCBiomeDecorator().setFlowersPerChunk(4);
        this.getHMCBiomeDecorator().setGrassPerChunk(10);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random var1)
    {
        return new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
}
