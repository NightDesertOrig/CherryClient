//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.block.*;
import com.cocoapc.cherry.features.modules.player.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.entity.*;
import java.util.function.*;

public class CevBreaker extends Module
{
    public static CevBreaker INSTANCE;
    int sleep;
    private Setting<Integer> crystalDelay;
    boolean flag;
    Entity currentEntity;
    public final Setting<Float> range;
    public final Setting<mode> modes;
    int progress;
    boolean breakFlag;
    int civCounter;
    
    private int findMaterials(final Block block) {
        for (int i = 0; i < 9; ++i) {
            if (CevBreaker.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)CevBreaker.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public int onTick() {
        if (CevBreaker.INSTANCE.modes.getValue() == mode.ON) {
            MoonBreak.getInstance().disable();
        }
        final int n = this.findItem(Items.DIAMOND_PICKAXE);
        final int n2 = this.findItem(Items.END_CRYSTAL);
        final int n3 = this.findMaterials(Blocks.OBSIDIAN);
        final BlockPos[] arrblockPos = { new BlockPos(0, 0, 1), new BlockPos(0, 1, 1), new BlockPos(0, 2, 1), new BlockPos(0, 2, 0) };
        final int n4 = InventoryUtils.getSlot();
        if (n == -1 || n2 == -1 || n3 == -1) {
            this.disable();
            return n;
        }
        if (this.currentEntity == null || this.currentEntity.getDistance((Entity)CevBreaker.mc.player) > (double)this.range.getValue()) {
            this.findTarget();
        }
        if (this.currentEntity != null) {
            final Entity entity = this.currentEntity;
            if (entity instanceof EntityPlayer) {
                if (n2 == -1 || n2 != -1 || !((ItemStack)CevBreaker.mc.player.inventory.offHandInventory.get(0)).getItem().getClass().equals(Item.getItemById(426).getClass())) {}
                if (this.sleep > 0) {
                    --this.sleep;
                }
                else {
                    entity.move(MoverType.SELF, 0.0, -2.0, 0.0);
                    switch (this.progress) {
                        case 0: {
                            final BlockPos blockPos = new BlockPos(entity);
                            for (final BlockPos blockPos2 : arrblockPos) {
                                if (Arrays.asList(arrblockPos).indexOf(blockPos2) != -1 && this.civCounter < 1) {
                                    this.flag = true;
                                    InventoryUtils.setSlot(n3);
                                }
                                else {
                                    InventoryUtils.setSlot(n3);
                                }
                                final BlockUtils blockUtils = BlockUtils.isPlaceable(blockPos.add((Vec3i)blockPos2), 0.0, true);
                                if (blockUtils != null) {
                                    blockUtils.doPlace(true);
                                }
                            }
                            InventoryUtils.setSlot(n2);
                            CrystalUtils.placeCrystal(new BlockPos(entity.posX, entity.posY + 3.0, entity.posZ));
                            ++this.progress;
                            break;
                        }
                        case 1: {
                            InventoryUtils.setSlot(n);
                            CevBreaker.mc.playerController.onPlayerDamageBlock(new BlockPos(entity).add(0, 2, 0), EnumFacing.UP);
                            Util.mc.getConnection().sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(entity).add(0, 2, 0), EnumFacing.UP));
                            if (CevBreaker.mc.world.isAirBlock(new BlockPos(entity).add(0, 2, 0))) {
                                for (final Entity entity2 : CevBreaker.mc.world.loadedEntityList) {
                                    if (entity.getDistance(entity2) <= (double)this.range.getValue()) {
                                        if (!(entity2 instanceof EntityEnderCrystal)) {
                                            continue;
                                        }
                                        CevBreaker.mc.playerController.attackEntity((EntityPlayer)CevBreaker.mc.player, entity2);
                                    }
                                }
                                this.breakFlag = true;
                            }
                            if (this.civCounter < 1) {
                                CevBreaker.mc.playerController.onPlayerDamageBlock(new BlockPos(entity).add(0, 2, 0), EnumFacing.UP);
                                this.sleep += 30;
                            }
                            ++this.progress;
                            break;
                        }
                        case 2: {
                            int n5 = 0;
                            for (final Entity entity3 : CevBreaker.mc.world.loadedEntityList) {
                                if (entity.getDistance(entity3) <= (double)this.range.getValue()) {
                                    if (!(entity3 instanceof EntityEnderCrystal)) {
                                        continue;
                                    }
                                    CevBreaker.mc.playerController.attackEntity((EntityPlayer)CevBreaker.mc.player, entity3);
                                    ++n5;
                                }
                            }
                            if (n5 != 0 && !this.flag) {
                                break;
                            }
                            ++this.progress;
                            break;
                        }
                        case 3: {
                            BlockUtils.doPlace(BlockUtils.isPlaceable(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), 0.0, true), true);
                            InventoryUtils.setSlot(n3);
                            this.progress = 0;
                            ++this.civCounter;
                            break;
                        }
                    }
                }
                InventoryUtils.setSlot(n4);
                return n;
            }
            InventoryUtils.setSlot(n4);
        }
        return n;
    }
    
    @Override
    public void onRender3D() {
    }
    
    private boolean lambda$findTarget$0(final Entity entity) {
        return entity != CevBreaker.mc.player && entity instanceof EntityLivingBase && entity.getDistance((Entity)CevBreaker.mc.player) < (double)this.range.getValue();
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
    
    public CevBreaker() {
        super("FastCevBreaker", "Cev.", Category.COMBAT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)6.0f, (T)0.1f, (T)10.0f));
        this.modes = (Setting<mode>)this.register(new Setting("Mode", (T)mode.OFF));
        this.progress = 0;
        CevBreaker.INSTANCE = this;
    }
    
    public void findTarget() {
        this.currentEntity = (Entity)CevBreaker.mc.world.loadedEntityList.stream().filter(this::lambda$findTarget$0).findFirst().orElse(null);
    }
    
    @Override
    public void onEnable() {
        this.findTarget();
        this.progress = 0;
        this.breakFlag = false;
        this.flag = false;
        this.civCounter = 0;
        this.sleep = 0;
        super.onEnable();
    }
    
    @Override
    public int onDisable() {
        if (CevBreaker.INSTANCE.modes.getValue() == mode.ON) {
            MoonBreak.getInstance().enable();
        }
        super.onDisable();
        return 0;
    }
    
    public enum mode
    {
        OFF, 
        ON;
    }
}
