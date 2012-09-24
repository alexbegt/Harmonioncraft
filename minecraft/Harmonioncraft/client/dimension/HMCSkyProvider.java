package Harmonioncraft.client.dimension;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import Harmonioncraft.common.dimension.HMCWorld;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GLAllocation;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.Tessellator;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import net.minecraft.src.WorldClient;
import net.minecraftforge.client.SkyProvider;

public class HMCSkyProvider extends SkyProvider
{
    private int starGLCallList = GLAllocation.generateDisplayLists(3);
    private int glSkyList;
    private int glSkyList2;

    @SideOnly(Side.CLIENT)
    public HMCSkyProvider()
    {
        GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator var1 = Tessellator.instance;
        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
        byte var2 = 64;
        int var3 = 256 / var2 + 2;
        float var4 = 16.0F;
        int var5;
        int var6;

        for (var5 = -var2 * var3; var5 <= var2 * var3; var5 += var2)
        {
            for (var6 = -var2 * var3; var6 <= var2 * var3; var6 += var2)
            {
                var1.startDrawingQuads();
                var1.addVertex((double)(var5 + 0), (double)var4, (double)(var6 + 0));
                var1.addVertex((double)(var5 + var2), (double)var4, (double)(var6 + 0));
                var1.addVertex((double)(var5 + var2), (double)var4, (double)(var6 + var2));
                var1.addVertex((double)(var5 + 0), (double)var4, (double)(var6 + var2));
                var1.draw();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
        var4 = -16.0F;
        var1.startDrawingQuads();

        for (var5 = -var2 * var3; var5 <= var2 * var3; var5 += var2)
        {
            for (var6 = -var2 * var3; var6 <= var2 * var3; var6 += var2)
            {
                var1.addVertex((double)(var5 + var2), (double)var4, (double)(var6 + 0));
                var1.addVertex((double)(var5 + 0), (double)var4, (double)(var6 + 0));
                var1.addVertex((double)(var5 + 0), (double)var4, (double)(var6 + var2));
                var1.addVertex((double)(var5 + var2), (double)var4, (double)(var6 + var2));
            }
        }

        var1.draw();
        GL11.glEndList();
    }

    @SideOnly(Side.CLIENT)
    public void render(float var1, WorldClient var2, Minecraft var3)
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Vec3 var4 = this.getTwilightSkyColor();
        float var5 = (float)var4.xCoord;
        float var6 = (float)var4.yCoord;
        float var7 = (float)var4.zCoord;
        float var8;
        float var10;

        if (var3.gameSettings.anaglyph)
        {
            float var9 = (var5 * 30.0F + var6 * 59.0F + var7 * 11.0F) / 100.0F;
            var10 = (var5 * 30.0F + var6 * 70.0F) / 100.0F;
            var8 = (var5 * 30.0F + var7 * 70.0F) / 100.0F;
            var5 = var9;
            var6 = var10;
            var7 = var8;
        }

        GL11.glColor3f(var5, var6, var7);
        Tessellator var17 = Tessellator.instance;
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(var5, var6, var7);
        GL11.glCallList(this.glSkyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.disableStandardItemLighting();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glPushMatrix();
        var8 = 1.0F - var2.getRainStrength(var1);
        var10 = 0.0F;
        float var11 = 0.0F;
        float var12 = 0.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, var8);
        GL11.glTranslatef(var10, var11, var12);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.getRealCelestialAngle(var2, var1) * 360.0F, 1.0F, 0.0F, 0.0F);
        float var13 = 30.0F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        float var14 = 1.0F;

        if (var14 > 0.0F)
        {
            GL11.glColor4f(var14, var14, var14, var14);
            GL11.glCallList(this.starGLCallList);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.0F, 0.0F);
        double var15 = var3.thePlayer.getPosition(var1).yCoord - (double)HMCWorld.SEALEVEL;

        if (var15 < 0.0D)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 12.0F, 0.0F);
            GL11.glCallList(this.glSkyList2);
            GL11.glPopMatrix();
            var11 = 1.0F;
            var12 = -((float)(var15 + 65.0D));
            var13 = -var11;
            var17.startDrawingQuads();
            var17.setColorRGBA_I(0, 255);
            var17.addVertex((double)(-var11), (double)var12, (double)var11);
            var17.addVertex((double)var11, (double)var12, (double)var11);
            var17.addVertex((double)var11, (double)var13, (double)var11);
            var17.addVertex((double)(-var11), (double)var13, (double)var11);
            var17.addVertex((double)(-var11), (double)var13, (double)(-var11));
            var17.addVertex((double)var11, (double)var13, (double)(-var11));
            var17.addVertex((double)var11, (double)var12, (double)(-var11));
            var17.addVertex((double)(-var11), (double)var12, (double)(-var11));
            var17.addVertex((double)var11, (double)var13, (double)(-var11));
            var17.addVertex((double)var11, (double)var13, (double)var11);
            var17.addVertex((double)var11, (double)var12, (double)var11);
            var17.addVertex((double)var11, (double)var12, (double)(-var11));
            var17.addVertex((double)(-var11), (double)var12, (double)(-var11));
            var17.addVertex((double)(-var11), (double)var12, (double)var11);
            var17.addVertex((double)(-var11), (double)var13, (double)var11);
            var17.addVertex((double)(-var11), (double)var13, (double)(-var11));
            var17.addVertex((double)(-var11), (double)var13, (double)(-var11));
            var17.addVertex((double)(-var11), (double)var13, (double)var11);
            var17.addVertex((double)var11, (double)var13, (double)var11);
            var17.addVertex((double)var11, (double)var13, (double)(-var11));
            var17.draw();
        }

        if (var2.provider.isSkyColored())
        {
            GL11.glColor3f(var5 * 0.2F + 0.04F, var6 * 0.2F + 0.04F, var7 * 0.6F + 0.1F);
        }
        else
        {
            GL11.glColor3f(var5, var6, var7);
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -((float)(var15 - 16.0D)), 0.0F);
        GL11.glCallList(this.glSkyList2);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
    }

    private float getRealCelestialAngle(World var1, float var2)
    {
        int var3 = (int)(var1.getWorldTime() % 24000L);
        float var4 = ((float)var3 + var2) / 24000.0F - 0.25F;

        if (var4 < 0.0F)
        {
            ++var4;
        }

        if (var4 > 1.0F)
        {
            --var4;
        }

        float var5 = var4;
        var4 = 1.0F - (float)((Math.cos((double)var4 * Math.PI) + 1.0D) / 2.0D);
        var4 = var5 + (var4 - var5) / 3.0F;
        return var4;
    }

    private Vec3 getTwilightSkyColor()
    {
        return Vec3.getVec3Pool().getVecFromPool(0.16796875D, 0.1796875D, 0.38671875D);
    }

    private void renderStars()
    {
        Random var1 = new Random(10842L);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();

        for (int var3 = 0; var3 < 3000; ++var3)
        {
            double var4 = (double)(var1.nextFloat() * 2.0F - 1.0F);
            double var6 = (double)(var1.nextFloat() * 2.0F - 1.0F);
            double var8 = (double)(var1.nextFloat() * 2.0F - 1.0F);
            double var10 = (double)(0.1F + var1.nextFloat() * 0.25F);
            double var12 = var4 * var4 + var6 * var6 + var8 * var8;

            if (var12 < 1.0D && var12 > 0.01D)
            {
                var12 = 1.0D / Math.sqrt(var12);
                var4 *= var12;
                var6 *= var12;
                var8 *= var12;
                double var14 = var4 * 100.0D;
                double var16 = var6 * 100.0D;
                double var18 = var8 * 100.0D;
                double var20 = Math.atan2(var4, var8);
                double var22 = Math.sin(var20);
                double var24 = Math.cos(var20);
                double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
                double var28 = Math.sin(var26);
                double var30 = Math.cos(var26);
                double var32 = var1.nextDouble() * Math.PI * 2.0D;
                double var34 = Math.sin(var32);
                double var36 = Math.cos(var32);

                for (int var38 = 0; var38 < 4; ++var38)
                {
                    double var39 = 0.0D;
                    double var41 = (double)((var38 & 2) - 1) * var10;
                    double var43 = (double)((var38 + 1 & 2) - 1) * var10;
                    double var45 = var41 * var36 - var43 * var34;
                    double var47 = var43 * var36 + var41 * var34;
                    double var49 = var45 * var28 + var39 * var30;
                    double var51 = var39 * var28 - var45 * var30;
                    double var53 = var51 * var22 - var47 * var24;
                    double var55 = var47 * var22 + var51 * var24;
                    var2.addVertex(var14 + var53, var16 + var49, var18 + var55);
                }
            }
        }

        var2.draw();
    }
}
