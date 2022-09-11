//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.setting.sets;

public class NumberSetting<T extends Number> extends SettingS<T>
{
    private final T min;
    private T max;
    
    public NumberSetting(final String name, final T defaultValue, final T min, final T max) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
    }
    
    public T getMin() {
        return this.min;
    }
    
    public T getMax() {
        return this.max;
    }
    
    public void setMax(final T m) {
        this.max = m;
    }
    
    public boolean isInRange(final Number valueIn) {
        return valueIn.doubleValue() <= this.max.doubleValue() && valueIn.doubleValue() >= this.min.doubleValue();
    }
}
