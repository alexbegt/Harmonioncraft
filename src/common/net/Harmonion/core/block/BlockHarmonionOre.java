package net.Harmonion.core.block;

import java.util.List;
import java.util.Random;

import net.Harmonion.core.item.ModItems;
import net.Harmonion.core.lib.Strings;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraftforge.common.MinecraftForge;

public class BlockHarmonionOre extends BlockHarmonion {
	
	public static boolean[] enable = new boolean[4];

    public BlockHarmonionOre(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundStoneFootstep);
        this.setTextureFile(Strings.Sound_Stone_Ore_Name);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
        MinecraftForge.setBlockHarvestLevel(this, 0, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(this, 1, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(this, 2, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(this, 3, "pickaxe", 2);
        this.blockIndexInTexture = 240;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return this.blockIndexInTexture + var2;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 4; ++var4)
        {
            if (enable[var4])
            {
                var3.add(new ItemStack(var1, 1, var4));
            }
        }
    }
}

