package net.Harmonioncraft;

import java.util.EnumSet;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import net.Harmonioncraft.block.ModBlocks;
import net.Harmonioncraft.mods.Harmonioncraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Potion;
import net.minecraft.src.ServerCommandManager;
import net.minecraft.src.World;
import net.minecraft.src.WorldServer;

public class ServerTickHandler implements ITickHandler
{
    public boolean needPreGen = true;
    public World lastWorld = null;

    public void tickStart(EnumSet var1, Object ... var2) {}

    public void tickEnd(EnumSet var1, Object ... var2)
    {
        if (var1.equals(EnumSet.of(TickType.SERVER)))
        {
            this.onTickInGame();
        }
    }

    public EnumSet ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    public String getLabel()
    {
        return null;
    }

    public void onTickInGame()
    {
        if (FMLCommonHandler.instance() != null && FMLCommonHandler.instance().getMinecraftServerInstance() != null)
        {
            WorldServer var1 = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);

            if (this.lastWorld != var1 && var1 != null)
            {
                this.lastWorld = var1;
                //((ServerCommandManager)FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager()).registerCommand(new CommandTeleportTropics());
            }

            int var2;
            Entity var3;

            if (var1 != null && var1 instanceof WorldServer)
            {
                for (var2 = 0; var2 < var1.playerEntities.size(); ++var2)
                {
                    var3 = (Entity)var1.playerEntities.get(var2);

                    if (var3 instanceof EntityPlayerMP)
                    {
                        if (var1.getBlockId((int)var3.posX, (int)var3.posY, (int)var3.posZ) == ModBlocks.HarmonionPortal.blockID)
                        {
                            var3.setAir(300);
                            ++((EntityPlayerMP)var3).timeUntilPortal;

                            if (((EntityPlayerMP)var3).timeUntilPortal > 250.0F)
                            {
                                ((EntityPlayerMP)var3).timeUntilPortal = (int) 0.0F;
                                Harmonioncraft.teleportPlayerToHarmonion1((EntityPlayerMP)var3);
                            }
                        }
                        else if (((EntityPlayerMP)var3).timeUntilPortal > 25.0F)
                        {
                            ((EntityPlayerMP)var3).timeUntilPortal = (int) 0.0F;
                        }
                    }
                }
            }

            var1 = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(-128);

            if (var1 != null && var1 instanceof WorldServer)
            {
                if (this.needPreGen)
                {
                    Harmonioncraft.instance.initialWorldChunkLoad((WorldServer)var1);
                    this.needPreGen = false;
                }

                for (var2 = 0; var2 < var1.playerEntities.size(); ++var2)
                {
                    var3 = (Entity)var1.playerEntities.get(var2);

                    if (var3 instanceof EntityPlayerMP)
                    {
                        var1.getBlockId((int)var3.posX, (int)var3.posY, (int)var3.posZ);

                        if (var1.getBlockId((int)var3.posX, (int)var3.posY, (int)var3.posZ) == ModBlocks.HarmonionPortal.blockID)
                        {
                            var3.setAir(300);
                            ++((EntityPlayerMP)var3).timeUntilPortal;

                            if (((EntityPlayerMP)var3).timeUntilPortal > 250.0F)
                            {
                                ((EntityPlayerMP)var3).timeUntilPortal = (int) 0.0F;
                                Harmonioncraft.teleportPlayerToHarmonion1((EntityPlayerMP)var3);
                            }
                        }
                        else if (((EntityPlayerMP)var3).timeUntilPortal > 25.0F)
                        {
                            ((EntityPlayerMP)var3).timeUntilPortal = (int) 0.0F;
                        }
                    }
                }
            }
        }
    }
}
