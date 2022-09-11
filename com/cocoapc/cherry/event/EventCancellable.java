//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event;

public abstract class EventCancellable extends Event<Event> implements IEventCancellable
{
    private boolean isCancelled;
    
    public EventCancellable() {
        this.isCancelled = false;
    }
    
    public void setCancelled() {
        this.isCancelled = true;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.isCancelled = cancelled;
    }
    
    public boolean isCancelled() {
        return this.isCancelled;
    }
    
    public abstract void setCanceled(final boolean p0);
}
