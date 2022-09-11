//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.manager;

import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.features.gui.font.*;
import java.awt.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.cocoapc.cherry.*;
import net.minecraft.util.math.*;

public class TextManager extends Feature
{
    private final Timer idleTimer;
    public int scaledWidth;
    public int scaledHeight;
    public int scaleFactor;
    private final CustomFont headerFonte;
    private final CustomFont headerFont;
    private final CustomFont headerFont2;
    private final CustomFont headerFonts;
    private CustomFont customFont;
    private boolean idling;
    
    public TextManager() {
        this.idleTimer = new Timer();
        this.headerFonte = new CustomFont(new Font("Tahoma", 1, 35), true, false);
        this.headerFont = new CustomFont(new Font("Tahoma", 1, 25), true, false);
        this.headerFont2 = new CustomFont(new Font("Tahoma", 1, 15), true, false);
        this.headerFonts = new CustomFont(new Font("Tahoma", 1, 10), true, false);
        this.customFont = new CustomFont(new Font("Verdana", 0, 17), true, false);
        this.updateResolution();
    }
    
    public void init(final boolean startup) {
        final FontMod cFont = (FontMod)Cherry.moduleManager.getModuleByClass((Class)FontMod.class);
        try {
            this.setFontRenderer(new Font((String)cFont.fontName.getValue(), (int)cFont.fontStyle.getValue(), (int)cFont.fontSize.getValue()), (boolean)cFont.antiAlias.getValue(), (boolean)cFont.fractionalMetrics.getValue());
        }
        catch (Exception ex) {}
    }
    
    public void drawStringWithShadow(final String text, final float x, final float y, final int color) {
        this.drawString(text, x, y, color, true);
    }
    
    public void drawString(final String text, final float x, final float y, final int color, final boolean shadow) {
        if (Cherry.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            if (shadow) {
                this.customFont.drawStringWithShadow(text, (double)x, (double)y, color);
            }
            else {
                this.customFont.drawString(text, x, y, color);
            }
            return;
        }
        TextManager.mc.fontRenderer.drawString(text, x, y, color, shadow);
    }
    
    public int getStringWidth(final String text) {
        if (Cherry.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            return this.customFont.getStringWidth(text);
        }
        return TextManager.mc.fontRenderer.getStringWidth(text);
    }
    
    public int getFontHeight() {
        if (Cherry.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            final String text = "A";
            return this.customFont.getStringHeight(text);
        }
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public void setFontRenderer(final Font font, final boolean antiAlias, final boolean fractionalMetrics) {
        this.customFont = new CustomFont(font, antiAlias, fractionalMetrics);
    }
    
    public Font getCurrentFont() {
        return this.customFont.getFont();
    }
    
    public void updateResolution() {
        this.scaledWidth = TextManager.mc.displayWidth;
        this.scaledHeight = TextManager.mc.displayHeight;
        this.scaleFactor = 1;
        final boolean flag = TextManager.mc.isUnicode();
        int i = TextManager.mc.gameSettings.guiScale;
        if (i == 0) {
            i = 1000;
        }
        while (this.scaleFactor < i && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        final double scaledWidthD = this.scaledWidth / this.scaleFactor;
        final double scaledHeightD = this.scaledHeight / this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(scaledWidthD);
        this.scaledHeight = MathHelper.ceil(scaledHeightD);
    }
    
    public String getIdleSign() {
        if (this.idleTimer.passedMs(500L)) {
            this.idling = !this.idling;
            this.idleTimer.reset();
        }
        if (this.idling) {
            return "_";
        }
        return "";
    }
    
    public void drawStringSmall(final String s, final float x, final float v, final int rgb, final boolean b) {
    }
    
    public void drawStringBig(final String crepe, final float x, final float v, final int rgb, final boolean b) {
    }
    
    public float drawStringBig1(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.headerFont.drawStringWithShadow(string, (double)x, (double)y, colour);
        }
        return this.headerFont.drawString(string, x, y, colour);
    }
    
    public float drawStringBige(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.headerFonte.drawStringWithShadow(string, (double)x, (double)y, colour);
        }
        return this.headerFonte.drawString(string, x, y, colour);
    }
    
    public float drawStringBig2(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.headerFont2.drawStringWithShadow(string, (double)x, (double)y, colour);
        }
        return this.headerFont2.drawString(string, x, y, colour);
    }
    
    public float drawStringBig3(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.headerFonts.drawStringWithShadow(string, (double)x, (double)y, colour);
        }
        return this.headerFonts.drawString(string, x, y, colour);
    }
}
