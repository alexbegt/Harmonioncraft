package net.Harmonion.power;

import net.minecraft.tileentity.TileEntity;

class TileBatteryBox$1 extends HarmonionConductor
{
    final TileBatteryBox this$0;

    TileBatteryBox$1(TileBatteryBox var1)
    {
        this.this$0 = var1;
    }

    public TileEntity getParent()
    {
        return this.this$0;
    }

    public double getInvCap()
    {
        return 0.25D;
    }
}
