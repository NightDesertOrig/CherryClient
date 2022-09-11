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

public class ArrayLists extends Module
{
    public final Setting<Integer> X;
    public final Setting<Integer> R;
    public final Setting<Integer> G;
    public final Setting<Integer> B;
    public final Setting<Integer> A;
    Module module;
    
    public ArrayLists() {
        super("ArrayLIST", "E", Category.CLIENT, true, false, false);
        this.X = (Setting<Integer>)this.register(new Setting("X", (T)0, (T)1, (T)2000));
        this.R = (Setting<Integer>)this.register(new Setting("RED", (T)255, (T)1, (T)255));
        this.G = (Setting<Integer>)this.register(new Setting("GREEN", (T)0, (T)1, (T)255));
        this.B = (Setting<Integer>)this.register(new Setting("BLUE", (T)0, (T)1, (T)255));
        this.A = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)1, (T)255));
    }
    
    @SubscribeEvent
    public void onUpdate(final RenderGameOverlayEvent.Post event) {
        if (Feature.nullCheck()) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            int j = 0;
            int a = 0;
            for (j = 0; j < Cherry.moduleManager.sortedModulesABC.size(); ++j) {
                a += Cherry.m_font.impact5.getHeight() + 5;
                final String s1 = Cherry.moduleManager.sortedModulesABC.get(j);
                Cherry.m_font.impact5.drawString(s1, this.X.getValue(), (float)a, ColorUtil.toRGBA(this.R.getValue(), this.G.getValue(), this.B.getValue(), this.A.getValue()));
                System.out.println(a);
            }
        }
    }
    
    @Override
    public void onRender3D() {
    }
}
