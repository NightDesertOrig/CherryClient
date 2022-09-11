//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event.events;

public class CancelableEvent
{
    public boolean cancelled;
    
    public CancelableEvent() {
        this.cancelled = false;
    }
    
    public boolean isCanceled() {
        return this.cancelled;
    }
    
    public void Cancel() {
        this.cancelled = true;
    }
    
    public void setCanceled(final boolean bl) {
        this.cancelled = bl;
    }
}
