//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;

public class Description extends Module
{
    public static Description INSTANCE;
    public Setting<Boolean> Description;
    
    public Description() {
        super("Description", "Module\u306e\u8aac\u660e\u3092\u8868\u793a\u3057\u307e\u3059\u3002", Category.CLIENT, true, false, false);
        this.Description = (Setting<Boolean>)this.register(new Setting("Enable", (T)false));
        com.cocoapc.cherry.features.modules.client.Description.INSTANCE = this;
    }
    
    @Override
    public int onUpdate() {
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
