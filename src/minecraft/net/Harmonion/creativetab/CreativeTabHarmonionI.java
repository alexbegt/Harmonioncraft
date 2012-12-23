package net.Harmonion.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.Harmonion.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;

public final class CreativeTabHarmonionI extends CreativeTabs
{
    public CreativeTabHarmonionI(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return ModItems.Refinedsoundstone.shiftedIndex;
    }
}