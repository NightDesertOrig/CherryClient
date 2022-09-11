//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import jdk.nashorn.internal.ir.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.util.*;
import java.util.*;

public class WorldUtils implements Util
{
    public static final List<Object> empty;
    public static final List<Block> blackList;
    public static List<Block> emptyBlocks;
    
    public static net.minecraft.block.Block getBlock(final BlockPos block) {
        return WorldUtils.mc.world.getBlockState(block).getBlock();
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
                    final float f = (float)y;
                    final float f3;
                    final float f2 = f3 = (sphere ? (cy + r) : ((float)(cy + h)));
                    if (f >= f2) {
                        break;
                    }
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        circleblocks.add(new BlockPos(x, y + plus_y, z));
                    }
                    ++y;
                }
            }
        }
        return circleblocks;
    }
    
    public static EnumFacing getPlaceableSide(final BlockPos pos) {
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbour = pos.offset(side);
            final IBlockState blockState;
            if (WorldUtils.mc.world.getBlockState(neighbour).getBlock().canCollideCheck(WorldUtils.mc.world.getBlockState(neighbour), false) && !(blockState = WorldUtils.mc.world.getBlockState(neighbour)).getBlock().getMaterial(blockState).isReplaceable()) {
                return side;
            }
        }
        return null;
    }
    
    public static double getRange(final Vec3d vec) {
        return WorldUtils.mc.player.getPositionVector().add(0.0, (double)WorldUtils.mc.player.eyeHeight, 0.0).distanceTo(vec);
    }
    
    public static EnumFacing getEnumFacing(final boolean rayTrace, final BlockPos placePosition) {
        final RayTraceResult result = WorldUtils.mc.world.rayTraceBlocks(new Vec3d(WorldUtils.mc.player.posX, WorldUtils.mc.player.posY + WorldUtils.mc.player.getEyeHeight(), WorldUtils.mc.player.posZ), new Vec3d(placePosition.getX() + 0.5, placePosition.getY() - 0.5, placePosition.getZ() + 0.5));
        if (placePosition.getY() == 255) {
            return EnumFacing.DOWN;
        }
        if (rayTrace) {
            return (result == null || result.sideHit == null) ? EnumFacing.UP : result.sideHit;
        }
        return EnumFacing.UP;
    }
    
    public static boolean canBeClicked(final BlockPos pos) {
        return WorldUtils.mc.world.getBlockState(pos).getBlock().canCollideCheck(WorldUtils.mc.world.getBlockState(pos), false);
    }
    
    public static boolean isWithin(final double distance, final Vec3d vec, final Vec3d vec2) {
        return vec.squareDistanceTo(vec2) <= distance * distance;
    }
    
    public static boolean isOutside(final double distance, final Vec3d vec, final Vec3d vec2) {
        return vec.squareDistanceTo(vec2) > distance * distance;
    }
    
    private boolean place(final BlockPos pos) {
        boolean isSneaking = WorldUtils.mc.player.isSneaking();
        final net.minecraft.block.Block block = WorldUtils.mc.world.getBlockState(pos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockFire)) {
            return false;
        }
        final EnumFacing side = getPlaceableSide(pos);
        if (side == null) {
            return false;
        }
        final BlockPos neighbour = pos.offset(side);
        final EnumFacing opposite = side.getOpposite();
        if (!canBeClicked(neighbour)) {
            return false;
        }
        final Vec3d hitVec = new Vec3d((Vec3i)neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        final net.minecraft.block.Block neighbourBlock = WorldUtils.mc.world.getBlockState(neighbour).getBlock();
        if (!isSneaking && WorldUtils.blackList.contains(neighbourBlock)) {
            WorldUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)WorldUtils.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            isSneaking = true;
        }
        WorldUtils.mc.playerController.processRightClickBlock(WorldUtils.mc.player, WorldUtils.mc.world, neighbour, opposite, hitVec, EnumHand.MAIN_HAND);
        WorldUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }
    
    public static void placeBlock(final BlockPos pos, final int slot) {
        if (slot == -1) {
            return;
        }
        final int prev = WorldUtils.mc.player.inventory.currentItem;
        WorldUtils.mc.player.inventory.currentItem = slot;
        placeBlock(pos);
        WorldUtils.mc.player.inventory.currentItem = prev;
    }
    
    public static void openBlock(final BlockPos pos) {
        final EnumFacing[] values;
        final EnumFacing[] facings = values = EnumFacing.values();
        for (final EnumFacing f : values) {
            final net.minecraft.block.Block neighborBlock = WorldUtils.mc.world.getBlockState(pos.offset(f)).getBlock();
            if (WorldUtils.emptyBlocks.contains(neighborBlock)) {
                WorldUtils.mc.playerController.processRightClickBlock(WorldUtils.mc.player, WorldUtils.mc.world, pos, f.getOpposite(), new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
                return;
            }
        }
    }
    
    public static boolean placeBlock(final BlockPos pos) {
        if (TestUtil.isBlockEmpty(pos)) {
            final EnumFacing[] values;
            final EnumFacing[] facings = values = EnumFacing.values();
            for (final EnumFacing f : values) {
                final net.minecraft.block.Block neighborBlock = Util.mc.world.getBlockState(pos.offset(f)).getBlock();
                final Vec3d vec = new Vec3d(pos.getX() + 0.5 + f.getXOffset() * 0.5, pos.getY() + 0.5 + f.getYOffset() * 0.5, pos.getZ() + 0.5 + f.getZOffset() * 0.5);
                if (!WorldUtils.emptyBlocks.contains(neighborBlock) && Util.mc.player.getPositionEyes(WorldUtils.mc.getRenderPartialTicks()).distanceTo(vec) <= 4.25) {
                    final float[] rot = { Util.mc.player.rotationYaw, Util.mc.player.rotationPitch };
                    if (TestUtil.rightclickableBlocks.contains(neighborBlock)) {
                        Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    Util.mc.playerController.processRightClickBlock(Util.mc.player, Util.mc.world, pos.offset(f), f.getOpposite(), new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
                    if (TestUtil.rightclickableBlocks.contains(neighborBlock)) {
                        Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    Util.mc.player.swingArm(EnumHand.MAIN_HAND);
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        empty = Arrays.asList(new Object[0]);
        blackList = Arrays.asList(new Block[0]);
        WorldUtils.emptyBlocks = Arrays.asList(new Block[0]);
    }
}
