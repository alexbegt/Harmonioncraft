package Harmonioncraft.common.dimension;

public class HMCBiomeMushrooms extends HMCBiomeBase
{
    public HMCBiomeMushrooms(int var1)
    {
        super(var1);
        this.rainfall = 0.8F;
        this.getHMCBiomeDecorator().setTreesPerChunk(8);
        this.getHMCBiomeDecorator().setMushroomsPerChunk(8);
        this.getHMCBiomeDecorator().setBigMushroomsPerChunk(2);
        this.getHMCBiomeDecorator().chanceCanopyIsMushroom = 0.2F;
    }
}
