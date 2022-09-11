//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;

public class Flys extends Module
{
    public final Setting<Float> speed;
    
    public Flys() {
        super("Flight", "", Module.Category.MOVEMENT, true, false, false);
        this.speed = (Setting<Float>)this.register(new Setting("Speeds", (T)0.05f, (T)0.05f, (T)10.0f));
    }
    
    public int onUpdate() {
        Util.mc.player.capabilities.setFlySpeed((float)this.speed.getValue());
        Util.mc.player.capabilities.isFlying = true;
        return 0;
    }
    
    public int onDisable() {
        Util.mc.player.capabilities.setFlySpeed(0.05f);
        Util.mc.player.capabilities.isFlying = false;
        return 0;
    }
    
    public void onRender3D() {
    }
}
