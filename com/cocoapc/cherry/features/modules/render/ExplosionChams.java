//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.server.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.event.events.*;
import java.awt.*;
import com.cocoapc.cherry.util.util2.*;

public class ExplosionChams extends Module
{
    public final Setting<Integer> red;
    public final Setting<Integer> green;
    public final Setting<Integer> blue;
    public final Setting<Integer> alpha;
    public final Setting<Float> increase;
    public final Setting<Integer> riseSpeed;
    public final Setting<Boolean> rainbow;
    public Map<EntityEnderCrystal, BlockPos> explodedCrystals;
    public BlockPos crystalPos;
    public int aliveTicks;
    public double speed;
    public Timer timer;
    
    public ExplosionChams() {
        super("CrystalChams", "Crystal Chams", Module.Category.RENDER, true, false, false);
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)30, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)167, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)150, (T)0, (T)255));
        this.increase = (Setting<Float>)this.register(new Setting("Increase Size", (T)0.5f, (T)0.1f, (T)5.0f));
        this.riseSpeed = (Setting<Integer>)this.register(new Setting("Rise Time", (T)5, (T)1, (T)50));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Sync", (T)false));
        this.explodedCrystals = new HashMap<EntityEnderCrystal, BlockPos>();
        this.crystalPos = null;
        this.aliveTicks = 0;
        this.speed = 0.0;
        this.timer = new Timer();
    }
    
    public void onEnable() {
        this.explodedCrystals.clear();
    }
    
    public int onUpdate() {
        if (fullNullCheck()) {
            return 0;
        }
        ++this.aliveTicks;
        if (this.timer.passedMss(5L)) {
            this.speed += 0.5;
            this.timer.reset();
        }
        if (this.speed > this.riseSpeed.getValue()) {
            this.speed = 0.0;
            this.explodedCrystals.clear();
        }
        return 0;
    }
    
    public void onRender3D() {
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        try {
            for (final Entity crystal : ExplosionChams.mc.world.loadedEntityList) {
                if (crystal instanceof EntityEnderCrystal) {
                    if (!(event.getPacket() instanceof SPacketExplosion)) {
                        continue;
                    }
                    this.crystalPos = new BlockPos(crystal.posX, crystal.posY, crystal.posZ);
                    this.explodedCrystals.put((EntityEnderCrystal)crystal, this.crystalPos);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    public void onRender3D(final Render3DEvent event) {
        if (!this.explodedCrystals.isEmpty()) {
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.7f, (float)this.crystalPos.getZ(), 0.6f + this.increase.getValue(), (Colour)new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue() - 60));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.6f, (float)this.crystalPos.getZ(), 0.5f + this.increase.getValue(), (Colour)new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue() - 50));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.5f, (float)this.crystalPos.getZ(), 0.4f + this.increase.getValue(), (Colour)new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue() - 40));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.4f, (float)this.crystalPos.getZ(), 0.3f + this.increase.getValue(), (Colour)new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue() - 30));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.3f, (float)this.crystalPos.getZ(), 0.2f + this.increase.getValue(), (Colour)new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue() - 20));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.2f, (float)this.crystalPos.getZ(), 0.1f + this.increase.getValue(), (Colour)new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue() - 10));
            RenderUtil.drawCircle((float)this.crystalPos.getX(), this.crystalPos.getY() + (float)this.speed / 3.0f + 0.1f, (float)this.crystalPos.getZ(), 0.0f + this.increase.getValue(), (Colour)new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()));
        }
    }
}
