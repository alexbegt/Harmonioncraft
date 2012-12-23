package net.Harmonion.block;

import net.Harmonion.util.Strings;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockHarmonionBlock extends BlockHarmonion {
	
    public BlockHarmonionBlock(int par1, int par2)
    {
        super(par1, Material.iron);
        this.blockIndexInTexture = par2;
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
        this.setBlockName(Strings.Sound_Stone_Block_Name);
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int par1)
    {
        return this.blockIndexInTexture;
    }
}