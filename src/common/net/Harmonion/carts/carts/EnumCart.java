package net.Harmonion.carts.carts;

import net.minecraft.src.Block;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public enum EnumCart
{
    BASIC(1, 135, "Minecart", EntityCartBasic.class, (ItemStack)null),
    CHEST(2, 151, "Chest Cart", EntityCartChest.class, new ItemStack(Block.chest)),
    FURNACE(3, 167, "Furnace Cart", EntityCartFurnace.class, new ItemStack(Block.stoneOvenIdle));
    private final int icon;
    private final String name;
    private final Class type;
    private final byte id;
    private ItemStack contents = null;

    private EnumCart(int var3, int var4, String var5, Class var6, ItemStack var7)
    {
        this.name = var5;
        this.id = (byte)var3;
        this.icon = var4;
        this.type = var6;
        this.contents = var7;
    }

    public byte getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public int getIconIndex()
    {
        return this.icon;
    }

    public String getTag()
    {
        return "cart." + this.name().toLowerCase().replace('_', '.');
    }

    public String getItemTag()
    {
        return "item.cart." + this.name().toLowerCase().replace('_', '.');
    }

    public Class getCartClass()
    {
        return this.type;
    }

    public void setContents(ItemStack var1)
    {
        this.contents = var1.copy();
    }

    public ItemStack getContents()
    {
        switch (EnumCart$1.$SwitchMap$railcraft$common$carts$EnumCart[this.ordinal()])
        {
            default:
                return this.contents == null ? null : this.contents.copy();
        }
    }

    public EntityMinecart makeCart(ItemStack var1, World var2, double var3, double var5, double var7)
    {
        switch (EnumCart$1.$SwitchMap$railcraft$common$carts$EnumCart[this.ordinal()])
        {
        	case 3:
        		return new EntityCartFurnace(var2, var3, var5, var7);
            
            case 2:
                return new EntityCartChest(var2, var3, var5, var7);

            case 1:
                return new EntityCartFurnace(var2, var3, var5, var7);

            default:
                return new EntityCartBasic(var2, var3, var5, var7);
        }
    }
}
