package net.Harmonion.world.gen.feature;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;

import net.Harmonion.block.ModBlocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldPopulator implements IWorldGenerator
{
    protected World currentWorld;
    protected int chunk_X;
    protected int chunk_Z;
    protected Random rand;
    public static WorldGenerator blueGen = new WorldPopMinable(ModBlocks.HarmonionOre.blockID, 0, 6);
    public static WorldGenerator greenGen = new WorldPopMinable(ModBlocks.HarmonionOre.blockID, 1, 6);
    private final int gemHeight = 56;

    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        this.currentWorld = var4;
        this.rand = var1;
        this.chunk_X = var2;
        this.chunk_Z = var3;

        if (!(var6 instanceof ChunkProviderHell) && !(var6 instanceof ChunkProviderEnd))
        {
            this.gen(var4, this.rand, this.chunk_X, this.chunk_Z);
        }
    }

    public void gen(World var1, Random var2, int var3, int var4)
    {
        this.genOres();
    }

    protected void genOre(int var1, WorldGenerator var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < var1; ++var5)
        {
            int var6 = this.chunk_X * 16 + this.rand.nextInt(16);
            int var7 = this.rand.nextInt(var4 - var3) + var3;
            int var8 = this.chunk_Z * 16 + this.rand.nextInt(16);
            var2.generate(this.currentWorld, this.rand, var6, var7, var8);
        }
    }

    private boolean isAdjacent(World var1, int var2, int var3, int var4, int var5)
    {
        try
        {
            if (var1.getBlockId(var2, var3, var4 + 1) == var5 || var1.getBlockId(var2, var3, var4 - 1) == var5 || var1.getBlockId(var2 + 1, var3, var4) == var5 || var1.getBlockId(var2 - 1, var3, var4) == var5 || var1.getBlockId(var2, var3 + 1, var4) == var5 || var1.getBlockId(var2, var3 - 1, var4) == var5)
            {
                return true;
            }
        }
        catch (Exception var7)
        {
            ;
        }

        return false;
    }

    protected void genOres()
    {
        Random var1 = new Random();
        this.genOre(var1.nextInt(8) + 2, blueGen, 4, 56);
        this.genOre(var1.nextInt(8) + 2, greenGen, 4, 56);
    }
}
