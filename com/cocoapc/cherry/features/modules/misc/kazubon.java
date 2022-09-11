//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class kazubon extends Module
{
    public kazubon() {
        super("Kazubon", "kazubon  ga irerotte itta", Category.MISC, true, false, false);
    }
    
    @Override
    public int onUpdate() {
        kazubon.mc.player.connection.sendPacket((Packet)new CPacketCloseWindow(1));
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
