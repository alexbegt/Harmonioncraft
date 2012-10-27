package net.Harmonioncraft.world.chunkprovider;

import java.util.List;
import java.util.Random;

import net.Harmonioncraft.block.ModBlocks;
import net.Harmonioncraft.world.biomes.BiomeGenHarmonion;
import net.Harmonioncraft.world.mapgen.MapGenHMCCaves;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NoiseGeneratorOctaves;
import net.minecraft.src.SpawnerAnimals;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenClay;
import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.WorldGenReed;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class ChunkProviderHMC implements IChunkProvider
{
    private Random rand;
    //private MapGenKoaVillage villageGenerator = new MapGenKoaVillage();
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    protected WorldGenerator eudialyteGen;
    //protected WorldGenerator zirconGen;
    //protected WorldGenerator azuriteGen;
    protected WorldGenerator ironGen;
    protected WorldGenerator coalGen;
    protected WorldGenerator lapisGen;
    public MapGenHMCCaves cavegen;
    //public MapGenVolcano volcanoGen;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double[] noiseArray;
    public static int water;
    final int heightShift = 7;
    final int xShift = 11;
    private final int HOME_TREE_RARITY = 350;
    private BiomeGenHarmonion[] biomesForGeneration;
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    float[] field_35388_l;
    int[][] unusedIntArray32x32 = new int[32][32];

    public ChunkProviderHMC(World var1, long var2, boolean var4)
    {
        water = Block.waterStill.blockID;
        this.cavegen = new MapGenHMCCaves();
        //this.volcanoGen = new MapGenVolcano();
        this.worldObj = var1;
        this.mapFeaturesEnabled = var4;
        this.rand = new Random(var2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
        this.lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
        this.ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
        this.eudialyteGen = new WorldGenMinable(ModBlocks.HarmonionOre.blockID, 6);
    }

    public void generateTerrain(int var1, int var2, byte[] var3)
    {
        water = Block.waterStill.blockID;
        byte var4 = 4;
        byte var5 = 16;
        byte var6 = 63;
        int var7 = var4 + 1;
        byte var8 = 17;
        int var9 = var4 + 1;
        this.biomesForGeneration = (BiomeGenHarmonion[])((BiomeGenHarmonion[])this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, var1 * 4 - 2, var2 * 4 - 2, var7 + 5, var9 + 5));
        this.noiseArray = this.initializeNoiseField(this.noiseArray, var1 * var4, 0, var2 * var4, var7, var8, var9);

        for (int var10 = 0; var10 < var4; ++var10)
        {
            for (int var11 = 0; var11 < var4; ++var11)
            {
                for (int var12 = 0; var12 < var5; ++var12)
                {
                    double var13 = 0.125D;
                    double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

                    for (int var31 = 0; var31 < 8; ++var31)
                    {
                        double var32 = 0.25D;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;

                        for (int var42 = 0; var42 < 4; ++var42)
                        {
                            int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
                            short var44 = 128;
                            var43 -= var44;
                            double var45 = 0.25D;
                            double var47 = (var36 - var34) * var45;
                            double var49 = var34 - var47;

                            for (int var51 = 0; var51 < 4; ++var51)
                            {
                                if ((var49 += var47) > 0.0D)
                                {
                                    var3[var43 += var44] = (byte)Block.stone.blockID;
                                }
                                else if (var12 * 8 + var31 < var6)
                                {
                                    var3[var43 += var44] = (byte)(Block.waterStill.blockID & 255);
                                }
                                else
                                {
                                    var3[var43 += var44] = 0;
                                }
                            }

                            var34 += var38;
                            var36 += var40;
                        }

                        var15 += var23;
                        var17 += var25;
                        var19 += var27;
                        var21 += var29;
                    }
                }
            }
        }
    }

    public void replaceBlocksForBiome(int var1, int var2, byte[] var3, BiomeGenHarmonion[] var4)
    {
        int var5 = -1;
        water = Block.waterStill.blockID;
        boolean var6 = false;
        boolean var7 = true;
        double var8 = 0.03125D;

        for (int var10 = 0; var10 < 16; ++var10)
        {
            for (int var11 = 0; var11 < 16; ++var11)
            {
                BiomeGenHarmonion var12 = var4[var11 + var10 * 16];
                boolean var13 = true;
                byte var14 = var12.topBlock;
                byte var15 = var12.fillerBlock;
                byte var16 = var12.beachTopBlock;
                byte var17 = var12.beachFillerBlock;
                boolean var18 = var12.beachLayerLowered;
                int var19 = this.rand.nextInt(2) + 2;

                for (int var20 = 127; var20 >= 0; --var20)
                {
                    int var21 = (var11 * 16 + var10) * 128 + var20;
                    byte var22 = var3[var21];

                    if (var20 <= 0)
                    {
                        var3[var21] = (byte)Block.bedrock.blockID;
                    }
                    else if (var22 != 0 && var22 != (byte)(Block.waterStill.blockID & 255))
                    {
                        if (var5 >= 0 && var5 < 5)
                        {
                            byte var23 = (byte)Block.stone.blockID;

                            if (var5 == 0 && var20 < 66)
                            {
                                var6 = true;
                            }

                            if (var6)
                            {
                                if (var16 != Block.sand.blockID)
                                {
                                    if (var5 == 0)
                                    {
                                        var23 = var16;
                                    }
                                    else if (var5 < 5)
                                    {
                                        var23 = var17;
                                    }
                                }
                                else if (var5 < var19)
                                {
                                    var23 = var16;
                                }
                                else if (var5 < 5)
                                {
                                    var23 = var17;
                                }
                            }
                            else if (var14 != Block.sand.blockID)
                            {
                                if (var5 == 0)
                                {
                                    var23 = var14;
                                }
                                else if (var5 < 5)
                                {
                                    var23 = var15;
                                }
                            }
                            else if (var5 < var19)
                            {
                                var23 = var14;
                            }
                            else if (var5 < 5)
                            {
                                var23 = var15;
                            }

                            var3[var21] = var23;
                            ++var5;
                        }
                        else
                        {
                            var6 = false;
                            var5 = -1;
                        }
                    }
                    else
                    {
                        var5 = 0;
                    }
                }

                var5 = -1;
            }
        }
    }

    /**
     * loads or generates the chunk at the chunk location specified
     */
    public Chunk loadChunk(int var1, int var2)
    {
        return this.provideChunk(var1, var2);
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int var1, int var2)
    {
        this.rand.setSeed((long)var1 * 341873128712L + (long)var2 * 132897987541L);
        byte[] var3 = new byte[32768];
        this.generateTerrain(var1, var2, var3);
        this.biomesForGeneration = (BiomeGenHarmonion[])((BiomeGenHarmonion[])this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, var1 * 16, var2 * 16, 16, 16));
        this.replaceBlocksForBiome(var1, var2, var3, this.biomesForGeneration);

        //if (TropicraftMod.generateVolcanoes)
        //{
        //    this.volcanoGen.generate(this.worldObj, var1, var2, var3);
        //}

        this.cavegen.generate(this, this.worldObj, var1, var2, var3);
        //this.villageGenerator.generate(this, this.worldObj, var1, var2, var3);
        Chunk var4 = new Chunk(this.worldObj, var3, var1, var2);
        var4.generateSkylightMap();
        return var4;
    }

    private double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        if (var1 == null)
        {
            var1 = new double[var5 * var6 * var7];
        }

        if (this.field_35388_l == null)
        {
            this.field_35388_l = new float[25];

            for (int var8 = -2; var8 <= 2; ++var8)
            {
                for (int var9 = -2; var9 <= 2; ++var9)
                {
                    float var10 = 10.0F / MathHelper.sqrt_float((float)(var8 * var8 + var9 * var9) + 0.2F);
                    this.field_35388_l[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }

        double var44 = 684.412D;
        double var45 = 684.412D;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, var2, var4, var5, var7, 1.121D, 1.121D, 0.5D);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, var2, var4, var5, var7, 200.0D, 200.0D, 0.5D);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, var2, var3, var4, var5, var6, var7, var44 / 80.0D, var45 / 30.0D, var44 / 80.0D);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, var2, var3, var4, var5, var6, var7, var44, var45 / 20.0D, var44);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, var2, var3, var4, var5, var6, var7, var44, var45, var44);
        boolean var12 = false;
        boolean var13 = false;
        int var14 = 0;
        int var15 = 0;

        for (int var16 = 0; var16 < var5; ++var16)
        {
            for (int var17 = 0; var17 < var7; ++var17)
            {
                float var18 = 0.0F;
                float var19 = 0.0F;
                float var20 = 0.0F;
                byte var21 = 2;
                BiomeGenHarmonion var22 = this.biomesForGeneration[var16 + 2 + (var17 + 2) * (var5 + 5)];

                for (int var23 = -var21; var23 <= var21; ++var23)
                {
                    for (int var24 = -var21; var24 <= var21; ++var24)
                    {
                        BiomeGenHarmonion var25 = this.biomesForGeneration[var16 + var23 + 2 + (var17 + var24 + 2) * (var5 + 5)];
                        float var26 = this.field_35388_l[var23 + 2 + (var24 + 2) * 5] / (var25.minHeight + 2.0F);

                        if (var25.minHeight > var22.minHeight)
                        {
                            var26 /= 2.0F;
                        }

                        var18 += var25.maxHeight * var26;
                        var19 += var25.minHeight * var26;
                        var20 += var26;
                    }
                }

                var18 /= var20;
                var19 /= var20;
                var18 = var18 * 0.9F + 0.1F;
                var19 = (var19 * 4.0F - 1.0F) / 8.0F;
                double var47 = this.noise6[var15] / 8000.0D;

                if (var47 < 0.0D)
                {
                    var47 = -var47 * 0.3D;
                }

                var47 = var47 * 3.0D - 2.0D;

                if (var47 < 0.0D)
                {
                    var47 /= 2.0D;

                    if (var47 < -1.0D)
                    {
                        var47 = -1.0D;
                    }

                    var47 /= 1.4D;
                    var47 /= 2.0D;
                }
                else
                {
                    if (var47 > 1.0D)
                    {
                        var47 = 1.0D;
                    }

                    var47 /= 8.0D;
                }

                ++var15;

                for (int var46 = 0; var46 < var6; ++var46)
                {
                    double var48 = (double)var19;
                    double var28 = (double)var18;
                    var48 += var47 * 0.2D;
                    var48 = var48 * (double)var6 / 16.0D;
                    double var30 = (double)var6 / 2.0D + var48 * 4.0D;
                    double var32 = 0.0D;
                    double var34 = ((double)var46 - var30) * 12.0D * 128.0D / 128.0D / var28;

                    if (var34 < 0.0D)
                    {
                        var34 *= 4.0D;
                    }

                    double var36 = this.noise1[var14] / 512.0D;
                    double var38 = this.noise2[var14] / 512.0D;
                    double var40 = (this.noise3[var14] / 10.0D + 1.0D) / 2.0D;

                    if (var40 < 0.0D)
                    {
                        var32 = var36;
                    }
                    else if (var40 > 1.0D)
                    {
                        var32 = var38;
                    }
                    else
                    {
                        var32 = var36 + (var38 - var36) * var40;
                    }

                    var32 -= var34;

                    if (var46 > var6 - 4)
                    {
                        double var42 = (double)((float)(var46 - (var6 - 4)) / 3.0F);
                        var32 = var32 * (1.0D - var42) + -10.0D * var42;
                    }

                    var1[var14] = var32;
                    ++var14;
                }
            }
        }

        return var1;
    }

    protected void generateOres(int var1, int var2)
    {
        this.genStandardOre1(19, this.coalGen, 0, 128, var1, var2);
        this.genStandardOre1(10, this.ironGen, 0, 64, var1, var2);
        //this.genStandardOre1(15, this.zirconGen, 0, 32, var1, var2);
        this.genStandardOre1(20, this.eudialyteGen, 0, 64, var1, var2);
        //this.genStandardOre1(10, this.azuriteGen, 0, 128, var1, var2);
        this.genStandardOre2(1, this.lapisGen, 16, 16, var1, var2);
    }

    protected void genStandardOre1(int var1, WorldGenerator var2, int var3, int var4, int var5, int var6)
    {
        for (int var7 = 0; var7 < var1; ++var7)
        {
            int var8 = var5 + this.rand.nextInt(16);
            int var9 = this.rand.nextInt(var4 - var3) + var3;
            int var10 = var6 + this.rand.nextInt(16);
            var2.generate(this.worldObj, this.rand, var8, var9, var10);
        }
    }

    protected void genStandardOre2(int var1, WorldGenerator var2, int var3, int var4, int var5, int var6)
    {
        for (int var7 = 0; var7 < var1; ++var7)
        {
            int var8 = var5 + this.rand.nextInt(16);
            int var9 = this.rand.nextInt(var4) + this.rand.nextInt(var4) + (var3 - var4);
            int var10 = var6 + this.rand.nextInt(16);
            var2.generate(this.worldObj, this.rand, var8, var9, var10);
        }
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int var1, int var2)
    {
        return true;
    }

    private boolean isRainforestBiome(BiomeGenBase var1)
    {
        return var1 == BiomeGenHarmonion.rainforestHills || var1 == BiomeGenHarmonion.rainforestMountains || var1 == BiomeGenHarmonion.rainforestPlains;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider var1, int var2, int var3)
    {
        BlockSand.fallInstantly = true;
        int var4 = var2 * 16;
        int var5 = var3 * 16;
        BiomeGenHarmonion var6 = (BiomeGenHarmonion)this.worldObj.getWorldChunkManager().getBiomeGenAt(var4, var5);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)var2 * var7 + (long)var3 * var9 ^ this.worldObj.getSeed());
        //this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, var2, var3);
        this.cavegen.generateWater(this.worldObj, var2, var3);

        this.generateOres(var4, var5);
        (new WorldGenTallGrass(Block.tallGrass.blockID, 1)).generate(this.worldObj, this.rand, var4 + 6 + this.rand.nextInt(4), this.rand.nextInt(15) + 64, var5 + 6 + this.rand.nextInt(4));
        int var13;
        int var14;
        int var18;

        int var15;

        int var17;
        int var16;

        for (var14 = 0; var14 < 10; ++var14)
        {
            var15 = var4 + this.rand.nextInt(16) + 8;
            var16 = this.rand.nextInt(128);
            var17 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenReed()).generate(this.worldObj, this.rand, var15, var16, var17);
        }

        var14 = var4 + this.rand.nextInt(16) + 8;
        var15 = this.rand.nextInt(62) + 64;
        var16 = var5 + this.rand.nextInt(16) + 8;

        if (this.rand.nextBoolean())
        {
            var18 = this.rand.nextInt(16) + var4 + 8;
            var13 = this.rand.nextInt(16) + var5 + 8;
            (new WorldGenClay(this.rand.nextInt(9) + 12)).generate(this.worldObj, this.rand, var18, this.rand.nextInt(16) + 56, var13);
        }

        this.worldObj.getChunkFromChunkCoords(var2, var3).updateSkylight();
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
        BlockSand.fallInstantly = false;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean var1, IProgressUpdate var2)
    {
        return true;
    }

    /**
     * Unloads the 100 oldest chunks from memory, due to a bug with chunkSet.add() never being called it thinks the list
     * is always empty and will not remove any chunks.
     */
    public boolean unload100OldestChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4)
    {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(var2, var4);
        return var5 == null ? null : var5.getSpawnableList(var1);
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World var1, String var2, int var3, int var4, int var5)
    {
        return null;
    }

    int getTerrainHeightAt(int var1, int var2)
    {
        for (int var3 = 128; var3 > 0; --var3)
        {
            int var4 = this.worldObj.getBlockId(var1, var3, var2);

            if (var4 != 0 && var4 != Block.tallGrass.blockID && var4 != Block.snow.blockID && var4 != Block.leaves.blockID && var4 != Block.wood.blockID)
            {
                return var3 + 1;
            }
        }

        return 0;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

	@Override
	public String makeString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void func_82695_e(int var1, int var2) {
		// TODO Auto-generated method stub
		
	}
}
