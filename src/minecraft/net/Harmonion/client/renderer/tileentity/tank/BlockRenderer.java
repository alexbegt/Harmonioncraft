package net.Harmonion.client.renderer.tileentity.tank;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class BlockRenderer implements ISimpleBlockRenderingHandler, IInvRenderer
{
    private Map blockRenderers = new HashMap();
    private Map itemRenderers = new HashMap();
    private ICombinedRenderer defaultRenderer = new BlockRenderer$DefaultRenderer(this, (BlockRenderer$1)null);
    private final ItemRenderer itemRenderer;
    private final Block block;

    public BlockRenderer(Block var1)
    {
        this.block = var1;
        this.itemRenderer = new ItemRenderer(this);
    }

    public void addCombinedRenderer(int var1, ICombinedRenderer var2)
    {
        this.blockRenderers.put(Integer.valueOf(var1), var2);
        this.itemRenderers.put(Integer.valueOf(var1), var2);
    }

    public void addBlockRenderer(int var1, IBlockRenderer var2)
    {
        this.blockRenderers.put(Integer.valueOf(var1), var2);
    }

    public void addItemRenderer(int var1, IInvRenderer var2)
    {
        this.itemRenderers.put(Integer.valueOf(var1), var2);
    }

    public void setDefaultRenderer(ICombinedRenderer var1)
    {
        this.defaultRenderer = var1;
    }

    public IItemRenderer getItemRenderer()
    {
        return this.itemRenderer;
    }

    public void renderInventoryBlock(Block var1, int var2, int var3, RenderBlocks var4) {}

    public boolean renderWorldBlock(IBlockAccess var1, int var2, int var3, int var4, Block var5, int var6, RenderBlocks var7)
    {
        if (!this.blockRenderers.isEmpty())
        {
            int var8 = var1.getBlockMetadata(var2, var3, var4);
            IBlockRenderer var9 = (IBlockRenderer)this.blockRenderers.get(Integer.valueOf(var8));

            if (var9 != null)
            {
                var9.renderBlock(var7, var1, var2, var3, var4, var5);
                return true;
            }
        }

        this.defaultRenderer.renderBlock(var7, var1, var2, var3, var4, var5);
        return true;
    }

    public void renderItem(RenderBlocks var1, ItemStack var2, ItemRenderType var3)
    {
        IInvRenderer var4 = (IInvRenderer)this.itemRenderers.get(Integer.valueOf(var2.getItemDamage()));

        if (var4 != null)
        {
            var4.renderItem(var1, var2, var3);
        }
        else
        {
            this.defaultRenderer.renderItem(var1, var2, var3);
        }
    }

    public boolean shouldRender3DInInventory()
    {
        return false;
    }

    public int getRenderId()
    {
        return this.block.getRenderType();
    }

    public Block getBlock()
    {
        return this.block;
    }

    static Block access$100(BlockRenderer var0)
    {
        return var0.block;
    }
}
