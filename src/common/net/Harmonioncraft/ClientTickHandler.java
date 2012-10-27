package net.Harmonioncraft;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import java.lang.reflect.Field;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.WorldClient;

public class ClientTickHandler implements ITickHandler
{
    Field curPlayingStr = null;

    public void tickStart(EnumSet var1, Object ... var2) {}

    public void tickEnd(EnumSet var1, Object ... var2)
    {
        if (var1.equals(EnumSet.of(TickType.RENDER)))
        {
            this.onRenderTick();
        }
        else if (var1.equals(EnumSet.of(TickType.CLIENT)))
        {
            GuiScreen var3 = Minecraft.getMinecraft().currentScreen;

            if (var3 != null)
            {
                this.onTickInGUI(var3);
            }
            else
            {
                this.onTickInGame();
            }
        }
    }

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
    }

    public String getLabel()
    {
        return null;
    }

    public void onRenderTick() {}

    public void onTickInGUI(GuiScreen var1)
    {
        this.onTickInGame();
    }

    public void onTickInGame()
    {
        WorldClient var1 = FMLClientHandler.instance().getClient().theWorld;

        if (var1 != null && !FMLClientHandler.instance().getClient().isGamePaused)
        {
            ;
        }
    }

    static void getField(Field var0, Object var1) throws Exception
    {
        var0.setAccessible(true);
        Field var2 = Field.class.getDeclaredField("modifiers");
        var2.setAccessible(true);
        var2.setInt(var0, var0.getModifiers() & -17);
        var0.set((Object)null, var1);
    }
}
