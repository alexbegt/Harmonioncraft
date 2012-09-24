package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeGlacier extends HMCBiomeBase
{
    public HMCBiomeGlacier(int var1)
    {
        super(var1);
        this.getHMCBiomeDecorator().setTreesPerChunk(1);
        this.getHMCBiomeDecorator().setGrassPerChunk(0);
        this.temperature = 0.0F;
        this.rainfall = 0.1F;
        this.getHMCBiomeDecorator().canopyPerChunk = -999;
        //this.spawnableCreatureList.add(new SpawnListEntry(EntityTFPenguin.class, 10, 4, 4));
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
    /**
    public void decorate(World var1, Random var2, int var3, int var4)
    {
        super.decorate(var1, var2, var3, var4);
        TFGenPenguins var5 = new TFGenPenguins();

        if (var2.nextInt(4) == 0)
        {
            int var6 = var3 + var2.nextInt(16) + 8;
            byte var7 = (byte)TFWorld.SEALEVEL;
            int var8 = var4 + var2.nextInt(16) + 8;
            var5.generate(var1, var2, var6, var7, var8);
        }
    }*/
}