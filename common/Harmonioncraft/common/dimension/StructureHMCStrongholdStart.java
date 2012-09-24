package Harmonioncraft.common.dimension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.src.ComponentStrongholdStairs2;
import net.minecraft.src.StructureComponent;
import net.minecraft.src.StructureStart;
import net.minecraft.src.StructureStrongholdPieces;
import net.minecraft.src.World;

public class StructureHMCStrongholdStart extends StructureStart {
	
    public StructureHMCStrongholdStart(World var1, Random var2, int var3, int var4)
    {
        StructureStrongholdPieces.prepareStructurePieces();
        ComponentStrongholdStairs2 var5 = new ComponentStrongholdStairs2(0, var2, (var3 << 4) + 2, (var4 << 4) + 2);
        var5.getBoundingBox().offset(0, -28, 0);
        System.out.println("Made a stronghold at " + var5.getBoundingBox().minX + ", " + var5.getBoundingBox().minY + ", " + var5.getBoundingBox().minZ);
        this.components.add(var5);
        var5.buildComponent(var5, this.components, var2);
        ArrayList var7 = var5.field_75026_c;

        while (!var7.isEmpty())
        {
            int var8 = var2.nextInt(var7.size());
            StructureComponent var6 = (StructureComponent)var7.remove(var8);
            var6.buildComponent(var5, this.components, var2);
        }

        this.updateBoundingBox();
        this.markAvailableHeight(var1, var2, 1);
    }

    /**
     * offsets the structure Bounding Boxes up to a certain height, typically 63 - 10
     */
    protected void markAvailableHeight(World var1, Random var2, int var3)
    {
        var1.getClass();
        int var4 = 35 - var3;
        int var5 = this.boundingBox.getYSize() + 1;

        if (var5 < var4)
        {
            var5 += var2.nextInt(var4 - var5);
        }

        int var6 = var5 - this.boundingBox.maxY;
        this.boundingBox.offset(0, var6, 0);
        Iterator var8 = this.components.iterator();

        while (var8.hasNext())
        {
            StructureComponent var7 = (StructureComponent)var8.next();
            var7.getBoundingBox().offset(0, var6, 0);
        }
    }
    
}
