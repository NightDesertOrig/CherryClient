//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.features.modules.render.*;

@Mixin({ AbstractClientPlayer.class })
public class MixinAbstractClientPlayer
{
    @Inject(method = { "hasSkin" }, at = { @At("RETURN") }, cancellable = true)
    public void hasSkin(final CallbackInfoReturnable<Boolean> cir) {
        if (isEnabled()) {
            cir.setReturnValue((Object)true);
        }
    }
    
    @Inject(method = { "getLocationSkin()Lnet/minecraft/util/ResourceLocation;" }, at = { @At("RETURN") }, cancellable = true)
    private void getLocationSkin(final CallbackInfoReturnable<ResourceLocation> cir) {
        if (isEnabled()) {
            cir.setReturnValue((Object)getLocation());
        }
    }
    
    @Inject(method = { "getLocationSkin(Ljava/lang/String;)Lnet/minecraft/util/ResourceLocation;" }, at = { @At("RETURN") }, cancellable = true)
    private static void getLocationSkinByUserName(final CallbackInfoReturnable<ResourceLocation> cir) {
        if (isEnabled()) {
            cir.setReturnValue((Object)getLocation());
        }
    }
    
    @Inject(method = { "getSkinType" }, at = { @At("RETURN") }, cancellable = true)
    private void getSkinType(final CallbackInfoReturnable<String> cir) {
        if (isEnabled()) {
            cir.setReturnValue((Object)"default");
        }
    }
    
    private static boolean isEnabled() {
        return SussySkin.INSTANCE.isEnabled();
    }
    
    private static ResourceLocation getLocation() {
        if (SussySkin.INSTANCE.skin.getValue() == SussySkin.skins.enokitoraisu) {
            return new ResourceLocation("enokitoraisu.png");
        }
        if (SussySkin.INSTANCE.skin.getValue() == SussySkin.skins.Sus) {
            return new ResourceLocation("redsus.png");
        }
        if (SussySkin.INSTANCE.skin.getValue() == SussySkin.skins.BlueSus) {
            return new ResourceLocation("bluesus.png");
        }
        if (SussySkin.INSTANCE.skin.getValue() == SussySkin.skins.Yellowsus) {
            return new ResourceLocation("yellowsus.png");
        }
        if (SussySkin.INSTANCE.skin.getValue() == SussySkin.skins.Blacksus) {
            return new ResourceLocation("blacksus.png");
        }
        if (SussySkin.INSTANCE.skin.getValue() == SussySkin.skins.Purplesus) {
            return new ResourceLocation("purple.png");
        }
        if (SussySkin.INSTANCE.skin.getValue() == SussySkin.skins.seisan) {
            return new ResourceLocation("seisan.png");
        }
        return null;
    }
}
