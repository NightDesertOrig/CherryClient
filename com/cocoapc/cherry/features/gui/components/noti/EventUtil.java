//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.noti;

import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class EventUtil
{
    public static void register(final Object obj) {
        MinecraftForge.EVENT_BUS.register(obj);
    }
    
    public static void unregister(final Object obj) {
        MinecraftForge.EVENT_BUS.unregister(obj);
    }
    
    public static void post(final Event event) {
        MinecraftForge.EVENT_BUS.post(event);
    }
}
