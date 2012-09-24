package Harmonioncraft.common.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordinates;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;

public class HMCFeature {
	public static final HMCFeature nothing = (new HMCFeature(0, 0, "No Feature")).enableDecorations().disableStructure();
    public static final HMCFeature hill1 = (new HMCFeature(1, 1, "Small Hollow Hill")).enableDecorations();
    public static final HMCFeature hill2 = (new HMCFeature(2, 2, "Medium Hollow Hill")).enableDecorations();
    public static final HMCFeature hill3 = (new HMCFeature(3, 3, "Large Hollow Hill")).enableDecorations();
    public static final HMCFeature hedgeMaze = new HMCFeature(4, 2, "Hedge Maze");
    public static final HMCFeature nagaLair = new HMCFeature(5, 3, "Naga Courtyard");
    public static final HMCFeature wizardTower = new HMCFeature(6, 1, "Wizard Tower");
    public static final HMCFeature glacierMaze = (new HMCFeature(7, 2, "Glacier Maze")).disableStructure();
    public static final HMCFeature questIsland = (new HMCFeature(8, 1, "Quest Island")).disableStructure();
    public static final HMCFeature questGrove = new HMCFeature(9, 1, "Quest Grove");
    public static final HMCFeature druidGrove = (new HMCFeature(10, 1, "Druid Grove")).disableStructure();
    public static final HMCFeature floatRuins = (new HMCFeature(11, 3, "Floating Ruins")).disableStructure();
    public static final HMCFeature hydraLair = new HMCFeature(12, 2, "Hydra Lair");
    public static final HMCFeature underground = new HMCFeature(255, 0, "Underground");
    public int featureID;
    public int size;
    public String name;
    public boolean chunkDecorationsEnabled;
    public boolean structureEnabled;
    protected List spawnableMonsterList;

    public HMCFeature(int var1, int var2, String var3)
    {
        this.featureID = var1;
        this.size = var2;
        this.name = var3;
        this.chunkDecorationsEnabled = false;
        this.structureEnabled = true;
        this.spawnableMonsterList = new ArrayList();
    }

    public HMCFeature enableDecorations()
    {
        this.chunkDecorationsEnabled = true;
        return this;
    }

    public HMCFeature disableStructure()
    {
        this.structureEnabled = false;
        return this;
    }

    public HMCFeature addMonster(Class var1, int var2, int var3, int var4)
    {
        this.spawnableMonsterList.add(new SpawnListEntry(var1, var2, var3, var4));
        return this;
    }

    public static HMCFeature getFeatureDirectlyAt(int var0, int var1, World var2)
    {
        if (!(var2.getWorldChunkManager() instanceof HMCWorldChunkManager))
        {
            return nothing;
        }
        else if (!((HMCWorldChunkManager)var2.getWorldChunkManager()).isInFeatureChunk(var0 << 4, var1 << 4))
        {
            return nothing;
        }
        else
        {
            BiomeGenBase var3 = var2.getBiomeGenForCoords((var0 << 4) + 8, (var1 << 4) + 8);
            Random var4 = new Random(var2.getSeed() + (long)(var0 * 25117) + (long)(var1 * 151121));
            int var5 = var4.nextInt(16);

            if (var3 == HMCBiomeBase.glacier && var5 % 2 == 0)
            {
                return glacierMaze;
            }
            else if (var3 == HMCBiomeBase.HMCLake)
            {
                return questIsland;
            }
            else if (var3 == HMCBiomeBase.enchantedForest)
            {
                return questGrove;
            }
            else if (var3 == HMCBiomeBase.fireSwamp)
            {
                return hydraLair;
            }
            else
            {
                switch (var5)
                {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return hill1;

                    case 7:
                    case 8:
                    case 9:
                        return hill2;

                    case 10:
                        return hill3;

                    case 11:
                    case 12:
                        return var3 != HMCBiomeBase.glacier ? hedgeMaze : nothing;

                    case 13:
                        return var3 != HMCBiomeBase.glacier && var3 != HMCBiomeBase.HMCLake ? nagaLair : nothing;

                    case 14:
                    case 15:
                        return wizardTower;

                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    default:
                        return nothing;
                }
            }
        }
    }

    public static HMCFeature getNearestFeature(int var0, int var1, World var2)
    {
        for (int var3 = 1; var3 <= 3; ++var3)
        {
            for (int var4 = -var3; var4 <= var3; ++var4)
            {
                for (int var5 = -var3; var5 <= var3; ++var5)
                {
                    HMCFeature var6 = getFeatureDirectlyAt(var4 + var0, var5 + var1, var2);

                    if (var6.size == var3)
                    {
                        return var6;
                    }
                }
            }
        }

        return nothing;
    }

    public static int[] getNearestCenter(int var0, int var1, World var2)
    {
        for (int var3 = 1; var3 <= 3; ++var3)
        {
            for (int var4 = -var3; var4 <= var3; ++var4)
            {
                for (int var5 = -var3; var5 <= var3; ++var5)
                {
                    if (getFeatureDirectlyAt(var4 + var0, var5 + var1, var2).size == var3)
                    {
                        int[] var6 = new int[] {var4 * 16 + 8, var5 * 16 + 8};
                        return var6;
                    }
                }
            }
        }

        int[] var7 = new int[] {0, 0};
        return var7;
    }

    public static ChunkCoordinates getNearestCenterXYZ(int var0, int var1, World var2)
    {
        int var3 = var0 / 256 * 256 + 8;
        int var4 = var1 / 256 * 256 + 8;
        return new ChunkCoordinates(var3, HMCWorld.SEALEVEL, var4);
    }

    public List getSpawnableList(EnumCreatureType var1)
    {
        return this.spawnableMonsterList;
    }

    static
    {
    }
}
