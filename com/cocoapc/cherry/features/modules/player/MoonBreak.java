//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.player;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.features.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.awt.*;
import com.cocoapc.cherry.event.events2.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.util.*;

public class MoonBreak extends Module
{
    private static MoonBreak INSTANCE;
    private Setting<Boolean> creativeMode;
    private Setting<Boolean> ghostHand;
    private Setting<Boolean> fastUpdate;
    private Setting<Boolean> render;
    private boolean $Cancel;
    private BlockPos breakPos;
    private EnumFacing $Facing;
    
    public MoonBreak() {
        super("BREAK", "MoonBreak", Module.Category.PLAYER, true, false, false);
        this.creativeMode = (Setting<Boolean>)this.register(new Setting("CreativeMode", (T)true));
        this.ghostHand = (Setting<Boolean>)this.register(new Setting("GhostHand", (T)true, v -> this.creativeMode.getValue()));
        this.fastUpdate = (Setting<Boolean>)this.register(new Setting("FastUpDate", (T)true, v -> this.creativeMode.getValue() && !this.ghostHand.getValue()));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true));
        this.$Cancel = false;
        this.setInstance();
        MoonBreak.INSTANCE = this;
    }
    
    public static MoonBreak getInstance() {
        if (MoonBreak.INSTANCE == null) {
            MoonBreak.INSTANCE = new MoonBreak();
        }
        return MoonBreak.INSTANCE;
    }
    
    private void setInstance() {
        MoonBreak.INSTANCE = this;
    }
    
    public int onUpdate() {
        if (!Feature.fullNullCheck()) {
            if (this.breakPos != null && this.creativeMode.getValue() && Util.mc.world.getBlockState(this.breakPos).getBlock() != Blocks.AIR) {
                if (this.ghostHand.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) != -1) {
                    final int slotMain = Util.mc.player.inventory.currentItem;
                    Util.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                    Util.mc.playerController.updateController();
                    Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakPos, this.$Facing));
                    Util.mc.player.inventory.currentItem = slotMain;
                    Util.mc.playerController.updateController();
                }
                else {
                    Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakPos, this.$Facing));
                    if (this.fastUpdate.getValue()) {
                        Util.mc.world.setBlockToAir(this.breakPos);
                    }
                }
            }
            Util.mc.playerController.blockHitDelay = 0;
        }
        return 0;
    }
    
    public void onRender3D(final Render3DEvent event) {
        if (!Feature.fullNullCheck() && this.breakPos != null && this.render.getValue()) {
            if (Util.mc.world.getBlockState(this.breakPos).getBlock() != Blocks.AIR) {
                RenderUtil.drawBoxESP(this.breakPos, new Color(214, 75, 229, 255), false, new Color(255, 76, 209, 255), 1.0f, true, true, 84, false);
            }
            else {
                RenderUtil.drawBoxESP(this.breakPos, new Color(69, 84, 169, 255), false, new Color(80, 86, 148, 255), 1.0f, true, true, 84, false);
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEventM.Send event) {
        if (!Feature.fullNullCheck() && event.getPacket() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging packet = (CPacketPlayerDigging)event.getPacket();
            if (packet.getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK && this.$Cancel) {
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onBlockEvent(final PlayerDamageBlockEvent event) {
        if (!Feature.fullNullCheck()) {
            if (event.getStage() == 1 && Util.mc.playerController.curBlockDamageMP > 0.1f) {
                Util.mc.playerController.isHittingBlock = true;
            }
            if (event.getStage() == 0 && BlockUtil.canBreak(event.pos)) {
                Util.mc.playerController.isHittingBlock = false;
                if (this.breakPos != null && new BlockPos(event.pos.getX(), event.pos.getY(), event.pos.getZ()) == new BlockPos(this.breakPos.getX(), this.breakPos.getY(), this.breakPos.getZ())) {
                    this.$Cancel = true;
                }
                else {
                    this.$Cancel = false;
                    Util.mc.player.swingArm(EnumHand.MAIN_HAND);
                    Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, event.pos, event.facing));
                }
                Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.pos, event.facing));
                this.breakPos = event.pos;
                this.$Facing = event.facing;
                event.setCanceled(true);
            }
        }
    }
    
    public String getDisplayInfo() {
        return this.ghostHand.getValue() ? "Ghost" : "Normal";
    }
    
    public void onRender3D() {
    }
    
    static {
        MoonBreak.INSTANCE = new MoonBreak();
    }
}
