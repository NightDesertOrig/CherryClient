//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.util.*;
import java.util.*;

public class HoleTP extends Module
{
    private final Setting<Float> range;
    
    public HoleTP() {
        super("HoleTP", "like TP", Module.Category.MOVEMENT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)0.5f, (T)0.1f, (T)10.0f));
    }
    
    public int onUpdate() {
        final BlockPos hole = Cherry.holeManager.calcHoles().stream().min(Comparator.comparing(p -> Util.mc.player.getDistance((double)p.getX(), (double)p.getY(), (double)p.getZ()))).orElse(null);
        if (hole != null && Util.mc.player.getDistance((double)hole.getX(), (double)hole.getY(), (double)hole.getZ()) < this.range.getValue() + 1.5) {
            Util.mc.player.setPosition(hole.getX() + 0.5, (double)hole.getY(), hole.getZ() + 0.5);
            Util.mc.player.setPosition(hole.getX() + 0.5, (double)hole.getY(), hole.getZ() + 0.5);
            this.disable();
        }
        return 0;
    }
    
    public void onRender3D() {
    }
}
