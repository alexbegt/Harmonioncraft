package net.Harmonioncraft.core.handlers;

import net.Harmonioncraft.block.ModBlocks;
import net.minecraft.src.Achievement;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.AchievementPage;

public class AchievementPageHandler extends AchievementPage{
	
	public static Achievement twilightPortal = (new Achievement(7001, "twilightPortal", -2, 1, ModBlocks.HarmonionPortal, (Achievement)null)).setSpecial().registerAchievement();
	public static Achievement twilightArrival = (new Achievement(7002, "twilightArrival", 0, 0, new ItemStack(ModBlocks.HarmonionPlank, 1, 9), twilightPortal)).registerAchievement();
	
	public AchievementPageHandler()
    {
		super("Twilight Forest", new Achievement[] {twilightPortal, twilightArrival});
    }
	
}
