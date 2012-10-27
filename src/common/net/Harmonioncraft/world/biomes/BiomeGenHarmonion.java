package net.Harmonioncraft.world.biomes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.SpawnListEntry;

public class BiomeGenHarmonion extends BiomeGenBase
{
    public static BiomeGenHarmonion[] hmcBiomeList = new BiomeGenHarmonion[256];
    public byte beachTopBlock;
    public byte beachFillerBlock;
    public boolean beachLayerLowered;
    public byte beachAlternateTopBlock;
    public byte beachAlternateFillerBlock;
    public boolean beachAlternateLayerLowered;
    public byte underwaterTopBlock;
    public byte underwaterFillerBlock;
    public static final BiomeGenHarmonion HarmonionOcean = (new BiomeGenHarmonionOcean(60)).setBiomeName("Harmonion Ocean").setColor(9285632).setMinMaxHeight(-1.0F, 0.1F).setTemperatureRainfall(0.8F, 0.5F);
    public static final BiomeGenHarmonion Harmonion = (new BiomeGenHarmonion(61)).setBiomeName("Harmonion").setColor(9286496).setMinMaxHeight(0.05F, 0.3F).setTemperatureRainfall(0.8F, 0.5F);
    public static final BiomeGenHarmonion rainforestPlains = (new BiomeGenHarmonionRainforest(62)).setBiomeName("Rainforest Plains").setColor(1148975).setMinMaxHeight(0.4F, 0.5F).setTemperatureRainfall(0.6F, 0.8F);
    public static final BiomeGenHarmonion rainforestHills = (new BiomeGenHarmonionRainforest(63)).setBiomeName("Rainforest Hills").setColor(1148975).setMinMaxHeight(0.4F, 0.9F).setTemperatureRainfall(0.6F, 0.8F);
    public static final BiomeGenHarmonion rainforestMountains = (new BiomeGenHarmonionRainforest(64)).setBiomeName("Rainforest Mountains").setColor(1148975).setMinMaxHeight(0.5F, 1.3F).setTemperatureRainfall(0.6F, 0.8F);
    public static final BiomeGenHarmonion HarmonionRiver = (new BiomeGenHarmonionOcean(65)).setBiomeName("Harmonion River").setColor(9285632).setMinMaxHeight(-0.7F, 0.0F).setTemperatureRainfall(0.8F, 0.5F);
    public static final BiomeGenHarmonion beach = (new BiomeGenHarmonionBeach(66)).setBiomeName("Beach").setColor(9285632).setMinMaxHeight(0.1F, -0.1F).setTemperatureRainfall(0.7F, 0.6F);
    public static final BiomeGenHarmonion lake = (new BiomeGenHarmonionOcean(67)).setBiomeName("Lake").setColor(9285632).setMinMaxHeight(-0.6F, 0.1F).setTemperatureRainfall(0.8F, 0.5F);
    public static List rainforestBiomes = Arrays.asList(new BiomeGenHarmonion[] {rainforestPlains, rainforestHills, rainforestMountains});
    private static ArrayList mobnames;
    private static ArrayList classnames;
    private static ArrayList spawnrates;
    private static ArrayList creaturetypes;
    private static ArrayList othermobnames;
    private static ArrayList otherclassnames;
    private int poisonredfrograrity;
    private int poisonyellowfrograrity;
    private int poisonbluefrograrity;
    private int treefrograrity;
    private int MOWrarity;
    private int EIHrarity;
    private int iggyrarity;
    private int starfishrarity;
    private int marlinrarity;
    private int turtlerarity;
    private int seagullrarity;
    private int vmonkeyrarity;
    private int ashenrarity;
    private int tropfishrarity;

    protected BiomeGenHarmonion(int var1)
    {
        super(var1);
        this.beachTopBlock = (byte)Block.sand.blockID;
        this.beachFillerBlock = (byte)Block.sandStone.blockID;
        this.beachLayerLowered = false;
        this.beachAlternateTopBlock = (byte)Block.sand.blockID;
        this.beachAlternateFillerBlock = (byte)Block.sandStone.blockID;
        this.beachAlternateLayerLowered = false;
        this.underwaterTopBlock = (byte)Block.dirt.blockID;
        this.underwaterFillerBlock = (byte)Block.dirt.blockID;
        this.poisonredfrograrity = 5;
        this.poisonyellowfrograrity = 5;
        this.poisonbluefrograrity = 5;
        this.treefrograrity = 7;
        this.MOWrarity = 20;
        this.EIHrarity = 10;
        this.iggyrarity = 15;
        this.starfishrarity = 30;
        this.marlinrarity = 24;
        this.turtlerarity = 24;
        this.seagullrarity = 10;
        this.vmonkeyrarity = 30;
        this.ashenrarity = 30;
        this.tropfishrarity = 10;
        mobnames = new ArrayList();
        classnames = new ArrayList();
        spawnrates = new ArrayList();
        creaturetypes = new ArrayList();
        othermobnames = new ArrayList();
        otherclassnames = new ArrayList();
        hmcBiomeList[var1] = this;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
    }

    /**
     * Returns the correspondent list of the EnumCreatureType informed.
     */
    public List getSpawnableList(EnumCreatureType var1)
    {
        return var1 == EnumCreatureType.monster ? this.spawnableMonsterList : (var1 == EnumCreatureType.creature ? this.spawnableCreatureList : (var1 == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : null));
    }

    private BiomeGenHarmonion setTemperatureRainfall(float var1, float var2)
    {
        if (var1 > 0.1F && var1 < 0.2F)
        {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        }
        else
        {
            this.temperature = var1;
            this.rainfall = var2;
            return this;
        }
    }

    /**
     * returns the chance a creature has to spawn.
     */
    public float getSpawningChance()
    {
        return 0.1F;
    }

    private BiomeGenHarmonion setMinMaxHeight(float var1, float var2)
    {
        this.minHeight = var1;
        this.maxHeight = var2;
        return this;
    }

    protected BiomeGenHarmonion setBiomeName(String var1)
    {
        this.biomeName = var1;
        return this;
    }

    protected BiomeGenHarmonion func_76733_a(int var1)
    {
        this.field_76754_C = var1;
        return this;
    }

    protected BiomeGenHarmonion setColor(int var1)
    {
        this.color = var1;
        return this;
    }

    /**
     * takes temperature, returns color
     */
    public int getSkyColorByTemp(float var1)
    {
        var1 /= 3.0F;

        if (var1 < -1.0F)
        {
            var1 = -1.0F;
        }

        if (var1 > 1.0F)
        {
            var1 = 1.0F;
        }

        return Color.getHSBColor(0.6322222F - var1 * 0.05F, 0.5F + var1 * 0.1F, 1.0F).getRGB();
    }

    protected void addMobWithNaturalSpawn(Class var1, int var2, int var3, int var4)
    {
        this.spawnableCreatureList.add(new SpawnListEntry(var1, var4, var2, var3));
    }

    protected void addMonsterWithNaturalSpawn(Class var1, int var2, int var3, int var4)
    {
        this.spawnableMonsterList.add(new SpawnListEntry(var1, var4, var2, var3));
    }

    protected void addWaterMobWithNaturalSpawn(Class var1, int var2, int var3, int var4)
    {
        this.spawnableWaterCreatureList.add(new SpawnListEntry(var1, var4, var2, var3));
    }
}
