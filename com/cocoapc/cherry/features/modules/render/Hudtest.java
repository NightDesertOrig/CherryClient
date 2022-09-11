//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.item.*;
import net.minecraftforge.client.event.*;
import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.util.util2.*;
import net.minecraft.client.entity.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.*;
import com.cocoapc.cherry.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;

public class Hudtest extends Module
{
    public Setting<Integer> LL;
    public Setting<Integer> ls;
    public Setting<Integer> Fontsize;
    public Setting<Integer> Hp;
    
    public Hudtest() {
        super("TargetHud", "description", Module.Category.RENDER, true, false, false);
        this.LL = (Setting<Integer>)this.register(new Setting("X", (T)0, (T)0, (T)2000));
        this.ls = (Setting<Integer>)this.register(new Setting("Y", (T)0, (T)0, (T)2000));
        this.Fontsize = (Setting<Integer>)this.register(new Setting("Fontsize", (T)30, (T)0, (T)100));
        this.Hp = (Setting<Integer>)this.register(new Setting("Font", (T)30, (T)0, (T)100));
    }
    
    public void renderItem(final ItemStack item, final int x, final int y) {
        if (item == null) {
            return;
        }
        if (!item.isEmpty()) {
            RD.renderItem(item, (float)(this.LL.getValue() + x), (float)(this.ls.getValue() + y - 4));
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (Feature.nullCheck()) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            final EntityPlayer target = PlayerUtil.getNearestPlayer(30.0);
            if (target == null) {
                return;
            }
            RoundedUtils.drawRoundedRect(this.LL.getValue(), this.ls.getValue(), (float)(this.LL.getValue() + 200), (float)(this.ls.getValue() + 80), 10.0f, ColorUtil.toRGBA(35, 35, 35, 255));
            RenderUtil.drawHead(((AbstractClientPlayer)target).getLocationSkin(), this.LL.getValue() + 30, this.ls.getValue() + 65, 60, 60);
            RD.renderEntity((EntityLivingBase)target, (float)(this.LL.getValue() + 30), (float)(this.ls.getValue() + 65), 30.0f);
            Cherry.textManager.drawStringBig1(target.getName(), (float)(this.LL.getValue() + 60), (float)(this.ls.getValue() + 5), ColorUtil.toRGBA(255, 255, 255), false);
            Cherry.textManager.drawStringBig2(target.getHealth() + "HP", (float)(this.LL.getValue() + 60), (float)(this.ls.getValue() + 25), ColorUtil.toRGBA(234, 145, 255), false);
            final ItemStack slot4 = this.getArmorInv(3);
            final ItemStack slot5 = this.getArmorInv(2);
            final ItemStack slot6 = this.getArmorInv(1);
            final ItemStack slot7 = this.getArmorInv(0);
            final ItemStack mainHand = target.getHeldItemMainhand();
            final ItemStack offHand = target.getHeldItemOffhand();
            this.renderItem(slot4, 60, 60);
            this.renderItem(slot5, 80, 60);
            this.renderItem(slot6, 100, 60);
            this.renderItem(slot7, 120, 60);
            this.renderItem(mainHand, 140, 60);
            this.renderItem(offHand, 160, 60);
        }
    }
    
    public ItemStack getArmorInv(final int slot) {
        final EntityPlayer target = PlayerUtil.getNearestPlayer(30.0);
        final InventoryPlayer inv = target.inventory;
        return inv.armorItemInSlot(slot);
    }
    
    public void onRender3D() {
    }
}
