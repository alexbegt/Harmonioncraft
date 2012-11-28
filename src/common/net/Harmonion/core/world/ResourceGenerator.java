package net.Harmonion.core.world;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.Harmonion.core.block.BlockHarmonionOre;
import net.Harmonion.core.block.ModBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;

public class ResourceGenerator implements IWorldGenerator
{
    public static ResourceGenerator instance = new ResourceGenerator();

    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        switch (var4.provider.dimensionId)
        {
            case -1:
            case 1:
                break;

            case 0:
                this.generateSurface(var4, var1, var2, var3);
                break;

            default:
                this.generateSurface(var4, var1, var2, var3);
        }
    }

    private void generateNether(World var1, Random var2, int var3, int var4)
    {
        int var5 = var3 * 16;
        int var6 = var4 * 16;
    }

    private void generateSurface(World var1, Random var2, int var3, int var4)
    {
        int var5 = var3 * 16;
        int var6 = var4 * 16;
        int var7;
        int var8;
        int var9;
        int var10;
        
        
        
        
        int var20;
		BiomeGenBase var21 = var1.getWorldChunkManager().getBiomeGenAt(var3 * 16 + 16, var4 * 16 + 16);
    	var8 = 0;
    	
    	if (var21.biomeName.toLowerCase().contains("taiga"))
        {
            var8 += var2.nextInt(3);
        }

        if (var21.biomeName.toLowerCase().contains("forest") || var21.biomeName.toLowerCase().contains("jungle"))
        {
            var8 += var2.nextInt(5) + 1;
        }

        if (var21.biomeName.toLowerCase().contains("swamp"))
        {
            var8 += var2.nextInt(10) + 5;
        }

        if (var2.nextInt(10) + 1 <= var8 * 2)
        {
            (new WorldGenHarmonionTree()).generate(var1, var2, var3 * 16 + var2.nextInt(16), var8, var4 * 16 + var2.nextInt(16));
        }
    }
}
