package Harmonioncraft.common.block;

import Harmonioncraft.common.api.Vector3;
import Harmonioncraft.common.entity.TileEntityConductor;
import Harmonioncraft.common.lib.Reference;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class BlockHarmonionConductor extends BlockContainer
{
    public BlockHarmonionConductor(int id, Material material)
    {
        super(id, material);
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.updateConductorTileEntity(world, x, y, z);
        world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockID)
    {
        this.updateConductorTileEntity(world, x, y, z);
    }

    public static void updateConductorTileEntity(World world, int x, int y, int z)
    {
        for (byte i = 0; i < 6; i++)
        {
            //Update the tile entity on neighboring blocks
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

            if (tileEntity != null)
            {
                if (tileEntity instanceof TileEntityConductor)
                {
                    TileEntityConductor conductorTileEntity = (TileEntityConductor)tileEntity;
                    conductorTileEntity.updateConnection(Vector3.getConnectorFromSide(world, new Vector3(x, y, z), ForgeDirection.getOrientation(i)), ForgeDirection.getOrientation(i));
                }
            }
        }
    }

    /**
     * In your BlockConductor class you must specify the TileEntityConductor class (or a instance of it).
     */
    @Override
    public TileEntity createNewTileEntity(World var1)
    {
    	return null;
    }
}
