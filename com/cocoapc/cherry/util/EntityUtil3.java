//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util;

import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.passive.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import com.cocoapc.cherry.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.*;
import java.awt.*;
import com.cocoapc.cherry.features.modules.combat.*;
import net.minecraft.util.math.*;
import java.util.*;
import java.text.*;
import java.math.*;
import com.cocoapc.cherry.features.modules.client.*;

public class EntityUtil3 implements Util
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
    
    public static void attackEntity(final Entity entity, final boolean packet, final boolean swingArm) {
        if (packet) {
            EntityUtil3.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil3.mc.playerController.attackEntity((EntityPlayer)EntityUtil3.mc.player, entity);
        }
        if (swingArm) {
            EntityUtil3.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static void OffhandAttack(final Entity entity, final boolean packet, final boolean swingArm) {
        if (packet) {
            EntityUtil3.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil3.mc.playerController.attackEntity((EntityPlayer)EntityUtil3.mc.player, entity);
        }
        if (swingArm) {
            EntityUtil3.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float time) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * time, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * time, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * time);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float partialTicks) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, partialTicks));
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float partialTicks) {
        return getInterpolatedPos(entity, partialTicks).subtract(EntityUtil3.mc.getRenderManager().renderPosX, EntityUtil3.mc.getRenderManager().renderPosY, EntityUtil3.mc.getRenderManager().renderPosZ);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Vec3d vec) {
        return new Vec3d(vec.x, vec.y, vec.z).subtract(EntityUtil3.mc.getRenderManager().renderPosX, EntityUtil3.mc.getRenderManager().renderPosY, EntityUtil3.mc.getRenderManager().renderPosZ);
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
    
    public static boolean isSafe(final Entity entity, final int height, final boolean floor, final boolean face) {
        return getUnsafeBlocks(entity, height, floor, face).size() == 0;
    }
    
    public static boolean stopSneaking(final boolean isSneaking) {
        if (isSneaking && EntityUtil3.mc.player != null) {
            EntityUtil3.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil3.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }
    
    public static boolean isSafe(final Entity entity) {
        return isSafe(entity, 0, false, true);
    }
    
    public static BlockPos getPlayerPos(final EntityPlayer player) {
        return new BlockPos(Math.floor(player.posX), Math.floor(player.posY), Math.floor(player.posZ));
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int height, final boolean floor, final boolean face) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector(), height, floor, face);
    }
    
    public static boolean isMobAggressive(final Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        }
        else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtil3.mc.player.equals((Object)((EntityWolf)entity).getOwner());
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
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d pos, final int height, final boolean floor, final boolean face) {
        final List<Vec3d> vec3ds = new ArrayList<Vec3d>();
        for (final Vec3d vector : getOffsets(height, floor, face)) {
            final BlockPos targetPos = new BlockPos(pos).add(vector.x, vector.y, vector.z);
            final Block block = EntityUtil3.mc.world.getBlockState(targetPos).getBlock();
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
    
    public static boolean isCrystalAtFeet(final EntityEnderCrystal crystal, final double range) {
        for (final EntityPlayer player : EntityUtil3.mc.world.playerEntities) {
            if (EntityUtil3.mc.player.getDistanceSq((Entity)player) > range * range) {
                continue;
            }
            if (Cherry.friendManager.isFriend(player)) {
                continue;
            }
            for (final Vec3d vec : EntityUtil3.doubleLegOffsetList) {
                if (new BlockPos(player.getPositionVector()).add(vec.x, vec.y, vec.z) == crystal.getPosition()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks;
        final BlockPos[] array2;
        final BlockPos[] array = array2 = (touchingBlocks = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() });
        for (final BlockPos pos : array2) {
            final IBlockState touchingState = EntityUtil3.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks;
        final BlockPos[] array2;
        final BlockPos[] array = array2 = (touchingBlocks = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() });
        for (final BlockPos pos : array2) {
            final IBlockState touchingState = EntityUtil3.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] touchingBlocks;
        final BlockPos[] array2;
        final BlockPos[] array = array2 = (touchingBlocks = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() });
        for (final BlockPos pos : array2) {
            final IBlockState touchingState = EntityUtil3.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || (touchingState.getBlock() != Blocks.BEDROCK && touchingState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static Vec3d[] getUnsafeBlockArray(final Entity entity, final int height, final boolean floor, final boolean face) {
        final List<Vec3d> list = getUnsafeBlocks(entity, height, floor, face);
        final Vec3d[] array = new Vec3d[list.size()];
        return list.toArray(array);
    }
    
    public static Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d pos, final int height, final boolean floor, final boolean face) {
        final List<Vec3d> list = getUnsafeBlocksFromVec3d(pos, height, floor, face);
        final Vec3d[] array = new Vec3d[list.size()];
        return list.toArray(array);
    }
    
    public static double getDst(final Vec3d vec) {
        return EntityUtil3.mc.player.getPositionVector().distanceTo(vec);
    }
    
    public static boolean isTrapped(final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean face) {
        return getUntrappedBlocks(player, antiScaffold, antiStep, legs, platform, antiDrop, face).size() == 0;
    }
    
    public static boolean isTrappedExtended(final int extension, final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean raytrace, final boolean noScaffoldExtend, final boolean face) {
        return getUntrappedBlocksExtended(extension, player, antiScaffold, antiStep, legs, platform, antiDrop, raytrace, noScaffoldExtend, face).size() == 0;
    }
    
    public static List<Vec3d> getUntrappedBlocks(final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean face) {
        final List<Vec3d> vec3ds = new ArrayList<Vec3d>();
        if (!antiStep && getUnsafeBlocks((Entity)player, 2, false, face).size() == 4) {
            vec3ds.addAll(getUnsafeBlocks((Entity)player, 2, false, face));
        }
        for (int i = 0; i < getTrapOffsets(antiScaffold, antiStep, legs, platform, antiDrop, face).length; ++i) {
            final Vec3d vector = getTrapOffsets(antiScaffold, antiStep, legs, platform, antiDrop, face)[i];
            final BlockPos targetPos = new BlockPos(player.getPositionVector()).add(vector.x, vector.y, vector.z);
            final Block block = EntityUtil3.mc.world.getBlockState(targetPos).getBlock();
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
                if (EntityUtil3.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isDrivenByPlayer(final Entity entityIn) {
        return EntityUtil3.mc.player != null && entityIn != null && entityIn.equals((Object)EntityUtil3.mc.player.getRidingEntity());
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
                if (EntityUtil3.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List<Vec3d> getUntrappedBlocksExtended(final int extension, final EntityPlayer player, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean raytrace, final boolean noScaffoldExtend, final boolean face) {
        final List<Vec3d> placeTargets = new ArrayList<Vec3d>();
        if (extension == 1) {
            placeTargets.addAll(targets(player.getPositionVector(), antiScaffold, antiStep, legs, platform, antiDrop, raytrace, face));
        }
        else {
            int extend = 1;
            for (final Vec3d vec3d : MathUtil.getBlockBlocks((Entity)player)) {
                if (extend > extension) {
                    break;
                }
                placeTargets.addAll(targets(vec3d, !noScaffoldExtend, antiStep, legs, platform, antiDrop, raytrace, face));
                ++extend;
            }
        }
        final List<Vec3d> removeList = new ArrayList<Vec3d>();
        for (final Vec3d vec3d : placeTargets) {
            final BlockPos pos = new BlockPos(vec3d);
            if (BlockUtil.isPositionPlaceable(pos, raytrace) == -1) {
                removeList.add(vec3d);
            }
        }
        for (final Vec3d vec3d : removeList) {
            placeTargets.remove(vec3d);
        }
        return placeTargets;
    }
    
    public static List<Vec3d> targets(final Vec3d vec3d, final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean raytrace, final boolean face) {
        final List<Vec3d> placeTargets = new ArrayList<Vec3d>();
        if (antiDrop) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil.antiDropOffsetList));
        }
        if (platform) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil.platformOffsetList));
        }
        if (legs) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil.legOffsetList));
        }
        Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil.OffsetList));
        if (antiStep) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil.antiStepOffsetList));
        }
        else {
            final List<Vec3d> vec3ds = getUnsafeBlocksFromVec3d(vec3d, 2, false, face);
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
                    break;
                }
            }
        }
        if (antiScaffold) {
            Collections.addAll(placeTargets, BlockUtil.convertVec3ds(vec3d, EntityUtil.antiScaffoldOffsetList));
        }
        if (!face) {
            final List<Vec3d> offsets = new ArrayList<Vec3d>();
            offsets.add(new Vec3d(1.0, 1.0, 0.0));
            offsets.add(new Vec3d(0.0, 1.0, -1.0));
            offsets.add(new Vec3d(0.0, 1.0, 1.0));
            final Vec3d[] array = new Vec3d[offsets.size()];
            placeTargets.removeAll(Arrays.asList(BlockUtil.convertVec3ds(vec3d, (Vec3d[])offsets.toArray(array))));
        }
        return placeTargets;
    }
    
    public static List<Vec3d> getOffsetList(final int y, final boolean floor, final boolean face) {
        final List<Vec3d> offsets = new ArrayList<Vec3d>();
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
    
    public static Vec3d[] getOffsets(final int y, final boolean floor, final boolean face) {
        final List<Vec3d> offsets = getOffsetList(y, floor, face);
        final Vec3d[] array = new Vec3d[offsets.size()];
        return offsets.toArray(array);
    }
    
    public static Vec3d[] getTrapOffsets(final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean face) {
        final List<Vec3d> offsets = getTrapOffsetsList(antiScaffold, antiStep, legs, platform, antiDrop, face);
        final Vec3d[] array = new Vec3d[offsets.size()];
        return offsets.toArray(array);
    }
    
    public static List<Vec3d> getTrapOffsetsList(final boolean antiScaffold, final boolean antiStep, final boolean legs, final boolean platform, final boolean antiDrop, final boolean face) {
        final List<Vec3d> offsets = new ArrayList<Vec3d>(getOffsetList(1, false, face));
        offsets.add(new Vec3d(0.0, 2.0, 0.0));
        if (antiScaffold) {
            offsets.add(new Vec3d(0.0, 3.0, 0.0));
        }
        if (antiStep) {
            offsets.addAll(getOffsetList(2, false, face));
        }
        if (legs) {
            offsets.addAll(getOffsetList(0, false, face));
        }
        if (platform) {
            offsets.addAll(getOffsetList(-1, false, face));
            offsets.add(new Vec3d(0.0, -1.0, 0.0));
        }
        if (antiDrop) {
            offsets.add(new Vec3d(0.0, -2.0, 0.0));
        }
        return offsets;
    }
    
    public static Vec3d[] getHeightOffsets(final int min, final int max) {
        final List<Vec3d> offsets = new ArrayList<Vec3d>();
        for (int i = min; i <= max; ++i) {
            offsets.add(new Vec3d(0.0, (double)i, 0.0));
        }
        final Vec3d[] array = new Vec3d[offsets.size()];
        return offsets.toArray(array);
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
        return EntityUtil3.mc.world.rayTraceBlocks(new Vec3d(EntityUtil3.mc.player.posX, EntityUtil3.mc.player.posX + EntityUtil3.mc.player.getEyeHeight(), EntityUtil3.mc.player.posZ), new Vec3d(entityIn.posX, entityIn.posY, entityIn.posZ), false, true, false) == null;
    }
    
    public static boolean isntValid(final Entity entity, final double range) {
        return entity == null || isDead(entity) || entity.equals((Object)EntityUtil3.mc.player) || (entity instanceof EntityPlayer && Cherry.friendManager.isFriend(entity.getName())) || EntityUtil3.mc.player.getDistanceSq(entity) > MathUtil.square(range);
    }
    
    public static boolean isValid(final Entity entity, final double range) {
        return !isntValid(entity, range);
    }
    
    public static boolean holdingWeapon(final EntityPlayer player) {
        return player.getHeldItemMainhand().getItem() instanceof ItemSword || player.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }
    
    public static double getMaxSpeed() {
        double maxModifier = 0.2873;
        if (EntityUtil3.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(1)))) {
            maxModifier *= 1.0 + 0.2 * (Objects.requireNonNull(EntityUtil3.mc.player.getActivePotionEffect((Potion)Objects.requireNonNull(Potion.getPotionById(1)))).getAmplifier() + 1);
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
            return EntityUtil3.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil3.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil3.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil3.mc.gameSettings.keyBindRight.isKeyDown();
        }
        return entity.motionX != 0.0 || entity.motionY != 0.0 || entity.motionZ != 0.0;
    }
    
    public static boolean movementKey() {
        return EntityUtil3.mc.player.movementInput.forwardKeyDown || EntityUtil3.mc.player.movementInput.rightKeyDown || EntityUtil3.mc.player.movementInput.leftKeyDown || EntityUtil3.mc.player.movementInput.backKeyDown || EntityUtil3.mc.player.movementInput.jump || EntityUtil3.mc.player.movementInput.sneak;
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
    
    public static boolean holding32k(final EntityPlayer player) {
        return is32k(player.getHeldItemMainhand());
    }
    
    public static boolean is32k(final ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (stack.getTagCompound() == null) {
            return false;
        }
        final NBTTagList enchants = (NBTTagList)stack.getTagCompound().getTag("ench");
        if (enchants == null) {
            return false;
        }
        int i = 0;
        while (i < enchants.tagCount()) {
            final NBTTagCompound enchant = enchants.getCompoundTagAt(i);
            if (enchant.getInteger("id") == 16) {
                final int lvl = enchant.getInteger("lvl");
                if (lvl >= 42) {
                    return true;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    public static boolean simpleIs32k(final ItemStack stack) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack) >= 1000;
    }
    
    public static void moveEntityStrafe(final double speed, final Entity entity) {
        if (entity != null) {
            final MovementInput movementInput = EntityUtil3.mc.player.movementInput;
            double forward = movementInput.moveForward;
            double strafe = movementInput.moveStrafe;
            float yaw = EntityUtil3.mc.player.rotationYaw;
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
        return !shouldCheck || EntityUtil3.mc.player.canEntityBeSeen(entity);
    }
    
    public static Color getColor(final Entity entity, final int red, final int green, final int blue, final int alpha, final boolean colorFriends) {
        Color color = new Color(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);
        if (entity instanceof EntityPlayer) {
            if (colorFriends && Cherry.friendManager.isFriend((EntityPlayer)entity)) {
                color = new Color(0.33333334f, 1.0f, 1.0f, alpha / 255.0f);
            }
            final Killaura killaura = (Killaura)Cherry.moduleManager.getModuleByClass((Class)Killaura.class);
            color = new Color(1.0f, 0.0f, 0.0f, alpha / 255.0f);
        }
        return color;
    }
    
    public static boolean isMoving() {
        return EntityUtil3.mc.player.moveForward != 0.0 || EntityUtil3.mc.player.moveStrafing != 0.0;
    }
    
    public static EntityPlayer getClosestEnemy(final double distance) {
        EntityPlayer closest = null;
        for (final EntityPlayer player : EntityUtil3.mc.world.playerEntities) {
            if (isntValid((Entity)player, distance)) {
                continue;
            }
            if (closest == null) {
                closest = player;
            }
            else {
                if (EntityUtil3.mc.player.getDistanceSq((Entity)player) >= EntityUtil3.mc.player.getDistanceSq((Entity)closest)) {
                    continue;
                }
                closest = player;
            }
        }
        return closest;
    }
    
    public static boolean checkCollide() {
        return !EntityUtil3.mc.player.isSneaking() && (EntityUtil3.mc.player.getRidingEntity() == null || EntityUtil3.mc.player.getRidingEntity().fallDistance < 3.0f) && EntityUtil3.mc.player.fallDistance < 3.0f;
    }
    
    public static boolean isInLiquid() {
        if (EntityUtil3.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        boolean inLiquid = false;
        final AxisAlignedBB bb = (EntityUtil3.mc.player.getRidingEntity() != null) ? EntityUtil3.mc.player.getRidingEntity().getEntityBoundingBox() : EntityUtil3.mc.player.getEntityBoundingBox();
        final int y = (int)bb.minY;
        for (int x = MathHelper.floor(bb.minX); x < MathHelper.floor(bb.maxX) + 1; ++x) {
            for (int z = MathHelper.floor(bb.minZ); z < MathHelper.floor(bb.maxZ) + 1; ++z) {
                final Block block = EntityUtil3.mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (!(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    inLiquid = true;
                }
            }
        }
        return inLiquid;
    }
    
    public static boolean isOnLiquid(final double offset) {
        if (EntityUtil3.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        final AxisAlignedBB bb = (EntityUtil3.mc.player.getRidingEntity() != null) ? EntityUtil3.mc.player.getRidingEntity().getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(0.0, -offset, 0.0) : EntityUtil3.mc.player.getEntityBoundingBox().contract(0.0, 0.0, 0.0).offset(0.0, -offset, 0.0);
        boolean onLiquid = false;
        final int y = (int)bb.minY;
        for (int x = MathHelper.floor(bb.minX); x < MathHelper.floor(bb.maxX + 1.0); ++x) {
            for (int z = MathHelper.floor(bb.minZ); z < MathHelper.floor(bb.maxZ + 1.0); ++z) {
                final Block block = EntityUtil3.mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block != Blocks.AIR) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    onLiquid = true;
                }
            }
        }
        return onLiquid;
    }
    
    public static boolean isAboveLiquid(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY + 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtil3.mc.world.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((EntityUtil3.mc.player.getRidingEntity() != null) ? EntityUtil3.mc.player.getRidingEntity().posX : EntityUtil3.mc.player.posX, (EntityUtil3.mc.player.getRidingEntity() != null) ? EntityUtil3.mc.player.getRidingEntity().posY : EntityUtil3.mc.player.posY, (EntityUtil3.mc.player.getRidingEntity() != null) ? EntityUtil3.mc.player.getRidingEntity().posZ : EntityUtil3.mc.player.posZ);
    }
    
    public static boolean checkForLiquid(final Entity entity, final boolean b) {
        if (entity == null) {
            return false;
        }
        final double posY = entity.posY;
        double n;
        if (b) {
            n = 0.03;
        }
        else if (entity instanceof EntityPlayer) {
            n = 0.2;
        }
        else {
            n = 0.5;
        }
        final double n2 = posY - n;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtil3.mc.world.getBlockState(new BlockPos(i, MathHelper.floor(n2), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isOnLiquid() {
        final double y = EntityUtil3.mc.player.posY - 0.03;
        for (int x = MathHelper.floor(EntityUtil3.mc.player.posX); x < MathHelper.ceil(EntityUtil3.mc.player.posX); ++x) {
            for (int z = MathHelper.floor(EntityUtil3.mc.player.posZ); z < MathHelper.ceil(EntityUtil3.mc.player.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);
                if (EntityUtil3.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static double[] forward(final double speed) {
        float forward = EntityUtil3.mc.player.movementInput.moveForward;
        float side = EntityUtil3.mc.player.movementInput.moveStrafe;
        float yaw = EntityUtil3.mc.player.prevRotationYaw + (EntityUtil3.mc.player.rotationYaw - EntityUtil3.mc.player.prevRotationYaw) * EntityUtil3.mc.getRenderPartialTicks();
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
        for (final EntityPlayer player : EntityUtil3.mc.world.playerEntities) {
            if (player.isInvisible() && !(boolean)Managers.getInstance().tRadarInv.getValue()) {
                continue;
            }
            if (player.getName().equals(EntityUtil3.mc.player.getName())) {
                continue;
            }
            final int hpRaw = (int)getHealth((Entity)player);
            final String hp = dfHealth.format(hpRaw);
            healthSB.append("\uff82\uff67");
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
            final int distanceInt = (int)EntityUtil3.mc.player.getDistance((Entity)player);
            final String distance = dfDistance.format(distanceInt);
            distanceSB.append("\uff82\uff67");
            if (distanceInt >= 25) {
                distanceSB.append("a");
            }
            else if (distanceInt > 10) {
                distanceSB.append("6");
            }
            else if (distanceInt >= 50) {
                distanceSB.append("7");
            }
            else {
                distanceSB.append("c");
            }
            distanceSB.append(distance);
            healthSB.setLength(0);
            distanceSB.setLength(0);
        }
        if (!output.isEmpty()) {}
        return output;
    }
    
    public static void swingArmNoPacket(final EnumHand hand, final EntityLivingBase entity) {
        final ItemStack stack = entity.getHeldItem(hand);
        if (!stack.isEmpty() && stack.getItem().onEntitySwing(entity, stack)) {
            return;
        }
        entity.swingProgressInt = -1;
        entity.isSwingInProgress = true;
        entity.swingingHand = hand;
    }
    
    public static boolean isAboveBlock(final Entity entity, final BlockPos blockPos) {
        return entity.posY >= blockPos.getY();
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
    }
}
