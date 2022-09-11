//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.util.util2.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;

public class PhaseWalk extends Module
{
    public final Setting<Boolean> phaseCheck;
    public final Setting<NoClipMode> noClipMode;
    public final Setting<Boolean> fallPacket;
    public final Setting<Boolean> sprintPacket;
    public final Setting<Boolean> instantWalk;
    public final Setting<Boolean> selfAnvil;
    public final Setting<Boolean> antiVoid;
    public final Setting<Boolean> clip;
    public final Setting<Boolean> clipRange;
    public final Setting<Boolean> clipDelay;
    public final Setting<Integer> antiVoidHeight;
    public final Setting<Double> instantWalkSpeed;
    public final Setting<Double> phaseSpeed;
    public final Setting<Boolean> downOnShift;
    public final Setting<Boolean> stopMotion;
    public final Setting<Integer> stopMotionDelay;
    int delay;
    
    public PhaseWalk() {
        super("PhaseWalk", "Allows you to walk through blocks", Module.Category.MOVEMENT, true, false, false);
        this.phaseCheck = (Setting<Boolean>)this.register(new Setting("Only In Block", (T)true));
        this.noClipMode = (Setting<NoClipMode>)this.register(new Setting("NoClipMode", (T)NoClipMode.NoClip));
        this.fallPacket = (Setting<Boolean>)this.register(new Setting("Fall Packet", (T)true));
        this.sprintPacket = (Setting<Boolean>)this.register(new Setting("Sprint Packet", (T)true));
        this.instantWalk = (Setting<Boolean>)this.register(new Setting("Instant Walk", (T)true));
        this.selfAnvil = (Setting<Boolean>)this.register(new Setting("Self Anvil", (T)false));
        this.antiVoid = (Setting<Boolean>)this.register(new Setting("Anti Void", (T)false));
        this.clip = (Setting<Boolean>)this.register(new Setting("Clip", (T)true));
        this.clipRange = (Setting<Boolean>)this.register(new Setting("Clip Range", (T)2, (T)1, (T)5));
        this.clipDelay = (Setting<Boolean>)this.register(new Setting("Clip Delay", (T)10, (T)1, (T)150));
        this.antiVoidHeight = (Setting<Integer>)this.register(new Setting("Anti Void Height", (T)5, (T)1, (T)100));
        this.instantWalkSpeed = (Setting<Double>)this.register(new Setting("Instant Speed", (T)1.8, (T)0.1, (T)2.0, v -> this.instantWalk.getValue()));
        this.phaseSpeed = (Setting<Double>)this.register(new Setting("Phase Walk Speed", (T)42.4, (T)0.1, (T)70.0));
        this.downOnShift = (Setting<Boolean>)this.register(new Setting("Phase Down When Crouch", (T)true));
        this.stopMotion = (Setting<Boolean>)this.register(new Setting("Attempt Clips", (T)true));
        this.stopMotionDelay = (Setting<Integer>)this.register(new Setting("Attempt Clips Delay", (T)5, (T)0, (T)20, v -> this.stopMotion.getValue()));
        this.delay = 0;
    }
    
    public int onUpdate() {
        ++this.delay;
        final double phaseSpeedValue = this.phaseSpeed.getValue() / 1000.0;
        final double instantSpeedValue = this.instantWalkSpeed.getValue() / 10.0;
        final RayTraceResult trace;
        if (this.antiVoid.getValue() && PhaseWalk.mc.player.posY <= this.antiVoidHeight.getValue() && ((trace = PhaseWalk.mc.world.rayTraceBlocks(PhaseWalk.mc.player.getPositionVector(), new Vec3d(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ), false, false, false)) == null || trace.typeOfHit != RayTraceResult.Type.BLOCK)) {
            PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
        }
        if (this.phaseCheck.getValue()) {
            if ((PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) && ((!this.eChestCheck() && !PhaseWalk.mc.world.getBlockState(PlayerUtil.getPlayerPos()).getBlock().equals(Blocks.AIR)) || !PhaseWalk.mc.world.getBlockState(PlayerUtil.getPlayerPos().up()).getBlock().equals(Blocks.AIR))) {
                if (PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isPressed() && PhaseWalk.mc.player.isSneaking()) {
                    final double[] dirSpeed = this.getMotion(phaseSpeedValue);
                    if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    else {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Fall) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                        PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                        if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                            final double[] speed = MathUtil.directionSpeed(0.05999999865889549);
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + speed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + speed[1], PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                        PhaseWalk.mc.player.noClip = true;
                    }
                    if (this.fallPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                    }
                    if (this.sprintPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                    }
                    if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                    }
                    else {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                    }
                    PhaseWalk.mc.player.motionZ = 0.0;
                    PhaseWalk.mc.player.motionY = 0.0;
                    PhaseWalk.mc.player.motionX = 0.0;
                    PhaseWalk.mc.player.noClip = true;
                }
                if (!PhaseWalk.mc.player.collidedHorizontally || !this.clip.getValue() || PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {}
                Label_2554: {
                    if (PhaseWalk.mc.player.collidedHorizontally && this.stopMotion.getValue()) {
                        if (this.delay < this.stopMotionDelay.getValue()) {
                            break Label_2554;
                        }
                    }
                    else if (!PhaseWalk.mc.player.collidedHorizontally) {
                        break Label_2554;
                    }
                    final double[] dirSpeed = this.getMotion(phaseSpeedValue);
                    if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    else {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Fall) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                        PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                        if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                            final double[] speed = MathUtil.directionSpeed(0.05999999865889549);
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + speed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + speed[1], PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                        PhaseWalk.mc.player.noClip = true;
                    }
                    if (this.fallPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                    }
                    if (this.sprintPacket.getValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                    }
                    if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                    }
                    else {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                    }
                    PhaseWalk.mc.player.motionZ = 0.0;
                    PhaseWalk.mc.player.motionY = 0.0;
                    PhaseWalk.mc.player.motionX = 0.0;
                    PhaseWalk.mc.player.noClip = true;
                    this.delay = 0;
                    return 0;
                }
                if (this.instantWalk.getValue()) {
                    final double[] dir = MathUtil.directionSpeed(instantSpeedValue);
                    PhaseWalk.mc.player.motionX = dir[0];
                    PhaseWalk.mc.player.motionZ = dir[1];
                }
            }
        }
        else if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
            if (PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isPressed() && PhaseWalk.mc.player.isSneaking()) {
                final double[] dirSpeed = this.getMotion(phaseSpeedValue);
                if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                else {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                if (this.noClipMode.getValue() == NoClipMode.Fall) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                }
                if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                    PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                    if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                        final double[] speed = MathUtil.directionSpeed(0.05999999865889549);
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + speed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + speed[1], PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                }
                if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                    PhaseWalk.mc.player.noClip = true;
                }
                if (this.fallPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                }
                if (this.sprintPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                }
                else {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                }
                PhaseWalk.mc.player.motionZ = 0.0;
                PhaseWalk.mc.player.motionY = 0.0;
                PhaseWalk.mc.player.motionX = 0.0;
                PhaseWalk.mc.player.noClip = true;
            }
            Label_4826: {
                if (PhaseWalk.mc.player.collidedHorizontally && this.stopMotion.getValue()) {
                    if (this.delay < this.stopMotionDelay.getValue()) {
                        break Label_4826;
                    }
                }
                else if (!PhaseWalk.mc.player.collidedHorizontally) {
                    break Label_4826;
                }
                final double[] dirSpeed = this.getMotion(phaseSpeedValue);
                if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                else {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                if (this.noClipMode.getValue() == NoClipMode.Fall) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                }
                if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                    PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                    if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                        final double[] speed = MathUtil.directionSpeed(0.05999999865889549);
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + speed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + speed[1], PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + 0.05999999865889549, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                }
                if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                    PhaseWalk.mc.player.noClip = true;
                }
                if (this.fallPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                }
                if (this.sprintPacket.getValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if (this.downOnShift.getValue() && PhaseWalk.mc.player.collidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                }
                else {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + dirSpeed[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + dirSpeed[1]);
                }
                PhaseWalk.mc.player.motionZ = 0.0;
                PhaseWalk.mc.player.motionY = 0.0;
                PhaseWalk.mc.player.motionX = 0.0;
                PhaseWalk.mc.player.noClip = true;
                this.delay = 0;
                return 0;
            }
            if (this.instantWalk.getValue()) {
                final double[] dir = MathUtil.directionSpeed(instantSpeedValue);
                PhaseWalk.mc.player.motionX = dir[0];
                PhaseWalk.mc.player.motionZ = dir[1];
            }
        }
        return 0;
    }
    
    public void onRender3D() {
    }
    
    private double[] getMotion(final double speed) {
        float moveForward = PhaseWalk.mc.player.movementInput.moveForward;
        float moveStrafe = PhaseWalk.mc.player.movementInput.moveStrafe;
        float rotationYaw = PhaseWalk.mc.player.prevRotationYaw + (PhaseWalk.mc.player.rotationYaw - PhaseWalk.mc.player.prevRotationYaw) * PhaseWalk.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                rotationYaw += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                rotationYaw += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double posX = moveForward * speed * -Math.sin(Math.toRadians(rotationYaw)) + moveStrafe * speed * Math.cos(Math.toRadians(rotationYaw));
        final double posZ = moveForward * speed * Math.cos(Math.toRadians(rotationYaw)) - moveStrafe * speed * -Math.sin(Math.toRadians(rotationYaw));
        return new double[] { posX, posZ };
    }
    
    private double[] getDistance() {
        final float forward = PhaseWalk.mc.player.movementInput.moveForward;
        final float strafe = PhaseWalk.mc.player.movementInput.moveStrafe;
        final float rotYaw = PhaseWalk.mc.player.prevRotationYaw + (PhaseWalk.mc.player.rotationYaw - PhaseWalk.mc.player.prevRotationYaw) * PhaseWalk.mc.getRenderPartialTicks();
        final double posX = strafe * (double)forward * -Math.sin(Math.toRadians(rotYaw)) + strafe * (double)forward * Math.cos(Math.toRadians(rotYaw));
        final double posZ = strafe * (double)forward * Math.cos(Math.toRadians(rotYaw)) - strafe * (double)forward * -Math.sin(Math.toRadians(rotYaw));
        return new double[] { posX, posZ };
    }
    
    public int onDisable() {
        PhaseWalk.mc.player.noClip = false;
        return 0;
    }
    
    private boolean eChestCheck() {
        final String loc = String.valueOf(PhaseWalk.mc.player.posY);
        final String deciaml = loc.split("\\.")[1];
        return deciaml.equals("875");
    }
    
    public enum NoClipMode
    {
        NoClip, 
        Fall, 
        Bypass, 
        None;
    }
}
