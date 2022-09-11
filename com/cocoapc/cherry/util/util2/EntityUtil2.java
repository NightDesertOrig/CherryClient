//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import net.minecraft.entity.player.*;
import java.util.function.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.state.*;
import javax.annotation.*;
import net.minecraft.block.*;
import net.minecraft.client.network.*;
import net.minecraft.network.*;
import com.cocoapc.cherry.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import com.cocoapc.cherry.mixin.mixins.*;
import net.minecraft.entity.item.*;
import java.util.stream.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.*;
import java.awt.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class EntityUtil2 implements Util
{
    public static final Vec3d[] antiDropOffsetList;
    public static final Vec3d[] platformOffsetList;
    public static final Vec3d[] legOffsetList;
    public static final Vec3d[] doubleLegOffsetList;
    public static final Vec3d[] OffsetList;
    public static final Vec3d[] headpiece;
    public static final Vec3d[] offsetsNoHead;
    public static final Vec3d[] antiStepOffsetList;
    public static final Vec3d[] antiScaffoldOffsetList;
    private static final BlockPos[] offsets;
    
    public static EntityPlayer getTargetDouble(final double range) {
        EntityPlayer currentTarget = null;
        for (int size = EntityUtil2.mc.world.playerEntities.size(), i = 0; i < size; ++i) {
            final EntityPlayer player = EntityUtil2.mc.world.playerEntities.get(i);
            if (!isntValid((Entity)player, range)) {
                if (currentTarget == null) {
                    currentTarget = player;
                }
                else if (EntityUtil2.mc.player.getDistanceSq((Entity)player) < EntityUtil2.mc.player.getDistanceSq((Entity)currentTarget)) {
                    currentTarget = player;
                }
            }
        }
        return currentTarget;
    }
    
    public static EntityLivingBase getTarget1(final boolean players, final boolean neutral, final boolean friends, final boolean hostile, final boolean passive, final double range, final int mode2) {
        EntityLivingBase entity2 = null;
        if (mode2 == 0) {
            entity2 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity1 -> isValid(entity1, players, neutral, friends, hostile, passive, range)).min(Comparator.comparing(entity1 -> EntityUtil2.mc.player.getPositionVector().squareDistanceTo(entity1.getPositionVector()))).orElse(null);
        }
        else if (mode2 == 1) {
            entity2 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity1 -> isValid(entity1, players, neutral, friends, hostile, passive, range)).map(entity1 -> entity1).min(Comparator.comparing((Function<? super T, ? extends Comparable>)EntityLivingBase::getHealth)).orElse(null);
        }
        else if (mode2 == 2) {
            entity2 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity1 -> isValid(entity1, players, neutral, friends, hostile, passive, range)).map(entity1 -> entity1).max(Comparator.comparing((Function<? super T, ? extends Comparable>)EntityLivingBase::getHealth)).orElse(null);
        }
        return entity2;
    }
    
    public static BlockPos getLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(EntityUtil2.mc.player.posX), Math.floor(EntityUtil2.mc.player.posY), Math.floor(EntityUtil2.mc.player.posZ));
    }
    
    public static boolean onMovementInput() {
        return EntityUtil2.mc.player.movementInput.moveForward != 0.0f || EntityUtil2.mc.player.movementInput.moveStrafe != 0.0f;
    }
    
    public void setSpeed(final double speed) {
        final EntityPlayerSP player = EntityUtil2.mc.player;
        player.motionX = -(Math.sin(getDirection()) * speed);
        player.motionZ = Math.cos(getDirection()) * speed;
    }
    
    public static double getDirection() {
        float forward = 0.0f;
        final EntityPlayerSP player = EntityUtil2.mc.player;
        float direction = player.rotationYaw;
        if (player.moveForward < 0.0f) {
            direction += 180.0f;
        }
        float n = 0.0f;
        if (player.moveForward >= 0.0f) {
            float n2 = 0.0f;
            if (player.moveForward > 0.0f) {
                n = 0.5f;
            }
            else {
                n2 = 1.0f;
            }
            forward = n2;
        }
        final float f2 = n;
        if (player.moveStrafing > 0.0f) {
            direction -= 90.0f * forward;
        }
        else if (player.moveStrafing < 0.0f) {
            direction += 90.0f * forward;
        }
        return direction * 0.017453292f;
    }
    
    public static double getMovementSpeed() {
        if (!EntityUtil2.mc.player.isPotionActive(MobEffects.SPEED)) {
            return getBaseMovementSpeed();
        }
        return getBaseMovementSpeed() * 1.0 + 0.06 * (Objects.requireNonNull(EntityUtil2.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
    }
    
    public static double getBaseMovementSpeed() {
        if (EntityUtil2.mc.player.isSneaking()) {
            return 0.064755;
        }
        return EntityUtil2.mc.player.isSprinting() ? 0.2806 : 0.21585;
    }
    
    public static boolean getSurroundWeakness(final Vec3d pos, final int feetMine, final int render) {
        switch (feetMine) {
            case 1: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)(raytrace.getX() - 2), (double)raytrace.getY(), (double)raytrace.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(raytrace.getX() - 2), (double)raytrace.getY(), (double)raytrace.getZ())) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-2, 1, 0)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block != Blocks.AIR && block != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-2, 0, 0)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 2: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)(raytrace.getX() + 2), (double)raytrace.getY(), (double)raytrace.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(raytrace.getX() + 2), (double)raytrace.getY(), (double)raytrace.getZ())) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(2, 1, 0)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block != Blocks.AIR && block != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(2, 0, 0)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 3: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() - 2)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() - 2))) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, -2)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block != Blocks.AIR && block != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, -2)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, -1, -2)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 4: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() + 2)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() + 2))) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, 2)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block != Blocks.AIR && block != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, 2)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, -1, 2)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 5: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)(raytrace.getX() - 1), (double)raytrace.getY(), (double)raytrace.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(raytrace.getX() - 1), (double)raytrace.getY(), (double)raytrace.getZ())) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-1, 1, 0)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 6: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)(raytrace.getX() + 1), (double)raytrace.getY(), (double)raytrace.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(raytrace.getX() + 1), (double)raytrace.getY(), (double)raytrace.getZ())) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(1, 1, 0)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 7: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() - 1)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() - 1))) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, -1)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 8: {
                final BlockPos raytrace = new BlockPos(pos);
                if (!BlockUtil2.canBlockBeSeen((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() + 1)) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)raytrace.getX(), (double)raytrace.getY(), (double)(raytrace.getZ() + 1))) > 3.0) {
                    return false;
                }
                final Block block = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, 1)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
        }
        switch (render) {
            case 1: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-2, 1, 0)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block2 != Blocks.AIR && block2 != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-2, 0, 0)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 2: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(2, 1, 0)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block2 != Blocks.AIR && block2 != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(2, 0, 0)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 3: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, -2)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block2 != Blocks.AIR && block2 != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, -2)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, -1, -2)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 4: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, 2)).getBlock();
                final Block blocka;
                final Block blockb;
                if ((block2 != Blocks.AIR && block2 != Blocks.FIRE) || ((blocka = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, 2)).getBlock()) != Blocks.AIR && blocka != Blocks.FIRE) || ((blockb = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, -1, 2)).getBlock()) != Blocks.OBSIDIAN && blockb != Blocks.BEDROCK)) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 5: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-1, 1, 0)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 6: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(1, 1, 0)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 7: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, -1)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
            case 8: {
                final Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 1, 1)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    break;
                }
                if (EntityUtil2.mc.world.getBlockState(new BlockPos(pos).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
                    break;
                }
                return true;
            }
        }
        return false;
    }
    
    public static boolean isPlayerInHole() {
        final BlockPos blockPos = getLocalPlayerPosFloored();
        final IBlockState blockState = EntityUtil2.mc.world.getBlockState(blockPos);
        if (blockState.getBlock() != Blocks.AIR) {
            return false;
        }
        if (EntityUtil2.mc.world.getBlockState(blockPos.up()).getBlock() != Blocks.AIR) {
            return false;
        }
        if (EntityUtil2.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.AIR) {
            return false;
        }
        final BlockPos[] touchingBlocks = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west() };
        int validHorizontalBlocks = 0;
        for (final BlockPos touching : touchingBlocks) {
            final IBlockState touchingState = EntityUtil2.mc.world.getBlockState(touching);
            if (touchingState.getBlock() != Blocks.AIR) {
                if (touchingState.isFullBlock()) {
                    ++validHorizontalBlocks;
                }
            }
        }
        return validHorizontalBlocks >= 4;
    }
    
    public static Boolean posEqualsBlock(final BlockPos pos, final jdk.nashorn.internal.ir.Block block) {
        return getBlockOnPos(pos).equals(block);
    }
    
    public static Block getBlockOnPos(final BlockPos pos) {
        return EntityUtil2.mc.world.getBlockState(pos).getBlock();
    }
    
    public static BlockPos getPositionVectors(final Entity entity, @Nullable final BlockPos blockPos) {
        final Vec3d vec3d = entity.getPositionVector();
        if (blockPos == null) {
            return new BlockPos(vec3d.x, vec3d.y, vec3d.z);
        }
        return new BlockPos(vec3d.x, vec3d.y, vec3d.z).add((Vec3i)blockPos);
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int height, final boolean floor, final boolean face) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector().add(0.0, 0.125, 0.0), height, floor, face);
    }
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d pos, final int height, final boolean floor, final boolean face) {
        final ArrayList<Vec3d> vec3ds = new ArrayList<Vec3d>();
        for (final Vec3d vector : getOffsets(height, floor, face)) {
            final BlockPos targetPos = new BlockPos(pos).add(vector.x, vector.y, vector.z);
            final Block block = EntityUtil2.mc.world.getBlockState(targetPos).getBlock();
            if (block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow) {
                vec3ds.add(vector);
            }
        }
        return vec3ds;
    }
    
    public static <T> Vec3d[] getUnsafeBlockArray(final Entity entity, final int height, final boolean floor, final boolean face) {
        final List<Vec3d> list = getUnsafeBlocks(entity, height, floor, face);
        final Vec3d[] array = new Vec3d[list.size()];
        return list.toArray(array);
    }
    
    public static <T> Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d pos, final int height, final boolean floor, final boolean face) {
        final List<Vec3d> list = getUnsafeBlocksFromVec3d(pos, height, floor, face);
        final Vec3d[] array = new Vec3d[list.size()];
        return list.toArray(array);
    }
    
    public static <T> Vec3d[] getOffsets(final int y, final boolean floor, final boolean face) {
        final List<Vec3d> offsets = getOffsetList(y, floor, face);
        final Vec3d[] array = new Vec3d[offsets.size()];
        return offsets.toArray(array);
    }
    
    public static int getEntityPing(final EntityPlayer player) {
        int ping = 0;
        try {
            ping = MathUtil.clamp(Objects.requireNonNull(EntityUtil2.mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime(), 1, 99999);
        }
        catch (NullPointerException ex) {}
        return ping;
    }
    
    public static List<Vec3d> getOffsetList(final int y, final boolean floor, final boolean face) {
        final ArrayList<Vec3d> offsets = new ArrayList<Vec3d>();
        if (face) {
            offsets.add(new Vec3d(-1.0, (double)y, 0.0));
            offsets.add(new Vec3d(1.0, (double)y, 0.0));
            offsets.add(new Vec3d(0.0, (double)y, -1.0));
            offsets.add(new Vec3d(0.0, (double)y, 1.0));
        }
        else {
            offsets.add(new Vec3d(-1.0, (double)y, 0.0));
        }
        if (floor) {
            offsets.add(new Vec3d(0.0, (double)(y - 1), 0.0));
        }
        return offsets;
    }
    
    public static boolean isSafe(final Entity entity, final int height, final boolean floor, final boolean face) {
        return getUnsafeBlocks(entity, height, floor, face).size() == 0;
    }
    
    public static void attackEntity(final Entity entity, final boolean packet, final boolean swingArm) {
        if (packet) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
        }
        if (swingArm) {
            EntityUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static void attackEntityCrystal(final Entity entity) {
        EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
    }
    
    public static double getDistPlayerToBlock(final Entity p_Entity, final BlockPos p_Pos) {
        return getDistance(p_Entity.posX, p_Entity.posY, p_Entity.posZ, p_Pos.getX(), p_Pos.getY(), p_Pos.getZ());
    }
    
    private static boolean isValid(final Entity entity, final boolean players, final boolean neutral, final boolean friends, final boolean hostile, final boolean passive, final double range) {
        if (entity.isDead) {
            return false;
        }
        if (!(entity instanceof EntityLivingBase) || entity == EntityUtil2.mc.player || entity.getDistanceSq((Entity)EntityUtil2.mc.player) > range * range) {
            return false;
        }
        if (entity instanceof EntityPlayer && players) {
            return friends || !Cherry.friendManager.isFriend((EntityPlayer)entity);
        }
        return (isHostileMob(entity) && hostile) || (isNeutralMob(entity) && neutral) || (isPassive(entity) && passive);
    }
    
    public static BlockPos getEntityPosFloored(final Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }
    
    public static ArrayList<BlockPos> getPos(final double posX, final double posY, final double posZ, final Entity entity) {
        final ArrayList<BlockPos> block = new ArrayList<BlockPos>();
        if (entity != null) {
            final AxisAlignedBB bb = (entity.ridingEntity != null) ? entity.ridingEntity.getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(posX, posY, posZ) : entity.getEntityBoundingBox().contract(0.01, 1.0, 0.01).offset(posX, posY, posZ);
            final int y = (int)bb.minY;
            for (int x = (int)Math.floor(bb.minX); x < Math.floor(bb.maxX) + 1.0; ++x) {
                for (int z = (int)Math.floor(bb.minZ); z < Math.floor(bb.maxZ) + 1.0; ++z) {
                    block.add(new BlockPos(x, y, z));
                }
            }
        }
        return block;
    }
    
    public static int toMode(final String mode2) {
        if (mode2.equalsIgnoreCase("Closest")) {
            return 0;
        }
        if (mode2.equalsIgnoreCase("Lowest Health")) {
            return 1;
        }
        if (mode2.equalsIgnoreCase("Highest Health")) {
            return 2;
        }
        throw new IllegalArgumentException(mode2);
    }
    
    public static double getRange(final Entity entity) {
        return EntityUtil2.mc.player.getPositionVector().add(0.0, (double)EntityUtil2.mc.player.eyeHeight, 0.0).distanceTo(entity.getPositionVector().add(0.0, entity.height / 2.0, 0.0));
    }
    
    public static EntityLivingBase getTarget(final boolean players, final boolean neutral, final boolean friends, final boolean hostile, final boolean passive, final double range, final int mode2) {
        EntityLivingBase entity2 = null;
        if (mode2 == 0) {
            entity2 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity1 -> isValid(entity1, players, neutral, friends, hostile, passive, range)).min(Comparator.comparing(entity1 -> EntityUtil2.mc.player.getPositionVector().squareDistanceTo(entity1.getPositionVector()))).orElse(null);
        }
        else if (mode2 == 1) {
            entity2 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity1 -> isValid(entity1, players, neutral, friends, hostile, passive, range)).map(entity1 -> entity1).min(Comparator.comparing((Function<? super T, ? extends Comparable>)EntityLivingBase::getHealth)).orElse(null);
        }
        else if (mode2 == 2) {
            entity2 = (EntityLivingBase)EntityUtil2.mc.world.loadedEntityList.stream().filter(entity1 -> isValid(entity1, players, neutral, friends, hostile, passive, range)).map(entity1 -> entity1).max(Comparator.comparing((Function<? super T, ? extends Comparable>)EntityLivingBase::getHealth)).orElse(null);
        }
        return entity2;
    }
    
    public static EntityPlayer getTarget2(final float range) {
        EntityPlayer currentTarget = null;
        for (int size = EntityUtil.mc.world.playerEntities.size(), i = 0; i < size; ++i) {
            final EntityPlayer player = EntityUtil.mc.world.playerEntities.get(i);
            if (!EntityUtil.isntValid((Entity)player, (double)range)) {
                if (currentTarget == null) {
                    currentTarget = player;
                }
                else if (EntityUtil.mc.player.getDistanceSq((Entity)player) < EntityUtil.mc.player.getDistanceSq((Entity)currentTarget)) {
                    currentTarget = player;
                }
            }
        }
        return currentTarget;
    }
    
    public static double getDistance(final double p_X, final double p_Y, final double p_Z, final double x, final double y, final double z) {
        final double d0 = p_X - x;
        final double d2 = p_Y - y;
        final double d3 = p_Z - z;
        return MathHelper.sqrt(d0 * d0 + d2 * d2 + d3 * d3);
    }
    
    public static List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plusY) {
        final ArrayList<BlockPos> circleBlocks = new ArrayList<BlockPos>();
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
                        final BlockPos l = new BlockPos(x, y + plusY, z);
                        circleBlocks.add(l);
                    }
                    ++y;
                }
            }
        }
        return circleBlocks;
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float time) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * time, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * time, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * time);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float partialTicks) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, partialTicks));
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float partialTicks) {
        return getInterpolatedPos(entity, partialTicks).subtract(EntityUtil2.mc.getRenderManager().renderPosX, EntityUtil2.mc.getRenderManager().renderPosY, EntityUtil2.mc.getRenderManager().renderPosZ);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Vec3d vec) {
        return new Vec3d(vec.x, vec.y, vec.z).subtract(EntityUtil2.mc.getRenderManager().renderPosX, EntityUtil2.mc.getRenderManager().renderPosY, EntityUtil2.mc.getRenderManager().renderPosZ);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double x, final double y, final double z) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * x, (entity.posY - entity.lastTickPosY) * y, (entity.posZ - entity.lastTickPosZ) * z);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final Vec3d vec) {
        return getInterpolatedAmount(entity, vec.x, vec.y, vec.z);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final float partialTicks) {
        return getInterpolatedAmount(entity, partialTicks, partialTicks, partialTicks);
    }
    
    public static boolean isPassive(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }
    
    public static boolean isSafe(final Entity entity, final int height, final boolean floor) {
        return getUnsafeBlocks(entity, height, floor).size() == 0;
    }
    
    public static boolean stopSneaking(final boolean isSneaking) {
        if (isSneaking && EntityUtil2.mc.player != null) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }
    
    public static boolean isSafe(final Entity entity) {
        return isSafe(entity, 0, false);
    }
    
    public static BlockPos getPlayerPos(final EntityPlayer player) {
        return new BlockPos(Math.floor(player.posX), Math.floor(player.posY), Math.floor(player.posZ));
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int height, final boolean floor) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector(), height, floor);
    }
    
    public static boolean isMobAggressive(final Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        }
        else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtil2.mc.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return isHostileMob(entity);
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    public static boolean isProjectile(final Entity entity) {
        return entity instanceof EntityShulkerBullet || entity instanceof EntityFireball;
    }
    
    public static boolean isVehicle(final Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }
    
    public static boolean isFriendlyMob(final Entity entity) {
        return (entity.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(entity)) || entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof EntityVillager || entity instanceof EntityIronGolem || (isNeutralMob(entity) && !isMobAggressive(entity));
    }
    
    public static boolean isHostileMob(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity);
    }
    
    public static double[] calcLooking(final double px, final double py, final double pz, final EntityPlayer me) {
        double dirx = me.posX - px;
        double diry = me.posY - py;
        double dirz = me.posZ - pz;
        final double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
        double pitch = Math.asin(diry /= len);
        dirz /= len;
        double yaw = Math.atan2(dirz, dirx /= len);
        pitch = pitch * 180.0 / 3.141592653589793;
        yaw = yaw * 180.0 / 3.141592653589793;
        final double[] array = new double[2];
        yaw = (array[0] = yaw + 90.0);
        array[1] = pitch;
        return array;
    }
    
    public static void swingArmNoPacket(final EnumHand hand, final EntityLivingBase entity) {
        final ItemStack stack = entity.getHeldItem(hand);
        if (!stack.isEmpty() && stack.getItem().onEntitySwing(entity, stack)) {
            return;
        }
        if (!entity.isSwingInProgress || entity.swingProgressInt >= ((IEntityLivingBase)entity).getArmSwingAnimationEnd() / 2 || entity.swingProgressInt < 0) {
            entity.swingProgressInt = -1;
            entity.isSwingInProgress = true;
            entity.swingingHand = hand;
        }
    }
    
    public static void OffhandAttack(final Entity entity, final boolean packet, final boolean swingArm) {
        if (packet) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
        }
        if (swingArm) {
            EntityUtil2.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }
    
    public static boolean isCrystalAtFeet(final EntityEnderCrystal crystal, final double range) {
        for (final EntityPlayer player : EntityUtil2.mc.world.playerEntities) {
            if (EntityUtil2.mc.player.getDistanceSq((Entity)player) <= range * range) {
                if (Cherry.friendManager.isFriend(player)) {
                    continue;
                }
                for (final Vec3d vec : EntityUtil2.doubleLegOffsetList) {
                    if (new BlockPos(player.getPositionVector()).add(vec.x, vec.y, vec.z) == crystal.getPosition()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d pos, final int height, final boolean floor) {
        final ArrayList<Vec3d> vec3ds = new ArrayList<Vec3d>();
        for (final Vec3d vector : getOffsets(height, floor)) {
            final BlockPos targetPos = new BlockPos(pos).add(vector.x, vector.y, vector.z);
            final Block block = EntityUtil2.mc.world.getBlockState(targetPos).getBlock();
            if (block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow) {
                vec3ds.add(vector);
            }
        }
        return vec3ds;
    }
    
    public static boolean isInHole(final Entity entity) {
        return isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
    
    public static boolean isBlockValid(final BlockPos blockPos) {
        return isBedrockHole(blockPos) || isObbyHole(blockPos) || isBothHole(blockPos);
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array;
        final BlockPos[] touchingBlocks = array = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (final BlockPos pos : array) {
            final IBlockState touchingState = EntityUtil2.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array;
        final BlockPos[] touchingBlocks = array = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (final BlockPos pos : array) {
            final IBlockState touchingState = EntityUtil2.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array;
        final BlockPos[] touchingBlocks = array = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (final BlockPos pos : array) {
            final IBlockState touchingState = EntityUtil2.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || (touchingState.getBlock() != Blocks.BEDROCK && touchingState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static <T> Vec3d[] getUnsafeBlockArray(final Entity entity, final int height, final boolean floor) {
        final List<Vec3d> list = getUnsafeBlocks(entity, height, floor);
        final Vec3d[] array = new Vec3d[list.size()];
        return list.toArray(array);
    }
    
    public static <T> Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d pos, final int height, final boolean floor) {
        final List<Vec3d> list = getUnsafeBlocksFromVec3d(pos, height, floor);
        final Vec3d[] array = new Vec3d[list.size()];
        return list.toArray(array);
    }
    
    public static double getDst(final Vec3d vec) {
        return EntityUtil2.mc.player.getPositionVector().distanceTo(vec);
    }
    
    public static boolean isTrapped(final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop) {
        return getUntrappedBlocks(player, antiScaffold, antiStep, legs, platform, antiDrop).size() == 0;
    }
    
    public static boolean isTrappedExtended(final int extension, final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean raytrace, final boolean noScaffoldExtend, final boolean face) {
        return getUntrappedBlocksExtended(extension, player, antiScaffold, antiStep, legs, platform, antiDrop, raytrace, noScaffoldExtend, face).size() == 0;
    }
    
    public static boolean isTrapped(final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean face) {
        return getUntrappedBlocks(player, antiScaffold, antiStep, legs, platform, antiDrop).size() == 0;
    }
    
    public static List<Vec3d> getUntrappedBlocks(final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop) {
        final ArrayList<Vec3d> vec3ds = new ArrayList<Vec3d>();
        if (!antiStep && getUnsafeBlocks((Entity)player, 2, false).size() == 4) {
            vec3ds.addAll(getUnsafeBlocks((Entity)player, 2, false));
        }
        for (int i = 0; i < getTrapOffsets(antiScaffold, antiStep, legs, platform, antiDrop).length; ++i) {
            final Vec3d vector = getTrapOffsets(antiScaffold, antiStep, legs, platform, antiDrop)[i];
            final BlockPos targetPos = new BlockPos(player.getPositionVector()).add(vector.x, vector.y, vector.z);
            final Block block = EntityUtil2.mc.world.getBlockState(targetPos).getBlock();
            if (block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow) {
                vec3ds.add(vector);
            }
        }
        return vec3ds;
    }
    
    public static boolean isInWater(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double y = entity.posY + 0.01;
        for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); ++x) {
            for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, (int)y, z);
                if (EntityUtil2.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isDrivenByPlayer(final Entity entityIn) {
        return EntityUtil2.mc.player != null && entityIn != null && entityIn.equals((Object)EntityUtil2.mc.player.getRidingEntity());
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    public static boolean isAboveWater(final Entity entity) {
        return isAboveWater(entity, false);
    }
    
    public static boolean isAboveWater(final Entity entity, final boolean packet) {
        if (entity == null) {
            return false;
        }
        final double y = entity.posY - (packet ? 0.03 : (isPlayer(entity) ? 0.2 : 0.5));
        for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); ++x) {
            for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);
                if (EntityUtil2.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List<Vec3d> getUntrappedBlocksExtended(final int extension, final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean raytrace, final boolean noScaffoldExtend, final boolean face) {
        final ArrayList<Vec3d> placeTargets = new ArrayList<Vec3d>();
        if (extension == 1) {
            placeTargets.addAll(targets(player.getPositionVector(), antiScaffold, antiStep, legs, platform, antiDrop, raytrace));
        }
        else {
            int extend = 1;
            for (final Vec3d vec3d : MathUtil.getBlockBlocks((Entity)player)) {
                if (extend > extension) {
                    break;
                }
                placeTargets.addAll(targets(vec3d, !noScaffoldExtend, antiStep, legs, platform, antiDrop, raytrace));
                ++extend;
            }
        }
        final ArrayList<Vec3d> removeList = new ArrayList<Vec3d>();
        for (final Vec3d vec3d : placeTargets) {
            final BlockPos pos = new BlockPos(vec3d);
            if (BlockUtil.isPositionPlaceable(pos, raytrace) != -1) {
                continue;
            }
            removeList.add(vec3d);
        }
        for (final Vec3d vec3d : removeList) {
            placeTargets.remove(vec3d);
        }
        return placeTargets;
    }
    
    public static List<Vec3d> targets(final Vec3d vec3d, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean raytrace) {
        final ArrayList<Vec3d> placeTargets = new ArrayList<Vec3d>();
        if (antiDrop) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiDropOffsetList));
        }
        if (platform) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil2.platformOffsetList));
        }
        if (legs) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil2.legOffsetList));
        }
        Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil2.OffsetList));
        if (antiStep) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiStepOffsetList));
        }
        else {
            final List<Vec3d> vec3ds = getUnsafeBlocksFromVec3d(vec3d, 2, false);
            if (vec3ds.size() == 4) {
                for (final Vec3d vector : vec3ds) {
                    final BlockPos position = new BlockPos(vec3d).add(vector.x, vector.y, vector.z);
                    switch (BlockUtil.isPositionPlaceable(position, raytrace)) {
                        case -1:
                        case 1:
                        case 2: {
                            continue;
                        }
                        case 3: {
                            placeTargets.add(vec3d.add(vector));
                            break;
                        }
                    }
                    if (antiScaffold) {
                        Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiScaffoldOffsetList));
                    }
                    return placeTargets;
                }
            }
        }
        if (antiScaffold) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil2.antiScaffoldOffsetList));
        }
        return placeTargets;
    }
    
    public static List<Vec3d> getOffsetList(final int y, final boolean floor) {
        final ArrayList<Vec3d> offsets = new ArrayList<Vec3d>();
        offsets.add(new Vec3d(-1.0, (double)y, 0.0));
        offsets.add(new Vec3d(1.0, (double)y, 0.0));
        offsets.add(new Vec3d(0.0, (double)y, -1.0));
        offsets.add(new Vec3d(0.0, (double)y, 1.0));
        if (floor) {
            offsets.add(new Vec3d(0.0, (double)(y - 1), 0.0));
        }
        return offsets;
    }
    
    public static Vec3d[] getOffsets(final int y, final boolean floor) {
        final List<Vec3d> offsets = getOffsetList(y, floor);
        final Vec3d[] array = new Vec3d[offsets.size()];
        return offsets.toArray(new Vec3d[0]);
    }
    
    public static Vec3d[] getTrapOffsets(final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop) {
        final List<Vec3d> offsets = getTrapOffsetsList(antiScaffold, antiStep, legs, platform, antiDrop);
        final Vec3d[] array = new Vec3d[offsets.size()];
        return offsets.toArray(new Vec3d[0]);
    }
    
    public static List<Vec3d> getTrapOffsetsList(final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop) {
        final ArrayList<Vec3d> offsets = new ArrayList<Vec3d>(getOffsetList(1, false));
        offsets.add(new Vec3d(0.0, 2.0, 0.0));
        if (antiScaffold) {
            offsets.add(new Vec3d(0.0, 3.0, 0.0));
        }
        if (antiStep) {
            offsets.addAll(getOffsetList(2, false));
        }
        if (legs) {
            offsets.addAll(getOffsetList(0, false));
        }
        if (platform) {
            offsets.addAll(getOffsetList(-1, false));
            offsets.add(new Vec3d(0.0, -1.0, 0.0));
        }
        if (antiDrop) {
            offsets.add(new Vec3d(0.0, -2.0, 0.0));
        }
        return offsets;
    }
    
    public static Vec3d[] getHeightOffsets(final int min, final int max) {
        final ArrayList<Vec3d> offsets = new ArrayList<Vec3d>();
        for (int i = min; i <= max; ++i) {
            offsets.add(new Vec3d(0.0, (double)i, 0.0));
        }
        final Vec3d[] array = new Vec3d[offsets.size()];
        return offsets.toArray(new Vec3d[0]);
    }
    
    public static BlockPos getRoundedBlockPos(final Entity entity) {
        return new BlockPos(MathUtil.roundVec(entity.getPositionVector(), 0));
    }
    
    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }
    
    public static boolean isAlive(final Entity entity) {
        return isLiving(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    
    public static boolean isDead(final Entity entity) {
        return !isAlive(entity);
    }
    
    public static float getHealth(final Entity entity) {
        if (isLiving(entity)) {
            final EntityLivingBase livingBase = (EntityLivingBase)entity;
            return livingBase.getHealth() + livingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }
    
    public static float getHealth(final Entity entity, final boolean absorption) {
        if (isLiving(entity)) {
            final EntityLivingBase livingBase = (EntityLivingBase)entity;
            return livingBase.getHealth() + (absorption ? livingBase.getAbsorptionAmount() : 0.0f);
        }
        return 0.0f;
    }
    
    public static boolean canEntityFeetBeSeen(final Entity entityIn) {
        return EntityUtil2.mc.world.rayTraceBlocks(new Vec3d(EntityUtil2.mc.player.posX, EntityUtil2.mc.player.posX + EntityUtil2.mc.player.getEyeHeight(), EntityUtil2.mc.player.posZ), new Vec3d(entityIn.posX, entityIn.posY, entityIn.posZ), false, true, false) == null;
    }
    
    public static List<EntityPlayer> getNearbyPlayers(final double d) {
        if (EntityUtil2.mc.world.getLoadedEntityList().size() == 0) {
            return null;
        }
        final List<EntityPlayer> list = (List<EntityPlayer>)EntityUtil2.mc.world.playerEntities.stream().filter(entityPlayer -> EntityUtil2.mc.player != entityPlayer).filter(entityPlayer -> EntityUtil2.mc.player.getDistance(entityPlayer) <= d).filter(entityPlayer -> getHealth(entityPlayer) >= 0.0f).collect(Collectors.toList());
        list.removeIf(entityPlayer -> Cherry.friendManager.isFriend(entityPlayer.getName()));
        return list;
    }
    
    public static ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>> getCityablePlayers() {
        final ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>> arrayList = new ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>>();
        for (final EntityPlayer entity : Objects.requireNonNull(getNearbyPlayers(6.0)).stream().filter(entityPlayer -> !Cherry.friendManager.isFriend(entityPlayer)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList())) {
            final ArrayList<BlockPos> arrayList2 = new ArrayList<BlockPos>();
            for (int i = 0; i < 4; ++i) {
                final BlockPos blockPos = getPositionVectors((Entity)entity, EntityUtil2.offsets[i]);
                if (EntityUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) {
                    boolean bl = false;
                    switch (i) {
                        case 0: {
                            bl = BlockUtil.canPlaceCrystal(blockPos.north(2), true, false);
                            break;
                        }
                        case 1: {
                            bl = BlockUtil.canPlaceCrystal(blockPos.east(2), true, false);
                            break;
                        }
                        case 2: {
                            bl = BlockUtil.canPlaceCrystal(blockPos.south(2), true, false);
                            break;
                        }
                        case 3: {
                            bl = BlockUtil.canPlaceCrystal(blockPos.west(2), true, false);
                            break;
                        }
                    }
                    if (bl) {
                        arrayList2.add(blockPos);
                    }
                }
            }
            if (arrayList2.isEmpty()) {
                continue;
            }
            arrayList.add((PairUtil<EntityPlayer, ArrayList<BlockPos>>)new PairUtil((Object)entity, (Object)arrayList2));
        }
        return arrayList;
    }
    
    public static boolean isntValid(final Entity entity, final double range) {
        return entity == null || isDead(entity) || entity.equals((Object)EntityUtil2.mc.player) || (entity instanceof EntityPlayer && Cherry.friendManager.isFriend(entity.getName())) || EntityUtil2.mc.player.getDistanceSq(entity) > MathUtil.square(range);
    }
    
    public static boolean isValid(final Entity entity, final double range) {
        return !isntValid(entity, range);
    }
    
    public static boolean holdingWeapon(final EntityPlayer player) {
        return player.getHeldItemMainhand().getItem() instanceof ItemSword || player.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }
    
    public static double getMaxSpeed() {
        double maxModifier = 0.2873;
        if (EntityUtil2.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(1)))) {
            maxModifier *= 1.0 + 0.2 * (Objects.requireNonNull(EntityUtil2.mc.player.getActivePotionEffect((Potion)Objects.requireNonNull(Potion.getPotionById(1)))).getAmplifier() + 1);
        }
        return maxModifier;
    }
    
    public static void mutliplyEntitySpeed(final Entity entity, final double multiplier) {
        if (entity != null) {
            entity.motionX *= multiplier;
            entity.motionZ *= multiplier;
        }
    }
    
    public static boolean isEntityMoving(final Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return EntityUtil2.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindRight.isKeyDown();
        }
        return entity.motionX != 0.0 || entity.motionY != 0.0 || entity.motionZ != 0.0;
    }
    
    public static double getEntitySpeed(final Entity entity) {
        if (entity != null) {
            final double distTraveledLastTickX = entity.posX - entity.prevPosX;
            final double distTraveledLastTickZ = entity.posZ - entity.prevPosZ;
            final double speed = MathHelper.sqrt(distTraveledLastTickX * distTraveledLastTickX + distTraveledLastTickZ * distTraveledLastTickZ);
            return speed * 20.0;
        }
        return 0.0;
    }
    
    public static boolean is32k(final ItemStack stack) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack) >= 1000;
    }
    
    public static void moveEntityStrafe(final double speed, final Entity entity) {
        if (entity != null) {
            final MovementInput movementInput = EntityUtil2.mc.player.movementInput;
            double forward = movementInput.moveForward;
            double strafe = movementInput.moveStrafe;
            float yaw = EntityUtil2.mc.player.rotationYaw;
            if (forward == 0.0 && strafe == 0.0) {
                entity.motionX = 0.0;
                entity.motionZ = 0.0;
            }
            else {
                if (forward != 0.0) {
                    if (strafe > 0.0) {
                        yaw += ((forward > 0.0) ? -45 : 45);
                    }
                    else if (strafe < 0.0) {
                        yaw += ((forward > 0.0) ? 45 : -45);
                    }
                    strafe = 0.0;
                    if (forward > 0.0) {
                        forward = 1.0;
                    }
                    else if (forward < 0.0) {
                        forward = -1.0;
                    }
                }
                entity.motionX = forward * speed * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0f));
                entity.motionZ = forward * speed * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * speed * Math.cos(Math.toRadians(yaw + 90.0f));
            }
        }
    }
    
    public static boolean rayTraceHitCheck(final Entity entity, final boolean shouldCheck) {
        return !shouldCheck || EntityUtil2.mc.player.canEntityBeSeen(entity);
    }
    
    public static Color getColor(final Entity entity, final int red, final int green, final int blue, final int alpha, final boolean colorFriends) {
        Color color = new Color(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);
        if (entity instanceof EntityPlayer && colorFriends && Cherry.friendManager.isFriend((EntityPlayer)entity)) {
            color = new Color(0.33333334f, 1.0f, 1.0f, alpha / 255.0f);
        }
        return color;
    }
    
    public static boolean isMoving(final EntityPlayerSP player) {
        return EntityUtil2.mc.player.moveForward != 0.0 || EntityUtil2.mc.player.moveStrafing != 0.0;
    }
    
    public static EntityPlayer getClosestEnemy(final double distance) {
        EntityPlayer closest = null;
        for (final EntityPlayer player : EntityUtil2.mc.world.playerEntities) {
            if (isntValid((Entity)player, distance)) {
                continue;
            }
            if (closest == null) {
                closest = player;
            }
            else {
                if (EntityUtil2.mc.player.getDistanceSq((Entity)player) >= EntityUtil2.mc.player.getDistanceSq((Entity)closest)) {
                    continue;
                }
                closest = player;
            }
        }
        return closest;
    }
    
    public static boolean checkCollide() {
        return !EntityUtil2.mc.player.isSneaking() && (EntityUtil2.mc.player.getRidingEntity() == null || EntityUtil2.mc.player.getRidingEntity().fallDistance < 3.0f) && EntityUtil2.mc.player.fallDistance < 3.0f;
    }
    
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((EntityUtil2.mc.player.getRidingEntity() != null) ? EntityUtil2.mc.player.getRidingEntity().posX : EntityUtil2.mc.player.posX, (EntityUtil2.mc.player.getRidingEntity() != null) ? EntityUtil2.mc.player.getRidingEntity().posY : EntityUtil2.mc.player.posY, (EntityUtil2.mc.player.getRidingEntity() != null) ? EntityUtil2.mc.player.getRidingEntity().posZ : EntityUtil2.mc.player.posZ);
    }
    
    public static double[] forward(final double speed) {
        float forward = EntityUtil2.mc.player.movementInput.moveForward;
        float side = EntityUtil2.mc.player.movementInput.moveStrafe;
        float yaw = EntityUtil2.mc.player.prevRotationYaw + (EntityUtil2.mc.player.rotationYaw - EntityUtil2.mc.player.prevRotationYaw) * EntityUtil2.mc.getRenderPartialTicks();
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
    
    public static Map<String, Integer> getTextRadarPlayers() {
        final Map<String, Integer> output = new HashMap<String, Integer>();
        final DecimalFormat dfHealth = new DecimalFormat("#.#");
        dfHealth.setRoundingMode(RoundingMode.CEILING);
        final DecimalFormat dfDistance = new DecimalFormat("#.#");
        dfDistance.setRoundingMode(RoundingMode.CEILING);
        final StringBuilder healthSB = new StringBuilder();
        final StringBuilder distanceSB = new StringBuilder();
        for (final EntityPlayer player : EntityUtil2.mc.world.playerEntities) {
            if (!player.isInvisible()) {
                if (player.getName().equals(EntityUtil2.mc.player.getName())) {
                    continue;
                }
                final int hpRaw = (int)getHealth((Entity)player);
                final String hp = dfHealth.format(hpRaw);
                healthSB.append("\u00c2§");
                if (hpRaw >= 20) {
                    healthSB.append("a");
                }
                else if (hpRaw >= 10) {
                    healthSB.append("e");
                }
                else if (hpRaw >= 5) {
                    healthSB.append("6");
                }
                else {
                    healthSB.append("c");
                }
                healthSB.append(hp);
                final int distanceInt = (int)EntityUtil2.mc.player.getDistance((Entity)player);
                final String distance = dfDistance.format(distanceInt);
                distanceSB.append("\u00c2§");
                if (distanceInt >= 25) {
                    distanceSB.append("a");
                }
                else if (distanceInt > 10) {
                    distanceSB.append("6");
                }
                else {
                    distanceSB.append("c");
                }
                distanceSB.append(distance);
                healthSB.setLength(0);
                distanceSB.setLength(0);
            }
        }
        return output;
    }
    
    static {
        antiDropOffsetList = new Vec3d[] { new Vec3d(0.0, -2.0, 0.0) };
        platformOffsetList = new Vec3d[] { new Vec3d(0.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(1.0, -1.0, 0.0) };
        legOffsetList = new Vec3d[] { new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0) };
        doubleLegOffsetList = new Vec3d[] { new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(0.0, 0.0, 2.0) };
        OffsetList = new Vec3d[] { new Vec3d(1.0, 1.0, 0.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, 0.0) };
        headpiece = new Vec3d[] { new Vec3d(0.0, 2.0, 0.0) };
        offsetsNoHead = new Vec3d[] { new Vec3d(1.0, 1.0, 0.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 1.0, -1.0) };
        antiStepOffsetList = new Vec3d[] { new Vec3d(-1.0, 2.0, 0.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(0.0, 2.0, -1.0) };
        antiScaffoldOffsetList = new Vec3d[] { new Vec3d(0.0, 3.0, 0.0) };
        offsets = new BlockPos[] { new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
}
