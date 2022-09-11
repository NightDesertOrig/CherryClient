//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.*;
import net.minecraft.entity.player.*;
import com.cocoapc.cherry.*;
import java.util.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.event.events.*;
import com.mojang.realmsclient.gui.*;
import java.awt.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.util.util2.*;
import java.util.stream.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;

public class NotiManager extends Feature
{
    public List<Notif> notifs;
    public List<EntityPlayer> players;
    private Map<String, Integer> popCounter;
    private int scaledWidth;
    private int scaledHeight;
    private int scaleFactor;
    
    public NotiManager() {
        this.notifs = new ArrayList<Notif>();
        this.players = new ArrayList<EntityPlayer>();
        this.popCounter = new HashMap<String, Integer>();
        this.scaleFactor = 0;
    }
    
    public void showNotification(final String msg) {
        if (NotiManager.mc.player == null) {
            return;
        }
        final Notif notif = new Notif(msg);
        for (final Notif notif2 : this.notifs) {
            final Notif n = notif2;
            notif2.y -= Cherry.m_font.getHeight() + 40;
        }
        this.updateResolution();
        notif.y = (float)(this.scaledHeight - 50);
        notif._y = (float)(this.scaledHeight - 50);
        this.notifs.add(notif);
    }
    
    public void onUpdate() {
        if (NotiManager.mc.world == null) {
            return;
        }
        if (Noti.INSTANCE.player.getValue()) {
            for (final EntityPlayer player : new ArrayList<EntityPlayer>(NotiManager.mc.world.playerEntities)) {
                if (!this.players.contains(player)) {
                    this.showNotification(player.getName() + " is coming towards you!");
                }
            }
            this.players = new ArrayList<EntityPlayer>(NotiManager.mc.world.playerEntities);
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
            if (packet.getOpCode() == 35 && packet.getEntity((World)NotiManager.mc.world) instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)packet.getEntity((World)NotiManager.mc.world);
                final Noti notification = Cherry.moduleManager.getModuleByClass(Noti.class);
                if (notification.pop.getValue()) {
                    final int pop = this.countPop(player.getName());
                    if (pop == 1) {
                        this.showNotification(player.getName() + " popped a totem!");
                    }
                    else {
                        this.showNotification(player.getName() + " popped " + pop + " totems!");
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerDeath(final PlayerDeathEvent event) {
        final Noti notification = Cherry.moduleManager.getModuleByClass(Noti.class);
        if (notification.death.getValue()) {
            final EntityPlayer player = event.player;
            if (player == null) {
                return;
            }
            final int pop = this.getPop(player.getName());
            if (pop == 0) {
                this.showNotification(ChatFormatting.RED + player.getName() + " dead!");
            }
            else {
                this.showNotification(ChatFormatting.RED + player.getName() + " dead after " + pop + " pop!");
            }
            if (this.popCounter.containsKey(player.getName())) {
                this.popCounter.remove(player.getName());
            }
        }
    }
    
    public int countPop(final String name) {
        if (!this.popCounter.containsKey(name)) {
            this.popCounter.put(name, 1);
            return 1;
        }
        this.popCounter.replace(name, this.popCounter.get(name) + 1);
        return this.popCounter.get(name);
    }
    
    public int getPop(final String name) {
        if (!this.popCounter.containsKey(name)) {
            return 0;
        }
        return this.popCounter.get(name);
    }
    
    public void onRender2D() {
        try {
            if (NotiManager.mc.player == null) {
                return;
            }
            for (final Notif notification : this.notifs) {
                this.updateResolution();
                final String msg = notification.msg;
                final int width = Cherry.m_font.getWidth(msg);
                RD.drawRect(this.scaledWidth - width - 110 + notification.offsetX, notification._y - 21.0f, (float)(width + 100), (float)(Cherry.m_font.getHeight() + 30), ColorUtil.toRGBA(new Color(35, 35, 35, 255)));
                RD.drawRect(this.scaledWidth - width - 110 + notification.offsetX, notification._y - 20.0f, (float)(width + 100), (float)(Cherry.m_font.getHeight() + 30), ColorUtil.toRGBA(new Color(45, 45, 45, 255)));
                RD.drawRect(this.scaledWidth - width - 110 + notification.offsetX, notification._y + Cherry.m_font.getHeight() + 10.0f, (width + 56) * ((notification.max - notification.ticks) / notification.max), 2.0f, ColorUtil.toRGBA(new Color(Noti.INSTANCE.RED.getValue(), Noti.INSTANCE.GREEN.getValue(), Noti.INSTANCE.BLUE.getValue(), Noti.INSTANCE.ALPHA.getValue())));
                Cherry.textManager.drawStringBig1(msg, this.scaledWidth - width - 107 + notification.offsetX, notification._y - 10.0f - 6.0f, ColorUtil.toRGBA(170, 202, 222), false);
                if (notification.ticks <= 0.0f) {
                    final Notif notif = notification;
                    notif.offsetX += (500.0f - notification.offsetX) / 10.0f;
                }
                else {
                    final Notif notif2 = notification;
                    --notif2.ticks;
                    final Notif notif3 = notification;
                    notif3.offsetX += (0.0f - notification.offsetX) / 4.0f;
                    final Notif notif4 = notification;
                    notif4._y += (notification.y - notification._y) / 4.0f;
                }
            }
            this.notifs = this.notifs.stream().filter(n -> (n.offsetX < 450.0f || n.ticks != 0.0f) && n._y >= -100.0f).collect((Collector<? super Object, ?, List<Notif>>)Collectors.toList());
        }
        catch (Exception ex) {}
    }
    
    public void updateResolution() {
        this.scaledWidth = NotiManager.mc.displayWidth;
        this.scaledHeight = NotiManager.mc.displayHeight;
        this.scaleFactor = 1;
        final boolean flag = NotiManager.mc.isUnicode();
        int i = NotiManager.mc.gameSettings.guiScale;
        if (i == 0) {
            i = 1000;
        }
        while (this.scaleFactor < i && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        final double scaledWidthD = this.scaledWidth / (double)this.scaleFactor;
        final double scaledHeightD = this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(scaledWidthD);
        this.scaledHeight = MathHelper.ceil(scaledHeightD);
    }
    
    public class Notif
    {
        public String msg;
        public float offsetX;
        public float y;
        public float _y;
        public float ticks;
        public float max;
        
        public Notif(final String msg) {
            this.offsetX = 300.0f;
            this.y = 0.0f;
            this._y = 0.0f;
            this.ticks = 0.0f;
            this.max = 0.0f;
            this.msg = msg;
            int fps = Minecraft.getDebugFPS();
            if (fps == 0) {
                fps = 60;
            }
            final int seconds = Cherry.moduleManager.getModuleByClass(Noti.class).time.getValue();
            this.ticks = (float)(seconds * fps);
            this.max = (float)(seconds * fps);
        }
    }
}
