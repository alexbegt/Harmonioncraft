package net.Harmonioncraft.world.biomes;

import net.minecraft.src.EntitySquid;

public class BiomeGenHarmonionOcean extends BiomeGenHarmonion
{
    protected BiomeGenHarmonionOcean(int var1)
    {
        super(var1);
        this.addWaterMobWithNaturalSpawn(EntitySquid.class, 1, 2, 5);
    }
}
