//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.noti;

import java.awt.*;

public class ColorUtil
{
    public static int toARGB(final int r, final int g, final int b, final int a) {
        return new Color(r, g, b, a).getRGB();
    }
    
    public static int toRGBA(final int r, final int g, final int b) {
        return toRGBA(r, g, b, 255);
    }
    
    public static int toRGBA(final int r, final int g, final int b, final int a) {
        return (r << 16) + (g << 8) + b + (a << 24);
    }
    
    public static int toRGBA(final Color c) {
        return (c.getRed() << 16) + (c.getGreen() << 8) + c.getBlue() + (c.getAlpha() << 24);
    }
    
    public static int toRGBA(final float r, final float g, final float b, final float a) {
        return toRGBA((int)(r * 255.0f), (int)(g * 255.0f), (int)(b * 255.0f), (int)(a * 255.0f));
    }
    
    public static Color getColor(final int hex) {
        return new Color(hex);
    }
    
    public static int getRed(final int hex) {
        return hex >> 16 & 0xFF;
    }
    
    public static int getGreen(final int hex) {
        return hex >> 8 & 0xFF;
    }
    
    public static int getBlue(final int hex) {
        return hex & 0xFF;
    }
    
    public static int getHoovered(final int color, final boolean isHoovered) {
        return isHoovered ? ((color & 0x7F7F7F) << 1) : color;
    }
    
    public static Color dynamicRainbow(final long offset, final float brightness, final int speed) {
        final float hue = (System.nanoTime() + offset * speed) / 1.0E10f % 1.0f;
        final int color = (int)Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, brightness, 1.0f)), 16);
        return new Color(new Color(color).getRed() / 255.0f, new Color(color).getGreen() / 255.0f, new Color(color).getBlue() / 255.0f, new Color(color).getAlpha() / 255.0f);
    }
    
    public static int getRainbow(final int speed, final int offset, final float s, final float b) {
        float hue = (float)((System.currentTimeMillis() + offset) % speed);
        return Color.getHSBColor(hue /= speed, s, b).getRGB();
    }
}
