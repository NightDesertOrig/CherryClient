//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.client.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FakeChat extends Module
{
    public Setting<Mode> mode;
    public Setting<Boolean> All;
    
    public FakeChat() {
        super("FakeSuffix", "FakeSuffix", Category.MISC, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.Snow));
        this.All = (Setting<Boolean>)this.register(new Setting("All", (T)true));
    }
    
    @SubscribeEvent
    public int SubscribeEvent(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            if (((CPacketChatMessage)event.getPacket()).getMessage().startsWith("/") || ((CPacketChatMessage)event.getPacket()).getMessage().startsWith(Command.getCommandPrefix())) {
                return 0;
            }
            String message;
            final String msg1 = message = ((CPacketChatMessage)event.getPacket()).getMessage();
            message = msg1 + " Abyss || rootnet |\u23d0 \u0493\u1d1c\u1d1b\u1d1c\u0280\u1d07 \u1d04\u029f\u026a\u1d07\u0274\u1d1b |\u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b \u0e05\u2022\u03c9\u2022\u0e05 meow » \u02e2\u207f\u1d52\u02b7";
            ((CPacketChatMessage)event.getPacket()).message = message;
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    public enum Mode
    {
        Snow, 
        Luigihack, 
        Future, 
        RootNet, 
        Abyss, 
        RusherPlus, 
        Catalyst;
    }
}
