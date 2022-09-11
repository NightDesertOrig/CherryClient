//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry;

import club.minnced.discord.rpc.*;
import com.cocoapc.cherry.util.*;

public class RPC
{
    private static final DiscordRichPresence drp;
    private static final DiscordRPC discordRPC;
    public static String IlllIllIIIIIIIIlllll;
    
    public static void startRPC() {
        final DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        final String discordID = "1000386860058685480";
        RPC.discordRPC.Discord_Initialize(discordID, eventHandlers, true, (String)null);
        RPC.drp.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.drp.details = "Cherry Client Earth2b2t Hacked Client";
        RPC.drp.state = "Hi " + Util.mc.getSession().getUsername();
        RPC.drp.largeImageKey = "logo";
        RPC.drp.largeImageText = "V.5.0";
        RPC.drp.smallImageKey = "logo";
        RPC.drp.smallImageText = "E2b2t Hacked Client";
        RPC.discordRPC.Discord_UpdatePresence(RPC.drp);
    }
    
    public static void stopRPC() {
        RPC.discordRPC.Discord_Shutdown();
        RPC.discordRPC.Discord_ClearPresence();
    }
    
    static {
        drp = new DiscordRichPresence();
        discordRPC = DiscordRPC.INSTANCE;
        RPC.IlllIllIIIIIIIIlllll = "m/api/w";
    }
}
