//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.noti;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class Notification extends Module
{
    public static Notification INSTANCE;
    public Setting<Notification> mode;
    public Setting<Float> time;
    public Setting<Float> speed;
    public Setting<Boolean> toggleMessage;
    public Setting<Boolean> log;
    
    public Notification() {
        super("Notification", "", Category.RENDER, true, false, false);
        this.mode = (Setting<Notification>)this.register(new Setting("Mode", (T)modes.MAL, "Your Suffix."));
        this.time = (Setting<Float>)this.register(new Setting("Time", (T)1.5f, (T)5.0f, (T)0.5f));
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (T)3.0f, (T)5.0f, (T)1.0f));
        this.toggleMessage = (Setting<Boolean>)this.register(new Setting("Toggle", (T)true));
        this.log = (Setting<Boolean>)this.register(new Setting("Log", (T)true));
        Notification.INSTANCE = this;
    }
    
    @Override
    public void onRender3D() {
    }
    
    public enum modes
    {
        CHAT, 
        MAL;
    }
}
