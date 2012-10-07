package Harmonioncraft.common.entity;

import com.google.common.io.ByteArrayDataInput;

import Harmonioncraft.common.block.BlockHarmonionConductor;
import Harmonioncraft.common.network.IPacketReceiver;
import Harmonioncraft.common.network.NetworkManager;
import Harmonioncraft.common.power.ElectricityManager;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityConductor extends TileEntity implements IConductor, IPacketReceiver
{
    private int connectionID = 0;

    /**
     * Stores information on the blocks that this conductor is connected to
     */
    public TileEntity[] connectedBlocks = {null, null, null, null, null, null};

    public TileEntityConductor()
    {
        this.reset();
    }
    
    @Override
    public int getConnectionID()
    {
    	return this.connectionID;
    }
    
    @Override
    public void setConnectionID(int ID)
    {
    	this.connectionID = ID;
    }
    
    @Override
    public TileEntity[] getConnectedBlocks()
    {
    	return connectedBlocks;
    }

    /**
     * Adds a connection between this conductor and a UE unit
     * @param tileEntity - Must be either a producer, consumer or a conductor
     * @param side - side in which the connection is coming from
     */
    public void updateConnection(TileEntity tileEntity, ForgeDirection side)
    {
        if (tileEntity instanceof TileEntityConductor || tileEntity instanceof IConnector)
        {
            this.connectedBlocks[side.ordinal()] = tileEntity;

            if (tileEntity instanceof IConductor)
            {
                ElectricityManager.instance.mergeConnection(this.connectionID, ((TileEntityConductor)tileEntity).connectionID);
            }
        }
        else
        {
            if (this.connectedBlocks[side.ordinal()] != null)
            {
                if (this.connectedBlocks[side.ordinal()] instanceof IConductor)
                {
                    ElectricityManager.instance.splitConnection(this, (IConductor)this.getConnectedBlocks()[side.ordinal()]);
                }
            }

            this.connectedBlocks[side.ordinal()] = null;
        }
        
        if(!this.worldObj.isRemote)
        {
        	NetworkManager.sendPacketToClients(this.getDescriptionPacket());
        }
    }

    @Override
    public void updateConnectionWithoutSplit(TileEntity tileEntity, ForgeDirection side)
    {
        if(tileEntity instanceof TileEntityConductor || tileEntity instanceof IConnector)
        {
            this.connectedBlocks[side.ordinal()] = tileEntity;

            if (tileEntity instanceof TileEntityConductor)
            {
                ElectricityManager.instance.mergeConnection(this.connectionID, ((TileEntityConductor)tileEntity).connectionID);
            }
        }
        else
        {
            this.connectedBlocks[side.ordinal()] = null;
        }
        
        if(!this.worldObj.isRemote)
        {
        	NetworkManager.sendPacketToClients(this.getDescriptionPacket());
        }
    }
    
    @Override
    public void handlePacketData(net.minecraft.src.NetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream)
    {
    	if(this.worldObj.isRemote)
    	{
    		this.refreshConnectedBlocks();
    	}
    }
    
    /**
     * Determines if this TileEntity requires update calls.
     * @return True if you want updateEntity() to be called, false if not
     */
    @Override
    public boolean canUpdate()
    {
        return false;
    }

    @Override
    public void reset()
    {
        this.connectionID = 0;
        ElectricityManager.instance.registerConductor(this);
    }

    @Override
    public void refreshConnectedBlocks()
    {
        if (this.worldObj != null)
        {
        	BlockHarmonionConductor.updateConductorTileEntity(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    @Override
    public World getWorld()
    {
    	return this.worldObj;
    }
    
    @Override
	public boolean canConnect(ForgeDirection side) 
    {
		return true;
	}
    
    public Block getBlockType()
    {
        if (this.blockType == null)
        {
            this.blockType = Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)];
        }

        return this.blockType;
    }
}
