package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenBigMushroom;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public abstract class HMCBiomeBase extends BiomeGenBase {

	public static final BiomeGenBase tfLake = (new TFBiomeTwilightOcean(TwilightForestMod.idBiomeLake)).setColor(255).setBiomeName("Twilight Lake");
    public static final BiomeGenBase twilightForest = (new TFBiomeTwilightForest(TwilightForestMod.idBiomeTwilightForest)).setColor(21760).setBiomeName("Twilight Forest");
    public static final BiomeGenBase twilightForest2 = (new TFBiomeTwilightForestVariant(TwilightForestMod.idBiomeTwilightForestVariant)).setColor(21794).setBiomeName("Dense Twilight Forest");
    public static final BiomeGenBase highlands = (new TFBiomeHighlands(TwilightForestMod.idBiomeHighlands)).setColor(5596740).setBiomeName("Highlands");
    public static final BiomeGenBase mushrooms = (new TFBiomeMushrooms(TwilightForestMod.idBiomeMushrooms)).setColor(4482594).setBiomeName("Mushrooms");
    public static final BiomeGenBase swamp = (new TFBiomeSwamp(TwilightForestMod.idBiomeSwamp)).setColor(3359778).setBiomeName("Twilight Swamp");
    public static final BiomeGenBase stream = (new TFBiomeStream(TwilightForestMod.idBiomeStream)).setColor(3298231).setBiomeName("Twilight Stream");
    public static final BiomeGenBase snow = (new TFBiomeSnow(TwilightForestMod.idBiomeSnowfield)).setColor(13434879).setBiomeName("Snowfield");
    public static final BiomeGenBase glacier = (new TFBiomeGlacier(TwilightForestMod.idBiomeGlacier)).setColor(7829435).setBiomeName("Glacier");
    public static final BiomeGenBase clearing = (new TFBiomeClearing(TwilightForestMod.idBiomeClearing)).setColor(3447604).setBiomeName("Twilight Clearing");
    public static final BiomeGenBase clearingBorder = (new TFBiomeTwilightForest(TwilightForestMod.idBiomeClearingBorder)).setColor(26112).setBiomeName("Clearing Border");
    public static final BiomeGenBase lakeBorder = (new TFBiomeTwilightForest(TwilightForestMod.idBiomeLakeBorder)).setColor(26163).setBiomeName("Lake Border");
    public static final BiomeGenBase deepMushrooms = (new TFBiomeDeepMushrooms(TwilightForestMod.idBiomeDeepMushrooms)).setColor(6697762).setBiomeName("Lots of Mushrooms");
    public static final BiomeGenBase majorFeature = (new TFBiomeCenter(TwilightForestMod.idBiomeMajorFeature)).setColor(16711680).setBiomeName("Major Feature");
    public static final BiomeGenBase minorFeature = (new TFBiomeCenter2(TwilightForestMod.idBiomeMinorFeature)).setColor(11184640).setBiomeName("Minor Feature");
    public static final BiomeGenBase darkForest = (new TFBiomeDarkForest(TwilightForestMod.idBiomeDarkForest)).setColor(13073).setBiomeName("Dark Forest");
    public static final BiomeGenBase enchantedForest = (new TFBiomeEnchantedForest(TwilightForestMod.idBiomeEnchantedForest)).setColor(1135974).setBiomeName("Enchanted Forest");
    public static final BiomeGenBase fireSwamp = (new TFBiomeFireSwamp(TwilightForestMod.idBiomeFireSwamp)).setColor(4334362).setBiomeName("Fire Swamp");
    protected WorldGenBigMushroom bigMushroomGen = new WorldGenBigMushroom();
	
	protected HMCBiomeBase(int var1) {
		super(var1);
	}
	

    protected HMCBiomeBase setColor(int var1)
    {
        return (HMCBiomeBase)super.setColor(var1);
    }

    /**
     * returns the chance a creature has to spawn.
     */
    public float getSpawningChance()
    {
        return 0.12F;
    }

    /**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     */
    protected BiomeDecorator createBiomeDecorator()
    {
        return new HMCBiomeDecorator(this);
    }

    protected HMCBiomeDecorator getHMCBiomeDecorator()
    {
        return (HMCBiomeDecorator)this.theBiomeDecorator;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random var1)
    {
        return (WorldGenerator)(var1.nextInt(5) == 0 ? this.worldGeneratorForest : (var1.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForGrass(Random var1)
    {
        return var1.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Block.tallGrass.blockID, 1);
    }

    public static boolean isFeature(int var0)
    {
        return var0 == majorFeature.biomeID || var0 == minorFeature.biomeID;
    }
}
