package net.Harmonion.client.textureFX;

import net.minecraft.client.renderer.RenderEngine;
import net.minecraftforge.client.ForgeHooksClient;

public class TextureLiquidFX extends HarmonionTextureFX
{
    private final int redMin;
    private final int redMax;
    private final int greenMin;
    private final int greenMax;
    private final int blueMin;
    private final int blueMax;
    private final String texture;
    protected float[] red;
    protected float[] green;
    protected float[] blue;
    protected float[] alpha;

    public TextureLiquidFX(int var1, int var2, int var3, int var4, int var5, int var6, int var7, String var8)
    {
        super(var7, var8);
        this.redMin = var1;
        this.redMax = var2;
        this.greenMin = var3;
        this.greenMax = var4;
        this.blueMin = var5;
        this.blueMax = var6;
        this.texture = var8;
        this.setup();
    }

    public void setup()
    {
        super.setup();
        this.red = new float[this.tileSizeSquare];
        this.green = new float[this.tileSizeSquare];
        this.blue = new float[this.tileSizeSquare];
        this.alpha = new float[this.tileSizeSquare];
    }

    public void bindImage(RenderEngine var1)
    {
        ForgeHooksClient.bindTexture(this.texture, 0);
    }

    public void onTick()
    {
        int var1;
        int var2;
        float var3;
        int var5;
        int var6;

        for (var1 = 0; var1 < this.tileSizeBase; ++var1)
        {
            for (var2 = 0; var2 < this.tileSizeBase; ++var2)
            {
                var3 = 0.0F;

                for (int var4 = var1 - 1; var4 <= var1 + 1; ++var4)
                {
                    var5 = var4 & this.tileSizeMask;
                    var6 = var2 & this.tileSizeMask;
                    var3 += this.red[var5 + var6 * this.tileSizeBase];
                }

                this.green[var1 + var2 * this.tileSizeBase] = var3 / 3.3F + this.blue[var1 + var2 * this.tileSizeBase] * 0.8F;
            }
        }

        for (var1 = 0; var1 < this.tileSizeBase; ++var1)
        {
            for (var2 = 0; var2 < this.tileSizeBase; ++var2)
            {
                this.blue[var1 + var2 * this.tileSizeBase] += this.alpha[var1 + var2 * this.tileSizeBase] * 0.05F;

                if (this.blue[var1 + var2 * this.tileSizeBase] < 0.0F)
                {
                    this.blue[var1 + var2 * this.tileSizeBase] = 0.0F;
                }

                this.alpha[var1 + var2 * this.tileSizeBase] -= 0.1F;

                if (Math.random() < 0.05D)
                {
                    this.alpha[var1 + var2 * this.tileSizeBase] = 0.5F;
                }
            }
        }

        float[] var11 = this.green;
        this.green = this.red;
        this.red = var11;

        for (var2 = 0; var2 < this.tileSizeSquare; ++var2)
        {
            var3 = this.red[var2];

            if (var3 > 1.0F)
            {
                var3 = 1.0F;
            }

            if (var3 < 0.0F)
            {
                var3 = 0.0F;
            }

            float var12 = var3 * var3;
            var5 = (int)((float)this.redMin + var12 * (float)(this.redMax - this.redMin));
            var6 = (int)((float)this.greenMin + var12 * (float)(this.greenMax - this.greenMin));
            int var7 = (int)((float)this.blueMin + var12 * (float)(this.blueMax - this.blueMin));

            if (this.anaglyphEnabled)
            {
                int var8 = (var5 * 30 + var6 * 59 + var7 * 11) / 100;
                int var9 = (var5 * 30 + var6 * 70) / 100;
                int var10 = (var5 * 30 + var7 * 70) / 100;
                var5 = var8;
                var6 = var9;
                var7 = var10;
            }

            this.imageData[var2 * 4 + 0] = (byte)var5;
            this.imageData[var2 * 4 + 1] = (byte)var6;
            this.imageData[var2 * 4 + 2] = (byte)var7;
            this.imageData[var2 * 4 + 3] = -1;
        }
    }
}
