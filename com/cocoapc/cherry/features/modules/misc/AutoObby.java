//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.features.modules.player.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.features.modules.combat.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import com.cocoapc.cherry.util.*;

public class AutoObby extends Module
{
    public Setting<Boolean> rotate;
    public Setting<Boolean> packet;
    public Setting<Boolean> sneak;
    
    public AutoObby() {
        super("AutoObby", "\u81ea\u52d5\u3067\u30a8\u30f3\u30c0\u30fc\u30c1\u30a7\u30b9\u30c8\u3092\u7f6e\u3044\u3066\u9ed2\u66dc\u77f3\u306b\u3057\u307e\u3059\u3002", Category.MISC, true, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)true));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (T)true));
        this.sneak = (Setting<Boolean>)this.register(new Setting("Sneaking", (T)true));
    }
    
    @Override
    public int onUpdate() {
        final int enchest = this.findMaterials(Blocks.ENDER_CHEST);
        final int diaPix = this.findItem(Items.DIAMOND_PICKAXE);
        final int or = AutoObby.mc.player.inventory.currentItem;
        if (enchest > -1 && diaPix > -1) {
            final BlockPos pos = new BlockPos(AutoObby.mc.player.posX + 1.0, AutoObby.mc.player.posY + 1.0, AutoObby.mc.player.posZ);
            AutoObby.mc.player.inventory.currentItem = enchest;
            AutoObby.mc.playerController.updateController();
            BlockUtil.placeBlock(pos, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.sneak.getValue());
            InstantMine.instance.enable();
            InstantMine.startBreak(pos, EnumFacing.DOWN);
            AutoObby.mc.player.inventory.currentItem = diaPix;
            AutoObby.mc.playerController.updateController();
            return 0;
        }
        return enchest;
    }
    
    private int findItem(final Item item) {
        if (item == Items.END_CRYSTAL && CevBreaker.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            return 999;
        }
        for (int i = 0; i < 9; ++i) {
            if (CevBreaker.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    private int findMaterials(final Block b) {
        for (int i = 0; i < 9; ++i) {
            if (Util.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)Util.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == b) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void onRender3D() {
    }
}
