package net.Harmonion.power;

public class RenderShiftedEntry extends RenderListEntry
{
    public int shift;

    public RenderShiftedEntry(int var1)
    {
        this.shift = var1;
    }

    public int mapDamageValue(int var1)
    {
        return var1 >> this.shift;
    }
}
