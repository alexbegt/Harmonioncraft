package Harmonioncraft.common.dimension;

import net.minecraft.src.MapGenStructure;
import net.minecraft.src.StructureStart;

public class MapGenHMCMajorFeature extends MapGenStructure {
	
    protected boolean canSpawnStructureAtCoords(int var1, int var2)
    {
        return HMCFeature.getFeatureDirectlyAt(var1, var2, this.worldObj).structureEnabled;
    }

    protected StructureStart getStructureStart(int var1, int var2)
    {
        return new StructureHMCMajorFeatureStart(this.worldObj, this.rand, var1, var2);
    }
    
}