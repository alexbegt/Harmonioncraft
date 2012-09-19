package Harmonioncraft.common.dimension;

import java.util.Random;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenLakes;
import net.minecraft.src.WorldGenLiquids;

public class HMCBiomeDecorator extends BiomeDecorator
{
    HMCGenCanopyTree canopyTreeGen = new HMCGenCanopyTree();
    HMCGenCanopyMushroom canopyMushroomGen = new HMCGenCanopyMushroom();
    HMCGenHollowTree hollowTreeGen = new HMCGenHollowTree();
    HMCGenMyceliumBlob myceliumBlobGen = new HMCGenMyceliumBlob(5);
    WorldGenLakes extraLakeGen;
    WorldGenLakes extraLavaPoolGen;
    HMCGenMangroveTree mangroveTreeGen = new HMCGenMangroveTree();
    HMCGenPlantRoots plantRootGen;
    HMCGenWoodRoots woodRootGen;
    WorldGenLiquids caveWaterGen;
    HMCGenHangBerries hangBerryGen;
    public int canopyPerChunk;
    public float chanceCanopyIsMushroom;
    public int myceliumPerChunk;
    public int mangrovesPerChunk;
    public int lakesPerChunk;
    public float lavaPoolChance;

    public HMCBiomeDecorator(BiomeGenBase var1)
    {
        super(var1);
        this.extraLakeGen = new WorldGenLakes(Block.waterStill.blockID);
        this.extraLavaPoolGen = new WorldGenLakes(Block.lavaStill.blockID);
        this.plantRootGen = new HMCGenPlantRoots();
        this.woodRootGen = new HMCGenWoodRoots();
        this.caveWaterGen = new WorldGenLiquids(Block.waterMoving.blockID);
        this.hangBerryGen = new HMCGenHangBerries();
        this.canopyPerChunk = 1;
        this.chanceCanopyIsMushroom = 0.0F;
        this.myceliumPerChunk = 0;
        this.lakesPerChunk = 0;
        this.lavaPoolChance = 0.0F;
        this.mangrovesPerChunk = 0;
    }

    /**
     * Decorates the world. Calls code that was formerly (pre-1.8) in ChunkProviderGenerate.populate
     */
    public void decorate(World var1, Random var2, int var3, int var4)
    {
    	HMCFeature var5 = HMCFeature.getNearestFeature(var3 >> 4, var4 >> 4, var1);
    	HMCFeature var6 = HMCFeature.getFeatureDirectlyAt(var3 >> 4, var4 >> 4, var1);

        if (!var5.chunkDecorationsEnabled)
        {
            this.decorateUnderground(var1, var2, var3, var4);
            this.decorateOnlyOres(var1, var2, var3, var4);
        }
        else
        {
            if (var6 == HMCFeature.glacierMaze)
            {
                (new HMCGenGlacierMaze(1)).generate(var1, var2, var3 + 8, HMCWorld.SEALEVEL + 10, var4 + 8);
            }

            this.decorateUnderground(var1, var2, var3, var4);
            int var7;
            int var8;
            int var9;

            if (var2.nextInt(24) == 0)
            {
                var7 = var3 + var2.nextInt(16) + 8;
                var8 = var4 + var2.nextInt(16) + 8;
                var9 = var1.getHeightValue(var7, var8);
                this.hollowTreeGen.generate(var1, var2, var7, var9, var8);
            }

            if (var2.nextInt(6) == 0)
            {
                var7 = var3 + var2.nextInt(16) + 8;
                var8 = var4 + var2.nextInt(16) + 8;
                var9 = var1.getHeightValue(var7, var8);
                HMCGenerator var10 = this.randomFeature(var2);

                if (var10.generate(var1, var2, var7, var9, var8))
                {
                    ;
                }
            }

            var7 = this.canopyPerChunk + var2.nextInt(2);
            int var11;
            int var12;

            for (var8 = 0; var8 < var7; ++var8)
            {
                var9 = var3 + var2.nextInt(16) + 8;
                var12 = var4 + var2.nextInt(16) + 8;
                var11 = var1.getHeightValue(var9, var12);

                if (this.chanceCanopyIsMushroom > 0.0F && var2.nextFloat() <= this.chanceCanopyIsMushroom)
                {
                    this.canopyMushroomGen.generate(var1, var2, var9, var11, var12);
                }
                else if (var2.nextInt(24) > 0)
                {
                    this.canopyTreeGen.generate(var1, var2, var9, var11, var12);
                }
                else
                {
                    (new HMCGenHugeCanopyTree()).generate(var1, var2, var9, var11, var12);
                }
            }

            for (var8 = 0; var8 < this.lakesPerChunk; ++var8)
            {
                var9 = var3 + var2.nextInt(16) + 8;
                var12 = var4 + var2.nextInt(16) + 8;
                var11 = var1.getHeightValue(var9, var12);
                this.extraLakeGen.generate(var1, var2, var9, var11, var12);
            }

            if (var2.nextFloat() <= this.lavaPoolChance)
            {
                var8 = var3 + var2.nextInt(16) + 8;
                var9 = var4 + var2.nextInt(16) + 8;
                var12 = var1.getHeightValue(var8, var9);
                this.extraLavaPoolGen.generate(var1, var2, var8, var12, var9);
            }

            for (var8 = 0; var8 < this.myceliumPerChunk; ++var8)
            {
                var9 = var3 + var2.nextInt(16) + 8;
                var12 = var4 + var2.nextInt(16) + 8;
                var11 = var1.getHeightValue(var9, var12);
                this.myceliumBlobGen.generate(var1, var2, var9, var11, var12);
            }

            for (var8 = 0; var8 < this.mangrovesPerChunk; ++var8)
            {
                var9 = var3 + var2.nextInt(16) + 8;
                var12 = var4 + var2.nextInt(16) + 8;
                var11 = var1.getHeightValue(var9, var12);
                this.mangroveTreeGen.generate(var1, var2, var9, var11, var12);
            }

            super.decorate(var1, var2, var3, var4);
        }
    }

    protected void decorateUnderground(World var1, Random var2, int var3, int var4)
    {
        int var5;
        int var6;
        byte var7;
        int var8;

        for (var5 = 0; var5 < 12; ++var5)
        {
            var6 = var3 + var2.nextInt(16) + 8;
            var7 = 64;
            var8 = var4 + var2.nextInt(16) + 8;
            this.plantRootGen.generate(var1, var2, var6, var7, var8);
        }

        int var9;

        for (var5 = 0; var5 < 20; ++var5)
        {
            var6 = var3 + var2.nextInt(16) + 8;
            var9 = var2.nextInt(64);
            var8 = var4 + var2.nextInt(16) + 8;
            this.woodRootGen.generate(var1, var2, var6, var9, var8);
        }

        for (var5 = 0; var5 < 50; ++var5)
        {
            var6 = var3 + var2.nextInt(16) + 8;
            var9 = var2.nextInt(24) + 4;
            var8 = var4 + var2.nextInt(16) + 8;
            this.caveWaterGen.generate(var1, var2, var6, var9, var8);
        }

        for (var5 = 0; var5 < 3; ++var5)
        {
            var6 = var3 + var2.nextInt(16) + 8;
            var7 = 64;
            var8 = var4 + var2.nextInt(16) + 8;
            this.hangBerryGen.generate(var1, var2, var6, var7, var8);
        }
    }

    public void decorateOnlyOres(World var1, Random var2, int var3, int var4)
    {
        this.currentWorld = var1;
        this.randomGenerator = var2;
        this.chunk_X = var3;
        this.chunk_Z = var4;
        this.generateOres();
        this.currentWorld = null;
        this.randomGenerator = null;
    }

    public HMCGenerator randomFeature(Random var1)
    {
        int var2 = var1.nextInt(6);

        switch (var2)
        {
            case 0:
                return new HMCGenStoneCircle();

            case 1:
                return new HMCGenWell();

            case 2:
                return new HMCGenWitchHut();

            case 3:
                return new HMCGenOutsideStalagmite();

            case 4:
                return new HMCGenFoundation();

            case 5:
                return new HMCGenMonolith();

            default:
                return new HMCGenStoneCircle();
        }
    }

    public void setTreesPerChunk(int var1)
    {
        this.treesPerChunk = var1;
    }

    public void setBigMushroomsPerChunk(int var1)
    {
        this.bigMushroomsPerChunk = var1;
    }

    public void setClayPerChunk(int var1)
    {
        this.clayPerChunk = var1;
    }

    public void setDeadBushPerChunk(int var1)
    {
        this.deadBushPerChunk = var1;
    }

    public void setMushroomsPerChunk(int var1)
    {
        this.mushroomsPerChunk = var1;
    }

    public void setFlowersPerChunk(int var1)
    {
        this.flowersPerChunk = var1;
    }

    public void setReedsPerChunk(int var1)
    {
        this.reedsPerChunk = var1;
    }

    public void setWaterlilyPerChunk(int var1)
    {
        this.waterlilyPerChunk = var1;
    }

    public void setGrassPerChunk(int var1)
    {
        this.grassPerChunk = var1;
    }
}

