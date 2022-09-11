//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.misc;

import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.setting.*;
import net.minecraft.util.math.*;
import com.cocoapc.cherry.event.events.*;
import java.awt.*;
import net.minecraft.item.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.block.*;

public class BlockBug extends Module
{
    private Setting<Float> range;
    private Setting<Float> Yrange;
    private Setting<Integer> maxPlace;
    private Setting<Boolean> noLiquid;
    public Setting<Boolean> render;
    public Setting<Boolean> box;
    private final Setting<Integer> boxAlpha;
    public Setting<Boolean> outline;
    private final Setting<Float> lineWidth;
    private BlockPos currentPos;
    
    public BlockBug() {
        super("BlockBug", "spamming block", Category.MISC, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)6.0f, (T)2.0f, (T)15.0f));
        this.Yrange = (Setting<Float>)this.register(new Setting("YRange", (T)3.0f, (T)1.0f, (T)10.0f));
        this.maxPlace = (Setting<Integer>)this.register(new Setting("Max Place", (T)3, (T)1, (T)10));
        this.noLiquid = (Setting<Boolean>)this.register(new Setting("No Liquid", (T)true));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)false));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)false, v -> this.render.getValue()));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)85, (T)0, (T)255, v -> this.box.getValue() && this.render.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true, v -> this.render.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("Width", (T)1.0f, (T)0.1f, (T)5.0f, v -> this.outline.getValue() && this.render.getValue()));
        this.currentPos = null;
    }
    
    @Override
    public void onRender3D(final Render3DEvent event) {
        if (this.render.getValue() && this.currentPos != null) {
            final Color color = new Color(138, 62, 255);
            RenderUtil.drawBoxESP(this.currentPos, color, false, color, this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
            this.currentPos = null;
        }
    }
    
    @Override
    public int onTick() {
        if (nullCheck()) {
            return 0;
        }
        if (!(BlockBug.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBlock) && !(BlockBug.mc.player.inventory.getCurrentItem().getItem() instanceof ItemSkull)) {
            return 0;
        }
        final List<BlockPos> queue = new ArrayList<BlockPos>();
        final int r = (int)Math.ceil(this.range.getValue());
        final int ry = (int)Math.ceil(this.Yrange.getValue());
        for (int x = r * -1; x < r; ++x) {
            for (int y = ry * -1; y < 1; ++y) {
                for (int z = r * -1; z < r; ++z) {
                    final BlockPos pos = new BlockPos(BlockBug.mc.player.posX, BlockBug.mc.player.posY, BlockBug.mc.player.posZ).add(x, y, z);
                    if (BlockUtil.getFirstFacing(pos) != null && new BlockPos(BlockBug.mc.player.posX, BlockBug.mc.player.posY, BlockBug.mc.player.posZ).getDistance(pos.getX(), pos.getY(), pos.getZ()) >= 3.0 && (this.getBlock(pos) == Blocks.AIR || (this.getBlock(pos) instanceof BlockLiquid && !this.noLiquid.getValue()))) {
                        queue.add(pos);
                    }
                }
            }
        }
        for (int i = 0; i < this.maxPlace.getValue(); ++i) {
            if (queue.size() == 0) {
                return r;
            }
            final BlockPos nearest = queue.stream().min(Comparator.comparing(b -> BlockBug.mc.player.getDistance((double)b.getX(), (double)b.getY(), (double)b.getZ()))).orElse(null);
            for (final Entity e : BlockBug.mc.world.loadedEntityList) {
                if (e.getDistance((double)nearest.getX(), (double)nearest.getY(), (double)nearest.getZ()) < 2.0) {}
            }
            BlockUtil.placeBlock(nearest, EnumHand.MAIN_HAND, true, false, false);
            this.currentPos = nearest;
        }
        return r;
    }
    
    @Override
    public void onRender3D() {
    }
    
    @Override
    public int onDisable() {
        this.currentPos = null;
        return 0;
    }
    
    private Block getBlock(final BlockPos b) {
        return BlockBug.mc.world.getBlockState(b).getBlock();
    }
}
