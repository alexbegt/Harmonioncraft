package net.Harmonion.core.item;

import net.Harmonion.core.lib.Strings;


public class ItemHarmonionIngot extends ItemHarmonionMats {

	public ItemHarmonionIngot(int id) {
		super(id);
		this.setIconIndex(0);
		this.setItemName(Strings.Sound_Stone_Ingot_Name);
		this.setCreativeTab(ModItems.tabHarmonionI);
	}

}
