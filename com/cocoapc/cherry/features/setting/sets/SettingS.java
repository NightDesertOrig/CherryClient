//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.setting.sets;

import java.util.function.*;
import java.util.*;

public class SettingS<T>
{
    private final String name;
    private final T defaultValue;
    protected T value;
    private final List<BooleanSupplier> visibilities;
    public float optionAnimNow;
    public float optionAnim;
    
    public SettingS(final String name, final T defaultValue) {
        this.visibilities = new ArrayList<BooleanSupplier>();
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }
    
    public T getDefaultValue() {
        return this.defaultValue;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public void setValue(final T valueIn) {
        this.value = valueIn;
    }
    
    public SettingS<T> when(final BooleanSupplier booleanSupplier) {
        this.visibilities.add(booleanSupplier);
        return this;
    }
    
    public SettingS<T> whenAtMode(final SettingS<String> modeSetting, final String mode) {
        return this.when(() -> modeSetting.getValue().equals(mode));
    }
    
    public SettingS<T> whenFalse(final SettingS<Boolean> booleanSetting) {
        return this.when(() -> !booleanSetting.getValue());
    }
    
    public SettingS<T> whenTrue(final SettingS<Boolean> booleanSetting) {
        return this.when(booleanSetting::getValue);
    }
    
    public boolean isVisible() {
        for (final BooleanSupplier booleanSupplier : this.visibilities) {
            if (!booleanSupplier.getAsBoolean()) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        return this.name;
    }
}
