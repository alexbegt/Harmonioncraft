package Harmonioncraft.common.dimension;

public class HMCBiomeDeepMushrooms extends HMCBiomeBase
{
    public HMCBiomeDeepMushrooms(int var1)
    {
        super(var1);
        this.temperature = 0.8F;
        this.rainfall = 1.0F;
        this.minHeight = 0.15F;
        this.maxHeight = 0.4F;
        this.getHMCBiomeDecorator().setTreesPerChunk(1);
        this.getHMCBiomeDecorator().setMushroomsPerChunk(12);
        this.getHMCBiomeDecorator().setBigMushroomsPerChunk(8);
        this.getHMCBiomeDecorator().myceliumPerChunk = 3;
        this.getHMCBiomeDecorator().chanceCanopyIsMushroom = 0.9F;
    }
}

