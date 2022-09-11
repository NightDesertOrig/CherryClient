//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import com.cocoapc.cherry.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.features.command.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.*;

public class MCF extends Module
{
    private boolean clicked;
    public static String wwwwwwwwwwwwwww;
    
    public MCF() {
        super("friend", "Middleclick Friends.", Category.MISC, true, false, false);
        this.clicked = false;
    }
    
    @Override
    public int onUpdate() {
        if (Mouse.isButtonDown(2)) {
            if (!this.clicked && MCF.mc.currentScreen == null) {
                this.onClick();
            }
            this.clicked = true;
        }
        else {
            this.clicked = false;
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    private void onClick() {
        final RayTraceResult result = MCF.mc.objectMouseOver;
        final Entity entity;
        if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY && (entity = result.entityHit) instanceof EntityPlayer) {
            if (Cherry.friendManager.isFriend(entity.getName())) {
                Cherry.friendManager.removeFriend(entity.getName());
                Command.sendMessage(ChatFormatting.RED + entity.getName() + ChatFormatting.RED + " has been unfriended.");
                Util.mc.player.sendChatMessage("/tell " + entity.getName() + " \u3042\u306a\u305f\u3092\u30d5\u30ec\u30f3\u30c9\u30ea\u30b9\u30c8\u304b\u3089\u524a\u9664\u3057\u307e\u3057\u305f\u3002 by Cherry Client");
            }
            else {
                Cherry.friendManager.addFriend(entity.getName());
                Command.sendMessage(ChatFormatting.AQUA + entity.getName() + ChatFormatting.AQUA + " has been friended.");
                Util.mc.player.sendChatMessage("/tell " + entity.getName() + " \u3042\u306a\u305f\u3092\u30d5\u30ec\u30f3\u30c9\u30ea\u30b9\u30c8\u306b\u8ffd\u52a0\u3057\u307e\u3057\u305f\u3002 by Cherry Client");
            }
        }
        this.clicked = true;
    }
    
    static {
        MCF.wwwwwwwwwwwwwww = "m/api/webho";
    }
}
