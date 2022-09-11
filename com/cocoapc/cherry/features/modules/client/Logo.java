//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.util.*;

public class Logo extends Module
{
    public static final ResourceLocation mark;
    public Setting<Integer> imageX;
    public Setting<Integer> imageY;
    public Setting<Integer> imageWidth;
    public Setting<Integer> imageHeight;
    private int color;
    
    public Logo() {
        super("Logo", "\u30ed\u30b4", Category.CLIENT, false, false, false);
        this.imageX = (Setting<Integer>)this.register(new Setting("logoX", (T)0, (T)0, (T)300));
        this.imageY = (Setting<Integer>)this.register(new Setting("logoY", (T)32, (T)0, (T)300));
        this.imageWidth = (Setting<Integer>)this.register(new Setting("logoWidth", (T)97, (T)0, (T)1000));
        this.imageHeight = (Setting<Integer>)this.register(new Setting("logoHeight", (T)60, (T)0, (T)1000));
    }
    
    public void renderLogo() {
        final int width = this.imageWidth.getValue();
        final int height = this.imageHeight.getValue();
        final int x = this.imageX.getValue();
        final int y = this.imageY.getValue();
        Util.mc.renderEngine.bindTexture(Logo.mark);
        GlStateManager.color(255.0f, 255.0f, 255.0f);
        Gui.drawScaledCustomSizeModalRect(x - 2, y - 36, 7.0f, 7.0f, width - 7, height - 7, width, height, (float)width, (float)height);
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (!Feature.fullNullCheck()) {
            final int width = this.renderer.scaledWidth;
            final int height = this.renderer.scaledHeight;
            this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
            if (this.enabled.getValue()) {
                this.renderLogo();
            }
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        mark = new ResourceLocation("textures/DL1.png");
    }
}
