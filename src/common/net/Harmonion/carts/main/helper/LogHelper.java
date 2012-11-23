package net.Harmonion.carts.main.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.Harmonion.carts.lib.Reference;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class LogHelper {

	private static Logger eeLogger = Logger.getLogger(Reference.MOD_NAME);

	public static void init() {
		eeLogger.setParent(FMLLog.getLogger());
	}

	public static void log(Level logLevel, String message) {
		//System.out.println(Reference.LOGGER_PREFIX + message);
		eeLogger.log(logLevel, message);
	}

}