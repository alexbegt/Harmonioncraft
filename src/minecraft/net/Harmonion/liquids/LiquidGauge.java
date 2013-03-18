package net.Harmonion.liquids;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.Harmonion.gui.util.ToolTip;
import net.Harmonion.liquids.tanks.FilteredTank;
import net.Harmonion.server.Harmonion;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.ILiquidTank;

public class LiquidGauge
{
    public final ILiquidTank tank;
    public final int x;
    public final int y;
    public final int h;
    public final int w;

    public LiquidGauge(ILiquidTank var1, int var2, int var3, int var4, int var5)
    {
        this.tank = var1;
        this.x = var2;
        this.y = var3;
        this.h = var5;
        this.w = var4;
    }

    public List getToolTip()
    {
        if (this.tank instanceof FilteredTank)
        {
            return ((FilteredTank)this.tank).getToolTip();
        }
        else
        {
            ArrayList var1 = new ArrayList();
            int var2 = 0;

            if (this.tank.getLiquid() != null && this.tank.getLiquid().itemID != 0 && this.tank.getLiquid().amount > 0)
            {
                ItemStack var3 = this.tank.getLiquid().asItemStack();

                if (var3.getItem() == null)
                {
                    return var1;
                }

                var1.add(new ToolTip(Harmonion.proxy.getItemDisplayName(var3), Harmonion.proxy.getItemRarityColor(var3)));
                var2 = this.tank.getLiquid().amount;
            }

            var1.add(new ToolTip(String.format(Locale.ENGLISH, "%,d / %,d", new Object[] {Integer.valueOf(var2), Integer.valueOf(this.tank.getCapacity())})));
            return var1;
        }
    }
}
