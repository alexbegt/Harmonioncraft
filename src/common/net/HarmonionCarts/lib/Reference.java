package net.HarmonionCarts.lib;

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
    public static final String MOD_ID = "HMCC";
    public static final String MOD_NAME = "Fat Cat Cart Pack";
    public static final String VERSION = "0.0.0.5";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String LOGGER_PREFIX = "[" + MOD_ID + "] ";
    public static final int SECOND_IN_TICKS = 20;
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
    public static final String SERVER_PROXY_CLASS = "net.HarmonionCarts.core.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "net.HarmonionCarts.client.core.ClientProxy";
    
    /* Configuration related constants */
    public static final String ENABLE_VERSION_CHECK = "enable_version_check";
    public static final String ENABLE_SOUNDS = "enable_sounds";
    public static final String ENABLE_PARTICLE_FX = "enable_particle_fx";
    public static final String AUTO_RESOLVE_BLOCK_IDS = "auto_resolve_block_ids";
    
    /* Gui related constants */
    public static final String GUI_CALCINATOR_NAME = "gui.calcinator.name";
    
    /* General Tile Entity related constants */
    public static final String TE_GEN_OWNER_NBT_TAG_LABEL = "owner";
    public static final String TE_GEN_STATE_NBT_TAG_LABEL = "state";
    public static final String TE_GEN_DIRECTION_NBT_TAG_LABEL = "direction";
    
    /* Texture related constants */
    public static final String SPRITE_SHEET_LOCATION = "/net/Harmonioncraft/client/textures/";
    public static final String ITEM_SPRITE_SHEET = "Harmonioncraft_items.png";
    public static final String BLOCK_SPRITE_SHEET = "Harmonioncraft_blocks.png";
    
    public static final String GUI_SHEET_LOCATION = "/net/Harmonioncraft/client/textures/gui/";
    public static final String ARMOR_SHEET_LOCATION = "/net/Harmonioncraft/client/textures/armor/";
    
    public static final String WOLF_SHEET_LOCATION = "/net/Harmonioncraft/client/textures/entity/";
    public static final String LMM_WOLF_MAIN_SHEET = "cyberwolfn.png";
    public static final String LMM_WOLF_TAIMED_SHEET = "cyberwolft.png";
    public static final String LMM_WOLF_ANGRY_SHEET = "cyberwolfa.png";
    
}