package net.Harmonioncraft.core.handlers;

import java.util.EnumSet;
import net.Harmonioncraft.core.helper.VersionHelper;
import net.Harmonioncraft.lib.Colours;
import net.Harmonioncraft.lib.ConfigurationSettings;
import net.Harmonioncraft.lib.Reference;
import net.minecraft.src.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * VersionCheckTickHandler
 * 
 * Class for notifying the player on their client when they get in game the
 * outcome of the remote version check
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class VersionCheckTickHandler implements ITickHandler {

    private static boolean initialized = false;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
            if (!initialized) {
                for (TickType tickType : type) {
                    if (tickType == TickType.CLIENT) {
                        if (FMLClientHandler.instance().getClient().currentScreen == null) {
                            initialized = true;
                            if (VersionHelper.result == VersionHelper.OUTDATED) {
                                FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(VersionHelper.getResultMessageForClient());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel() {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

}