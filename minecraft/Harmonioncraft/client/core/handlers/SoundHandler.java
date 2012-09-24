package Harmonioncraft.client.core.handlers;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import Harmonioncraft.common.lib.Reference;
import Harmonioncraft.common.lib.Sounds;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

/**
 * SoundHandler
 * 
 * Client specific handler for sound related events
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class SoundHandler {

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {

        // For each custom sound file we have defined in Sounds
        for (String soundFile : Sounds.soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
                event.manager.addSound(soundFile, new File(this.getClass().getResource("/" + soundFile).toURI()));
            }
            // If we cannot add the custom sound file to the pool, log the
            // exception
            catch (Exception e) {
                FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, Reference.LOGGER_PREFIX + "Failed loading sound file: " + soundFile);
            }
        }

    }

}