//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import com.cocoapc.cherry.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.features.command.*;
import java.util.*;

public class PearlNotify extends Module
{
    private final HashMap<EntityPlayer, UUID> list;
    private Entity enderPearl;
    private boolean flag;
    
    public PearlNotify() {
        super("PearlNotify", "Notify pearl throws.", Category.MISC, true, false, false);
        this.list = new HashMap<EntityPlayer, UUID>();
    }
    
    @Override
    public void onEnable() {
        this.flag = true;
    }
    
    @Override
    public int onUpdate() {
        if (PearlNotify.mc.world == null || PearlNotify.mc.player == null) {
            return 0;
        }
        this.enderPearl = null;
        for (final Entity e : PearlNotify.mc.world.loadedEntityList) {
            if (e instanceof EntityEnderPearl) {
                this.enderPearl = e;
                break;
            }
        }
        if (this.enderPearl == null) {
            this.flag = true;
            return 0;
        }
        EntityPlayer closestPlayer = null;
        for (final EntityPlayer entity : PearlNotify.mc.world.playerEntities) {
            if (closestPlayer == null) {
                closestPlayer = entity;
            }
            else {
                if (closestPlayer.getDistance(this.enderPearl) <= entity.getDistance(this.enderPearl)) {
                    continue;
                }
                closestPlayer = entity;
            }
        }
        if (closestPlayer == PearlNotify.mc.player) {
            this.flag = false;
        }
        if (closestPlayer != null && this.flag) {
            String faceing = this.enderPearl.getHorizontalFacing().toString();
            if (faceing.equals("west")) {
                faceing = "east";
            }
            else if (faceing.equals("east")) {
                faceing = "west";
            }
            Command.sendMessage(Cherry.friendManager.isFriend(closestPlayer.getName()) ? (ChatFormatting.AQUA + closestPlayer.getName() + ChatFormatting.DARK_GRAY + " has just thrown a pearl heading " + faceing + "!") : (ChatFormatting.RED + closestPlayer.getName() + ChatFormatting.DARK_GRAY + " has just thrown a pearl heading " + faceing + "!"));
            this.flag = false;
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
