package net.Harmonion.carts.render;

import net.Harmonion.carts.carts.EntityCartChest;
import net.Harmonion.carts.carts.EntityCartTNT;
import net.Harmonion.carts.carts.ICartRenderInterface;
import net.Harmonion.carts.carts.util.FakeBlockRenderInfo;
import net.Harmonion.carts.carts.util.IExplosiveCart;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelMinecart;
import net.minecraft.src.Render;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Vec3;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

public class RenderCart extends Render
{
    protected ModelBase modelMinecart;
    private static final int[] TANK_TEXTURE = new int[] {230, 230, 229, 229, 229, 229};
    private static final int LIGHT_ON_TEXTURE = 209;
    private static final int LIGHT_OFF_TEXTURE = 210;
    private static final int LIGHT_BASE_TEXTURE = 211;
    private final FakeBlockRenderInfo fakeBlock = new FakeBlockRenderInfo();
    private final FakeBlockRenderInfo lightBlock = new FakeBlockRenderInfo(0.375F, 0.375F, 0.375F, 0.625F, 0.625F, 0.625F);
    private final FakeBlockRenderInfo lightBaseBlock = new FakeBlockRenderInfo(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.0625F, 0.6875F);
    private final FakeBlockRenderInfo bucketSign = new FakeBlockRenderInfo();
    private final FakeBlockRenderInfo fillBlock = new FakeBlockRenderInfo(0.4F, 0.0F, 0.4F, 0.6F, 0.999F, 0.6F);
    private static final float FILTER_SCALE_X = 1.38F;
    private static final float FILTER_SCALE_Y = 0.5F;
    private static final float FILTER_SCALE_Z = 0.5F;

    public RenderCart()
    {
        this.shadowSize = 0.5F;
        this.modelMinecart = new ModelMinecart();
        this.bucketSign.template = Block.glass;
        this.bucketSign.renderTop = false;
        this.bucketSign.renderBottom = false;
        this.bucketSign.renderEast = false;
        this.bucketSign.renderWest = false;
        this.lightBaseBlock.texture[0] = 211;
    }

    public void renderCart(EntityMinecart var1, double var2, double var4, double var6, float var8, float var9)
    {
        GL11.glPushMatrix();
        long var10 = (long)var1.entityId * 493286711L;
        var10 = var10 * var10 * 4392167121L + var10 * 98761L;
        float var12 = (((float)(var10 >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float var13 = (((float)(var10 >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float var14 = (((float)(var10 >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        GL11.glTranslatef(var12, var13, var14);
        double var15 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var9;
        double var17 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var9;
        double var19 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var9;
        double var21 = 0.30000001192092896D;
        Vec3 var23 = var1.func_70489_a(var15, var17, var19);
        float var24 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;

        if (var23 != null)
        {
            Vec3 var25 = var1.func_70495_a(var15, var17, var19, var21);
            Vec3 var26 = var1.func_70495_a(var15, var17, var19, -var21);

            if (var25 == null)
            {
                var25 = var23;
            }

            if (var26 == null)
            {
                var26 = var23;
            }

            var2 += var23.xCoord - var15;
            var4 += (var25.yCoord + var26.yCoord) / 2.0D - var17;
            var6 += var23.zCoord - var19;
            Vec3 var27 = var26.addVector(-var25.xCoord, -var25.yCoord, -var25.zCoord);

            if (var27.lengthVector() != 0.0D)
            {
                var27 = var27.normalize();
                var8 = (float)(Math.atan2(var27.zCoord, var27.xCoord) * 180.0D / Math.PI);
                var24 = (float)(Math.atan(var27.yCoord) * 73.0D);
            }
        }

        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        GL11.glRotatef(180.0F - var8, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
        float var32 = (float)var1.func_70496_j() - var9;
        float var35 = (float)var1.getDamage() - var9;

        if (var35 < 0.0F)
        {
            var35 = 0.0F;
        }

        float var33;

        if (var32 > 0.0F)
        {
            var33 = MathHelper.sin(var32) * var32 * var35 / 10.0F;
            var33 = Math.min(var33, 0.8F);
            var33 = Math.copySign(var33, (float)var1.func_70493_k());
            GL11.glRotatef(var33, 1.0F, 0.0F, 0.0F);
        }

        var33 = var1.getBrightness(var9);
        var33 += (1.0F - var33) * 0.4F;

        if (var1.getClass() != EntityMinecart.class)
        {
            float var28 = 0.75F;
            GL11.glScalef(var28, var28, var28);
            GL11.glPushAttrib(64);

            if (var1 instanceof EntityCartChest)
            {
                this.loadTexture("/terrain.png");
                GL11.glTranslatef(0.0F, 0.5F, 0.0F);
                this.renderBlocks.renderBlockAsItem(Block.chest, 0, var33);
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.5F, 0.0F, -0.5F);
            }
            else if (var1 instanceof IExplosiveCart && var1 instanceof ICartRenderInterface)
            {
                this.renderTNTCart((EntityCartTNT)var1, var33, var9);
            }
            else if (var1 instanceof ICartRenderInterface)
            {
                GL11.glTranslatef(0.0F, 0.3125F, 0.0F);
                Block var29 = ((ICartRenderInterface)var1).getBlock();
                ForgeHooksClient.bindTexture(var29.getTextureFile(), 0);
                this.renderBlocks.renderBlockAsItem(var29, ((ICartRenderInterface)var1).getBlockMetadata(), var33);
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.3125F, 0.0F);
                ForgeHooksClient.unbindTexture();
            }

            GL11.glScalef(1.0F / var28, 1.0F / var28, 1.0F / var28);
            GL11.glPopAttrib();
        }

        int var34 = 16777215;
        float var36 = (float)(var34 >> 16 & 255) / 255.0F;
        float var30 = (float)(var34 >> 8 & 255) / 255.0F;
        float var31 = (float)(var34 & 255) / 255.0F;
        GL11.glColor4f(var36 * var33, var30 * var33, var31 * var33, 1.0F);
        
        if (var1 instanceof EntityCartTNT)
        {
            this.loadTexture("/railcraft/client/textures/cart_wood.png");
        }
        else
        {
            this.loadTexture("/item/cart.png");
        }

        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelMinecart.render(var1, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.renderCart((EntityMinecart)var1, var2, var4, var6, var8, var9);
    }
    
    public void renderTNTCart(EntityCartTNT var1, float var2, float var3)
    {
        Block var4 = var1.getBlock();
        int var5 = var1.getBlockMetadata();
        GL11.glPushMatrix();
        this.loadTexture("/terrain.png");
        GL11.glTranslatef(0.0F, 0.3125F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        float var7;

        if (var1.isPrimed() && (float)var1.getFuse() - var3 + 1.0F < 10.0F)
        {
            var7 = 1.0F - ((float)var1.getFuse() - var3 + 1.0F) / 10.0F;

            if (var7 < 0.0F)
            {
                var7 = 0.0F;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            var7 *= var7;
            var7 *= var7;
            var7 = 1.0F + var7 * 0.3F;
            GL11.glScalef(var7, var7, var7);
        }

        (new RenderBlocks()).renderBlockAsItem(var4, var5, var2);

        if (var1.isPrimed() && var1.getFuse() / 5 % 2 == 0)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            var7 = (1.0F - ((float)var1.getFuse() - var3 + 1.0F) / 100.0F) * 0.8F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var7);
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            this.renderBlocks.renderBlockAsItem(var4, var5, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        GL11.glPopMatrix();
    }
}
