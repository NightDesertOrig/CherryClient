//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.noti;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.util.util2.*;

public class Notification
{
    private final String messsage;
    private final String title;
    private long start;
    private final long fadedIn;
    private final long fadeOut;
    private final long end;
    
    public Notification(final String message, final String title) {
        this.messsage = message;
        this.title = title;
        this.fadedIn = 350L;
        this.fadeOut = 2000L;
        this.end = this.fadeOut + this.fadedIn;
    }
    
    public void show() {
        this.start = System.currentTimeMillis();
    }
    
    public boolean isShown() {
        return this.getTime() <= this.end;
    }
    
    private long getTime() {
        return System.currentTimeMillis() - this.start;
    }
    
    public void render(int y) {
        final CFontRenderer fontRenderer = FontManager.fontRenderer;
        final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        final int width = 110 + fontRenderer.getStringWidth(this.messsage);
        final int height = 55;
        final long time = this.getTime();
        if (y > 510) {
            y = 65;
        }
        double offset;
        if (time < this.fadedIn) {
            offset = Math.tanh(time / (double)this.fadedIn * 3.0) * width;
        }
        else if (time > this.fadeOut) {
            offset = Math.tanh(3.0 - (time - this.fadeOut) / (double)(this.end - this.fadeOut) * 3.0) * width;
        }
        else {
            offset = width;
        }
        RenderUtil.drawImage(new ResourceLocation("textures/notification.png"), (int)(res.getScaledWidth() - offset), res.getScaledHeight() - y, width, 55);
        RenderUtil.drawImage(new ResourceLocation("textures/information.png"), (int)(res.getScaledWidth() - offset + 20.0), res.getScaledHeight() - y + 16, 22, 22);
        Fonts.font26.drawString(this.title, (float)(int)(res.getScaledWidth() - offset + 60.0), (float)(res.getScaledHeight() - y + 15), -1);
        Fonts.font18.drawString(this.messsage, (float)(int)(res.getScaledWidth() - offset + 60.0), (float)(res.getScaledHeight() - y + 35), -1);
    }
}
