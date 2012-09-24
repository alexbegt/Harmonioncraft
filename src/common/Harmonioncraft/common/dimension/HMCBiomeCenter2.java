package Harmonioncraft.common.dimension;

import net.minecraft.src.Block;

public class HMCBiomeCenter2 extends HMCBiomeBase
{
    public HMCBiomeCenter2(int var1)
    {
        super(var1);
        this.topBlock = (byte)Block.stone.blockID;
        this.fillerBlock = (byte)Block.stone.blockID;
    }
}