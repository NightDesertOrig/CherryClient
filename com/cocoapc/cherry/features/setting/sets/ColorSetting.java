//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.setting.sets;

import java.awt.*;

public class ColorSetting extends SettingS<Color>
{
    public ColorSetting(final String name, final Color defaultValue) {
        super(name, defaultValue);
    }
    
    public Color getColor() {
        return (Color)this.value;
    }
}
