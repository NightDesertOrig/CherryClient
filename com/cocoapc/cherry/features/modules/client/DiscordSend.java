//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.client.*;

public class DiscordSend extends Module
{
    public DiscordSend() {
        super("DiscordSender", "", Category.MISC, true, false, false);
    }
    
    public int onUpdate(final PacketEvent.Send event) {
        final String msg1 = ((CPacketChatMessage)event.getPacket()).getMessage();
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
