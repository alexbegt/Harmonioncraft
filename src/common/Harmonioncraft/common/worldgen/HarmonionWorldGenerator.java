package Harmonioncraft.common.worldgen;

import java.util.Random;
import Harmonioncraft.common.block.ModBlocks;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class HarmonionWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		//world.setBlock(chunkX*16 + random.nextInt(16), 100, chunkZ*16 + random.nextInt(16), ModBlocks.HarmonionOre.blockID);
		
		int baseScale = getSeaLevel(world) + 1;
		
		int baseCount = 20 * baseScale / 64;
		
		int count = (int)Math.round(random.nextGaussian() * Math.sqrt(baseCount) + baseCount);
		for (int n = 0; n < count; n++) {
			int x = chunkX * 16 + random.nextInt(16);
		    int y = random.nextInt(64 * baseScale / 64);
		    int z = chunkZ * 16 + random.nextInt(16);
		    new WorldGenMinable(ModBlocks.HarmonionOre.blockID, 8).generate(world, random, x, y, z);
		}
		
		int var8;
		
		BiomeGenBase var7 = world.getWorldChunkManager().getBiomeGenAt(chunkX * 16 + 16, chunkZ * 16 + 16);
        var8 = 0;

        if (var7.biomeName.toLowerCase().contains("taiga"))
        {
            var8 += random.nextInt(3);
        }

        if (var7.biomeName.toLowerCase().contains("forest") || var7.biomeName.toLowerCase().contains("jungle"))
        {
            var8 += random.nextInt(5) + 1;
        }

        if (var7.biomeName.toLowerCase().contains("swamp"))
        {
            var8 += random.nextInt(10) + 5;
        }

        if (random.nextInt(10) + 1 <= var8 * 2)
        {
            (new WorldGenHarmonionTree()).generate(world, random, chunkX * 16 + random.nextInt(16), var8, chunkZ * 16 + random.nextInt(16));
        }
    }
	
	public static int getSeaLevel(World var0)
	{
		return var0.provider.getAverageGroundLevel();
    }

	public static int getWorldHeight(World var0)
	{
		return var0.getHeight();
	}

}
