//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.manager;

import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.features.gui.*;
import java.awt.*;

public class MenuFont implements Globals
{
    private final CustomFont menuFont;
    private final CustomFont headerFont;
    
    public MenuFont() {
        this.menuFont = new CustomFont(new Font("Tahoma", 1, 21), true, false);
        this.headerFont = new CustomFont(new Font("Tahoma", 1, 41), true, false);
    }
    
    public void drawStringWithShadow(final String string, final float x, final float y, final int colour) {
        this.drawString(string, x, y, colour, true);
    }
    
    public float drawString(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.menuFont.drawStringWithShadow(string, (double)x, (double)y, colour);
        }
        return this.menuFont.drawString(string, x, y, colour);
    }
    
    public float drawStringBig(final String string, final float x, final float y, final int colour, final boolean shadow) {
        if (shadow) {
            return this.headerFont.drawStringWithShadow(string, (double)x, (double)y, colour);
        }
        return this.headerFont.drawString(string, x, y, colour);
    }
    
    public int getTextHeight() {
        return this.menuFont.getStringHeight();
    }
    
    public int getTextWidth(final String string) {
        return this.menuFont.getStringWidth(string);
    }
}
