package net.Harmonion.liquids;

import net.Harmonion.item.tanks.ItemHarmonion;
import net.Harmonion.util.random.Reference;
import net.minecraft.creativetab.CreativeTabs;

public class ItemLiquid extends ItemHarmonion
{
    public ItemLiquid(int var1)
    {
        super(var1);
        this.setCreativeTab((CreativeTabs)null);
    }

    public String getTextureFile()
    {
        return Reference.SPRITE_SHEET_LOCATION + Reference.POWER_ITEM_SPRITE_SHEET;
    }
}
