package net.HarmonionCarts.carts;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.HarmonionCarts.carts.util.GeneralTools;
import net.minecraft.src.Block;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.Packet;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class HmccTileEntity extends TileEntity
{
    private String owner = "[HMCC]";

    public void writePacketData(DataOutputStream var1) throws IOException
    {
        var1.writeUTF(this.owner);
    }

    public void readPacketData(DataInputStream var1) throws IOException
    {
        this.owner = var1.readUTF();
    }

    public void markBlockNeedsUpdate()
    {
        this.worldObj.markBlockNeedsUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public void onBlockPlacedBy(EntityLiving var1)
    {
        if (var1 instanceof EntityPlayer)
        {
            this.owner = ((EntityPlayer)var1).username;
        }
    }

    public boolean isRedstonePowered()
    {
        for (int var1 = 2; var1 < 6; ++var1)
        {
            ForgeDirection var2 = ForgeDirection.getOrientation(var1);
            int var3 = GeneralTools.getBlockIdOnSide(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var2);

            if (var3 == Block.redstoneWire.blockID)
            {
                int var4 = GeneralTools.getBlockMetadataOnSide(this.worldObj, this.xCoord, this.yCoord, this.zCoord, var2);

                if (var4 > 0)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public Block getBlock()
    {
        if (this.blockType == null)
        {
            this.blockType = Block.blocksList[this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord)];
        }

        return this.blockType;
    }

    public final int getBlockId()
    {
        Block var1 = this.getBlock();
        return var1 == null ? 0 : var1.blockID;
    }

    public final int getDimension()
    {
        return this.worldObj == null ? 0 : this.worldObj.provider.dimensionId;
    }

    public final String getOwner()
    {
        return this.owner;
    }

    /**
     * Returns the name of the inventory.
     */
    public abstract String getInvName();

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setString("owner", this.owner);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.owner = var1.getString("owner");
    }

    public final int getX()
    {
        return this.xCoord;
    }

    public final int getY()
    {
        return this.yCoord;
    }

    public final int getZ()
    {
        return this.zCoord;
    }

    public final World getWorld()
    {
        return this.worldObj;
    }

    public abstract short getId();
}
