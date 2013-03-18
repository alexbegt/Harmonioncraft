package net.Harmonion.block.tank;

import net.Harmonion.tanks.IEnumMachine;
import net.Harmonion.util.misc.MiscTools;
import net.minecraftforge.common.ForgeDirection;

public class TileTankHarmonionGauge extends TileTankHarmonion
{
    public IEnumMachine getMachineType()
    {
        return EnumMachineBeta.TANK_IRON_GAUGE;
    }

    public int getBlockTexture(int side)
    {
      ForgeDirection s = ForgeDirection.getOrientation(side);
      if ((!isStructureValid()) || (getPattern() == null)) {
        return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(side);
      }

      char markerSide = getPattern().getPatternMarkerChecked(MiscTools.getXOnSide(getPatternPositionX(), s), MiscTools.getYOnSide(getPatternPositionY(), s), MiscTools.getZOnSide(getPatternPositionZ(), s));

      if (!isMapPositionOtherBlock(markerSide)) {
        return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(6);
      }

      if ((s == ForgeDirection.UP) || (s == ForgeDirection.DOWN)) {
        int markerTop = getPattern().getPatternMarkerChecked(getPatternPositionX(), getPatternPositionY() + 1, getPatternPositionZ());
        if ((markerTop == 65) || (markerTop == 79)) {
          int metaUp = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord - 1);
          int metaDown = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord + 1);

          if ((metaUp == getBlockMetadata()) && (metaDown == getBlockMetadata()))
            return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(8);
          if (metaUp == getBlockMetadata())
            return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(7);
          if (metaDown == getBlockMetadata()) {
            return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(9);
          }
        }
        return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(0);
      }

      int metaUp = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord + 1, this.zCoord);
      int metaDown = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord);

      if ((metaUp == getBlockMetadata()) && (metaDown == getBlockMetadata()))
        return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(8);
      if (metaUp == getBlockMetadata())
        return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(7);
      if (metaDown == getBlockMetadata()) {
        return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(9);
      }

      return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(0);
    }
    
    /**public int getBlockTexture(int var1)
    {
        ForgeDirection var2 = ForgeDirection.getOrientation(var1);

        if (this.isStructureValid() && this.getPattern() != null)
        {
            char var3 = this.getPattern().getPatternMarkerChecked(MiscTools.getXOnSide(this.getPatternPositionX(), var2), MiscTools.getYOnSide(this.getPatternPositionY(), var2), MiscTools.getZOnSide(this.getPatternPositionZ(), var2));

            if (!this.isMapPositionOtherBlock(var3))
            {
                return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(6);
            }
            else
            {
                int var5;

                if (var2 != ForgeDirection.UP && var2 != ForgeDirection.DOWN)
                {
                    int var7 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord + 1, this.zCoord);
                    var5 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord);
                    return var7 == this.getBlockMetadata() && var5 == this.getBlockMetadata() ? EnumMachineBeta.TANK_IRON_GAUGE.getTexture(8) : (var7 == this.getBlockMetadata() ? EnumMachineBeta.TANK_IRON_GAUGE.getTexture(7) : (var5 == this.getBlockMetadata() ? EnumMachineBeta.TANK_IRON_GAUGE.getTexture(9) : EnumMachineBeta.TANK_IRON_GAUGE.getTexture(0)));
                }
                else
                {
                    char var4 = this.getPattern().getPatternMarkerChecked(this.getPatternPositionX(), this.getPatternPositionY() + 1, this.getPatternPositionZ());

                    if (var4 == 65 || var4 == 79)
                    {
                        var5 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord - 1);
                        int var6 = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord + 1);

                        if (var5 == this.getBlockMetadata() && var6 == this.getBlockMetadata())
                        {
                            return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(8);
                        }

                        if (var5 == this.getBlockMetadata())
                        {
                            return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(7);
                        }

                        if (var6 == this.getBlockMetadata())
                        {
                            return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(9);
                        }
                    }

                    return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(0);
                }
            }
        }
        else
        {
            return EnumMachineBeta.TANK_IRON_GAUGE.getTexture(var1);
        }
    }*/
}
