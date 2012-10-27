package net.Harmonioncraft.world.biomes;

import net.minecraft.src.Block;
import net.minecraft.src.EntitySpider;

public class BiomeGenHarmonionRainforest extends BiomeGenHarmonion
{
    protected BiomeGenHarmonionRainforest(int var1)
    {
        super(var1);
        this.topBlock = (byte)Block.grass.blockID;
        this.fillerBlock = (byte)Block.dirt.blockID;
        this.beachAlternateTopBlock = (byte)Block.gravel.blockID;
        this.beachAlternateFillerBlock = (byte)Block.gravel.blockID;
        this.beachAlternateLayerLowered = true;
        this.addMonsterWithNaturalSpawn(EntitySpider.class, 2, 2, 10);
    }
}
