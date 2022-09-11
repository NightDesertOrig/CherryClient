//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Spammer extends Module
{
    public static Spammer INSTANCE;
    public static Timerm timer;
    public Setting<Mode> Modes;
    public Setting custom;
    public Setting custom2;
    public Setting tell;
    public Setting Delay;
    public Setting random;
    
    public Spammer() {
        super("Spammer", "Spammer\u3067\u3059\u306e\u30e2\u30fc\u30c9\u3082\u5909\u66f4\u3059\u308b\u3053\u3068\u304c\u3067\u304d\u307e\u3059\u3002(Spammer\u306a\u3044\u3068kazubon\u304c\u6012\u308b)", Category.MISC, true, false, false);
        this.Modes = (Setting<Mode>)this.register(new Setting("mode", (T)Mode.normalspammer, "Spam"));
        this.custom = this.register(new Setting("CustomMsg", (T)"CustomMsg"));
        this.custom2 = this.register(new Setting("TellSpamMsg", (T)"TellSpamMsg"));
        this.tell = this.register(new Setting("Name", (T)"Name"));
        this.Delay = this.register(new Setting("Delay", (T)"10000"));
        this.random = this.register(new Setting("Random", (T)"100"));
        Spammer.INSTANCE = this;
    }
    
    @Override
    public int onUpdate() {
        if (Spammer.INSTANCE.Modes.getValue() == Mode.normalspammer) {
            final int Delay2 = Integer.parseInt(this.Delay.getValue());
            if (!fullNullCheck() && Spammer.timer.passedMs(Delay2)) {
                Spammer.timer.passedMs(Delay2);
                Spammer.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)this.custom.getValue()));
                Spammer.timer.reset();
            }
        }
        else if (Spammer.INSTANCE.Modes.getValue() == Mode.tellspam) {
            final int Delay2 = Integer.parseInt(this.Delay.getValue());
            if (!fullNullCheck() && Spammer.timer.passedMs(Delay2)) {
                Spammer.timer.passedMs(Delay2);
                Spammer.mc.player.connection.sendPacket((Packet)new CPacketChatMessage("/tell " + this.tell.getValue() + " " + this.custom2.getValue()));
                Spammer.timer.reset();
            }
            return 0;
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        Spammer.timer = new Timerm();
    }
    
    public enum Mode
    {
        normalspammer, 
        tellspam;
    }
}
