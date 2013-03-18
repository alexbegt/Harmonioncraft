package net.Harmonion.api.tracks;

public interface ITrackSwitch extends ITrackInstance
{

    enum ArrowDirection
    {

        NORTH, SOUTH, EAST, WEST, NORTH_SOUTH, EAST_WEST
    };

    public boolean isSwitched();

    public void setSwitched(boolean switched);

    public boolean isMirrored();

    public ArrowDirection getRedSignDirection();

    public ArrowDirection getWhiteSignDirection();
}
