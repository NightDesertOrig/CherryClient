//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.init.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraft.entity.*;
import com.cocoapc.cherry.util.util2.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;

public class TrapPhasee extends Module
{
    private EnumHand oldhand;
    private int oldslot;
    public Setting<mode> modes;
    public final Setting<Float> offset;
    public final Setting<Boolean> silentSwitch;
    public final Setting<Boolean> packetPlace;
    
    public TrapPhasee() {
        super("Trapphase", "", Category.COMBAT, true, false, false);
        this.oldhand = null;
        this.oldslot = -1;
        this.modes = (Setting<mode>)this.register(new Setting("Blocks", (T)mode.EN_CHEST, "Your Suffix."));
        this.offset = (Setting<Float>)this.register(new Setting("Offset", (T)0.2f, (T)0.0f, (T)1.0f));
        this.silentSwitch = (Setting<Boolean>)this.register(new Setting("SilentSwitch", (T)true));
        this.packetPlace = (Setting<Boolean>)this.register(new Setting("PacketPlace", (T)true));
    }
    
    @Override
    public int onTick() {
        if (nullCheck()) {
            return 0;
        }
        final int slot = InventoryUtilE.getBlockHotbar(Blocks.IRON_TRAPDOOR);
        if (slot == -1) {
            Command.sendMessage("Cannot find materials! disabling");
            this.disable();
            return slot;
        }
        final BlockPos[] offsets = { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
        final BlockPos playerPos = EntityUtilE.getEntityPos((Entity)TrapPhasee.mc.player);
        BlockPos trappos = null;
        for (final BlockPos offset : offsets) {
            final BlockPos pos = playerPos.add((Vec3i)offset);
            if (this.entityCheck(pos)) {
                if (!BlockUtilE.getBlockSHG(pos).equals(Blocks.AIR)) {
                    trappos = pos;
                }
            }
        }
        if (trappos == null) {
            Command.sendMessage("Cannot find space! disabling");
            this.disable();
            return slot;
        }
        this.setItem(slot);
        final double x = TrapPhasee.mc.player.posX;
        final double y = TrapPhasee.mc.player.posY;
        final double z = TrapPhasee.mc.player.posZ;
        TrapPhasee.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y + this.offset.getValue(), z, TrapPhasee.mc.player.onGround));
        EnumFacing facing = null;
        for (final EnumFacing f : EnumFacing.values()) {
            if (trappos.add(f.getDirectionVec()).equals((Object)playerPos)) {
                facing = f;
            }
        }
        BlockUtilE.rightClickBlock(trappos, facing, new Vec3d(0.5, 0.8, 0.5), this.packetPlace.getValue());
        TrapPhasee.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, TrapPhasee.mc.player.onGround));
        TrapPhasee.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, false));
        this.restoreItem();
        this.disable();
        return slot;
    }
    
    @Override
    public void onRender3D() {
    }
    
    public boolean entityCheck(final BlockPos pos) {
        return TrapPhasee.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(pos), e -> e instanceof EntityEnderCrystal || e instanceof EntityPlayer).isEmpty();
    }
    
    public void setItem(final int slot) {
        if (this.silentSwitch.getValue()) {
            this.oldhand = null;
            if (TrapPhasee.mc.player.isHandActive()) {
                this.oldhand = TrapPhasee.mc.player.getActiveHand();
            }
            this.oldslot = TrapPhasee.mc.player.inventory.currentItem;
            TrapPhasee.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        }
        else {
            TrapPhasee.mc.player.inventory.currentItem = slot;
            TrapPhasee.mc.playerController.updateController();
        }
    }
    
    public void restoreItem() {
        if (this.oldslot != -1 && this.silentSwitch.getValue()) {
            if (this.oldhand != null) {
                TrapPhasee.mc.player.setActiveHand(this.oldhand);
            }
            TrapPhasee.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.oldslot));
            this.oldslot = -1;
            this.oldhand = null;
        }
    }
    
    public enum mode
    {
        OB, 
        EN_CHEST, 
        ANVIL;
    }
}
