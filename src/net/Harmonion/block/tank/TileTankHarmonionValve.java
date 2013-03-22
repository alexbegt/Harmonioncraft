package net.Harmonion.block.tank;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.Harmonion.liquids.TankManager;
import net.Harmonion.liquids.TankWrapper;
import net.Harmonion.liquids.tanks.FakeTank;
import net.Harmonion.liquids.tanks.StandardTank;
import net.Harmonion.tanks.IEnumMachine;
import net.Harmonion.util.Game;
import net.Harmonion.util.misc.MiscTools;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;

public class TileTankHarmonionValve extends TileTankHarmonion implements ITankContainer
{
    private static final byte FILL_DURATION = 20;
    private byte filling = 0;

    private void setFilling()
    {
        boolean var1 = false;

        if (this.filling == 0)
        {
            var1 = true;
        }

        this.filling = 20;

        if (var1)
        {
            this.sendUpdateToClient();
        }
    }

    private void decrementFilling()
    {
        byte var1 = this.filling;

        if (this.filling > 0)
        {
            --this.filling;
        }

        if (this.filling == 0 && var1 != 0)
        {
            this.sendUpdateToClient();
        }
    }

    public boolean isFilling()
    {
        return this.filling > 0;
    }

    public IEnumMachine getMachineType()
    {
        return EnumMachineBeta.TANK_IRON_VALVE;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        super.updateEntity();

        if (!Game.isNotHost(this.worldObj))
        {
            this.decrementFilling();

            if (this.isMaster)
            {
                TileEntity var1 = this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
                TileTankHarmonionValve var2 = null;

                if (var1 instanceof TileTankHarmonionValve)
                {
                    var2 = (TileTankHarmonionValve)var1;

                    if (var2.isStructureValid() && var2.getPatternMarker() == 84)
                    {
                        StandardTank var3 = var2.getTankManager().getTank(0);
                        LiquidStack var4 = var3.getLiquid();

                        if (var4 != null && var4.amount >= var3.getCapacity() - 1000)
                        {
                            var2 = null;
                            LiquidStack var5 = var4.copy();
                            var5.amount = 1000 - (var3.getCapacity() - var4.amount);

                            if (var5.amount > 0)
                            {
                                int var6 = this.fill(0, var5, false);

                                if (var6 > 0)
                                {
                                    var5 = var3.drain(var6, true);
                                    this.fill(0, var5, true);
                                }
                            }
                        }
                    }
                    else
                    {
                        var2 = null;
                    }
                }

                if (var2 != null)
                {
                    LiquidStack var7 = this.tankManager.drain(0, 1000, false);

                    if (var7 != null && var7.amount > 0)
                    {
                        int var8 = var2.fill(ForgeDirection.UP, var7, true);
                        this.tankManager.drain(0, var8, true);
                    }
                }
            }
        }
    }

    public int getBlockTexture(int var1)
    {
        if (this.isStructureValid() && this.getPattern() != null)
        {
            ForgeDirection var2 = ForgeDirection.getOrientation(var1);
            char var3 = this.getPattern().getPatternMarkerChecked(MiscTools.getXOnSide(this.getPatternPositionX(), var2), MiscTools.getYOnSide(this.getPatternPositionY(), var2), MiscTools.getZOnSide(this.getPatternPositionZ(), var2));
            return !this.isMapPositionOtherBlock(var3) ? (var1 != ForgeDirection.UP.ordinal() && var1 != ForgeDirection.DOWN.ordinal() ? EnumMachineBeta.TANK_IRON_VALVE.getTexture(7) : EnumMachineBeta.TANK_IRON_VALVE.getTexture(6)) : EnumMachineBeta.TANK_IRON_VALVE.getTexture(var1);
        }
        else
        {
            return EnumMachineBeta.TANK_IRON_VALVE.getTexture(var1);
        }
    }

    public void writePacketData(DataOutputStream var1) throws IOException
    {
        super.writePacketData(var1);
        var1.writeByte(this.filling);
    }

    public void readPacketData(DataInputStream var1) throws IOException
    {
        super.readPacketData(var1);
        this.filling = var1.readByte();
    }

    public LiquidStack drain(ForgeDirection var1, int var2, boolean var3)
    {
        return this.drain(0, var2, var3);
    }

    public LiquidStack drain(int var1, int var2, boolean var3)
    {
        if (this.getPatternPositionY() - this.getPattern().getMasterOffsetY() > 1)
        {
            return null;
        }
        else
        {
            TankManager var4 = this.getTankManager();
            return var4 != null ? var4.drain(var1, var2, var3) : null;
        }
    }

    public int fill(ForgeDirection var1, LiquidStack var2, boolean var3)
    {
        return this.fill(0, var2, var3);
    }

    public int fill(int var1, LiquidStack var2, boolean var3)
    {
        if (var2 == null)
        {
            return 0;
        }
        else
        {
            TankManager var4 = this.getTankManager();

            if (var4 == null)
            {
                return 0;
            }
            else
            {
                int var5 = var4.fill(var1, var2, var3);

                if (var5 > 0)
                {
                    this.setFilling();
                }

                return var5;
            }
        }
    }

    public ILiquidTank[] getTanks(ForgeDirection var1)
    {
        TankManager var2 = this.getTankManager();
        return var2 != null ? var2.getTanks() : FakeTank.ARRAY;
    }

    public ILiquidTank getTank(ForgeDirection var1, LiquidStack var2)
    {
        TankManager var3 = this.getTankManager();
        return (ILiquidTank)(var3 != null ? new HarmonionTankWrapper(this, var3.getTank(0)) : FakeTank.INSTANCE);
    }

    static void access$000(TileTankHarmonionValve var0)
    {
        var0.setFilling();
    }
    
    class HarmonionTankWrapper extends TankWrapper
    {
        final TileTankHarmonionValve this$0;

        public HarmonionTankWrapper(TileTankHarmonionValve var1, ILiquidTank var2)
        {
            super(var2);
            this.this$0 = var1;
        }

        public int fill(LiquidStack var1, boolean var2)
        {
            if (var1 == null)
            {
                return 0;
            }
            else
            {
                int var3 = this.tank.fill(var1, var2);

                if (var3 > 0)
                {
                    TileTankHarmonionValve.access$000(this.this$0);
                }

                return var3;
            }
        }

        public LiquidStack drain(int var1, boolean var2)
        {
            return this.this$0.getPatternPositionY() - this.this$0.getPattern().getMasterOffsetY() > 1 ? null : this.tank.drain(var1, var2);
        }

        public int getTankPressure()
        {
            return this.this$0.getPatternPositionY() - this.this$0.getPattern().getMasterOffsetY() > 1 ? 0 : 1;
        }
    }

}
