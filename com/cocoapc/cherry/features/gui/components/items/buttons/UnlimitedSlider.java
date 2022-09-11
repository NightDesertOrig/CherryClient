//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.items.buttons;

import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.features.gui.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class UnlimitedSlider extends Button
{
    public Setting setting;
    
    public boolean getState() {
        return true;
    }
    
    public int getHeight() {
        return 14;
    }
    
    public boolean isRight(final int n) {
        return n > this.x + (this.width + 7.4f) / 2.0f;
    }
    
    public void drawScreen(final int n, final int n2, final float f) {
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()));
        Cherry.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(" - ").append(this.setting.getName()).append(" ").append(ChatFormatting.GRAY).append(this.setting.getValue()).append(ChatFormatting.WHITE).append(" +")), this.x + 2.3f, this.y - 1.7f - CherryGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
            if (this.isRight(n)) {
                if (this.setting.getValue() instanceof Double) {
                    this.setting.setValue(this.setting.getValue() + 1.0);
                }
                else if (this.setting.getValue() instanceof Float) {
                    this.setting.setValue(this.setting.getValue() + 1.0f);
                }
                else if (this.setting.getValue() instanceof Integer) {
                    this.setting.setValue(this.setting.getValue() + 1);
                }
            }
            else if (this.setting.getValue() instanceof Double) {
                this.setting.setValue(this.setting.getValue() - 1.0);
            }
            else if (this.setting.getValue() instanceof Float) {
                this.setting.setValue(this.setting.getValue() - 1.0f);
            }
            else if (this.setting.getValue() instanceof Integer) {
                this.setting.setValue(this.setting.getValue() - 1);
            }
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public UnlimitedSlider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    public void toggle() {
    }
}
