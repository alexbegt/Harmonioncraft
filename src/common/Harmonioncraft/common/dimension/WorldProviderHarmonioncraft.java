package Harmonioncraft.common.dimension;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Vec3;
import net.minecraft.src.WorldProvider;

public class WorldProviderHarmonioncraft extends WorldProvider {
	
	 /**
     * Returns array with sunrise/sunset colors
     */
    public float[] calcSunriseSunsetColors(float var1, float var2)
    {
        return null;
    }

    /**
     * Return Vec3D with biome specific fog color
     */
    public Vec3 getFogColor(float var1, float var2)
    {
        float var3 = MathHelper.cos(((float)Math.PI / 2F)) * 2.0F + 0.5F;

        if (var3 < 0.0F)
        {
            var3 = 0.0F;
        }

        if (var3 > 1.0F)
        {
            var3 = 1.0F;
        }

        float var4 = 0.7529412F;
        float var5 = 1.0F;
        float var6 = 0.8470588F;
        var4 *= var3 * 0.94F + 0.06F;
        var5 *= var3 * 0.94F + 0.06F;
        var6 *= var3 * 0.91F + 0.09F;
        return Vec3.getVec3Pool().getVecFromPool((double)var4, (double)var5, (double)var6);
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long var1, float var3)
    {
        return 0.225F;
    }

    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new HMCWorldChunkManager(this.worldObj);
        this.dimensionId = 8;
    }

    /**
     * Returns the chunk provider back for the world provider
     */
    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderHarmonionCraft(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
    }

    public boolean isSkyColored()
    {
        return false;
    }

    public int getAverageGroundLevel()
    {
        return 33;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int var1, int var2)
    {
        int var3 = this.worldObj.getFirstUncoveredBlock(var1, var2);
        return var3 == 0 ? false : Block.blocksList[var3].blockMaterial.isSolid();
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return true;
    }

    @Override
    public String getSaveFolder()
    {
        return "DIM8";
    }

    @Override
    public String getWelcomeMessage()
    {
        return "Entering the HarmonionCraft";
    }
    
    @Override
    public String getDepartMessage()
    {
        return "Leaving the HarmonionCraft";
    }

    @Override
	public String getDimensionName() {
		return "HarmonionCraft";
	}

}
