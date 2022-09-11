//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.noti;

import com.cocoapc.cherry.util.util2.*;
import java.awt.*;

public class FontManager
{
    public static CFontRenderer fontRenderer;
    public static CFontRenderer bigFontRenderer;
    
    public void init() {
        final String fontFile = "/assets/minecraft/font/crepe.ttf";
        final int[] colorCode = new int[0];
        FontManager.fontRenderer = new CFontRenderer(new CFont.CustomFont(fontFile, 20.0f, 0), true, false);
        FontManager.bigFontRenderer = new CFontRenderer(new CFont.CustomFont(fontFile, 30.0f, 0), true, false, colorCode);
    }
    
    public int getWidth(final String str) {
        return FontManager.fontRenderer.getStringWidth(str);
    }
    
    public int getHeight() {
        return FontManager.fontRenderer.getHeight() + 2;
    }
    
    public void draw(final String str, final int x, final int y, final int color) {
        FontManager.fontRenderer.drawString(str, (float)x, (float)y, color);
    }
    
    public void draw(final String str, final int x, final int y, final Color color) {
        FontManager.fontRenderer.drawString(str, (float)x, (float)y, color.getRGB());
    }
    
    public void drawWithShadow(final String str, final int x, final int y, final int color) {
        FontManager.fontRenderer.drawStringWithShadow(str, x, y, color);
    }
    
    public void drawWithShadow(final String str, final int x, final int y, final Color color) {
        FontManager.fontRenderer.drawStringWithShadow(str, x, y, color.getRGB());
    }
}
