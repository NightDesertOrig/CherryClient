//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event.events;

import net.minecraft.client.gui.*;

public class OpenGuiEvent extends CancelableEvent
{
    public GuiScreen Field1152;
    
    public void Method1160(final GuiScreen guiScreen) {
        this.Field1152 = guiScreen;
    }
    
    public OpenGuiEvent(final GuiScreen guiScreen) {
        this.Field1152 = guiScreen;
    }
    
    public GuiScreen Method1161() {
        return this.Field1152;
    }
}
