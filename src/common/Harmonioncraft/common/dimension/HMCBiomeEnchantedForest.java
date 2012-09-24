package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeEnchantedForest extends HMCBiomeBase
{
    Random colorRNG = new Random();

    public HMCBiomeEnchantedForest(int var1)
    {
        super(var1);
        this.getHMCBiomeDecorator().setGrassPerChunk(15);
        this.getHMCBiomeDecorator().setFlowersPerChunk(8);
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return (ColorizerGrass.getGrassColor((double)this.colorRNG.nextFloat(), (double)this.colorRNG.nextFloat()) & 16776960) + this.colorRNG.nextInt(256);
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return (ColorizerGrass.getGrassColor((double)this.colorRNG.nextFloat(), (double)this.colorRNG.nextFloat()) & 16776960) + this.colorRNG.nextInt(256);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random var1)
    {
        return var1.nextInt(3) > 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        WorldGenVines var5 = new WorldGenVines();

        for (int var6 = 0; var6 < 20; ++var6)
        {
            int var7 = var3 + var2.nextInt(16) + 8;
            byte var8 = (byte)HMCWorld.SEALEVEL;
            int var9 = var4 + var2.nextInt(16) + 8;
            var5.generate(var1, var2, var7, var8, var9);
        }
    }
}
