package net.Harmonion.block.tank;

import java.util.ArrayList;
import java.util.List;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.modules.ModuleManager;
import net.Harmonion.modules.ModuleManager.Module;
import net.Harmonion.tanks.IEnumMachine;
import net.Harmonion.tanks.TileMachineBase;
import net.Harmonion.util.Config;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public enum EnumMachineBeta implements IEnumMachine
{
    TANK_IRON_WALL(Module.TANKS, "tankwall", TileTankHarmonionWall.class, new int[]{19, 19, 20, 20, 20, 20}),
    TANK_IRON_GAUGE(Module.TANKS, "tankgauge", TileTankHarmonionGauge.class, new int[]{36, 36, 36, 36, 36, 36, 52, 53, 37, 21}),
    TANK_IRON_VALVE(Module.TANKS, "tankvalve", TileTankHarmonionValve.class, new int[]{35, 35, 51, 51, 51, 51, 19, 20});
    private final Module module;
    private final String tag;
    private final Class tile;
    private final int[] texture;
    private static final List creativeList = new ArrayList();
    private static final EnumMachineBeta[] VALUES = values();

    private EnumMachineBeta(Module var3, String var4, Class var5, int[] var6)
    {
        this.module = var3;
        this.tile = var5;
        this.tag = var4;
        this.texture = var6;
    }

    public boolean isDepreciated()
    {
        return this.module == null;
    }

    public int[] getTexture()
    {
        return this.texture;
    }

    public int getTexture(int var1)
    {
        if (var1 < 0 || var1 >= this.texture.length)
        {
            var1 = 0;
        }

        return this.texture[var1];
    }

    public static EnumMachineBeta fromId(int var0)
    {
        if (var0 < 0 || var0 >= VALUES.length)
        {
            var0 = 0;
        }

        return VALUES[var0];
    }

    public static List getCreativeList()
    {
        return creativeList;
    }

    public String getTag()
    {
        return "tile." + this.tag;
    }
    
    public void addItemInfo(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        switch (EnumMachineBeta$1.$SwitchMap$railcraft$common$blocks$machine$beta$EnumMachineBeta[this.ordinal()])
        {
            default:
                String var5 = "Multi-Block";
                
                //var3.add(var5);
        }
    }

    public Class getTileClass()
    {
        return this.tile;
    }

    public TileMachineBase getTileEntity()
    {
        try
        {
            return (TileMachineBase)this.tile.newInstance();
        }
        catch (Exception var2)
        {
            return null;
        }
    }

    public ItemStack getItem()
    {
        return this.getItem(1);
    }

    public ItemStack getItem(int var1)
    {
        Block var2 = this.getBlock();
        return var2 == null ? null : new ItemStack(var2, var1, this.ordinal());
    }

    public Module getModule()
    {
        return this.module;
    }

    public Block getBlock()
    {
        return ModBlocks.getBlockMachineBeta();
    }

    public int getBlockId()
    {
        Block var1 = this.getBlock();
        return var1 != null ? var1.blockID : 0;
    }

    public boolean isEnabled()
    {
        return ModuleManager.isModuleLoaded(this.getModule()) && this.getBlock() != null;
    }

    static {
        creativeList.add(TANK_IRON_WALL);
        creativeList.add(TANK_IRON_GAUGE);
        creativeList.add(TANK_IRON_VALVE);
    }
}
