package net.Harmonion.tileentity;

public interface IConnectable
{
    int getConnectableMask();

    int getConnectClass(int var1);

    int getCornerPowerMode();
}
