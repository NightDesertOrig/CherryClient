//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraftforge.fml.common.*;

public class Announcer extends Module
{
    public static Timerm timer;
    public final Setting<announce> mode;
    private final Setting<Integer> Delay;
    
    public Announcer() {
        super("Announcer", "Announcer", Category.MISC, true, false, false);
        this.mode = (Setting<announce>)this.register(new Setting("Mode", (T)announce.FPS));
        this.Delay = (Setting<Integer>)this.register(new Setting("Delay", (T)0, (T)1, (T)5000));
    }
    
    @Override
    public int onUpdate() {
        switch (this.mode.getValue()) {
            case FPS: {
                if (!fullNullCheck() && Announcer.timer.passedMs(this.Delay.getValue())) {
                    Announcer.timer.passedMs(this.Delay.getValue());
                    final int fps2 = Integer.parseInt(String.valueOf(Minecraft.debugFPS));
                    Announcer.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("\u3084\u3081\u3066\uff01\u79c1\u306eFPS\u306f " + fps2 + "FPS\u3088\uff01\uff01\uff01"));
                    Announcer.timer.reset();
                    break;
                }
                break;
            }
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    @Mod.EventHandler
    public void onjoin(final ConnectionEvent e) {
    }
    
    static {
        Announcer.timer = new Timerm();
    }
    
    public enum announce
    {
        FPS;
    }
}
