package net.Harmonion.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.Harmonion.util.random.Reference;
import net.Harmonion.util.random.Strings;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;

public class BlockHarmonionGlass extends BlockBreakable
{
    public BlockHarmonionGlass(int par1, int par2, Material par3Material, boolean par4)
    {
        super(par1, par2, par3Material, par4);
        this.setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
        this.setBlockName(Strings.Sound_Stone_Leaves_Name);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return true;
    }
}
