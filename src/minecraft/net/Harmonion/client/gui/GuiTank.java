package net.Harmonion.client.gui;

import net.Harmonion.block.tank.ITankTile;
import net.Harmonion.gui.containers.ContainerTank;
import net.Harmonion.tanks.RailcraftTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiTank extends TileGui
{
    private ITankTile tile;

    public GuiTank(InventoryPlayer var1, ITankTile var2)
    {
        super((RailcraftTileEntity)var2, new ContainerTank(var1, var2), "/railcraft/client/textures/gui/gui_tank_water.png");
        this.tile = var2;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        int var3 = this.fontRenderer.getStringWidth(this.tile.getInventory().getInvName());
        int var4 = this.xSize / 2 - var3 / 2;
        this.fontRenderer.drawString(this.tile.getInventory().getInvName(), var4, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
}
