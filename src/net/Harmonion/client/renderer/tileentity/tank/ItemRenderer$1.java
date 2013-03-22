package net.Harmonion.client.renderer.tileentity.tank;

import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

class ItemRenderer$1
{
    static final int[] $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper;

    static final int[] $SwitchMap$net$minecraftforge$client$ItemRenderType = new int[ItemRenderType.values().length];

    static
    {
        try
        {
            $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.EQUIPPED.ordinal()] = 1;
        }
        catch (NoSuchFieldError var4)
        {
            ;
        }

        try
        {
            $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.INVENTORY.ordinal()] = 2;
        }
        catch (NoSuchFieldError var3)
        {
            ;
        }

        try
        {
            $SwitchMap$net$minecraftforge$client$ItemRenderType[ItemRenderType.ENTITY.ordinal()] = 3;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper = new int[ItemRendererHelper.values().length];

        try
        {
            $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.BLOCK_3D.ordinal()] = 1;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
