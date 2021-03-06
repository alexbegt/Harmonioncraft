package net.Harmonion.client.renderer.entity;

import net.Harmonion.entity.passive.EntityHarmonionWolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHarmonionWolf extends RenderLiving
{
    public RenderHarmonionWolf(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }

    protected float getTailRotation(EntityHarmonionWolf par1EntityWolf, float par2)
    {
        return par1EntityWolf.getTailRotation();
    }

    protected int func_82447_a(EntityHarmonionWolf par1EntityWolf, int par2, float par3)
    {
        float var4;

        if (par2 == 0 && par1EntityWolf.getWolfShaking())
        {
            var4 = par1EntityWolf.getBrightness(par3) * par1EntityWolf.getShadingWhileShaking(par3);
            this.loadTexture(par1EntityWolf.getTexture());
            GL11.glColor3f(var4, var4, var4);
            return 1;
        }
        else if (par2 == 1 && par1EntityWolf.isTamed())
        {
            this.loadTexture("/mob/wolf_collar.png");
            var4 = 1.0F;
            int var5 = par1EntityWolf.getCollarColor();
            GL11.glColor3f(var4 * EntitySheep.fleeceColorTable[var5][0], var4 * EntitySheep.fleeceColorTable[var5][1], var4 * EntitySheep.fleeceColorTable[var5][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.func_82447_a((EntityHarmonionWolf)par1EntityLiving, par2, par3);
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
    {
        return this.getTailRotation((EntityHarmonionWolf)par1EntityLiving, par2);
    }
}
