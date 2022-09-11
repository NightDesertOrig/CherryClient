//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui;

import com.cocoapc.cherry.features.gui.components.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.gui.components.items.buttons.*;
import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.features.gui.components.items.*;
import java.util.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import java.io.*;

public class CherryGui extends GuiScreen
{
    public String moving;
    private Component hubComponent;
    private static CherryGui sweetplusGui;
    private static CherryGui INSTANCE;
    Random random;
    private ArrayList<Snow> _snowList;
    public ParticleSystem particleSystem;
    private final ArrayList<Component> components;
    
    public CherryGui() {
        this.moving = "";
        this.hubComponent = null;
        this.random = new Random();
        this._snowList = new ArrayList<Snow>();
        this.components = new ArrayList<Component>();
        this.setInstance();
        this._snowList = new ArrayList<Snow>();
        this.load();
    }
    
    public static CherryGui getInstance() {
        if (CherryGui.INSTANCE == null) {
            CherryGui.INSTANCE = new CherryGui();
        }
        return CherryGui.INSTANCE;
    }
    
    private void playMusic() {
        if (!this.mc.soundHandler.isSoundPlaying(Cherry.SONG_MANAGER.getMenuSong())) {
            this.mc.soundHandler.playSound(Cherry.SONG_MANAGER.getMenuSong());
        }
    }
    
    private void stop() {
        this.mc.soundHandler.stopSound(Cherry.SONG_MANAGER.getMenuSong());
    }
    
    public static CherryGui getClickGui() {
        return getInstance();
    }
    
    private void setInstance() {
        CherryGui.INSTANCE = this;
    }
    
    private void load() {
        for (int i = 0; i < 100; ++i) {
            for (int y = 0; y < 3; ++y) {
                final Snow snow = new Snow(25 * i, y * -50, this.random.nextInt(3) + 1, this.random.nextInt(2) + 1);
            }
        }
        int x = -84;
        for (final Module.Category category : Cherry.moduleManager.getCategories()) {
            final ArrayList<Component> components2 = this.components;
            final String name = category.getName();
            x += 90;
            components2.add(new Component(name, x, 4, true) {
                @Override
                public void setupItems() {
                    CherryGui$1.counter1 = new int[] { 1 };
                    Cherry.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton(new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(components -> components.getItems().sort(Comparator.comparingInt(Feature::getNameLength).reversed()));
    }
    
    public void updateModule(final Module module) {
        for (final Component component : this.components) {
            for (final Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) {
                    continue;
                }
                final ModuleButton button = (ModuleButton)item;
                final Module mod = button.getModule();
                if (module == null) {
                    continue;
                }
                if (!module.equals(mod)) {
                    continue;
                }
                button.initSettings();
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.fontRenderer.drawString("Hello", 0, 300, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB());
        if (ClickGui.getInstance().dark.getValue()) {
            this.drawDefaultBackground();
        }
        final ScaledResolution res = new ScaledResolution(this.mc);
        this.checkMouseWheel();
        this.components.forEach(components -> components.drawScreen(mouseX, mouseY, partialTicks));
        final ScaledResolution sr = new ScaledResolution(this.mc);
        if (this.particleSystem != null && ClickGui.getInstance().particles.getValue()) {
            this.particleSystem.render(mouseX, mouseY);
        }
        else {
            this.particleSystem = new ParticleSystem(new ScaledResolution(this.mc));
        }
        this.components.forEach(components -> components.drawScreen(mouseX, mouseY, partialTicks));
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
        this.components.forEach(components -> components.mouseClicked(mouseX, mouseY, clickedButton));
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int releaseButton) {
        this.components.forEach(components -> components.mouseReleased(mouseX, mouseY, releaseButton));
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public final ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void checkMouseWheel() {
        final int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        }
        else if (dWheel > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
    }
    
    public void updateScreen() {
        if (this.particleSystem != null) {
            this.particleSystem.update();
        }
    }
    
    public int getTextOffset() {
        return -6;
    }
    
    public Component getComponentByName(final String name) {
        for (final Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return component;
        }
        return null;
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.components.forEach(component -> component.onKeyTyped(typedChar, keyCode));
    }
    
    static {
        CherryGui.INSTANCE = new CherryGui();
    }
}
