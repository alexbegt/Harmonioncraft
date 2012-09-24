package Harmonioncraft.common.dimension;

import java.util.Random;

import Harmonioncraft.common.lib.BiomeIds;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenBigMushroom;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public abstract class HMCBiomeBase extends BiomeGenBase {

	public static final BiomeGenBase HMCLake = (new HMCBiomeOcean(BiomeIds.idBiomeLake)).setColor(255).setBiomeName("HMC Lake");
    public static final BiomeGenBase HMC = (new HMCBiomeForest(BiomeIds.idBiomeHMC)).setColor(21760).setBiomeName("HMC Forest");
    public static final BiomeGenBase HMC2 = (new HMCBiomeForestVariant(BiomeIds.idBiomeHMCVariant)).setColor(21794).setBiomeName("Dense HMC Forest");
    public static final BiomeGenBase highlands = (new HMCBiomeHighlands(BiomeIds.idBiomeHighlands)).setColor(5596740).setBiomeName("Highlands");
    public static final BiomeGenBase mushrooms = (new HMCBiomeMushrooms(BiomeIds.idBiomeMushrooms)).setColor(4482594).setBiomeName("Mushrooms");
    public static final BiomeGenBase swamp = (new HMCBiomeSwamp(BiomeIds.idBiomeSwamp)).setColor(3359778).setBiomeName("HMC Swamp");
    public static final BiomeGenBase stream = (new HMCBiomeStream(BiomeIds.idBiomeStream)).setColor(3298231).setBiomeName("HMC Stream");
    public static final BiomeGenBase snow = (new HMCBiomeSnow(BiomeIds.idBiomeSnowfield)).setColor(13434879).setBiomeName("Snowfield");
    public static final BiomeGenBase glacier = (new HMCBiomeGlacier(BiomeIds.idBiomeGlacier)).setColor(7829435).setBiomeName("Glacier");
    public static final BiomeGenBase clearing = (new HMCBiomeClearing(BiomeIds.idBiomeClearing)).setColor(3447604).setBiomeName("HMC Clearing");
    public static final BiomeGenBase clearingBorder = (new HMCBiomeForest(BiomeIds.idBiomeClearingBorder)).setColor(26112).setBiomeName("Clearing Border");
    public static final BiomeGenBase lakeBorder = (new HMCBiomeForest(BiomeIds.idBiomeLakeBorder)).setColor(26163).setBiomeName("Lake Border");
    public static final BiomeGenBase deepMushrooms = (new HMCBiomeDeepMushrooms(BiomeIds.idBiomeDeepMushrooms)).setColor(6697762).setBiomeName("Lots of Mushrooms");
    public static final BiomeGenBase majorFeature = (new HMCBiomeCenter(BiomeIds.idBiomeMajorFeature)).setColor(16711680).setBiomeName("Major Feature");
    public static final BiomeGenBase minorFeature = (new HMCBiomeCenter2(BiomeIds.idBiomeMinorFeature)).setColor(11184640).setBiomeName("Minor Feature");
    public static final BiomeGenBase darkForest = (new HMCBiomeDarkForest(BiomeIds.idBiomeDarkForest)).setColor(13073).setBiomeName("Dark Forest");
    public static final BiomeGenBase enchantedForest = (new HMCBiomeEnchantedForest(BiomeIds.idBiomeEnchantedForest)).setColor(1135974).setBiomeName("Enchanted Forest");
    public static final BiomeGenBase fireSwamp = (new HMCBiomeFireSwamp(BiomeIds.idBiomeFireSwamp)).setColor(4334362).setBiomeName("Fire Swamp");
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
