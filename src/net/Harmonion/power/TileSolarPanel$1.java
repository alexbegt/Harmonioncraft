package net.Harmonion.power;

import net.minecraft.tileentity.TileEntity;

class TileSolarPanel$1 extends HarmonionConductor
{
    final TileSolarPanel this$0;

    TileSolarPanel$1(TileSolarPanel var1)
    {
        this.this$0 = var1;
    }

    public TileEntity getParent()
    {
        return this.this$0;
    }

    public double getInvCap()
    {
        return 4.0D;
    }
}
