//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.player.*;

public class AutoHoleFill extends Module
{
    private final Setting<Integer> range;
    
    public AutoHoleFill() {
        super("AutoHoleFill", "AutoHoleFill", Category.COMBAT, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (T)3, (T)0, (T)30));
    }
    
    @Override
    public int onUpdate() {
        final EntityPlayer target = PlayerUtil.getNearestPlayer(this.range.getValue());
        if (target == null) {
            return 0;
        }
        HoleFiller.getInstance().enable();
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
