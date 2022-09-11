//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.*;

public class DiscordRPC extends Module
{
    public DiscordRPC() {
        super("DiscordRPC", "", Category.CLIENT, true, false, false);
    }
    
    @Override
    public int onUpdate() {
        RPC.startRPC();
        return 0;
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
