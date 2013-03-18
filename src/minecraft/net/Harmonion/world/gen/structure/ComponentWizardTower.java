package net.Harmonion.world.gen.structure;

import java.util.List;
import java.util.Random;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.item.ModItems;
import net.Harmonion.util.random.ItemIds;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.common.ChestGenHooks;

public class ComponentWizardTower extends ComponentVillage
{
    private int averageGroundLevel = -1;
    public static final WeightedRandomChestContent[] towerChestContents = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.lightStoneDust.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.glassBottle.itemID, 0, 1, 5, 10), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.fireballCharge.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Item.skull.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(ModItems.Harmonionhelmet.itemID, 1, 1, 1, 1), new WeightedRandomChestContent(ModItems.Harmonionchestplate.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(ModItems.Harmonionlegs.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(ModItems.Harmonionboots.itemID, 0, 1, 1, 1)};

    protected ComponentWizardTower(ComponentVillageStartPiece var1, int var2)
    {
        super(var1, var2);
    }

    public ComponentWizardTower(ComponentVillageStartPiece var1, int var2, Random var3, StructureBoundingBox var4, int var5)
    {
        super(var1, var2);
        this.coordBaseMode = var5;
        this.boundingBox = var4;
    }

    public static Object buildComponent(ComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7)
    {
        StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 5, 12, 5, var6);
        return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null ? new ComponentWizardTower(var0, var7, var2, var8, var6) : null;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3)
    {
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(var1, var3);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 12 - 1, 0);
        }

        this.fillWithBlocks(var1, var3, 2, 1, 2, 4, 11, 4, 0, 0, false);
        this.fillWithBlocks(var1, var3, 2, 0, 2, 4, 0, 4, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(var1, var3, 2, 5, 2, 4, 5, 4, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(var1, var3, 2, 10, 2, 4, 10, 4, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(var1, var3, 1, 0, 2, 1, 11, 4, ModBlocks.HarmonionBlock.blockID, ModBlocks.HarmonionBlock.blockID, false);
        this.fillWithBlocks(var1, var3, 2, 0, 1, 4, 11, 1, ModBlocks.HarmonionBlock.blockID, ModBlocks.HarmonionBlock.blockID, false);
        this.fillWithBlocks(var1, var3, 5, 0, 2, 5, 11, 4, ModBlocks.HarmonionBlock.blockID, ModBlocks.HarmonionBlock.blockID, false);
        this.fillWithBlocks(var1, var3, 2, 0, 5, 4, 11, 5, ModBlocks.HarmonionBlock.blockID, ModBlocks.HarmonionBlock.blockID, false);
        this.fillWithBlocks(var1, var3, 2, 0, 5, 4, 11, 5, ModBlocks.HarmonionBlock.blockID, ModBlocks.HarmonionBlock.blockID, false);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 1, 0, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 1, 0, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 5, 0, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 5, 0, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 1, 5, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 1, 5, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 5, 5, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 5, 5, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 1, 10, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 1, 10, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 5, 10, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.stoneBrick.blockID, 3, 5, 10, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 3, 7, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 3, 8, 1, var3);
        this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 3, 7, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 3, 8, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 3, 2, 5, var3);
        this.placeBlockAtCurrentPosition(var1, Block.thinGlass.blockID, 0, 3, 3, 5, var3);
        int var4 = this.getMetadataWithOffset(Block.ladder.blockID, 4);

        for (int var5 = 1; var5 <= 9; ++var5)
        {
            this.placeBlockAtCurrentPosition(var1, Block.ladder.blockID, var4, 4, var5, 3, var3);
        }

        this.placeBlockAtCurrentPosition(var1, Block.trapdoor.blockID, var4, 4, 10, 3, var3);
        this.placeBlockAtCurrentPosition(var1, Block.glowStone.blockID, 0, 3, 5, 3, var3);
        ChestGenHooks var8 = new ChestGenHooks("towerChestContents", towerChestContents, 4, 9);
        this.generateStructureChestContents(var1, var3, var2, 2, 6, 2, var8.getItems(var2), var8.getCount(var2));
        this.placeBlockAtCurrentPosition(var1, 0, 0, 3, 1, 1, var3);
        this.placeBlockAtCurrentPosition(var1, 0, 0, 3, 2, 1, var3);
        this.placeDoorAtCurrentPosition(var1, var3, var2, 3, 1, 1, this.getMetadataWithOffset(Block.doorWood.blockID, 1));

        if (this.getBlockIdAtCurrentPosition(var1, 3, 0, 0, var3) == 0 && this.getBlockIdAtCurrentPosition(var1, 3, -1, 0, var3) != 0)
        {
            this.placeBlockAtCurrentPosition(var1, Block.stairCompactCobblestone.blockID, this.getMetadataWithOffset(Block.stairCompactCobblestone.blockID, 3), 3, 0, 0, var3);
        }

        for (int var6 = 0; var6 < 12; ++var6)
        {
            for (int var7 = 0; var7 < 5; ++var7)
            {
                this.clearCurrentPositionBlocksUpwards(var1, var7, 12, var6, var3);
                this.fillCurrentPositionBlocksDownwards(var1, ModBlocks.HarmonionBlock.blockID, 0, var7, -1, var6, var3);
            }
        }

        this.spawnVillagers(var1, var3, 7, 1, 1, 1);
        return true;
    }

    /**
     * Gets the replacement block for the current biome
     */
    protected int getBiomeSpecificBlock(int var1, int var2)
    {
        return this.startPiece.inDesert && var1 == Block.stoneBrick.blockID ? Block.sandStone.blockID : super.getBiomeSpecificBlock(var1, var2);
    }

    /**
     * Gets the replacement block metadata for the current biome
     */
    protected int getBiomeSpecificBlockMetadata(int var1, int var2)
    {
        return this.startPiece.inDesert && var1 == Block.stoneBrick.blockID ? 1 : super.getBiomeSpecificBlockMetadata(var1, var2);
    }

    /**
     * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
     */
    protected int getVillagerType(int var1)
    {
        return ItemIds.Harmonion_Villager;
    }
}
