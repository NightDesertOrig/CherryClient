//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.player;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;

public class InstantMine extends Module
{
    public static InstantMine instance;
    public Setting<Boolean> autoBreak;
    public Setting<Integer> delay;
    public Setting<Boolean> picOnly;
    public Setting<Boolean> render;
    public Setting<Boolean> line;
    public Setting<Float> width;
    private static boolean packetCancel;
    private static Timer breaktimer;
    private static Timer timer;
    private static EnumFacing direction;
    public static BlockPos FourZeroFourBlock;
    private static BlockPos lastBlock;
    
    public InstantMine() {
        super("InstantMine", "a", Module.Category.PLAYER, true, false, false);
        this.autoBreak = (Setting<Boolean>)this.register(new Setting("AutoBreak", (T)true));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)20, (T)0, (T)500));
        this.picOnly = (Setting<Boolean>)this.register(new Setting("Only Pickaxe", (T)true));
        final Setting register = this.register(new Setting("Render", (T)false));
        this.render = (Setting<Boolean>)register;
        this.render = (Setting<Boolean>)register;
        this.line = (Setting<Boolean>)this.register(new Setting("Line", (T)false));
        this.width = (Setting<Float>)this.register(new Setting("Width", (T)2.0f, (T)0.2f, (T)5.0f));
        InstantMine.instance = this;
    }
    
    public void onRender3D() {
    }
    
    public int onUpdate() {
        this.update();
        return 0;
    }
    
    public void update() {
        if (nullCheck()) {
            return;
        }
        if (InstantMine.FourZeroFourBlock != null && this.autoBreak.getValue() && InstantMine.timer.passedMs(this.delay.getValue())) {
            if (this.picOnly.getValue() && InstantMine.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() != Items.DIAMOND_PICKAXE) {
                return;
            }
            InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.FourZeroFourBlock, InstantMine.direction));
            InstantMine.breaktimer.reset();
        }
    }
    
    public void onPacketSend(final PacketEvent.Send event) {
        if (nullCheck()) {
            return;
        }
        final Packet<?> packet = (Packet<?>)event.getPacket();
        if (packet instanceof CPacketPlayerDigging && ((CPacketPlayerDigging)packet).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK && InstantMine.packetCancel) {
            event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void OnDamageBlock(final BlockEvent event) {
        if (nullCheck()) {
            return;
        }
        startBreak(event.pos, event.facing);
        event.setCanceled(true);
    }
    
    public static void startBreak(final BlockPos pos, final EnumFacing facing) {
        if (canBreak(pos)) {
            if (InstantMine.lastBlock == null || pos.getX() != InstantMine.lastBlock.getX() || pos.getY() != InstantMine.lastBlock.getY() || pos.getZ() != InstantMine.lastBlock.getZ()) {
                InstantMine.packetCancel = false;
                InstantMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
            }
            InstantMine.packetCancel = true;
            InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, facing));
            InstantMine.FourZeroFourBlock = pos;
            InstantMine.lastBlock = pos;
            InstantMine.direction = facing;
        }
    }
    
    private static boolean canBreak(final BlockPos pos) {
        final IBlockState blockState = InstantMine.mc.world.getBlockState(pos);
        final Block block = blockState.getBlock();
        return block.getBlockHardness(blockState, (World)InstantMine.mc.world, pos) != -1.0f;
    }
    
    static {
        InstantMine.packetCancel = false;
        InstantMine.breaktimer = new Timer();
        InstantMine.timer = new Timer();
    }
}
