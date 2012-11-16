package net.Harmonioncraft.block;

import java.util.Random;

import net.Harmonioncraft.item.ModItems;
import net.Harmonioncraft.lib.Strings;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class BlockHarmonionBlock extends BlockHarmonion {

	public BlockHarmonionBlock(int par1, int par2) {
		
		super(par1, par2, Material.rock);
		//this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(3.0F);
		this.setResistance(7.0F);
		this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
		this.setBlockName(Strings.Sound_Stone_Door_Name);
		
	}
	
	/**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }
	
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ModBlocks.HarmonionBlock.blockID;
    }
}