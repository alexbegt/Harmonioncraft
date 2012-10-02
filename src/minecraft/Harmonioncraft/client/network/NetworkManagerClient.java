package Harmonioncraft.client.network;

import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.Side;
import Harmonioncraft.common.network.NetworkManager;
import net.minecraft.src.World;
import Harmonioncraft.common.*;

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
