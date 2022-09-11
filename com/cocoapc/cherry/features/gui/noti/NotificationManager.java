//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.noti;

import java.util.*;

public class NotificationManager
{
    private static final List<Notification> notifications;
    
    public static void show(final Notification notification) {
        NotificationManager.notifications.add(0, notification);
        notification.show();
    }
    
    public void render() {
        try {
            if (!NotificationManager.notifications.isEmpty()) {
                for (int notificationCount = 0; notificationCount <= NotificationManager.notifications.size(); ++notificationCount) {
                    final Notification currentNotification = NotificationManager.notifications.get(notificationCount);
                    if (currentNotification != null) {
                        if (!currentNotification.isShown()) {
                            NotificationManager.notifications.remove(currentNotification);
                        }
                        currentNotification.render(notificationCount * 34 + 40);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        notifications = new ArrayList<Notification>();
    }
}
