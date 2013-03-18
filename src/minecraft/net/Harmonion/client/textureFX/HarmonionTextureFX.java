package net.Harmonion.client.textureFX;

import cpw.mods.fml.client.FMLTextureFX;
import net.minecraft.client.Minecraft;

public abstract class HarmonionTextureFX extends FMLTextureFX
{
    public HarmonionTextureFX(int var1, String var2)
    {
        super(var1);
        this.tileImage = Minecraft.getMinecraft().renderEngine.getTexture(var2);
    }
}
