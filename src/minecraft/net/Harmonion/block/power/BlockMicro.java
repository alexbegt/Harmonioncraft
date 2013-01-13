package net.Harmonion.block.power;

import net.Harmonion.block.ModBlocks;
import net.Harmonion.item.power.ItemMicro;
import net.Harmonion.power.CoreLib;
import net.Harmonion.power.IMicroPlacement;
import net.Harmonion.power.HarmonionLib;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

public class BlockMicro extends BlockCoverable
{
    public BlockMicro(int var1)
    {
        super(var1, CoreLib.materialRedpower);
        this.setHardness(0.1F);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftW);
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return !HarmonionLib.isSearching();
    }

    public boolean canConnectRedstone(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (HarmonionLib.isSearching())
        {
            return false;
        }
        else
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            return var6 == 1 || var6 == 2;
        }
    }

    public void registerPlacement(int var1, IMicroPlacement var2)
    {
        ((ItemMicro)Item.itemsList[this.blockID]).registerPlacement(var1, var2);
    }
}
