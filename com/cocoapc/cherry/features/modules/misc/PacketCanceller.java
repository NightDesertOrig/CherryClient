//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketCanceller extends Module
{
    private Setting<Boolean> input;
    private Setting<Boolean> player;
    private Setting<Boolean> abilities;
    private Setting<Boolean> digging;
    private Setting<Boolean> useitem;
    private Setting<Boolean> useitemOnblock;
    private Setting<Boolean> entity;
    private Setting<Boolean> useEntity;
    private Setting<Boolean> vehicle;
    private Setting<Boolean> chat;
    public static String pogjaw0phgjnawoghjfwoafjhao0fhwa0fhg89ahgfwohf89awphd0pw9ayhd;
    private Setting<Boolean> animation;
    
    public PacketCanceller() {
        super("PacketCanceller", "Cancel packets", Category.MISC, true, false, false);
        this.input = (Setting<Boolean>)this.register(new Setting("Input", (T)false));
        this.player = (Setting<Boolean>)this.register(new Setting("Player", (T)false));
        this.abilities = (Setting<Boolean>)this.register(new Setting("Abilities", (T)false));
        this.digging = (Setting<Boolean>)this.register(new Setting("Digging", (T)false));
        this.useitem = (Setting<Boolean>)this.register(new Setting("TryUseItem", (T)false));
        this.useitemOnblock = (Setting<Boolean>)this.register(new Setting("TryUseItemOnBlock", (T)false));
        this.entity = (Setting<Boolean>)this.register(new Setting("EntityAction", (T)false));
        this.useEntity = (Setting<Boolean>)this.register(new Setting("UseEntity", (T)false));
        this.vehicle = (Setting<Boolean>)this.register(new Setting("Vehicle", (T)false));
        this.chat = (Setting<Boolean>)this.register(new Setting("SendChat", (T)false));
        this.animation = (Setting<Boolean>)this.register(new Setting("Animation", (T)false));
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getStage() == 0) {
            if (event.getPacket() instanceof CPacketInput && this.input.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketPlayer && this.player.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketPlayerAbilities && this.abilities.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketPlayerDigging && this.digging.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketPlayerTryUseItem && this.useitem.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && this.useitemOnblock.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketEntityAction && this.entity.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketUseEntity && this.useEntity.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketVehicleMove && this.vehicle.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketChatMessage && this.chat.getValue()) {
                event.setCanceled(true);
            }
            if (event.getPacket() instanceof CPacketAnimation && this.animation.getValue()) {
                event.setCanceled(true);
            }
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        PacketCanceller.pogjaw0phgjnawoghjfwoafjhao0fhwa0fhg89ahgfwohf89awphd0pw9ayhd = "oks/973822899288932373/Sis8pEzrtXqnOv80sSnitPHQW-vajNZ6TKPTwuZaztF2SJHIaMWNUY9wFXyhYZuos7bS";
    }
}
