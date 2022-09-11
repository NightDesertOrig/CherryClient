//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import com.cocoapc.cherry.features.modules.combat.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class SelfBow extends Module
{
    public SelfBow() {
        super("SelfBow", "SelfBow", Category.MISC, true, false, false);
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
    
    @Override
    public int onUpdate() {
        final int anv = this.findItem((Item)Items.BOW);
        final int or = SelfBow.mc.player.inventory.currentItem;
        SelfBow.mc.player.inventory.currentItem = anv;
        SelfBow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(0.0f, -90.0f, true));
        SelfBow.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        SelfBow.mc.player.setActiveHand(EnumHand.MAIN_HAND);
        SelfBow.mc.player.inventory.currentItem = or;
        this.disable();
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
}
