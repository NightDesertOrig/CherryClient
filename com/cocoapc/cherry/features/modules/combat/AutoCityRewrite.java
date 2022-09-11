//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;

public class AutoCityRewrite extends Module
{
    public Setting<Integer> ranges;
    
    public AutoCityRewrite() {
        super("AutoCityRewrite", "AutoCityRewrite!", Category.COMBAT, true, false, false);
        this.ranges = (Setting<Integer>)this.register(new Setting("Range", (T)5, (T)1, (T)8));
    }
    
    @Override
    public void onEnable() {
        final EntityPlayer target = PlayerUtil.getNearestPlayer(this.ranges.getValue());
        if (target == null) {
            return;
        }
        final BlockPos pos1 = new BlockPos(target.posX + 1.0, target.posY, target.posZ);
        final BlockPos pos2 = new BlockPos(target.posX, target.posY, target.posZ + 1.0);
        final BlockPos pos3 = new BlockPos(target.posX - 1.0, target.posY, target.posZ);
        final BlockPos pos4 = new BlockPos(target.posX, target.posY, target.posZ - 1.0);
        if (BlockUtil.getBlock(pos1) == Blocks.OBSIDIAN) {
            AutoCityRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos1, EnumFacing.DOWN));
            this.disable();
        }
        else if (BlockUtil.getBlock(pos2) == Blocks.OBSIDIAN) {
            AutoCityRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos2, EnumFacing.DOWN));
            this.disable();
        }
        else if (BlockUtil.getBlock(pos3) == Blocks.OBSIDIAN) {
            AutoCityRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos3, EnumFacing.DOWN));
            this.disable();
        }
        else if (BlockUtil.getBlock(pos4) == Blocks.OBSIDIAN) {
            AutoCityRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, pos4, EnumFacing.DOWN));
            this.disable();
        }
        else {
            this.disable();
        }
    }
    
    @Override
    public void onRender3D() {
    }
}
