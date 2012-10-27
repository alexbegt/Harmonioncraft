package net.Harmonioncraft.world.worldgen;

import java.util.Random;

import net.Harmonioncraft.block.ModBlocks;
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
