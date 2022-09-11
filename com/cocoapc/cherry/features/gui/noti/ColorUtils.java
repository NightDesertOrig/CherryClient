//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.noti;

import com.cocoapc.cherry.util.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.texture.*;
import java.nio.*;

public class ColorUtils implements Util
{
    private static int[] pixelValues;
    private static ScaledResolution sr;
    
    public static int transparency(final int color, final double alpha) {
        final Color c = new Color(color);
        final float r = 0.003921569f * c.getRed();
        final float g = 0.003921569f * c.getGreen();
        final float b = 0.003921569f * c.getBlue();
        return new Color(r, g, b, (float)alpha).getRGB();
    }
    
    public static Color blend(final Color color1, final Color color2) {
        final float ir = 0.5f;
        final float[] rgb1 = new float[3];
        final float[] rgb2 = new float[3];
        color1.getColorComponents(rgb1);
        color2.getColorComponents(rgb2);
        final Color color3 = new Color(rgb1[0] * 0.5f + rgb2[0] * ir, rgb1[1] * 0.5f + rgb2[1] * ir, rgb1[2] * 0.5f + rgb2[2] * ir);
        return color3;
    }
    
    public static Color colorFromInt(final int color) {
        final Color c = new Color(color);
        final Color cn = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255);
        return cn;
    }
    
    public static int getHealthColor(final float health, final float maxHealth) {
        final float[] fractions = { 0.0f, 0.5f, 1.0f };
        final Color[] colors = { new Color(108, 20, 20), new Color(255, 0, 60), Color.GREEN };
        final float progress = health / maxHealth;
        return blendColors(fractions, colors, progress).brighter().getRGB();
    }
    
    public static Color blendColors(final float[] fractions, final Color[] colors, final float progress) {
        if (fractions.length == colors.length) {
            final int[] indices = getFractionIndices(fractions, progress);
            final float[] range = { fractions[indices[0]], fractions[indices[1]] };
            final Color[] colorRange = { colors[indices[0]], colors[indices[1]] };
            final float max = range[1] - range[0];
            final float value = progress - range[0];
            final float weight = value / max;
            final Color color = blend(colorRange[0], colorRange[1], 1.0f - weight);
            return color;
        }
        throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
    }
    
    public static int[] getFractionIndices(final float[] fractions, final float progress) {
        final int[] range = new int[2];
        int startPoint;
        for (startPoint = 0; startPoint < fractions.length && fractions[startPoint] <= progress; ++startPoint) {}
        if (startPoint >= fractions.length) {
            startPoint = fractions.length - 1;
        }
        range[0] = startPoint - 1;
        range[1] = startPoint;
        return range;
    }
    
    public static void onRender2D() {
        ColorUtils.sr = new ScaledResolution(ColorUtils.mc);
        int width = 0;
        int height = 0;
        IntBuffer pixelBuffer = null;
        width = ColorUtils.sr.getScaledWidth();
        height = ColorUtils.sr.getScaledHeight();
        final int var6 = width * height;
        if (pixelBuffer == null || pixelBuffer.capacity() < var6) {
            pixelBuffer = BufferUtils.createIntBuffer(var6);
            ColorUtils.pixelValues = new int[var6];
        }
        GL11.glPixelStorei(3333, 1);
        GL11.glPixelStorei(3317, 1);
        pixelBuffer.clear();
        GL11.glReadPixels(0, height, width, height, 32993, 33639, pixelBuffer);
        pixelBuffer.get(ColorUtils.pixelValues);
        TextureUtil.processPixelValues(ColorUtils.pixelValues, width, height);
    }
    
    public static int pickRGB(final int x, final int y) {
        return ColorUtils.pixelValues[45 * ColorUtils.sr.getScaleFactor() * 180 + 10];
    }
    
    public static int maxAlpha(final int hex) {
        final int shifted = hex << 2 & 0x10;
        return -16777216 + shifted >> 2 & 0x10;
    }
    
    public static int rainbow(final int delay, final float saturation, final float brightness, final int index) {
        double rainbow = Math.ceil((double)((System.currentTimeMillis() + delay + index * 2) / 8L));
        rainbow %= 360.0;
        return Color.getHSBColor((float)(rainbow / 360.0), saturation, brightness).getRGB();
    }
    
    public static int asolfo(final int delay) {
        float hue;
        for (hue = (float)(System.currentTimeMillis() % delay); hue > delay; hue -= delay) {}
        hue /= delay;
        if (hue > 0.5) {
            hue = 0.5f - (hue - 0.5f);
        }
        hue += 0.5f;
        return Color.HSBtoRGB(hue, 0.6f, 1.0f);
    }
    
    public static int opacity(final int width, final float offset) {
        final int op = 0;
        final float offs = 255.0f - Math.abs(width / 2 - offset) * 1.8f;
        final Color c = new Color(255, 255, 255, (int)Math.min(Math.max(0.0f, offs), 255.0f));
        return c.getRGB();
    }
    
    public static void glColor(final int hex, final float alpha) {
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void glColor(final int hex) {
        final float alpha = (hex >> 24 & 0xFF) / 255.0f;
        final float red = (hex >> 16 & 0xFF) / 255.0f;
        final float green = (hex >> 8 & 0xFF) / 255.0f;
        final float blue = (hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static int getAlpha(final int hex) {
        return hex >> 24 & 0xFF;
    }
    
    public static int getRed(final int hex) {
        return hex >> 16 & 0xFF;
    }
    
    public static int getBlue(final int hex) {
        return hex & 0xFF;
    }
    
    public static int getGreen(final int hex) {
        return hex >> 8 & 0xFF;
    }
    
    public static Color blend(final Color color1, final Color color2, final double ratio) {
        final float r = (float)ratio;
        final float ir = 1.0f - r;
        final float[] rgb1 = color1.getColorComponents(new float[3]);
        final float[] rgb2 = color2.getColorComponents(new float[3]);
        float red = rgb1[0] * r + rgb2[0] * ir;
        float green = rgb1[1] * r + rgb2[1] * ir;
        float blue = rgb1[2] * r + rgb2[2] * ir;
        if (red < 0.0f) {
            red = 0.0f;
        }
        else if (red > 255.0f) {
            red = 255.0f;
        }
        if (green < 0.0f) {
            green = 0.0f;
        }
        else if (green > 255.0f) {
            green = 255.0f;
        }
        if (blue < 0.0f) {
            blue = 0.0f;
        }
        else if (blue > 255.0f) {
            blue = 255.0f;
        }
        return new Color(red, green, blue);
    }
}
