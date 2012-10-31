package net.Harmonioncraft.item;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.src.Block;
import net.minecraft.src.BlockJukeBox;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemRecord;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet131MapData;
import net.minecraft.src.World;
import net.Harmonioncraft.mods.Harmonioncraft;
import net.Harmonioncraft.lib.Reference;

public class ItemRecords extends ItemRecord
{
    public final String recordName;
    public final String recordInfo;

    public ItemRecords(int var1, String var2, String var3)
    {
        super(var1, var2);
        this.recordName = var2;
        this.recordInfo = var3;
        this.maxStackSize = 1;
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
    }

    @Override
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var3.getBlockId(var4, var5, var6) == Block.jukebox.blockID && var3.getBlockMetadata(var4, var5, var6) == 0)
        {
            if (var3.isRemote)
            {
                return true;
            }
            else
            {
                ((BlockJukeBox)Block.jukebox).insertRecord(var3, var4, var5, var6, this.shiftedIndex);
                var3.playAuxSFXAtEntity((EntityPlayer)null, 1005, var4, var5, var6, this.shiftedIndex);
                --var1.stackSize;
                ByteArrayOutputStream var11 = new ByteArrayOutputStream();
                DataOutputStream var12 = new DataOutputStream(var11);

                try
                {
                    var12.writeUTF(this.getRecordInfo(var1));
                    PacketDispatcher.sendPacketToAllAround((double)var4, (double)var5, (double)var6, 64.0D, var3.provider.dimensionId, new Packet131MapData((short)getNetId(), (short)7, var11.toByteArray()));
                }
                catch (IOException var14)
                {
                    var14.printStackTrace();
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add(this.getRecordInfo(par1ItemStack));
    }

    public String getRecordInfo(ItemStack var1)
    {
        StringBuilder var2 = new StringBuilder();
        var2.append("Gotye - ").append(this.recordInfo);
        return var2.toString();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        if (this.shiftedIndex != Item.potion.shiftedIndex && this.shiftedIndex != Item.monsterPlacer.shiftedIndex)
        {
            var3.add(new ItemStack(this, 1, 0));
        }
    }
    public static int getNetId()
    {
        return FMLNetworkHandler.instance().findNetworkModHandler(Harmonioncraft.instance).getNetworkId();
    }
}
