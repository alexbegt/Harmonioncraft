package net.Harmonion.block.tank;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.Harmonion.gui.EnumGui;
import net.Harmonion.gui.GuiHandler;
import net.Harmonion.gui.slots.SlotLiquidContainer;
import net.Harmonion.liquids.LiquidManager;
import net.Harmonion.liquids.TankManager;
import net.Harmonion.liquids.tanks.StandardTank;
import net.Harmonion.util.Game;
import net.Harmonion.util.LocalizationHandler;
import net.Harmonion.util.inventory.StandaloneInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public abstract class TileTankHarmonion extends TileMultiBlock implements ITankTile
{
	private static final int CAPACITY_PER_BLOCK = 16000;
	protected static final int SLOT_INPUT = 0;
	protected static final int SLOT_OUTPUT = 1;
	protected final TankManager tankManager = new TankManager();
	private static final List patterns = buildPatterns();
	private static final Set tankBlocks = new HashSet();
	private final StandaloneInventory inv;

    protected TileTankHarmonion()
    {
        super(patterns);
        this.inv = new StandaloneInventory(2, "gui.tank.iron", this);
        StandardTank tank = new StandardTank(64000, this);
        this.tankManager.addTank(tank);
    }

    public IInventory getInventory()
    {
        return this.inv;
    }

    public Slot getInputSlot(IInventory var1, int xOffset, int yOffset, int zOffset)
    {
        return new SlotLiquidContainer(var1, xOffset, yOffset, zOffset);
    }

    public float getResistance(Entity var1)
    {
        return 15.0F;
    }

    protected int getMaxRecursionDepth()
    {
        return 500;
    }

    protected boolean isStructureTile(TileEntity var1)
    {
        return var1 instanceof TileTankHarmonion;
    }
    
    public boolean blockActivated(EntityPlayer player, int side)
    {
        ItemStack current = player.getItemInUse();
        if (Game.isHost(this.worldObj)) {
          if ((isStructureValid()) && (LiquidManager.getInstance().handleRightClick(getTankManager(), 0, player, true, true))) {
            return true;
          }
        }
        else if (LiquidManager.getInstance().isContainer(current)) {
          return true;
        }

        if ((current != null) && (current.itemID == Item.boat.itemID)) {
          return true;
        }
        return super.blockActivated(player, side);
      }

      public boolean openGui(EntityPlayer player)
      {
        TileMultiBlock mBlock = getMasterBlock();
        if (mBlock != null) {
          GuiHandler.openGui(EnumGui.TANK, player, this.worldObj, mBlock.xCoord, mBlock.yCoord, mBlock.zCoord);
          return true;
        }
        return false;
      }
      
      public TankManager getTankManager()
      {
    	TileTankHarmonion mBlock = (TileTankHarmonion)getMasterBlock();
        if (mBlock != null) {
          return mBlock.tankManager;
        }
        return null;
      }

      public ILiquidTank getTank()
      {
    	TileTankHarmonion mBlock = (TileTankHarmonion)getMasterBlock();
        if (mBlock != null) {
          return mBlock.tankManager.getTank(0);
        }
        return null;
      }

      protected void onPatternLock(MultiBlockPattern pattern)
      {
        if (this.isMaster) {
          int capacity = (pattern.getPatternWidthX() - 2) * (pattern.getPatternHeight() - pattern.getMasterOffsetY() * 2) * (pattern.getPatternWidthZ() - 2) * 16000;
          this.tankManager.setCapacity(0, capacity);
        } else {
          this.tankManager.getTank(0).setLiquid(null);
        }
      }

      protected boolean isMapPositionValid(int x, int y, int z, char mapPos)
      {
        switch (mapPos)
        {
        case 'O':
          int id = this.worldObj.getBlockId(x, y, z);
          if (id == getBlockId()) {
            int meta = this.worldObj.getBlockMetadata(x, y, z);
            if (tankBlocks.contains(Integer.valueOf(meta))) {
              return false;
            }
          }
          return true;
        case 'W':
          int id1 = this.worldObj.getBlockId(x, y, z);
          if (id1 != getBlockId()) {
            return false;
          }
          int meta1 = this.worldObj.getBlockMetadata(x, y, z);
          if (!tankBlocks.contains(Integer.valueOf(meta1))) {
            return false;
          }
          return true;
        case 'B':
          int id2 = this.worldObj.getBlockId(x, y, z);
          if (id2 != getBlockId()) {
            return false;
          }
          int meta = this.worldObj.getBlockMetadata(x, y, z);
          if (meta != EnumMachineBeta.TANK_IRON_WALL.ordinal()) {
            return false;
          }
          return true;
        case 'M':
        case 'T':
          int id3 = this.worldObj.getBlockId(x, y, z);
          if (id3 != getBlockId()) {
            return false;
          }
          int meta4 = this.worldObj.getBlockMetadata(x, y, z);
          if (!tankBlocks.contains(Integer.valueOf(meta4))) {
            return false;
          }
          TileEntity tile = this.worldObj.getBlockTileEntity(x, y, z);
          if (!(tile instanceof TileMultiBlock)) {
            this.worldObj.removeBlockTileEntity(x, y, z);
            return true;
          }
          if (((TileMultiBlock)tile).isStructureValid()) {
            return false;
          }
          return true;
        case 'A':
          if (!this.worldObj.isAirBlock(x, y, z)) {
            return false;
          }
          return true;
        }

        return true;
      }

    public void updateEntity()
    {
        super.updateEntity();

        if ((Game.isHost(this.worldObj)) && 
        	      (this.isMaster))
        	    {
        	      if (this.update % 8 == 0) {
        	        LiquidManager.getInstance().processContainers(this.tankManager.getTank(0), this.inv, 0, 1);
        	      }

        	      if (this.update % 128 == 0)
        	        sendUpdateToClient();
        	    }
    }

    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        super.invalidate();
        this.tankManager.getTank(0).setLiquid(null);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        this.tankManager.writeTanksToNBT(var1);
        this.inv.writeToNBT("inv", var1);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.tankManager.readTanksFromNBT(var1);
        this.inv.readFromNBT("inv", var1);
    }

    public void writePacketData(DataOutputStream var1) throws IOException
    {
        super.writePacketData(var1);
        this.tankManager.writePacketData(var1);
    }

    public void readPacketData(DataInputStream var1) throws IOException
    {
        super.readPacketData(var1);
        this.tankManager.readPacketData(var1);
    }
    
    private static List buildPatterns() {
        List pats = new ArrayList();
        boolean client = FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;

        int xOffset = 2;
        int yOffset = 0;
        int zOffset = 2;

        char[][] bottom = { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'M', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O' } };

        char[][] middle = { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'W', 'B', 'O' }, { 'O', 'W', 'A', 'W', 'O' }, { 'O', 'B', 'W', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O' } };

        char[][] top = { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'T', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O' } };

        char[][] border = { { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O' } };

        for (int i = 4; i <= 8; i++) {
          char[][][] map = buildMap(i, bottom, middle, top, border);
          AxisAlignedBB entityCheck = AxisAlignedBB.getBoundingBox(0.0D, 1.0D, 0.0D, 1.0D, i - 1, 1.0D);
          pats.add(buildPattern(map, xOffset, yOffset, zOffset, entityCheck));
        }

        if ((client) || (LocalizationHandler.getMaxTankSize() >= 5)) {
          xOffset = zOffset = 3;

          bottom = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'M', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          middle = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          top = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'T', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          border = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          for (int i = 4; i <= 8; i++) {
            char[][][] map = buildMap(i, bottom, middle, top, border);
            AxisAlignedBB entityCheck = AxisAlignedBB.getBoundingBox(-1.0D, 1.0D, -1.0D, 2.0D, i - 1, 2.0D);
            pats.add(buildPattern(map, xOffset, yOffset, zOffset, entityCheck));
          }

        }

        if ((client) || (LocalizationHandler.getMaxTankSize() >= 7)) {
          xOffset = zOffset = 4;

          bottom = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'M', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          middle = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          top = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'T', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          border = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          for (int i = 4; i <= 8; i++) {
            char[][][] map = buildMap(i, bottom, middle, top, border);
            AxisAlignedBB entityCheck = AxisAlignedBB.getBoundingBox(-2.0D, 1.0D, -2.0D, 3.0D, i - 1, 3.0D);
            pats.add(buildPattern(map, xOffset, yOffset, zOffset, entityCheck));
          }

        }

        if ((client) || (LocalizationHandler.getMaxTankSize() >= 9)) {
          xOffset = zOffset = 5;

          bottom = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'M', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          middle = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'W', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'W', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          top = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'T', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'B', 'O' }, { 'O', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          border = new char[][] { { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' }, { 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' } };

          for (int i = 4; i <= 8; i++) {
            char[][][] map = buildMap(i, bottom, middle, top, border);
            AxisAlignedBB entityCheck = AxisAlignedBB.getBoundingBox(-3.0D, 1.0D, -3.0D, 4.0D, i - 1, 4.0D);
            pats.add(buildPattern(map, xOffset, yOffset, zOffset, entityCheck));
          }
        }

        return pats;
      }
    
    private static MultiBlockPattern buildPattern(char[][][] map, int xOffset, int yOffset, int zOffset, AxisAlignedBB entityCheck) {
        if (!LocalizationHandler.allowTankStacking()) {
          entityCheck.offset(0.0D, 1.0D, 0.0D);
          yOffset = 1;
        }
        return new MultiBlockPattern(map, xOffset, yOffset, zOffset, entityCheck);
      }

    private static char[][][] buildMap(int height, char[][] bottom, char[][] mid, char[][] top, char[][] border)
    {
      char[][][] map;
      if (LocalizationHandler.allowTankStacking()) {
        map = new char[height][][];

        map[0] = bottom;
        map[(height - 1)] = top;

        for (int i = 1; i < height - 1; i++)
          map[i] = mid;
      }
      else {
        map = new char[height + 2][][];

        map[0] = border;
        map[1] = bottom;
        map[height] = top;
        map[(height + 1)] = border;

        for (int i = 2; i < height; i++) {
          map[i] = mid;
        }
      }

      return map;
    }

    static
    {
        tankBlocks.add(Integer.valueOf(EnumMachineBeta.TANK_IRON_WALL.ordinal()));
        tankBlocks.add(Integer.valueOf(EnumMachineBeta.TANK_IRON_VALVE.ordinal()));
        tankBlocks.add(Integer.valueOf(EnumMachineBeta.TANK_IRON_GAUGE.ordinal()));
    }
}
