package net.Harmonion.carts.carts;

import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

public abstract class CartBase extends EntityMinecart implements IMinecart
{
    private float trainSpeed = 1.2F;

    public CartBase(World var1)
    {
        super(var1);
        CartTools.setCartOwner(this, "[Railcraft]");
    }

    public World getWorld()
    {
        return this.worldObj;
    }

    public final float getMaxSpeedRail()
    {
        return Math.min(this.getCartMaxSpeed(), this.trainSpeed);
    }

    public float getCartMaxSpeed()
    {
        return 1.2F;
    }

    public final void setTrainSpeed(float var1)
    {
        this.trainSpeed = var1;
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public final boolean interact(EntityPlayer var1)
    {
        if (MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, var1)))
        {
            return true;
        }
        else
        {
            if (CartTools.getCartOwner(this).equals("[Railcraft]"))
            {
                CartTools.setCartOwner(this, var1);
            }

            return this.doInteract(var1);
        }
    }

    public boolean doInteract(EntityPlayer var1)
    {
        return super.interact(var1);
    }

    public boolean doesCartMatchFilter(ItemStack var1, EntityMinecart var2)
    {
        if (var1 != null && var2 != null)
        {
            ItemStack var3 = var2.getCartItem();
            return var3 != null && var1.isItemEqual(var3);
        }
        else
        {
            return false;
        }
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        for (int var1 = 0; var1 < this.getSizeInventory(); ++var1)
        {
            ItemStack var2 = this.getStackInSlot(var1);
            this.setInventorySlotContents(var1, (ItemStack)null);

            if (!this.worldObj.isRemote && var2 != null)
            {
                float var3 = this.rand.nextFloat() * 0.8F + 0.1F;
                float var4 = this.rand.nextFloat() * 0.8F + 0.1F;
                float var5 = this.rand.nextFloat() * 0.8F + 0.1F;

                while (var2.stackSize > 0)
                {
                    int var6 = this.rand.nextInt(21) + 10;

                    if (var6 > var2.stackSize)
                    {
                        var6 = var2.stackSize;
                    }

                    var2.stackSize -= var6;
                    EntityItem var7 = new EntityItem(this.worldObj, this.posX + (double)var3, this.posY + (double)var4, this.posZ + (double)var5, new ItemStack(var2.itemID, var6, var2.getItemDamage()));

                    if (var2.hasTagCompound())
                    {
                        var7.item.setTagCompound((NBTTagCompound)var2.getTagCompound().copy());
                    }

                    float var8 = 0.05F;
                    var7.motionX = (double)((float)this.rand.nextGaussian() * var8);
                    var7.motionY = (double)((float)this.rand.nextGaussian() * var8 + 0.2F);
                    var7.motionZ = (double)((float)this.rand.nextGaussian() * var8);
                    this.worldObj.spawnEntityInWorld(var7);
                }
            }
        }

        super.setDead();
    }
}
