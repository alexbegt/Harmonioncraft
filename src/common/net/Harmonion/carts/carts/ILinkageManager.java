package net.Harmonion.carts.carts;

import net.minecraft.src.EntityMinecart;

public interface ILinkageManager
{
    float LINKAGE_DISTANCE = 1.25F;
    float OPTIMAL_DISTANCE = 0.78F;

    boolean createLink(EntityMinecart var1, EntityMinecart var2);

    EntityMinecart getLinkedCartA(EntityMinecart var1);

    EntityMinecart getLinkedCartB(EntityMinecart var1);

    boolean areLinked(EntityMinecart var1, EntityMinecart var2);

    void breakLink(EntityMinecart var1, EntityMinecart var2);

    void breakLinks(EntityMinecart var1);

    void breakLinkA(EntityMinecart var1);

    void breakLinkB(EntityMinecart var1);

    int countCartsInTrain(EntityMinecart var1);
}
