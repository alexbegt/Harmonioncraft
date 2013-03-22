package net.Harmonion.liquids;

import net.Harmonion.item.ModItems;
import net.Harmonion.item.tanks.ItemHarmonion;
import net.Harmonion.util.random.Reference;
import net.minecraft.creativetab.CreativeTabs;

public class ItemLiquidContainer extends ItemHarmonion
{
    public ItemLiquidContainer(int var1)
    {
        super(var1);
        this.setCreativeTab(ModItems.tabHarmonionI);
        this.setMaxStackSize(16);
    }

    public String getTextureFile()
    {
    	return Reference.SPRITE_SHEET_LOCATION + Reference.POWER_ITEM_SPRITE_SHEET;
    }
}
