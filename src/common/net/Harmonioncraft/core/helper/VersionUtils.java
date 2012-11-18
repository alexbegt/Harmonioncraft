package net.Harmonioncraft.core.helper;

import net.Harmonioncraft.core.helper.VersionUtils$1;
import net.Harmonioncraft.core.helper.VersionUtils$VersionCheckThread;

public class VersionUtils
{
    private static String latest = "0.0.0.5";
    private static String latestMC = "1.4.4";
    private static String description = "";
    private static boolean criticalUpdate;
    private static boolean newVersion;
    private static boolean newMinecraftVersion;
    private static boolean versionCheckComplete;
    private static final String RELEASE_URL = "https://dl.dropbox.com/u/48633261/Minecraft/Harmonioncraft/version.txt";

    public static void checkForNewVersion()
    {
        VersionUtils$VersionCheckThread var0 = new VersionUtils$VersionCheckThread((VersionUtils$1)null);
        var0.start();
    }

    public static String getCurrentVersion()
    {
        return "0.0.0.5";
    }

    public static String getLatestVersion()
    {
        return latest;
    }

    public static String getLatestMCVersion()
    {
        return latestMC;
    }

    public static String getVersionDescription()
    {
        return description;
    }

    public static boolean isCriticalUpdate()
    {
        return criticalUpdate;
    }

    public static boolean isNewVersionAvailable()
    {
        return newVersion;
    }

    public static boolean isMinecraftOutdated()
    {
        return newMinecraftVersion;
    }

    public static boolean isVersionCheckComplete()
    {
        return versionCheckComplete;
    }

    public static boolean beforeTargetVersion(String var0, String var1)
    {
        String[] var2 = var0.trim().split("\\.");
        String[] var3 = var1.trim().split("\\.");

        for (int var4 = 0; var4 < var2.length; ++var4)
        {
            int var5 = Integer.valueOf(var2[var4]).intValue();
            int var6 = Integer.valueOf(var3[var4]).intValue();

            if (var5 < var6)
            {
                return true;
            }

            if (var5 > var6)
            {
                return false;
            }
        }

        return false;
    }

    public static boolean afterTargetVersion(String var0, String var1)
    {
        String[] var2 = var0.trim().split("\\.");
        String[] var3 = var1.trim().split("\\.");

        for (int var4 = 0; var4 < var2.length; ++var4)
        {
            int var5 = Integer.valueOf(var2[var4]).intValue();
            int var6 = Integer.valueOf(var3[var4]).intValue();

            if (var5 > var6)
            {
                return true;
            }
        }

        return false;
    }

    static String access$102(String var0)
    {
        latest = var0;
        return var0;
    }

    static String access$202(String var0)
    {
        description = var0;
        return var0;
    }

    static boolean access$302(boolean var0)
    {
        criticalUpdate = var0;
        return var0;
    }

    static String access$402(String var0)
    {
        latestMC = var0;
        return var0;
    }

    static String access$100()
    {
        return latest;
    }

    static boolean access$502(boolean var0)
    {
        newVersion = var0;
        return var0;
    }

    static boolean access$300()
    {
        return criticalUpdate;
    }

    static String access$400()
    {
        return latestMC;
    }

    static boolean access$602(boolean var0)
    {
        newMinecraftVersion = var0;
        return var0;
    }

    static boolean access$702(boolean var0)
    {
        versionCheckComplete = var0;
        return var0;
    }
}
