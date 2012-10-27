package net.Harmonioncraft.client.network;

import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.Side;
import net.Harmonioncraft.network.NetworkManager;
import net.Harmonioncraft.mods.Harmonioncraft;
import net.minecraft.src.World;

@SideOnly(Side.CLIENT)
public class NetworkManagerClient extends NetworkManager {
	
	public void announceBlockUpdate(World var1, int var2, int var3, int var4)
    {
        if (Harmonioncraft.proxy.isSimulating())
        {
            super.announceBlockUpdate(var1, var2, var3, var4);
        }
    }
	
}
