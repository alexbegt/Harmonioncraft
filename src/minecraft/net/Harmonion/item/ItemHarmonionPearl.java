package net.Harmonion.item;

import net.Harmonion.util.random.Strings;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ItemHarmonionPearl extends ItemHarmonionMats {
	
	public ItemHarmonionPearl(int id) {
		super(id);
		this.setIconIndex(15);
		this.setItemName(Strings.Sound_Stone_Pearl_Name);
		this.setCreativeTab(ModItems.tabHarmonionI);
	}
	
}
