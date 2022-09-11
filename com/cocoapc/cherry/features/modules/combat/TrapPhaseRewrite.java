//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.features.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import com.cocoapc.cherry.util.util2.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;

public class TrapPhaseRewrite extends Module
{
    public static TrapPhaseRewrite INSTANCE;
    public int Nulls;
    public int Nells;
    public Setting<Float> Lol;
    public Setting<modes> mode;
    public Setting<Float> set;
    public Setting<Boolean> Switch;
    public Setting<Boolean> Place;
    private EnumHand oldhand;
    private int oldslot;
    
    public TrapPhaseRewrite() {
        super("TrapBurrow", "MANKO", Category.COMBAT, true, false, false);
        this.Nulls = 1;
        this.Nells = 0;
        this.Lol = (Setting<Float>)this.register(new Setting("Speed", (T)0.5f, (T)(-10.0f), (T)10.0f));
        this.mode = (Setting<modes>)this.register(new Setting("Blocks", (T)modes.EN_CHEST, "Your Suffix."));
        this.set = (Setting<Float>)this.register(new Setting("Offset", (T)0.5f, (T)(-10.0f), (T)10.0f));
        this.Switch = (Setting<Boolean>)this.register(new Setting("Switch", (T)true));
        this.Place = (Setting<Boolean>)this.register(new Setting("Place", (T)true));
        this.oldhand = null;
        this.oldslot = -1;
        TrapPhaseRewrite.INSTANCE = this;
    }
    
    @Override
    public int onTick() {
        if (Feature.nullCheck()) {
            return 0;
        }
        if (this.mode.getValue() == modes.OB) {
            final int IRON_TRAPDOOR = InventoryUtilE.getBlockHotbar(Blocks.IRON_TRAPDOOR);
            final int OBSIDIAN = InventoryUtilE.getBlockHotbar(Blocks.OBSIDIAN);
            if (IRON_TRAPDOOR == -1 || OBSIDIAN == -1) {
                this.disable();
                return IRON_TRAPDOOR;
            }
            final BlockPos[] offsets = { new BlockPos(this.Nulls, this.Nells, this.Nells), new BlockPos(-1, this.Nells, this.Nells), new BlockPos(this.Nells, this.Nells, this.Nulls), new BlockPos(this.Nells, this.Nells, -1) };
            final BlockPos playerPos = EntityUtilE.getEntityPos((Entity)TrapPhaseRewrite.mc.player);
            BlockPos trappos = null;
            for (final BlockPos offset : offsets) {
                final BlockPos pos = playerPos.add((Vec3i)offset);
                if (this.entityCheck(pos)) {
                    if (!BlockUtilE.getBlock(pos).equals(Blocks.AIR)) {
                        trappos = pos;
                    }
                }
            }
            if (trappos == null) {
                this.disable();
                return IRON_TRAPDOOR;
            }
            this.sets(IRON_TRAPDOOR);
            final double x = TrapPhaseRewrite.mc.player.posX;
            final double y = TrapPhaseRewrite.mc.player.posY;
            final double z = TrapPhaseRewrite.mc.player.posZ;
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + this.set.getValue(), z, TrapPhaseRewrite.mc.player.onGround));
            EnumFacing facing = null;
            for (final EnumFacing f : EnumFacing.values()) {
                if (trappos.add(f.getDirectionVec()).equals((Object)playerPos)) {
                    facing = f;
                }
            }
            BlockUtilE.rightClickBlock(trappos, facing, new Vec3d(0.5, 0.8, 0.5), this.Place.getValue());
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, TrapPhaseRewrite.mc.player.onGround));
            this.sets(OBSIDIAN);
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y - 0.8, z, false));
            BlockUtilE.rightClickBlock(playerPos, EnumFacing.UP, this.Place.getValue());
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, false));
            Command.sendMessage("Ez Trap phase Rewrite");
            this.Item();
            this.disable();
            return IRON_TRAPDOOR;
        }
        else if (this.mode.getValue() == modes.EN_CHEST) {
            final int IRON_TRAPDOOR = InventoryUtilE.getBlockHotbar(Blocks.IRON_TRAPDOOR);
            final int OBSIDIAN = InventoryUtilE.getBlockHotbar(Blocks.ENDER_CHEST);
            if (IRON_TRAPDOOR == -1 || OBSIDIAN == -1) {
                this.disable();
                return IRON_TRAPDOOR;
            }
            final BlockPos[] offsets = { new BlockPos(this.Nulls, this.Nells, this.Nells), new BlockPos(-1, this.Nells, this.Nells), new BlockPos(this.Nells, this.Nells, this.Nulls), new BlockPos(this.Nells, this.Nells, -1) };
            final BlockPos playerPos = EntityUtilE.getEntityPos((Entity)TrapPhaseRewrite.mc.player);
            BlockPos trappos = null;
            for (final BlockPos offset : offsets) {
                final BlockPos pos = playerPos.add((Vec3i)offset);
                if (this.entityCheck(pos)) {
                    if (!BlockUtilE.getBlock(pos).equals(Blocks.AIR)) {
                        trappos = pos;
                    }
                }
            }
            if (trappos == null) {
                this.disable();
                return IRON_TRAPDOOR;
            }
            this.sets(IRON_TRAPDOOR);
            final double x = TrapPhaseRewrite.mc.player.posX;
            final double y = TrapPhaseRewrite.mc.player.posY;
            final double z = TrapPhaseRewrite.mc.player.posZ;
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + this.set.getValue(), z, TrapPhaseRewrite.mc.player.onGround));
            EnumFacing facing = null;
            for (final EnumFacing f : EnumFacing.values()) {
                if (trappos.add(f.getDirectionVec()).equals((Object)playerPos)) {
                    facing = f;
                }
            }
            BlockUtilE.rightClickBlock(trappos, facing, new Vec3d(0.5, 0.8, 0.5), this.Place.getValue());
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, TrapPhaseRewrite.mc.player.onGround));
            this.sets(OBSIDIAN);
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y - 0.8, z, false));
            BlockUtilE.rightClickBlock(playerPos, EnumFacing.UP, this.Place.getValue());
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, false));
            Command.sendMessage("Ez Trap phase Rewrite");
            this.Item();
            this.disable();
            return IRON_TRAPDOOR;
        }
        else {
            if (this.mode.getValue() != modes.ANVIL) {
                return 0;
            }
            final int IRON_TRAPDOOR = InventoryUtilE.getBlockHotbar(Blocks.IRON_TRAPDOOR);
            final int OBSIDIAN = InventoryUtilE.getBlockHotbar(Blocks.ANVIL);
            if (IRON_TRAPDOOR == -1 || OBSIDIAN == -1) {
                this.disable();
                return IRON_TRAPDOOR;
            }
            final BlockPos[] offsets = { new BlockPos(this.Nulls, this.Nells, this.Nells), new BlockPos(-1, this.Nells, this.Nells), new BlockPos(this.Nells, this.Nells, this.Nulls), new BlockPos(this.Nells, this.Nells, -1) };
            final BlockPos playerPos = EntityUtilE.getEntityPos((Entity)TrapPhaseRewrite.mc.player);
            BlockPos trappos = null;
            for (final BlockPos offset : offsets) {
                final BlockPos pos = playerPos.add((Vec3i)offset);
                if (this.entityCheck(pos)) {
                    if (!BlockUtilE.getBlock(pos).equals(Blocks.AIR)) {
                        trappos = pos;
                    }
                }
            }
            if (trappos == null) {
                this.disable();
                return IRON_TRAPDOOR;
            }
            this.sets(IRON_TRAPDOOR);
            final double x = TrapPhaseRewrite.mc.player.posX;
            final double y = TrapPhaseRewrite.mc.player.posY;
            final double z = TrapPhaseRewrite.mc.player.posZ;
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + this.set.getValue(), z, TrapPhaseRewrite.mc.player.onGround));
            EnumFacing facing = null;
            for (final EnumFacing f : EnumFacing.values()) {
                if (trappos.add(f.getDirectionVec()).equals((Object)playerPos)) {
                    facing = f;
                }
            }
            BlockUtilE.rightClickBlock(trappos, facing, new Vec3d(0.5, 0.8, 0.5), this.Place.getValue());
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, TrapPhaseRewrite.mc.player.onGround));
            this.sets(OBSIDIAN);
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y - 0.8, z, false));
            BlockUtilE.rightClickBlock(playerPos, EnumFacing.UP, this.Place.getValue());
            TrapPhaseRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, false));
            Command.sendMessage("Ez Trap phase Rewrite");
            this.Item();
            this.disable();
            return IRON_TRAPDOOR;
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    public boolean entityCheck(final BlockPos pos) {
        return Util.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(pos), e -> e instanceof EntityEnderCrystal || e instanceof EntityPlayer).isEmpty();
    }
    
    public void sets(final int slot) {
        if (this.Switch.getValue()) {
            this.oldhand = null;
            if (Util.mc.player.isHandActive()) {
                this.oldhand = Util.mc.player.getActiveHand();
            }
            this.oldslot = Util.mc.player.inventory.currentItem;
            Util.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        }
        else {
            Util.mc.player.inventory.currentItem = slot;
            Util.mc.playerController.updateController();
        }
    }
    
    public void Item() {
        if (this.oldslot != -1 && this.Switch.getValue()) {
            if (this.oldhand != null) {
                Util.mc.player.setActiveHand(this.oldhand);
            }
            Util.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.oldslot));
            this.oldslot = -1;
            this.oldhand = null;
        }
    }
    
    public enum modes
    {
        OB, 
        EN_CHEST, 
        ANVIL;
    }
}
