//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.init.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

public class Slime extends Module
{
    public Setting<Integer> set;
    
    public Slime() {
        super("Slime", "Slime Fly", Module.Category.MOVEMENT, true, false, false);
        this.set = (Setting<Integer>)this.register(new Setting("Speed", (T)50, (T)1, (T)100));
    }
    
    public int onUpdate() {
        final int num = Integer.parseInt(String.valueOf(this.set.getValue()));
        Slime.mc.timer.tickLength = (float)num;
        Slime.mc.player.motionY = 0.0;
        Slime.mc.player.onGround = true;
        return num;
    }
    
    private int findItem(final Item item) {
        if (item == Items.END_CRYSTAL && Util.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            return 999;
        }
        for (int i = 0; i < 9; ++i) {
            if (Util.mc.player.inventory.getStackInSlot(i).getItem() == item) {
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
    
    public int onDisable() {
        Slime.mc.timer.tickLength = 50.0f;
        return 0;
    }
    
    public void onRender3D() {
    }
}
