package net.Harmonioncraft.core.handlers;

import java.util.EnumSet;
import net.Harmonioncraft.core.helper.VersionUtils;
import net.Harmonioncraft.lib.Colours;
import net.Harmonioncraft.lib.ConfigurationSettings;
import net.Harmonioncraft.lib.Reference;
import net.minecraft.src.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class VersionCheckTickHandler implements IScheduledTickHandler {

	private static boolean initialized = false;
	public static VersionCheckTickHandler instance = new VersionCheckTickHandler();
	private boolean sent;

	public void tickStart(EnumSet var1, Object ... var2)
    {
        if (!this.sent && VersionUtils.isVersionCheckComplete())
        {
            this.sent = true;

            if (VersionUtils.isNewVersionAvailable())
            {
                EntityPlayer var3 = (EntityPlayer)var2[0];
                var3.sendChatToPlayer("[Harmonioncraft Mods] A new version is available: " + VersionUtils.getLatestVersion());
                var3.sendChatToPlayer(VersionUtils.getVersionDescription());
            }
        }
    }

    public void tickEnd(EnumSet var1, Object ... var2) {}

	@Override
	public EnumSet ticks()
    {
        return this.sent ? EnumSet.noneOf(TickType.class) : EnumSet.of(TickType.PLAYER);
    }

	@Override
	public String getLabel() {
		return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
	}
	
	public int nextTickSpacing()
    {
        return !this.sent ? 400 : 72000;
    }

}