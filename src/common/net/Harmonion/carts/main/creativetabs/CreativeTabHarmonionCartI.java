package net.Harmonion.carts.main.creativetabs;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class CreativeTabHarmonionCartI extends CreativeTabs
{
    public CreativeTabHarmonionCartI(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return Item.minecartPowered.shiftedIndex;
    }
}