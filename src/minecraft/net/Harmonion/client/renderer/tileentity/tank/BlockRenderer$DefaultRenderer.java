package net.Harmonion.client.renderer.tileentity.tank;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

class BlockRenderer$DefaultRenderer implements ICombinedRenderer
{
    final BlockRenderer this$0;

    private BlockRenderer$DefaultRenderer(BlockRenderer var1)
    {
        this.this$0 = var1;
    }

    public void renderItem(RenderBlocks var1, ItemStack var2, ItemRenderType var3)
    {
        int var4 = var2.getItemDamage();
        BlockRenderer.access$100(this.this$0).setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        RenderTools.renderBlockOnInventory(var1, BlockRenderer.access$100(this.this$0), var4, 1.0F);
    }

    public void renderBlock(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
        var6.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        RenderTools.renderStandardBlock(var1, var6, var3, var4, var5);
    }

    BlockRenderer$DefaultRenderer(BlockRenderer var1, BlockRenderer$1 var2)
    {
        this(var1);
    }
}
