package net.Harmonion.liquids;

import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class ImmutableTankWrapper extends TankWrapper
{
    public ImmutableTankWrapper(ILiquidTank var1)
    {
        super(var1);
    }

    public LiquidStack getLiquid()
    {
        return this.tank.getLiquid() == null ? null : this.tank.getLiquid().copy();
    }

    public int getCapacity()
    {
        return this.tank.getCapacity();
    }

    public int fill(LiquidStack var1, boolean var2)
    {
        return this.tank.fill(var1, false);
    }

    public LiquidStack drain(int var1, boolean var2)
    {
        return this.tank.drain(var1, false);
    }

    public int getTankPressure()
    {
        return this.tank.getTankPressure();
    }
}
