package net.Harmonion.client.renderer.tileentity.tank;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IBlockRenderer
{
    void renderBlock(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6);
}
