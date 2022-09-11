//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraft.client.*;

public class Kill extends Module
{
    public Kill() {
        super("Suicide", "Ez Spam if you have bariton", Category.MISC, true, false, false);
    }
    
    @Override
    public void onEnable() {
        Kill.mc.player.sendChatMessage("/kill");
        Kill.mc.player.sendChatMessage("kill");
        Command.sendMessage("Kill");
        System.out.println(Kill.mc.player.getName() + Kill.mc.player.getDisplayName() + Minecraft.getMinecraft().player.getName());
        this.disable();
    }
    
    @Override
    public void onRender3D() {
    }
}
