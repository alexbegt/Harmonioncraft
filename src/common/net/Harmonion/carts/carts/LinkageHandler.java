package net.Harmonion.carts.carts;

import net.Harmonion.carts.carts.util.Vec2D;
import net.minecraft.src.BlockRail;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;

public class LinkageHandler
{
    public static final String LINK_A_TIMER = "linkA_timer";
    public static final String LINK_B_TIMER = "linkB_timer";
    public static final float MAX_DISTANCE = 8.0F;
    private static final float STIFFNESS = 0.65F;
    private static final float DAMPING = 0.4F;
    private static final float FORCE_LIMITER = 6.0F;
    private static LinkageHandler instance;

    public static LinkageHandler getInstance()
    {
        if (instance == null)
        {
            instance = new LinkageHandler();
        }

        return instance;
    }

    private float getOptimalDistance(EntityMinecart var1, EntityMinecart var2)
    {
        float var3 = 0.0F;

        if (var1 instanceof ILinkableCart)
        {
            var3 += ((ILinkableCart)var1).getOptimalDistance(var2);
        }
        else
        {
            var3 += 0.78F;
        }

        if (var2 instanceof ILinkableCart)
        {
            var3 += ((ILinkableCart)var2).getOptimalDistance(var1);
        }
        else
        {
            var3 += 0.78F;
        }

        return var3;
    }

    private boolean canCartBeAdjustedBy(EntityMinecart var1, EntityMinecart var2)
    {
        return var1 == var2 ? false : (var1 instanceof ILinkableCart && !((ILinkableCart)var1).canBeAdjusted(var2) ? false : false);
    }

    protected void adjustVelocity(EntityMinecart var1, EntityMinecart var2, char var3)
    {
        String var4 = "linkA_timer";

        if (var3 == 66)
        {
            var4 = "linkB_timer";
        }

        if (var1.worldObj.provider.dimensionId != var2.worldObj.provider.dimensionId)
        {
            short var27 = var1.getEntityData().getShort(var4);
            ++var27;

            if (var27 > 200)
            {
                LinkageManager.getInstance(var1.worldObj).breakLink(var1, var2);
            }

            var1.getEntityData().setShort(var4, var27);
        }
        else
        {
            var1.getEntityData().setShort(var4, (short)0);
            double var5 = (double)var1.getDistanceToEntity(var2);

            if (var5 > 8.0D)
            {
                LinkageManager.getInstance(var1.worldObj).breakLink(var1, var2);
            }
            else
            {
                boolean var7 = this.canCartBeAdjustedBy(var1, var2);
                boolean var8 = this.canCartBeAdjustedBy(var2, var1);
                Vec2D var9 = new Vec2D(var1.posX, var1.posZ);
                Vec2D var10 = new Vec2D(var2.posX, var2.posZ);
                Vec2D var11 = Vec2D.subtract(var10, var9);
                var11.normalize();
                float var12 = this.getOptimalDistance(var1, var2);
                double var13 = var5 - (double)var12;
                double var15 = 0.6499999761581421D * var13 * var11.getX();
                double var17 = 0.6499999761581421D * var13 * var11.getY();
                var15 = this.limitForce(var15);
                var17 = this.limitForce(var17);

                if (var7)
                {
                    var1.motionX += var15;
                    var1.motionZ += var17;
                }

                if (var8)
                {
                    var2.motionX -= var15;
                    var2.motionZ -= var17;
                }

                Vec2D var19 = new Vec2D(var1.motionX, var1.motionZ);
                Vec2D var20 = new Vec2D(var2.motionX, var2.motionZ);
                double var21 = Vec2D.subtract(var20, var19).dotProduct(var11);
                double var23 = 0.4000000059604645D * var21 * var11.getX();
                double var25 = 0.4000000059604645D * var21 * var11.getY();
                var23 = this.limitForce(var23);
                var25 = this.limitForce(var25);

                if (var7)
                {
                    var1.motionX += var23;
                    var1.motionZ += var25;
                }

                if (var8)
                {
                    var2.motionX -= var23;
                    var2.motionZ -= var25;
                }
            }
        }
    }

    private float getTrainMaxSpeed(EntityMinecart var1)
    {
        LinkageManager var2 = LinkageManager.getInstance(var1.worldObj);
        EntityMinecart var3 = var2.getLinkedCartA(var1);
        EntityMinecart var4 = var2.getLinkedCartB(var1);
        float var5 = var1.getMaxSpeedRail();

        if (var1 instanceof IMinecart)
        {
            var5 = ((IMinecart)var1).getCartMaxSpeed();
        }

        float var6 = Math.min(var5, this.getTrainMaxSpeedRecursive(var3, var1));
        float var7 = Math.min(var5, this.getTrainMaxSpeedRecursive(var4, var1));
        return Math.min(var6, var7);
    }

    private float getTrainMaxSpeedRecursive(EntityMinecart var1, EntityMinecart var2)
    {
        if (var1 == null)
        {
            return Float.MAX_VALUE;
        }
        else
        {
            LinkageManager var3 = LinkageManager.getInstance(var1.worldObj);
            EntityMinecart var4 = var3.getLinkedCartA(var1);
            EntityMinecart var5 = var3.getLinkedCartB(var1);
            float var6 = var1.getMaxSpeedRail();

            if (var1 instanceof IMinecart)
            {
                var6 = ((IMinecart)var1).getCartMaxSpeed();
            }

            float var7 = var6;

            if (var4 != var2)
            {
                var7 = Math.min(var6, this.getTrainMaxSpeedRecursive(var4, var1));
            }

            float var8 = var6;

            if (var5 != var2)
            {
                var8 = Math.min(var6, this.getTrainMaxSpeedRecursive(var5, var1));
            }

            return Math.min(var7, var8);
        }
    }

    private void setTrainMaxSpeed(EntityMinecart var1, float var2)
    {
        LinkageManager var3 = LinkageManager.getInstance(var1.worldObj);
        EntityMinecart var4 = var3.getLinkedCartA(var1);
        EntityMinecart var5 = var3.getLinkedCartB(var1);
        this.setTrainMaxSpeedRecursive(var4, var1, var2);
        this.setTrainMaxSpeedRecursive(var5, var1, var2);

        if (var1 instanceof IMinecart)
        {
            ((IMinecart)var1).setTrainSpeed(var2);
        }
        else
        {
            var1.setMaxSpeedRail(var2);
        }
    }

    private void setTrainMaxSpeedRecursive(EntityMinecart var1, EntityMinecart var2, float var3)
    {
        if (var1 != null)
        {
            LinkageManager var4 = LinkageManager.getInstance(var1.worldObj);
            EntityMinecart var5 = var4.getLinkedCartA(var1);
            EntityMinecart var6 = var4.getLinkedCartB(var1);

            if (var5 != var2)
            {
                this.setTrainMaxSpeedRecursive(var5, var1, var3);
            }

            if (var6 != var2)
            {
                this.setTrainMaxSpeedRecursive(var6, var1, var3);
            }

            if (var1 instanceof IMinecart)
            {
                ((IMinecart)var1).setTrainSpeed(var3);
            }
            else
            {
                var1.setMaxSpeedRail(var3);
            }
        }
    }

    private double limitForce(double var1)
    {
        return Math.copySign(Math.min(Math.abs(var1), 6.0D), var1);
    }

    @ForgeSubscribe
    public void onMinecartUpdate(MinecartUpdateEvent var1)
    {
        EntityMinecart var2 = var1.minecart;
        LinkageManager var3 = LinkageManager.getInstance(var2.worldObj);

        if (var2.isDead)
        {
            var3.breakLinks(var2);
            var3.removeLinkageId(var2);
        }
        else
        {
            var3.getLinkageId(var2);
            EntityMinecart var4 = var3.getLinkedCartA(var2);

            if (var4 != null)
            {
                if (var4.isDead)
                {
                    var3.breakLinkA(var2);
                    var3.removeLinkageId(var4);
                }
                else
                {
                    this.adjustVelocity(var2, var4, 'A');
                }
            }

            EntityMinecart var5 = var3.getLinkedCartB(var2);

            if (var5 != null)
            {
                if (var5.isDead)
                {
                    var3.breakLinkB(var2);
                    var3.removeLinkageId(var5);
                }
                else
                {
                    this.adjustVelocity(var2, var5, 'B');
                }
            }

            if ((var4 != null || var5 == null) && (var4 == null || var5 != null))
            {
                if (var4 == null && var5 == null)
                {
                    this.setTrainMaxSpeed(var2, 1.2F);
                }
            }
            else
            {
                float var6 = this.getTrainMaxSpeed(var2);
                this.setTrainMaxSpeed(var2, var6);
            }
        }
    }

    @ForgeSubscribe
    public void onMinecartInteract(MinecartInteractEvent var1)
    {
        EntityPlayer var2 = var1.player;
    }
}
