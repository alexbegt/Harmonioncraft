package net.Harmonion.util.inventory;

import net.Harmonion.tanks.RailcraftTileEntity;

class StandaloneInventory$TileCallback extends StandaloneInventory$Callback
{
    private RailcraftTileEntity inv;

    final StandaloneInventory this$0;

    public StandaloneInventory$TileCallback(StandaloneInventory var1, RailcraftTileEntity var2)
    {
        super(var1, (StandaloneInventory$1)null);
        this.this$0 = var1;
        this.inv = var2;
    }

    public void onInventoryChanged()
    {
        this.inv.onInventoryChanged();
    }

    public String getInvName()
    {
        return this.inv.getInvName();
    }
}
