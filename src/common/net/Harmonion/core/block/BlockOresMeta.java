package net.Harmonion.core.block;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class BlockOresMeta extends ItemBlock
{
    public static final String[] blockType = new String[] {"Soundstoneore", "Resoniumore", "Soundstoneplank", "Soundstoneblock"};

    public BlockOresMeta(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    public String getItemNameIS(ItemStack var1)
    {
        return "tile." + blockType[var1.getItemDamage()];
    }
}
