package Harmonioncraft.common.dimension;

public class HMCBiomeOcean extends HMCBiomeBase
{
    public HMCBiomeOcean(int var1)
    {
        super(var1);
        this.minHeight = -1.9F;
        this.maxHeight = 0.5F;
        this.temperature = 0.66F;
        this.rainfall = 1.0F;
        this.spawnableCreatureList.clear();
    }
}