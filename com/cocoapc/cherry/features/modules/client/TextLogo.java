//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraftforge.client.event.*;
import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class TextLogo extends Module
{
    public Setting<Integer> X;
    public Setting<Integer> Y;
    public Setting<Integer> R;
    public Setting<Integer> G;
    public Setting<Integer> B;
    public Setting<Integer> A;
    
    public TextLogo() {
        super("TextLogo", "TextLogo", Category.CLIENT, true, false, false);
        this.X = (Setting<Integer>)this.register(new Setting("X", (T)255, (T)1, (T)255));
        this.Y = (Setting<Integer>)this.register(new Setting("Y", (T)255, (T)1, (T)255));
        this.R = (Setting<Integer>)this.register(new Setting("R", (T)255, (T)1, (T)255));
        this.G = (Setting<Integer>)this.register(new Setting("G", (T)255, (T)1, (T)255));
        this.B = (Setting<Integer>)this.register(new Setting("B", (T)255, (T)1, (T)255));
        this.A = (Setting<Integer>)this.register(new Setting("A", (T)255, (T)1, (T)255));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (Feature.nullCheck()) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            Cherry.textManager.drawStringBige("Cherry", 4.0f, 0.0f, ColorUtil.toRGBA(this.R.getValue(), this.G.getValue(), this.B.getValue(), this.A.getValue()), false);
            Cherry.textManager.drawStringBige("      Client", 4.0f, (float)(Cherry.textManager.getFontHeight() + 7), ColorUtil.toRGBA(255, 255, 255, 255), false);
        }
    }
    
    @Override
    public void onRender3D() {
    }
}
