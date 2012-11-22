package net.Harmonion.core.main.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.Harmonion.core.lib.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class LogHelper {

	public static Logger eeLogger = Logger.getLogger(Reference.MOD_NAME);

	public static void init() {
		eeLogger.setParent(FMLLog.getLogger());
	}

	public static void log(Level logLevel, String message) {
		//System.out.println(Reference.LOGGER_PREFIX + message);
		eeLogger.log(logLevel, message);
	}

}