//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.block.state.*;

public class NewBlocker extends Module
{
    private Setting<Boolean> piston;
    private Setting<Boolean> cev;
    private Setting<Float> range;
    private Setting<Boolean> packetPlace;
    private BlockPos b_piston;
    private BlockPos b_cev;
    
    public NewBlocker() {
        super("Blocker", "Block gs attack lmao", Category.COMBAT, true, false, false);
        this.piston = (Setting<Boolean>)this.register(new Setting("Piston", (T)true));
        this.cev = (Setting<Boolean>)this.register(new Setting("CevBreaker", (T)true));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)6.1f, (T)1.0f, (T)10.0f));
        this.packetPlace = (Setting<Boolean>)this.register(new Setting("PacketPlace", (T)true));
        this.b_piston = null;
        this.b_cev = null;
    }
    
    @Override
    public int onTick() {
        if (Util.mc.player == null) {
            return 0;
        }
        try {
            final int ob = this.findMaterials(Blocks.OBSIDIAN);
            if (ob == -1) {
                return ob;
            }
            final BlockPos p_pos = new BlockPos(Util.mc.player.posX, Util.mc.player.posY, Util.mc.player.posZ);
            if (this.piston.getValue()) {
                final BlockPos[] offset = { new BlockPos(2, 1, 0), new BlockPos(-2, 1, 0), new BlockPos(0, 1, 2), new BlockPos(0, 1, -2) };
                for (int y = 0; y < 4; ++y) {
                    for (int i = 0; i < offset.length; ++i) {
                        final BlockPos pre_piston = p_pos.add((Vec3i)offset[i].add(0, y, 0));
                        if (this.getBlock(pre_piston).getBlock() == Blocks.PISTON || this.getBlock(pre_piston).getBlock() == Blocks.STICKY_PISTON) {
                            this.b_piston = pre_piston;
                        }
                    }
                }
                if (this.b_piston != null) {
                    if (this.getBlock(this.b_piston).getBlock() == Blocks.AIR) {
                        if (Util.mc.player.getDistance((double)this.b_piston.getX(), (double)this.b_piston.getY(), (double)this.b_piston.getZ()) > this.range.getValue()) {
                            return ob;
                        }
                        final int oldslot = Util.mc.player.inventory.currentItem;
                        Util.mc.player.inventory.currentItem = ob;
                        Util.mc.playerController.updateController();
                        BlockUtil.placeBlock(this.b_piston, EnumHand.MAIN_HAND, true, this.packetPlace.getValue(), false);
                        Util.mc.player.inventory.currentItem = oldslot;
                        Util.mc.playerController.updateController();
                    }
                    if (this.getBlock(this.b_piston).getBlock() == Blocks.OBSIDIAN || Util.mc.player.getDistance((double)this.b_piston.getX(), (double)this.b_piston.getY(), (double)this.b_piston.getZ()) > this.range.getValue()) {
                        this.b_piston = null;
                    }
                }
            }
            if (this.cev.getValue()) {
                final BlockPos p_player = new BlockPos(Util.mc.player.posX, Util.mc.player.posY, Util.mc.player.posZ);
                final Entity crystal = (Entity)Util.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityEnderCrystal).filter(c -> c.posY > p_player.getY()).filter(c -> Util.mc.player.getDistance(c.posX, Util.mc.player.posY, c.posZ) < 1.0).min(Comparator.comparing(c -> Util.mc.player.getDistanceSq(c.posX, c.posY, c.posZ))).orElse(null);
                if (this.getBlock(new BlockPos(p_player.getX(), p_player.getY() + 2, p_player.getZ())).getBlock() == Blocks.OBSIDIAN && crystal != null) {
                    this.b_cev = new BlockPos(crystal.posX, crystal.posY, crystal.posZ);
                }
                if (this.b_cev != null && this.getBlock(this.b_cev).getBlock() == Blocks.AIR) {
                    if (Util.mc.player.getDistance((double)this.b_cev.getX(), (double)this.b_cev.getY(), (double)this.b_cev.getZ()) > this.range.getValue()) {
                        return ob;
                    }
                    if (crystal == null && new BlockPos(Util.mc.player.posX, (double)this.b_cev.getY(), Util.mc.player.posZ).getDistance(this.b_cev.getX(), this.b_cev.getY(), this.b_cev.getZ()) < 1.0) {
                        final int oldslot2 = Util.mc.player.inventory.currentItem;
                        Util.mc.player.inventory.currentItem = ob;
                        Util.mc.playerController.updateController();
                        BlockUtil.placeBlock(this.b_cev.add(0, -1, 0), EnumHand.MAIN_HAND, true, false, false);
                        BlockUtil.placeBlock(this.b_cev, EnumHand.MAIN_HAND, true, false, false);
                        Util.mc.player.inventory.currentItem = oldslot2;
                        Util.mc.playerController.updateController();
                        this.b_cev = null;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.b_cev = null;
            this.b_piston = null;
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    private int findMaterials(final Block b) {
        for (int i = 0; i < 9; ++i) {
            if (Util.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)Util.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == b) {
                return i;
            }
        }
        return -1;
    }
    
    public BlockPos getPistonPos() {
        return this.b_piston;
    }
    
    private IBlockState getBlock(final BlockPos o) {
        return Util.mc.world.getBlockState(o);
    }
}
