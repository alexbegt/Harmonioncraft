package net.Harmonion.block.tank;

import net.Harmonion.tanks.IEnumMachine;

public class TileTankHarmonionWall extends TileTankHarmonion
{
    public IEnumMachine getMachineType()
    {
        return EnumMachineBeta.TANK_IRON_WALL;
    }

    public int getBlockTexture(int var1)
    {
        return EnumMachineBeta.TANK_IRON_WALL.getTexture(var1);
    }
}
