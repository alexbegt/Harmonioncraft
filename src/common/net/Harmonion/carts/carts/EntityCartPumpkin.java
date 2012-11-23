package net.Harmonion.carts.carts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.Harmonion.carts.carts.util.Game;
import net.Harmonion.carts.carts.util.InventoryTools;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityAIArrowAttack;
import net.minecraft.src.EntityAIAttackOnCollide;
import net.minecraft.src.EntityList;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.PotionHelper;
import net.minecraft.src.World;

public class EntityCartPumpkin extends EntityCartTNT
{
    private static final byte SPAWN_DIST = 2;
    private static final Map mobWeights = new HashMap();
    private static final Map mobNumber = new HashMap();
    private static final List mobs = new ArrayList();
    private static final List potions = new ArrayList();

    public EntityCartPumpkin(World var1)
    {
        super(var1);
    }

    public EntityCartPumpkin(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4 + (double)this.yOffset, var6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = var2;
        this.prevPosY = var4;
        this.prevPosZ = var6;
        this.setBlastRadius(1.5F);
    }

    public List getItemsDropped()
    {
        ArrayList var1 = new ArrayList();
        
        var1.add(this.getCartItem());

        return var1;
    }

    protected byte getMinBlastRadius()
    {
        return (byte)1;
    }

    protected byte getMaxBlastRadius()
    {
        return (byte)8;
    }

    public void explode()
    {
        if (Game.isHost(this.getWorld()))
        {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.getBlastRadius(), true);
            this.setDead();
            this.spawnMob();
            this.spawnPotion();
        }
    }

    private String getMobToSpawn()
    {
        String var2;
        int var3;

        do
        {
            int var1 = this.rand.nextInt(mobs.size());
            var2 = (String)mobs.get(var1);
            var3 = this.rand.nextInt(100);
        }
        while (((Integer)mobWeights.get(var2)).intValue() < var3);

        return var2;
    }

    private void spawnMob()
    {
        String var1 = this.getMobToSpawn();
        int var2 = ((Integer)mobNumber.get(var1)).intValue();

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Entity var4 = EntityList.createEntityByName(var1, this.worldObj);

            if (var4 == null)
            {
                return;
            }

            double var5 = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 2.0D;
            double var7 = this.posY + 1.0D + (double)this.rand.nextInt(3) - 1.0D;
            double var9 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 2.0D;
            EntityLiving var11 = var4 instanceof EntityLiving ? (EntityLiving)var4 : null;
            var4.setLocationAndAngles(var5, var7, var9, this.rand.nextFloat() * 360.0F, 0.0F);

            if (this.worldObj.checkIfAABBIsClear(var4.boundingBox) && this.worldObj.getCollidingBoundingBoxes(var4, var4.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(var4.boundingBox))
            {
                if (var4 instanceof EntitySkeleton)
                {
                    EntitySkeleton var12 = (EntitySkeleton)var4;

                    if (this.rand.nextInt(4) == 0)
                    {
                        var12.tasks.addTask(4, new EntityAIAttackOnCollide(var12, EntityPlayer.class, 0.25F, false));
                        var12.setSkeletonType(1);
                        var12.setCurrentItemOrArmor(0, new ItemStack(Item.swordStone));
                    }
                    else
                    {
                        var12.tasks.addTask(4, new EntityAIArrowAttack(var12, 0.25F, 60, 10.0F));
                        var12.setCurrentItemOrArmor(0, new ItemStack(Item.bow));
                    }

                    var4.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.25F ? Block.pumpkinLantern : Block.pumpkin));
                }
                else
                {
                    var11.initCreature();
                }

                this.worldObj.spawnEntityInWorld(var4);
                this.worldObj.playAuxSFX(2004, (int)var5, (int)var7, (int)var9, 0);

                if (var11 != null)
                {
                    var11.spawnExplosionParticle();
                }
            }
        }
    }

    private void spawnPotion()
    {
        int var1 = ((Integer)potions.get(this.rand.nextInt(potions.size()))).intValue();
        ItemStack var2 = new ItemStack(Item.potion, 1, var1);
        double var3 = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 2.0D;
        double var5 = this.posY + 1.0D + (double)this.rand.nextInt(3) - 1.0D;
        double var7 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 2.0D;
        InventoryTools.dropItem(var2, this.worldObj, var3, var5, var7);
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "Pumpkin Cart";
    }

    public Block getBlock()
    {
        return Block.pumpkin;
    }

    public int getBlockMetadata()
    {
        return 0;
    }

    static
    {
        mobs.add("Skeleton");
        mobs.add("Bat");
        mobs.add("Witch");
        mobs.add("WitherBoss");
        mobWeights.put("Skeleton", Integer.valueOf(50));
        mobWeights.put("Bat", Integer.valueOf(75));
        mobWeights.put("Witch", Integer.valueOf(25));
        mobWeights.put("WitherBoss", Integer.valueOf(5));
        mobNumber.put("Skeleton", Integer.valueOf(1));
        mobNumber.put("Bat", Integer.valueOf(3));
        mobNumber.put("Witch", Integer.valueOf(1));
        mobNumber.put("WitherBoss", Integer.valueOf(1));

        for (int var0 = 0; var0 <= 32767; ++var0)
        {
            List var1 = PotionHelper.getPotionEffects(var0, false);

            if (var1 != null && !var1.isEmpty())
            {
                potions.add(Integer.valueOf(var0));
            }
        }
    }
}
