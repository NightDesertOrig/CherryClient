//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class MainMenu extends Module
{
    private static MainMenu INSTANCE;
    public Setting<Boolean> mainScreen;
    public Setting<Boolean> particles;
    
    public MainMenu() {
        super("CustomMainMenu", "MainMenuScreen", Category.CLIENT, true, false, false);
        this.mainScreen = (Setting<Boolean>)this.register(new Setting("MainScreen", (T)true));
        this.particles = (Setting<Boolean>)this.register(new Setting("Particles", (T)true));
        this.setInstance();
    }
    
    public static MainMenu getInstance() {
        if (MainMenu.INSTANCE == null) {
            MainMenu.INSTANCE = new MainMenu();
        }
        return MainMenu.INSTANCE;
    }
    
    private void setInstance() {
        MainMenu.INSTANCE = this;
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        MainMenu.INSTANCE = new MainMenu();
    }
}
