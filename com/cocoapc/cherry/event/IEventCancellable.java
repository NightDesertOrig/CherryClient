//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event;

public interface IEventCancellable extends IEvent
{
    void setCancelled();
    
    void setCancelled(final boolean p0);
    
    boolean isCancelled();
}
