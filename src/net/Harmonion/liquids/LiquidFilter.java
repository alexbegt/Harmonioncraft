package net.Harmonion.liquids;

import net.minecraft.block.Block;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidFilter
{
    public static final LiquidStack WATER = new LiquidStack(Block.waterStill, 0);
    public static final LiquidStack LAVA = new LiquidStack(Block.lavaStill, 0);
    public static final LiquidStack CREOSOTE = LiquidItems.getPhazonLiquid(0);
}
