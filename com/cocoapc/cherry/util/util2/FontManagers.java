//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import com.cocoapc.cherry.features.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class FontManagers extends Feature
{
    public CFontRenderer iconFont;
    public Font fontRenderer_;
    public Font fontRenderer_2;
    public Font fontRenderer_3;
    public Font fontRenderer_4;
    public Font fontRenderer_5;
    public Font impact_5;
    public Font iconFont_;
    public CFontRenderer fontRenderer;
    public CFontRenderer fontRenderer2;
    public CFontRenderer fontRenderer3;
    public CFontRenderer fontRenderer4;
    public CFontRenderer fontRenderer5;
    public CFontRenderer impact5;
    
    private static Font getFont(final Map<String, Font> locationMap, final String location, final int size) {
        Font font;
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
    
    public void load() {
        final Map<String, Font> locationmap = new HashMap<String, Font>();
        this.fontRenderer_ = getFont(locationmap, "Helvetica.ttf", 20);
        this.fontRenderer_2 = getFont(locationmap, "UI.ttf", 20);
        this.fontRenderer_3 = getFont(locationmap, "se.ttf", 35);
        this.fontRenderer_4 = getFont(locationmap, "icon.ttf", 45);
        this.fontRenderer_5 = getFont(locationmap, "ll.ttf", 35);
        this.impact_5 = getFont(locationmap, "impact.ttf", 20);
        this.iconFont_ = getFont(locationmap, "Helvetica.ttf", 35);
        this.fontRenderer = new CFontRenderer(this.fontRenderer_, true, true);
        this.fontRenderer2 = new CFontRenderer(this.fontRenderer_2, true, true);
        this.iconFont = new CFontRenderer(this.iconFont_, true, true);
        this.fontRenderer3 = new CFontRenderer(this.fontRenderer_3, true, true);
        this.fontRenderer4 = new CFontRenderer(this.fontRenderer_4, true, true);
        this.fontRenderer5 = new CFontRenderer(this.fontRenderer_5, true, true);
        this.impact5 = new CFontRenderer(this.impact_5, true, true);
    }
    
    public int getWidth(final String str) {
        return this.fontRenderer.getStringWidth(str);
    }
    
    public int getHeight() {
        return this.fontRenderer.getHeight() + 2;
    }
    
    public void draw(final String str, final int x, final int y, final int color, final float scale) {
        this.fontRenderer.drawString(str, (float)x, (float)y, color, scale);
    }
    
    public void draw2(final String str, final int x, final int y, final int color, final float scale) {
        this.fontRenderer2.drawString(str, (float)x, (float)y, color, scale);
    }
    
    public void draw(final String str, final int x, final int y, final Color color, final float scale) {
        this.fontRenderer.drawString(str, (float)x, (float)y, color.getRGB(), scale);
    }
    
    public int getIconWidth() {
        return this.iconFont.getStringWidth("q");
    }
    
    public int getIconHeight() {
        return this.iconFont.getHeight();
    }
    
    public void drawIcon(final int x, final int y, final int color, final float scale) {
        this.iconFont.drawString("q", (float)x, (float)y, color, scale);
    }
    
    public void drawIcon(final int x, final int y, final Color color, final float scale) {
        this.iconFont.drawString("+", (float)x, (float)y, color.getRGB(), scale);
    }
}
