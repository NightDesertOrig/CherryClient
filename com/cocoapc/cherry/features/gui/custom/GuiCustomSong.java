//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.custom;

import java.util.*;
import java.net.*;
import net.minecraftforge.fml.common.*;

public class GuiCustomSong
{
    public static List<String> getHWIDList() {
        final ArrayList HWIDList = new ArrayList();
        try {
            final URL url1 = new URL("https://pastebin.com/raw/rtD4fBmG");
            final URL url2 = new URL("https://pastebin.com/raw/rtD4fBmG");
            final URL url3 = new URL("https://pastebin.com/raw/rtD4fBmG");
        }
        catch (Exception var4) {
            FMLLog.log.info("Load HWID Failed!");
        }
        return (List<String>)HWIDList;
    }
}
