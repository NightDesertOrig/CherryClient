//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event.events;

import com.cocoapc.cherry.event.*;

public class EventRender2D extends Event<EventRender2D>
{
    public final int width;
    public final int height;
    
    public EventRender2D(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void call() {
        new EventPreShader(this.width, this.height).call();
    }
}
