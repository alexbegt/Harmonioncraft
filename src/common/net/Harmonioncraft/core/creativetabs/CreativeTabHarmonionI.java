package net.Harmonioncraft.core.creativetabs;

import net.Harmonioncraft.item.ModItems;
import net.minecraft.src.CreativeTabs;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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