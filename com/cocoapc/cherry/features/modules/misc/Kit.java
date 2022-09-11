//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.features.setting.*;

public class Kit extends Module
{
    private final Timerm timer;
    private Setting<String> custom;
    
    public Kit() {
        super("Kit", "testmodule", Category.MISC, true, false, false);
        this.timer = new Timerm();
        this.custom = (Setting<String>)this.register(new Setting("Kitname", (T)"Kitname"));
    }
    
    @Override
    public void onEnable() {
        Kit.mc.player.sendChatMessage("/kit " + this.custom.getValue());
        this.disable();
    }
    
    @Override
    public void onRender3D() {
    }
}
