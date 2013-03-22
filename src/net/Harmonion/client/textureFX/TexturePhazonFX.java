package net.Harmonion.client.textureFX;

import net.Harmonion.liquids.LiquidItems;

public class TexturePhazonFX extends TextureLiquidFX
{
    public TexturePhazonFX()
    {
        super(0, 0, 200, 255, 200, 255, LiquidItems.getPhazon().getIconIndex(), LiquidItems.getPhazon().getItem().getTextureFile());
    }
}