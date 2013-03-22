package net.Harmonion.api.transport;

import net.minecraft.nbt.NBTTagCompound;

public interface IPassiveItemContribution {

	public void readFromNBT(NBTTagCompound nbttagcompound);

	public void writeToNBT(NBTTagCompound nbttagcompound);

}
