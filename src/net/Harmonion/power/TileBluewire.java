package net.Harmonion.power;

import net.minecraft.nbt.NBTTagCompound;

public class TileBluewire extends TileWiring implements IHarmonionConnectable
{
    HarmonionConductor cond = new TileBluewire$1(this);

    public float getWireHeight()
    {
        switch (this.Metadata)
        {
            case 0:
                return 0.188F;

            case 1:
                return 0.25F;

            case 2:
                return 0.3125F;

            default:
                return 0.25F;
        }
    }

    public int getExtendedID()
    {
        return 5;
    }

    public int getConnectClass(int var1)
    {
        switch (this.Metadata)
        {
            case 0:
                return 64;

            case 1:
                return 68;

            default:
                return 69;
        }
    }

    public HarmonionConductor getBlueConductor(int var1)
    {
        return this.cond;
    }

    public int getConnectionMask()
    {
        if (this.ConMask >= 0)
        {
            return this.ConMask;
        }
        else
        {
            this.ConMask = HarmonionLib.getConnections(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);

            if (this.EConMask < 0)
            {
                this.EConMask = HarmonionLib.getExtConnections(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);
                this.EConEMask = HarmonionLib.getExtConnectionExtras(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);
            }

            if (CoreLib.isClient(this.worldObj))
            {
                return this.ConMask;
            }
            else
            {
                this.cond.recache(this.ConMask, this.EConMask);
                return this.ConMask;
            }
        }
    }

    public int getExtConnectionMask()
    {
        if (this.EConMask >= 0)
        {
            return this.EConMask;
        }
        else
        {
            this.EConMask = HarmonionLib.getExtConnections(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);
            this.EConEMask = HarmonionLib.getExtConnectionExtras(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);

            if (this.ConMask < 0)
            {
                this.ConMask = HarmonionLib.getConnections(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);
            }

            if (CoreLib.isClient(this.worldObj))
            {
                return this.EConMask;
            }
            else
            {
                this.cond.recache(this.ConMask, this.EConMask);
                return this.EConMask;
            }
        }
    }

    public boolean canUpdate()
    {
        return true;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!CoreLib.isClient(this.worldObj))
        {
            if (this.ConMask < 0 || this.EConMask < 0)
            {
                if (this.ConMask < 0)
                {
                    this.ConMask = HarmonionLib.getConnections(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);
                }

                if (this.EConMask < 0)
                {
                    this.EConMask = HarmonionLib.getExtConnections(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);
                    this.EConEMask = HarmonionLib.getExtConnectionExtras(this.worldObj, this, this.xCoord, this.yCoord, this.zCoord);
                }

                this.cond.recache(this.ConMask, this.EConMask);
            }

            this.cond.iterate();
            this.dirtyBlock();
        }
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.cond.readFromNBT(var1);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        this.cond.writeToNBT(var1);
    }
}
