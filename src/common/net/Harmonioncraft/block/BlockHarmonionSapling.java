package net.Harmonioncraft.block;

import java.util.List;
import java.util.Random;

import net.Harmonioncraft.lib.Reference;
import net.Harmonioncraft.lib.Strings;
import net.Harmonioncraft.mods.Harmonioncraft;
import net.Harmonioncraft.world.worldgen.WorldGenHarmonionTree;
import net.minecraft.src.Block;
import net.minecraft.src.BlockSapling;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BlockHarmonionSapling extends BlockSapling
{
    public BlockHarmonionSapling(int var1, int var2)
    {
        super(var1, var2);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
        this.setBlockName(Strings.Sound_Stone_Sapling_Name);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return this.blockIndexInTexture;
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int var1, int var2)
    {
        return var1 == 0 ? var2 == Block.dirt.blockID || var2 == Block.grass.blockID : (var1 >= 5 ? false : var2 == Block.grass.blockID || var2 == Block.dirt.blockID);
    }
    
    public boolean canBlockStay(World var1, int var2, int var3, int var4, int var5)
    {
        return (var1.getFullBlockLightValue(var2, var3, var4) >= 8 || var1.canBlockSeeTheSky(var2, var3, var4)) && this.canThisPlantGrowOnThisBlockID(var5, var1.getBlockId(var2, var3 - 1, var4));
    }


    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.isRemote)
        {
            super.updateTick(var1, var2, var3, var4, var5);

            if (!this.canBlockStay(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4)))
            {
                this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }

            if (var1.getBlockLightValue(var2, var3 + 1, var4) >= 9 && var5.nextInt(30) == 0)
            {
                var1.getBlockMetadata(var2, var3, var4);
                this.growTree(var1, var2, var3, var4, var1.rand);
            }
        }
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World var1, int var2, int var3, int var4, Random var5)
    {
    	int var6 = var1.getBlockMetadata(var2, var3, var4);
    	Object var7 = null;
    	
    	//var7 = new WorldGenHarmonionTree(true);
    	
    	if (var6 == 0)
        {
            int var8 = var5.nextInt(3);

            if (var8 == 0)
            {
                var7 = new WorldGenHarmonionTree(true);
            }
        }
    	
    	if (var7 != null)
        {
            var1.setBlock(var2, var3, var4, 0);

            if (!((WorldGenerator)var7).generate(var1, var5, var2, var3, var4))
            {
                var1.setBlockAndMetadata(var2, var3, var4, this.blockID, var6);
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }
    
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.canBlockStay(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4)))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.inventory.getCurrentItem();

        if (var10 != null && var10.getItem() == Item.dyePowder && var10.getItemDamage() == 15)
        {
            --var10.stackSize;
            this.growTree(var1, var2, var3, var4, var1.rand);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
    }
}
