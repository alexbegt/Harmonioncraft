package net.Harmonion.client.gui;

import net.Harmonion.gui.containers.HarmonionContainer;
import net.Harmonion.tanks.HarmonionTileEntity;

public abstract class TileGui extends GuiContainerHarmonion
{
    private final HarmonionTileEntity tile;

    public TileGui(HarmonionTileEntity var1, HarmonionContainer var2, String var3)
    {
        super(var2, var3);
        this.tile = var1;
    }
}
