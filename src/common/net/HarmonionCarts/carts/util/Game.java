package net.HarmonionCarts.carts.util;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class Game
{
    private static final Logger log = Logger.getLogger("Railcraft");

    public static boolean isHost(World var0)
    {
        return !var0.isRemote;
    }

    public static boolean isNotHost(World var0)
    {
        return var0.isRemote;
    }

    @SideOnly(Side.CLIENT)
    public static World getWorld()
    {
        Minecraft var0 = FMLClientHandler.instance().getClient();
        return var0 != null ? var0.theWorld : null;
    }

    public static void log(Level var0, String var1, Object ... var2)
    {
        String var3 = var1;

        for (int var4 = 0; var4 < var2.length; ++var4)
        {
            var3 = var3.replace("{" + var4 + "}", var2[var4].toString());
        }

        log.log(var0, var3);
    }

    public static void playSound(World var0, int var1, int var2, int var3, String var4, float var5, float var6)
    {
    }

    public static void playSoundAtEntity(Entity var0, String var1, float var2, float var3)
    {
    }

    public static void playFX(World var0, EntityPlayer var1, int var2, int var3, int var4, int var5, int var6)
    {
    }

    static
    {
        log.setParent(FMLCommonHandler.instance().getFMLLogger());
    }
}
