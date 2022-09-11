//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class CoordMiss extends Module
{
    public static Timerm timer;
    private final Setting<Integer> Delay;
    
    public CoordMiss() {
        super("CoordMiss", "MissCoord Chat", Category.MISC, true, false, false);
        this.Delay = (Setting<Integer>)this.register(new Setting("Delay", (T)0, (T)1, (T)5000));
    }
    
    @Override
    public void onEnable() {
        final int fff = Globals.random.nextInt(20);
        final int posX1 = (int)(CoordMiss.mc.player.posX + 162.0);
        final int posX2 = posX1 * fff;
        final int posZ1 = (int)(CoordMiss.mc.player.posZ + 153.0);
        final int posZ2 = posZ1 * fff;
        Cherry.LOGGER.info((Object)fff);
        if (!fullNullCheck() && CoordMiss.timer.passedMs(this.Delay.getValue())) {
            CoordMiss.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf("\\tell sisomaki83 " + posX2 + " " + posZ2 + " \u306b\u6765\u3066")));
            CoordMiss.timer.reset();
        }
        if (!fullNullCheck() && CoordMiss.timer.passedMs(this.Delay.getValue())) {
            CoordMiss.timer.passedMs(3000L);
            CoordMiss.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("\u30df\u30b9\u3063\u305f\r\n"));
            CoordMiss.timer.reset();
        }
        if (!fullNullCheck() && CoordMiss.timer.passedMs(this.Delay.getValue())) {
            CoordMiss.timer.passedMs(3000L);
            CoordMiss.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("\u306a\u3093\u3067\u3082\u306a\u3044\u3067\u3059"));
            CoordMiss.timer.reset();
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        CoordMiss.timer = new Timerm();
    }
}
