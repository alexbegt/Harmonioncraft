package net.Harmonion.item;

import net.Harmonion.*;
import net.Harmonion.util.random.Reference;
import net.minecraft.item.Item;

public class ItemHarmonion extends Item {
	
	public ItemHarmonion(int id) {
        super(id);
        maxStackSize = 1;
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
    }
	
}
