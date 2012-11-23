package net.Harmonion.carts.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;

import net.Harmonion.carts.carts.util.Game;
import net.Harmonion.carts.carts.util.GeneralTools;
import net.minecraft.src.StringTranslate;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * LocalizationHelper
 * 
 * Helper class for looking up localized strings in the Language Registry
 * 
 * @author Alexbegt,DJP
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LocalizationHelper {
	
	public static final String ENGLISH = "en_US";
    private Map languageTable = new TreeMap();
    private static LocalizationHelper instance;
    
    public FMLPreInitializationEvent fMLPreInitializationEvent;

    public LocalizationHelper()
    {
        this.loadDefaultLanguage("en_US", "");
        File var1 = new File("Config", "Harmonion/Carts/lang");

        if (!var1.exists())
        {
            var1.mkdirs();
        }

        File[] var2 = var1.listFiles();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            File var5 = var2[var4];
            String var6 = var5.getName();
            var6 = var6.replace(".lang", "");
            this.loadCustomLanguage(var5, var6);
        }
    }

    public static LocalizationHelper getInstance()
    {
        if (instance == null)
        {
            instance = new LocalizationHelper();
        }

        return instance;
    }

    public static String translate(String var0)
    {
        return getInstance().translate_do(var0);
    }

    private String translate_do(String var1)
    {
        String var2 = StringTranslate.getInstance().getCurrentLanguage();
        return this.translate(var1, var2);
    }

    private void loadDefaultLanguage(String var1, String var2)
    {
        Properties var3 = new Properties((Properties)this.languageTable.get(var2));

        try
        {
            InputStream var4 = this.getClass().getResourceAsStream(var1 + ".lang");

            if (var4 == null)
            {
                throw new IOException();
            }

            InputStreamReader var5 = new InputStreamReader(var4, "UTF-8");
            var3.load(var5);
        }
        catch (IOException var6)
        {
            Game.log(Level.WARNING, "Default language file not found: {0}", new Object[] {var1});
        }

        this.languageTable.put(var1, var3);
    }

    private void loadCustomLanguage(File var1, String var2)
    {
        Properties var3 = (Properties)this.languageTable.get(var2);

        if (var3 == null)
        {
            var3 = (Properties)this.languageTable.get("en_US");
        }

        Properties var4 = new Properties(var3);

        try
        {
            InputStreamReader var5 = new InputStreamReader(new FileInputStream(var1), "UTF-8");
            var4.load(var5);
            Game.log(Level.INFO, "Custom language file loaded: {0}", new Object[] {var2});
        }
        catch (IOException var6)
        {
            Game.log(Level.INFO, "Custom language file not found: {0}", new Object[] {var2});
        }

        this.languageTable.put(var2, var4);
    }

    public String translate(String var1, String var2)
    {
        var1 = GeneralTools.cleanTag(var1);
        Properties var3 = (Properties)this.languageTable.get(var2);

        if (var3 == null)
        {
            var3 = (Properties)this.languageTable.get("en_US");
        }

        String var4 = var3.getProperty(var1);

        if (var4 == null)
        {
            Game.log(Level.WARNING, "Language Tag is unknown: {0}, {1}", new Object[] {var2, var1});
            return var1;
        }
        else
        {
            return var4;
        }
    }

    public void registerItemName(Object var1, String var2)
    {
        var2 = GeneralTools.cleanTag(var2);
        Iterator var3 = this.languageTable.keySet().iterator();

        while (var3.hasNext())
        {
            String var4 = (String)var3.next();
            LanguageRegistry.instance().addNameForObject(var1, var4, this.translate(var2, var4));
        }
    }

    public void registerDepreciatedItemName(Object var1, String var2)
    {
        var2 = GeneralTools.cleanTag(var2);
        Iterator var3 = this.languageTable.keySet().iterator();

        while (var3.hasNext())
        {
            String var4 = (String)var3.next();
            LanguageRegistry.instance().addNameForObject(var1, var4, this.translate(var2, var4) + " (" + this.translate("obsolete", var4) + ")");
        }
    }
}
