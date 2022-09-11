//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.features.gui.*;
import net.minecraft.client.settings.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.client.gui.*;

public class ClickGui extends Module
{
    private static ClickGui INSTANCE;
    public Setting<String> prefix;
    public Setting<Boolean> customFov;
    public Setting<Float> fov;
    public Setting<Boolean> dark;
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> hoverAlpha;
    public Setting<Integer> topRed;
    public Setting<Integer> topGreen;
    public Setting<Integer> topBlue;
    public Setting<Integer> alpha;
    public Setting<Boolean> rainbow;
    public Setting<rainbowMode> rainbowModeHud;
    public Setting<rainbowModeArray> rainbowModeA;
    public Setting<Integer> rainbowHue;
    public Setting<Float> rainbowBrightness;
    public Setting<Float> rainbowSaturation;
    public Setting<Integer> outline;
    public Setting<Boolean> particles;
    public Setting<Integer> gradiant;
    public Setting<Integer> particleLength;
    public Setting<Integer> particlered;
    public Setting<Integer> particlegreen;
    public Setting<Integer> particleblue;
    private CherryGui click;
    
    public ClickGui() {
        super("ClickGUI", "Gui\u3092\u958b\u304d\u307e\u3059\u3002", Category.CLIENT, true, false, false);
        this.prefix = (Setting<String>)this.register(new Setting("Prefix", (T)"-"));
        this.customFov = (Setting<Boolean>)this.register(new Setting("CustomFov", (T)false));
        this.fov = (Setting<Float>)this.register(new Setting("Fov", (T)150.0f, (T)(-180.0f), (T)180.0f));
        this.dark = (Setting<Boolean>)this.register(new Setting("Dark", (T)true));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)176, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)142, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)250, (T)0, (T)255));
        this.hoverAlpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)191, (T)0, (T)255));
        this.topRed = (Setting<Integer>)this.register(new Setting("SecondRed", (T)155, (T)0, (T)255));
        this.topGreen = (Setting<Integer>)this.register(new Setting("SecondGreen", (T)185, (T)0, (T)255));
        this.topBlue = (Setting<Integer>)this.register(new Setting("SecondBlue", (T)241, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("HoverAlpha", (T)253, (T)0, (T)255));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)false));
        this.rainbowModeHud = (Setting<rainbowMode>)this.register(new Setting("HRainbowMode", (T)rainbowMode.Static, v -> this.rainbow.getValue()));
        this.rainbowModeA = (Setting<rainbowModeArray>)this.register(new Setting("ARainbowMode", (T)rainbowModeArray.Static, v -> this.rainbow.getValue()));
        this.rainbowHue = (Setting<Integer>)this.register(new Setting("Delay", (T)240, (T)0, (T)600, v -> this.rainbow.getValue()));
        this.rainbowBrightness = (Setting<Float>)this.register(new Setting("Brightness ", (T)150.0f, (T)1.0f, (T)255.0f, v -> this.rainbow.getValue()));
        this.rainbowSaturation = (Setting<Float>)this.register(new Setting("Saturation", (T)150.0f, (T)1.0f, (T)255.0f, v -> this.rainbow.getValue()));
        this.outline = (Setting<Integer>)this.register(new Setting("outline", (T)true));
        this.particles = (Setting<Boolean>)this.register(new Setting("Particles", (T)true));
        this.gradiant = (Setting<Integer>)this.register(new Setting("gradiant", (T)true));
        this.particleLength = (Setting<Integer>)this.register(new Setting("ParticleLength", (T)134, (T)0, (T)300, v -> this.particles.getValue()));
        this.particlered = (Setting<Integer>)this.register(new Setting("ParticleRed", (T)180, (T)0, (T)255, v -> this.particles.getValue()));
        this.particlegreen = (Setting<Integer>)this.register(new Setting("ParticleGreen", (T)0, (T)0, (T)255, v -> this.particles.getValue()));
        this.particleblue = (Setting<Integer>)this.register(new Setting("ParticleBlue", (T)255, (T)0, (T)255, v -> this.particles.getValue()));
        this.setInstance();
    }
    
    public static ClickGui getInstance() {
        if (ClickGui.INSTANCE == null) {
            ClickGui.INSTANCE = new ClickGui();
        }
        return ClickGui.INSTANCE;
    }
    
    private void setInstance() {
        ClickGui.INSTANCE = this;
    }
    
    @Override
    public int onUpdate() {
        if (this.customFov.getValue()) {
            ClickGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                Cherry.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to " + ChatFormatting.DARK_GRAY + Cherry.commandManager.getPrefix());
            }
            Cherry.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }
    
    @Override
    public void onEnable() {
        Util.mc.displayGuiScreen((GuiScreen)CherryGui.getClickGui());
    }
    
    @Override
    public void onLoad() {
        Cherry.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        Cherry.commandManager.setPrefix(this.prefix.getValue());
    }
    
    @Override
    public int onTick() {
        if (!(ClickGui.mc.currentScreen instanceof CherryGui)) {
            this.disable();
        }
        return 0;
    }
    
    static {
        ClickGui.INSTANCE = new ClickGui();
    }
    
    public enum rainbowModeArray
    {
        Static, 
        Up;
    }
    
    public enum rainbowMode
    {
        Static, 
        Sideway;
    }
    
    public enum Gui
    {
        NEW, 
        OLD;
    }
}
