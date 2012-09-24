package Harmonioncraft.client.dimension;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Vec3;
import net.minecraft.src.WorldClient;
import net.minecraft.src.WorldSettings;

public class HMCWorldClient extends WorldClient
{
    public HMCWorldClient(WorldClient var1)
    {
        super(FMLClientHandler.instance().getClient().getSendQueue(), new WorldSettings(var1.getWorldInfo()), 8, var1.difficultySetting, var1.theProfiler);
    }

    public static void sendToTwilightForestMulti()
    {
        Minecraft var0 = FMLClientHandler.instance().getClient();
        HMCWorldClient var1 = new HMCWorldClient(var0.theWorld);
        var1.isRemote = true;
        var0.loadWorld(var1, "Entering the HarmonionCraft");
        var0.thePlayer.worldObj = var0.theWorld;
        var0.thePlayer.dimension = 8;
        setWorldClient(var0, (HMCWorldClient)var0.theWorld);
        //var0.thePlayer.triggerAchievement(TFAchievementPage.twilightPortal);
        //var0.thePlayer.triggerAchievement(TFAchievementPage.twilightArrival);
    }

    private static void setWorldClient(Minecraft var0, HMCWorldClient var1)
    {
        NetClientHandler var2 = var0.getSendQueue();

        for (int var3 = 0; var3 < NetClientHandler.class.getDeclaredFields().length; ++var3)
        {
            try
            {
                if (ModLoader.getPrivateValue(NetClientHandler.class, var2, var3) instanceof WorldClient)
                {
                    ModLoader.setPrivateValue(NetClientHandler.class, var2, var3, var1);
                }
            }
            catch (IllegalArgumentException var5)
            {
                var5.printStackTrace();
            }
            catch (SecurityException var6)
            {
                var6.printStackTrace();
            }
        }
    }

    /**
     * Sets a new spawn location by finding an uncovered block at a random (x,z) location in the chunk.
     */
    public void setSpawnLocation() {}

    /**
     * Returns the amount of skylight subtracted for the current time
     */
    public int calculateSkylightSubtracted(float var1)
    {
        float var2 = 0.5F;
        var2 = (float)((double)var2 * (1.0D - (double)(this.getRainStrength(var1) * 5.0F) / 16.0D));
        var2 = (float)((double)var2 * (1.0D - (double)(this.getWeightedThunderStrength(var1) * 5.0F) / 16.0D));
        var2 = 1.0F - var2;
        return (int)(var2 * 11.0F);
    }

    public float func_72971_b(float var1)
    {
        float var2 = 0.2F;
        var2 = (float)((double)var2 * (1.0D - (double)(this.getRainStrength(var1) * 5.0F) / 16.0D));
        var2 = (float)((double)var2 * (1.0D - (double)(this.getWeightedThunderStrength(var1) * 5.0F) / 16.0D));
        return var2 * 0.8F + 0.2F;
    }

    /**
     * Calculates the color for the skybox
     */
    public Vec3 getSkyColor(Entity var1, float var2)
    {
        float var3 = 0.25F;
        float var4 = MathHelper.cos(var3 * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (var4 < 0.0F)
        {
            var4 = 0.0F;
        }

        if (var4 > 1.0F)
        {
            var4 = 1.0F;
        }

        int var5 = MathHelper.floor_double(var1.posX);
        int var6 = MathHelper.floor_double(var1.posZ);
        int var7 = this.getBiomeGenForCoords(var5, var6).getSkyColorByTemp(0.5F);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;
        var8 *= var4;
        var9 *= var4;
        var10 *= var4;
        float var11 = this.getRainStrength(var2);
        float var12;
        float var13;

        if (var11 > 0.0F)
        {
            var12 = (var8 * 0.3F + var9 * 0.59F + var10 * 0.11F) * 0.6F;
            var13 = 1.0F - var11 * 0.75F;
            var8 = var8 * var13 + var12 * (1.0F - var13);
            var9 = var9 * var13 + var12 * (1.0F - var13);
            var10 = var10 * var13 + var12 * (1.0F - var13);
        }

        var12 = this.getWeightedThunderStrength(var2);

        if (var12 > 0.0F)
        {
            var13 = (var8 * 0.3F + var9 * 0.59F + var10 * 0.11F) * 0.2F;
            float var14 = 1.0F - var12 * 0.75F;
            var8 = var8 * var14 + var13 * (1.0F - var14);
            var9 = var9 * var14 + var13 * (1.0F - var14);
            var10 = var10 * var14 + var13 * (1.0F - var14);
        }

        if (this.lightningFlash > 0)
        {
            var13 = (float)this.lightningFlash - var2;

            if (var13 > 1.0F)
            {
                var13 = 1.0F;
            }

            var13 *= 0.45F;
            var8 = var8 * (1.0F - var13) + 0.8F * var13;
            var9 = var9 * (1.0F - var13) + 0.8F * var13;
            var10 = var10 * (1.0F - var13) + 1.0F * var13;
        }

        return Vec3.getVec3Pool().getVecFromPool((double)var8, (double)var9, (double)var10);
    }

    /**
     * How bright are stars in the sky
     */
    public float getStarBrightness(float var1)
    {
        return 1.5F;
    }

    /**
     * Returns horizon height for use in rendering the sky.
     */
    public double getHorizon()
    {
        return 32.0D;
    }
}
