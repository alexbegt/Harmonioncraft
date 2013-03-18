package net.Harmonion.liquids.tanks;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class StandardTank extends LiquidTank
{
    private int tankIndex;

    public StandardTank(int var1)
    {
        super(var1);
    }

    public StandardTank(int var1, TileEntity var2)
    {
        super((LiquidStack)null, var1, var2);
    }

    public void setTankIndex(int var1)
    {
        this.tankIndex = var1;
    }

    public int getTankIndex()
    {
        return this.tankIndex;
    }

    public boolean isEmpty()
    {
        return this.getLiquid() == null || this.getLiquid().amount <= 0;
    }

    public void writeToNBT(NBTTagCompound var1)
    {
        if (this.getLiquid() != null && this.getLiquid().amount > 0)
        {
            this.getLiquid().writeToNBT(var1);
        }
    }

    public void readFromNBT(NBTTagCompound var1)
    {
        LiquidStack var2 = new LiquidStack(0, 0, 0);
        var2.readFromNBT(var1);

        if (Item.itemsList[var2.itemID] == null || var2.amount <= 0)
        {
            var2 = null;
        }

        this.setLiquid(var2);
    }
}
