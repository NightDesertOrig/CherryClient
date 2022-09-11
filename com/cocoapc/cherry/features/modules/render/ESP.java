//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

import com.cocoapc.cherry.features.modules.*;
import java.util.concurrent.*;
import com.cocoapc.cherry.features.setting.*;
import java.awt.*;
import com.cocoapc.cherry.util.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.passive.*;
import com.cocoapc.cherry.util.util2.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import java.util.*;
import net.minecraft.client.renderer.*;

public class ESP extends Module
{
    private final CopyOnWriteArraySet<CirclePos> positions;
    public Setting<Float> speed;
    public Setting<Float> power;
    public Setting<Float> scale;
    public Setting<Float> width;
    public Setting<Float> fade;
    public Setting<Boolean> player;
    public Setting<Color> playerColor;
    public Setting<Color> friendColor;
    public Setting<Boolean> villager;
    public Setting<Color> villagerColor;
    public Setting<Boolean> animal;
    public Setting<Color> animalColor;
    public Setting<Boolean> monster;
    public Setting<Color> monsterColor;
    public Setting<Boolean> boss;
    public Setting<Color> bossColor;
    public Setting<Integer> alpha;
    private int ticks;
    
    public ESP() {
        super("ESP", "", Module.Category.RENDER, true, false, false);
        this.positions = new CopyOnWriteArraySet<CirclePos>();
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (T)1.0f, (T)10.0f, (T)0.1f));
        this.power = (Setting<Float>)this.register(new Setting("Power", (T)1.0f, (T)1.5f, (T)0.5f));
        this.scale = (Setting<Float>)this.register(new Setting("Scale", (T)1.0f, (T)5.0f, (T)0.5f));
        this.width = (Setting<Float>)this.register(new Setting("Width", (T)1.0f, (T)5.0f, (T)0.5f));
        this.fade = (Setting<Float>)this.register(new Setting("Fade", (T)50.0f, (T)100.0f, (T)10.0f));
        this.player = (Setting<Boolean>)this.register(new Setting("Player", (T)true));
        this.playerColor = (Setting<Color>)this.register(new Setting("Player Color", (T)new Color(255, 10, 10, 255), v -> this.player.getValue()));
        this.friendColor = (Setting<Color>)this.register(new Setting("Friend Color", (T)new Color(50, 200, 205, 255), v -> this.player.getValue()));
        this.villager = (Setting<Boolean>)this.register(new Setting("Villager", (T)true));
        this.villagerColor = (Setting<Color>)this.register(new Setting("Villager Color", (T)new Color(25, 150, 50, 255), v -> this.player.getValue()));
        this.animal = (Setting<Boolean>)this.register(new Setting("Animal", (T)true));
        this.animalColor = (Setting<Color>)this.register(new Setting("Animal Color", (T)new Color(150, 255, 100, 255), v -> this.animal.getValue()));
        this.monster = (Setting<Boolean>)this.register(new Setting("Animal", (T)true));
        this.monsterColor = (Setting<Color>)this.register(new Setting("Monster Color", (T)new Color(255, 255, 0, 255), v -> this.monster.getValue()));
        this.boss = (Setting<Boolean>)this.register(new Setting("Boss", (T)true));
        this.bossColor = (Setting<Color>)this.register(new Setting("Boss Color", (T)new Color(0, 0, 255, 255), v -> this.boss.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)255, (T)100));
        this.ticks = 0;
    }
    
    public void onRender3D() {
        try {
            final float spd = this.speed.getValue() * 0.01f;
            for (final Entity entity : (List)Util.mc.world.loadedEntityList.stream().filter(e -> e.entityId != Util.mc.player.entityId).collect(Collectors.toList())) {
                final double y = entity.posY + entity.height / 2.0f + MathHelper.sin(this.ticks * spd) * this.power.getValue();
                if (entity instanceof EntityVillager && this.villager.getValue()) {
                    this.positions.add(new CirclePos(entity.posX, y, entity.posZ, entity.width, this.villagerColor.getValue(), this.alpha.getValue()));
                }
                if (EntityType.isAnimal(entity) && this.animal.getValue()) {
                    this.positions.add(new CirclePos(entity.posX, y, entity.posZ, entity.width, this.animalColor.getValue(), this.alpha.getValue()));
                }
                if (EntityType.isMonster(entity) && this.monster.getValue()) {
                    this.positions.add(new CirclePos(entity.posX, y, entity.posZ, entity.width, this.monsterColor.getValue(), this.alpha.getValue()));
                }
                if (EntityType.isBoss(entity) && this.boss.getValue()) {
                    this.positions.add(new CirclePos(entity.posX, y, entity.posZ, entity.width, this.bossColor.getValue(), this.alpha.getValue()));
                }
            }
            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.disableAlpha();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.shadeModel(7425);
            GL11.glLineWidth((float)this.width.getValue());
            final float posx = (float)(Util.mc.player.lastTickPosX + (Util.mc.player.posX - Util.mc.player.lastTickPosX) * Util.mc.getRenderPartialTicks());
            final float posy = (float)(Util.mc.player.lastTickPosY + (Util.mc.player.posY - Util.mc.player.lastTickPosY) * Util.mc.getRenderPartialTicks());
            final float posz = (float)(Util.mc.player.lastTickPosZ + (Util.mc.player.posZ - Util.mc.player.lastTickPosZ) * Util.mc.getRenderPartialTicks());
            GlStateManager.translate(posx * -1.0f, posy * -1.0f, posz * -1.0f);
            for (final CirclePos pos : this.positions) {
                final Tessellator tessellator = Tessellator.getInstance();
                final BufferBuilder builder = tessellator.getBuffer();
                builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
                for (int i = 0; i < 360; ++i) {
                    final double x1 = pos.x + MathHelper.sin(i * 3.1415927f / 180.0f) * pos.width * this.scale.getValue();
                    final double z1 = pos.z + MathHelper.cos(i * 3.1415927f / 180.0f) * pos.width * this.scale.getValue();
                    final double x2 = pos.x + MathHelper.sin((i + 1) * 3.1415927f / 180.0f) * pos.width * this.scale.getValue();
                    final double z2 = pos.z + MathHelper.cos((i + 1) * 3.1415927f / 180.0f) * pos.width * this.scale.getValue();
                    final Color color = pos.color;
                    if (pos.alpha < 0) {
                        break;
                    }
                    builder.pos(x1, pos.y, z1).color(color.getRed(), color.getGreen(), color.getBlue(), pos.alpha).endVertex();
                    builder.pos(x2, pos.y, z2).color(color.getRed(), color.getGreen(), color.getBlue(), pos.alpha).endVertex();
                }
                tessellator.draw();
                final CirclePos circlePos = pos;
                circlePos.alpha -= (int)(Object)this.fade.getValue();
            }
            this.positions.removeIf(p -> p.alpha <= 0);
            ++this.ticks;
            GL11.glLineWidth(1.0f);
            GlStateManager.shadeModel(7424);
            GL11.glDisable(2848);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }
        catch (Exception ex) {}
    }
    
    private class CirclePos
    {
        public double x;
        public double y;
        public double z;
        public double width;
        public Color color;
        public int alpha;
        
        public CirclePos(final double x, final double y, final double z, final double width, final Color color, final int alpha) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.width = width;
            this.color = color;
            this.alpha = alpha;
        }
    }
}
