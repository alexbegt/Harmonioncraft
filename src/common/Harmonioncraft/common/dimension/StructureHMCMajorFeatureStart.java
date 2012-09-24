package Harmonioncraft.common.dimension;

import java.util.Random;

import net.minecraft.src.StructureComponent;
import net.minecraft.src.StructureStart;
import net.minecraft.src.World;

public class StructureHMCMajorFeatureStart  extends StructureStart
{
    public HMCFeature feature;

    public StructureHMCMajorFeatureStart(World var1, Random var2, int var3, int var4)
    {
        int var5 = (var3 << 4) + 8;
        int var6 = (var4 << 4) + 8;
        int var7 = HMCWorld.SEALEVEL + 1;
        this.feature = HMCFeature.getFeatureDirectlyAt(var3, var4, var1);
       // StructureComponent var8 = this.makeFirstComponent(var1, var2, this.feature, var5, var7, var6);

        //if (var8 != null)
        //{
        //    this.components.add(var8);
        //    var8.buildComponent(var8, this.components, var2);
        //}

        this.updateBoundingBox();
    }

    /*public StructureComponent makeFirstComponent(World var1, Random var2, HMCFeature var3, int var4, int var5, int var6)
    {
        return (StructureComponent)(var3 == HMCFeature.nagaLair ? new ComponentTFNagaCourtyard(var1, var2, 0, var4, var5, var6) : (var3 == HMCFeature.hedgeMaze ? new ComponentTFHedgeMaze(var1, var2, 0, var4, var5, var6) : (var3 == TFFeature.hill1 ? new ComponentTFHollowHill(var1, var2, 0, 1, var4, var5, var6) : (var3 == TFFeature.hill2 ? new ComponentTFHollowHill(var1, var2, 0, 2, var4, var5, var6) : (var3 == TFFeature.hill3 ? new ComponentTFHollowHill(var1, var2, 0, 3, var4, var5, var6) : (var3 == TFFeature.wizardTower ? new ComponentTFTowerMain(var1, var2, 0, var4, var5, var6) : (var3 == TFFeature.questGrove ? new ComponentTFQuestGrove(var1, var2, 0, var4, var5, var6) : (var3 == TFFeature.hydraLair ? new ComponentTFHydraLair(var1, var2, 0, var4, var5, var6) : null))))))));
    }*/

    /**
     * currently only defined for Villages, returns true if Village has more than 2 non-road components
     */
    public boolean isSizeableStructure()
    {
        return this.feature.structureEnabled;
    }
}