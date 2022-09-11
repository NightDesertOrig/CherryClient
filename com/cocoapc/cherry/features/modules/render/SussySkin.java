//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class SussySkin extends Module
{
    public static SussySkin INSTANCE;
    public Setting<skins> skin;
    
    public SussySkin() {
        super("SussySkin", "", Module.Category.RENDER, true, false, false);
        this.skin = (Setting<skins>)this.register(new Setting("Mode", (T)skins.enokitoraisu));
        SussySkin.INSTANCE = this;
    }
    
    public void onRender3D() {
    }
    
    public enum skins
    {
        enokitoraisu, 
        Sus, 
        Yellowsus, 
        Blacksus, 
        Purplesus, 
        seisan, 
        BlueSus;
    }
}
