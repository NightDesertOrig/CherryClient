//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.player;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.item.*;
import com.cocoapc.cherry.util.*;

public class FastPlace extends Module
{
    public FastPlace() {
        super("FastPlace", "Fast everything.", Module.Category.PLAYER, true, false, false);
    }
    
    public int onUpdate() {
        if (fullNullCheck()) {
            return 0;
        }
        if (InventoryUtil.holdingItem(ItemExpBottle.class)) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        return 0;
    }
    
    public void onRender3D() {
    }
}
