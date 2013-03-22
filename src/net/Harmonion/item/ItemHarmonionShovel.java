package net.Harmonion.item;

import net.Harmonion.util.random.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;


public class ItemHarmonionShovel extends ItemHarmonionTool {
	
    /** an array of the blocks this spade is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[] {Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium};

    public ItemHarmonionShovel(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, 1, par2EnumToolMaterial, blocksEffectiveAgainst);
        this.setIconIndex(8);
        this.setItemName(Strings.Sound_Stone_Shovel_Name);
        this.setCreativeTab(ModItems.tabHarmonionI);
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }
}
