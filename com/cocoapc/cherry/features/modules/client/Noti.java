//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class Noti extends Module
{
    public static Noti INSTANCE;
    public Setting<Integer> time;
    public Setting<Integer> RED;
    public Setting<Integer> GREEN;
    public Setting<Integer> BLUE;
    public Setting<Integer> ALPHA;
    public Setting<Boolean> togglE;
    public Setting<Boolean> message;
    public Setting<Boolean> player;
    public Setting<Boolean> pop;
    public Setting<Boolean> death;
    
    public Noti() {
        super("New Notification", "Notifications", Category.CLIENT, true, false, false);
        this.time = (Setting<Integer>)this.register(new Setting("Time", (T)2, (T)1, (T)5));
        this.RED = (Setting<Integer>)this.register(new Setting("RED", (T)250, (T)1, (T)255));
        this.GREEN = (Setting<Integer>)this.register(new Setting("GREEN", (T)10, (T)1, (T)255));
        this.BLUE = (Setting<Integer>)this.register(new Setting("BLUE", (T)170, (T)1, (T)255));
        this.ALPHA = (Setting<Integer>)this.register(new Setting("ALPHA", (T)255, (T)1, (T)255));
        this.togglE = (Setting<Boolean>)this.register(new Setting("Toggle", (T)false));
        this.message = (Setting<Boolean>)this.register(new Setting("Message", (T)false));
        this.player = (Setting<Boolean>)this.register(new Setting("Player", (T)false));
        this.pop = (Setting<Boolean>)this.register(new Setting("Totem", (T)false));
        this.death = (Setting<Boolean>)this.register(new Setting("Death", (T)false));
        Noti.INSTANCE = this;
    }
    
    @Override
    public void onEnable() {
    }
    
    @Override
    public void onRender3D() {
    }
}
