//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.util.util2.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.entity.*;
import com.cocoapc.cherry.features.modules.combat.*;
import com.cocoapc.cherry.*;
import net.minecraft.item.*;
import org.lwjgl.input.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.util.*;
import java.util.*;

public class BowSpam extends Module
{
    private final TimerUtil timer;
    public Setting<Mode> mode;
    public Setting<Boolean> bowbomb;
    public Setting<Boolean> allowOffhand;
    public Setting<Integer> ticks;
    public Setting<Integer> delay;
    public Setting<Boolean> tpsSync;
    public Setting<Boolean> autoSwitch;
    public Setting<Boolean> onlyWhenSave;
    public Setting<Target> targetMode;
    public Setting<Float> range;
    public Setting<Float> health;
    public Setting<Float> ownHealth;
    private boolean offhand;
    private boolean switched;
    private int lastHotbarSlot;
    
    public BowSpam() {
        super("BowSpam", "Spams your bow.", Category.COMBAT, true, false, false);
        this.timer = new TimerUtil();
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.FAST));
        this.bowbomb = (Setting<Boolean>)this.register(new Setting("BowBomb", (T)false, v -> this.mode.getValue() != Mode.BOWBOMB));
        this.allowOffhand = (Setting<Boolean>)this.register(new Setting("Offhand", (T)true, v -> this.mode.getValue() != Mode.AUTORELEASE));
        this.ticks = (Setting<Integer>)this.register(new Setting("Ticks", (T)3, (T)0, (T)20, v -> this.mode.getValue() == Mode.BOWBOMB || this.mode.getValue() == Mode.FAST, "Speed"));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)50, (T)0, (T)500, v -> this.mode.getValue() == Mode.AUTORELEASE, "Speed"));
        this.tpsSync = (Setting<Boolean>)this.register(new Setting("TpsSync", (T)true));
        this.autoSwitch = (Setting<Boolean>)this.register(new Setting("AutoSwitch", (T)false));
        this.onlyWhenSave = (Setting<Boolean>)this.register(new Setting("OnlyWhenSave", (T)true, v -> this.autoSwitch.getValue()));
        this.targetMode = (Setting<Target>)this.register(new Setting("Target", (T)Target.LOWEST, v -> this.autoSwitch.getValue()));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)3.0f, (T)0.0f, (T)6.0f, v -> this.autoSwitch.getValue(), "Range of the target"));
        this.health = (Setting<Float>)this.register(new Setting("Lethal", (T)6.0f, (T)0.1f, (T)36.0f, v -> this.autoSwitch.getValue(), "When should it switch?"));
        this.ownHealth = (Setting<Float>)this.register(new Setting("OwnHealth", (T)20.0f, (T)0.1f, (T)36.0f, v -> this.autoSwitch.getValue(), "Own Health."));
        this.lastHotbarSlot = -1;
    }
    
    @Override
    public void onEnable() {
        this.lastHotbarSlot = BowSpam.mc.player.inventory.currentItem;
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent event) {
        if (event.getStage() != 0) {
            return;
        }
        if (this.autoSwitch.getValue() && InventoryUtil.findHotbarBlock(ItemBow.class) != -1 && this.ownHealth.getValue() <= EntityUtil.getHealth((Entity)BowSpam.mc.player) && (!this.onlyWhenSave.getValue() || EntityUtil.isSafe((Entity)BowSpam.mc.player))) {
            final EntityPlayer target = this.getTarget();
            if (target != null && (!Cherry.moduleManager.getModuleByClass(AutoCrystal.class).isOn() || !InventoryUtil.holdingItem(ItemEndCrystal.class))) {
                final Vec3d pos = target.getPositionVector();
                final double xPos = pos.x;
                double yPos = pos.y;
                final double zPos = pos.z;
                if (BowSpam.mc.player.canEntityBeSeen((Entity)target)) {
                    yPos += target.eyeHeight;
                }
                else {
                    if (!EntityUtil.canEntityFeetBeSeen((Entity)target)) {
                        return;
                    }
                    yPos += 0.1;
                }
                if (!(BowSpam.mc.player.getHeldItemMainhand().getItem() instanceof ItemBow)) {
                    this.lastHotbarSlot = BowSpam.mc.player.inventory.currentItem;
                    InventoryUtil.switchToHotbarSlot(ItemBow.class, false);
                    BowSpam.mc.gameSettings.keyBindUseItem.pressed = true;
                    this.switched = true;
                }
                Cherry.rotationManager.lookAtVec3d(xPos, yPos, zPos);
                if (BowSpam.mc.player.getHeldItemMainhand().getItem() instanceof ItemBow) {
                    this.switched = true;
                }
            }
        }
        else if (event.getStage() == 0 && this.switched && this.lastHotbarSlot != -1) {
            InventoryUtil.switchToHotbarSlot(this.lastHotbarSlot, false);
            BowSpam.mc.gameSettings.keyBindUseItem.pressed = Mouse.isButtonDown(1);
            this.switched = false;
        }
        else {
            BowSpam.mc.gameSettings.keyBindUseItem.pressed = Mouse.isButtonDown(1);
        }
        if (this.mode.getValue() == Mode.FAST && (this.offhand || BowSpam.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow) && BowSpam.mc.player.isHandActive()) {
            final float f = (float)BowSpam.mc.player.getItemInUseMaxCount();
            final float f2 = this.ticks.getValue();
            final float f3 = this.tpsSync.getValue() ? Cherry.serverManager.getTpsFactor() : 1.0f;
            if (f >= f2 * f3) {
                BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, BowSpam.mc.player.getHorizontalFacing()));
                BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND));
                BowSpam.mc.player.stopActiveHand();
            }
        }
    }
    
    @Override
    public int onUpdate() {
        this.offhand = (BowSpam.mc.player.getHeldItemOffhand().getItem() == Items.BOW && this.allowOffhand.getValue());
        switch (this.mode.getValue()) {
            case AUTORELEASE: {
                if (!this.offhand && !(BowSpam.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow)) {
                    break;
                }
                if (!this.timer.passedMs((int)(this.delay.getValue() * (this.tpsSync.getValue() ? Cherry.serverManager.getTpsFactor() : 1.0f)))) {
                    break;
                }
                BowSpam.mc.playerController.onStoppedUsingItem((EntityPlayer)BowSpam.mc.player);
                this.timer.reset();
                break;
            }
            case BOWBOMB: {
                if (!this.offhand && !(BowSpam.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow)) {
                    break;
                }
                if (!BowSpam.mc.player.isHandActive()) {
                    break;
                }
                final float f = (float)BowSpam.mc.player.getItemInUseMaxCount();
                final float f2 = this.ticks.getValue();
                final float f3 = this.tpsSync.getValue() ? Cherry.serverManager.getTpsFactor() : 1.0f;
                if (f < f2 * f3) {
                    break;
                }
                BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, BowSpam.mc.player.getHorizontalFacing()));
                BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(BowSpam.mc.player.posX, BowSpam.mc.player.posY - 0.0624, BowSpam.mc.player.posZ, BowSpam.mc.player.rotationYaw, BowSpam.mc.player.rotationPitch, false));
                BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(BowSpam.mc.player.posX, BowSpam.mc.player.posY - 999.0, BowSpam.mc.player.posZ, BowSpam.mc.player.rotationYaw, BowSpam.mc.player.rotationPitch, true));
                BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND));
                BowSpam.mc.player.stopActiveHand();
                break;
            }
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getStage() == 0 && this.bowbomb.getValue() && this.mode.getValue() != Mode.BOWBOMB && event.getPacket() instanceof CPacketPlayerDigging && ((CPacketPlayerDigging)event.getPacket()).getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM && (this.offhand || BowSpam.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow) && BowSpam.mc.player.getItemInUseMaxCount() >= 20 && !BowSpam.mc.player.onGround) {
            BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowSpam.mc.player.posX, BowSpam.mc.player.posY - 0.10000000149011612, BowSpam.mc.player.posZ, false));
            BowSpam.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowSpam.mc.player.posX, BowSpam.mc.player.posY - 10000.0, BowSpam.mc.player.posZ, true));
        }
    }
    
    private EntityPlayer getTarget() {
        double maxHealth = 36.0;
        EntityPlayer target = null;
        for (final EntityPlayer player : BowSpam.mc.world.playerEntities) {
            if (player != null && !EntityUtil.isDead((Entity)player) && EntityUtil.getHealth((Entity)player) <= this.health.getValue() && !player.equals((Object)BowSpam.mc.player) && !Cherry.friendManager.isFriend(player) && BowSpam.mc.player.getDistanceSq((Entity)player) <= MathUtil.square(this.range.getValue())) {
                if (!BowSpam.mc.player.canEntityBeSeen((Entity)player) && !EntityUtil.canEntityFeetBeSeen((Entity)player)) {
                    continue;
                }
                if (target == null) {
                    target = player;
                    maxHealth = EntityUtil.getHealth((Entity)player);
                }
                if (this.targetMode.getValue() == Target.CLOSEST && BowSpam.mc.player.getDistanceSq((Entity)player) < BowSpam.mc.player.getDistanceSq((Entity)target)) {
                    target = player;
                    maxHealth = EntityUtil.getHealth((Entity)player);
                }
                if (this.targetMode.getValue() != Target.LOWEST) {
                    continue;
                }
                if (EntityUtil.getHealth((Entity)player) >= maxHealth) {
                    continue;
                }
                target = player;
                maxHealth = EntityUtil.getHealth((Entity)player);
            }
        }
        return target;
    }
    
    public enum Mode
    {
        FAST, 
        AUTORELEASE, 
        BOWBOMB;
    }
    
    public enum Target
    {
        CLOSEST, 
        LOWEST;
    }
}
