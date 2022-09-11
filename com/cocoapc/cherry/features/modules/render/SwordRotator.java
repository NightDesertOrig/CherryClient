//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class SwordRotator extends Module
{
    public static SwordRotator INSTANCE;
    public SettingK<Float> speed;
    public SettingK<Float> type;
    public boolean isPressed;
    
    private SettingK<Float> register(final SettingK speed) {
        return null;
    }
    
    public SwordRotator() {
        super("SwordAnimation", "", Module.Category.RENDER, true, false, false);
        this.speed = this.register(new SettingK("Speed", (T)3.0f, (T)10.0f, (T)0.1f));
        this.type = this.register(new SettingK("Type", (T)"X", new String[] { "X", "Y", "Z" }));
        this.isPressed = false;
        SwordRotator.INSTANCE = this;
    }
    
    public int onTick() {
        if (nullCheck()) {
            return 0;
        }
        this.isPressed = (SwordRotator.mc.gameSettings.keyBindAttack.pressed || SwordRotator.mc.player.isSwingInProgress);
        return 0;
    }
    
    public void onRender3D() {
    }
}
