//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;

public class TEST extends Module
{
    private final Timerm timer;
    private Setting<String> custom;
    
    public TEST() {
        super("AWA", "testmodule", Category.MISC, true, false, false);
        this.timer = new Timerm();
        this.custom = (Setting<String>)this.register(new Setting("Custom", (T)"SweetPlus Client On Top!!!"));
    }
    
    @Override
    public void onEnable() {
        Util.mc.player.sendChatMessage((String)this.custom.getValue());
        this.disable();
    }
    
    @Override
    public void onRender3D() {
    }
}
