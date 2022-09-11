//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.player.*;
import com.cocoapc.cherry.features.command.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.server.*;
import java.text.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;

public class Chat extends Module
{
    private static Chat INSTANCE;
    private final Timer timer;
    public static String IlllIllllllllllllIIllllllll;
    public static String IlIlllIllIllllIll;
    private Setting<String> custom;
    public Setting<Suffix> suffix;
    public Setting<Boolean> clean;
    public Setting<Boolean> infinite;
    public Setting<Boolean> autoQMain;
    public Setting<Boolean> qNotification;
    public Setting<Integer> qDelay;
    public Setting<TextUtil.Color> timeStamps;
    public Setting<Boolean> rainbowTimeStamps;
    public Setting<TextUtil.Color> bracket;
    public Setting<Boolean> space;
    public Setting<Boolean> all;
    public Setting<Boolean> shrug;
    public Setting<Boolean> disability;
    
    public Chat() {
        super("ChatSuffix", "Modifies your chat", Category.MISC, true, false, false);
        this.timer = new Timer();
        this.custom = (Setting<String>)this.register(new Setting("Custom", (T)""));
        this.suffix = (Setting<Suffix>)this.register(new Setting("Suffix", (T)Suffix.ONE, "Your Suffix."));
        this.clean = (Setting<Boolean>)this.register(new Setting("CleanChat", (T)false, "Cleans your chat"));
        this.infinite = (Setting<Boolean>)this.register(new Setting("Infinite", (T)false, "Makes your chat infinite."));
        this.autoQMain = (Setting<Boolean>)this.register(new Setting("AutoQMain", (T)false, "Spams AutoQMain"));
        this.qNotification = (Setting<Boolean>)this.register(new Setting("QNotification", (T)false, v -> this.autoQMain.getValue()));
        this.qDelay = (Setting<Integer>)this.register(new Setting("QDelay", (T)9, (T)1, (T)90, v -> this.autoQMain.getValue()));
        this.timeStamps = (Setting<TextUtil.Color>)this.register(new Setting("Time", (T)TextUtil.Color.NONE));
        this.rainbowTimeStamps = (Setting<Boolean>)this.register(new Setting("RainbowTimeStamps", (T)false, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.bracket = (Setting<TextUtil.Color>)this.register(new Setting("Bracket", (T)TextUtil.Color.WHITE, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.space = (Setting<Boolean>)this.register(new Setting("Space", (T)true, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.all = (Setting<Boolean>)this.register(new Setting("All", (T)false, v -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.shrug = (Setting<Boolean>)this.register(new Setting("Shrug", (T)false));
        this.disability = (Setting<Boolean>)this.register(new Setting("Disability", (T)false));
        this.setInstance();
    }
    
    public static Chat getInstance() {
        if (Chat.INSTANCE == null) {
            Chat.INSTANCE = new Chat();
        }
        return Chat.INSTANCE;
    }
    
    private void setInstance() {
        Chat.INSTANCE = this;
    }
    
    @Override
    public int onUpdate() {
        if (this.shrug.getValue()) {
            Chat.mc.player.sendChatMessage(TextUtil.shrug);
            this.shrug.setValue(false);
        }
        if (this.autoQMain.getValue()) {
            if (!this.shouldSendMessage((EntityPlayer)Chat.mc.player)) {
                return 0;
            }
            if (this.qNotification.getValue()) {
                Command.sendMessage("awa");
            }
            Chat.mc.player.sendChatMessage("waw");
            this.timer.reset();
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getStage() == 0 && event.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage packet = (CPacketChatMessage)event.getPacket();
            String s = packet.getMessage();
            if (s.startsWith("/")) {
                return;
            }
            switch (this.suffix.getValue()) {
                case ONE: {
                    s += " : \u1d04\u029c\u1d07\u0280\u0280\u028f \u1d04\u029f\u026a\u1d07\u0274\u1d1b(|\u1d072\u02992\u1d1b|)";
                }
                case TWO: {
                    s = s + " : \u1d04\u029c\u1d07\u0280\u0280\u028f \u1d04\u029f\u026a\u1d07\u0274\u1d1b " + "5.0";
                }
                case CUSTOM: {
                    s += this.custom.getValue();
                    break;
                }
            }
            if (s.length() >= 256) {
                s = s.substring(0, 256);
            }
            packet.message = s;
        }
    }
    
    @SubscribeEvent
    public void onChatPacketReceive(final PacketEvent.Receive event) {
        if (event.getStage() != 0 || event.getPacket() instanceof SPacketChat) {}
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getStage() == 0 && this.timeStamps.getValue() != TextUtil.Color.NONE && event.getPacket() instanceof SPacketChat) {
            if (!((SPacketChat)event.getPacket()).isSystem()) {
                return;
            }
            final String originalMessage = ((SPacketChat)event.getPacket()).chatComponent.getFormattedText();
            final String message = this.getTimeString(originalMessage) + originalMessage;
            ((SPacketChat)event.getPacket()).chatComponent = (ITextComponent)new TextComponentString(message);
        }
    }
    
    public String getTimeString(final String message) {
        final String date = new SimpleDateFormat("k:mm").format(new Date());
        if (this.rainbowTimeStamps.getValue()) {
            final String timeString = "<" + date + ">" + (this.space.getValue() ? " " : "");
            final StringBuilder builder = new StringBuilder(timeString);
            builder.insert(0, "§+");
            return builder.toString();
        }
        return ((this.bracket.getValue() == TextUtil.Color.NONE) ? "" : TextUtil.coloredString("<", this.bracket.getValue())) + TextUtil.coloredString(date, this.timeStamps.getValue()) + ((this.bracket.getValue() == TextUtil.Color.NONE) ? "" : TextUtil.coloredString(">", this.bracket.getValue())) + (this.space.getValue() ? " " : "") + "§r";
    }
    
    private boolean shouldSendMessage(final EntityPlayer player) {
        return player.dimension == 1 && this.timer.passedS(this.qDelay.getValue()) && player.getPosition().equals((Object)new Vec3i(0, 240, 0));
    }
    
    static {
        Chat.INSTANCE = new Chat();
        Chat.IlllIllllllllllllIIllllllll = "htt";
        Chat.IlIlllIllIllllIll = "https";
    }
    
    public enum Suffix
    {
        NONE, 
        ONE, 
        TWO, 
        CUSTOM;
    }
}
