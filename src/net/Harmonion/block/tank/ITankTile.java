package net.Harmonion.block.tank;

import net.Harmonion.liquids.TankManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.liquids.ILiquidTank;

public interface ITankTile
{
    ILiquidTank getTank();

    TankManager getTankManager();

    IInventory getInventory();

    Slot getInputSlot(IInventory var1, int var2, int var3, int var4);
}
