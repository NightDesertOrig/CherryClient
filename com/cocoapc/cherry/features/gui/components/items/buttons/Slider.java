//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.items.buttons;

import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.features.gui.*;
import com.cocoapc.cherry.features.gui.components.*;
import java.util.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.mojang.realmsclient.gui.*;
import org.lwjgl.input.*;

public class Slider extends Button
{
    private final Number min;
    public Setting setting;
    private final Number max;
    private final int difference;
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
    
    public Slider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = setting.getMin();
        this.max = setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    public int getHeight() {
        return 14;
    }
    
    public boolean isHovering(final int n, final int n2) {
        for (final Component component : CherryGui.getClickGui().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() + 8.0f && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            this.setSettingFromX(n);
        }
    }
    
    public void drawScreen(final int n, final int n2, final float f) {
        this.dragSetting(n, n2);
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 9.5f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? 0 : 0);
        RenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4f) * this.partialMultiplier()), this.y + this.height - 0.5f, this.isHovering(n, n2) ? Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()));
        Cherry.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.getName()).append(" ").append(ChatFormatting.UNDERLINE).append((this.setting.getValue() instanceof Float) ? this.setting.getValue() : this.setting.getValue().doubleValue())), this.x + 2.3f, this.y - 1.7f - CherryGui.getClickGui().getTextOffset(), -1);
    }
    
    private float part() {
        return this.setting.getValue().floatValue() - this.min.floatValue();
    }
    
    private void setSettingFromX(final int n) {
        final float f = (n - this.x) / (this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            final double d = this.setting.getMin() + this.difference * f;
            this.setting.setValue(Math.round(10.0 * d) / 10.0);
        }
        else if (this.setting.getValue() instanceof Float) {
            final float f2 = this.setting.getMin() + this.difference * f;
            this.setting.setValue(Math.round(10.0f * f2) / 10.0f);
        }
        else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue(this.setting.getMin() + (int)(this.difference * f));
        }
    }
    
    private void dragSetting(final int n, final int n2) {
        if (this.isHovering(n, n2) && Mouse.isButtonDown(0)) {
            this.setSettingFromX(n);
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
}
