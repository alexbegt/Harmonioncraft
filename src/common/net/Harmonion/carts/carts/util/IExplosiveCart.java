package net.Harmonion.carts.carts.util;

public interface IExplosiveCart
{
    void setPrimed(boolean var1);

    boolean isPrimed();

    int getFuse();

    void setFuse(int var1);

    float getBlastRadius();

    void setBlastRadius(float var1);

    void explode();
}
