package net.Harmonion.util.misc;

import net.Harmonion.util.LocalizationHandler;

public enum EnumColor
{
    BLACK(2960685),
    RED(10696757),
    GREEN(3755038),
    BROWN(6044196),
    BLUE(3424674),
    PURPLE(8667071),
    CYAN(3571870),
    LIGHT_GRAY(8947848),
    GRAY(4473924),
    PINK(15041952),
    LIME(4172342),
    YELLOW(13615665),
    LIGHT_BLUE(8362705),
    MAGENTA(16737535),
    ORANGE(16738816),
    WHITE(16777215);
    public static final EnumColor[] VALUES = values();
    public static final String[] DYES = new String[]{"dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite"};
    private final int color;

    private EnumColor(int var3)
    {
        this.color = var3;
    }

    public int getHexColor()
    {
        return this.color;
    }

    public static EnumColor fromId(int var0)
    {
        return var0 >= 0 && var0 < VALUES.length ? VALUES[var0] : WHITE;
    }

    public static EnumColor fromDye(String var0)
    {
        for (int var1 = 0; var1 < DYES.length; ++var1)
        {
            if (DYES[var1].equals(var0))
            {
                return VALUES[var1];
            }
        }

        return null;
    }

    public static EnumColor getRand()
    {
        return VALUES[MiscTools.getRand().nextInt(VALUES.length)];
    }

    public String getTag()
    {
        return "color." + this.name().replace("_", ".").toLowerCase();
    }

    public String getBasicTag()
    {
        return this.name().replace("_", ".").toLowerCase();
    }

    public String getTranslatedName()
    {
        return LocalizationHandler.translate(this.getTag());
    }

    public String getDye()
    {
        return DYES[this.ordinal()];
    }

    public String toString()
    {
        String var1 = this.name().replace("_", " ");
        String[] var2 = var1.split(" ");
        StringBuilder var3 = new StringBuilder();
        String[] var4 = var2;
        int var5 = var2.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            String var7 = var4[var6];
            var3.append(var7.charAt(0)).append(var7.substring(1).toLowerCase()).append(" ");
        }

        return var3.toString().trim();
    }
}
