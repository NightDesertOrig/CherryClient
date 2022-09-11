//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import net.minecraft.entity.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class PlayerUtil implements Util
{
    public static boolean canEntityBeSeen(final Entity entityIn) {
        return BlockUtil.rayTraceBlocks(new Vec3d(PlayerUtil.mc.player.posX, PlayerUtil.mc.player.posY + PlayerUtil.mc.player.getEyeHeight(), PlayerUtil.mc.player.posZ), new Vec3d(entityIn.posX, entityIn.posY + entityIn.getEyeHeight(), entityIn.posZ), false, true, false, 1000) == null;
    }
    
    public static boolean canBlockBeSeen(final BlockPos pos) {
        return BlockUtil.rayTraceBlocks(new Vec3d(PlayerUtil.mc.player.posX, PlayerUtil.mc.player.posY + PlayerUtil.mc.player.getEyeHeight(), PlayerUtil.mc.player.posZ), new Vec3d(pos.getX() + 0.5, (double)pos.getY(), pos.getZ() + 0.5), false, true, false, 1000) == null;
    }
    
    public static boolean canSeeEntity(final Entity e) {
        return PlayerUtil.mc.player.canEntityBeSeen(e);
    }
    
    public static double getDistance(final Entity e) {
        return PlayerUtil.mc.player.getDistance(e);
    }
    
    public static double getDistance(final BlockPos e) {
        return PlayerUtil.mc.player.getDistance((double)e.getX(), (double)e.getY(), (double)e.getZ());
    }
    
    public static double getDistance(final Vec3d e) {
        return PlayerUtil.mc.player.getDistance(e.x, e.y, e.z);
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos((Entity)PlayerUtil.mc.player);
    }
    
    public static double[] directionSpeed(final double speed) {
        float forward = PlayerUtil.mc.player.movementInput.moveForward;
        float side = PlayerUtil.mc.player.movementInput.moveStrafe;
        float yaw = PlayerUtil.mc.player.prevRotationYaw + (PlayerUtil.mc.player.rotationYaw - PlayerUtil.mc.player.prevRotationYaw) * PlayerUtil.mc.getRenderPartialTicks();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += ((forward > 0.0f) ? -45 : 45);
            }
            else if (side < 0.0f) {
                yaw += ((forward > 0.0f) ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            }
            else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        final double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        final double posX = forward * speed * cos + side * speed * sin;
        final double posZ = forward * speed * sin - side * speed * cos;
        return new double[] { posX, posZ };
    }
    
    public static boolean isPlayerMoving() {
        return PlayerUtil.mc.player.movementInput.moveStrafe != 0.0f || PlayerUtil.mc.player.movementInput.moveForward != 0.0f;
    }
    
    public static BlockPos getPlayerPosFloored() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static boolean isPhasing() {
        try {
            final AxisAlignedBB playerBoundingBox = PlayerUtil.mc.player.getEntityBoundingBox();
            for (int x = MathHelper.floor(playerBoundingBox.minX); x < MathHelper.floor(playerBoundingBox.maxX) + 1; ++x) {
                for (int y = MathHelper.floor(playerBoundingBox.minY); y < MathHelper.floor(playerBoundingBox.maxY) + 1; ++y) {
                    for (int z = MathHelper.floor(playerBoundingBox.minZ); z < MathHelper.floor(playerBoundingBox.maxZ) + 1; ++z) {
                        final Block block = PlayerUtil.mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                        if (block != null && !(block instanceof BlockAir)) {
                            AxisAlignedBB boundingBox = block.getCollisionBoundingBox(PlayerUtil.mc.world.getBlockState(new BlockPos(x, y, z)), (IBlockAccess)PlayerUtil.mc.world, new BlockPos(x, y, z)).offset((double)x, (double)y, (double)z);
                            if (block instanceof BlockHopper) {
                                boundingBox = new AxisAlignedBB((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
                            }
                            if (boundingBox != null && playerBoundingBox.intersects(boundingBox)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }
}
