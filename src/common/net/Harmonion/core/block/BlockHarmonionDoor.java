package net.Harmonion.core.block;

import java.util.Random;


import net.Harmonion.core.item.ModItems;
import net.Harmonion.core.lib.Reference;
import net.Harmonion.core.lib.Strings;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockDoor;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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
        return ModItems.HarmonionDoor.shiftedIndex;
    }
}