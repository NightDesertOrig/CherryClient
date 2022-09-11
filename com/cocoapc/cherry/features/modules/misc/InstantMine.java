//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import jdk.nashorn.internal.ir.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.util.*;

public class InstantMine extends Module
{
    private final Timer breakSuccess;
    private static InstantMine INSTANCE;
    private Setting<Boolean> creativeMode;
    private Setting<Boolean> ghostHand;
    private Setting<Boolean> render;
    private final List<Block> godBlocks;
    private boolean cancelStart;
    private boolean empty;
    private EnumFacing facing;
    public static BlockPos breakPos;
    
    public InstantMine() {
        super("InstantMine", "InstantMine", Category.MISC, true, false, false);
        this.breakSuccess = new Timer();
        this.creativeMode = (Setting<Boolean>)this.register(new Setting("CreativeMode", (T)true));
        this.ghostHand = (Setting<Boolean>)this.register(new Setting("GhostHand", (T)true, v -> this.creativeMode.getValue()));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true));
        this.godBlocks = Arrays.asList(new Block[0]);
        this.cancelStart = false;
        this.empty = false;
        this.setInstance();
    }
    
    public static InstantMine getInstance() {
        if (InstantMine.INSTANCE == null) {
            InstantMine.INSTANCE = new InstantMine();
        }
        return InstantMine.INSTANCE;
    }
    
    private void setInstance() {
        InstantMine.INSTANCE = this;
    }
    
    @Override
    public int onUpdate() {
        if (fullNullCheck()) {
            return 0;
        }
        if (this.creativeMode.getValue() && this.cancelStart && !this.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
            if (this.ghostHand.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) != -1) {
                final int slotMain = InstantMine.mc.player.inventory.currentItem;
                if (InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.OBSIDIAN) {
                    if (this.breakSuccess.passedMs(1234L)) {
                        InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                        InstantMine.mc.playerController.updateController();
                        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                        InstantMine.mc.player.inventory.currentItem = slotMain;
                        InstantMine.mc.playerController.updateController();
                    }
                }
                else {
                    InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                    InstantMine.mc.playerController.updateController();
                    InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                    InstantMine.mc.player.inventory.currentItem = slotMain;
                    InstantMine.mc.playerController.updateController();
                }
            }
            else {
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
            }
        }
        return 0;
    }
    
    @Override
    public void onRender3D(final Render3DEvent event) {
        if (fullNullCheck()) {
            return;
        }
        if (this.render.getValue() && this.creativeMode.getValue() && this.cancelStart) {
            if (this.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
                this.empty = true;
            }
            final Color color = new Color(this.empty ? 0 : 255, this.empty ? 255 : 0, 0, 255);
            RenderUtil.drawBoxESP(InstantMine.breakPos, color, false, color, 1.0f, true, true, 55, false);
        }
        else if (this.cancelStart && this.render.getValue()) {
            RenderUtil.drawBoxESP(InstantMine.breakPos, new Color(255, 255, 255), false, new Color(255, 255, 255), 1.0f, true, true, 55, false);
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (fullNullCheck()) {
            return;
        }
        final CPacketPlayerDigging packet;
        if (event.getPacket() instanceof CPacketPlayerDigging && (packet = (CPacketPlayerDigging)event.getPacket()).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            event.setCanceled(this.cancelStart);
        }
    }
    
    @SubscribeEvent
    public void onBlockEvent(final PlayerDamageBlockEvent event) {
        if (fullNullCheck()) {
            return;
        }
        if (BlockUtil.canBreak(event.pos)) {
            this.empty = false;
            this.cancelStart = false;
            InstantMine.breakPos = event.pos;
            this.breakSuccess.reset();
            this.facing = event.facing;
            if (InstantMine.breakPos != null) {
                InstantMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                this.cancelStart = true;
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                event.setCanceled(true);
            }
        }
    }
    
    @Override
    public String getDisplayInfo() {
        return this.ghostHand.getValue() ? "Silent" : "Normal";
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        InstantMine.INSTANCE = new InstantMine();
    }
}
