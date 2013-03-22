package net.Harmonion.world.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenDense extends BiomeGenBase
{
    public BiomeGenDense(int var1)
    {
        super(var1);
        this.setBiomeName("Harmonion");
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.topBlock = (byte)Block.grass.blockID;
        this.fillerBlock = (byte)Block.dirt.blockID;
        //this.topBlock = (byte)DivineRPG.denseGrass.blockID;
        //this.fillerBlock = (byte)DivineRPG.denseDirt.blockID;
        this.waterColorMultiplier = 2368548;
        this.func_76733_a(5159473);
        this.setTemperatureRainfall(0.7F, 0.8F);
    }
}
