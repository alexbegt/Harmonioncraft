package net.Harmonion.carts.carts;

import net.minecraft.src.EntityMinecart;

public interface ILinkableCart
{
    boolean isLinkable();

    boolean canLinkWithCart(EntityMinecart var1);

    boolean hasTwoLinks();

    float getLinkageDistance(EntityMinecart var1);

    float getOptimalDistance(EntityMinecart var1);

    boolean canBeAdjusted(EntityMinecart var1);

    void onLinkCreated(EntityMinecart var1);

    void onLinkBroken(EntityMinecart var1);
}
