package net.Harmonioncraft.item;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.Harmonioncraft.lib.CustomItemRarity;
import net.Harmonioncraft.lib.Reference;
import net.Harmonioncraft.mods.Harmonioncraft;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class ItemHarmonion extends Item {
	
	public ItemHarmonion(int id) {
        super(id);
        maxStackSize = 1;
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
    }
	
	@SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return Harmonioncraft.proxy.getCustomRarityType(CustomItemRarity.MAGICAL);
    }
	
}
