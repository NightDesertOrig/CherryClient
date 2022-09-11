//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.noti;

import java.awt.*;

public class NotificationRenderer
{
    public static final int HEIGHT = 40;
    
    public static void renderNotification(final String title, final String msg, final float x, final float y, final int more) {
        final int width = FontManager.notifMsgFont.getStringWidth(msg) + 70;
        RenderUtil.drawRect(x, y, x + width, y + 40.0f, new Color(0, 0, 0, 140));
        RenderUtil.drawRect(x, y, x + 2.0f, y + 40.0f, new Color(240, 240, 240));
        FontManager.notifTitleFont.drawString(title, x + 15.0f, y + 9.0f, ColorUtil.toRGBA(240, 240, 240));
        FontManager.notifMsgFont.drawString(msg, x + 15.0f, y + 40.0f - FontManager.notifMsgFont.getHeight() - 8.0f, ColorUtil.toRGBA(240, 240, 240));
        if (more > 0) {
            FontManager.notifCounterFont.drawString(more + " more", x + width - FontManager.notifCounterFont.getStringWidth(more + " more") - 7.0f, y + 10.0f, ColorUtil.toRGBA(100, 100, 100));
        }
    }
    
    public static float getNotificationWidth(final String msg) {
        return (float)(FontManager.notifMsgFont.getStringWidth(msg) + 70);
    }
}
