package net.Harmonion.item.tanks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.Harmonion.block.ModBlocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRailcraft extends Item
{
    private float smeltingExperiance = -1.0F;
    private int rarity = 0;

    public ItemRailcraft(int var1)
    {
        super(var1);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
    }

    public ItemRailcraft setRarity(int var1)
    {
        this.rarity = var1;
        return this;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack var1)
    {
        return EnumRarity.values()[this.rarity];
    }

    public String getTextureFile()
    {
        return "/railcraft/client/textures/railcraft.png";
    }

    public ItemRailcraft setSmeltingExperiance(float var1)
    {
        this.smeltingExperiance = var1;
        return this;
    }

    public float getSmeltingExperience(ItemStack var1)
    {
        return this.smeltingExperiance;
    }
}
