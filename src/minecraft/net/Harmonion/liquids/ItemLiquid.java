package net.Harmonion.liquids;

import net.Harmonion.item.tanks.ItemRailcraft;
import net.minecraft.creativetab.CreativeTabs;

public class ItemLiquid extends ItemRailcraft
{
    public ItemLiquid(int var1)
    {
        super(var1);
        this.setCreativeTab((CreativeTabs)null);
    }

    public String getTextureFile()
    {
        return "/railcraft/client/textures/liquids.png";
    }
}
