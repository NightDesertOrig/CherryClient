//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import net.minecraft.util.*;
import java.io.*;
import javax.imageio.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraftforge.fml.client.*;
import java.awt.image.*;

public class SkinStorageManipulationer
{
    public static ResourceLocation getTexture() {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File("Wurstplus3/tmp/skin.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        final DynamicTexture texture = new DynamicTexture(bufferedImage);
        final WrappedResource wr = new WrappedResource(FMLClientHandler.instance().getClient().getTextureManager().getDynamicTextureLocation("skin.png", texture));
        return wr.location;
    }
    
    public static class WrappedResource
    {
        public final ResourceLocation location;
        
        public WrappedResource(final ResourceLocation location) {
            this.location = location;
        }
    }
}
