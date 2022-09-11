//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.fonts;

import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.io.*;
import java.util.*;

public class FontUtil
{
    public static volatile int completed;
    public static MinecraftFontRenderer normal;
    public static MinecraftFontRenderer small;
    public static MinecraftFontRenderer comfortaa;
    public static MinecraftFontRenderer comfortaa2;
    public static MinecraftFontRenderer impact2;
    private static Font normal_;
    private static Font small_;
    private static Font comfortaa_;
    private static Font comfortaa_2;
    private static Font impact_2;
    
    private static Font getFont(final Map<String, Font> locationMap, final String location, final int size) {
        Font font = null;
        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(0, (float)size);
            }
            else {
                final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("font/" + location)).getInputStream();
                font = Font.createFont(0, is);
                locationMap.put(location, font);
                font = font.deriveFont(0, (float)size);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, 10);
        }
        return font;
    }
    
    public static boolean hasLoaded() {
        return FontUtil.completed >= 3;
    }
    
    public static void bootstrap() {
        final HashMap<String, Font> locationMap;
        new Thread(() -> {
            locationMap = new HashMap<String, Font>();
            FontUtil.normal_ = getFont(locationMap, "UI.ttf", 40);
            FontUtil.small_ = getFont(locationMap, "SF-UI-Display-Medium.ttf", 15);
            FontUtil.comfortaa_ = getFont(locationMap, "SF-UI-Display-Medium.ttf", 20);
            FontUtil.comfortaa_2 = getFont(locationMap, "wt.ttf", 20);
            FontUtil.impact_2 = getFont(locationMap, "impact.ttf", 20);
            ++FontUtil.completed;
            return;
        }).start();
        final HashMap<String, Font> locationMap2;
        new Thread(() -> {
            locationMap2 = new HashMap<String, Font>();
            ++FontUtil.completed;
            return;
        }).start();
        final HashMap<String, Font> locationMap3;
        new Thread(() -> {
            locationMap3 = new HashMap<String, Font>();
            ++FontUtil.completed;
            return;
        }).start();
        while (!hasLoaded()) {
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FontUtil.normal = new MinecraftFontRenderer(FontUtil.normal_, true, true);
        FontUtil.small = new MinecraftFontRenderer(FontUtil.small_, true, true);
        FontUtil.comfortaa = new MinecraftFontRenderer(FontUtil.comfortaa_, true, true);
        FontUtil.comfortaa2 = new MinecraftFontRenderer(FontUtil.comfortaa_2, true, true);
    }
}
