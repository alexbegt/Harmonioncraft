package net.Harmonion.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import net.Harmonion.util.Config;
import net.Harmonion.util.random.Strings;
import net.Harmonion.world.TeleporterHarmonion;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockHarmonionPortal extends BlockBreakable
{
    private int firetick;
    private int firemax = 1000;

    public BlockHarmonionPortal(int var1, int var2)
    {
        super(var1, var2, Material.portal, false);
        this.firetick = this.firemax;
        this.setBlockUnbreakable();
        this.setHardness(10000.0F);
        this.setResistance(6000000.0F);
        this.setBlockName(Strings.Sound_Stone_Portal_Name);
        this.setCreativeTab(null);
        //this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        float var5;
        float var6;

        if (var1.getBlockId(var2 - 1, var3, var4) != this.blockID && var1.getBlockId(var2 + 1, var3, var4) != this.blockID)
        {
            var5 = 0.125F;
            var6 = 0.5F;
            this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
        }
        else
        {
            var5 = 0.5F;
            var6 = 0.125F;
            this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean tryToCreatePortal(World var1, int var2, int var3, int var4)
    {
        byte var5 = 0;
        byte var6 = 0;

        if (var1.getBlockId(var2 - 1, var3, var4) == ModBlocks.HarmonionBlock.blockID || var1.getBlockId(var2 + 1, var3, var4) == ModBlocks.HarmonionBlock.blockID)
        {
            var5 = 1;
        }

        if (var1.getBlockId(var2, var3, var4 - 1) == ModBlocks.HarmonionBlock.blockID || var1.getBlockId(var2, var3, var4 + 1) == ModBlocks.HarmonionBlock.blockID)
        {
            var6 = 1;
        }

        if (var5 == var6)
        {
            return false;
        }
        else
        {
            if (var1.getBlockId(var2 - var5, var3, var4 - var6) == 0)
            {
                var2 -= var5;
                var4 -= var6;
            }

            int var7;
            int var8;

            for (var7 = -1; var7 <= 2; ++var7)
            {
                for (var8 = -1; var8 <= 3; ++var8)
                {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;

                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3)
                    {
                        int var10 = var1.getBlockId(var2 + var5 * var7, var3 + var8, var4 + var6 * var7);

                        if (var9)
                        {
                            if (var10 != ModBlocks.HarmonionBlock.blockID)
                            {
                                return false;
                            }
                        }
                        else if (var10 != 0 && var10 != ModBlocks.HarmonionFire.blockID)
                        {
                            return false;
                        }
                    }
                }
            }

            var1.editingBlocks = true;

            for (var7 = 0; var7 < 2; ++var7)
            {
                for (var8 = 0; var8 < 3; ++var8)
                {
                    var1.setBlockWithNotify(var2 + var5 * var7, var3 + var8, var4 + var6 * var7, ModBlocks.HarmonionPortal.blockID);
                }
            }

            var1.editingBlocks = false;
            return true;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        byte var6 = 0;
        byte var7 = 1;

        if (var1.getBlockId(var2 - 1, var3, var4) == this.blockID || var1.getBlockId(var2 + 1, var3, var4) == this.blockID)
        {
            var6 = 1;
            var7 = 0;
        }

        int var8;

        for (var8 = var3; var1.getBlockId(var2, var8 - 1, var4) == this.blockID; --var8)
        {
            ;
        }

        if (var1.getBlockId(var2, var8 - 1, var4) != ModBlocks.HarmonionBlock.blockID)
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
        else
        {
            int var9;

            for (var9 = 1; var9 < 4 && var1.getBlockId(var2, var8 + var9, var4) == this.blockID; ++var9)
            {
                ;
            }

            if (var9 == 3 && var1.getBlockId(var2, var8 + var9, var4) == ModBlocks.HarmonionBlock.blockID)
            {
                boolean var10 = var1.getBlockId(var2 - 1, var3, var4) == this.blockID || var1.getBlockId(var2 + 1, var3, var4) == this.blockID;
                boolean var11 = var1.getBlockId(var2, var3, var4 - 1) == this.blockID || var1.getBlockId(var2, var3, var4 + 1) == this.blockID;

                if (var10 && var11)
                {
                    var1.setBlockWithNotify(var2, var3, var4, 0);
                }
                else if ((var1.getBlockId(var2 + var6, var3, var4 + var7) != ModBlocks.HarmonionBlock.blockID || var1.getBlockId(var2 - var6, var3, var4 - var7) != this.blockID) && (var1.getBlockId(var2 - var6, var3, var4 - var7) != ModBlocks.HarmonionBlock.blockID || var1.getBlockId(var2 + var6, var3, var4 + var7) != this.blockID))
                {
                    var1.setBlockWithNotify(var2, var3, var4, 0);
                }
            }
            else
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }
        }
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            return false;
        }
        else
        {
            boolean var6 = var1.getBlockId(var2 - 1, var3, var4) == this.blockID && var1.getBlockId(var2 - 2, var3, var4) != this.blockID;
            boolean var7 = var1.getBlockId(var2 + 1, var3, var4) == this.blockID && var1.getBlockId(var2 + 2, var3, var4) != this.blockID;
            boolean var8 = var1.getBlockId(var2, var3, var4 - 1) == this.blockID && var1.getBlockId(var2, var3, var4 - 2) != this.blockID;
            boolean var9 = var1.getBlockId(var2, var3, var4 + 1) == this.blockID && var1.getBlockId(var2, var3, var4 + 2) != this.blockID;
            boolean var10 = var6 || var7;
            boolean var11 = var8 || var9;
            return var10 && var5 == 4 ? true : (var10 && var5 == 5 ? true : (var11 && var5 == 2 ? true : var11 && var5 == 3));
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 0;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote)
        {
            if (this.firetick == this.firemax && this.firemax != 0)
            {
                if (var5 instanceof EntityPlayerMP)
                {
                    WorldServer var6 = (WorldServer)var1;
                    EntityPlayerMP var7 = (EntityPlayerMP)var5;

                    if (var5.ridingEntity == null && var5.riddenByEntity == null && var5 instanceof EntityPlayer)
                    {
                        if (var7.dimension != Config.getBlockID("dimension.dimension.id"))
                        {
                            var7.mcServer.getConfigurationManager().transferPlayerToDimension(var7, Config.getBlockID("dimension.dimension.id"), new TeleporterHarmonion(var7.mcServer.worldServerForDimension(Config.getBlockID("dimension.dimension.id"))));
                        }
                        else
                        {
                            var7.mcServer.getConfigurationManager().transferPlayerToDimension(var7, 0, new TeleporterHarmonion(var7.mcServer.worldServerForDimension(0)));
                        }
                    }
                }

                this.firetick = 0;
            }
            else
            {
                ++this.firetick;
            }
        }
    }
    
}
