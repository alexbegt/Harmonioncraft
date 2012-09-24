package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeHighlands extends HMCBiomeBase
{
    public HMCBiomeHighlands(int var1)
    {
        super(var1);
        this.minHeight = 2.5F;
        this.maxHeight = 0.4F;
        this.temperature = 0.5F;
        this.rainfall = 0.3F;
        ((HMCBiomeDecorator)this.theBiomeDecorator).canopyPerChunk = -999;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random var1)
    {
        return (WorldGenerator)(var1.nextInt(4) == 0 ? this.worldGeneratorBigTree : (var1.nextInt(10) == 0 ? new WorldGenTaiga2(true) : this.worldGeneratorForest));
    }
}