package net.Harmonion.api.transport;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

/**
 * Interface used to put objects into pipes, implemented by pipe tile entities.
 */
public interface IPipeEntry {

	void entityEntering(ItemStack payload, ForgeDirection orientation);

	void entityEntering(IPipedItem item, ForgeDirection orientation);

	boolean acceptItems();

}
