package net.Harmonion.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
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

	/***
	 * Loads in all the localization files from the Localizations library class
	 */
	public static void loadLanguages() {
		// For every file specified in the Localization library class, load them into the Language Registry
		for (String localizationFile : Localizations.localeFiles) {
			LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
		}
	}

}