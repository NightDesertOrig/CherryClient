//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.setting;

import java.util.function.*;

public class SettingK<T>
{
    private final String name;
    private T value;
    private String[] values;
    private T maxValue;
    private T minValue;
    private Predicate<T> visible;
    
    public SettingK(final String name, final T value) {
        this.name = name;
        this.value = value;
    }
    
    public SettingK(final String name, final T value, final T maxValue, final T minValue) {
        this.name = name;
        this.value = value;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }
    
    public SettingK(final String name, final T value, final String[] values) {
        this.name = name;
        this.value = value;
        this.values = values;
    }
    
    public SettingK(final String name, final T value, final Predicate<T> visible) {
        this.name = name;
        this.value = value;
        this.visible = visible;
    }
    
    public SettingK(final String name, final T value, final T maxValue, final T minValue, final Predicate<T> visible) {
        this.name = name;
        this.value = value;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.visible = visible;
    }
    
    public SettingK(final String name, final T value, final String[] values, final Predicate<T> visible) {
        this.name = name;
        this.value = value;
        this.values = values;
        this.visible = visible;
    }
    
    public String getName() {
        return this.name;
    }
    
    public T getValue() {
        return this.value;
    }
    
    public T setValue(final T value) {
        return this.value = value;
    }
    
    public T getMaxValue() {
        return this.maxValue;
    }
    
    public void setMaxValue(final T value) {
        this.maxValue = value;
    }
    
    public T getMinValue() {
        return this.minValue;
    }
    
    public String[] getValues() {
        return this.values;
    }
    
    public boolean isVisible() {
        return this.visible == null || this.visible.test(this.getValue());
    }
}
