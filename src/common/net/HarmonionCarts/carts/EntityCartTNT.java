package net.HarmonionCarts.carts;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.HarmonionCarts.carts.util.Game;
import net.HarmonionCarts.carts.util.IExplosiveCart;
import net.minecraft.src.Block;
import net.minecraft.src.BlockRail;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
public class EntityCartTNT extends CartBase implements ICartRenderInterface, IExplosiveCart
{
    private static final byte FUSE_DATA_ID = 20;
    private static final byte BLAST_DATA_ID = 21;
    private static final byte PRIMED_DATA_ID = 22;
    private static final float BLAST_RADIUS_MULTIPLIER = 0.5F;
    private static final byte BLAST_RADIUS_MIN = 4;
    private static final byte BLAST_RADIUS_MAX = 12;
    public static final short MAX_FUSE = 500;
    public static final short MIN_FUSE = 0;
    private double prevMotionX;
    private double prevMotionZ;
    private boolean prevOnGround;
    private boolean prevOnRail;
    private short timeInAir;

    public EntityCartTNT(World var1)
    {
        super(var1);
        this.timeInAir = 0;
    }

    public EntityCartTNT(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4 + (double)this.yOffset, var6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = var2;
        this.prevPosY = var4;
        this.prevPosZ = var6;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(20, Short.valueOf((short)80));
        this.dataWatcher.addObject(21, Byte.valueOf((byte)9));
        this.dataWatcher.addObject(22, Byte.valueOf((byte)0));
    }

    public List getItemsDropped()
    {
        ArrayList var1 = new ArrayList();

        var1.add(this.getCartItem());

        return var1;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.motionX == 0.0D ^ this.prevMotionX == 0.0D && Math.abs(this.prevMotionX) > 0.4000000059604645D && this.motionZ == 0.0D || this.motionZ == 0.0D ^ this.prevMotionZ == 0.0D && Math.abs(this.prevMotionZ) > 0.4000000059604645D && this.motionX == 0.0D || this.onGround && !this.prevOnGround && !this.prevOnRail && this.timeInAir > 5)
        {
            this.explode();
        }

        this.prevMotionX = this.motionX;
        this.prevMotionZ = this.motionZ;
        this.prevOnGround = this.onGround;
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);

        if (BlockRail.isRailBlockAt(this.worldObj, var1, var2 - 1, var3))
        {
            --var2;
        }

        int var4 = this.worldObj.getBlockId(var1, var2, var3);
        this.prevOnRail = BlockRail.isRailBlock(var4);

        if (!this.onGround && !this.prevOnRail)
        {
            ++this.timeInAir;
        }
        else
        {
            this.timeInAir = 0;
        }

        if (this.isPrimed())
        {
            this.setFuse((short)(this.getFuse() - 1));

            if (this.getFuse() <= 0)
            {
                this.explode();
            }
            else
            {
                this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.8D, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public void explode()
    {
        if (Game.isHost(this.getWorld()))
        {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.getBlastRadius(), true);
            this.setDead();
        }
    }

    public boolean doInteract(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null)
        {
            if (var2.itemID == Item.flintAndSteel.shiftedIndex)
            {
                this.setPrimed(true);
            }
        }

        return true;
    }

    protected double getDrag()
    {
        return 0.991999979019165D;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 0;
    }

    public boolean isStorageCart()
    {
        return false;
    }

    public boolean canBeRidden()
    {
        return false;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "TNTcart";
    }

    public boolean isPrimed()
    {
        return this.dataWatcher.getWatchableObjectByte(22) != 0;
    }

    public void setPrimed(boolean var1)
    {
        this.dataWatcher.updateObject(22, var1 ? Byte.valueOf((byte)1) : Byte.valueOf((byte)0));
    }

    public int getFuse()
    {
        return this.dataWatcher.getWatchableObjectShort(20);
    }

    public void setFuse(int var1)
    {
        short var2 = (short)Math.max(var1, 0);
        var2 = (short)Math.min(var2, 500);
        this.dataWatcher.updateObject(20, Short.valueOf((short)var2));
    }

    protected byte getMinBlastRadius()
    {
        return (byte)4;
    }

    protected byte getMaxBlastRadius()
    {
        return (byte)12;
    }

    public float getBlastRadius()
    {
        return (float)this.dataWatcher.getWatchableObjectByte(21) * 0.5F;
    }

    public void setBlastRadius(float var1)
    {
        var1 /= 0.5F;
        var1 = Math.max(var1, (float)this.getMinBlastRadius());
        var1 = Math.min(var1, (float)this.getMaxBlastRadius());
        this.dataWatcher.updateObject(21, Byte.valueOf((byte)((int)var1)));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setShort("Fuse", (short)this.getFuse());
        var1.setByte("blastRadius", this.dataWatcher.getWatchableObjectByte(21));
        var1.setBoolean("Primed", this.isPrimed());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
        this.setFuse(var1.getShort("Fuse"));
        this.setBlastRadius((float)var1.getByte("blastRadius"));
        this.setPrimed(var1.getBoolean("Primed"));
    }

    public void writeGuiData(DataOutputStream var1) throws IOException
    {
        var1.writeShort(this.getFuse());
        var1.writeByte(this.dataWatcher.getWatchableObjectByte(21));
    }

    public void readGuiData(DataInputStream var1) throws IOException
    {
        this.setFuse(var1.readShort());
        this.setBlastRadius((float)var1.readByte());
    }

    public World getWorld()
    {
        return this.worldObj;
    }

    public Block getBlock()
    {
        return Block.tnt;
    }

    public int getBlockMetadata()
    {
        return 0;
    }
}
