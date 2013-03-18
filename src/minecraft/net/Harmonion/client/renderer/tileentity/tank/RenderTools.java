package net.Harmonion.client.renderer.tileentity.tank;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class RenderTools
{
    public static final int SIGNAL_BRIGHTNESS = 210;
    public static final int BOX_BRIGHTNESS = 165;
    public static final float PIXEL = 0.0625F;

    public static boolean renderStandardBlock(RenderBlocks var0, Block var1, int var2, int var3, int var4)
    {
        var0.setRenderBoundsFromBlock(var1);
        return var0.renderStandardBlock(var1, var2, var3, var4);
    }

    public static boolean renderStandardBlockWithColorMultiplier(RenderBlocks var0, Block var1, int var2, int var3, int var4)
    {
        var0.setRenderBoundsFromBlock(var1);
        int var5 = var1.colorMultiplier(var0.blockAccess, var2, var3, var4);
        float var6 = (float)(var5 >> 16 & 255) / 255.0F;
        float var7 = (float)(var5 >> 8 & 255) / 255.0F;
        float var8 = (float)(var5 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var9 = (var6 * 30.0F + var7 * 59.0F + var8 * 11.0F) / 100.0F;
            float var10 = (var6 * 30.0F + var7 * 70.0F) / 100.0F;
            float var11 = (var6 * 30.0F + var8 * 70.0F) / 100.0F;
            var6 = var9;
            var7 = var10;
            var8 = var11;
        }

        return var0.renderStandardBlockWithColorMultiplier(var1, var2, var3, var4, var6, var7, var8);
    }

    public static void renderBlockSideWithBrightness(RenderBlocks var0, IBlockAccess var1, Block var2, int var3, int var4, int var5, int var6, int var7)
    {
        var0.setRenderBoundsFromBlock(var2);
        var0.enableAO = false;
        Tessellator var8 = Tessellator.instance;
        var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var8.setBrightness(var7);

        if (var6 == 0)
        {
            var0.renderBottomFace(var2, (double)var3, (double)var4, (double)var5, var2.getBlockTexture(var1, var3, var4, var5, 0));
        }
        else if (var6 == 1)
        {
            var0.renderTopFace(var2, (double)var3, (double)var4, (double)var5, var2.getBlockTexture(var1, var3, var4, var5, 1));
        }
        else if (var6 == 2)
        {
            var0.renderEastFace(var2, (double)var3, (double)var4, (double)var5, var2.getBlockTexture(var1, var3, var4, var5, 2));
        }
        else if (var6 == 3)
        {
            var0.renderWestFace(var2, (double)var3, (double)var4, (double)var5, var2.getBlockTexture(var1, var3, var4, var5, 3));
        }
        else if (var6 == 4)
        {
            var0.renderNorthFace(var2, (double)var3, (double)var4, (double)var5, var2.getBlockTexture(var1, var3, var4, var5, 4));
        }
        else if (var6 == 5)
        {
            var0.renderSouthFace(var2, (double)var3, (double)var4, (double)var5, var2.getBlockTexture(var1, var3, var4, var5, 5));
        }
    }

    public static void renderBlockOnInventory(RenderBlocks var0, Block var1, int var2, float var3)
    {
        renderBlockOnInventory(var0, var1, var2, var3, -1);
    }

    public static void renderBlockOnInventory(RenderBlocks var0, Block var1, int var2, float var3, int var4)
    {
        Tessellator var5 = Tessellator.instance;
        boolean var6 = var1.blockID == Block.grass.blockID;
        int var7;
        float var8;
        float var9;
        float var10;

        if (var0.useInventoryTint)
        {
            var7 = var1.getRenderColor(var2);

            if (var6)
            {
                var7 = 16777215;
            }

            var8 = (float)(var7 >> 16 & 255) / 255.0F;
            var9 = (float)(var7 >> 8 & 255) / 255.0F;
            var10 = (float)(var7 & 255) / 255.0F;
            GL11.glColor4f(var8 * var3, var9 * var3, var10 * var3, 1.0F);
        }

        var1.setBlockBoundsForItemRender();
        var0.setRenderBoundsFromBlock(var1);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        if (var4 == 0 || var4 == -1)
        {
            var5.startDrawingQuads();
            var5.setNormal(0.0F, -1.0F, 0.0F);
            var0.renderBottomFace(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSideAndMetadata(0, var2));
            var5.draw();
        }

        if (var6 && var0.useInventoryTint)
        {
            var7 = var1.getRenderColor(var2);
            var8 = (float)(var7 >> 16 & 255) / 255.0F;
            var9 = (float)(var7 >> 8 & 255) / 255.0F;
            var10 = (float)(var7 & 255) / 255.0F;
            GL11.glColor4f(var8 * var3, var9 * var3, var10 * var3, 1.0F);
        }

        if (var4 == 1 || var4 == -1)
        {
            var5.startDrawingQuads();
            var5.setNormal(0.0F, 1.0F, 0.0F);
            var0.renderTopFace(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSideAndMetadata(1, var2));
            var5.draw();
        }

        if (var6 && var0.useInventoryTint)
        {
            GL11.glColor4f(var3, var3, var3, 1.0F);
        }

        if (var4 == 2 || var4 == -1)
        {
            var5.startDrawingQuads();
            var5.setNormal(0.0F, 0.0F, -1.0F);
            var0.renderEastFace(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSideAndMetadata(2, var2));
            var5.draw();
        }

        if (var4 == 3 || var4 == -1)
        {
            var5.startDrawingQuads();
            var5.setNormal(0.0F, 0.0F, 1.0F);
            var0.renderWestFace(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSideAndMetadata(3, var2));
            var5.draw();
        }

        if (var4 == 4 || var4 == -1)
        {
            var5.startDrawingQuads();
            var5.setNormal(-1.0F, 0.0F, 0.0F);
            var0.renderNorthFace(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSideAndMetadata(4, var2));
            var5.draw();
        }

        if (var4 == 5 || var4 == -1)
        {
            var5.startDrawingQuads();
            var5.setNormal(1.0F, 0.0F, 0.0F);
            var0.renderSouthFace(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSideAndMetadata(5, var2));
            var5.draw();
        }

        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
}
