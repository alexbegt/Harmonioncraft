package net.Harmonioncraft.core.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import net.Harmonioncraft.core.helper.VersionUtils$1;

class VersionUtils$VersionCheckThread extends Thread
{
    private VersionUtils$VersionCheckThread() {}

    public void run()
    {
        try
        {
            URL var1 = new URL("https://dl.dropbox.com/u/48633261/Minecraft/Harmonioncraft/version.txt");
            BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.openStream()));
            VersionUtils.access$102(var2.readLine());
            VersionUtils.access$202(var2.readLine());
            VersionUtils.access$302(Boolean.parseBoolean(var2.readLine()));
            VersionUtils.access$402(var2.readLine());
            var2.close();

            if (VersionUtils.beforeTargetVersion("0.0.0.5", VersionUtils.access$100()))
            {
                LogHelper.eeLogger.log(Level.INFO, "An updated version of Harmonioncraft is available: " + VersionUtils.access$100() + ".");
                VersionUtils.access$502(true);

                if (VersionUtils.access$300())
                {
                    LogHelper.eeLogger.log(Level.INFO, "This update has been marked as CRITICAL and will ignore notification suppression.");
                }

                if (VersionUtils.beforeTargetVersion("1.4.4", VersionUtils.access$400()))
                {
                    VersionUtils.access$602(true);
                    LogHelper.eeLogger.log(Level.INFO, "This update is for Minecraft " + VersionUtils.access$400() + ".");
                }
            }
        }
        catch (Exception var3)
        {
            LogHelper.eeLogger.log(Level.WARNING, "Version Check Failed: " + var3.getMessage());
        }

        VersionUtils.access$702(true);
    }

    VersionUtils$VersionCheckThread(VersionUtils$1 var1)
    {
        this();
    }
}
