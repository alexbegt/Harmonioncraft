package Harmonioncraft.common.entity;

import Harmonioncraft.common.lib.Reference;
import Harmonioncraft.common.network.NetworkManager;
import net.minecraft.src.Block;
import net.minecraft.src.Packet;

public class TileEntityHarmonionWire extends TileEntityConductor
{
	public static double RESISTANCE = 0.05;
	public static double MAX_AMPS = 500;

    @Override
    public double getResistance()
    {
        return RESISTANCE;
    }

	@Override
	public double getMaxAmps()
	{
		return MAX_AMPS;
	}

	@Override
	public void onConductorMelt()
	{
		if(!this.worldObj.isRemote)
		{
			this.worldObj.setBlockWithNotify(this.xCoord, this.yCoord, this.zCoord, Block.fire.blockID);
		}
	}
	
	@Override
    public Packet getDescriptionPacket()
    {
        return NetworkManager.getPacket(Reference.CHANNEL_NAME, this);
    }
}
