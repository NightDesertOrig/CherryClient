//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.entity.player.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

public class CIV extends Module
{
    private List<BlockPos> placeList;
    private boolean placing;
    private boolean placedCrystal;
    private boolean breaking;
    private boolean broke;
    private EntityPlayer _target;
    private BlockPos b_crystal;
    private BlockPos breakPos;
    private int attempts;
    private Setting<type> targetType;
    private Setting<mode> breakMode;
    private Setting<Boolean> rotate;
    private Setting<Integer> startDelay;
    private Setting<Integer> breakDelay;
    private Setting<Integer> crystalDelay;
    private Setting<Integer> hitDelay;
    private Setting<Integer> nosleep;
    private int timer;
    
    public CIV() {
        super("CIV BREAK", "Attack Ceil", Category.COMBAT, true, false, false);
        this.placeList = new ArrayList<BlockPos>();
        this.placing = false;
        this.placedCrystal = false;
        this.breaking = false;
        this.broke = false;
        this._target = null;
        this.b_crystal = null;
        this.breakPos = null;
        this.attempts = 0;
        this.targetType = (Setting<type>)this.register(new Setting("Target", (T)type.NEAREST));
        this.breakMode = (Setting<mode>)this.register(new Setting("Break Mode", (T)mode.Vanilla));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)true));
        this.startDelay = (Setting<Integer>)this.register(new Setting("Start Delay", (T)1, (T)0, (T)10));
        this.breakDelay = (Setting<Integer>)this.register(new Setting("Break Delay", (T)1, (T)0, (T)10));
        this.crystalDelay = (Setting<Integer>)this.register(new Setting("Crystal Delay", (T)1, (T)0, (T)10));
        this.hitDelay = (Setting<Integer>)this.register(new Setting("Hit Delay", (T)3, (T)0, (T)10));
        this.nosleep = (Setting<Integer>)this.register(new Setting("Block Delay", (T)3, (T)0, (T)10));
        this.timer = 0;
    }
    
    @Override
    public void onEnable() {
        this.init();
    }
    
    private void init() {
        this.placeList = new ArrayList<BlockPos>();
        this._target = null;
        this.b_crystal = null;
        this.placedCrystal = false;
        this.placing = false;
        this.breaking = false;
        this.broke = false;
        this.timer = 0;
        this.attempts = 0;
    }
    
    @Override
    public int onTick() {
        final int pix = this.findItem(Items.DIAMOND_PICKAXE);
        final int crystal = this.findItem(Items.END_CRYSTAL);
        final int obby = this.findMaterials(Blocks.OBSIDIAN);
        if (pix == -1 || crystal == -1 || obby == -1) {
            this.disable();
            return pix;
        }
        if (this._target == null) {
            if (this.targetType.getValue() == type.NEAREST) {
                this._target = (EntityPlayer)Util.mc.world.playerEntities.stream().filter(p -> p.getEntityId() != Util.mc.player.getEntityId()).min(Comparator.comparing(p -> p.getDistance((Entity)Util.mc.player))).orElse(null);
            }
            if (this._target == null) {
                this.disable();
                return pix;
            }
        }
        if (this.placeList.size() == 0 && !this.placing) {
            this.searchSpace();
            if (this.placeList.size() == 0) {
                this.disable();
                return pix;
            }
        }
        if (!this.placedCrystal) {
            if (this.timer < this.startDelay.getValue()) {
                ++this.timer;
                return pix;
            }
            this.timer = 0;
            this.doPlace(obby, crystal);
        }
        else if (!this.breaking) {
            if (this.timer < this.breakDelay.getValue()) {
                ++this.timer;
                return pix;
            }
            this.timer = 0;
            if (this.breakMode.getValue() == mode.Vanilla) {
                Util.mc.player.inventory.currentItem = pix;
                Util.mc.playerController.updateController();
                Util.mc.player.swingArm(EnumHand.MAIN_HAND);
                Util.mc.playerController.onPlayerDamageBlock(this.breakPos, EnumFacing.DOWN);
            }
            else {
                Util.mc.player.swingArm(EnumHand.MAIN_HAND);
                Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.breakPos, EnumFacing.DOWN));
                Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakPos, EnumFacing.DOWN));
            }
            this.breaking = true;
        }
        else if (this.breaking && !this.broke) {
            if (this.getBlock(this.breakPos) == Blocks.AIR) {
                this.broke = true;
            }
        }
        else if (this.broke) {
            if (this.timer < this.crystalDelay.getValue()) {
                ++this.timer;
                return pix;
            }
            this.timer = 0;
            final Entity bcrystal = (Entity)Util.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityEnderCrystal).min(Comparator.comparing(c -> c.getDistance((Entity)this._target))).orElse(null);
            if (bcrystal == null) {
                if (this.attempts < this.hitDelay.getValue()) {
                    ++this.attempts;
                    return pix;
                }
                if (this.attempts < this.nosleep.getValue()) {
                    ++this.attempts;
                    return pix;
                }
                this.placedCrystal = false;
                this.placeList.add(this.breakPos);
                this.breaking = false;
                this.broke = false;
                this.attempts = 0;
            }
            else {
                Util.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(bcrystal));
                this.placedCrystal = false;
                this.placeList.add(this.breakPos);
                this.breaking = false;
                this.broke = false;
                this.attempts = 0;
            }
        }
        return pix;
    }
    
    @Override
    public void onRender3D() {
    }
    
    private void doPlace(final int obby, final int crystal) {
        this.placing = true;
        if (this.placeList.size() != 0) {
            final int oldslot = Util.mc.player.inventory.currentItem;
            Util.mc.player.inventory.currentItem = obby;
            Util.mc.playerController.updateController();
            BlockUtil.placeBlock(this.placeList.get(0), EnumHand.MAIN_HAND, this.rotate.getValue(), false, false);
            this.placeList.remove(0);
            Util.mc.player.inventory.currentItem = oldslot;
        }
        else if (!this.placedCrystal) {
            final int oldslot = Util.mc.player.inventory.currentItem;
            if (crystal != 999) {
                Util.mc.player.inventory.currentItem = crystal;
            }
            Util.mc.playerController.updateController();
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.b_crystal, EnumFacing.UP, (Util.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            Util.mc.player.inventory.currentItem = oldslot;
            this.placedCrystal = true;
        }
    }
    
    private void searchSpace() {
        final BlockPos ppos = Util.mc.player.getPosition();
        final BlockPos tpos = new BlockPos(this._target.posX, this._target.posY, this._target.posZ);
        this.placeList = new ArrayList<BlockPos>();
        final BlockPos[] offset = { new BlockPos(1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 0), new BlockPos(0, 0, 0) };
        if (this.getBlock(new BlockPos(tpos.getX(), tpos.getY() + 1, tpos.getZ())) != Blocks.AIR || this.getBlock(new BlockPos(tpos.getX(), tpos.getY() + 2, tpos.getZ())) != Blocks.AIR) {
            return;
        }
        final List<BlockPos> posList = new ArrayList<BlockPos>();
        for (int i = 0; i < offset.length; ++i) {
            final BlockPos offsetPos = tpos.add((Vec3i)offset[i]);
            final Block block = this.getBlock(offsetPos);
            if (block != Blocks.AIR && !(block instanceof BlockLiquid)) {
                posList.add(offsetPos);
            }
        }
        final BlockPos base = posList.stream().max(Comparator.comparing(b -> this._target.getDistance((double)b.getX(), (double)b.getY(), (double)b.getZ()))).orElse(null);
        if (base == null) {
            return;
        }
        this.placeList.add(base);
        this.placeList.add(base.add(0, 0, 0));
        this.placeList.add(base.add(0, 0, 0));
        this.placeList.add(tpos.add(1, 1, 0));
        this.breakPos = tpos.add(1, 1, 0);
        this.b_crystal = tpos.add(1, 1, 0);
    }
    
    private int findMaterials(final Block b) {
        for (int i = 0; i < 9; ++i) {
            if (Util.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)Util.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == b) {
                return i;
            }
        }
        return -1;
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
    
    private Block getBlock(final BlockPos b) {
        return Util.mc.world.getBlockState(b).getBlock();
    }
    
    public enum type
    {
        NEAREST, 
        LOOKING;
    }
    
    public enum mode
    {
        Vanilla, 
        Packet;
    }
}
