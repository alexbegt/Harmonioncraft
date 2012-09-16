package Harmonioncraft.common.item;

import Harmonioncraft.common.lib.Reference;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;

public class ItemHarmonion extends Item {
	
	public ItemHarmonion(int id) {
        super(id);
        maxStackSize = 1;
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
    }
	
}
