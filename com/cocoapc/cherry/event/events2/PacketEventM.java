//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.event.events2;

import com.cocoapc.cherry.event.*;
import com.jcraft.jogg.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketEventM extends EventStage
{
    private final Packet packet;
    
    public PacketEventM(final Packet packet) {
        this.packet = packet;
    }
    
    public <T extends Packet> T getPacket() {
        return (T)this.packet;
    }
    
    @Cancelable
    public static class Receive extends PacketEvent
    {
        public Receive(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
    
    @Cancelable
    public static class Send extends PacketEvent
    {
        public Send(final Packet packet) {
            super((net.minecraft.network.Packet)packet);
        }
    }
}
