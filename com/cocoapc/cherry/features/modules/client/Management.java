//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Management extends Module
{
    private static Management INSTANCE;
    public Setting<Boolean> betterFrames;
    public Setting<String> commandBracket;
    public Setting<String> commandBracket2;
    public Setting<String> command;
    public Setting<Boolean> rainbowPrefix;
    public Setting<TextUtil.Color> bracketColor;
    public Setting<TextUtil.Color> commandColor;
    public Setting<Integer> betterFPS;
    public Setting<Boolean> potions;
    public Setting<Integer> textRadarUpdates;
    public Setting<Integer> respondTime;
    public Setting<Integer> moduleListUpdates;
    public Setting<Float> holeRange;
    public Setting<Integer> holeUpdates;
    public Setting<Integer> holeSync;
    public Setting<Boolean> safety;
    public Setting<Integer> safetyCheck;
    public Setting<ThreadMode> holeThread;
    public Setting<Boolean> speed;
    public Setting<Boolean> oneDot15;
    public Setting<Boolean> tRadarInv;
    public Setting<Boolean> unfocusedCpu;
    public Setting<Integer> cpuFPS;
    public Setting<Integer> baritoneTimeOut;
    public Setting<Boolean> oneChunk;
    
    public Management() {
        super("Management", "ClientManagement", Category.CLIENT, false, false, true);
        this.betterFrames = (Setting<Boolean>)this.register(new Setting("BetterMaxFPS", (T)false));
        this.commandBracket = (Setting<String>)this.register(new Setting("Bracket", (T)"<"));
        this.commandBracket2 = (Setting<String>)this.register(new Setting("Bracket2", (T)">"));
        this.command = (Setting<String>)this.register(new Setting("Command", (T)"Phobos.eu"));
        this.rainbowPrefix = (Setting<Boolean>)this.register(new Setting("RainbowPrefix", (T)false));
        this.bracketColor = (Setting<TextUtil.Color>)this.register(new Setting("BColor", (T)TextUtil.Color.BLUE));
        this.commandColor = (Setting<TextUtil.Color>)this.register(new Setting("CColor", (T)TextUtil.Color.BLUE));
        this.betterFPS = (Setting<Integer>)this.register(new Setting("MaxFPS", (T)300, (T)30, (T)1000, v -> this.betterFrames.getValue()));
        this.potions = (Setting<Boolean>)this.register(new Setting("Potions", (T)true));
        this.textRadarUpdates = (Setting<Integer>)this.register(new Setting("TRUpdates", (T)500, (T)0, (T)1000));
        this.respondTime = (Setting<Integer>)this.register(new Setting("SeverTime", (T)500, (T)0, (T)1000));
        this.moduleListUpdates = (Setting<Integer>)this.register(new Setting("ALUpdates", (T)1000, (T)0, (T)1000));
        this.holeRange = (Setting<Float>)this.register(new Setting("HoleRange", (T)6.0f, (T)1.0f, (T)256.0f));
        this.holeUpdates = (Setting<Integer>)this.register(new Setting("HoleUpdates", (T)100, (T)0, (T)1000));
        this.holeSync = (Setting<Integer>)this.register(new Setting("HoleSync", (T)10000, (T)1, (T)10000));
        this.safety = (Setting<Boolean>)this.register(new Setting("SafetyPlayer", (T)false));
        this.safetyCheck = (Setting<Integer>)this.register(new Setting("SafetyCheck", (T)50, (T)1, (T)150));
        this.holeThread = (Setting<ThreadMode>)this.register(new Setting("HoleThread", (T)ThreadMode.WHILE));
        this.speed = (Setting<Boolean>)this.register(new Setting("Speed", (T)true));
        this.oneDot15 = (Setting<Boolean>)this.register(new Setting("1.15", (T)false));
        this.tRadarInv = (Setting<Boolean>)this.register(new Setting("TRadarInv", (T)true));
        this.unfocusedCpu = (Setting<Boolean>)this.register(new Setting("UnfocusedCPU", (T)false));
        this.cpuFPS = (Setting<Integer>)this.register(new Setting("UnfocusedFPS", (T)60, (T)1, (T)60, v -> this.unfocusedCpu.getValue()));
        this.baritoneTimeOut = (Setting<Integer>)this.register(new Setting("Baritone", (T)5, (T)1, (T)20));
        this.oneChunk = (Setting<Boolean>)this.register(new Setting("OneChunk", (T)false));
        this.setInstance();
    }
    
    public static Management getInstance() {
        if (Management.INSTANCE == null) {
            Management.INSTANCE = new Management();
        }
        return Management.INSTANCE;
    }
    
    private void setInstance() {
        Management.INSTANCE = this;
    }
    
    @Override
    public void onLoad() {
        Cherry.commandManager.setClientMessage(this.getCommandMessage());
    }
    
    @Override
    public void onRender3D() {
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2) {
            if (this.oneChunk.getPlannedValue()) {
                Management.mc.gameSettings.renderDistanceChunks = 1;
            }
            if (event.getSetting() != null && this.equals(event.getSetting().getFeature())) {
                if (event.getSetting().equals(this.holeThread)) {}
                Cherry.commandManager.setClientMessage(this.getCommandMessage());
            }
        }
    }
    
    public String getCommandMessage() {
        if (this.rainbowPrefix.getPlannedValue()) {
            final StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
            stringBuilder.insert(0, "§+");
            stringBuilder.append("§r");
            return stringBuilder.toString();
        }
        return TextUtil.coloredString(this.commandBracket.getPlannedValue(), this.bracketColor.getPlannedValue()) + TextUtil.coloredString(this.command.getPlannedValue(), this.commandColor.getPlannedValue()) + TextUtil.coloredString(this.commandBracket2.getPlannedValue(), this.bracketColor.getPlannedValue());
    }
    
    public String getRainbowCommandMessage() {
        final StringBuilder stringBuilder = new StringBuilder(this.getRawCommandMessage());
        stringBuilder.insert(0, "§+");
        stringBuilder.append("§r");
        return stringBuilder.toString();
    }
    
    public String getRawCommandMessage() {
        return this.commandBracket.getValue() + this.command.getValue() + this.commandBracket2.getValue();
    }
    
    static {
        Management.INSTANCE = new Management();
    }
    
    public enum ThreadMode
    {
        POOL, 
        WHILE, 
        NONE;
    }
}
