package Harmonioncraft.common.block;

import Harmonioncraft.common.lib.Reference;
import net.minecraft.src.Block;
import net.minecraft.src.Material;

public class BlockHarmonion extends Block{

	public BlockHarmonion(int par1, Material par2Material) {
		super(par1, par2Material);
		setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
	}
	
	public BlockHarmonion(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
	}

}
