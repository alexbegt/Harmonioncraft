package net.Harmonion.gui.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.Harmonion.block.tank.ITankTile;
import net.Harmonion.gui.slots.SlotOutput;
import net.Harmonion.liquids.LiquidGauge;
import net.Harmonion.liquids.TankManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraftforge.liquids.ILiquidTank;

public class ContainerTank extends RailcraftContainer
{
    private ITankTile tile;
    private Slot input;

    public ContainerTank(InventoryPlayer var1, ITankTile var2)
    {
        super(var2.getInventory());
        this.tile = var2;
        ILiquidTank var3 = var2.getTank();

        if (var3 != null)
        {
            this.addGauge(new LiquidGauge(var3, 35, 23, 48, 47));
        }

        this.addSlot(this.input = var2.getInputSlot(var2.getInventory(), 0, 116, 21));
        this.addSlot(new SlotOutput(var2.getInventory(), 1, 116, 56));
        int var4;

        for (var4 = 0; var4 < 3; ++var4)
        {
            for (int var5 = 0; var5 < 9; ++var5)
            {
                this.addSlot(new Slot(var1, var5 + var4 * 9 + 9, 8 + var5 * 18, 84 + var4 * 18));
            }
        }

        for (var4 = 0; var4 < 9; ++var4)
        {
            this.addSlot(new Slot(var1, var4, 8 + var4 * 18, 142));
        }
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        TankManager var1 = this.tile.getTankManager();

        if (var1 != null)
        {
            var1.updateGuiData(this, this.crafters, 0);
        }
    }

    public void addCraftingToCrafters(ICrafting var1)
    {
        super.addCraftingToCrafters(var1);
        TankManager var2 = this.tile.getTankManager();

        if (var2 != null)
        {
            var2.initGuiData(this, var1, 0);
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var1, int var2)
    {
        TankManager var3 = this.tile.getTankManager();

        if (var3 != null)
        {
            var3.processGuiUpdate(var1, var2);
        }
    }
}
