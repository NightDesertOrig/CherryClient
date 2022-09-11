//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import java.util.*;

public class NoteBotBug extends Module
{
    private Setting<Integer> range;
    private Setting<Integer> max;
    private List<BlockPos> notes;
    
    public NoteBotBug() {
        super("NoteBug", "", Category.MISC, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (T)7, (T)10, (T)1));
        this.max = (Setting<Integer>)this.register(new Setting("MaxBlock", (T)30, (T)100, (T)1));
        this.notes = new ArrayList<BlockPos>();
    }
    
    @Override
    public int onTick() {
        int n = 0;
        this.notes = new ArrayList<BlockPos>();
        final List<BlockPos> list = BlockUtil.getSphere(PlayerUtil.getPlayerPos((EntityPlayer)NoteBotBug.mc.player), this.range.getValue(), this.range.getValue(), false, true, 0);
        for (final BlockPos blockPos : list) {
            if (BlockUtil.getBlock(blockPos) != Blocks.NOTEBLOCK) {
                continue;
            }
            NoteBotBug.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.UP));
            if (++n <= this.max.getValue()) {
                continue;
            }
            return n;
        }
        return n;
    }
    
    @Override
    public void onRender3D() {
    }
}
