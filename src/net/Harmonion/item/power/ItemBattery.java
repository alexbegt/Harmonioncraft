package net.Harmonion.item.power;

import java.util.ArrayList;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.item.ModItems;
import net.Harmonion.power.IChargeableHarmonion;
import net.Harmonion.util.random.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBattery extends Item
{
    public ItemBattery(int var1)
    {
        super(var1);
        this.setIconIndex(8);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setMaxDamage(1500);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftW);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        for (int var4 = 0; var4 < 9; ++var4)
        {
            ItemStack var5 = var3.inventory.getStackInSlot(var4);

            if (var5 != null && var5.getItem() instanceof IChargeableHarmonion && var5.getItemDamage() > 1)
            {
                int var6 = Math.min(var5.getItemDamage() - 1, var1.getMaxDamage() - var1.getItemDamage());
                var6 = Math.min(var6, 25);
                var1.setItemDamage(var1.getItemDamage() + var6);
                var5.setItemDamage(var5.getItemDamage() - var6);
                var3.inventory.onInventoryChanged();

                if (var1.getItemDamage() == var1.getMaxDamage())
                {
                    return new ItemStack(ModItems.itemBatteryEmpty, 1);
                }

                break;
            }
        }

        return var1;
    }

    public void addCreativeItems(ArrayList var1) {}

    public String getTextureFile()
    {
        return Reference.SPRITE_SHEET_LOCATION + Reference.POWER_ITEM_SPRITE_SHEET;
    }
}
