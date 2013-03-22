package net.Harmonion.block;

import java.util.Random;

import net.Harmonion.item.ModItems;
import net.Harmonion.util.random.Reference;
import net.Harmonion.util.random.Strings;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHarmonionDoor extends BlockDoor {
	
	public int spriteIndexTop;
    public int spriteIndexBottom;
    public int itemDropped;

    public BlockHarmonionDoor(int var1, int var2, int var3, Material var4)
    {
        super(var1, var4);
        this.spriteIndexTop = var2;
        this.spriteIndexBottom = var3;
        this.blockIndexInTexture = 37;
        this.disableStats();
        this.setRequiresSelfNotify();
        this.setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
        this.setBlockName(Strings.Sound_Stone_Door_Name);
        
    }

    public BlockHarmonionDoor setItemDropped(int var1)
    {
        this.itemDropped = var1;
        return this;
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        return (var2 & 8) == 8 ? this.spriteIndexTop : this.spriteIndexBottom;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.getBlockTextureFromSideAndMetadata(var5, var1.getBlockMetadata(var2, var3, var4));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return (var1 & 8) == 8 ? 0 : this.itemDropped;
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return ModItems.HarmonionDoor.itemID;
    }
}