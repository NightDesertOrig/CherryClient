//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.server.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class rollback extends Module
{
    public rollback() {
        super("rollback", "", Category.MISC, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (!nullCheck() && event.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
            Command.sendMessage("xyz : " + packet.x + "," + packet.y + "," + packet.z + "; rotate :" + packet.yaw + " - " + packet.pitch);
        }
    }
    
    @Override
    public void onRender3D() {
    }
}
