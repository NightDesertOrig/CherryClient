//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class RPC extends Module
{
    public static RPC INSTANCE;
    public Setting<Boolean> catMode;
    public Setting<Boolean> showIP;
    public Setting<String> state;
    
    public RPC() {
        super("RPC", "Discord rich presence.", Module.Category.MISC, false, false, false);
        this.catMode = (Setting<Boolean>)this.register(new Setting("CatMode", (Object)true));
        this.showIP = (Setting<Boolean>)this.register(new Setting("ShowIP", (Object)true, "Shows the server IP in your discord presence."));
        this.state = (Setting<String>)this.register(new Setting("State", (Object)"Perry's Phobos 1.9.0", "Sets the state of the DiscordRPC."));
        RPC.INSTANCE = this;
    }
    
    public void onRender3D() {
    }
}
