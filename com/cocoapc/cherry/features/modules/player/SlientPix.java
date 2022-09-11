//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.player;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import com.cocoapc.cherry.features.modules.combat.*;

public class SlientPix extends Module
{
    public SlientPix() {
        super("SlientPixel", "SlientPixel", Module.Category.PLAYER, true, false, false);
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
    
    public void onEnable() {
        final int diaPix = this.findItem(Items.DIAMOND_PICKAXE);
        final int or = SlientPix.mc.player.inventory.currentItem;
        if (diaPix > -1) {
            SlientPix.mc.player.inventory.currentItem = diaPix;
            SlientPix.mc.playerController.updateController();
            SlientPix.mc.player.inventory.currentItem = or;
            SlientPix.mc.playerController.updateController();
            this.disable();
        }
    }
    
    public void onRender3D() {
    }
}
