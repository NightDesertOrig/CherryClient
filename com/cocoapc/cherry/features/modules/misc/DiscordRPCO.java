//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.features.setting.*;

public class DiscordRPCO extends Module
{
    public static RPC INSTANCE;
    public Setting<Boolean> showIP;
    public Setting<Boolean> users;
    
    public DiscordRPCO() {
        super("DiscordRPC", "RPC", Category.CLIENT, true, false, false);
        this.showIP = (Setting<Boolean>)this.register(new Setting("IP", (T)false));
        this.users = (Setting<Boolean>)this.register(new Setting("Users", (T)false));
    }
    
    @Override
    public void onEnable() {
        RPC.startRPC();
    }
    
    @Override
    public int onDisable() {
        RPC.stopRPC();
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
