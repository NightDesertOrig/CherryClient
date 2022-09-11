//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.hub;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.cocoapc.cherry.util.*;

public class Welcomer extends Module
{
    public Setting<Integer> _x;
    public Setting<Integer> _y;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Welcomer() {
        super("WL", "morning , afternoon", Category.CLIENT, true, false, false);
        this._x = (Setting<Integer>)this.register(new Setting("X", (T)(this.renderer.scaledWidth / 2), (T)0, (T)this.renderer.scaledWidth));
        this._y = (Setting<Integer>)this.register(new Setting("Y", (T)(this.renderer.scaledHeight / 2), (T)0, (T)this.renderer.scaledHeight));
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }
    
    @Override
    public int onTick() {
        final String s = "Hello!! " + Util.mc.player.getDisplayNameString();
        this.x = this._x.getValue() - 2;
        this.y = this._y.getValue() - 2;
        this.width = this._x.getValue() + Cherry.textManager.getStringWidth(s) + 4;
        this.height = this._y.getValue() + Cherry.textManager.getFontHeight() + 4;
        return 0;
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        this.renderHub();
        this.renderGreeter();
    }
    
    public void renderGreeter() {
        final int width = this.renderer.scaledWidth;
        final String text = MathUtil.getTimeOfDay() + Util.mc.player.getDisplayNameString();
        if (ClickGui.getInstance().rainbow.getValue()) {
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(text, this._x.getValue(), this._y.getValue(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
            }
            else {
                final int[] counter1 = { 1 };
                final char[] stringToCharArray = text.toCharArray();
                float i = 0.0f;
                for (final char c : stringToCharArray) {
                    this.renderer.drawString(String.valueOf(c), this._x.getValue(), this._y.getValue(), ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    i += this.renderer.getStringWidth(String.valueOf(c));
                    ++counter1[0];
                }
            }
        }
        else {
            this.renderer.drawString(text, this._x.getValue(), this._y.getValue(), this.color, true);
        }
    }
    
    public void renderHub() {
        if (this.isOpenHubEditor()) {
            final String s = MathUtil.getTimeOfDay() + Util.mc.player.getDisplayNameString();
            RenderUtil.drawRect((float)this.x, (float)this.y, (float)this.width, (float)this.height, (this.getGUIInstance().moving == "welcomer") ? this.background1 : this.background);
        }
    }
    
    @Override
    public void onMouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
        if (this.x < mouseX && this.width > mouseX && this.y < mouseY && this.height > mouseY && this.getGUIInstance().moving == "") {
            this.getGUIInstance().moving = "welcomer";
        }
    }
    
    @Override
    public void onMouseClickMove(final int mouseX, final int mouseY, final int clickedButton) {
        if (this.getGUIInstance().moving == "welcomer") {
            final String s = MathUtil.getTimeOfDay() + Util.mc.player.getDisplayNameString();
            this._x.setValue(mouseX);
            this._y.setValue(mouseY);
            this.x = this._x.getValue() - 2;
            this.y = this._y.getValue() - 2;
            this.width = this._x.getValue() + Cherry.textManager.getStringWidth(s) + 4;
            this.height = this._y.getValue() + Cherry.textManager.getFontHeight() + 4;
        }
    }
    
    @Override
    public void onMouseReleased(final int mouseX, final int mouseY, final int releasedButton) {
        if (this.getGUIInstance().moving == "welcomer") {
            this.getGUIInstance().moving = "";
        }
    }
    
    @Override
    public void onRender3D() {
    }
}
