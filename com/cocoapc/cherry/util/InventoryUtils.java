//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util;

import net.minecraft.client.*;
import net.minecraft.item.*;
import java.util.*;

public class InventoryUtils
{
    protected static Minecraft mc;
    
    public static int pickItem(final int n) {
        final ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem(((ItemStack)InventoryUtils.mc.player.inventory.mainInventory.get(i)).getItem()) == n) {
                arrayList.add(InventoryUtils.mc.player.inventory.mainInventory.get(i));
            }
        }
        if (arrayList.size() >= 1) {
            return InventoryUtils.mc.player.inventory.mainInventory.indexOf(arrayList.get(0));
        }
        return -1;
    }
    
    public static int getSlot() {
        return InventoryUtils.mc.player.inventory.currentItem;
    }
    
    public static int getPlaceableItem() {
        final ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (((ItemStack)InventoryUtils.mc.player.inventory.mainInventory.get(i)).getItem() instanceof ItemBlock) {
                arrayList.add(InventoryUtils.mc.player.inventory.mainInventory.get(i));
            }
        }
        arrayList.sort(InventoryUtils::lambda$getPlaceableItem$0);
        if (arrayList.size() >= 1) {
            return InventoryUtils.mc.player.inventory.mainInventory.indexOf(arrayList.get(0));
        }
        return -1;
    }
    
    private static int lambda$getPlaceableItem$0(final Object o, final Object o1) {
        return 0;
    }
    
    private static int lambda$getPlaceableItem$0(final ItemStack itemStack, final ItemStack itemStack2) {
        return itemStack2.getCount() - itemStack.getCount();
    }
    
    public static void setSlot(final int n) {
        if (n > 8 || n < 0) {
            return;
        }
        InventoryUtils.mc.player.inventory.currentItem = n;
    }
    
    static {
        InventoryUtils.mc = Minecraft.getMinecraft();
    }
}
