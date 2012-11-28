package net.Harmonion.core.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.Harmonion.core.lib.Reference;
import net.Harmonion.core.lib.Strings;
import net.minecraft.src.BlockLeaves;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.IShearable;

public class BlockHarmonionLeaves extends BlockLeaves implements IShearable {
	
    int[] adjacentTreeBlocks;

    public BlockHarmonionLeaves(int var1)
    {
        super(var1, 14);
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
        this.graphicsLevel = true;
        this.setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
        this.setBlockName(Strings.Sound_Stone_Leaves_Name);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return this.blockIndexInTexture;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    //public int getRenderColor(int var1)
    //{
    //    return ColorizerFoliage.getFoliageColorBasic();
    //}

    //@SideOnly(Side.CLIENT)

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    //public int colorMultiplier(IBlockAccess var1, int var2, int var3, int var4)
    //{
    //    return ColorizerFoliage.getFoliageColorBasic();
    //}

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return var1.nextInt(35) != 0 ? 0 : 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return ModBlocks.HarmonionSapling.blockID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var1.getBlockId(var2, var3, var4) == this.blockID ? this.graphicsLevel : !var1.isBlockOpaqueCube(var2, var3, var4);
    }

    public boolean isShearable(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public ArrayList onSheared(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        ArrayList var7 = new ArrayList();
        var7.add(new ItemStack(this, 1, var2.getBlockMetadata(var3, var4, var5) & 3));
        return var7;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
    }
}