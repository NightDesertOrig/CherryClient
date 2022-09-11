//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class SlowSwing extends Module
{
    public static SlowSwing INSTANCE;
    public final Setting<Integer> rotate;
    public final Setting<Integer> speed;
    public Setting<types> type;
    public boolean isPressed;
    
    public SlowSwing() {
        super("SwingSpeed", "", Module.Category.RENDER, true, false, false);
        this.rotate = (Setting<Integer>)this.register(new Setting("rotate", (T)6, (T)1, (T)20));
        this.speed = (Setting<Integer>)this.register(new Setting("Speed", (T)6, (T)1, (T)50));
        this.type = (Setting<types>)this.register(new Setting("Type", (T)types.y));
        this.isPressed = false;
        SlowSwing.INSTANCE = this;
    }
    
    public void onRender3D() {
    }
    
    public enum types
    {
        x, 
        y, 
        z;
    }
}
