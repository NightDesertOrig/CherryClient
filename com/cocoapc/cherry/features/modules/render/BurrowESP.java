//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.*;
import com.cocoapc.cherry.event.events.*;
import java.util.function.*;
import java.util.*;
import net.minecraft.init.*;
import java.awt.*;

public class BurrowESP extends Module
{
    private final Setting<Boolean> outline;
    private final Setting<Integer> outlineGreen;
    private final Setting<Integer> boxGreen;
    private final Setting<Float> outlineWidth;
    private final Setting<Integer> boxAlpha;
    private final Setting<Integer> outlineAlpha;
    private final Map<EntityPlayer, BlockPos> burrowedPlayers;
    private final Setting<Integer> outlineBlue;
    private final Setting<Integer> boxBlue;
    private final Setting<Boolean> box;
    private final Setting<Integer> boxRed;
    private final Setting<Boolean> name;
    private final Setting<Boolean> cOutline;
    private final Setting<Integer> outlineRed;
    
    private boolean lambda$new$2(final Integer n) {
        return this.box.getValue();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.box.getValue();
    }
    
    private boolean lambda$new$4(final Float f) {
        return this.outline.getValue();
    }
    
    public void onEnable() {
        this.burrowedPlayers.clear();
    }
    
    public int onUpdate() {
        if (fullNullCheck()) {
            return 0;
        }
        this.burrowedPlayers.clear();
        this.getPlayers();
        return 0;
    }
    
    public void onRender3D() {
    }
    
    private void lambda$onRender3D$8(final Map.Entry entry) {
        this.renderBurrowedBlock(entry.getValue());
        if (this.name.getValue()) {
            RenderUtil.drawText(new BlockPos((Vec3i)entry.getValue()), ((EntityPlayer)entry.getKey()).getGameProfile().getName());
        }
    }
    
    private boolean lambda$new$7(final Integer n) {
        return this.cOutline.getValue();
    }
    
    private boolean lambda$new$6(final Integer n) {
        return this.cOutline.getValue();
    }
    
    private void getPlayers() {
        for (final EntityPlayer entityPlayer : BurrowESP.mc.world.playerEntities) {
            if (entityPlayer != BurrowESP.mc.player && !Cherry.friendManager.isFriend(entityPlayer.getName()) && EntityUtil.isLiving((Entity)entityPlayer)) {
                if (!this.isBurrowed(entityPlayer)) {
                    continue;
                }
                this.burrowedPlayers.put(entityPlayer, new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));
            }
        }
    }
    
    private boolean lambda$new$9(final Integer n) {
        return this.cOutline.getValue();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!this.burrowedPlayers.isEmpty()) {
            this.burrowedPlayers.entrySet().forEach(this::lambda$onRender3D$8);
        }
    }
    
    public BurrowESP() {
        super("BurrowESP", "Renders people burrowing", Module.Category.RENDER, true, false, false);
        this.name = (Setting<Boolean>)this.register(new Setting("Name", (T)false));
        this.box = new Setting<Boolean>("Box", true);
        this.boxRed = (Setting<Integer>)this.register(new Setting("BoxRed", (T)152, (T)0, (T)255, (Predicate<T>)this::lambda$new$0));
        this.boxGreen = (Setting<Integer>)this.register(new Setting("BoxGreen", (T)62, (T)0, (T)255, (Predicate<T>)this::lambda$new$1));
        this.boxBlue = (Setting<Integer>)this.register(new Setting("BoxBlue", (T)241, (T)0, (T)255, (Predicate<T>)this::lambda$new$2));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)119, (T)0, (T)255, (Predicate<T>)this::lambda$new$3));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)false));
        this.outlineWidth = (Setting<Float>)this.register(new Setting("OutlineWidth", (T)1.0f, (T)0.0f, (T)5.0f, (Predicate<T>)this::lambda$new$4));
        this.cOutline = (Setting<Boolean>)this.register(new Setting("CustomOutline", (T)false, (Predicate<T>)this::lambda$new$5));
        this.outlineRed = (Setting<Integer>)this.register(new Setting("OutlineRed", (T)255, (T)0, (T)255, (Predicate<T>)this::lambda$new$6));
        this.outlineGreen = (Setting<Integer>)this.register(new Setting("OutlineGreen", (T)255, (T)0, (T)255, (Predicate<T>)this::lambda$new$7));
        this.outlineBlue = (Setting<Integer>)this.register(new Setting("OutlineBlue", (T)255, (T)0, (T)255, (Predicate<T>)this::lambda$new$8));
        this.outlineAlpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", (T)255, (T)0, (T)255, (Predicate<T>)this::lambda$new$9));
        this.burrowedPlayers = new HashMap<EntityPlayer, BlockPos>();
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.box.getValue();
    }
    
    private boolean lambda$new$1(final Integer n) {
        return this.box.getValue();
    }
    
    private boolean lambda$new$5(final Boolean bl) {
        return this.outline.getValue();
    }
    
    private boolean isBurrowed(final EntityPlayer entityPlayer) {
        final BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
        return BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.IRON_TRAPDOOR || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ANVIL;
    }
    
    private boolean lambda$new$8(final Integer n) {
        return this.cOutline.getValue();
    }
    
    private void renderBurrowedBlock(final BlockPos blockPos) {
        RenderUtil.drawBoxESP(blockPos, new Color(this.boxRed.getValue(), this.boxGreen.getValue(), this.boxBlue.getValue(), this.boxAlpha.getValue()), true, new Color(this.outlineRed.getValue(), this.outlineGreen.getValue(), this.outlineBlue.getValue(), this.outlineAlpha.getValue()), this.outlineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
    }
}
