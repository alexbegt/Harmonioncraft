package net.Harmonion.power;

public interface IHarmonionPowerWiring extends IHarmonionPowerConnectable, IWiring
{
    int scanPoweringStrength(int var1, int var2);

    int getCurrentStrength(int var1, int var2);

    void updateCurrentStrength();
}
