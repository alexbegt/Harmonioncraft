package net.Harmonion.liquids;

import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class TankWrapper implements ILiquidTank
{
    protected final ILiquidTank tank;

    public TankWrapper(ILiquidTank var1)
    {
        this.tank = var1;
    }

    public LiquidStack getLiquid()
    {
        return this.tank.getLiquid();
    }

    public int getCapacity()
    {
        return this.tank.getCapacity();
    }

    public int fill(LiquidStack var1, boolean var2)
    {
        return this.tank.fill(var1, var2);
    }

    public LiquidStack drain(int var1, boolean var2)
    {
        return this.tank.drain(var1, var2);
    }

    public int getTankPressure()
    {
        return this.tank.getTankPressure();
    }
}
