//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.movement;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.util.math.*;
import java.awt.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.util.*;

public class Scafforld extends Module
{
    public Scafforld() {
        super("Scafforld", "Scafforld", Module.Category.MOVEMENT, true, false, false);
    }
    
    public void onRender3D(final Render3DEvent event) {
        final BlockPos pos3 = new BlockPos(Scafforld.mc.player.posX, Scafforld.mc.player.posY - 1.0, Scafforld.mc.player.posZ);
        RenderUtil.drawBox(pos3, Color.cyan);
        BlockUtil.placeBlock(pos3, EnumHand.MAIN_HAND, false, true, false);
    }
    
    public void onRender3D() {
    }
}
