package net.Harmonion.block;

import java.util.List;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.Harmonion.client.renderer.tileentity.RenderHarmonion;
import net.Harmonion.item.ModItems;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockHarmonionOre extends BlockHarmonion {

	public BlockHarmonionOre(int var1, int var2)
    {
        super(var1, var2, Material.iron);
        this.setHardness(1.5F);
        this.setResistance(8.0F);
        this.setTickRandomly(true);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return true;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return RenderHarmonion.renderID;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return var2 < 6 ? this.blockIndexInTexture + var2 % 7 : (var2 == 11 ? 21 : 32);
    }

    public int quantityDropped(int var1, int var2, Random var3)
    {
        return var1 == 1 ? 3 + var3.nextInt(2) : 1;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 == 1 ? 0 : var1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        switch (var1)
        {
            case 0:
            	return this.blockID;

            case 1:
                return ModItems.Harmonionlegs.shiftedIndex;

            default:
                return this.blockID;
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 3; ++var4)
        {
            var3.add(new ItemStack(this, 1, var4));
        }
    }

    public boolean canCreatureSpawn(EnumCreatureType var1, World var2, int var3, int var4, int var5)
    {
        return var2.getBlockMetadata(var3, var4, var5) < 6;
    }

    public boolean isBeaconBase(World var1, int var2, int var3, int var4, int var5, int var6, int var7)
    {
        return var1.getBlockMetadata(var2, var3, var4) >= 6;
    }
    
}
