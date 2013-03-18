package net.Harmonion.client.renderer.tileentity.tank;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class BlockRenderer$DoNothingRenderer implements IBlockRenderer
{
    final BlockRenderer this$0;

    protected BlockRenderer$DoNothingRenderer(BlockRenderer var1)
    {
        this.this$0 = var1;
    }

    public void renderBlock(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6) {}
}
