package net.Harmonion.tileentity;

public interface IWiring extends IConnectable
{
    int getConnectionMask();

    int getExtConnectionMask();
}
