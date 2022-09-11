//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.manager;

import com.cocoapc.cherry.features.gui.*;
import java.util.*;
import java.awt.*;
import com.cocoapc.cherry.util.util2.*;
import com.cocoapc.cherry.util.*;

public class GuiFont implements Globals
{
    private final String[] fonts;
    public String fontName;
    public int fontSize;
    private CustomFont font;
    
    public GuiFont() {
        this.fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);
        this.fontName = "Tahoma";
        this.fontSize = 16;
        this.font = new CustomFont(new Font(this.fontName, 0, this.fontSize), true, false);
    }
    
    public void setFont() {
        this.font = new CustomFont(new Font(this.fontName, 0, this.fontSize), true, false);
    }
    
    public void reset() {
        this.setFont("Tahoma");
        this.setFontSize(16);
        this.setFont();
    }
    
    public boolean setFont(final String fontName) {
        for (final String font : this.fonts) {
            if (fontName.equalsIgnoreCase(font)) {
                this.fontName = font;
                this.setFont();
                return true;
            }
        }
        return false;
    }
    
    public void setFontSize(final int size) {
        this.fontSize = size;
        this.setFont();
    }
    
    public String setRandomFont() {
        this.fontName = RenderUtil.getRandomFont();
        this.setFont();
        return this.fontName;
    }
    
    public void drawStringWithShadow(final String string, final float x, final float y, final int colour) {
        this.drawString(string, x, y, colour, true);
    }
    
    public float drawString(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.font.drawStringWithShadow(string, (double)x, (double)y, colour);
        }
        return this.font.drawString(string, x, y, colour);
    }
    
    public void drawStringRainbow(final String string, final float x, final float y, final boolean shadow) {
        if (shadow) {
            this.font.drawStringWithShadow(string, (double)x, (double)y, Rainbow.getColour().getRGB());
        }
        else {
            this.font.drawString(string, x, y, Rainbow.getColour().getRGB());
        }
    }
    
    public int getTextHeight() {
        return this.font.getStringHeight();
    }
    
    public int getTextWidth(final String string) {
        return this.font.getStringWidth(string);
    }
}
