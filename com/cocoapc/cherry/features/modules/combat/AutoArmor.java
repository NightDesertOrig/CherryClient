//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.combat;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.util.*;
import java.util.concurrent.*;
import net.minecraft.client.gui.inventory.*;
import com.cocoapc.cherry.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import java.util.*;
import net.minecraft.entity.player.*;

public class AutoArmor extends Module
{
    private final Setting<Integer> delay;
    private final Setting<Boolean> curse;
    private final Setting<Boolean> mendingTakeOff;
    private final Setting<Integer> closestEnemy;
    private final Setting<Integer> repair;
    private final Setting<Integer> actions;
    private final Timer timer;
    private final Queue<InventoryUtil.Task> taskList;
    private final List<Integer> doneSlots;
    boolean flag;
    
    public AutoArmor() {
        super("AutoArmor", "Puts Armor on for you.", Category.COMBAT, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)50, (T)0, (T)500));
        this.curse = (Setting<Boolean>)this.register(new Setting("Vanishing", (T)false));
        this.mendingTakeOff = (Setting<Boolean>)this.register(new Setting("AutoMend", (T)false));
        this.closestEnemy = (Setting<Integer>)this.register(new Setting("Enemy", (T)8, (T)1, (T)20, v -> this.mendingTakeOff.getValue()));
        this.repair = (Setting<Integer>)this.register(new Setting("Repair%", (T)80, (T)1, (T)100, v -> this.mendingTakeOff.getValue()));
        this.actions = (Setting<Integer>)this.register(new Setting("Packets", (T)3, (T)1, (T)12));
        this.timer = new Timer();
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.doneSlots = new ArrayList<Integer>();
    }
    
    @Override
    public void onLogin() {
        this.timer.reset();
    }
    
    @Override
    public int onDisable() {
        this.taskList.clear();
        this.doneSlots.clear();
        this.flag = false;
        return 0;
    }
    
    @Override
    public void onLogout() {
        this.taskList.clear();
        this.doneSlots.clear();
    }
    
    @Override
    public void onRender3D() {
    }
    
    @Override
    public int onTick() {
        if (fullNullCheck() || (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof GuiInventory))) {
            return 0;
        }
        if (this.taskList.isEmpty()) {
            int slot = 0;
            if (this.mendingTakeOff.getValue() && InventoryUtil.holdingItem(ItemExpBottle.class) && AutoArmor.mc.gameSettings.keyBindUseItem.isKeyDown() && AutoArmor.mc.world.playerEntities.stream().noneMatch(e -> e != AutoArmor.mc.player && !Cherry.friendManager.isFriend(((EntityPlayer)e).getName()) && AutoArmor.mc.player.getDistance((Entity)e) <= this.closestEnemy.getValue()) && !this.flag) {
                int takeOff = 0;
                for (final Map.Entry<Integer, ItemStack> armorSlot : this.getArmor().entrySet()) {
                    final ItemStack stack = armorSlot.getValue();
                    final float percent = this.repair.getValue() / 100.0f;
                    final int dam = Math.round(stack.getMaxDamage() * percent);
                    final int goods;
                    if (dam >= (goods = stack.getMaxDamage() - stack.getItemDamage())) {
                        continue;
                    }
                    ++takeOff;
                }
                if (takeOff == 4) {
                    this.flag = true;
                }
                if (!this.flag) {
                    final ItemStack itemStack1 = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
                    if (!itemStack1.isEmpty) {
                        final float percent2 = this.repair.getValue() / 100.0f;
                        final int dam2 = Math.round(itemStack1.getMaxDamage() * percent2);
                        final int goods2;
                        if (dam2 < (goods2 = itemStack1.getMaxDamage() - itemStack1.getItemDamage())) {
                            this.takeOffSlot(5);
                        }
                    }
                    final ItemStack itemStack2 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack();
                    if (!itemStack2.isEmpty) {
                        final float percent = this.repair.getValue() / 100.0f;
                        final int dam3 = Math.round(itemStack2.getMaxDamage() * percent);
                        final int goods3;
                        if (dam3 < (goods3 = itemStack2.getMaxDamage() - itemStack2.getItemDamage())) {
                            this.takeOffSlot(6);
                        }
                    }
                    final ItemStack itemStack3 = AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack();
                    if (!itemStack3.isEmpty) {
                        final float percent = this.repair.getValue() / 100.0f;
                        final int dam = Math.round(itemStack3.getMaxDamage() * percent);
                        final int goods;
                        if (dam < (goods = itemStack3.getMaxDamage() - itemStack3.getItemDamage())) {
                            this.takeOffSlot(7);
                        }
                    }
                    final ItemStack itemStack4 = AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack();
                    if (!itemStack4.isEmpty) {
                        final float percent3 = this.repair.getValue() / 100.0f;
                        final int dam4 = Math.round(itemStack4.getMaxDamage() * percent3);
                        final int goods4;
                        if (dam4 < (goods4 = itemStack4.getMaxDamage() - itemStack4.getItemDamage())) {
                            this.takeOffSlot(8);
                        }
                    }
                }
                return slot;
            }
            this.flag = false;
            final ItemStack helm = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
            final int slot2;
            if (helm.getItem() == Items.AIR && (slot2 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.HEAD, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(5, slot2);
            }
            final ItemStack chest;
            final int slot3;
            if ((chest = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack()).getItem() == Items.AIR && (slot3 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.CHEST, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(6, slot3);
            }
            final ItemStack legging;
            final int slot4;
            if ((legging = AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack()).getItem() == Items.AIR && (slot4 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.LEGS, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(7, slot4);
            }
            final ItemStack feet;
            if ((feet = AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack()).getItem() == Items.AIR && (slot = InventoryUtil.findArmorSlot(EntityEquipmentSlot.FEET, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(8, slot);
            }
        }
        if (this.timer.passedMs((int)(this.delay.getValue() * Cherry.serverManager.getTpsFactor()))) {
            if (!this.taskList.isEmpty()) {
                for (int i = 0; i < this.actions.getValue(); ++i) {
                    final InventoryUtil.Task task = this.taskList.poll();
                    if (task != null) {
                        task.run();
                    }
                }
            }
            this.timer.reset();
        }
        return 0;
    }
    
    private void takeOffSlot(final int slot) {
        if (this.taskList.isEmpty()) {
            int target = -1;
            for (final int i : InventoryUtil.findEmptySlots(true)) {
                if (this.doneSlots.contains(target)) {
                    continue;
                }
                target = i;
                this.doneSlots.add(i);
            }
            if (target != -1) {
                this.taskList.add(new InventoryUtil.Task(slot));
                this.taskList.add(new InventoryUtil.Task(target));
                this.taskList.add(new InventoryUtil.Task());
            }
        }
    }
    
    private void getSlotOn(final int slot, final int target) {
        if (this.taskList.isEmpty()) {
            this.doneSlots.remove((Object)target);
            this.taskList.add(new InventoryUtil.Task(target));
            this.taskList.add(new InventoryUtil.Task(slot));
            this.taskList.add(new InventoryUtil.Task());
        }
    }
    
    private Map<Integer, ItemStack> getArmor() {
        return this.getInventorySlots(5, 8);
    }
    
    private Map<Integer, ItemStack> getInventorySlots(int current, final int last) {
        final HashMap<Integer, ItemStack> fullInventorySlots = new HashMap<Integer, ItemStack>();
        while (current <= last) {
            fullInventorySlots.put(current, (ItemStack)AutoArmor.mc.player.inventoryContainer.getInventory().get(current));
            ++current;
        }
        return fullInventorySlots;
    }
}