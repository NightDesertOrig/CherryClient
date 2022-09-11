//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import com.cocoapc.cherry.features.modules.*;
import com.google.gson.*;
import java.net.*;
import java.util.concurrent.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;

public class PlayerSpoofer extends Module
{
    public static PlayerSpoofer INSTANCE;
    public String name;
    public File tmp;
    
    public PlayerSpoofer() {
        super("SP", "", Module.Category.RENDER, true, false, false);
        this.name = "travis";
        PlayerSpoofer.INSTANCE = this;
    }
    
    public void onEnable() {
        try {
            this.tmp = new File("Wurstplus3" + File.separator + "tmp");
            if (!this.tmp.exists()) {
                this.tmp.mkdirs();
            }
            final Gson gson = new Gson();
            if (this.name == null) {
                this.disable();
            }
            final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + this.name);
            final Reader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final Map<?, ?> map = (Map<?, ?>)gson.fromJson(reader, (Class)Map.class);
            final ConcurrentHashMap<String, String> valsMap = new ConcurrentHashMap<String, String>();
            for (final Map.Entry<?, ?> entry : map.entrySet()) {
                final String key = (String)entry.getKey();
                final String val = (String)entry.getValue();
                valsMap.put(key, val);
            }
            reader.close();
            final String uuid = valsMap.get("id");
            final URL url2 = new URL("https://mc-heads.net/skin/" + uuid);
            final BufferedImage image = ImageIO.read(url2);
            ImageIO.write(image, "png", new File("Wurstplus3/tmp/skin.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int onDisable() {
        this.deleteSkinChangerFiles();
        return 0;
    }
    
    public void deleteSkinChangerFiles() {
        for (final File file : PlayerSpoofer.mc.gameDir.listFiles()) {
            if (!file.isDirectory() && file.getName().contains("-skinchanger")) {
                file.delete();
            }
        }
    }
    
    public String getOldName() {
        return PlayerSpoofer.mc.getSession().getUsername();
    }
    
    public String getDisplayInfo() {
        return this.name;
    }
    
    public void onRender3D() {
    }
}
