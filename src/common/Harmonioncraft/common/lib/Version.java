package Harmonioncraft.common.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cpw.mods.fml.common.FMLLog;

import Harmonioncraft.common.commands.CommandHMCV;

public class Version
{
    public static final String VERSION = Reference.VERSION;//"0.0.0.1";
    public static final String BUILD_NUMBER = "";
    private static final String REMOTE_VERSION_FILE = "http://bit.ly/Harmonioncraftver";
    public static EnumUpdateState currentVersion = EnumUpdateState.CURRENT;
    public static final int FORGE_VERSION_MAJOR = 4;
    public static final int FORGE_VERSION_MINOR = 0;
    public static final int FORGE_VERSION_PATCH = 0;
    private static String recommendedVersion;

    public static String getVersion()
    {
        return Reference.VERSION;//"0.0.0.1 (:B)";
    }

    public static String getRecommendedVersion()
    {
        return recommendedVersion;
    }

    public static void versionCheck()
    {
        try
        {
            String var0 = "http://bit.ly/Harmonioncraftver";
            HttpURLConnection var1;

            for (var1 = null; var0 != null && !var0.isEmpty(); var0 = var1.getHeaderField("Location"))
            {
                URL var2 = new URL(var0);
                var1 = (HttpURLConnection)var2.openConnection();
                var1.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
                var1.connect();
            }

            BufferedReader var7 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
            String var3 = null;
            String var4 = CommandHMCV.getMinecraftVersion();

            while ((var3 = var7.readLine()) != null)
            {
                if (var3.startsWith(var4) && var3.contains("Harmonioncraft"))
                {
                    String[] var5 = var3.split(":");
                    recommendedVersion = var5[2];

                    if (var3.endsWith(Reference.VERSION))
                    {
                        FMLLog.finer("Harmonioncraft: Using the latest version [" + getVersion() + "] for Minecraft " + var4, new Object[0]);
                        currentVersion = EnumUpdateState.CURRENT;
                        return;
                    }
                }
            }

            FMLLog.warning("Harmonioncraft: Using outdated version [0.0.0.2] for Minecraft " + var4 + ". Consider updating.", new Object[0]);
            currentVersion = EnumUpdateState.OUTDATED;
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
            FMLLog.warning("Harmonioncraft: Unable to read from remote version authority.", new Object[0]);
            currentVersion = EnumUpdateState.CONNECTION_ERROR;
        }
    }
    public static enum EnumUpdateState
	  {
	    CURRENT, OUTDATED, CONNECTION_ERROR;
	  }
}
/**
public class Version {
	public static final String VERSION = Reference.VERSION;
	public static final String BUILD_NUMBER = "0";
	private static final String REMOTE_VERSION_FILE = "http://bit.ly/Harmonioncraftver";
	public static EnumUpdateState currentVersion = EnumUpdateState.CURRENT;
	public static final int FORGE_VERSION_MAJOR = 4;
	public static final int FORGE_VERSION_MINOR = 0;
	public static final int FORGE_VERSION_PATCH = 0;
	private static String recommendedVersion;

	public static String getVersion()
	{
	  return Reference.VERSION;
	}

	public static String getRecommendedVersion() {
	  return recommendedVersion;
	}

	  public static void versionCheck()
	  {
	    try {
	      String location = "http://bit.ly/Harmonioncraftver";
	      HttpURLConnection conn = null;
	      while ((location != null) && (!location.isEmpty())) {
	        URL url = new URL(location);
	        conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
	        conn.connect();
	        location = conn.getHeaderField("Location");
	      }

	      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	      String line = null;
	      String mcVersion = CommandHMCV.getMinecraftVersion();
	      while ((line = reader.readLine()) != null) {
	        if ((line.startsWith(mcVersion)) && 
	          (line.contains("Harmonioncraft")))
	        {
	          String[] tokens = line.split(":");
	          recommendedVersion = tokens[2];

	          if (line.endsWith(getVersion())) {
	            FMLLog.finer("Harmonioncraft: Using the latest version for Minecraft " + mcVersion, new Object[0]);
	            currentVersion = EnumUpdateState.CURRENT;
	            return;
	          }
	        }

	      }

	      FMLLog.warning("Harmonioncraft: Using outdated version for Minecraft " + mcVersion + ". Consider updating.", new Object[0]);
	      currentVersion = EnumUpdateState.OUTDATED;
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      FMLLog.warning("Harmonioncraft: Unable to read from remote version authority.", new Object[0]);
	      currentVersion = EnumUpdateState.CONNECTION_ERROR;
	    }
	  }

	  public static enum EnumUpdateState
	  {
	    CURRENT, OUTDATED, CONNECTION_ERROR;
	  }
}*/
