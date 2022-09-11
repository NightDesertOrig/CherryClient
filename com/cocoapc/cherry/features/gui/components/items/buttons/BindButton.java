//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.items.buttons;

import com.cocoapc.cherry.features.modules.client.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.util.util2.*;
import com.cocoapc.cherry.features.gui.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import com.cocoapc.cherry.features.setting.*;

public class BindButton extends Button
{
    public boolean isListening;
    private final Setting setting;
    
    @Override
    public void drawScreen(final int n, final int n2, final float f) {
        final int n3 = ColorUtil.toARGB(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue(), 255);
        RoundedUtils.drawRoundedRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, 5.0f, this.getState() ? (this.isHovering(n, n2) ? -2007673515 : 290805077) : (this.isHovering(n, n2) ? Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())));
        if (this.isListening) {
            Cherry.textManager.drawStringWithShadow("Press a Key...", this.x + 2.3f, this.y - 1.7f - CherryGui.getClickGui().getTextOffset(), -1);
        }
        else {
            Cherry.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.setting.getName()).append(" ").append(ChatFormatting.GREEN).append(this.setting.getValue().toString().toUpperCase())), this.x + 2.3f, this.y - 1.7f - CherryGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    @Override
    public boolean getState() {
        return !this.isListening;
    }
    
    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
        }
    }
    
    @Override
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            Bind bind = new Bind(n);
            if (bind.toString().equalsIgnoreCase("Escape")) {
                return;
            }
            if (bind.toString().equalsIgnoreCase("Delete")) {
                bind = new Bind(-1);
            }
            this.setting.setValue(bind);
            this.onMouseClick();
        }
    }
    
    public BindButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
}
