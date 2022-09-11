//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class draw extends Module
{
    public static draw INSTANCE;
    public Setting<Integer> W;
    public Setting<Integer> S;
    public Setting<Integer> D;
    public Setting<Integer> A;
    
    public draw() {
        super("Draw", "description", Category.CLIENT, true, false, false);
        this.W = (Setting<Integer>)this.register(new Setting("X", (T)255, (T)1, (T)1000));
        this.S = (Setting<Integer>)this.register(new Setting("Y", (T)255, (T)1, (T)1000));
        this.D = (Setting<Integer>)this.register(new Setting("W", (T)255, (T)1, (T)1000));
        this.A = (Setting<Integer>)this.register(new Setting("H", (T)255, (T)1, (T)1000));
        draw.INSTANCE = this;
    }
    
    @Override
    public void onRender3D() {
    }
}
