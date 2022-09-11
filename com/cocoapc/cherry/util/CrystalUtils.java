//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;

public class CrystalUtils
{
    protected static Minecraft mc;
    public static final AxisAlignedBB CRYSTAL_AABB;
    
    protected static final Vec3d getVectorForRotation(final double d, final double d2) {
        final float f = MathHelper.cos((float)(-d2 * 0.01745329238474369 - 3.1415927410125732));
        final float f2 = MathHelper.sin((float)(-d2 * 0.01745329238474369 - 3.1415927410125732));
        final float f3 = -MathHelper.cos((float)(-d * 0.01745329238474369));
        final float f4 = MathHelper.sin((float)(-d * 0.01745329238474369));
        return new Vec3d((double)(f2 * f3), (double)f4, (double)(f * f3));
    }
    
    public static EnumActionResult placeCrystal(final BlockPos blockPos) {
        blockPos.offset(EnumFacing.DOWN);
        final double d = blockPos.getX() + 0.5 - CrystalUtils.mc.player.posX;
        final double d2 = blockPos.getY() + 0.5 - CrystalUtils.mc.player.posY - 0.5 - CrystalUtils.mc.player.getEyeHeight();
        final double d3 = blockPos.getZ() + 0.5 - CrystalUtils.mc.player.posZ;
        final double d4 = getDirection2D(d3, d);
        final double d5 = getDirection2D(d2, Math.sqrt(d * d + d3 * d3));
        final Vec3d vec3d = getVectorForRotation(-d5, d4 - 90.0);
        if (((ItemStack)CrystalUtils.mc.player.inventory.offHandInventory.get(0)).getItem().getClass().equals(Item.getItemById(426).getClass())) {
            return CrystalUtils.mc.playerController.processRightClickBlock(CrystalUtils.mc.player, CrystalUtils.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vec3d, EnumHand.OFF_HAND);
        }
        if (InventoryUtils.pickItem(426) != -1) {
            InventoryUtils.setSlot(InventoryUtils.pickItem(426));
            return CrystalUtils.mc.playerController.processRightClickBlock(CrystalUtils.mc.player, CrystalUtils.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vec3d, EnumHand.MAIN_HAND);
        }
        return EnumActionResult.FAIL;
    }
    
    protected static final double getDirection2D(final double d, final double d2) {
        double d3;
        if (d2 == 0.0) {
            d3 = ((d > 0.0) ? 90.0 : -90.0);
        }
        else {
            d3 = Math.atan(d / d2) * 57.2957796;
            if (d2 < 0.0) {
                d3 = ((d > 0.0) ? (d3 += 180.0) : ((d < 0.0) ? (d3 -= 180.0) : 180.0));
            }
        }
        return d3;
    }
    
    public static EnumActionResult doPlace(final BlockPos blockPos) {
        final double d = blockPos.getX() + 0.5 - CrystalUtils.mc.player.posX;
        final double d2 = blockPos.getY() - 1 + 0.5 - CrystalUtils.mc.player.posY - 0.5 - CrystalUtils.mc.player.getEyeHeight();
        final double d3 = blockPos.getZ() + 0.5 - CrystalUtils.mc.player.posZ;
        final double d4 = getDirection2D(d3, d);
        final double d5 = getDirection2D(d2, Math.sqrt(d * d + d3 * d3));
        final Vec3d vec3d = getVectorForRotation(-d5, d4 - 90.0);
        return CrystalUtils.mc.playerController.processRightClickBlock(CrystalUtils.mc.player, CrystalUtils.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vec3d, CrystalUtils.mc.player.getActiveHand());
    }
    
    public static boolean canPlace(final BlockPos blockPos) {
        return CrystalUtils.mc.world.getBlockState(blockPos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockEmptyDrops && CrystalUtils.mc.world.getBlockState(blockPos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockObsidian && CrystalUtils.mc.world.checkNoEntityCollision(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 2.0, 1.0).offset(blockPos), (Entity)null);
    }
    
    static {
        CrystalUtils.mc = Minecraft.getMinecraft();
        CRYSTAL_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 2.0, 1.0);
    }
}
