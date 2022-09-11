//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.noti;

import net.minecraft.client.*;
import net.minecraftforge.client.event.*;
import com.cocoapc.cherry.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class tests extends GuiScreen
{
    Minecraft mc;
    
    public tests() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void hud(final RenderGameOverlayEvent e) {
        final ScaledResolution resolution = new ScaledResolution(this.mc);
        final FontRenderer renderer = this.mc.fontRenderer;
        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Cherry.textManager.drawStringBig1("EZZ", 0.0f, 0.0f, Color.CYAN.getRGB(), false);
        }
    }
}
