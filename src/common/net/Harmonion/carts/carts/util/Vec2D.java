package net.Harmonion.carts.carts.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Vec2D extends Double
{
    public static final float DEGREES_45 = ((float)Math.PI / 4F);
    public static final float DEGREES_90 = ((float)Math.PI / 2F);
    public static final float DEGREES_135 = 2.3561945F;
    public static final float DEGREES_180 = (float)Math.PI;
    public static final float DEGREES_270 = ((float)Math.PI * 3F / 2F);

    public Vec2D() {}

    public Vec2D(Point2D var1)
    {
        super(var1.getX(), var1.getY());
    }

    public Vec2D(double var1, double var3)
    {
        super(var1, var3);
    }

    public Vec2D clone()
    {
        return new Vec2D(this.x, this.y);
    }

    public static Vec2D fromPolar(double var0, float var2)
    {
        Vec2D var3 = new Vec2D();
        var3.setFromPolar(var0, var2);
        return var3;
    }

    public static Vec2D add(Point2D var0, Point2D var1)
    {
        return new Vec2D(var0.getX() + var1.getX(), var0.getY() + var1.getY());
    }

    public static Vec2D subtract(Point2D var0, Point2D var1)
    {
        return new Vec2D(var0.getX() - var1.getX(), var0.getY() - var1.getY());
    }

    public void setLocation(int var1, int var2)
    {
        this.x = (double)var1;
        this.y = (double)var2;
    }

    public void setFromPolar(double var1, float var3)
    {
        float var4 = (float)Math.cos(var1) * var3;
        float var5 = (float)Math.sin(var1) * var3;
        this.setLocation((double)var4, (double)var5);
    }

    public void zero()
    {
        this.x = this.y = 0.0D;
    }

    public void normalize()
    {
        double var1 = this.magnitude();

        if (var1 != 0.0D)
        {
            this.setLocation(this.x / var1, this.y / var1);
        }
    }

    public Vec2D unitVector()
    {
        Vec2D var1 = this.clone();
        var1.normalize();
        return var1;
    }

    public double magnitude()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void setMagnitude(float var1)
    {
        this.setFromPolar((double)this.angle(), var1);
    }

    public double magnitudeSq()
    {
        return this.x * this.x + this.y * this.y;
    }

    public void negate()
    {
        this.x = -this.x;
        this.y = -this.y;
    }

    public float angle()
    {
        return (float)Math.atan2(this.y, this.x);
    }

    public void rotate(double var1)
    {
        double var3 = this.x * (double)((float)Math.cos(var1)) - this.y * (double)((float)Math.sin(var1));
        double var5 = this.x * (double)((float)Math.sin(var1)) + this.y * (double)((float)Math.cos(var1));
        this.setLocation(var3, var5);
    }

    public void rotate90()
    {
        double var1 = this.x;
        this.x = -this.y;
        this.y = var1;
    }

    public void rotate270()
    {
        double var1 = this.x;
        this.x = this.y;
        this.y = -var1;
    }

    public void subtract(Point2D var1)
    {
        this.x -= var1.getX();
        this.y -= var1.getY();
    }

    public void subtract(int var1, int var2)
    {
        this.x -= (double)var1;
        this.y -= (double)var2;
    }

    public void subtract(double var1, double var3)
    {
        this.x -= (double)((int)var1);
        this.y -= (double)((int)var3);
    }

    public void add(Point2D var1)
    {
        this.x += var1.getX();
        this.y += var1.getY();
    }

    public void add(int var1, int var2)
    {
        this.x += (double)var1;
        this.y += (double)var2;
    }

    public void add(double var1, double var3)
    {
        this.x += (double)((int)var1);
        this.y += (double)((int)var3);
    }

    public void transform(AffineTransform var1)
    {
        this.setLocation(var1.transform(this, (Point2D)null));
    }

    public Vec2D makeTransform(AffineTransform var1)
    {
        return new Vec2D(var1.transform(this, (Point2D)null));
    }

    public double dotProduct(Point2D var1)
    {
        return this.x * var1.getX() + this.y * var1.getY();
    }

    public double angleBetween(Vec2D var1)
    {
        double var2 = this.dotProduct(var1);
        double var4 = this.magnitude() * var1.magnitude();

        if (var4 == 0.0D)
        {
            return 0.0D;
        }
        else
        {
            var2 /= var4;

            if (var2 > 1.0D)
            {
                var2 = 1.0D;
            }
            else if (var2 < -1.0D)
            {
                var2 = -1.0D;
            }

            return Math.acos(var2);
        }
    }

    public double angleTo(Point2D var1)
    {
        return Math.atan2(var1.getY() - this.y, var1.getX() - this.x);
    }

    public double angleFrom(Point2D var1)
    {
        return Math.atan2(this.y - var1.getY(), this.x - var1.getX());
    }

    public void scale(float var1)
    {
        this.x *= (double)var1;
        this.y *= (double)var1;
    }

    public void addScale(float var1, Point2D var2)
    {
        this.setLocation(this.x + var2.getX() * (double)var1, this.y + var2.getY() * (double)var1);
    }
}
