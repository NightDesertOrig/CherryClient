//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event.events;

import com.cocoapc.cherry.event.*;
import net.minecraft.entity.player.*;

public class PlayerDeathEvent extends EventStage
{
    public EntityPlayer player;
    
    public PlayerDeathEvent(final EntityPlayer player) {
        this.player = player;
    }
}
