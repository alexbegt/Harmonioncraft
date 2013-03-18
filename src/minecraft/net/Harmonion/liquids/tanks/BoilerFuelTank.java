package net.Harmonion.liquids.tanks;

import net.Harmonion.api.fuel.FuelManager;
import net.minecraftforge.liquids.LiquidStack;

public class BoilerFuelTank extends StandardTank
{
    public BoilerFuelTank(int var1)
    {
        super(var1);
    }

    public int fill(LiquidStack var1, boolean var2)
    {
        return FuelManager.getBoilerFuelValue(var1) > 0 ? super.fill(var1, var2) : 0;
    }
}
