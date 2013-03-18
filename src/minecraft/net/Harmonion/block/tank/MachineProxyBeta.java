package net.Harmonion.block.tank;

import java.util.List;

import net.Harmonion.tanks.IMachineProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class MachineProxyBeta implements IMachineProxy
{
    public String getTag(int var1)
    {
        return EnumMachineBeta.fromId(var1).getTag();
    }

    public int getTexture(int var1, int var2)
    {
        return EnumMachineBeta.fromId(var1).getTexture(var2);
    }

    public Class getTileClass(int var1)
    {
        return EnumMachineBeta.fromId(var1).getTileClass();
    }

    public TileEntity getTileEntity(int var1)
    {
        return EnumMachineBeta.fromId(var1).getTileEntity();
    }

    public List getCreativeList()
    {
        return EnumMachineBeta.getCreativeList();
    }
    
    public void addItemInfo(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        EnumMachineBeta.fromId(var1.getItemDamage()).addItemInfo(var1, var2, var3, var4);
    }
}
