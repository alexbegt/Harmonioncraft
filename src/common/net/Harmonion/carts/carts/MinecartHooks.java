package net.Harmonion.carts.carts;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.Harmonion.carts.carts.util.Game;
import net.Harmonion.carts.carts.util.GeneralTools;
import net.Harmonion.carts.carts.util.Vec2D;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockRail;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityIronGolem;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.IMinecartCollisionHandler;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;

public final class MinecartHooks implements IMinecartCollisionHandler
{
    private static final float BLAST_RADIUS = 1.5F;
    protected static float DRAG_FACTOR_GROUND = 0.5F;
    protected static float DRAG_FACTOR_AIR = 0.99999F;
    protected static float OPTIMAL_DISTANCE = 1.28F;
    protected static float COEF_SPRING = 0.2F;
    protected static float COEF_SPRING_PLAYER = 0.5F;
    protected static float COEF_RESTITUTION = 0.2F;
    protected static float COEF_DAMPING = 0.4F;
    protected static float ENTITY_REDUCTION = 0.25F;
    protected static float CART_LENGTH = 1.22F;
    protected static float CART_WIDTH = 0.98F;
    protected static float COLLISION_EXPANSION = 0.2F;
    protected static float MAX_INTERACT_DISTANCE = 9.0F;
    private Random rand = new Random();
    private static MinecartHooks instance;

    public static MinecartHooks getInstance()
    {
        if (instance == null)
        {
            instance = new MinecartHooks();
        }

        return instance;
    }

    public void onEntityCollision(EntityMinecart var1, Entity var2)
    {
        if (!Game.isNotHost(var1.worldObj) && var2 != var1.riddenByEntity)
        {
            LinkageManager var3 = LinkageManager.getInstance(var1.worldObj);
            EntityMinecart var4 = var3.getLinkedCartA(var1);

            if (var4 == null || var4 != var2 && var2 != var4.riddenByEntity)
            {
                var4 = var3.getLinkedCartB(var1);

                if (var4 == null || var4 != var2 && var2 != var4.riddenByEntity)
                {
                    boolean var5 = var2 instanceof EntityLiving;
                    boolean var6 = var2 instanceof EntityPlayer;
                    int var7;

                    if (var5 && !var6 && var1.canBeRidden() && !(var2 instanceof EntityIronGolem) && var1.motionX * var1.motionX + var1.motionZ * var1.motionZ > 0.001D && var1.riddenByEntity == null && var2.ridingEntity == null)
                    {
                        var7 = var1.getEntityData().getInteger("MountPrevention");

                        if (var7 <= 0)
                        {
                            var2.mountEntity(var1);
                        }
                    }

                    var7 = MathHelper.floor_double(var1.posX);
                    int var8 = MathHelper.floor_double(var1.posY);
                    int var9 = MathHelper.floor_double(var1.posZ);
                    int var10 = var1.worldObj.getBlockId(var7, var8, var9);
                }
            }
        }
    }

    public AxisAlignedBB getCollisionBox(EntityMinecart var1, Entity var2)
    {
        return var2 instanceof EntityPlayer ? var2.boundingBox : null;
    }

    public AxisAlignedBB getMinecartCollisionBox(EntityMinecart var1)
    {
        return this.getMinecartCollisionBox(var1, COLLISION_EXPANSION);
    }

    private AxisAlignedBB getMinecartCollisionBox(EntityMinecart var1, float var2)
    {
        double var3 = Math.toRadians((double)var1.rotationYaw);
        double var5 = (double)(CART_LENGTH - CART_WIDTH) / 2.0D + (double)var2;
        double var7 = var5 * Math.abs(Math.cos(var3));
        double var9 = var5 * Math.abs(Math.sin(var3));
        return var1.boundingBox.expand(var7, (double)var2, var9);
    }

    public AxisAlignedBB getBoundingBox(EntityMinecart var1)
    {
        return var1 == null ? null : var1.boundingBox;
    }

    @ForgeSubscribe
    public void onMinecartUpdate(MinecartUpdateEvent var1)
    {
        EntityMinecart var2 = var1.minecart;
        int var3 = (int)var1.x;
        int var4 = (int)var1.y;
        int var5 = (int)var1.z;
        int var6 = var2.worldObj.getBlockId(var3, var4, var5);
        int var7;

        if (BlockRail.isRailBlock(var6))
        {
            var2.fallDistance = 0.0F;

            if (var2.riddenByEntity != null)
            {
                var2.riddenByEntity.fallDistance = 0.0F;
            }
        }
        else
        {
            var7 = var2.getEntityData().getInteger("Launched");

            if (var7 == 1)
            {
                var2.getEntityData().setInteger("Launched", 2);
                var2.setCanUseRail(true);
            }
            else if (var7 == 2 && (var2.onGround || var2.isInsideOfMaterial(Material.circuits)))
            {
                var2.getEntityData().setInteger("Launched", 0);
                var2.setMaxSpeedAirLateral(EntityMinecart.defaultMaxSpeedAirLateral);
                var2.setMaxSpeedAirVertical(EntityMinecart.defaultMaxSpeedAirVertical);
                var2.setDragAir(EntityMinecart.defaultDragAir);
            }
        }

        var7 = var2.getEntityData().getInteger("MountPrevention");

        if (var7 > 0)
        {
            --var7;
        }

        var2.getEntityData().setInteger("MountPrevention", var7);

        if (var2.getEntityData().getBoolean("explode"))
        {
            this.explode(var2);
        }

        boolean var8 = var2.getEntityData().getBoolean("HighSpeed");

        if (var8)
        {
            double var9 = var2.getEntityData().getDouble("PrevMotionX");
            double var11 = var2.getEntityData().getDouble("PrevMotionZ");

            if (!this.isSpeedRailAt(var2.worldObj, var3, var4, var5) || var2.motionX == 0.0D && var2.motionZ == 0.0D || var2.motionX == 0.0D && Math.abs(var9) > 0.38999998569488525D || var2.motionZ == 0.0D && Math.abs(var11) > 0.38999998569488525D)
            {
                System.out.println("Exploded in doUpdate: [" + var3 + ", " + var4 + ", " + var5 + "], rail = " + BlockRail.isRailBlockAt(var2.worldObj, var3, var4, var5) + ", speed rail = " + this.isSpeedRailAt(var2.worldObj, var3, var4, var5) + ", mx = " + var2.motionX + ", pmx = " + var9 + ", mz = " + var2.motionZ + ", pmz = " + var11);
                this.explode(var2);
            }

            if (Math.abs(var2.motionX) < 0.38999998569488525D && Math.abs(var2.motionZ) < 0.38999998569488525D)
            {
                var2.getEntityData().setBoolean("HighSpeed", false);
            }
        }

        var2.getEntityData().setDouble("PrevMotionX", var2.motionX);
        var2.getEntityData().setDouble("PrevMotionZ", var2.motionZ);
        var2.motionX = Math.copySign(Math.min(Math.abs(var2.motionX), 9.5D), var2.motionX);
        var2.motionY = Math.copySign(Math.min(Math.abs(var2.motionY), 9.5D), var2.motionY);
        var2.motionZ = Math.copySign(Math.min(Math.abs(var2.motionZ), 9.5D), var2.motionZ);
        List var13 = var2.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.getMinecartCollisionBox(var2, COLLISION_EXPANSION));

        if (var13 != null)
        {
            Iterator var10 = var13.iterator();

            while (var10.hasNext())
            {
                Entity var14 = (Entity)var10.next();

                if (var14 != var2.riddenByEntity && var14.canBePushed())
                {
                    var2.applyEntityCollision(var14);
                }
            }
        }
    }

    private boolean isSpeedRailAt(World var1, int var2, int var3, int var4)
    {
        TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);

        return false;
    }

    @ForgeSubscribe
    public void onMinecartEntityCollision(MinecartCollisionEvent var1)
    {
        EntityMinecart var2 = var1.minecart;
        Entity var3 = var1.collider;

        if (var3 != var2.riddenByEntity)
        {
            this.testHighSpeed(var2, var3);
            int var4 = MathHelper.floor_double(var2.posX);
            int var5 = MathHelper.floor_double(var2.posY);
            int var6 = MathHelper.floor_double(var2.posZ);

            if (GeneralTools.getRand().nextFloat() < 0.001F)
            {
                List var7 = CartTools.getMinecartsAt(var2.worldObj, var4, var5, var6, 0.0F);

                if (var7.size() > 5)
                {
                    this.primeToExplode(var2);
                }
            }
        }
    }

    private void testHighSpeed(EntityMinecart var1, Entity var2)
    {
        boolean var3 = var1.getEntityData().getBoolean("HighSpeed");

        if (var3)
        {
            LinkageManager var4 = LinkageManager.getInstance(var1.worldObj);
            EntityMinecart var5 = var4.getLinkedCartA(var1);

            if (var5 != null && (var5 == var2 || var2 == var5.riddenByEntity))
            {
                return;
            }

            var5 = var4.getLinkedCartB(var1);

            if (var5 != null && (var5 == var2 || var2 == var5.riddenByEntity))
            {
                return;
            }

            if (!(var2 instanceof EntityMinecart))
            {
                this.primeToExplode(var1);
            }
            else if (var2 instanceof EntityMinecart)
            {
                boolean var6 = var2.getEntityData().getBoolean("HighSpeed");

                if (!var6 || var1.motionX > 0.0D ^ var2.motionX > 0.0D || var1.motionZ > 0.0D ^ var2.motionZ > 0.0D)
                {
                    this.primeToExplode(var1);
                }
            }
        }
    }

    private void primeToExplode(EntityMinecart var1)
    {
        var1.getEntityData().setBoolean("explode", true);
    }

    private void explode(EntityMinecart var1)
    {
        var1.getEntityData().setBoolean("HighSpeed", false);
        var1.getEntityData().setBoolean("explode", false);
        var1.motionX = 0.0D;
        var1.motionZ = 0.0D;

        if (!Game.isNotHost(var1.worldObj))
        {
            if (var1.riddenByEntity != null)
            {
                var1.riddenByEntity.mountEntity(var1);
            }

            var1.worldObj.createExplosion(var1, var1.posX, var1.posY, var1.posZ, 1.5F, true);

            if (this.rand.nextInt(3) == 0)
            {
                var1.setDead();
            }
        }
    }

    @ForgeSubscribe
    public void onMinecartInteract(MinecartInteractEvent var1)
    {
        EntityMinecart var2 = var1.minecart;
        EntityPlayer var3 = var1.player;

        if (CartTools.getCartOwner(var2).equals("[Railcraft]") || CartTools.getCartOwner(var2).equals(""))
        {
            CartTools.setCartOwner(var2, var3);
        }
        
        if (var2.isDead)
        {
            var1.setCanceled(true);
        }
        else
        {
            if (var2.canBeRidden())
            {
                if (var2.riddenByEntity != null && var3.ridingEntity != var2)
                {
                    var1.setCanceled(true);
                    return;
                }

                if (var3.ridingEntity != null && var3.ridingEntity != var2)
                {
                    var1.setCanceled(true);
                    return;
                }

                if (var3.ridingEntity != var2 && var3.isOnLadder())
                {
                    var1.setCanceled(true);
                    return;
                }
            }

            if (!var3.canEntityBeSeen(var2))
            {
                var1.setCanceled(true);
            }
        }
    }
}
