package Harmonioncraft.common.dimension;

import java.util.List;
import java.util.Random;

import Harmonioncraft.common.block.ModBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSand;
import net.minecraft.src.Chunk;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.ExtendedBlockStorage;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.IProgressUpdate;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NoiseGeneratorOctaves;
import net.minecraft.src.SpawnerAnimals;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDungeons;
import net.minecraft.src.WorldGenLakes;

public class ChunkProviderHarmonionCraft implements IChunkProvider {
	
	private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double[] landMap;
    private double[] stoneNoise = new double[256];
    private HMCGenCaves caveGenerator = new HMCGenCaves();
    public MapGenHMCStronghold strongholdGenerator = new MapGenHMCStronghold();
    private HMCGenRavine ravineGenerator = new HMCGenRavine();
    private BiomeGenBase[] biomesForGeneration;
    double[] noise3;
    double[] noise1;
    double[] noise2;
    double[] noise5;
    double[] noise6;
    float[] squareTable;
    int[][] unusedIntArray32x32 = new int[32][32];
    private MapGenHMCMajorFeature majorFeatureGenerator = new MapGenHMCMajorFeature();
    public static boolean shouldOtherModsGenerateInHMC;

    public ChunkProviderHarmonionCraft(World var1, long var2, boolean var4)
    {
        this.worldObj = var1;
        this.mapFeaturesEnabled = var4;
        this.rand = new Random(var2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
    }

    public void generateTerrain(int var1, int var2, short[] var3)
    {
        byte var4 = 4;
        byte var5 = 8;
        byte var6 = (byte)HMCWorld.SEALEVEL;
        int var7 = var4 + 1;
        byte var8 = 9;
        int var9 = var4 + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, var1 * 4 - 2, var2 * 4 - 2, var7 + 5, var9 + 5);
        this.landMap = this.makeLandPerBiome(this.landMap, var1 * var4, 0, var2 * var4, var7, var8, var9);

        for (int var10 = 0; var10 < var4; ++var10)
        {
            for (int var11 = 0; var11 < var4; ++var11)
            {
                for (int var12 = 0; var12 < var5; ++var12)
                {
                    double var13 = 0.125D;
                    double var15 = this.landMap[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.landMap[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.landMap[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.landMap[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.landMap[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.landMap[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.landMap[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.landMap[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

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
                            double var49 = (var36 - var34) * var45;
                            double var47 = var34 - var49;

                            for (int var51 = 0; var51 < 4; ++var51)
                            {
                                if ((var47 += var49) > 0.0D)
                                {
                                    var3[var43 += var44] = (short)Block.stone.blockID;
                                }
                                else if (var12 * 8 + var31 < var6)
                                {
                                    var3[var43 += var44] = (short)Block.waterStill.blockID;
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

    public void replaceBlocksForBiome(int var1, int var2, short[] var3, BiomeGenBase[] var4)
    {
        byte var5 = (byte)HMCWorld.SEALEVEL;
        double var6 = 0.03125D;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, var1 * 16, var2 * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                BiomeGenBase var10 = var4[var9 + var8 * 16];
                float var11 = var10.getFloatTemperature();
                int var12 = (int)(this.stoneNoise[var8 + var9 * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int var13 = -1;
                short var14 = (short)var10.topBlock;
                short var15 = (short)var10.fillerBlock;

                for (int var16 = 127; var16 >= 0; --var16)
                {
                    int var17 = (var9 * 16 + var8) * 128 + var16;

                    if (var16 <= 0 + this.rand.nextInt(5))
                    {
                        var3[var17] = (short)Block.bedrock.blockID;
                    }
                    else
                    {
                        short var18 = var3[var17];

                        if (var18 == Block.stone.blockID)
                        {
                            if (var13 == -1)
                            {
                                if (var12 <= 0)
                                {
                                    var14 = 0;
                                    var15 = (short)Block.stone.blockID;
                                }
                                else if (var16 >= var5 - 4 && var16 <= var5 + 1)
                                {
                                    var14 = (short)var10.topBlock;
                                    var15 = (short)var10.fillerBlock;
                                }

                                if (var16 < var5 && var14 == 0)
                                {
                                    if (var11 < 0.15F)
                                    {
                                        var14 = (short)Block.ice.blockID;
                                    }
                                    else
                                    {
                                        var14 = (short)Block.waterStill.blockID;
                                    }
                                }

                                var13 = var12;

                                if (var16 >= var5 - 1)
                                {
                                    var3[var17] = var14;
                                }
                                else
                                {
                                    var3[var17] = var15;
                                }
                            }
                            else if (var13 > 0)
                            {
                                --var13;
                                var3[var17] = var15;

                                if (var13 == 0 && var15 == Block.sand.blockID)
                                {
                                    var13 = this.rand.nextInt(4);
                                    var15 = (short)Block.sandStone.blockID;
                                }
                            }
                        }
                    }
                }
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
        short[] var3 = new short[32768];
        byte[] var4 = new byte[32768];
        this.generateTerrain(var1, var2, var3);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, var1 * 16, var2 * 16, 16, 16);
        this.addGlaciers(var1, var2, var3, var4, this.biomesForGeneration);
        this.raiseHills(var1, var2, var3);
        this.addDarkForestCanopy(var1, var2, var3, var4, this.biomesForGeneration);
        this.replaceBlocksForBiome(var1, var2, var3, this.biomesForGeneration);
        this.caveGenerator.generate(this, this.worldObj, var1, var2, var3);
        this.ravineGenerator.generate(this, this.worldObj, var1, var2, var3);
        byte[] var5 = new byte[0];

        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generate(this, this.worldObj, var1, var2, var5);
        }

        this.majorFeatureGenerator.generate(this, this.worldObj, var1, var2, var5);
        Chunk var6 = this.makeAChunk(this.worldObj, var3, var4, var1, var2);
        var6.generateSkylightMap();
        return var6;
    }

    protected Chunk makeAChunk(World var1, short[] var2, byte[] var3, int var4, int var5)
    {
        Chunk var6 = new Chunk(var1, var4, var5);
        ExtendedBlockStorage[] var7 = var6.getBlockStorageArray();
        int var8 = var2.length / 256;

        for (int var9 = 0; var9 < 16; ++var9)
        {
            for (int var10 = 0; var10 < 16; ++var10)
            {
                for (int var11 = 0; var11 < var8; ++var11)
                {
                    int var12 = var9 << 11 | var10 << 7 | var11;
                    int var13 = var2[var12] & 4095;
                    byte var14 = var3[var12];

                    if (var13 != 0)
                    {
                        int var15 = var11 >> 4;

                        if (var7[var15] == null)
                        {
                            var7[var15] = new ExtendedBlockStorage(var15 << 4);
                        }

                        var7[var15].setExtBlockID(var9, var11 & 15, var10, var13);
                        var7[var15].setExtBlockMetadata(var9, var11 & 15, var10, var14);
                    }
                }
            }
        }

        return var6;
    }

    public void raiseHills(int var1, int var2, short[] var3)
    {
        HMCFeature var4 = HMCFeature.getNearestFeature(var1, var2, this.worldObj);

        if (var4 != HMCFeature.nothing)
        {
            int[] var5 = HMCFeature.getNearestCenter(var1, var2, this.worldObj);
            double var6 = (double)(var4.size * 2 + 1) * 16.0D;
            int var8 = var5[0];
            int var9 = var5[1];

            for (int var10 = 0; var10 < 16; ++var10)
            {
                for (int var11 = 0; var11 < 16; ++var11)
                {
                    boolean var12 = true;
                    int var13 = -1;
                    int var14 = var10 - var8;
                    int var15 = var11 - var9;
                    int var16 = (int)Math.sqrt((double)(var14 * var14 + var15 * var15));
                    int var17 = (int)(Math.cos((double)var16 / var6 * Math.PI) * (var6 / 3.0D));
                    int var19;
                    int var21;
                    int var22;
                    int var25;

                    if (var4 == HMCFeature.hill1 || var4 == HMCFeature.hill2 || var4 == HMCFeature.hill3 || var4 == HMCFeature.hydraLair)
                    {
                        int var18;

                        for (var18 = 0; var18 <= 127; ++var18)
                        {
                            var19 = (var10 * 16 + var11) * HMCWorld.WORLDHEIGHT + var18;
                            short var20 = var3[var19];

                            if (var20 == 0 || var20 == Block.ice.blockID)
                            {
                                if (var13 == -1)
                                {
                                    var13 = var18 + var17;
                                }

                                if (var18 <= var13)
                                {
                                    var3[var19] = (short)Block.stone.blockID;
                                }
                            }
                        }

                        var18 = var17 - 4 - var4.size;

                        if (var4 == HMCFeature.hydraLair)
                        {
                            var19 = var14 + 16;
                            var25 = var15 + 16;
                            var21 = (int)Math.sqrt((double)(var19 * var19 + var25 * var25));
                            var22 = (int)(Math.cos((double)var21 / (var6 / 1.5D) * Math.PI) * (var6 / 1.5D));
                            var18 = Math.max(var22 - 4, var18);
                        }

                        if (var18 < 0)
                        {
                            var18 = 0;
                        }

                        for (var19 = 0; var19 <= 127; ++var19)
                        {
                            var25 = (var10 * 16 + var11) * HMCWorld.WORLDHEIGHT + var19;

                            if (var17 > 0 && var19 < HMCWorld.SEALEVEL && var3[var25] != Block.stone.blockID)
                            {
                                var3[var25] = (short)Block.stone.blockID;
                            }

                            var21 = HMCWorld.SEALEVEL - 3 - var18 / 8;

                            if (var4 == HMCFeature.hydraLair)
                            {
                                var21 = HMCWorld.SEALEVEL;
                            }

                            if (var19 > var21 && var19 < var21 + var18)
                            {
                                var3[var25] = 0;
                            }
                        }
                    }

                    if (var4 == HMCFeature.hedgeMaze || var4 == HMCFeature.nagaLair || var4 == HMCFeature.questGrove)
                    {
                        float var24 = 0.0F;
                        var19 = HMCWorld.SEALEVEL + 1;
                        var25 = (var4.size * 2 + 1) * 8 - 8;

                        if (var14 <= -var25)
                        {
                            var24 = (float)(-var14 - var25) / 8.0F;
                        }

                        if (var14 >= var25)
                        {
                            var24 = (float)(var14 - var25) / 8.0F;
                        }

                        if (var15 <= -var25)
                        {
                            var24 = Math.max(var24, (float)(-var15 - var25) / 8.0F);
                        }

                        if (var15 >= var25)
                        {
                            var24 = Math.max(var24, (float)(var15 - var25) / 8.0F);
                        }

                        if (var24 > 0.0F)
                        {
                            var13 = -1;

                            for (var21 = 0; var21 <= 127; ++var21)
                            {
                                var22 = (var10 * 16 + var11) * HMCWorld.WORLDHEIGHT + var21;
                                short var23 = var3[var22];

                                if (var23 != Block.stone.blockID && var13 == -1)
                                {
                                    var19 = (int)((float)var19 + (float)(var21 - var19) * var24);
                                    var13 = var21;
                                }
                            }
                        }

                        for (var21 = 0; var21 <= 127; ++var21)
                        {
                            var22 = (var10 * 16 + var11) * HMCWorld.WORLDHEIGHT + var21;

                            if (var21 < var19 && (var3[var22] == 0 || var3[var22] == Block.waterStill.blockID))
                            {
                                var3[var22] = (short)Block.stone.blockID;
                            }

                            if (var21 >= var19 && var3[var22] != Block.waterStill.blockID)
                            {
                                var3[var22] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    private double[] makeLandPerBiome(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        if (var1 == null)
        {
            var1 = new double[var5 * var6 * var7];
        }

        if (this.squareTable == null)
        {
            this.squareTable = new float[25];

            for (int var8 = -2; var8 <= 2; ++var8)
            {
                for (int var9 = -2; var9 <= 2; ++var9)
                {
                    float var10 = 10.0F / MathHelper.sqrt_float((float)(var8 * var8 + var9 * var9) + 0.2F);
                    this.squareTable[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }

        double var44 = 684.412D;
        double var45 = 684.412D;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, var2, var4, var5, var7, 1.121D, 1.121D, 0.5D);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, var2, var4, var5, var7, 200.0D, 200.0D, 0.5D);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, var2, var3, var4, var5, var6, var7, var44 / 80.0D, var45 / 160.0D, var44 / 80.0D);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, var2, var3, var4, var5, var6, var7, var44, var45, var44);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, var2, var3, var4, var5, var6, var7, var44, var45, var44);
        boolean var43 = false;
        boolean var42 = false;
        int var12 = 0;
        int var13 = 0;

        for (int var14 = 0; var14 < var5; ++var14)
        {
            for (int var15 = 0; var15 < var7; ++var15)
            {
                float var16 = 0.0F;
                float var17 = 0.0F;
                float var18 = 0.0F;
                byte var19 = 2;
                BiomeGenBase var20 = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (var5 + 5)];

                for (int var21 = -var19; var21 <= var19; ++var21)
                {
                    for (int var22 = -var19; var22 <= var19; ++var22)
                    {
                        BiomeGenBase var23 = this.biomesForGeneration[var14 + var21 + 2 + (var15 + var22 + 2) * (var5 + 5)];
                        float var24 = this.squareTable[var21 + 2 + (var22 + 2) * 5] / (var23.minHeight + 2.0F);

                        if (var23.minHeight > var20.minHeight)
                        {
                            var24 /= 2.0F;
                        }

                        var16 += var23.maxHeight * var24;
                        var17 += var23.minHeight * var24;
                        var18 += var24;
                    }
                }

                var16 /= var18;
                var17 /= var18;
                var16 = var16 * 0.9F + 0.1F;
                var17 = (var17 * 4.0F - 1.0F) / 8.0F;
                double var47 = this.noise6[var13] / 8000.0D;

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

                ++var13;

                for (int var46 = 0; var46 < var6; ++var46)
                {
                    double var48 = (double)var17;
                    double var26 = (double)var16;
                    var48 += var47 * 0.2D;
                    var48 = var48 * (double)var6 / 16.0D;
                    double var28 = (double)var6 / 2.0D + var48 * 4.0D;
                    double var30 = 0.0D;
                    double var32 = ((double)var46 - var28) * 12.0D * 128.0D / (double)HMCWorld.WORLDHEIGHT / var26;

                    if (var32 < 0.0D)
                    {
                        var32 *= 4.0D;
                    }

                    double var34 = this.noise1[var12] / 512.0D;
                    double var36 = this.noise2[var12] / 512.0D;
                    double var38 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;

                    if (var38 < 0.0D)
                    {
                        var30 = var34;
                    }
                    else if (var38 > 1.0D)
                    {
                        var30 = var36;
                    }
                    else
                    {
                        var30 = var34 + (var36 - var34) * var38;
                    }

                    var30 -= var32;

                    if (var46 > var6 - 4)
                    {
                        double var40 = (double)((float)(var46 - (var6 - 4)) / 3.0F);
                        var30 = var30 * (1.0D - var40) + -10.0D * var40;
                    }

                    var1[var12] = var30;
                    ++var12;
                }
            }
        }

        return var1;
    }

    public void addGlaciers(int var1, int var2, short[] var3, byte[] var4, BiomeGenBase[] var5)
    {
        for (int var6 = 0; var6 < 16; ++var6)
        {
            for (int var7 = 0; var7 < 16; ++var7)
            {
                BiomeGenBase var8 = var5[var7 + var6 * 16];

                if (var8 == HMCBiomeBase.glacier)
                {
                    int var9 = -1;
                    int var11;

                    for (int var10 = 127; var10 >= 0; --var10)
                    {
                        var11 = (var7 * 16 + var6) * 128 + var10;
                        short var12 = var3[var11];

                        if (var12 == Block.stone.blockID)
                        {
                            var9 = var10;
                            var3[var11] = (short)Block.gravel.blockID;
                            break;
                        }
                    }

                    byte var14 = 32;
                    var11 = var9 + var14 + 1;

                    for (int var15 = var9 + 1; var15 <= var11 && var15 < 128; ++var15)
                    {
                        int var13 = (var7 * 16 + var6) * 128 + var15;
                        var3[var13] = (short)Block.ice.blockID;
                    }
                }
            }
        }
    }

    public void addDarkForestCanopy(int var1, int var2, short[] var3, byte[] var4, BiomeGenBase[] var5)
    {
        double var6 = 0.03125D;
        this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, var1 * 16, var2 * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);

        for (int var8 = 0; var8 < 16; ++var8)
        {
            for (int var9 = 0; var9 < 16; ++var9)
            {
                BiomeGenBase var10 = var5[var9 + var8 * 16];

                if (var10 == HMCBiomeBase.darkForest)
                {
                    int var11 = -1;
                    int var12;
                    int var13;

                    for (var12 = 127; var12 >= 0; --var12)
                    {
                        var13 = (var9 * 16 + var8) * 128 + var12;
                        short var14 = var3[var13];

                        if (var14 == Block.waterStill.blockID)
                        {
                            break;
                        }

                        if (var14 == Block.stone.blockID)
                        {
                            var11 = var12;
                            break;
                        }
                    }

                    if (var11 != -1)
                    {
                        var12 = (int)(this.stoneNoise[var8 + var9 * 16] / 2.0D);
                        var13 = var11 + 7 - var12;
                        int var17 = var13 + 6 + var12;

                        for (int var15 = var13; var15 <= var17; ++var15)
                        {
                            int var16 = (var9 * 16 + var8) * 128 + var15;
                            var3[var16] = (short)ModBlocks.HarmonionBlock.blockID;
                            var4[var16] = 1;
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int var1, int var2)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider var1, int var2, int var3)
    {
        BlockSand.fallInstantly = true;
        int var4 = var2 * 16;
        int var5 = var3 * 16;
        BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long var7 = this.rand.nextLong() / 2L * 2L + 1L;
        long var9 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)var2 * var7 + (long)var3 * var9 ^ this.worldObj.getSeed());
        boolean var11 = false;

        if (this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, var2, var3);
            var11 |= this.majorFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, var2, var3);
        }

        var11 |= !HMCFeature.getNearestFeature(var2, var3, this.worldObj).chunkDecorationsEnabled;
        int var12;
        int var13;
        int var14;

        if (!var11 && this.rand.nextInt(4) == 0)
        {
            var12 = var4 + this.rand.nextInt(16) + 8;
            var13 = this.rand.nextInt(HMCWorld.WORLDHEIGHT);
            var14 = var5 + this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
        }

        if (!var11 && this.rand.nextInt(32) == 0)
        {
            var12 = var4 + this.rand.nextInt(16) + 8;
            var13 = this.rand.nextInt(this.rand.nextInt(HMCWorld.WORLDHEIGHT - 8) + 8);
            var14 = var5 + this.rand.nextInt(16) + 8;

            if (var13 < HMCWorld.SEALEVEL || this.rand.nextInt(10) == 0)
            {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, var12, var13, var14);
            }
        }

        for (var12 = 0; var12 < 8; ++var12)
        {
            var13 = var4 + this.rand.nextInt(16) + 8;
            var14 = this.rand.nextInt(HMCWorld.WORLDHEIGHT);
            int var15 = var5 + this.rand.nextInt(16) + 8;

            if (!(new WorldGenDungeons()).generate(this.worldObj, this.rand, var13, var14, var15))
            {
                ;
            }
        }

        var6.decorate(this.worldObj, this.rand, var4, var5);
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, var4 + 8, var5 + 8, 16, 16, this.rand);
        var4 += 8;
        var5 += 8;

        for (var12 = 0; var12 < 16; ++var12)
        {
            for (var13 = 0; var13 < 16; ++var13)
            {
                var14 = this.worldObj.getPrecipitationHeight(var4 + var12, var5 + var13);

                if (this.worldObj.isBlockFreezable(var12 + var4, var14 - 1, var13 + var5))
                {
                    this.worldObj.setBlockWithNotify(var12 + var4, var14 - 1, var13 + var5, Block.ice.blockID);
                }

                if (this.worldObj.canSnowAt(var12 + var4, var14, var13 + var5))
                {
                    this.worldObj.setBlockWithNotify(var12 + var4, var14, var13 + var5, Block.snow.blockID);
                }
            }
        }

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
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return shouldOtherModsGenerateInHMC ? "RandomLevelSource" : "HMCLevelSource";
    }

    /**
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType var1, int var2, int var3, int var4)
    {
        HMCFeature var5 = HMCFeature.getNearestFeature(var2 >> 4, var4 >> 4, this.worldObj);

        if (var5 != HMCFeature.nothing)
        {
            this.majorFeatureGenerator.getNearestInstance(this.worldObj, var2, var3, var4);

            if (this.majorFeatureGenerator.hasStructureAt(var2, var3, var4))
            {
                return var5.getSpawnableList(var1);
            }
        }

        if (var3 < HMCWorld.SEALEVEL)
        {
            return HMCFeature.underground.getSpawnableList(var1);
        }
        else
        {
            BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var2, var4);
            return var6 == null ? null : var6.getSpawnableList(var1);
        }
    }

    /**
     * Returns the location of the closest structure of the specified type. If not found returns null.
     */
    public ChunkPosition findClosestStructure(World var1, String var2, int var3, int var4, int var5)
    {
        return "Stronghold".equals(var2) && this.strongholdGenerator != null ? this.strongholdGenerator.getNearestInstance(var1, var3, var4, var5) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }
}

