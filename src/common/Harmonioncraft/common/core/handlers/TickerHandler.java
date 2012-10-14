package Harmonioncraft.common.core.handlers;

import java.util.EnumSet;

import Harmonioncraft.common.api.ElectricityManager;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickerHandler implements ITickHandler {
	
	public static long inGameTicks = 0;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if(ElectricityManager.instance != null)
		{
			ElectricityManager.instance.tickStart(type, tickData);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if(ElectricityManager.instance != null)
		{
			ElectricityManager.instance.tickEnd(type, tickData);
		}
		
		inGameTicks ++;
        
        if(inGameTicks >= Long.MAX_VALUE)
        {
        	inGameTicks = 0;
        }
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.SERVER, TickType.CLIENT);
	}

	@Override
	public String getLabel()
	{
		return "Electricity Manager";
	}
}
