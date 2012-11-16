package net.Harmonioncraft.item;

import net.Harmonioncraft.lib.Strings;

public class ItemHarmonionIngot extends ItemHarmonionMats {

	public ItemHarmonionIngot(int id) {
		super(id);
		this.setIconIndex(0);
		this.setItemName(Strings.Sound_Stone_Ingot_Name);
		this.setCreativeTab(ModItems.tabHarmonioncraftI);
	}

}
