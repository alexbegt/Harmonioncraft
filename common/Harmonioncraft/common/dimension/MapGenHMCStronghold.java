package Harmonioncraft.common.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.ComponentStrongholdStairs2;
import net.minecraft.src.MapGenStructure;
import net.minecraft.src.StructureStart;

public class MapGenHMCStronghold extends MapGenStructure
{
    private BiomeGenBase[] allowedBiomeGenBases;
    private boolean ranBiomeCheck;
    private ChunkCoordIntPair[] structureCoords;

    public MapGenHMCStronghold()
    {
        this.allowedBiomeGenBases = new BiomeGenBase[] {};//HMCBiomeBase.twilighHMCorest, HMCBiomeBase.twilighHMCorest2, HMCBiomeBase.highlands, HMCBiomeBase.mushrooms, HMCBiomeBase.swamp, HMCBiomeBase.stream, HMCBiomeBase.snow, HMCBiomeBase.glacier, HMCBiomeBase.clearing, HMCBiomeBase.clearingBorder, HMCBiomeBase.deepMushrooms};
        this.structureCoords = new ChunkCoordIntPair[3];
    }

    protected boolean canSpawnStructureAtCoords(int var1, int var2)
    {
        if (!this.ranBiomeCheck)
        {
            Random var3 = new Random();
            var3.setSeed(this.worldObj.getSeed());
            double var4 = var3.nextDouble() * Math.PI * 2.0D;

            for (int var6 = 0; var6 < this.structureCoords.length; ++var6)
            {
                double var7 = (1.25D + var3.nextDouble()) * 32.0D;
                int var9 = (int)Math.round(Math.cos(var4) * var7);
                int var10 = (int)Math.round(Math.sin(var4) * var7);
                ArrayList var11 = new ArrayList();
                BiomeGenBase[] var12 = this.allowedBiomeGenBases;
                int var13 = var12.length;

                for (int var14 = 0; var14 < var13; ++var14)
                {
                    BiomeGenBase var15 = var12[var14];
                    var11.add(var15);
                }

                ChunkPosition var19 = this.worldObj.getWorldChunkManager().findBiomePosition((var9 << 4) + 8, (var10 << 4) + 8, 112, var11, var3);

                if (var19 != null)
                {
                    var9 = var19.x >> 4;
                    var10 = var19.z >> 4;
                }
                else
                {
                    System.out.println("Placed stronghold in INVALID biome at (" + var9 + ", " + var10 + ")");
                }

                this.structureCoords[var6] = new ChunkCoordIntPair(var9, var10);
                var4 += (Math.PI * 2D) / (double)this.structureCoords.length;
            }

            this.ranBiomeCheck = true;
        }

        ChunkCoordIntPair[] var16 = this.structureCoords;
        int var17 = var16.length;

        for (int var5 = 0; var5 < var17; ++var5)
        {
            ChunkCoordIntPair var18 = var16[var5];

            if (var1 == var18.chunkXPos && var2 == var18.chunkZPos)
            {
                System.out.println(var1 + ", " + var2);
                return true;
            }
        }

        return false;
    }

    protected List func_40482_a()
    {
        ArrayList var1 = new ArrayList();
        ChunkCoordIntPair[] var2 = this.structureCoords;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            ChunkCoordIntPair var5 = var2[var4];

            if (var5 != null)
            {
                var1.add(var5.getChunkPosition(64));
            }
        }

        return var1;
    }

    protected StructureStart getStructureStart(int var1, int var2)
    {
        StructureHMCStrongholdStart var3;

        for (var3 = new StructureHMCStrongholdStart(this.worldObj, this.rand, var1, var2); var3.getComponents().isEmpty() || ((ComponentStrongholdStairs2)var3.getComponents().get(0)).portalRoom == null; var3 = new StructureHMCStrongholdStart(this.worldObj, this.rand, var1, var2))
        {
            ;
        }

        return var3;
    }
}
