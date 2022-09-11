//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class TitleUtils
{
    int ticks;
    int bruh;
    int breakTimer;
    String bruh1;
    boolean qwerty;
    
    public TitleUtils() {
        this.ticks = 0;
        this.bruh = 0;
        this.breakTimer = 0;
        this.bruh1 = "CherryClient / 5.0";
        this.qwerty = false;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        ++this.ticks;
        if (this.ticks % 17 == 0) {
            Display.setTitle(this.bruh1.substring(0, this.bruh1.length() - this.bruh));
            if ((this.bruh == this.bruh1.length() && this.breakTimer != 0) || (this.bruh == 0 && this.breakTimer != 0)) {
                ++this.breakTimer;
                return;
            }
            this.breakTimer = 0;
            if (this.bruh == this.bruh1.length()) {
                this.qwerty = true;
            }
            this.bruh = (this.qwerty ? (--this.bruh) : (++this.bruh));
            if (this.bruh == 0) {
                this.qwerty = false;
            }
        }
    }
}
