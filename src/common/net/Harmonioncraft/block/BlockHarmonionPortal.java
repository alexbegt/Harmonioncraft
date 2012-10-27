package net.Harmonioncraft.block;

import java.util.Random;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.Harmonioncraft.core.handlers.AchievementPageHandler;
import net.minecraft.src.AchievementList;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockBreakable;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLightningBolt;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemMonsterPlacer;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockHarmonionPortal extends BlockBreakable
{
    public BlockHarmonionPortal(int var1, int var2)
    {
        super(var1, var2, Material.portal, false);
        this.setHardness(-1.0F);
        this.setStepSound(Block.soundGlassFootstep);
        this.setLightValue(0.75F);
        this.setCreativeTab(ModBlocks.tabHarmonioncraftB);
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting)
        {
            int var6;

            for (var6 = par3; !par1World.doesBlockHaveSolidTopSurface(par2, var6, par4) && var6 > 0; --var6)
            {
                ;
            }

            if (var6 > 0 && !par1World.isBlockNormalCube(par2, var6 + 1, par4))
            {
                ItemMonsterPlacer.spawnCreature(par1World, 57, (double)par2 + 0.5D, (double)var6 + 1.1D, (double)par4 + 0.5D);
            }
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float var5;
        float var6;

        if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) != this.blockID && par1IBlockAccess.getBlockId(par2 + 1, par3, par4) != this.blockID)
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

    /**
     * Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z
     */
    public boolean tryToCreatePortal(World var1, int var2, int var3, int var4)
    {
        if (this.isGoodPortalPool(var1, var2, var3, var4))
        {
            var1.addWeatherEffect(new EntityLightningBolt(var1, (double)var2, (double)var3, (double)var4));
            this.transmuteWaterToPortal(var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void transmuteWaterToPortal(World var1, int var2, int var3, int var4)
    {
        int var5 = var2;
        int var6 = var4;

        if (var1.getBlockMaterial(var2 - 1, var3, var4) == Material.water)
        {
            var5 = var2 - 1;
        }

        if (var1.getBlockMaterial(var5, var3, var4 - 1) == Material.water)
        {
            var6 = var4 - 1;
        }

        var1.editingBlocks = true;
        var1.setBlockWithNotify(var5 + 0, var3, var6 + 0, ModBlocks.HarmonionPortal.blockID);
        var1.setBlockWithNotify(var5 + 1, var3, var6 + 0, ModBlocks.HarmonionPortal.blockID);
        var1.setBlockWithNotify(var5 + 1, var3, var6 + 1, ModBlocks.HarmonionPortal.blockID);
        var1.setBlockWithNotify(var5 + 0, var3, var6 + 1, ModBlocks.HarmonionPortal.blockID);
        var1.editingBlocks = false;
    }

    public boolean isGoodPortalPool(World var1, int var2, int var3, int var4)
    {
        boolean var5 = false;
        var5 |= this.isGoodPortalPoolStrict(var1, var2 + 0, var3, var4 + 0);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 - 1, var3, var4 - 1);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 + 0, var3, var4 - 1);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 + 1, var3, var4 - 1);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 - 1, var3, var4 + 0);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 + 1, var3, var4 + 0);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 - 1, var3, var4 + 1);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 + 0, var3, var4 + 1);
        var5 |= this.isGoodPortalPoolStrict(var1, var2 + 1, var3, var4 + 1);
        return var5;
    }

    public boolean isGoodPortalPoolStrict(World var1, int var2, int var3, int var4)
    {
        boolean var5 = true;
        var5 &= var1.getBlockMaterial(var2 + 0, var3, var4 + 0) == Material.water;
        var5 &= var1.getBlockMaterial(var2 + 1, var3, var4 + 0) == Material.water;
        var5 &= var1.getBlockMaterial(var2 + 1, var3, var4 + 1) == Material.water;
        var5 &= var1.getBlockMaterial(var2 + 0, var3, var4 + 1) == Material.water;
        var5 &= this.isGrassOrDirt(var1, var2 - 1, var3, var4 - 1);
        var5 &= this.isGrassOrDirt(var1, var2 - 1, var3, var4 + 0);
        var5 &= this.isGrassOrDirt(var1, var2 - 1, var3, var4 + 1);
        var5 &= this.isGrassOrDirt(var1, var2 - 1, var3, var4 + 2);
        var5 &= this.isGrassOrDirt(var1, var2 + 0, var3, var4 - 1);
        var5 &= this.isGrassOrDirt(var1, var2 + 1, var3, var4 - 1);
        var5 &= this.isGrassOrDirt(var1, var2 + 0, var3, var4 + 2);
        var5 &= this.isGrassOrDirt(var1, var2 + 1, var3, var4 + 2);
        var5 &= this.isGrassOrDirt(var1, var2 + 2, var3, var4 - 1);
        var5 &= this.isGrassOrDirt(var1, var2 + 2, var3, var4 + 0);
        var5 &= this.isGrassOrDirt(var1, var2 + 2, var3, var4 + 1);
        var5 &= this.isGrassOrDirt(var1, var2 + 2, var3, var4 + 2);
        var5 &= var1.getBlockMaterial(var2 + 0, var3 - 1, var4 + 0).isSolid();
        var5 &= var1.getBlockMaterial(var2 + 1, var3 - 1, var4 + 0).isSolid();
        var5 &= var1.getBlockMaterial(var2 + 1, var3 - 1, var4 + 1).isSolid();
        var5 &= var1.getBlockMaterial(var2 + 0, var3 - 1, var4 + 1).isSolid();
        return var5;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        boolean var6 = true;

        if (var1.getBlockId(var2 - 1, var3, var4) == this.blockID)
        {
            var6 &= this.isGrassOrDirt(var1, var2 + 1, var3, var4);
        }
        else if (var1.getBlockId(var2 + 1, var3, var4) == this.blockID)
        {
            var6 &= this.isGrassOrDirt(var1, var2 - 1, var3, var4);
        }
        else
        {
            var6 = false;
        }

        if (var1.getBlockId(var2, var3, var4 - 1) == this.blockID)
        {
            var6 &= this.isGrassOrDirt(var1, var2, var3, var4 + 1);
        }
        else if (var1.getBlockId(var2, var3, var4 + 1) == this.blockID)
        {
            var6 &= this.isGrassOrDirt(var1, var2, var3, var4 - 1);
        }
        else
        {
            var6 = false;
        }

        if (!var6)
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.waterStill.blockID);
        }
    }

    protected boolean isGrassOrDirt(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMaterial(var2, var3, var4) == Material.rock;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID)
        {
            return false;
        }
        else
        {
            boolean var6 = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID;
            boolean var7 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID;
            boolean var8 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID;
            boolean var9 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID;
            boolean var10 = var6 || var7;
            boolean var11 = var8 || var9;
            return var10 && par5 == 4 ? true : (var10 && par5 == 5 ? true : (var11 && par5 == 2 ? true : var11 && par5 == 3));
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par5Random.nextInt(100) == 0)
        {
            par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F);
        }

        for (int var6 = 0; var6 < 4; ++var6)
        {
            double var7 = (double)((float)par2 + par5Random.nextFloat());
            double var9 = (double)((float)par3 + par5Random.nextFloat());
            double var11 = (double)((float)par4 + par5Random.nextFloat());
            double var13 = 0.0D;
            double var15 = 0.0D;
            double var17 = 0.0D;
            int var19 = par5Random.nextInt(2) * 2 - 1;
            var13 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            var15 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
            var17 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;

            if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID && par1World.getBlockId(par2 + 1, par3, par4) != this.blockID)
            {
                var7 = (double)par2 + 0.5D + 0.25D * (double)var19;
                var13 = (double)(par5Random.nextFloat() * 2.0F * (float)var19);
            }
            else
            {
                var11 = (double)par4 + 0.5D + 0.25D * (double)var19;
                var17 = (double)(par5Random.nextFloat() * 2.0F * (float)var19);
            }

            par1World.spawnParticle("portal", var7, var9, var11, var13, var15, var17);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return 0;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     *
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (var5.ridingEntity == null && var5.riddenByEntity == null && var5 instanceof EntityPlayerMP)
        {
            EntityPlayerMP var6 = (EntityPlayerMP)var5;

            var6.timeInPortal += 0.0125F;

            if (var6.timeInPortal >= 1.0F)
            {
            	if (var6.dimension != 8)
            	{
            		var6.triggerAchievement(AchievementPageHandler.twilightPortal);
                    var6.triggerAchievement(AchievementPageHandler.twilightArrival);
            		var6.mcServer.getConfigurationManager().transferPlayerToDimension(var6, 8, new HMCTeleporter());
            		var6.addExperience(0);
            		var6.triggerAchievement(AchievementPageHandler.twilightPortal);
                    var6.triggerAchievement(AchievementPageHandler.twilightArrival);
            	}
            	else
            	{
            		var6.mcServer.getConfigurationManager().transferPlayerToDimension(var6, 0, new HMCTeleporter());
            		var6.removeExperience(0);
            	}
            }
        }
    }*/
}
