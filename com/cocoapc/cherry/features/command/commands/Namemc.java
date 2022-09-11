//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.command.commands;

import com.cocoapc.cherry.features.command.*;
import java.awt.*;
import java.net.*;
import com.mojang.realmsclient.gui.*;

public class Namemc extends Command
{
    public Namemc() {
        super("Namemc", new String[] { "<player>" });
    }
    
    public void execute(final String[] var1) {
        try {
            Desktop.getDesktop().browse(new URI("https://ja.namemc.com/profile/" + this.getArg(1).toLowerCase()));
            sendMessage(ChatFormatting.RED + "Open");
        }
        catch (Exception ex) {
            sendMessage("lol");
        }
    }
}
