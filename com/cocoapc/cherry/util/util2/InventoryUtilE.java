//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import com.cocoapc.cherry.util.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class InventoryUtilE implements Util
{
    public static int getBlockHotbar(final Block blockIn) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = InventoryUtilE.mc.player.inventory.getStackInSlot(i);
            final Block block;
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock && (block = ((ItemBlock)stack.getItem()).getBlock()) == blockIn) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getBlockHotbar(final Class clazz) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = InventoryUtilE.mc.player.inventory.getStackInSlot(i);
            final Block block;
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock && (block = ((ItemBlock)stack.getItem()).getBlock()).getClass() == clazz) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getItemHotbar(final Item input) {
        for (int i = 0; i < 9; ++i) {
            final Item item = InventoryUtilE.mc.player.inventory.getStackInSlot(i).getItem();
            if (Item.getIdFromItem(item) == Item.getIdFromItem(input)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void moveItemTo(final int item, final int empty) {
        InventoryUtilE.mc.playerController.windowClick(InventoryUtilE.mc.player.inventoryContainer.windowId, item, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtilE.mc.player);
        InventoryUtilE.mc.playerController.windowClick(InventoryUtilE.mc.player.inventoryContainer.windowId, empty, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtilE.mc.player);
        InventoryUtilE.mc.playerController.windowClick(InventoryUtilE.mc.player.inventoryContainer.windowId, item, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtilE.mc.player);
        InventoryUtilE.mc.playerController.updateController();
    }
    
    public static void dropItem(final int item) {
        InventoryUtilE.mc.playerController.windowClick(InventoryUtilE.mc.player.inventoryContainer.windowId, item, 0, ClickType.THROW, (EntityPlayer)InventoryUtilE.mc.player);
        InventoryUtilE.mc.playerController.updateController();
    }
}
