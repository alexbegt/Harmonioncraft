package Harmonioncraft.common.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenerator;

public class HMCBiomeDarkForest extends HMCBiomeBase
{
    private static final int MONSTER_SPAWN_RATE = 20;
    Random monsterRNG;

    public HMCBiomeDarkForest(int var1)
    {
        super(var1);
        this.temperature = 0.7F;
        this.rainfall = 0.8F;
        this.getHMCBiomeDecorator().canopyPerChunk = 5;
        this.getHMCBiomeDecorator().setTreesPerChunk(10);
        this.getHMCBiomeDecorator().setGrassPerChunk(-99);
        this.getHMCBiomeDecorator().setFlowersPerChunk(-99);
        this.getHMCBiomeDecorator().setMushroomsPerChunk(1);
        this.getHMCBiomeDecorator().setDeadBushPerChunk(10);
        this.minHeight = 0.05F;
        this.maxHeight = 0.05F;
        this.monsterRNG = new Random();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 5, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 5, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 1, 4));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random var1)
    {
        return (WorldGenerator)(var1.nextInt(5) == 0 ? new WorldGenShrub(3, 0) : (var1.nextInt(8) == 0 ? this.worldGeneratorForest : this.worldGeneratorTrees));
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerGrass.getGrassColor(var1, var3) & 16711422) + 1969742) / 2;
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerFoliage.getFoliageColor(var1, var3) & 16711422) + 1969742) / 2;
    }

    /**
     * Returns the correspondent list of the EnumCreatureType informed.
     */
    public List getSpawnableList(EnumCreatureType var1)
    {
        return var1 == EnumCreatureType.monster ? (this.monsterRNG.nextInt(20) == 0 ? this.spawnableMonsterList : null) : (var1 == EnumCreatureType.creature ? this.spawnableCreatureList : (var1 == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : null));
    }
}