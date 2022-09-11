//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event.events2;

import com.cocoapc.cherry.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
class ChatEventX extends EventStage
{
    private final String msg;
    
    public ChatEventX(final String msg) {
        this.msg = msg;
    }
    
    public String getMsg() {
        return this.msg;
    }
}
