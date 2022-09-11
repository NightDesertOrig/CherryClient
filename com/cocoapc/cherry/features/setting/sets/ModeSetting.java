//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.setting.sets;

import java.util.*;

public class ModeSetting extends SettingS<String>
{
    private final List<String> modes;
    
    public ModeSetting(final String name, final String defaultValue, final List<String> modes) {
        super(name, defaultValue);
        this.modes = new ArrayList<String>();
        if (!modes.contains(defaultValue)) {
            modes.add(defaultValue);
        }
        modes.forEach(it -> {
            if (!this.modes.contains(it)) {
                this.modes.add(it);
            }
        });
    }
    
    public boolean toggled(final String modeName) {
        return this.getValue().equals(modeName);
    }
    
    @Override
    public void setValue(final String modeName) {
        if (this.modes.contains(modeName)) {
            this.value = (T)modeName;
        }
    }
    
    public void forwardLoop() {
        final int index = this.modes.indexOf(this.value);
        if (index == this.modes.size() - 1) {
            this.value = (T)this.modes.get(0);
        }
        else {
            this.value = (T)this.modes.get(index + 1);
        }
    }
    
    public List<String> getModes() {
        return this.modes;
    }
}
