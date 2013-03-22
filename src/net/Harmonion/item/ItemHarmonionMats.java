package net.Harmonion.item;

import net.Harmonion.util.random.Reference;
import net.minecraft.item.Item;


public class ItemHarmonionMats extends Item {
	
	public ItemHarmonionMats(int id) {
        super(id);
        maxStackSize = 64;
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
        setCreativeTab(ModItems.tabHarmonionI);
    }
	
}
