package net.Harmonion.village;

import java.util.List;
import java.util.Random;
import net.Harmonion.item.ModItems;
import net.Harmonion.world.gen.structure.ComponentPowerRoom;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VillageManager1 implements IVillageCreationHandler, IVillageTradeHandler
{
    public void manipulateTradesForVillager(EntityVillager var1, MerchantRecipeList var2, Random var3)
    {
        var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 10, 0), new ItemStack(ModItems.Harmonionhelmet, 1, 0)));
        var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 10, 0), new ItemStack(ModItems.Harmonionchestplate, 1, 0)));
        var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 10, 0), new ItemStack(ModItems.Harmonionlegs, 1, 0)));
        var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2, 0), new ItemStack(ModItems.Refinedsoundstone, 4, 0)));
    }

    public StructureVillagePieceWeight getVillagePieceWeight(Random var1, int var2)
    {
        return new StructureVillagePieceWeight(ComponentPowerRoom.class, 15, MathHelper.getRandomIntegerInRange(var1, 0, 1 + var2));
    }

    public Class getComponentClass()
    {
        return ComponentPowerRoom.class;
    }

    public Object buildComponent(StructureVillagePieceWeight var1, ComponentVillageStartPiece var2, List var3, Random var4, int var5, int var6, int var7, int var8, int var9)
    {
        return ComponentPowerRoom.buildComponent(var2, var3, var4, var5, var6, var7, var8, var9);
    }
}
