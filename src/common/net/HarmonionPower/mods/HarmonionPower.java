package net.HarmonionPower.mods;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.NetworkMod;
import net.HarmonionPower.core.CommonProxy;
import net.HarmonionPower.lib.*;


@Mod(
		modid = Reference.MOD_ID,
		name = Reference.MOD_NAME,
		version = Reference.VERSION,
		useMetadata = true,
		dependencies = "required-after:Forge@[6.0.1.332,);after:HMC;after:HMCC"
)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false//,
        //packetHandler = PacketHandler.class
)
public class HarmonionPower {
	
	@Instance(Reference.MOD_ID)
	public static HarmonionPower instance;
	
	//@SidedProxy(
	//		clientSide = Reference.CLIENT_PROXY_CLASS,
	//		serverSide = Reference.SERVER_PROXY_CLASS
	//)
    //public static CommonProxy proxy;
	
}
