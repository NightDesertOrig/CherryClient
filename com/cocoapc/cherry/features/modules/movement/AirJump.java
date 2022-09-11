//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;

public class AirJump extends Module
{
    public AirJump() {
        super("AirJump", "Makes it possible to jump while ur in the air.", Module.Category.MOVEMENT, false, false, false);
    }
    
    public int onUpdate() {
        AirJump.mc.player.onGround = true;
        return 0;
    }
    
    public void onRender3D() {
    }
}
