//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;

public class SelfParticles extends Module
{
    public SelfParticles() {
        super("Particle Bug", "Display Particle.", Category.RENDER, false, false, false);
    }
    
    @Override
    public int onUpdate() {
        final int x = (int)(Math.random() * 5.0 + 0.0);
        final int y = (int)(Math.random() * 3.0 + 1.0);
        final int z = (int)(Math.random() * 5.0 - 1.0);
        final int particleId = (int)(Math.random() * 47.0 + 1.0);
        if (particleId != 1 && particleId != 2 && particleId != 41) {
            SelfParticles.mc.effectRenderer.spawnEffectParticle(particleId, SelfParticles.mc.player.posX + 1.5 + -x, SelfParticles.mc.player.posY + y, SelfParticles.mc.player.posZ + 1.5 + -z, 0.0, 0.5, 0.0, new int[] { 10 });
        }
        return x;
    }
    
    @Override
    public void onRender3D() {
    }
}
