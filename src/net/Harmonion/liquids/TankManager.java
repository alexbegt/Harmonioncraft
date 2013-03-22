package net.Harmonion.liquids;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.Harmonion.liquids.tanks.StandardTank;
import net.Harmonion.util.network.PacketGuiUpdate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TankManager implements ITankContainer
{
    private static final byte NETWORK_DATA = 3;
    private List tanks = new ArrayList();
    private List prevLiquids = new ArrayList();

    public void addTank(StandardTank var1)
    {
        this.tanks.add(var1);
        int var2 = this.tanks.indexOf(var1);
        var1.setTankIndex(var2);
        this.prevLiquids.add(var1.getLiquid() == null ? null : var1.getLiquid().copy());
    }

    public void addTanks(Collection var1)
    {
        Iterator var2 = var1.iterator();

        while (var2.hasNext())
        {
            StandardTank var3 = (StandardTank)var2.next();
            this.addTank(var3);
        }
    }

    public void addTanks(StandardTank[] var1)
    {
        StandardTank[] var2 = var1;
        int var3 = var1.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            StandardTank var5 = var2[var4];
            this.addTank(var5);
        }
    }

    public void writeTanksToNBT(NBTTagCompound var1)
    {
        NBTTagList var2 = new NBTTagList();

        for (byte var3 = 0; var3 < this.tanks.size(); ++var3)
        {
            StandardTank var4 = (StandardTank)this.tanks.get(var3);

            if (var4.getLiquid() != null)
            {
                NBTTagCompound var5 = new NBTTagCompound();
                var5.setByte("tank", var3);
                var4.writeToNBT(var5);
                var2.appendTag(var5);
            }
        }

        var1.setTag("tanks", var2);
    }

    public void readTanksFromNBT(NBTTagCompound var1)
    {
        NBTTagList var2 = var1.getTagList("tanks");

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("tank");

            if (var5 >= 0 && var5 < this.tanks.size())
            {
                ((StandardTank)this.tanks.get(var5)).readFromNBT(var4);
            }
        }
    }

    public void writePacketData(DataOutputStream var1) throws IOException
    {
        for (int var2 = 0; var2 < this.tanks.size(); ++var2)
        {
            this.writePacketData(var1, var2);
        }
    }

    public void writePacketData(DataOutputStream var1, int var2) throws IOException
    {
        if (var2 < this.tanks.size())
        {
            ILiquidTank var3 = (ILiquidTank)this.tanks.get(var2);
            LiquidStack var4 = var3.getLiquid();

            if (var4 == null)
            {
                var4 = new LiquidStack(0, 0, 0);
            }

            var1.writeShort(var4.itemID);
            var1.writeShort(var4.itemMeta);
            var1.writeInt(var4.amount);
        }
    }

    public void readPacketData(DataInputStream var1) throws IOException
    {
        for (int var2 = 0; var2 < this.tanks.size(); ++var2)
        {
            this.readPacketData(var1, var2);
        }
    }

    public void readPacketData(DataInputStream var1, int var2) throws IOException
    {
        if (var2 < this.tanks.size())
        {
            LiquidTank var3 = (LiquidTank)this.tanks.get(var2);
            LiquidStack var4 = new LiquidStack(0, 0, 0);
            var4.itemID = var1.readShort();
            var4.itemMeta = var1.readShort();
            var4.amount = var1.readInt();

            if (var4.itemID == 0)
            {
                var4 = null;
            }

            var3.setLiquid(var4);
        }
    }

    public void initGuiData(Container var1, ICrafting var2, int var3)
    {
        if (var3 < this.tanks.size())
        {
            LiquidStack var4 = ((StandardTank)this.tanks.get(var3)).getLiquid();
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;

            if (var4 != null)
            {
                var5 = var4.itemID;
                var6 = var4.itemMeta;
                var7 = var4.amount;
            }

            var2.sendProgressBarUpdate(var1, var3 * 3 + 0, var5);
            var2.sendProgressBarUpdate(var1, var3 * 3 + 1, var6);
            PacketGuiUpdate var8 = new PacketGuiUpdate(var1.windowId, var3 * 3 + 2, var7);
            var8.sendPacket((EntityPlayer)var2);
        }
    }

    public void updateGuiData(Container var1, List var2, int var3)
    {
        for (int var4 = 0; var4 < var2.size(); ++var4)
        {
            ICrafting var5 = (ICrafting)var2.get(var4);
            EntityPlayer var6 = (EntityPlayer)var2.get(var4);
            LiquidStack var7 = ((StandardTank)this.tanks.get(var3)).getLiquid();
            LiquidStack var8 = (LiquidStack)this.prevLiquids.get(var3);

            if (var7 == null ^ var8 == null)
            {
                int var9 = 0;
                int var10 = 0;
                int var11 = 0;

                if (var7 != null)
                {
                    var9 = var7.itemID;
                    var10 = var7.itemMeta;
                    var11 = var7.amount;
                }

                var5.sendProgressBarUpdate(var1, var3 * 3 + 0, var9);
                var5.sendProgressBarUpdate(var1, var3 * 3 + 1, var10);
                PacketGuiUpdate var12 = new PacketGuiUpdate(var1.windowId, var3 * 3 + 2, var11);
                var12.sendPacket(var6);
            }
            else if (var7 != null && var8 != null)
            {
                if (var7.itemID != var8.itemID)
                {
                    var5.sendProgressBarUpdate(var1, var3 * 3 + 0, var7.itemID);
                }

                if (var7.itemMeta != var8.itemMeta)
                {
                    var5.sendProgressBarUpdate(var1, var3 * 3 + 1, var7.itemMeta);
                }

                if (var7.amount != var8.amount)
                {
                    PacketGuiUpdate var14 = new PacketGuiUpdate(var1.windowId, var3 * 3 + 2, var7.amount);
                    var14.sendPacket(var6);
                }
            }
        }

        ILiquidTank var13 = (ILiquidTank)this.tanks.get(var3);
        this.prevLiquids.set(var3, var13.getLiquid() == null ? null : var13.getLiquid().copy());
    }

    public void processGuiUpdate(int var1, int var2)
    {
        int var3 = var1 / 3;

        if (var3 < this.tanks.size())
        {
            LiquidStack var4 = ((StandardTank)this.tanks.get(var3)).getLiquid();

            if (var4 == null)
            {
                var4 = new LiquidStack(0, 0);
                ((StandardTank)this.tanks.get(var3)).setLiquid(var4);
            }

            switch (var1 % 3)
            {
                case 0:
                    var4.itemID = var2;
                    break;

                case 1:
                    var4.itemMeta = var2;
                    break;

                case 2:
                    var4.amount = var2;
            }
        }
    }

    public int fill(ForgeDirection var1, LiquidStack var2, boolean var3)
    {
        return this.fill(0, var2, var3);
    }

    public int fill(int var1, LiquidStack var2, boolean var3)
    {
        return var1 >= 0 && var1 < this.tanks.size() && var2 != null ? ((StandardTank)this.tanks.get(var1)).fill(var2, var3) : 0;
    }

    public LiquidStack drain(ForgeDirection var1, int var2, boolean var3)
    {
        return this.drain(0, var2, var3);
    }

    public LiquidStack drain(int var1, int var2, boolean var3)
    {
        return var1 >= 0 && var1 < this.tanks.size() ? ((StandardTank)this.tanks.get(var1)).drain(var2, var3) : null;
    }

    public ILiquidTank[] getTanks(ForgeDirection var1)
    {
        ILiquidTank[] var2 = new ILiquidTank[this.tanks.size()];

        for (int var3 = 0; var3 < var2.length; ++var3)
        {
            var2[var3] = new ImmutableTankWrapper((ILiquidTank)this.tanks.get(var3));
        }

        return var2;
    }

    public ILiquidTank[] getTanks()
    {
        return this.getTanks(ForgeDirection.UNKNOWN);
    }

    public StandardTank getTank(int var1)
    {
        return var1 >= 0 && var1 < this.tanks.size() ? (StandardTank)this.tanks.get(var1) : null;
    }

    public void setCapacity(int var1, int var2)
    {
        StandardTank var3 = this.getTank(var1);
        var3.setCapacity(var2);
        LiquidStack var4 = var3.getLiquid();

        if (var4 != null && var4.amount > var2)
        {
            var4.amount = var2;
        }
    }

    public void outputLiquid(ITankContainer[] var1, int var2, int var3)
    {
        for (int var4 = 0; var4 < 6; ++var4)
        {
            ITankContainer var5 = var1[var4];

            if (var5 != null)
            {
                ForgeDirection var6 = ForgeDirection.getOrientation(var4);
                var6 = var6.getOpposite();
                LiquidStack var7 = this.drain(var2, var3, false);

                if (var7 == null)
                {
                    break;
                }

                int var8 = var5.fill(var6, var7, true);
                this.drain(var2, var8, true);
            }
        }
    }

    private boolean tankMatchesLiquid(ILiquidTank var1, LiquidStack var2)
    {
        return var2 == null ? false : (var1.fill(var2, false) > 0 ? true : var2.isLiquidEqual(var1.getLiquid()));
    }

    public ILiquidTank getTank(ForgeDirection var1, LiquidStack var2)
    {
        if (var2 == null)
        {
            return null;
        }
        else
        {
            Iterator var3 = this.tanks.iterator();
            ILiquidTank var4;

            do
            {
                if (!var3.hasNext())
                {
                    return null;
                }

                var4 = (ILiquidTank)var3.next();
            }
            while (!this.tankMatchesLiquid(var4, var2));

            return var4;
        }
    }
}
