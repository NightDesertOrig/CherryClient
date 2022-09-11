//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;

public class AutoTrapBurrow extends Module
{
    private final Setting<Integer> range;
    
    public AutoTrapBurrow() {
        super("AutoTrapBurrow", "AutoTrapBurrow", Category.COMBAT, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (T)3, (T)0, (T)30));
    }
    
    @Override
    public int onUpdate() {
        final EntityPlayer target = PlayerUtil.getNearestPlayer(this.range.getValue());
        if (target == null) {
            return 0;
        }
        final BlockPos pos = new BlockPos(AutoTrapBurrow.mc.player.posX, AutoTrapBurrow.mc.player.posY, AutoTrapBurrow.mc.player.posZ + 1.0);
        final BlockPos pos2 = new BlockPos(AutoTrapBurrow.mc.player.posX, AutoTrapBurrow.mc.player.posY, AutoTrapBurrow.mc.player.posZ - 1.0);
        final BlockPos pos3 = new BlockPos(AutoTrapBurrow.mc.player.posX + 1.0, AutoTrapBurrow.mc.player.posY, AutoTrapBurrow.mc.player.posZ);
        final BlockPos pos4 = new BlockPos(AutoTrapBurrow.mc.player.posX - 1.0, AutoTrapBurrow.mc.player.posY, AutoTrapBurrow.mc.player.posZ);
        final BlockPos pos5 = new BlockPos(AutoTrapBurrow.mc.player.posX, AutoTrapBurrow.mc.player.posY - 0.5, AutoTrapBurrow.mc.player.posZ);
        if (BlockUtil.getBlock(pos) == Blocks.AIR) {
            return 0;
        }
        if (BlockUtil.getBlock(pos2) == Blocks.AIR) {
            return 0;
        }
        if (BlockUtil.getBlock(pos3) == Blocks.AIR) {
            return 0;
        }
        if (BlockUtil.getBlock(pos5) == Blocks.AIR) {
            return 0;
        }
        if (BlockUtil.getBlock(pos4) == Blocks.AIR) {
            return 0;
        }
        TrapPhaseRewrite.INSTANCE.enable();
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
