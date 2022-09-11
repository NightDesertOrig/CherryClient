//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.noti;

import java.util.concurrent.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NotificationManager
{
    public static final CopyOnWriteArrayList<Notif> notif;
    private int y;
    private int tick;
    
    public NotificationManager() {
        this.y = 0;
        this.tick = 0;
        EventUtil.register((Object)this);
    }
    
    public static void sendMessage(final String title) {
        NotificationManager.notif.add(new Notif(title, ""));
    }
    
    @SubscribeEvent
    public void onRender2D(final RenderGameOverlayEvent.Text event) {
        if (NotificationManager.notif.size() > 0) {
            final ScaledResolution sr = event.getResolution();
            final Notif n = NotificationManager.notif.get(0);
            NotificationRenderer.renderNotification(n.title, n.msg, sr.getScaledWidth() - NotificationRenderer.getNotificationWidth(n.msg) - 10.0f, (float)(sr.getScaledHeight() - this.y), NotificationManager.notif.size() - 1);
            final float speed = 5.0f - (Notification.INSTANCE.speed.getValue() - 1.5f);
            final int ticks = Minecraft.getDebugFPS() * Notification.INSTANCE.time.getValue().intValue();
            ++this.tick;
            if (this.tick > ticks) {
                if (this.y > 0.05) {
                    this.y += (int)(-this.y / speed);
                }
                else {
                    NotificationManager.notif.remove(0);
                    this.tick = 0;
                    this.y = 0;
                }
            }
            else {
                this.y += (int)((50 - this.y) / speed);
            }
        }
    }
    
    static {
        notif = new CopyOnWriteArrayList<Notif>();
    }
    
    private static class Notif
    {
        public String title;
        public String msg;
        
        public Notif(final String title, final String msg) {
            this.title = title;
            this.msg = msg;
        }
    }
}
