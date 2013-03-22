package net.Harmonion.liquids;

import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class ForgeLiquidAdapater implements ILiquidAdaptor
{
    private static ForgeLiquidAdapater instance;

    public static ForgeLiquidAdapater getInstance()
    {
        if (instance == null)
        {
            instance = new ForgeLiquidAdapater();
        }

        return instance;
    }

    public void registerContainer(LiquidContainerData var1)
    {
        LiquidContainerRegistry.registerLiquid(var1);
    }
}
