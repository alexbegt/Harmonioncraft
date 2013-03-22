package net.Harmonion.power;

import java.util.Random;

import net.Harmonion.util.random.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RenderSolarPanel extends RenderCustomBlock
{
    protected RenderContext context = new RenderContext();

    public RenderSolarPanel(Block var1)
    {
        super(var1);
    }

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {}

    public void renderWorldBlock(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, int var6)
    {
        TileMachinePanel var7 = (TileMachinePanel)CoreLib.getTileEntity(var2, var3, var4, var5, TileMachinePanel.class);

        if (var7 != null)
        {
            this.context.setDefaults();
            this.context.setLocalLights(0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F);
            this.context.setPos((double)var3, (double)var4, (double)var5);
            this.context.readGlobalLights(var2, var3, var4, var5);
            this.context.setTex(5, 5, 6, 6, 6, 6);
            this.context.setSize(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
            this.context.setupBox();
            this.context.transform();
            RenderLib.bindTexture(Reference.SPRITE_SHEET_LOCATION + Reference.POWER_BLOCK_SPRITE_SHEET);
            this.context.renderGlobFaces(62);
            RenderLib.unbindTexture();
        }
    }

    public void renderInvBlock(RenderBlocks var1, int var2)
    {
        this.block.setBlockBoundsForItemRender();
        this.context.setDefaults();
        this.context.setPos(-0.5D, -0.5D, -0.5D);
        this.context.useNormal = true;
        RenderLib.bindTexture(Reference.SPRITE_SHEET_LOCATION + Reference.POWER_BLOCK_SPRITE_SHEET);
        Tessellator var3 = Tessellator.instance;
        var3.startDrawingQuads();
        this.context.setTex(5, 5, 6, 6, 6, 6);
        this.context.renderBox(62, 0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
        var3.draw();
        RenderLib.unbindTexture();
        this.context.useNormal = false;
    }
}
