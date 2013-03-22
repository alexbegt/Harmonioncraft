package net.Harmonion.client.gui;

import java.util.logging.Level;

import net.Harmonion.block.tank.ITankTile;
import net.Harmonion.block.tank.TileMultiBlock;
import net.Harmonion.gui.EnumGui;
import net.Harmonion.util.Game;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;

public class FactoryGui
{
    public static GuiScreen build(EnumGui var0, InventoryPlayer var1, Object var2)
    {
        if (var2 == null)
        {
            return null;
        }
        else if (var2 instanceof TileMultiBlock && !((TileMultiBlock)var2).isStructureValid())
        {
            return null;
        }
        else
        {
            try
            {
                switch (FactoryGui$1.$SwitchMap$railcraft$common$gui$EnumGui[var0.ordinal()])
                {
                	case 1:
                	    return new GuiTank(var1, (ITankTile)var2);
                
                    case 17:
                        return new GuiTank(var1, (ITankTile)var2);
                        
                    case 19:
                        return new GuiTank(var1, (ITankTile)var2);
                    
                    default:
                        return null;
                }
            }
            catch (ClassCastException var4)
            {
                Game.log(Level.WARNING, "Error when attempting to build gui {0}: {1}", new Object[] {var0, var4});
                return null;
            }
        }
    }
}
