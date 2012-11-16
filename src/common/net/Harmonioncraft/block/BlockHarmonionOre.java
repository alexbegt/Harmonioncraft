package net.Harmonioncraft.block;

import java.util.Random;

import net.Harmonioncraft.item.ModItems;
import net.Harmonioncraft.lib.Strings;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.Material;

public class BlockHarmonionOre extends BlockHarmonion {

	public BlockHarmonionOre(int par1, int par2) {
		
		super(par1, par2, Material.rock);
		this.setCreativeTab(ModBlocks.tabHarmonioncraftB);//CreativeTabs.tabBlock);
		this.setHardness(3.0F);
		this.setBlockName(Strings.Sound_Stone_Ore_Name);
		this.setResistance(7.0F);
		
	}
	
	/**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par2Random.nextInt(10 - par3 * 3) == 0 ? ModItems.Harmonionpearl.shiftedIndex : this.blockID;
    }

}
