package net.Harmonion.client.renderer.tileentity.tank;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public interface IInvRenderer
{
    void renderItem(RenderBlocks var1, ItemStack var2, ItemRenderType var3);
}
