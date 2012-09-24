package Harmonioncraft.common.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.EntityCreeper;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeSwamp extends HMCBiomeBase
{
    private static final int MONSTER_SPAWN_RATE = 20;
    Random monsterRNG = new Random(53439L);

    public HMCBiomeSwamp(int var1)
    {
        super(var1);
        this.minHeight = -0.25F;
        this.maxHeight = 0.0F;
        this.temperature = 0.8F;
        this.rainfall = 0.9F;
        this.getHMCBiomeDecorator().setDeadBushPerChunk(1);
        this.getHMCBiomeDecorator().setMushroomsPerChunk(8);
        this.getHMCBiomeDecorator().setReedsPerChunk(10);
        this.getHMCBiomeDecorator().setClayPerChunk(1);
        this.getHMCBiomeDecorator().setTreesPerChunk(2);
        this.getHMCBiomeDecorator().setWaterlilyPerChunk(6);
        this.waterColorMultiplier = 14745518;
        this.getHMCBiomeDecorator().canopyPerChunk = -999;
        this.getHMCBiomeDecorator().lakesPerChunk = 2;
        this.getHMCBiomeDecorator().mangrovesPerChunk = 3;
        //this.spawnableMonsterList.add(new SpawnListEntry(EntityTFMosquitoSwarm.class, 10, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
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

        for (int var6 = 0; var6 < 50; ++var6)
        {
            int var7 = var3 + var2.nextInt(16) + 8;
            byte var8 = (byte)HMCWorld.SEALEVEL;
            int var9 = var4 + var2.nextInt(16) + 8;
            var5.generate(var1, var2, var7, var8, var9);
        }
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 5115470) / 2;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 5115470) / 2;
    }

    /**
     * Returns the correspondent list of the EnumCreatureType informed.
     */
    public List getSpawnableList(EnumCreatureType var1)
    {
        return var1 == EnumCreatureType.monster ? (this.monsterRNG.nextInt(20) == 0 ? this.spawnableMonsterList : null) : (var1 == EnumCreatureType.creature ? this.spawnableCreatureList : (var1 == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : null));
    }
}
