//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.client.entity.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.features.command.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.network.play.server.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.util.math.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.init.*;

public class FakePlayerPlus extends Module
{
    private static FakePlayerPlus INSTANCE;
    private final Setting<Boolean> pops;
    private final Setting<Boolean> totemPopParticle;
    private final Setting<Boolean> totemPopSound;
    public Setting<Boolean> move;
    public Setting<Type> type;
    public Setting<Integer> chaseX;
    public Setting<Integer> chaseY;
    public Setting<Integer> chaseZ;
    public EntityOtherPlayerMP fakePlayer;
    
    public FakePlayerPlus() {
        super("FakePlayerPlus", "Spawns a FakePlayer for testing.", Category.PLAYER, true, false, false);
        this.pops = (Setting<Boolean>)this.register(new Setting("TotemPops", (T)true));
        this.totemPopParticle = (Setting<Boolean>)this.register(new Setting("TotemPopParticle", (T)true));
        this.totemPopSound = (Setting<Boolean>)this.register(new Setting("TotemPopSound", (T)true));
        this.move = (Setting<Boolean>)this.register(new Setting("Move", (T)true));
        this.type = (Setting<Type>)this.register(new Setting("MovementMode", (T)Type.NONE, v -> this.move.getValue()));
        this.chaseX = (Setting<Integer>)this.register(new Setting("ChaseX", (T)4, (T)1, (T)120, v -> this.move.getValue() && this.type.getValue() == Type.CHASE));
        this.chaseY = (Setting<Integer>)this.register(new Setting("ChaseY", (T)4, (T)1, (T)120, v -> this.move.getValue() && this.type.getValue() == Type.CHASE));
        this.chaseZ = (Setting<Integer>)this.register(new Setting("ChaseZ", (T)4, (T)1, (T)120, v -> this.move.getValue() && this.type.getValue() == Type.CHASE));
    }
    
    public static FakePlayerPlus getInstance() {
        if (FakePlayerPlus.INSTANCE == null) {
            FakePlayerPlus.INSTANCE = new FakePlayerPlus();
        }
        return FakePlayerPlus.INSTANCE;
    }
    
    @Override
    public void onEnable() {
        if (FakePlayerPlus.mc.world == null || FakePlayerPlus.mc.player == null) {
            this.disable();
        }
        else {
            final UUID playerUUID = FakePlayerPlus.mc.player.getUniqueID();
            (this.fakePlayer = new EntityOtherPlayerMP((World)FakePlayerPlus.mc.world, new GameProfile(UUID.fromString(playerUUID.toString()), FakePlayerPlus.mc.player.getDisplayNameString()))).copyLocationAndAnglesFrom((Entity)FakePlayerPlus.mc.player);
            this.fakePlayer.inventory.copyInventory(FakePlayerPlus.mc.player.inventory);
            FakePlayerPlus.mc.world.addEntityToWorld(-7777, (Entity)this.fakePlayer);
            Command.sendMessage(ChatFormatting.GREEN + "FakePlayer name KuroHere was born in the world");
        }
    }
    
    @SubscribeEvent
    public void onTick(final PlayerLivingUpdateEvent event) {
        if (this.pops.getValue() && this.fakePlayer != null) {
            this.fakePlayer.inventory.offHandInventory.set(0, (Object)new ItemStack(Items.TOTEM_OF_UNDYING));
            if (this.fakePlayer.getHealth() <= 0.0f) {
                this.fakePop((Entity)this.fakePlayer);
                this.fakePlayer.setHealth(20.0f);
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.type.getValue().equals(Type.CHASE)) {
            this.fakePlayer.posX = FakePlayerPlus.mc.player.posX + this.chaseX.getValue();
            this.fakePlayer.posY = this.chaseY.getValue();
            this.fakePlayer.posZ = FakePlayerPlus.mc.player.posZ + this.chaseZ.getValue();
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (this.fakePlayer == null) {
            return;
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion explosion = (SPacketExplosion)event.getPacket();
            if (this.fakePlayer.getDistance(explosion.getX(), explosion.getY(), explosion.getZ()) <= 15.0) {
                final double damage = DamageUtil.calculateDamage(explosion.getX(), explosion.getY(), explosion.getZ(), (Entity)this.fakePlayer);
                if (damage > 0.0 && this.pops.getValue()) {
                    this.fakePlayer.setHealth((float)(this.fakePlayer.getHealth() - MathHelper.clamp(damage, 0.0, 999.0)));
                }
            }
        }
    }
    
    public void travel(final float strafe, final float vertical, final float forward) {
        final double d0 = this.fakePlayer.posY;
        float f1 = 0.8f;
        float f2 = 0.02f;
        float f3 = (float)EnchantmentHelper.getDepthStriderModifier((EntityLivingBase)this.fakePlayer);
        if (f3 > 3.0f) {
            f3 = 3.0f;
        }
        if (!this.fakePlayer.onGround) {
            f3 *= 0.5f;
        }
        if (f3 > 0.0f) {
            f1 += (0.54600006f - f1) * f3 / 3.0f;
            f2 += (this.fakePlayer.getAIMoveSpeed() - f2) * f3 / 4.0f;
        }
        this.fakePlayer.moveRelative(strafe, vertical, forward, f2);
        this.fakePlayer.move(MoverType.SELF, this.fakePlayer.motionX, this.fakePlayer.motionY, this.fakePlayer.motionZ);
        final EntityOtherPlayerMP fakePlayer5;
        final EntityOtherPlayerMP fakePlayer = fakePlayer5 = this.fakePlayer;
        fakePlayer5.motionX *= f1;
        final EntityOtherPlayerMP fakePlayer6;
        final EntityOtherPlayerMP fakePlayer2 = fakePlayer6 = this.fakePlayer;
        fakePlayer6.motionY *= 0.800000011920929;
        final EntityOtherPlayerMP fakePlayer7;
        final EntityOtherPlayerMP fakePlayer3 = fakePlayer7 = this.fakePlayer;
        fakePlayer7.motionZ *= f1;
        if (!this.fakePlayer.hasNoGravity()) {
            final EntityOtherPlayerMP fakePlayer8;
            final EntityOtherPlayerMP fakePlayer4 = fakePlayer8 = this.fakePlayer;
            fakePlayer8.motionY -= 0.02;
        }
        if (this.fakePlayer.collidedHorizontally && this.fakePlayer.isOffsetPositionInLiquid(this.fakePlayer.motionX, this.fakePlayer.motionY + 0.6000000238418579 - this.fakePlayer.posY + d0, this.fakePlayer.motionZ)) {
            this.fakePlayer.motionY = 0.30000001192092896;
        }
    }
    
    @Override
    public int onDisable() {
        if (this.fakePlayer != null && FakePlayerPlus.mc.world != null) {
            FakePlayerPlus.mc.world.removeEntityFromWorld(-7777);
            Command.sendMessage(ChatFormatting.GREEN + "FakePlayer name KuroHere got remove");
            this.fakePlayer = null;
        }
        return 0;
    }
    
    @Override
    public void onRender3D() {
    }
    
    private void fakePop(final Entity entity) {
        if (this.totemPopParticle.getValue()) {
            FakePlayerPlus.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.TOTEM, 30);
        }
        if (this.totemPopSound.getValue()) {
            FakePlayerPlus.mc.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
        }
    }
    
    static {
        FakePlayerPlus.INSTANCE = new FakePlayerPlus();
    }
    
    public enum Type
    {
        NONE, 
        CHASE, 
        STATIC;
    }
}
