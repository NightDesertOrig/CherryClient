//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import com.cocoapc.cherry.features.command.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Bow32k extends Module
{
    public Setting<Boolean> Bows;
    public Setting<Boolean> bypass;
    public Setting<Integer> Timeout;
    public Setting<Integer> spoofs;
    private boolean shooting;
    private long lastShootTime;
    public Setting<Boolean> debug;
    
    public Bow32k() {
        super("32kBow", "Uno hitter w bows", Category.COMBAT, true, false, false);
        this.bypass = (Setting<Boolean>)this.register(new Setting("AntiKick", (T)false));
        this.Bows = (Setting<Boolean>)this.register(new Setting("Bow", (T)true));
        this.Timeout = (Setting<Integer>)this.register(new Setting("ShotTiming", (T)500, (T)100, (T)600));
        this.spoofs = (Setting<Integer>)this.register(new Setting("ShotSpeed", (T)100, (T)1, (T)300));
        this.debug = (Setting<Boolean>)this.register(new Setting("ChatNotify", (T)false));
    }
    
    private void doSpoofs() {
        if (System.currentTimeMillis() - this.lastShootTime >= this.Timeout.getValue()) {
            this.shooting = true;
            this.lastShootTime = System.currentTimeMillis();
            Bow32k.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Bow32k.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            for (int i = 0; i < this.spoofs.getValue(); ++i) {
                if (this.bypass.getValue()) {
                    Bow32k.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Bow32k.mc.player.posX, Bow32k.mc.player.posY + 1.0E-10, Bow32k.mc.player.posZ, false));
                    Bow32k.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Bow32k.mc.player.posX, Bow32k.mc.player.posY - 1.0E-10, Bow32k.mc.player.posZ, true));
                }
                else {
                    Bow32k.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Bow32k.mc.player.posX, Bow32k.mc.player.posY - 1.0E-10, Bow32k.mc.player.posZ, true));
                    Bow32k.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Bow32k.mc.player.posX, Bow32k.mc.player.posY + 1.0E-10, Bow32k.mc.player.posZ, false));
                    if (null != null) {
                        return;
                    }
                }
            }
            if (this.debug.getValue()) {
                Command.sendMessage("E");
            }
            this.shooting = false;
        }
    }
    
    @Override
    public void onEnable() {
        if (this.isEnabled()) {
            this.shooting = false;
            this.lastShootTime = System.currentTimeMillis();
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() != 0) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayerDigging) {
            final CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket();
            final ItemStack itemStack2;
            if (cPacketPlayerDigging.getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM && !(itemStack2 = Bow32k.mc.player.getHeldItem(EnumHand.MAIN_HAND)).isEmpty() && itemStack2.getItem() != null && itemStack2.getItem() instanceof ItemBow && this.Bows.getValue()) {
                this.doSpoofs();
                if (this.debug.getValue()) {
                    Command.sendMessage("trying to spoof");
                }
            }
        }
        else {
            final CPacketPlayerTryUseItem cPacketPlayerTryUseItem;
            final ItemStack itemStack3;
            if (send.getPacket() instanceof CPacketPlayerTryUseItem && (cPacketPlayerTryUseItem = (CPacketPlayerTryUseItem)send.getPacket()).getHand() == EnumHand.MAIN_HAND && !(itemStack3 = Bow32k.mc.player.getHeldItem(EnumHand.MAIN_HAND)).isEmpty() && itemStack3.getItem() != null) {
                this.doSpoofs();
                this.doSpoofs();
                this.doSpoofs();
            }
        }
    }
}
