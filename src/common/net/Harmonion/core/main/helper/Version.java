package net.Harmonion.core.main.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.Harmonion.Harmonion;
import net.Harmonion.core.lib.Colours;
import net.Harmonion.core.lib.ConfigurationSettings;
import net.Harmonion.core.lib.Reference;
import net.Harmonion.core.lib.Strings;
import net.minecraftforge.common.Property;

/**
 * VersionHelper
 * 
 * Contains methods for checking the version of the currently running instance
 * of the mod against a remote version number authority. Meant to help users by
 * notifying them if they are behind the latest published version of the mod
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
import cpw.mods.fml.common.FMLLog;

public class Version {

	public enum EnumUpdateState { CURRENT, OUTDATED, CONNECTION_ERROR }

	public static final String VERSION = "@VERSION@";
	private static final String REMOTE_VERSION_FILE = "http://bit.ly/buildcraftver";

	public static EnumUpdateState currentVersion = EnumUpdateState.CURRENT;

	public static final int FORGE_VERSION_MAJOR = 4;
	public static final int FORGE_VERSION_MINOR = 0;
	public static final int FORGE_VERSION_PATCH = 0;

	private static String recommendedVersion;

	public static String getVersion() {
		return VERSION;
	}

	public static String getRecommendedVersion() {
		return recommendedVersion;
	}

	public static void versionCheck() {
		try {

			String location = REMOTE_VERSION_FILE;
			HttpURLConnection conn = null;
			while(location != null && !location.isEmpty()) {
				URL url = new URL(location);
				conn = (HttpURLConnection)url.openConnection();
				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
				conn.connect();
				location = conn.getHeaderField("Location");
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;
			String mcVersion = Harmonion.proxy.getMinecraftVersion();
		    while ((line = reader.readLine()) != null) {
		    	if (line.startsWith(mcVersion)) {
		    		if (line.contains(Reference.MOD_NAME)) {

		    			String[] tokens = line.split(":");
		    			recommendedVersion = tokens[2];

			    		if (line.endsWith(VERSION)) {
			    			FMLLog.finer(Reference.MOD_NAME + ": Using the latest version ["+ getVersion() +"] for Minecraft " + mcVersion);
			    			currentVersion = EnumUpdateState.CURRENT;
			    			return;
			    		}
		    		}
		    	}
		    }

		    FMLLog.warning(Reference.MOD_NAME + ": Using outdated version ["+ VERSION +"] for Minecraft " + mcVersion + ". Consider updating.");
			currentVersion = EnumUpdateState.OUTDATED;

		} catch (Exception e) {
			e.printStackTrace();
			FMLLog.warning(Reference.MOD_NAME + ": Unable to read from remote version authority.");
			currentVersion = EnumUpdateState.CONNECTION_ERROR;
		}
	}

}