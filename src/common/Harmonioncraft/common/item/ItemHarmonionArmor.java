package Harmonioncraft.common.item;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import Harmonioncraft.common.Harmonioncraft;
import Harmonioncraft.common.lib.CustomItemRarity;
import Harmonioncraft.common.lib.Reference;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemHarmonionArmor extends ItemArmor{

    public ItemHarmonionArmor(int i, int index, EnumArmorMaterial enumArmorMaterial, int k, int l)
    {
        super(i, enumArmorMaterial, k, l);
        
        this.setMaxDamage(enumArmorMaterial.getDurability(l));
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
        setCreativeTab(ModItems.tabHarmonioncraftI);
    }
    
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return Harmonioncraft.proxy.getCustomRarityType(CustomItemRarity.MAGICAL);
    }
}
