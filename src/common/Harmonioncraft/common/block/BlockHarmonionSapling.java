package Harmonioncraft.common.block;

import java.util.List;
import java.util.Random;

import Harmonioncraft.common.Harmonioncraft;
import Harmonioncraft.common.lib.Reference;
import Harmonioncraft.common.worldgen.WorldGenHarmonionTree;
import net.minecraft.src.BlockSapling;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class BlockHarmonionSapling extends BlockSapling
{
    public BlockHarmonionSapling(int var1, int var2)
    {
        super(var1, var2);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return this.blockIndexInTexture;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (Harmonioncraft.proxy.isSimulating())
        {
            if (!this.canBlockStay(var1, var2, var3, var4))
            {
                this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }
            else
            {
                if (var1.getBlockLightValue(var2, var3 + 1, var4) >= 9 && var5.nextInt(30) == 0)
                {
                    this.growTree(var1, var2, var3, var4, var5);
                }
            }
        }
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World var1, int var2, int var3, int var4, Random var5)
    {
        (new WorldGenHarmonionTree()).grow(var1, var2, var3, var4, var5);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    protected int damageDropped(int var1)
    {
        return 0;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!Harmonioncraft.proxy.isSimulating())
        {
            return false;
        }
        else
        {
            ItemStack var10 = var5.getCurrentEquippedItem();

            if (var10 == null)
            {
                return false;
            }
            else
            {
                if (var10.getItem() == Item.dyePowder && var10.getItemDamage() == 15)
                {
                    this.growTree(var1, var2, var3, var4, var1.rand);

                    if (!var5.capabilities.isCreativeMode)
                    {
                        --var10.stackSize;
                    }

                    var5.swingItem();
                }

                return false;
            }
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
    }
}