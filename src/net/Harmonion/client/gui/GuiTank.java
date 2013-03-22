package net.Harmonion.client.gui;

import net.Harmonion.block.tank.ITankTile;
import net.Harmonion.gui.containers.ContainerTank;
import net.Harmonion.tanks.HarmonionTileEntity;
import net.Harmonion.util.random.Reference;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiTank extends TileGui
{
    private ITankTile tile;

    public GuiTank(InventoryPlayer var1, ITankTile var2)
    {
        super((HarmonionTileEntity)var2, new ContainerTank(var1, var2), Reference.GUI_SHEET_LOCATION + Reference.TANK_SPRITE_SHEET);
        this.tile = var2;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        int var3 = this.fontRenderer.getStringWidth(this.tile.getInventory().getInvName());
        int var4 = this.xSize / 2 - var3 / 2;
        this.fontRenderer.drawString("Soundstone Reinforced Tank", 14, 6, 4210752);//, var4, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
}
