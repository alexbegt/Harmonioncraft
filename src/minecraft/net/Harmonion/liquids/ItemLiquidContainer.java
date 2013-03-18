package net.Harmonion.liquids;

import net.Harmonion.item.tanks.ItemRailcraft;
import net.minecraft.creativetab.CreativeTabs;

public class ItemLiquidContainer extends ItemRailcraft
{
    public ItemLiquidContainer(int var1)
    {
        super(var1);
        this.setCreativeTab((CreativeTabs)null);
        this.setMaxStackSize(16);
    }

    public String getTextureFile()
    {
        return "/railcraft/client/textures/containers.png";
    }
}
