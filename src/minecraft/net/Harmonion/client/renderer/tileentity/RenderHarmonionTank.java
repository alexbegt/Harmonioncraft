package net.Harmonion.client.renderer.tileentity;

import net.Harmonion.block.tank.TileMultiBlock;
import net.Harmonion.block.tank.TileTankHarmonion;
import net.Harmonion.block.tank.TileTankHarmonionValve;
import net.Harmonion.client.gui.LiquidRenderer;
import net.Harmonion.client.gui.RenderFakeBlock;
import net.Harmonion.liquids.TankManager;
import net.Harmonion.liquids.tanks.StandardTank;
import net.Harmonion.util.Config;
import net.Harmonion.util.misc.FakeBlockRenderInfo;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.liquids.LiquidStack;
import org.lwjgl.opengl.GL11;

public class RenderHarmonionTank extends TileEntitySpecialRenderer
{
    private static final FakeBlockRenderInfo fillBlock = new FakeBlockRenderInfo();

    public RenderHarmonionTank()
    {
        float var1 = 0.0625F;
        fillBlock.minX = 5.0F * var1;
        fillBlock.minZ = 5.0F * var1;
        fillBlock.maxX = 11.0F * var1;
        fillBlock.maxZ = 11.0F * var1;
    }

    private int bindLiquidTexture(LiquidStack var1)
    {
        fillBlock.texture = new int[] {var1.asItemStack().getIconIndex()};
        return LiquidRenderer.bindLiquidTexture(var1);
    }

    private float getVerticalScaleSide(TileMultiBlock var1)
    {
        int var2 = var1.getPatternPositionY();

        if (!Config.allowTankStacking())
        {
            --var2;
        }

        return (float)var2 - 0.3125F;
    }

    private int getTankHeight(TileMultiBlock var1)
    {
        int var2 = var1.getPattern().getPatternHeight();

        if (!Config.allowTankStacking())
        {
            var2 -= 2;
        }

        return var2;
    }

    private void draw()
    {
        this.preGL();
        RenderFakeBlock.renderBlockForEntity(fillBlock, (IBlockAccess)null, 0, 0, 0, false, true);
        this.postGL();
    }

    private void preGL()
    {
        GL11.glPushAttrib(8192);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
    }

    private void postGL()
    {
        GL11.glPopAttrib();
    }

    public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8)
    {
        if (((TileMultiBlock)var1).isStructureValid())
        {
            float var13;
            float var22;

            if (var1 instanceof TileTankHarmonionValve)
            {
                TileTankHarmonionValve var9 = (TileTankHarmonionValve)var1;

                if (var9.isFilling())
                {
                    TankManager var10 = var9.getTankManager();
                    LiquidStack var11 = null;

                    if (var10 != null)
                    {
                        var11 = var10.getTank(0).getLiquid();
                    }

                    if (var11 != null && var11.amount > 0 && Item.itemsList[var11.itemID] != null)
                    {
                        GL11.glPushMatrix();
                        GL11.glDisable(GL11.GL_LIGHTING);

                        if (var9.getPattern().getPatternMarkerChecked(var9.getPatternPositionX(), var9.getPatternPositionY() - 1, var9.getPatternPositionZ()) == 'A')
                        {
                            this.bindLiquidTexture(var11);
                            int var12 = this.getTankHeight(var9);
                            var13 = (float)var12 / 2.0F;
                            float var14 = (float)var12 - 2.0F;
                            GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + var13 - (float)var12 + 1.0F, (float)var6 + 0.5F);
                            GL11.glScalef(1.0F, var14, 1.0F);
                            this.draw();
                        }
                        else if (var9.getPattern().getPatternMarkerChecked(var9.getPatternPositionX() - 1, var9.getPatternPositionY(), var9.getPatternPositionZ()) == 'A')
                        {
                            this.bindLiquidTexture(var11);
                            var22 = this.getVerticalScaleSide(var9);
                            var13 = 0.5F - var22 / 2.0F + 0.1875F;
                            GL11.glTranslatef((float)var2 - 0.5F + 0.3125F, (float)var4 + var13, (float)var6 + 0.5F);
                            GL11.glScalef(1.0F, var22, 1.0F);
                            this.draw();
                        }
                        else if (var9.getPattern().getPatternMarkerChecked(var9.getPatternPositionX() + 1, var9.getPatternPositionY(), var9.getPatternPositionZ()) == 'A')
                        {
                            this.bindLiquidTexture(var11);
                            var22 = this.getVerticalScaleSide(var9);
                            var13 = 0.5F - var22 / 2.0F + 0.1875F;
                            GL11.glTranslatef((float)var2 + 1.5F - 0.3125F, (float)var4 + var13, (float)var6 + 0.5F);
                            GL11.glScalef(1.0F, var22, 1.0F);
                            this.draw();
                        }
                        else if (var9.getPattern().getPatternMarkerChecked(var9.getPatternPositionX(), var9.getPatternPositionY(), var9.getPatternPositionZ() - 1) == 'A')
                        {
                            this.bindLiquidTexture(var11);
                            var22 = this.getVerticalScaleSide(var9);
                            var13 = 0.5F - var22 / 2.0F + 0.1875F;
                            GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + var13, (float)var6 - 0.5F + 0.3125F);
                            GL11.glScalef(1.0F, var22, 1.0F);
                            this.draw();
                        }
                        else if (var9.getPattern().getPatternMarkerChecked(var9.getPatternPositionX(), var9.getPatternPositionY(), var9.getPatternPositionZ() + 1) == 'A')
                        {
                            this.bindLiquidTexture(var11);
                            var22 = this.getVerticalScaleSide(var9);
                            var13 = 0.5F - var22 / 2.0F + 0.1875F;
                            GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + var13, (float)var6 + 1.5F - 0.3125F);
                            GL11.glScalef(1.0F, var22, 1.0F);
                            this.draw();
                        }

                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glPopMatrix();
                    }
                }
            }

            TileTankHarmonion var20 = (TileTankHarmonion)var1;

            if (var20.isMaster() && !var20.isInvalid())
            {
                int var21 = this.getTankHeight(var20);
                float var23 = (float)var21 / 2.0F;
                var22 = (float)(var21 - 2);
                var13 = (float)(var20.getPattern().getPatternWidthX() - 2);
                TankManager var24 = var20.getTankManager();

                if (var24 != null)
                {
                    StandardTank var15 = var24.getTank(0);

                    if (var15 != null)
                    {
                        LiquidStack var16 = var15.getLiquid();

                        if (var16 != null && var16.amount > 0 && Item.itemsList[var16.itemID] != null)
                        {
                            GL11.glPushMatrix();
                            GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + var23 + 0.01F, (float)var6 + 0.5F);
                            this.preGL();
                            GL11.glScalef(var13, var22, var13);
                            int[] var17 = LiquidRenderer.getLiquidDisplayLists(var16);

                            if (var17 != null)
                            {
                                GL11.glPushMatrix();
                                this.bindLiquidTexture(var16);
                                float var18 = (float)var15.getCapacity();
                                float var19 = Math.min((float)var16.amount, var18) / var18;
                                GL11.glCallList(var17[(int)(var19 * 99.0F)]);
                                GL11.glPopMatrix();
                            }

                            this.postGL();
                            GL11.glPopMatrix();
                        }
                    }
                }
            }
        }
    }
}
