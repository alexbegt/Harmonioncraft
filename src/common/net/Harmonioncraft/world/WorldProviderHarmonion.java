package net.Harmonioncraft.world;

import net.Harmonioncraft.world.chunkprovider.ChunkProviderHMC;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.WorldProvider;

public class WorldProviderHarmonion extends WorldProvider
{
    public static final int timeOffset = -12000;

    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHarmonion(this.worldObj);
        this.dimensionId = -128;
        this.isHellWorld = false;
        this.hasNoSky = false;
    }

    /**
     * Returns the chunk provider back for the world provider
     */
    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderHMC(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled());
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return true;
    }

    public String getSaveFolder()
    {
        return "HMC";
    }

    public String getWelcomeMessage()
    {
        return "Drifting into the Hmc!";
    }

    public String getDepartMessage()
    {
        return "Leaving the Hmc!";
    }

    public int getAverageGroundLevel()
    {
        return 63;
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long var1, float var3)
    {
        return super.calculateCelestialAngle(var1 + -12000L, var3);
    }

    @Override
    public String getDimensionName()
    {
        return "HMC";
    }
}
