package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeForestVariant extends HMCBiomeBase
{
    public HMCBiomeForestVariant(int var1)
    {
        super(var1);
        this.temperature = 0.7F;
        this.rainfall = 0.8F;
        this.minHeight = 0.15F;
        this.maxHeight = 0.4F;
        this.getHMCBiomeDecorator().setTreesPerChunk(25);
        this.getHMCBiomeDecorator().setGrassPerChunk(15);
        this.getHMCBiomeDecorator().setFlowersPerChunk(8);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random var1)
    {
        return (WorldGenerator)(var1.nextInt(5) == 0 ? new WorldGenShrub(3, 0) : (var1.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random var1)
    {
        return var1.nextInt(4) != 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }
}