//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.util.*;

@Mixin({ Minecraft.class })
public interface AccessorMinecraft
{
    @Accessor("timer")
    Timer getTimer();
    
    @Accessor("session")
    void setSession(final Session p0);
    
    @Accessor("renderPartialTicksPaused")
    float getRenderPartialTicksPaused();
}
