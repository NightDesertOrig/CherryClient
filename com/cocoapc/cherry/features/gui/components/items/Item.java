//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.items;

import com.cocoapc.cherry.features.*;

public class Item extends Feature
{
    protected float x;
    private boolean hidden;
    protected int width;
    protected float y;
    protected int height;
    
    public int getHeight() {
        return this.height;
    }
    
    public void setWidth(final int n) {
        this.width = n;
    }
    
    public void setLocation(final float f, final float f2) {
        this.x = f;
        this.y = f2;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void update() {
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
    }
    
    public void drawScreen(final int n, final int n2, final float f) {
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Item(final String string) {
        super(string);
    }
    
    public void onKeyTyped(final char c, final int n) {
    }
    
    public float getX() {
        return this.x;
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
    }
    
    public void setHeight(final int n) {
        this.height = n;
    }
    
    public boolean setHidden(final boolean bl) {
        return this.hidden = bl;
    }
}
