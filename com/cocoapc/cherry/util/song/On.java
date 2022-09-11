//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.song;

import com.cocoapc.cherry.features.gui.custom.*;
import javax.annotation.*;
import net.minecraft.client.audio.*;
import net.minecraft.util.*;

public class On implements Globals
{
    public static final ISound sound;
    private static final String song = "on";
    private static final ResourceLocation loc;
    
    static {
        loc = new ResourceLocation("sounds/on.ogg");
        sound = (ISound)new ISound() {
            private final int pitch = 1;
            private final int volume = 1;
            
            public ResourceLocation getSoundLocation() {
                return On.loc;
            }
            
            @Nullable
            public SoundEventAccessor createAccessor(final SoundHandler soundHandler) {
                return new SoundEventAccessor(On.loc, "On");
            }
            
            public Sound getSound() {
                return new Sound("on", 1.0f, 1.0f, 1, Sound.Type.SOUND_EVENT, false);
            }
            
            public SoundCategory getCategory() {
                return SoundCategory.VOICE;
            }
            
            public boolean canRepeat() {
                return true;
            }
            
            public int getRepeatDelay() {
                return 2;
            }
            
            public float getVolume() {
                return 1.0f;
            }
            
            public float getPitch() {
                return 1.0f;
            }
            
            public float getXPosF() {
                return 1.0f;
            }
            
            public float getYPosF() {
                return 0.0f;
            }
            
            public float getZPosF() {
                return 0.0f;
            }
            
            public ISound.AttenuationType getAttenuationType() {
                return ISound.AttenuationType.LINEAR;
            }
        };
    }
}
