package net.Harmonion.core.block.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import java.awt.Color;
import net.minecraft.src.Block;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class RenderHarmonion extends RenderBlockHelper implements ISimpleBlockRenderingHandler
{
    public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
    private static final Color[] colors = new Color[] {new Color(0, 100, 255), Color.GREEN, Color.RED, (new Color(50, 50, 50)).brighter(), Color.WHITE, Color.WHITE.darker()};
    private static final float minSize = 0.01F;
    private static final float maxSize = 0.99F;

    public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4)
    {
        var1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        var4.func_83018_a(var1);

        if (var2 < 5)
        {
            DrawFaces(var4, var1, var2 % 6, false);
        }
        else if (var2 == 5)
        {
            DrawFaces(var4, var1, 5, false);
        }
        else if (var2 > 5 && var2 < 11)
        {
            DrawFaces(var4, var1, 32, false);
        }
        else if (var2 == 11)
        {
            DrawFaces(var4, var1, 21, false);
        }

        Color var5 = colors[var2 % 6];
        float var6 = (float)var5.getRed() / 255.0F;
        float var7 = (float)var5.getGreen() / 255.0F;
        float var8 = (float)var5.getBlue() / 255.0F;
        GL11.glColor3f(var6, var7, var8);
        var1.setBlockBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99F, 0.99F);
        var4.func_83018_a(var1);

        if (var2 < 5)
        {
            DrawFaces(var4, var1, 16 + var2 % 6, false);
        }
        else if (var2 == 5)
        {
            DrawFaces(var4, var1, 20, false);
        }
        else if (var2 > 5)
        {
            DrawFaces(var4, var1, 15, false);
        }

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        var1.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        var4.func_83018_a(var1);
    }

    public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
    {
        setBrightness(var1, var2, var3, var4, var5);
        int var9 = var1.getBlockMetadata(var2, var3, var4);
        var5.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        var7.func_83018_a(var5);

        if (var9 == 11)
        {
            var7.renderStandardBlock(var5, var2, var3, var4);
        }
        else
        {
            var7.renderStandardBlock(var5, var2, var3, var4);
            Tessellator var10 = Tessellator.instance;
            var10.setColorOpaque_I(colors[var9 % 6].getRGB());
            var10.setBrightness(var9 == 5 ? 175 : 200);
            var5.setBlockBounds(0.01F, 0.01F, 0.01F, 0.99F, 0.99F, 0.99F);
            var7.func_83018_a(var5);

            if (var9 == 5)
            {
                renderAllSides(var1, var2, var3, var4, var5, var7, 20, false);
            }
            else
            {
                renderAllSides(var1, var2, var3, var4, var5, var7, var9 > 5 ? 15 : 16 + var9 % 6, false);
            }
        }

        var7.overrideBlockTexture = -1;
        var5.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        var7.func_83018_a(var5);
        return true;
    }

    public boolean shouldRender3DInInventory()
    {
        return true;
    }

    public int getRenderId()
    {
        return renderID;
    }
}
