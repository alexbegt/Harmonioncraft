package net.Harmonion.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import net.Harmonion.server.Harmonion;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * LocalizationHandler
 * 
 * Loads in all specified localizations for the mod
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LocalizationHandler {
	
	static Properties rpTranslateTable = null;
	static File configDir = null;
    static File configFile = null;

	/***
	 * Loads in all the localization files from the Localizations library class
	 */
	public static void loadLanguages() {
		File var1;
		Iterator var4;
		
		if (configDir == null)
        {
            var1 = Loader.instance().getConfigDir();
            var1 = new File(var1, "/Harmonion/");
            var1.mkdir();
            configDir = var1;
        }
		
		
		if (rpTranslateTable == null)
        {
            rpTranslateTable = new Properties();
        }

        try
        {
            rpTranslateTable.load(Harmonion.class.getResourceAsStream("/net/Harmonion/client/lang/Harmonion.lang"));
            var1 = new File(configDir, "Harmonion.lang");

            if (var1.exists())
            {
                FileInputStream var5 = new FileInputStream(var1);
                rpTranslateTable.load(var5);
            }
        }
        catch (IOException var3)
        {
            var3.printStackTrace();
        }
        var4 = rpTranslateTable.entrySet().iterator();

        while (var4.hasNext())
        {
            Entry var6 = (Entry)var4.next();
            LanguageRegistry.instance().addStringLocalization((String)var6.getKey(), (String)var6.getValue());
        }
		/** For every file specified in the Localization library class, load them into the Language Registry
		for (String localizationFile : Localizations.localeFiles) {
			LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
		}*/
	}
	
	public static void saveLanguages() {
        try
        {
            File var0 = new File(configDir, "Harmonion.lang");
            FileOutputStream var1 = new FileOutputStream(var0);
            rpTranslateTable.store(var1, "Harmonion Language File");
        }
        catch (IOException var2)
        {
            var2.printStackTrace();
        }
	}

}