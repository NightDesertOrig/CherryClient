//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import java.util.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;

public class AutoBedTrap extends Module
{
    public final Setting<Integer> range;
    public final Setting<Integer> delay;
    public final Setting<Boolean> extraPacket;
    public final Setting<Boolean> rotate;
    public final Setting<Boolean> packet;
    public final Timer timer;
    private boolean isSneaking;
    
    public AutoBedTrap() {
        super("BedTrap", "Automatically traps beds around you in obsidian", Category.MISC, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (T)5, (T)0, (T)10));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)1, (T)0, (T)200));
        this.extraPacket = (Setting<Boolean>)this.register(new Setting("Extra Packet", (T)false));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (T)true));
        this.timer = new Timer();
    }
    
    @Override
    public int onUpdate() {
        final BlockPos bedPos = BlockUtil.getSphere(PlayerUtil.getPlayerPosFloored(), this.range.getValue(), this.range.getValue(), false, true, 0).stream().filter(pos -> this.isBed(pos)).min(Comparator.comparing(pos -> EntityUtil.getDistPlayerToBlock((Entity)AutoBedTrap.mc.player, pos))).orElse(null);
        if (bedPos == null || fullNullCheck()) {
            return 0;
        }
        if (InventoryUtil.findHotbarBlock(BlockObsidian.class) == -1) {
            return 0;
        }
        this.trapBed();
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    public void trapBed() {
        final BlockPos bedPos = BlockUtil.getSphere(PlayerUtil.getPlayerPosFloored(), this.range.getValue(), this.range.getValue(), false, true, 0).stream().filter(pos -> this.isBed(pos)).min(Comparator.comparing(pos -> EntityUtil.getDistPlayerToBlock((Entity)AutoBedTrap.mc.player, pos))).orElse(null);
        final BlockPos bedX = new BlockPos(bedPos.getX() + 1, bedPos.getY(), bedPos.getZ());
        final BlockPos bedXMinus = new BlockPos(bedPos.getX() - 1, bedPos.getY(), bedPos.getZ());
        final BlockPos bedZ = new BlockPos(bedPos.getX(), bedPos.getY(), bedPos.getZ() + 1);
        final BlockPos bedZMinus = new BlockPos(bedPos.getX(), bedPos.getY(), bedPos.getZ() - 1);
        final BlockPos bedY = new BlockPos(bedPos.getX(), bedPos.getY() + 1, bedPos.getZ());
        final BlockPos bedYMinus = new BlockPos(bedPos.getX(), bedPos.getY() - 1, bedPos.getZ());
        if (TestUtil.canPlaceBlock(bedX)) {
            this.placeBlock(bedX, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), true);
        }
        if (TestUtil.canPlaceBlock(bedXMinus)) {
            this.placeBlock(bedXMinus, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), true);
        }
        if (TestUtil.canPlaceBlock(bedZ)) {
            this.placeBlock(bedZ, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), true);
        }
        if (TestUtil.canPlaceBlock(bedZMinus)) {
            this.placeBlock(bedZMinus, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), true);
        }
        if (TestUtil.canPlaceBlock(bedY)) {
            this.placeBlock(bedY, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), true);
        }
        if (TestUtil.canPlaceBlock(bedYMinus)) {
            this.placeBlock(bedYMinus, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), true);
        }
    }
    
    public void placeBlock(final BlockPos pos, final EnumHand hand, final boolean rotate, final boolean packet, final boolean sneaking) {
        final int obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        final int oldSlot = AutoBedTrap.mc.player.inventory.currentItem;
        AutoBedTrap.mc.player.inventory.currentItem = obbySlot;
        AutoBedTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoBedTrap.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        if (this.timer.passedMs(this.delay.getValue())) {
            BlockUtil.placeBlock(pos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, this.isSneaking);
            this.timer.reset();
        }
        AutoBedTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoBedTrap.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        AutoBedTrap.mc.player.inventory.currentItem = oldSlot;
    }
    
    private boolean isBed(final BlockPos pos) {
        final IBlockState blockState = AutoBedTrap.mc.world.getBlockState(pos);
        return blockState.getBlock() instanceof BlockBed;
    }
}
