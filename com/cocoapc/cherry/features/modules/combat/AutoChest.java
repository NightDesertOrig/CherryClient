//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.block.state.*;

public class AutoChest extends Module
{
    public static AutoChest INSTANCE;
    private Setting<Boolean> autoToggle;
    public final Setting<Integer> WTF;
    public Setting<modes> mode;
    
    public AutoChest() {
        super("AntiFacePlace", "", Category.MISC, true, false, false);
        this.autoToggle = (Setting<Boolean>)this.register(new Setting("Toggle", (T)true));
        this.WTF = (Setting<Integer>)this.register(new Setting("WTF", (T)1, (T)1, (T)50));
        this.mode = (Setting<modes>)this.register(new Setting("Block", (T)modes.OB, "Blocks"));
        AutoChest.INSTANCE = this;
    }
    
    @Override
    public int onUpdate() {
        if (AutoChest.mc.player == null) {
            return 0;
        }
        switch (AutoChest.INSTANCE.mode.getValue()) {
            case EN_CHEST: {
                final int Blocked = this.findMaterials(Blocks.ENDER_CHEST);
                if (Blocked == -1) {
                    this.autoToggle();
                    return Blocked;
                }
                final BlockPos[] offset = { new BlockPos((int)AutoChest.INSTANCE.WTF.getValue(), 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
                final BlockPos pos = new BlockPos(AutoChest.mc.player.posX, AutoChest.mc.player.posY, AutoChest.mc.player.posZ);
                final int oldslot = AutoChest.mc.player.inventory.currentItem;
                AutoChest.mc.player.inventory.currentItem = Blocked;
                AutoChest.mc.playerController.updateController();
                for (int i = 0; i < offset.length; ++i) {
                    final BlockPos base = pos.add((Vec3i)offset[i]);
                    if (this.getBlock(base).getBlock() != Blocks.AIR && !(this.getBlock(base).getBlock() instanceof BlockLiquid) && this.getBlock(base.add(0, 1, 0)).getBlock() == Blocks.AIR) {
                        BlockUtil.placeBlock(base.add(0, 1, 0), EnumHand.MAIN_HAND, true, false, false);
                    }
                }
                AutoChest.mc.player.inventory.currentItem = oldslot;
                AutoChest.mc.playerController.updateController();
                if (this.autoToggle.getValue()) {
                    this.autoToggle();
                }
                return Blocked;
            }
            case OB: {
                final int Blocked = this.findMaterials(Blocks.OBSIDIAN);
                if (Blocked == -1) {
                    this.autoToggle();
                    return Blocked;
                }
                final BlockPos[] offset = { new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1) };
                final BlockPos pos = new BlockPos(AutoChest.mc.player.posX, AutoChest.mc.player.posY, AutoChest.mc.player.posZ);
                final int oldslot = AutoChest.mc.player.inventory.currentItem;
                AutoChest.mc.player.inventory.currentItem = Blocked;
                AutoChest.mc.playerController.updateController();
                for (int i = 0; i < offset.length; ++i) {
                    final BlockPos base = pos.add((Vec3i)offset[i]);
                    if (this.getBlock(base).getBlock() != Blocks.AIR && !(this.getBlock(base).getBlock() instanceof BlockLiquid) && this.getBlock(base.add(0, 1, 0)).getBlock() == Blocks.AIR) {
                        BlockUtil.placeBlock(base.add(0, 1, 0), EnumHand.MAIN_HAND, true, false, false);
                    }
                }
                AutoChest.mc.player.inventory.currentItem = oldslot;
                AutoChest.mc.playerController.updateController();
                if (this.autoToggle.getValue()) {
                    this.autoToggle();
                }
                return Blocked;
            }
            default: {
                return 0;
            }
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    private void autoToggle() {
        if (this.autoToggle.getValue()) {
            this.disable();
        }
    }
    
    private int findMaterials(final Block b) {
        for (int i = 0; i < 9; ++i) {
            if (AutoChest.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)AutoChest.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == b) {
                return i;
            }
        }
        return -1;
    }
    
    private IBlockState getBlock(final BlockPos b) {
        return AutoChest.mc.world.getBlockState(b);
    }
    
    public enum modes
    {
        OB, 
        EN_CHEST;
    }
}
