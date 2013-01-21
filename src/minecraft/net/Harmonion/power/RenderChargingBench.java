package net.Harmonion.power;

import java.util.Random;

import net.Harmonion.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RenderChargingBench extends RenderCustomBlock
{
    protected RenderContext context = new RenderContext();

    public RenderChargingBench(Block var1)
    {
        super(var1);
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {}

    public void renderWorldBlock(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, int var6)
    {
        TileChargingBench var7 = (TileChargingBench)CoreLib.getTileEntity(var2, var3, var4, var5, TileChargingBench.class);

        if (var7 != null)
        {
            this.context.setDefaults();
            this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
            this.context.setPos((double)var3, (double)var4, (double)var5);
            this.context.readGlobalLights(var2, var3, var4, var5);
            int var8;

            if (var7.Active)
            {
                var8 = 192 + var7.getStorageForRender();
                this.context.setTex(180, 177, var8, var8, 179, 179);
            }
            else
            {
                var8 = 181 + var7.getStorageForRender() + (var7.Powered ? 5 : 0);
                this.context.setTex(180, 176, var8, var8, 178, 178);
            }

            this.context.rotateTextures(var7.Rotation);
            this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            this.context.setupBox();
            this.context.transform();
            RenderLib.bindTexture(Reference.SPRITE_SHEET_LOCATION + Reference.POWER_SPRITE_SHEET);
            this.context.renderGlobFaces(63);
            RenderLib.unbindTexture();
        }
    }

    public void renderInvBlock(RenderBlocks var1, int var2)
    {
        this.block.setBlockBoundsForItemRender();
        this.context.setDefaults();
        this.context.setPos(-0.5D, -0.5D, -0.5D);
        this.context.useNormal = true;
        RenderLib.bindTexture(Reference.SPRITE_SHEET_LOCATION + Reference.POWER_SPRITE_SHEET);
        Tessellator var3 = Tessellator.instance;
        var3.startDrawingQuads();
        this.context.setTex(180, 176, 181, 181, 178, 178);
        this.context.renderBox(63, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        var3.draw();
        RenderLib.unbindTexture();
        this.context.useNormal = false;
    }
}