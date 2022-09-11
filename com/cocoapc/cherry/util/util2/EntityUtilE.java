//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import com.cocoapc.cherry.util.*;
import java.util.*;
import net.minecraft.entity.player.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class EntityUtilE implements Util
{
    public static List<EntityPlayer> getPlayers() {
        return (List<EntityPlayer>)EntityUtilE.mc.world.playerEntities.stream().filter(e -> e.entityId != EntityUtilE.mc.player.entityId).collect(Collectors.toList());
    }
    
    public static EntityPlayer getPlayerByName(final String name) {
        return getPlayers().stream().filter(e -> e.getDisplayNameString().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    
    public static BlockPos getEntityPos(final Entity entity) {
        return new BlockPos(entity);
    }
    
    public static float getPlayerHealth(final EntityPlayer player) {
        return player.getHealth() + player.getAbsorptionAmount();
    }
}
