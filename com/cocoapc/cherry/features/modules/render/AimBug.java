//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import java.util.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;

public class AimBug extends Module
{
    private final Random random;
    private final Setting<Boolean> turn;
    
    public AimBug() {
        super("AimBug", "\u8996\u70b9\u304c\u30d0\u30b0\u308a\u307e\u3059\u3002", Module.Category.PLAYER, true, false, false);
        this.turn = (Setting<Boolean>)this.register(new Setting("cocoaaaa", (T)true));
        this.random = new Random();
    }
    
    public int onUpdate() {
        if (Util.mc.player.ticksExisted % 1 == 0 && this.turn.getValue()) {
            Util.mc.player.rotationYaw = (float)(this.random.nextInt(360) - 31 - 90 - 10);
        }
        return 0;
    }
    
    public void onRender3D() {
    }
}
