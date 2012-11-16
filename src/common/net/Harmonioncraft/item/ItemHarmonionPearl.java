package net.Harmonioncraft.item;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.Harmonioncraft.lib.Strings;
import net.Harmonioncraft.network.*;

public class ItemHarmonionPearl extends ItemHarmonionMats {
	
	public ItemHarmonionPearl(int id) {
		super(id);
		this.setIconIndex(15);
		this.setItemName(Strings.Sound_Stone_Pearl_Name);
		this.setCreativeTab(ModItems.tabHarmonioncraftI);
	}
	
}
