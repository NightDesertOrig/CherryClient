//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import com.cocoapc.cherry.util.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.block.material.*;
import net.minecraft.init.*;
import java.util.*;

public class BlockUtilE implements Util
{
    public static final List<Block> blackList;
    
    public static Block getBlock(final BlockPos pos) {
        return getBlockState(pos).getBlock();
    }
    
    public static IBlockState getBlockState(final BlockPos pos) {
        return BlockUtilE.mc.world.getBlockState(pos);
    }
    
    public static EnumFacing getDirection(final BlockPos pos) {
        final RayTraceResult result = BlockUtilE.mc.world.rayTraceBlocks(new Vec3d(BlockUtilE.mc.player.posX, BlockUtilE.mc.player.posY + BlockUtilE.mc.player.eyeHeight, BlockUtilE.mc.player.posZ), new Vec3d((Vec3i)pos));
        if (result == null) {
            return EnumFacing.UP;
        }
        return result.sideHit;
    }
    
    public static List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final ArrayList<BlockPos> circleblocks = new ArrayList<BlockPos>();
        final int cx = loc.getX();
        final int cy = loc.getY();
        final int cz = loc.getZ();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                int y = sphere ? (cy - (int)r) : cy;
                while (true) {
                    final float f2;
                    final float f = f2 = (sphere ? (cy + r) : ((float)(cy + h)));
                    if (y >= f) {
                        break;
                    }
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                    ++y;
                }
            }
        }
        return circleblocks;
    }
    
    public static boolean canSeePos(final BlockPos pos) {
        final RayTraceResult result = BlockUtilE.mc.world.rayTraceBlocks(new Vec3d(BlockUtilE.mc.player.posX, BlockUtilE.mc.player.posY + BlockUtilE.mc.player.eyeHeight, BlockUtilE.mc.player.posZ), new Vec3d((Vec3i)pos));
        return result != null;
    }
    
    public static BlockPos placeBlock(final BlockPos pos, final boolean packet) {
        final Block block = BlockUtilE.mc.world.getBlockState(pos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return null;
        }
        final EnumFacing side = getPlaceableSide(pos);
        if (side == null) {
            return null;
        }
        final BlockPos neighbour = pos.offset(side);
        final EnumFacing opposite = side.getOpposite();
        final Vec3d hitVec = new Vec3d((Vec3i)neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        if (packet) {
            rightClickBlock(neighbour, hitVec, EnumHand.MAIN_HAND, opposite);
        }
        else {
            BlockUtilE.mc.playerController.processRightClickBlock(BlockUtilE.mc.player, BlockUtilE.mc.world, neighbour, opposite, hitVec, EnumHand.MAIN_HAND);
            BlockUtilE.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        return neighbour;
    }
    
    public static Block getBlockSHG(final BlockPos pos) {
        return getBlockState(pos).getBlock();
    }
    
    public static void rightClickBlock(final BlockPos pos, final EnumFacing facing, final boolean packet) {
        final Vec3d hitVec = new Vec3d((Vec3i)pos).add(0.5, 0.5, 0.5).add(new Vec3d(facing.getDirectionVec()).scale(0.5));
        if (packet) {
            rightClickBlock(pos, hitVec, EnumHand.MAIN_HAND, facing);
        }
        else {
            BlockUtilE.mc.playerController.processRightClickBlock(BlockUtilE.mc.player, BlockUtilE.mc.world, pos, facing, hitVec, EnumHand.MAIN_HAND);
            BlockUtilE.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static void rightClickBlock(final BlockPos pos, final Vec3d vec, final EnumHand hand, final EnumFacing direction) {
        final float f = (float)(vec.x - pos.getX());
        final float f2 = (float)(vec.y - pos.getY());
        final float f3 = (float)(vec.z - pos.getZ());
        BlockUtilE.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f2, f3));
        BlockUtilE.mc.player.connection.sendPacket((Packet)new CPacketAnimation(hand));
        BlockUtilE.mc.rightClickDelayTimer = 4;
    }
    
    public static void rightClickBlock(final BlockPos pos, final EnumFacing facing, final Vec3d hVec, final boolean packet) {
        final Vec3d hitVec = new Vec3d((Vec3i)pos).add(hVec).add(new Vec3d(facing.getDirectionVec()).scale(0.5));
        if (packet) {
            rightClickBlock(pos, hitVec, EnumHand.MAIN_HAND, facing);
        }
        else {
            BlockUtilE.mc.playerController.processRightClickBlock(BlockUtilE.mc.player, BlockUtilE.mc.world, pos, facing, hitVec, EnumHand.MAIN_HAND);
            BlockUtilE.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos pos) {
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbour = pos.offset(side);
            if (BlockUtilE.mc.world.getBlockState(neighbour).getBlock().canCollideCheck(BlockUtilE.mc.world.getBlockState(neighbour), false)) {
                final IBlockState blockState = BlockUtilE.mc.world.getBlockState(neighbour);
                if (!blockState.getMaterial().isReplaceable() && !BlockUtilE.blackList.contains(getBlock(neighbour))) {
                    return side;
                }
            }
        }
        return null;
    }
    
    public static RayTraceResult rayTraceBlocks(Vec3d vec31, final Vec3d vec32, final boolean stopOnLiquid, final boolean ignoreBlockWithoutBoundingBox, final boolean returnLastUncollidableBlock, final int maxDistance) {
        if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
            return null;
        }
        if (!Double.isNaN(vec32.x) && !Double.isNaN(vec32.y) && !Double.isNaN(vec32.z)) {
            final int i = MathHelper.floor(vec32.x);
            final int j = MathHelper.floor(vec32.y);
            final int k = MathHelper.floor(vec32.z);
            int l = MathHelper.floor(vec31.x);
            int i2 = MathHelper.floor(vec31.y);
            int j2 = MathHelper.floor(vec31.z);
            BlockPos blockpos = new BlockPos(l, i2, j2);
            final IBlockState iblockstate = BlockUtilE.mc.world.getBlockState(blockpos);
            final Block block = iblockstate.getBlock();
            if ((!ignoreBlockWithoutBoundingBox || iblockstate.getCollisionBoundingBox((IBlockAccess)BlockUtilE.mc.world, blockpos) != Block.NULL_AABB) && block.canCollideCheck(iblockstate, stopOnLiquid)) {
                final RayTraceResult raytraceresult = iblockstate.collisionRayTrace((World)BlockUtilE.mc.world, blockpos, vec31, vec32);
                if (raytraceresult != null) {
                    return raytraceresult;
                }
            }
            RayTraceResult raytraceresult2 = null;
            int k2 = maxDistance;
            while (k2-- >= 0) {
                if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
                    return null;
                }
                if (l == i && i2 == j && j2 == k) {
                    return returnLastUncollidableBlock ? raytraceresult2 : null;
                }
                boolean flag2 = true;
                boolean flag3 = true;
                boolean flag4 = true;
                double d0 = 999.0;
                double d2 = 999.0;
                double d3 = 999.0;
                if (i > l) {
                    d0 = l + 1.0;
                }
                else if (i < l) {
                    d0 = l + 0.0;
                }
                else {
                    flag2 = false;
                }
                if (j > i2) {
                    d2 = i2 + 1.0;
                }
                else if (j < i2) {
                    d2 = i2 + 0.0;
                }
                else {
                    flag3 = false;
                }
                if (k > j2) {
                    d3 = j2 + 1.0;
                }
                else if (k < j2) {
                    d3 = j2 + 0.0;
                }
                else {
                    flag4 = false;
                }
                double d4 = 999.0;
                double d5 = 999.0;
                double d6 = 999.0;
                final double d7 = vec32.x - vec31.x;
                final double d8 = vec32.y - vec31.y;
                final double d9 = vec32.z - vec31.z;
                if (flag2) {
                    d4 = (d0 - vec31.x) / d7;
                }
                if (flag3) {
                    d5 = (d2 - vec31.y) / d8;
                }
                if (flag4) {
                    d6 = (d3 - vec31.z) / d9;
                }
                if (d4 == -0.0) {
                    d4 = -1.0E-4;
                }
                if (d5 == -0.0) {
                    d5 = -1.0E-4;
                }
                if (d6 == -0.0) {
                    d6 = -1.0E-4;
                }
                EnumFacing enumfacing;
                if (d4 < d5 && d4 < d6) {
                    enumfacing = ((i > l) ? EnumFacing.WEST : EnumFacing.EAST);
                    vec31 = new Vec3d(d0, vec31.y + d8 * d4, vec31.z + d9 * d4);
                }
                else if (d5 < d6) {
                    enumfacing = ((j > i2) ? EnumFacing.DOWN : EnumFacing.UP);
                    vec31 = new Vec3d(vec31.x + d7 * d5, d2, vec31.z + d9 * d5);
                }
                else {
                    enumfacing = ((k > j2) ? EnumFacing.NORTH : EnumFacing.SOUTH);
                    vec31 = new Vec3d(vec31.x + d7 * d6, vec31.y + d8 * d6, d3);
                }
                l = MathHelper.floor(vec31.x) - ((enumfacing == EnumFacing.EAST) ? 1 : 0);
                i2 = MathHelper.floor(vec31.y) - ((enumfacing == EnumFacing.UP) ? 1 : 0);
                j2 = MathHelper.floor(vec31.z) - ((enumfacing == EnumFacing.SOUTH) ? 1 : 0);
                blockpos = new BlockPos(l, i2, j2);
                final IBlockState iblockstate2 = BlockUtilE.mc.world.getBlockState(blockpos);
                final Block block2 = iblockstate2.getBlock();
                if (ignoreBlockWithoutBoundingBox && iblockstate2.getMaterial() != Material.PORTAL && iblockstate2.getCollisionBoundingBox((IBlockAccess)BlockUtilE.mc.world, blockpos) == Block.NULL_AABB) {
                    continue;
                }
                if (block2.canCollideCheck(iblockstate2, stopOnLiquid)) {
                    final RayTraceResult raytraceresult3 = iblockstate2.collisionRayTrace((World)BlockUtilE.mc.world, blockpos, vec31, vec32);
                    if (raytraceresult3 != null) {
                        return raytraceresult3;
                    }
                    continue;
                }
                else {
                    raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec31, enumfacing, blockpos);
                }
            }
            return returnLastUncollidableBlock ? raytraceresult2 : null;
        }
        return null;
    }
    
    static {
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.IRON_TRAPDOOR, Blocks.ENCHANTING_TABLE);
    }
}
