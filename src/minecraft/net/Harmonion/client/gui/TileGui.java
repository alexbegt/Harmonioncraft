package net.Harmonion.client.gui;

import net.Harmonion.gui.containers.RailcraftContainer;
import net.Harmonion.tanks.RailcraftTileEntity;

public abstract class TileGui extends GuiContainerRailcraft
{
    private final RailcraftTileEntity tile;

    public TileGui(RailcraftTileEntity var1, RailcraftContainer var2, String var3)
    {
        super(var2, var3);
        this.tile = var1;
    }
}
