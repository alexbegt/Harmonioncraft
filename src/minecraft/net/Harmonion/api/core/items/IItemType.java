package net.Harmonion.api.core.items;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;

public interface IItemType
{
    public static final Map<String, IItemType> types = new HashMap<String, IItemType>();

    public boolean isItemType(ItemStack stack);
}
