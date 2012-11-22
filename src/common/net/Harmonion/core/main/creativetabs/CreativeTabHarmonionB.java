package net.Harmonion.core.main.creativetabs;

import net.Harmonion.core.block.ModBlocks;
import net.minecraft.src.CreativeTabs;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public final class CreativeTabHarmonionB extends CreativeTabs
{
    public CreativeTabHarmonionB(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return ModBlocks.HarmonionBlock.blockID;
    }
}