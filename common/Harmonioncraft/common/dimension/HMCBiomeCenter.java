package Harmonioncraft.common.dimension;

import net.minecraft.src.Block;

public class HMCBiomeCenter extends HMCBiomeBase
{
    public HMCBiomeCenter(int var1)
    {
        super(var1);
        this.topBlock = (byte)Block.stone.blockID;
        this.fillerBlock = (byte)Block.stone.blockID;
    }
}
