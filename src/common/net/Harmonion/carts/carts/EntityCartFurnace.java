package net.Harmonion.carts.carts;

import java.util.ArrayList;
import java.util.List;

import net.Harmonion.carts.carts.util.GeneralTools;
import net.Harmonion.carts.carts.util.InventoryTools;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityCartFurnace extends CartBase implements ICartRenderInterface
{
    private static final double DRAG_FACTOR = 0.99D;
    private static final double PUSH_FACTOR = 0.1D;

    public EntityCartFurnace(World var1)
    {
        super(var1);
    }

    public EntityCartFurnace(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4 + (double)this.yOffset, var6);
        this.prevPosX = var2;
        this.prevPosY = var4;
        this.prevPosZ = var6;
        this.fuel = 0;
        this.pushX = 0.0D;
        this.pushZ = 0.0D;
    }

    public boolean isActive()
    {
        return this.fuel > 0;
    }

    protected void applyDragAndPushForces()
    {
        this.motionX *= this.getDrag();
        this.motionY *= 0.0D;
        this.motionZ *= this.getDrag();
        double var1 = (double)MathHelper.sqrt_double(this.pushX * this.pushX + this.pushZ * this.pushZ);

        if (var1 > 0.01D)
        {
            this.pushX /= var1;
            this.pushZ /= var1;
            double var3 = 0.4D;
            this.motionX += this.pushX * var3;
            this.motionZ += this.pushZ * var3;
        }
    }

    protected void updatePushForces()
    {
        double var1 = (double)MathHelper.sqrt_double(this.pushX * this.pushX + this.pushZ * this.pushZ);

        if (var1 > 0.01D && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001D)
        {
            this.pushX /= var1;
            this.pushZ /= var1;

            if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0D)
            {
                this.pushX = 0.0D;
                this.pushZ = 0.0D;
            }
            else
            {
                this.pushX = this.motionX;
                this.pushZ = this.motionZ;
            }
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (this.isMinecartPowered() && this.rand.nextInt(4) == 0)
        {
            this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        super.onUpdate();
    }

    public List getItemsDropped()
    {
        ArrayList var1 = new ArrayList();

        var1.add(this.getCartItem());

        return var1;
    }

    public double getDrag()
    {
        return 0.99D;
    }

    public float getCartMaxSpeed()
    {
        return 0.39F;
    }

    public boolean isPoweredCart()
    {
        return true;
    }

    public boolean canBeRidden()
    {
        return false;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 0;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Steamcart";
    }

    public boolean doInteract(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null)
        {
            int var3 = GeneralTools.getItemBurnTime(var2);

            if (var3 > 0)
            {
                var1.inventory.setInventorySlotContents(var1.inventory.currentItem, InventoryTools.depleteItem(var2));
                this.fuel += var3;
                this.pushX = this.posX - var1.posX;
                this.pushZ = this.posZ - var1.posZ;
            }
        }

        return true;
    }

    public Block getBlock()
    {
        return Block.stoneOvenIdle;
    }

    public int getBlockMetadata()
    {
        return 0;
    }
}
