//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.player.*;

public class Targetfollow extends Module
{
    public Targetfollow() {
        super("Follow", "follow", Module.Category.MOVEMENT, true, false, false);
    }
    
    public int onUpdate() {
        final EntityPlayer target = PlayerUtil.getNearestPlayer(15.0);
        if (target == null) {
            return 0;
        }
        final double x = target.posX;
        final double y = target.posY;
        final double z = target.posZ;
        Targetfollow.mc.player.posX = target.posX;
        Targetfollow.mc.player.posY = target.posY;
        Targetfollow.mc.player.posZ = target.posZ;
        return 0;
    }
    
    public void onRender3D() {
    }
}
