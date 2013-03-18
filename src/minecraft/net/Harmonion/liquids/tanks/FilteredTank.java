package net.Harmonion.liquids.tanks;

import java.util.ArrayList;
import java.util.List;

import net.Harmonion.gui.util.ToolTip;
import net.Harmonion.server.Harmonion;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class FilteredTank extends StandardTank
{
    private final LiquidStack filter;

    public FilteredTank(int var1, LiquidStack var2)
    {
        super(var1);
        this.filter = var2;
    }

    public int fill(LiquidStack var1, boolean var2)
    {
        return this.filter.isLiquidEqual(var1) ? super.fill(var1, var2) : 0;
    }

    public LiquidStack getFilter()
    {
        return this.filter.copy();
    }

    public boolean liquidMatchesFilter(LiquidStack var1)
    {
        return var1 != null && this.filter != null ? this.filter.isLiquidEqual(var1) : false;
    }

    public List getToolTip()
    {
        ArrayList var1 = new ArrayList();
        int var2 = 0;
        LiquidStack var3 = this.getFilter();

        if (var3 != null && var3.itemID > 0)
        {
            ItemStack var4 = var3.asItemStack();

            if (var4.getItem() == null)
            {
                return var1;
            }

            var1.add(new ToolTip(Harmonion.proxy.getItemDisplayName(var4), Harmonion.proxy.getItemRarityColor(var4)));

            if (this.getLiquid() != null)
            {
                var2 = this.getLiquid().amount;
            }
        }

        var1.add(new ToolTip(String.format("%,d", new Object[] {Integer.valueOf(var2)}) + " / " + String.format("%,d", new Object[] {Integer.valueOf(this.getCapacity())})));
        return var1;
    }
}
