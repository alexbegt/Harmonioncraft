package net.HarmonionCarts.carts;

import net.HarmonionCarts.item.ModItems;
import net.minecraft.src.CreativeTabs;

public class ItemCartVanilla extends ItemCart
{
    public ItemCartVanilla(int var1, EnumCart var2)
    {
        super(var1, var2);
        this.setCreativeTab(ModItems.tabHarmonioncraftCartI);
    }

    public String getTextureFile()
    {
        return "/gui/items.png";
    }
}
