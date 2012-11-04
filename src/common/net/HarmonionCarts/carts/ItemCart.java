package net.HarmonionCarts.carts;

import net.HarmonionCarts.carts.util.Game;
import net.minecraft.src.BlockRail;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemMinecart;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemCart extends ItemMinecart implements IMinecartItem
{
    private final EnumCart type;

    public ItemCart(int var1, EnumCart var2)
    {
        super(var1, 0);
        this.maxStackSize = 0;
        this.type = var2;
        this.setIconIndex(var2.getIconIndex());
        this.setItemName(var2.getTag());
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public String getTextureFile()
    {
        return "/railcraft/client/textures/railcraft.png";
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (Game.isNotHost(var3))
        {
            return false;
        }
        else
        {
            EntityMinecart var11 = this.placeCart(var2.username, var1, var3, var4, var5, var6);

            if (var11 != null)
            {
                --var1.stackSize;
            }

            return var11 != null;
        }
    }

    public EnumCart getCartType()
    {
        return this.type;
    }

    public boolean canBePlacedByNonPlayer(ItemStack var1)
    {
        return true;
    }

    public EntityMinecart placeCart(String var1, ItemStack var2, World var3, int var4, int var5, int var6)
    {
        int var7 = var3.getBlockId(var4, var5, var6);

        if (BlockRail.isRailBlock(var7) && !CartTools.isMinecartAt(var3, var4, var5, var6, 0.0F))
        {
            EntityMinecart var8 = this.type.makeCart(var2, var3, (double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F));
            CartTools.setCartOwner(var8, var1);

            if (var3.spawnEntityInWorld(var8))
            {
                return var8;
            }
        }

        return null;
    }

    public static EnumCart getCartType(ItemStack var0)
    {
        return var0 != null && var0.getItem() instanceof ItemCart ? ((ItemCart)var0.getItem()).getCartType() : null;
    }
}
