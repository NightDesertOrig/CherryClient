//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.noti;

import java.awt.*;
import net.minecraft.entity.*;

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
    
    public static Color colorCode(final String code, final int alpha) {
        final String var2 = code.toLowerCase();
        byte var3 = -1;
        switch (var2.hashCode()) {
            case 48: {
                if (var2.equals("0")) {
                    var3 = 0;
                    break;
                }
                break;
            }
            case 49: {
                if (var2.equals("1")) {
                    var3 = 1;
                    break;
                }
                break;
            }
            case 50: {
                if (var2.equals("2")) {
                    var3 = 2;
                    break;
                }
                break;
            }
            case 51: {
                if (var2.equals("3")) {
                    var3 = 3;
                    break;
                }
                break;
            }
            case 52: {
                if (var2.equals("4")) {
                    var3 = 4;
                    break;
                }
                break;
            }
            case 53: {
                if (var2.equals("5")) {
                    var3 = 5;
                    break;
                }
                break;
            }
            case 54: {
                if (var2.equals("6")) {
                    var3 = 6;
                    break;
                }
                break;
            }
            case 55: {
                if (var2.equals("7")) {
                    var3 = 7;
                    break;
                }
                break;
            }
            case 56: {
                if (var2.equals("8")) {
                    var3 = 8;
                    break;
                }
                break;
            }
            case 57: {
                if (var2.equals("9")) {
                    var3 = 9;
                    break;
                }
                break;
            }
            case 97: {
                if (var2.equals("a")) {
                    var3 = 10;
                    break;
                }
                break;
            }
            case 98: {
                if (var2.equals("b")) {
                    var3 = 11;
                    break;
                }
                break;
            }
            case 99: {
                if (var2.equals("c")) {
                    var3 = 12;
                    break;
                }
                break;
            }
            case 100: {
                if (var2.equals("d")) {
                    var3 = 13;
                    break;
                }
                break;
            }
            case 101: {
                if (var2.equals("e")) {
                    var3 = 14;
                    break;
                }
                break;
            }
        }
        switch (var3) {
            case 0: {
                return new Color(0, 0, 0, alpha);
            }
            case 1: {
                return new Color(0, 0, 170, alpha);
            }
            case 2: {
                return new Color(0, 170, 0, alpha);
            }
            case 3: {
                return new Color(0, 170, 170, alpha);
            }
            case 4: {
                return new Color(170, 0, 0, alpha);
            }
            case 5: {
                return new Color(170, 0, 170, alpha);
            }
            case 6: {
                return new Color(255, 170, 0, alpha);
            }
            case 7: {
                return new Color(170, 170, 170, alpha);
            }
            case 8: {
                return new Color(85, 85, 85, alpha);
            }
            case 9: {
                return new Color(85, 85, 255, alpha);
            }
            case 10: {
                return new Color(85, 255, 85, alpha);
            }
            case 11: {
                return new Color(85, 255, 255, alpha);
            }
            case 12: {
                return new Color(255, 85, 85, alpha);
            }
            case 13: {
                return new Color(255, 85, 255, alpha);
            }
            case 14: {
                return new Color(255, 255, 85, alpha);
            }
            default: {
                return new Color(255, 255, 255, alpha);
            }
        }
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
        Color color3 = null;
        try {
            color3 = new Color(red, green, blue);
        }
        catch (IllegalArgumentException ex) {}
        return color3;
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
    
    public static Color getHealthColor(final EntityLivingBase entityLivingBase) {
        final float health = entityLivingBase.getHealth();
        final float[] fractions = { 0.0f, 0.15f, 0.55f, 0.7f, 0.9f };
        final Color[] colors = { new Color(133, 0, 0), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN };
        final float progress = health / entityLivingBase.getMaxHealth();
        return (health >= 0.0f) ? blendColors(fractions, colors, progress).brighter() : colors[0];
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
    
    public static Color rainbow(final int delay, final int rainbowSaturation, final int rainbowBrightness) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        return Color.getHSBColor((float)((rainbowState %= 360.0) / 360.0), rainbowSaturation / 255.0f, rainbowBrightness / 255.0f);
    }
}
