package net.Harmonion.gui;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import java.util.logging.Level;

import net.Harmonion.client.gui.FactoryGui;
import net.Harmonion.gui.containers.FactoryContainer;
import net.Harmonion.power.ContainerBatteryBox;
import net.Harmonion.power.CoreLib;
import net.Harmonion.power.GuiBatteryBox;
import net.Harmonion.power.TileBatteryBox;
import net.Harmonion.server.Harmonion;
import net.Harmonion.util.Game;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    public static void openGui(EnumGui var0, EntityPlayer var1, World var2, int var3, int var4, int var5)
    {
        if (Game.isHost(var2))
        {
            if (var0.hasContainer())
            {
                var1.openGui(Harmonion.getMod(), var0.getId(), var2, var3, var4, var5);
            }
        }
        else if (!var0.hasContainer())
        {
            TileEntity var6 = var2.getBlockTileEntity(var3, var4, var5);
            FMLClientHandler.instance().displayGuiScreen(var1, FactoryGui.build(var0, var1.inventory, var6));
        }
    }

    public static void openGui(EnumGui var0, EntityPlayer var1, World var2, Entity var3)
    {
        if (Game.isHost(var2))
        {
            if (var0.hasContainer())
            {
                var1.openGui(Harmonion.getMod(), var0.getId(), var2, var3.entityId, -1, 0);
            }
        }
        else if (!var0.hasContainer())
        {
            FMLClientHandler.instance().displayGuiScreen(var1, FactoryGui.build(var0, var1.inventory, var3));
        }
    }

    public Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        if (var5 < 0)
        {
            Entity var8 = var3.getEntityByID(var4);

            if (var8 == null)
            {
                Game.log(Level.WARNING, "[Server] Entity not found when opening GUI: {0}", new Object[] {Integer.valueOf(var4)});
                return null;
            }
            else
            {
                return FactoryContainer.build(EnumGui.fromId(var1), var2.inventory, var8);
            }
        }
        else
        {
            TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
            
            switch (var1)
            {
                case 8:
                    return new ContainerBatteryBox(var2.inventory, (TileBatteryBox)CoreLib.getTileEntity(var3, var4, var5, var6, TileBatteryBox.class));

                default:
                    return FactoryContainer.build(EnumGui.fromId(var1), var2.inventory, var7);
            }
        }
    }

    public Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
        if (var5 < 0)
        {
            Entity var8 = var3.getEntityByID(var4);

            if (var8 == null)
            {
                Game.log(Level.WARNING, "[Client] Entity not found when opening GUI: {0}", new Object[] {Integer.valueOf(var4)});
                return null;
            }
            else
            {
                return FactoryGui.build(EnumGui.fromId(var1), var2.inventory, var8);
            }
        }
        else
        {
            TileEntity var7 = var3.getBlockTileEntity(var4, var5, var6);
            switch (var1)
            {
                case 8:
                    return new GuiBatteryBox(var2.inventory, (TileBatteryBox)CoreLib.getGuiTileEntity(var3, var4, var5, var6, TileBatteryBox.class));
                    
                default:
                	return FactoryGui.build(EnumGui.fromId(var1), var2.inventory, var7);
            }
        }
    }
}
