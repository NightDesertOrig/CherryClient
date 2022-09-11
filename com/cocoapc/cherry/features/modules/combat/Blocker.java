//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.awt.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.util.*;

public class Blocker extends Module
{
    private final Timer timer;
    private final Setting<Integer> delay;
    
    public Blocker() {
        super("Blocker2", "blocker", Category.COMBAT, true, false, false);
        this.timer = new Timer();
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)50, (T)0, (T)250));
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
    
    @Override
    public void onRender3D(final Render3DEvent event) {
        final BlockPos pos = new BlockPos(Blocker.mc.player.posX, Blocker.mc.player.posY + 2.0, Blocker.mc.player.posZ);
        final BlockPos posa = new BlockPos(Blocker.mc.player.posX, Blocker.mc.player.posY + 4.0, Blocker.mc.player.posZ);
        final int obby = this.findMaterials(Blocks.OBSIDIAN);
        final int anv = this.findMaterials(Blocks.ANVIL);
        final int or = AnvilTrap.mc.player.inventory.currentItem;
        if (obby > -1 && anv > -1 && BlockUtil.getBlock(pos) == Blocks.OBSIDIAN) {
            if (BlockUtil.getBlock(posa) == Blocks.ANVIL) {
                return;
            }
            this.timer.passedMs(this.delay.getValue());
            final BlockPos pos2 = new BlockPos(Blocker.mc.player.posX, Blocker.mc.player.posY + 3.0, Blocker.mc.player.posZ);
            final BlockPos pos3 = new BlockPos(Blocker.mc.player.posX, Blocker.mc.player.posY + 4.0, Blocker.mc.player.posZ);
            Util.mc.player.inventory.currentItem = obby;
            AnvilTrap.mc.playerController.updateController();
            RenderUtil.drawBox(pos2, Color.cyan);
            BlockUtil.placeBlock(pos2, EnumHand.MAIN_HAND, true, true, false);
            Util.mc.player.inventory.currentItem = anv;
            AnvilTrap.mc.playerController.updateController();
            RenderUtil.drawBox(pos3, Color.cyan);
            BlockUtil.placeBlock(pos3, EnumHand.MAIN_HAND, true, true, false);
            Util.mc.player.inventory.currentItem = or;
            AnvilTrap.mc.playerController.updateController();
            this.timer.reset();
        }
    }
    
    @Override
    public void onRender3D() {
    }
}
