package Harmonioncraft.common.block;

import Harmonioncraft.common.entity.TileEntityHarmonionWire;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockHarmonionWire extends BlockHarmonionConductor{

	public BlockHarmonionWire(int id) {
		super(id, Material.cloth);
		this.setStepSound(soundClothFootstep);
        this.setResistance(0.2F);
        this.setBlockBounds(0.30F, 0.30F, 0.30F, 0.70F, 0.70F, 0.70F);
        this.setRequiresSelfNotify();
        this.blockIndexInTexture = 14;
        this.setCreativeTab(CreativeTabs.tabRedstone);
	}
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
    */
    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityHarmonionWire();
    }
}