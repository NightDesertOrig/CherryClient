//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.entity.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.features.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;

public class GM extends Module
{
    Entity currentEntity;
    
    public GM() {
        super("GM", "GM", Category.COMBAT, true, false, false);
    }
    
    @Override
    public void onRender3D(final Render3DEvent event) {
        final EntityPlayer target = PlayerUtil.getNearestPlayer(5.0);
        final Entity entity3 = (Entity)GM.mc.world.loadedEntityList;
        if (Feature.nullCheck()) {
            return;
        }
        if (target == null) {
            return;
        }
        final BlockPos X = new BlockPos(target.posX, target.posY, target.posZ + 1.0);
    }
    
    @Override
    public void onRender3D() {
    }
}
