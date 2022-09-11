//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraftforge.fml.client.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.client.resources.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.*;

@Mixin({ SplashProgress.class })
public interface AccessorSplashProgress
{
    @Accessor(value = "thread", remap = false)
    default void setThread(final Thread thread) {
        throw new AssertionError();
    }
    
    @Accessor(value = "miscPack", remap = false)
    default IResourcePack getMiscPack() {
        throw new AssertionError();
    }
    
    @Accessor(value = "done", remap = false)
    default boolean getDone() {
        throw new AssertionError();
    }
    
    @Accessor(value = "pause", remap = false)
    default boolean getPause() {
        throw new AssertionError();
    }
    
    @Accessor(value = "lock", remap = false)
    default Lock getLock() {
        throw new AssertionError();
    }
    
    @Accessor(value = "mutex", remap = false)
    default Semaphore getMutex() {
        throw new AssertionError();
    }
}
