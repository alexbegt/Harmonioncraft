package net.Harmonion.item;

import net.Harmonion.util.random.Reference;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemHarmonionArmor extends ItemArmor implements IArmorTextureProvider{

    public ItemHarmonionArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l)
    {
        super(i, enumArmorMaterial, k, l);
        
        this.setMaxDamage(enumArmorMaterial.getDurability(l));
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
        setCreativeTab(ModItems.tabHarmonionI);
    }
    
    @Override
    public String getArmorTextureFile(ItemStack var1)
    {
        return this.armorType == 2 ? "/net/Harmonion/client/textures/armor/soundstone_2.png" : "/net/Harmonion/client/textures/armor/soundstone_1.png";
    }
    
}
