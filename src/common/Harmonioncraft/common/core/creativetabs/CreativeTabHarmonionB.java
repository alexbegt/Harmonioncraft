package Harmonioncraft.common.core.creativetabs;

import net.minecraft.src.CreativeTabs;
import Harmonioncraft.common.block.ModBlocks;
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
        return ModBlocks.HarmonionOre.blockID;
    }
}