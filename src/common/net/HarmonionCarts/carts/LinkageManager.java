package net.HarmonionCarts.carts;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.World;

public class LinkageManager implements ILinkageManager
{
    private final Map carts = new HashMap();
    public static final String LINK_A_HIGH = "rcLinkAHigh";
    public static final String LINK_A_LOW = "rcLinkALow";
    public static final String LINK_B_HIGH = "rcLinkBHigh";
    public static final String LINK_B_LOW = "rcLinkBLow";

    public static LinkageManager getInstance(World var0)
    {
        return (LinkageManager)CartTools.serverLinkageManager;
    }

    public static void reset()
    {
        CartTools.serverLinkageManager = new LinkageManager();
    }

    public void removeLinkageId(EntityMinecart var1)
    {
        this.carts.remove(this.getLinkageId(var1));
    }

    public UUID getLinkageId(EntityMinecart var1)
    {
        UUID var2 = var1.getPersistentID();
        this.carts.put(var2, var1);
        return var2;
    }

    public EntityMinecart getCartFromLinkageId(UUID var1)
    {
        return (EntityMinecart)this.carts.get(var1);
    }

    private float getLinkageDistanceSq(EntityMinecart var1, EntityMinecart var2)
    {
        float var3 = 0.0F;

        if (var1 instanceof ILinkableCart)
        {
            var3 += ((ILinkableCart)var1).getLinkageDistance(var2);
        }
        else
        {
            ++var3;
        }

        if (var2 instanceof ILinkableCart)
        {
            var3 += ((ILinkableCart)var2).getLinkageDistance(var1);
        }
        else
        {
            ++var3;
        }

        return var3 * var3;
    }

    private boolean canLinkCarts(EntityMinecart var1, EntityMinecart var2)
    {
        if (var1 != null && var2 != null)
        {
            if (var1 == var2)
            {
                return false;
            }
            else
            {
                ILinkableCart var3;

                if (var1 instanceof ILinkableCart)
                {
                    var3 = (ILinkableCart)var1;

                    if (!var3.isLinkable() || !var3.canLinkWithCart(var2))
                    {
                        return false;
                    }
                }

                if (var2 instanceof ILinkableCart)
                {
                    var3 = (ILinkableCart)var2;

                    if (!var3.isLinkable() || !var3.canLinkWithCart(var1))
                    {
                        return false;
                    }
                }

                return this.areLinked(var1, var2) ? false : (var1.getDistanceSqToEntity(var2) > (double)this.getLinkageDistanceSq(var1, var2) ? false : this.hasFreeLink(var1) && this.hasFreeLink(var2));
            }
        }
        else
        {
            return false;
        }
    }

    public boolean createLink(EntityMinecart var1, EntityMinecart var2)
    {
        if (this.canLinkCarts(var1, var2))
        {
            this.setLink(var1, var2);
            this.setLink(var2, var1);

            if (var1 instanceof ILinkableCart)
            {
                ((ILinkableCart)var1).onLinkCreated(var2);
            }

            if (var2 instanceof ILinkableCart)
            {
                ((ILinkableCart)var2).onLinkCreated(var1);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean hasFreeLink(EntityMinecart var1)
    {
        return this.getLinkedCartA(var1) == null || this.hasLinkB(var1) && this.getLinkedCartB(var1) == null;
    }

    private boolean hasLinkB(EntityMinecart var1)
    {
        return var1 instanceof ILinkableCart ? ((ILinkableCart)var1).hasTwoLinks() : true;
    }

    private void setLink(EntityMinecart var1, EntityMinecart var2)
    {
        if (this.getLinkedCartA(var1) == null)
        {
            this.setLinkA(var1, var2);
        }
        else if (this.hasLinkB(var1) && this.getLinkedCartB(var1) == null)
        {
            this.setLinkB(var1, var2);
        }
    }

    public UUID getLinkA(EntityMinecart var1)
    {
        long var2 = var1.getEntityData().getLong("rcLinkAHigh");
        long var4 = var1.getEntityData().getLong("rcLinkALow");
        return new UUID(var2, var4);
    }

    private void setLinkA(EntityMinecart var1, EntityMinecart var2)
    {
        UUID var3 = this.getLinkageId(var2);
        var1.getEntityData().setLong("rcLinkAHigh", var3.getMostSignificantBits());
        var1.getEntityData().setLong("rcLinkALow", var3.getLeastSignificantBits());
    }

    public EntityMinecart getLinkedCartA(EntityMinecart var1)
    {
        return this.getCartFromLinkageId(this.getLinkA(var1));
    }

    public UUID getLinkB(EntityMinecart var1)
    {
        long var2 = var1.getEntityData().getLong("rcLinkBHigh");
        long var4 = var1.getEntityData().getLong("rcLinkBLow");
        return new UUID(var2, var4);
    }

    private void setLinkB(EntityMinecart var1, EntityMinecart var2)
    {
        if (this.hasLinkB(var1))
        {
            UUID var3 = this.getLinkageId(var2);
            var1.getEntityData().setLong("rcLinkBHigh", var3.getMostSignificantBits());
            var1.getEntityData().setLong("rcLinkBLow", var3.getLeastSignificantBits());
        }
    }

    public EntityMinecart getLinkedCartB(EntityMinecart var1)
    {
        return this.getCartFromLinkageId(this.getLinkB(var1));
    }

    public boolean areLinked(EntityMinecart var1, EntityMinecart var2)
    {
        if (var1 != null && var2 != null)
        {
            if (var1 == var2)
            {
                return false;
            }
            else
            {
                boolean var3 = false;
                UUID var4 = this.getLinkageId(var2);

                if (var4.equals(this.getLinkA(var1)) || var4.equals(this.getLinkB(var1)))
                {
                    var3 = true;
                }

                boolean var5 = false;
                var4 = this.getLinkageId(var1);

                if (var4.equals(this.getLinkA(var2)) || var4.equals(this.getLinkB(var2)))
                {
                    var5 = true;
                }

                return var3 && var5;
            }
        }
        else
        {
            return false;
        }
    }

    public void breakLink(EntityMinecart var1, EntityMinecart var2)
    {
        UUID var3 = this.getLinkageId(var2);

        if (var3.equals(this.getLinkA(var1)))
        {
            this.breakLinkA(var1);
        }

        if (var3.equals(this.getLinkB(var1)))
        {
            this.breakLinkB(var1);
        }
    }

    public void breakLinks(EntityMinecart var1)
    {
        this.breakLinkA(var1);
        this.breakLinkB(var1);
    }

    public void breakLinkA(EntityMinecart var1)
    {
        UUID var2 = this.getLinkA(var1);
        var1.getEntityData().setLong("rcLinkAHigh", 0L);
        var1.getEntityData().setLong("rcLinkALow", 0L);
        EntityMinecart var3 = this.getCartFromLinkageId(var2);

        if (var3 != null)
        {
            this.breakLink(var3, var1);
        }

        if (var1 instanceof ILinkableCart)
        {
            ((ILinkableCart)var1).onLinkBroken(var3);
        }
    }

    public void breakLinkB(EntityMinecart var1)
    {
        UUID var2 = this.getLinkB(var1);
        var1.getEntityData().setLong("rcLinkBHigh", 0L);
        var1.getEntityData().setLong("rcLinkBLow", 0L);
        EntityMinecart var3 = this.getCartFromLinkageId(var2);

        if (var3 != null)
        {
            this.breakLink(var3, var1);
        }

        if (var1 instanceof ILinkableCart)
        {
            ((ILinkableCart)var1).onLinkBroken(var3);
        }
    }

    public int countCartsInTrain(EntityMinecart var1)
    {
        EntityMinecart var2 = this.getLinkedCartA(var1);
        EntityMinecart var3 = this.getLinkedCartB(var1);
        byte var4 = 0;
        int var7 = var4 + this.countLinksRecursive(var2, var1);
        byte var5 = 0;
        int var6 = var5 + this.countLinksRecursive(var3, var1);
        return var7 + var6 + 1;
    }

    private int countLinksRecursive(EntityMinecart var1, EntityMinecart var2)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            EntityMinecart var3 = this.getLinkedCartA(var1);
            EntityMinecart var4 = this.getLinkedCartB(var1);
            int var5 = 0;

            if (var3 != var2)
            {
                var5 += this.countLinksRecursive(var3, var1);
            }

            int var6 = 0;

            if (var4 != var2)
            {
                var6 += this.countLinksRecursive(var4, var1);
            }

            return var5 + var6 + 1;
        }
    }
}
