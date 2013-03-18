package net.Harmonion.liquids.tanks;

import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public final class FakeTank extends LiquidTank
{
    public static final ILiquidTank INSTANCE = new FakeTank();
    public static final ILiquidTank[] ARRAY = new ILiquidTank[] {new FakeTank()};

    private FakeTank()
    {
        super(1);
    }

    public int fill(LiquidStack var1, boolean var2)
    {
        return 0;
    }

    public LiquidStack drain(int var1, boolean var2)
    {
        return null;
    }
}
