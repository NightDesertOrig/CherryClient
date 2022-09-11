//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ Render.class })
public class MixinRender
{
    @Overwrite
    public boolean shouldRender(final Entity livingEntity, final ICamera camera, final double camX, final double camY, final double camZ) {
        try {
            AxisAlignedBB ignored = livingEntity.getRenderBoundingBox().grow(0.5);
            if ((ignored.hasNaN() || ignored.getAverageEdgeLength() == 0.0) && livingEntity != null) {
                ignored = new AxisAlignedBB(livingEntity.posX - 2.0, livingEntity.posY - 2.0, livingEntity.posZ - 2.0, livingEntity.posX + 2.0, livingEntity.posY + 2.0, livingEntity.posZ + 2.0);
            }
            return livingEntity.isInRangeToRender3d(camX, camY, camZ) && (livingEntity.ignoreFrustumCheck || camera.isBoundingBoxInFrustum(ignored));
        }
        catch (Exception exception) {
            return false;
        }
    }
}
