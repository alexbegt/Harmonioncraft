package net.Harmonion.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class RenderBlockHelper
{
    public static void DrawFaces(RenderBlocks var0, Block var1, int var2, boolean var3)
    {
        DrawFaces(var0, var1, var2, var2, var2, var2, var2, var2, var3);
    }

    public static void DrawFaces(RenderBlocks var0, Block var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8)
    {
        Tessellator var9 = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        var9.startDrawingQuads();
        var9.setNormal(0.0F, -1.0F, 0.0F);
        var0.renderBottomFace(var1, 0.0D, 0.0D, 0.0D, var2);
        var9.draw();
        var9.startDrawingQuads();
        var9.setNormal(0.0F, 1.0F, 0.0F);
        var0.renderTopFace(var1, 0.0D, 0.0D, 0.0D, var3);
        var9.draw();
        var9.startDrawingQuads();
        var9.setNormal(0.0F, 0.0F, 1.0F);
        var0.renderWestFace(var1, 0.0D, 0.0D, 0.0D, var4);
        var9.draw();
        var9.startDrawingQuads();
        var9.setNormal(0.0F, 0.0F, -1.0F);
        var0.renderEastFace(var1, 0.0D, 0.0D, 0.0D, var5);
        var9.draw();
        var9.startDrawingQuads();
        var9.setNormal(1.0F, 0.0F, 0.0F);
        var0.renderSouthFace(var1, 0.0D, 0.0D, 0.0D, var6);
        var9.draw();
        var9.startDrawingQuads();
        var9.setNormal(-1.0F, 0.0F, 0.0F);
        var0.renderNorthFace(var1, 0.0D, 0.0D, 0.0D, var7);
        var9.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    public static int setBrightness(IBlockAccess var0, int var1, int var2, int var3, Block var4)
    {
        Tessellator var5 = Tessellator.instance;
        int var6 = var4.getMixedBrightnessForBlock(var0, var1, var2, var3);
        var5.setBrightness(var6);
        float var7 = 1.0F;
        int var8 = var4.colorMultiplier(var0, var1, var2, var3);
        float var9 = (float)(var8 >> 16 & 255) / 255.0F;
        float var10 = (float)(var8 >> 8 & 255) / 255.0F;
        float var11 = (float)(var8 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
            float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
            float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
            var9 = var12;
            var10 = var13;
            var11 = var14;
        }

        var5.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
        return var6;
    }

    public static void renderAllSides(IBlockAccess var0, int var1, int var2, int var3, Block var4, RenderBlocks var5, int var6)
    {
        renderAllSides(var0, var1, var2, var3, var4, var5, var6, true);
    }

    public static void renderAllSides(IBlockAccess var0, int var1, int var2, int var3, Block var4, RenderBlocks var5, int var6, boolean var7)
    {
        if (var7 || var4.shouldSideBeRendered(var0, var1 + 1, var2, var3, 6))
        {
            var5.renderSouthFace(var4, (double)var1, (double)var2, (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1 - 1, var2, var3, 6))
        {
            var5.renderNorthFace(var4, (double)var1, (double)var2, (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2, var3 + 1, 6))
        {
            var5.renderWestFace(var4, (double)var1, (double)var2, (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2, var3 - 1, 6))
        {
            var5.renderEastFace(var4, (double)var1, (double)var2, (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2 + 1, var3, 6))
        {
            var5.renderTopFace(var4, (double)var1, (double)var2, (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2 - 1, var3, 6))
        {
            var5.renderBottomFace(var4, (double)var1, (double)var2, (double)var3, var6);
        }
    }

    public static void renderAllSidesInverted(IBlockAccess var0, int var1, int var2, int var3, Block var4, RenderBlocks var5, int var6, boolean var7)
    {
        if (var7 || var4.shouldSideBeRendered(var0, var1 + 1, var2, var3, 6))
        {
            var5.renderSouthFace(var4, (double)(var1 - 1), (double)var2, (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1 - 1, var2, var3, 6))
        {
            var5.renderNorthFace(var4, (double)(var1 + 1), (double)var2, (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2, var3 + 1, 6))
        {
            var5.renderWestFace(var4, (double)var1, (double)var2, (double)(var3 - 1), var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2, var3 - 1, 6))
        {
            var5.renderEastFace(var4, (double)var1, (double)var2, (double)(var3 + 1), var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2 + 1, var3, 6))
        {
            var5.renderTopFace(var4, (double)var1, (double)(var2 - 1), (double)var3, var6);
        }

        if (var7 || var4.shouldSideBeRendered(var0, var1, var2 - 1, var3, 6))
        {
            var5.renderBottomFace(var4, (double)var1, (double)(var2 + 1), (double)var3, var6);
        }
    }
}
