package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeFireSwamp extends HMCBiomeBase
{
    protected HMCBiomeFireSwamp(int var1)
    {
        super(var1);
        this.minHeight = -0.25F;
        this.maxHeight = 0.0F;
        this.temperature = 1.0F;
        this.rainfall = 0.4F;
        this.getHMCBiomeDecorator().setDeadBushPerChunk(2);
        this.getHMCBiomeDecorator().setMushroomsPerChunk(8);
        this.getHMCBiomeDecorator().setReedsPerChunk(4);
        this.getHMCBiomeDecorator().setClayPerChunk(1);
        this.getHMCBiomeDecorator().setTreesPerChunk(3);
        this.getHMCBiomeDecorator().setWaterlilyPerChunk(6);
        this.waterColorMultiplier = 7089196;
        this.getHMCBiomeDecorator().canopyPerChunk = -999;
        this.getHMCBiomeDecorator().lavaPoolChance = 0.125F;
        this.getHMCBiomeDecorator().mangrovesPerChunk = 3;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random var1)
    {
        return (WorldGenerator)(var1.nextInt(3) == 0 ? new WorldGenShrub(3, 0) : this.worldGeneratorSwamp);
    }

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        WorldGenVines var5 = new WorldGenVines();
        int var7;
        byte var8;
        int var9;

        for (int var6 = 0; var6 < 50; ++var6)
        {
            var7 = var3 + var2.nextInt(16) + 8;
            var8 = (byte)HMCWorld.SEALEVEL;
            var9 = var4 + var2.nextInt(16) + 8;
            var5.generate(var1, var2, var7, var8, var9);
        }
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 5713443;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 6563343;
    }
}
