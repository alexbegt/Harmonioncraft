package Harmonioncraft.common.dimension;

public class HMCBiomeStream extends HMCBiomeBase
{
    public HMCBiomeStream(int var1)
    {
        super(var1);
        this.minHeight = -0.75F;
        this.maxHeight = -0.1F;
        this.temperature = 0.5F;
        this.rainfall = 1.0F;
        this.getHMCBiomeDecorator().setWaterlilyPerChunk(2);
        this.spawnableCreatureList.clear();
    }
}
