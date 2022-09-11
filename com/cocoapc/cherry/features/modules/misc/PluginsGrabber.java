//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.server.*;
import java.util.*;
import joptsimple.internal.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PluginsGrabber extends Module
{
    public PluginsGrabber() {
        super("PluginsLog", "Attempts to grab and display the plugins installed on a server", Category.MISC, true, false, false);
    }
    
    @Override
    public void onEnable() {
        if (nullCheck()) {
            return;
        }
        PluginsGrabber.mc.player.connection.sendPacket((Packet)new CPacketTabComplete("/", (BlockPos)null, false));
    }
    
    @Override
    public void onRender3D() {
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketTabComplete) {
            final SPacketTabComplete packetTabComplete = (SPacketTabComplete)event.getPacket();
            final ArrayList<String> plugins = new ArrayList<String>();
            final String[] getMatches;
            final String[] commands = getMatches = packetTabComplete.getMatches();
            for (final String s : getMatches) {
                final String[] command = s.split(":");
                if (command.length > 1) {
                    final String pluginName;
                    if (!plugins.contains(pluginName = command[0].replace("/", ""))) {
                        plugins.add(pluginName);
                    }
                }
            }
            Collections.sort(plugins);
            if (!plugins.isEmpty()) {
                Command.sendMessage("Plugins §7(§8" + plugins.size() + "§7): §9" + Strings.join((String[])plugins.toArray(new String[0]), "§7, §9"));
            }
            else {
                Command.sendMessage("Failed to detect Plugins");
            }
            this.disable();
        }
    }
}
