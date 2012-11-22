package net.Harmonion.core.item;

import net.Harmonion.core.lib.Reference;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;

public class ItemHarmonionMats extends Item {
	public ItemHarmonionMats(int id) {
        super(id);
        maxStackSize = 64;
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
        setCreativeTab(ModItems.tabHarmonionI);
    }
}
