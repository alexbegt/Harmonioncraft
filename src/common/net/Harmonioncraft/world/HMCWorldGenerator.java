package net.Harmonioncraft.world;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.Harmonioncraft.world.worldgen.WorldGenHarmonionTree;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldType;

public class HMCWorldGenerator implements IWorldGenerator
{
    private static final List rainBiomes = Arrays.asList(new BiomeGenBase[] {BiomeGenBase.desert, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver, BiomeGenBase.taiga});

    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        this.generateSurface(var4, var1, var2, var3);
    }

    public void generateSurface(World var1, Random var2, int var3, int var4)
    {
    	int var8;
		BiomeGenBase var7 = var1.getWorldChunkManager().getBiomeGenAt(var3 * 16 + 16, var4 * 16 + 16);
    	var8 = 0;
    	
    	if (var7.biomeName.toLowerCase().contains("taiga"))
        {
            var8 += var2.nextInt(3);
        }

        if (var7.biomeName.toLowerCase().contains("forest") || var7.biomeName.toLowerCase().contains("jungle"))
        {
            var8 += var2.nextInt(5) + 1;
        }

        if (var7.biomeName.toLowerCase().contains("swamp"))
        {
            var8 += var2.nextInt(10) + 5;
        }

        if (var2.nextInt(10) + 1 <= var8 * 2)
        {
            (new WorldGenHarmonionTree()).generate(var1, var2, var3 * 16 + var2.nextInt(16), var8, var4 * 16 + var2.nextInt(16));
        }
    }
}
