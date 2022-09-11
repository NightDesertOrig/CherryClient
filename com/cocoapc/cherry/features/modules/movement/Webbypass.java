//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.block.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.*;
import org.lwjgl.input.*;
import net.minecraft.client.entity.*;

public class Webbypass extends Module
{
    public Setting<Boolean> disableBB;
    public Setting<Float> bbOffset;
    public Setting<Boolean> onGround;
    public Setting<Float> motionY;
    public Setting<Float> motionX;
    
    public Webbypass() {
        super("Webbypass", "aw", Module.Category.MOVEMENT, true, false, false);
        this.disableBB = (Setting<Boolean>)this.register(new Setting("AddBB", (T)true));
        this.bbOffset = (Setting<Float>)this.register(new Setting("BBOffset", (T)0.4f, (T)(-2.0f), (T)2.0f));
        this.onGround = (Setting<Boolean>)this.register(new Setting("On Ground", (T)true));
        this.motionY = (Setting<Float>)this.register(new Setting("Set MotionY", (T)0.0f, (T)0.0f, (T)20.0f));
        this.motionX = (Setting<Float>)this.register(new Setting("Set MotionX", (T)0.8f, (T)(-1.0f), (T)5.0f));
    }
    
    @SubscribeEvent
    public void bbEvent(final BlockCollisionBoundingBoxEvent event) {
        if (nullCheck()) {
            return;
        }
        if (Webbypass.mc.world.getBlockState(event.getPos()).getBlock() instanceof BlockWeb && this.disableBB.getValue()) {
            event.setBoundingBox(Block.FULL_BLOCK_AABB.contract(0.0, (double)this.bbOffset.getValue(), 0.0));
        }
    }
    
    public int onUpdate() {
        if ((Webbypass.mc.player.isInWeb && !Cherry.moduleManager.isModuleEnabled("Step")) || (Webbypass.mc.player.isInWeb && !Cherry.moduleManager.isModuleEnabled("StepTwo"))) {
            if (Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindSneak.keyCode)) {
                Webbypass.mc.player.isInWeb = true;
                final EntityPlayerSP player = Webbypass.mc.player;
                player.motionY *= this.motionY.getValue();
            }
            else if (this.onGround.getValue()) {
                Webbypass.mc.player.onGround = false;
            }
            if (Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindForward.keyCode) || Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindBack.keyCode) || Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindLeft.keyCode) || Keyboard.isKeyDown(Webbypass.mc.gameSettings.keyBindRight.keyCode)) {
                Webbypass.mc.player.isInWeb = false;
                final EntityPlayerSP player2 = Webbypass.mc.player;
                player2.motionX *= this.motionX.getValue();
                final EntityPlayerSP player3 = Webbypass.mc.player;
                player3.motionZ *= this.motionX.getValue();
            }
        }
        return 0;
    }
    
    public void onRender3D() {
    }
}
