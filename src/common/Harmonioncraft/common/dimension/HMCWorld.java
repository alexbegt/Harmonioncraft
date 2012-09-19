package Harmonioncraft.common.dimension;

import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.Entity;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.ISaveHandler;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Profiler;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldSettings;

public class HMCWorld extends World {
    public static int SEALEVEL = 32;
    public static int WORLDHEIGHT = 128;

    public HMCWorld(ISaveHandler var1, String var2, WorldProvider var3, WorldSettings var4, Profiler var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    /**
     * Sets a new spawn location by finding an uncovered block at a random (x,z) location in the chunk.
     */
    public void setSpawnLocation() {}

    /**
     * Returns the coordinates of the spawn point
     */
    public ChunkCoordinates getSpawnPoint()
    {
        ChunkCoordinates var1 = new ChunkCoordinates(this.worldInfo.getSpawnX(), this.worldInfo.getSpawnY(), this.worldInfo.getSpawnZ());
        ChunkCoordinates var2 = var1;

        if (var1.equals(var1) && this.getBlockId(var1.posX, var1.posY, var1.posZ) == 0)
        {
            for (int var3 = var1.posY; var3 > 4; --var3)
            {
                if (this.getBlockId(var2.posX, var3, var2.posZ) != 0)
                {
                    var2.posY = var3;
                    break;
                }
            }
        }

        return var2;
    }

    /**
     * Returns the amount of skylight subtracted for the current time
     */
    public int calculateSkylightSubtracted(float var1)
    {
        float var2 = 0.5F;
        var2 = (float)((double)var2 * (1.0D - (double)(this.getRainStrength(var1) * 5.0F) / 16.0D));
        var2 = (float)((double)var2 * (1.0D - (double)(this.getWeightedThunderStrength(var1) * 5.0F) / 16.0D));
        var2 = 1.0F - var2;
        return (int)(var2 * 11.0F);
    }

    public float func_35464_b(float var1)
    {
        float var2 = 0.2F;
        var2 = (float)((double)var2 * (1.0D - (double)(this.getRainStrength(var1) * 5.0F) / 16.0D));
        var2 = (float)((double)var2 * (1.0D - (double)(this.getWeightedThunderStrength(var1) * 5.0F) / 16.0D));
        return var2 * 0.8F + 0.2F;
    }

    /**
     * Calculates the color for the skybox
     */
    public Vec3 getSkyColor(Entity var1, float var2)
    {
        float var3 = 0.25F;
        float var4 = MathHelper.cos(var3 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (var4 < 0.0F)
        {
            var4 = 0.0F;
        }

        if (var4 > 1.0F)
        {
            var4 = 1.0F;
        }

        int var5 = MathHelper.floor_double(var1.posX);
        int var6 = MathHelper.floor_double(var1.posZ);
        int var7 = this.getBiomeGenForCoords(var5, var6).getSkyColorByTemp(0.5F);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;
        var8 *= var4;
        var9 *= var4;
        var10 *= var4;
        float var11 = this.getRainStrength(var2);
        float var12;
        float var13;

        if (var11 > 0.0F)
        {
            var12 = (var8 * 0.3F + var9 * 0.59F + var10 * 0.11F) * 0.6F;
            var13 = 1.0F - var11 * 0.75F;
            var8 = var8 * var13 + var12 * (1.0F - var13);
            var9 = var9 * var13 + var12 * (1.0F - var13);
            var10 = var10 * var13 + var12 * (1.0F - var13);
        }

        var12 = this.getWeightedThunderStrength(var2);

        if (var12 > 0.0F)
        {
            var13 = (var8 * 0.3F + var9 * 0.59F + var10 * 0.11F) * 0.2F;
            float var14 = 1.0F - var12 * 0.75F;
            var8 = var8 * var14 + var13 * (1.0F - var14);
            var9 = var9 * var14 + var13 * (1.0F - var14);
            var10 = var10 * var14 + var13 * (1.0F - var14);
        }

        if (this.lightningFlash > 0)
        {
            var13 = (float)this.lightningFlash - var2;

            if (var13 > 1.0F)
            {
                var13 = 1.0F;
            }

            var13 *= 0.45F;
            var8 = var8 * (1.0F - var13) + 0.8F * var13;
            var9 = var9 * (1.0F - var13) + 0.8F * var13;
            var10 = var10 * (1.0F - var13) + 1.0F * var13;
        }

        return Vec3.getVec3Pool().getVecFromPool((double)var8, (double)var9, (double)var10);
    }

    /**
     * How bright are stars in the sky
     */
    public float getStarBrightness(float var1)
    {
        return 0.5F;
    }

    public double getSeaLevel()
    {
        return (double)SEALEVEL;
    }

    /**
     * Creates the chunk provider for this world. Called in the constructor. Retrieves provider from worldProvider?
     */
    protected IChunkProvider createChunkProvider()
    {
        return null;
    }
}