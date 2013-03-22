package net.Harmonion.liquids;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;

public class TankToolkit implements ITankContainer
{
    private ITankContainer tankContainer;

    public TankToolkit(ITankContainer var1)
    {
        this.tankContainer = var1;
    }

    public int getLiquidQty(LiquidStack var1)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            int var2 = 0;
            ILiquidTank[] var3 = this.getTanks(ForgeDirection.UNKNOWN);
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5)
            {
                ILiquidTank var6 = var3[var5];

                if (var1.isLiquidEqual(var6.getLiquid()))
                {
                    var2 += var6.getLiquid().amount;
                }
            }

            return var2;
        }
    }

    public boolean isTankEmpty(LiquidStack var1)
    {
        return this.getLiquidQty(var1) <= 0;
    }

    public boolean isTankFull(LiquidStack var1)
    {
        if (var1 == null)
        {
            return false;
        }
        else
        {
            var1 = var1.copy();
            var1.amount = 1;
            int var2 = this.fill(ForgeDirection.UNKNOWN, var1, false);
            return var2 <= 0;
        }
    }

    public boolean areTanksFull()
    {
        ILiquidTank[] var1 = this.getTanks(ForgeDirection.UNKNOWN);
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            ILiquidTank var4 = var1[var3];

            if (var4.getLiquid() == null || var4.getLiquid().amount < var4.getCapacity())
            {
                return false;
            }
        }

        return true;
    }

    public boolean areTanksEmpty()
    {
        return !this.isLiquidInTank();
    }

    public boolean isLiquidInTank()
    {
        ILiquidTank[] var1 = this.getTanks(ForgeDirection.UNKNOWN);
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            ILiquidTank var4 = var1[var3];
            boolean var5 = var4.getLiquid() == null || var4.getLiquid().amount <= 0;

            if (!var5)
            {
                return true;
            }
        }

        return false;
    }

    public float getLiquidLevel()
    {
        int var1 = 0;
        int var2 = 0;
        ILiquidTank[] var3 = this.getTanks(ForgeDirection.UNKNOWN);
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            ILiquidTank var6 = var3[var5];
            LiquidStack var7 = var6.getLiquid();
            var1 += var7 == null ? 0 : var7.amount;
            var2 += var6.getCapacity();
        }

        return var2 == 0 ? 0.0F : (float)(var1 / var2);
    }

    public float getLiquidLevel(LiquidStack var1)
    {
        int var2 = 0;
        int var3 = 0;
        ILiquidTank[] var4 = this.getTanks(ForgeDirection.UNKNOWN);
        int var5 = var4.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            ILiquidTank var7 = var4[var6];
            LiquidStack var8 = var7.getLiquid();

            if (var8 != null && var8.isLiquidEqual(var1))
            {
                var2 += var8.amount;
                var3 += var7.getCapacity();
            }
        }

        return var3 == 0 ? 0.0F : (float)var2 / (float)var3;
    }

    public boolean canPutLiquid(ForgeDirection var1, LiquidStack var2)
    {
        return var2 == null ? false : this.fill(var1, var2, false) > 0;
    }

    public int fill(ForgeDirection var1, LiquidStack var2, boolean var3)
    {
        return this.tankContainer.fill(var1, var2, var3);
    }

    public int fill(int var1, LiquidStack var2, boolean var3)
    {
        return this.tankContainer.fill(var1, var2, var3);
    }

    public LiquidStack drain(ForgeDirection var1, int var2, boolean var3)
    {
        return this.tankContainer.drain(var1, var2, var3);
    }

    public LiquidStack drain(int var1, int var2, boolean var3)
    {
        return this.tankContainer.drain(var1, var2, var3);
    }

    public ILiquidTank[] getTanks(ForgeDirection var1)
    {
        return this.tankContainer.getTanks(var1);
    }

    public ILiquidTank getTank(ForgeDirection var1, LiquidStack var2)
    {
        return this.tankContainer.getTank(var1, var2);
    }
}
