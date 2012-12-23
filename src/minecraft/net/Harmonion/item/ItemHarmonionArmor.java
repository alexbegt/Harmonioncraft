package net.Harmonion.item;

import net.Harmonion.util.Reference;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemHarmonionArmor extends ItemArmor{

    public ItemHarmonionArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l)
    {
        super(i, enumArmorMaterial, k, l);
        
        this.setMaxDamage(enumArmorMaterial.getDurability(l));
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
        setCreativeTab(ModItems.tabHarmonionI);
    }
}
