//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.items.buttons;

import com.cocoapc.cherry.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.cocoapc.cherry.util.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.features.gui.*;

public class EnumButton extends Button
{
    public Setting setting;
    
    public boolean getState() {
        return true;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
        }
    }
    
    public int getHeight() {
        return 14;
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public void toggle() {
        this.setting.increaseEnum();
    }
    
    public EnumButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    public void drawScreen(final int n, final int n2, final float f) {
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        Cherry.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.setting.getName()).append(" ").append(ChatFormatting.GRAY).append(this.setting.currentEnumName().equalsIgnoreCase("ABC") ? "ABC" : this.setting.currentEnumName())), this.x + 2.3f, this.y - 1.7f - CherryGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
}
