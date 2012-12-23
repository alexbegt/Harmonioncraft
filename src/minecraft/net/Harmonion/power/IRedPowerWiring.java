package net.Harmonion.power;

public interface IRedPowerWiring extends IRedPowerConnectable, IWiring
{
    int scanPoweringStrength(int var1, int var2);

    int getCurrentStrength(int var1, int var2);

    void updateCurrentStrength();
}
