package net.Harmonion.core.item;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.Harmonion.core.lib.Strings;
import net.Harmonion.core.network.*;

public class ItemHarmonionPearl extends ItemHarmonionMats {
	
	public ItemHarmonionPearl(int id) {
		super(id);
		this.setIconIndex(15);
		this.setItemName(Strings.Sound_Stone_Pearl_Name);
		this.setCreativeTab(ModItems.tabHarmonionI);
	}
	
}
