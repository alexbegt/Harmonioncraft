package net.Harmonion.client.textureFX;

import net.Harmonion.liquids.LiquidItems;

public class TexturePhazonFX extends TextureLiquidFX
{
    public TexturePhazonFX()
    {
        super(80, 130, 80, 130, 0, 20, LiquidItems.getPhazon().getIconIndex(), LiquidItems.getPhazon().getItem().getTextureFile());
    }
}