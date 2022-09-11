//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.entity.player.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.*;
import com.cocoapc.cherry.*;
import java.util.*;
import net.minecraft.client.gui.*;
import com.cocoapc.cherry.features.modules.misc.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class AntiBurrow extends Module
{
    private final Setting<Double> range;
    private final Setting<Boolean> disable;
    
    public AntiBurrow() {
        super("AntiBurrow", "AntiBurrow", Category.COMBAT, true, false, false);
        this.range = (Setting<Double>)this.register(new Setting("Ranges", (T)4.0, (T)0.0, (T)10.0));
        this.disable = (Setting<Boolean>)this.register(new Setting("disable", (T)true));
    }
    
    private EntityPlayer getTarget(final double range) {
        EntityPlayer target = null;
        double distance = Math.pow(range, 2.0) + 1.0;
        for (final EntityPlayer player : AutoTrap.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)player, range)) {
                if (Cherry.speedManager.getPlayerSpeed(player) > 10.0) {
                    continue;
                }
                if (target == null) {
                    target = player;
                    distance = AutoTrap.mc.player.getDistanceSq((Entity)player);
                }
                else {
                    if (AutoTrap.mc.player.getDistanceSq((Entity)player) >= distance) {
                        continue;
                    }
                    target = player;
                    distance = AutoTrap.mc.player.getDistanceSq((Entity)player);
                }
            }
        }
        return target;
    }
    
    @Override
    public int onUpdate() {
        if (fullNullCheck()) {
            return 0;
        }
        if (AntiBurrow.mc.currentScreen instanceof GuiHopper) {
            return 0;
        }
        final EntityPlayer player = this.getTarget(this.range.getValue());
        if (this.disable.getValue()) {
            this.disable();
        }
        if (player == null) {
            return 0;
        }
        final BlockPos pos = new BlockPos(player.posX, player.posY + 0.5, player.posZ);
        if (pos == null) {
            return 0;
        }
        if (InstantMine.breakPos == null) {
            return 0;
        }
        if (InstantMine.breakPos.getZ() == pos.getZ() && InstantMine.breakPos.getX() == pos.getX() && InstantMine.breakPos.getY() == pos.getY()) {
            return 0;
        }
        if (AntiBurrow.mc.world.getBlockState(pos).getBlock() == Blocks.AIR || this.isOnLiquid() || this.isInLiquid() || AntiBurrow.mc.world.getBlockState(pos).getBlock() == Blocks.WATER || AntiBurrow.mc.world.getBlockState(pos).getBlock() != Blocks.LAVA) {}
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    private boolean isOnLiquid() {
        final double y = AntiBurrow.mc.player.posY - 0.03;
        for (int x = MathHelper.floor(AntiBurrow.mc.player.posX); x < MathHelper.ceil(AntiBurrow.mc.player.posX); ++x) {
            for (int z = MathHelper.floor(AntiBurrow.mc.player.posZ); z < MathHelper.ceil(AntiBurrow.mc.player.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);
                if (AntiBurrow.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isInLiquid() {
        final double y = AntiBurrow.mc.player.posY + 0.01;
        for (int x = MathHelper.floor(AntiBurrow.mc.player.posX); x < MathHelper.ceil(AntiBurrow.mc.player.posX); ++x) {
            for (int z = MathHelper.floor(AntiBurrow.mc.player.posZ); z < MathHelper.ceil(AntiBurrow.mc.player.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, (int)y, z);
                if (AntiBurrow.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
}
