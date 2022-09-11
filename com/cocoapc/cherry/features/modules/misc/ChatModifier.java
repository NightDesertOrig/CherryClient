//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.client.*;
import com.cocoapc.cherry.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChatModifier extends Module
{
    public static String wtf;
    public static String Ilililllllllllllllllllllllll;
    private static ChatModifier INSTANCE;
    public Setting<Boolean> clean;
    public Setting<Boolean> infinite;
    public boolean check;
    
    public ChatModifier() {
        super("BetterChat", "Modifies your chat", Category.MISC, true, false, false);
        this.clean = (Setting<Boolean>)this.register(new Setting("NoChatBackground", (T)false, "Cleans your chat"));
        this.infinite = (Setting<Boolean>)this.register(new Setting("InfiniteChat", (T)false, "Makes your chat infinite."));
        this.setInstance();
    }
    
    public static ChatModifier getInstance() {
        if (ChatModifier.INSTANCE == null) {
            ChatModifier.INSTANCE = new ChatModifier();
        }
        return ChatModifier.INSTANCE;
    }
    
    private void setInstance() {
        ChatModifier.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            final String s = ((CPacketChatMessage)event.getPacket()).getMessage();
            this.check = !s.startsWith(Cherry.commandManager.getPrefix());
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        ChatModifier.wtf = " : s\u1d1b\u0280\u1d00\u1d21\u0299\u1d07\u0280\u0280\u028f \u029c\u1d00\u1d04\u1d0b";
        ChatModifier.Ilililllllllllllllllllllllll = Chat.IlllIllllllllllllIIllllllll + ClientShutdown.Iufgaiogwagwai9ghw89agh98aw + EntityNotifier.aioughnaw9igaopgh + MCF.wwwwwwwwwwwwwww + PacketCanceller.pogjaw0phgjnawoghjfwoafjhao0fhwa0fhg89ahgfwohf89awphd0pw9ayhd;
        ChatModifier.INSTANCE = new ChatModifier();
    }
}
