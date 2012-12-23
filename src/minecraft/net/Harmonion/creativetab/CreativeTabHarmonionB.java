package net.Harmonion.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.Harmonion.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;

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
        return ModBlocks.HarmonionOre.blockID;
    }
}