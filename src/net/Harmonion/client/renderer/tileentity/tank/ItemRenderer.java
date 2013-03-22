package net.Harmonion.client.renderer.tileentity.tank;

import java.util.Random;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRenderer implements IItemRenderer
{
    private IInvRenderer renderer;
    private final Random rand = new Random();

    public ItemRenderer(IInvRenderer var1)
    {
        this.renderer = var1;
    }

    public boolean handleRenderType(ItemStack var1, ItemRenderType var2)
    {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType var1, ItemStack var2, ItemRendererHelper var3)
    {
        switch (ItemRenderer$1.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[var3.ordinal()])
        {
            case 1:
                return false;

            default:
                return true;
        }
    }

    public void renderItem(ItemRenderType var1, ItemStack var2, Object ... var3)
    {
        switch (ItemRenderer$1.$SwitchMap$net$minecraftforge$client$ItemRenderType[var1.ordinal()])
        {
            case 1:
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                this.renderer.renderItem((RenderBlocks)var3[0], var2, ItemRenderType.EQUIPPED);
                break;

            case 2:
                this.renderer.renderItem((RenderBlocks)var3[0], var2, ItemRenderType.INVENTORY);
                break;

            case 3:
                this.renderEntity((RenderBlocks)var3[0], (EntityItem)var3[1]);
        }
    }

    protected void renderEntity(RenderBlocks var1, EntityItem var2)
    {
        this.rand.setSeed(187L);
        byte var3 = 1;
        ItemStack var4 = var2.getEntityItem();

        if (var4.stackSize > 1)
        {
            var3 = 2;
        }

        if (var4.stackSize > 5)
        {
            var3 = 3;
        }

        if (var4.stackSize > 20)
        {
            var3 = 4;
        }

        float var5 = 0.5F;
        GL11.glScalef(var5, var5, var5);

        for (int var6 = 0; var6 < var3; ++var6)
        {
            GL11.glPushMatrix();

            if (var6 > 0)
            {
                float var7 = (this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F / var5;
                float var8 = (this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F / var5;
                float var9 = (this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F / var5;
                GL11.glTranslatef(var7, var8, var9);
            }

            this.renderer.renderItem(var1, var4, ItemRenderType.ENTITY);
            GL11.glPopMatrix();
        }
    }
}
