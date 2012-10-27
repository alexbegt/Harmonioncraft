package net.Harmonioncraft;

import net.Harmonioncraft.block.BlockHarmonionSapling;
import net.Harmonioncraft.block.ModBlocks;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class HmcBonemeal {

    @ForgeSubscribe
    public void onUseBonemeal(BonemealEvent event)
    {
            if (event.ID == ModBlocks.HarmonionSapling.blockID)
            {
                    if (!event.world.isRemote)
                    {
                            ((BlockHarmonionSapling)ModBlocks.HarmonionSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
                    }
            }

    }


}