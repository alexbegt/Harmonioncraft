package Harmonioncraft.common.lib;

/**
 * Reference
 * 
 * General purpose library to contain mod related constants
 * 
 * @author Alexbegt, DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Reference {
	
	/* Debug Mode On-Off */
    public static final boolean DEBUG_MODE = false;

    /* General Mod related constants */
    public static final String MOD_ID = "HMC";
    public static final String MOD_NAME = "Harmonioncraft";
    public static final String VERSION = "0.0.0.4B";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String LOGGER_PREFIX = "[" + MOD_ID + "] ";
    
	/* Configuration related constants */
    public static final String ENABLE_SOUNDS = "enable_sounds";
    public static final String ENABLE_PARTICLE_FX = "enable_particle_fx";
    public static final String AUTO_RESOLVE_BLOCK_IDS = "auto_resolve_block_ids";
    
    /* Texture related constants */
    public static final String SPRITE_SHEET_LOCATION = "/Harmonioncraft/client/textures/";
    public static final String ITEM_SPRITE_SHEET = "Harmonioncraft_items.png";
    public static final String BLOCK_SPRITE_SHEET = "Harmonioncraft_blocks.png";
    public static final String GUI_SHEET_LOCATION = "/Harmonioncraft/client/textures/gui/";
    public static final String ARMOR_SHEET_LOCATION = "/Harmonioncraft/client/textures/armor/";
}
