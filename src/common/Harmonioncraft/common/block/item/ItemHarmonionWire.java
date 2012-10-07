package Harmonioncraft.common.block.item;

import java.util.List;

import Harmonioncraft.common.block.ModBlocks;
import Harmonioncraft.common.entity.TileEntityHarmonionWire;
import Harmonioncraft.common.power.ElectricInfo;
import Harmonioncraft.common.power.ElectricInfo.ElectricUnit;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemHarmonionWire extends ItemBlock {
	public ItemHarmonionWire(int id)
    {
        super(id);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getItemName() {
    	return super.getItemName();
    }
    /**public String getItemName(ItemStack itemstack)
    {
    	return "item.Soundstonewire.name"; /**(new StringBuilder())
                .append("item")
                .append(".")
                .append("Soundstonewire")
                .append(".")
                .append("name")
                .toString();
    }*/
    
    @Override
    public void addInformation(ItemStack par1ItemStack, List par2List)
    {
    	par2List.add("Resistance: "+ElectricInfo.getDisplay(TileEntityHarmonionWire.RESISTANCE, ElectricUnit.RESISTANCE));
    	par2List.add("Max Amps: "+ElectricInfo.getDisplay(TileEntityHarmonionWire.MAX_AMPS, ElectricUnit.AMPERE));
    }

}
