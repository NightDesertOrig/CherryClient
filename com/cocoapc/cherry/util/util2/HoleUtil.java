//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import com.cocoapc.cherry.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import java.util.*;

public class HoleUtil implements Util
{
    public static Hole getHole(final BlockPos pos, final int height) {
        boolean a = false;
        for (int b = 0; b < height; ++b) {
            if (!WorldUtils.empty.contains(WorldUtils.getBlock(pos.add(0, b + 1, 0)))) {
                a = true;
            }
        }
        if (a) {
            return null;
        }
        if (WorldUtils.empty.contains(WorldUtils.getBlock(pos)) && !WorldUtils.empty.contains(WorldUtils.getBlock(pos.down()))) {
            if ((WorldUtils.getBlock(pos.north()) instanceof BlockObsidian || WorldUtils.getBlock(pos.north()) == Blocks.BEDROCK) && (WorldUtils.getBlock(pos.south()) instanceof BlockObsidian || WorldUtils.getBlock(pos.south()) == Blocks.BEDROCK) && (WorldUtils.getBlock(pos.east()) instanceof BlockObsidian || WorldUtils.getBlock(pos.east()) == Blocks.BEDROCK) && (WorldUtils.getBlock(pos.west()) instanceof BlockObsidian || WorldUtils.getBlock(pos.west()) == Blocks.BEDROCK)) {
                if (WorldUtils.getBlock(pos.north()) instanceof BlockObsidian || WorldUtils.getBlock(pos.east()) instanceof BlockObsidian || WorldUtils.getBlock(pos.south()) instanceof BlockObsidian || WorldUtils.getBlock(pos.west()) instanceof BlockObsidian) {
                    return new SingleHole(pos, material.OBSIDIAN);
                }
                return new SingleHole(pos, material.BEDROCK);
            }
            else {
                final BlockPos[] dir = { pos.west(), pos.north(), pos.east(), pos.south() };
                BlockPos pos2 = null;
                for (final BlockPos dir2 : dir) {
                    if (WorldUtils.empty.contains(WorldUtils.getBlock(dir2))) {
                        pos2 = dir2;
                        break;
                    }
                }
                if (pos2 == null || WorldUtils.empty.contains(WorldUtils.getBlock(pos2.down()))) {
                    return null;
                }
                final BlockPos[] dir3 = { pos2.west(), pos2.north(), pos2.east(), pos2.south() };
                int checked = 0;
                boolean obi = false;
                EnumFacing facing = null;
                for (final BlockPos pos3 : dir3) {
                    if (pos3 != pos) {
                        if (WorldUtils.getBlock(pos3) instanceof BlockObsidian) {
                            obi = true;
                            ++checked;
                        }
                        if (WorldUtils.getBlock(pos3) == Blocks.BEDROCK) {
                            ++checked;
                        }
                    }
                }
                for (final BlockPos pos3 : dir) {
                    if (pos3 != pos2) {
                        if (WorldUtils.getBlock(pos3) instanceof BlockObsidian) {
                            obi = true;
                            ++checked;
                        }
                        if (WorldUtils.getBlock(pos3) == Blocks.BEDROCK) {
                            ++checked;
                        }
                    }
                }
                final EnumFacing[] values = EnumFacing.values();
                for (int length4 = values.length, l = 0; l < length4; ++l) {
                    final EnumFacing facing2 = facing = values[l];
                }
                if (checked >= 6) {
                    return new DoubleHole(pos, pos2, obi ? material.OBSIDIAN : material.BEDROCK, facing);
                }
            }
        }
        return null;
    }
    
    public static ArrayList<Hole> holes(final float r, final int height) {
        final ArrayList<Hole> holes = new ArrayList<Hole>();
        for (final BlockPos pos : WorldUtils.getSphere(PlayerUtil.getPlayerPosFloored(), r, (int)r, false, true, 0)) {
            final Hole hole = getHole(pos, height);
            if (hole instanceof QuadHole) {
                boolean a = false;
                for (final Hole hole2 : holes) {
                    if (hole2 instanceof QuadHole) {
                        if (!((QuadHole)hole2).contains((QuadHole)hole)) {
                            continue;
                        }
                        a = true;
                        break;
                    }
                }
                if (a) {
                    continue;
                }
            }
            if (hole instanceof DoubleHole) {
                boolean a = false;
                for (final Hole hole2 : holes) {
                    if (hole2 instanceof DoubleHole) {
                        if (!((DoubleHole)hole2).contains((DoubleHole)hole)) {
                            continue;
                        }
                        a = true;
                        break;
                    }
                }
                if (a) {
                    continue;
                }
            }
            if (hole == null) {
                continue;
            }
            holes.add(hole);
        }
        return holes;
    }
    
    public static final class QuadHole extends Hole
    {
        public BlockPos pos;
        public BlockPos pos1;
        public BlockPos pos2;
        public BlockPos pos3;
        public EnumFacing dir;
        
        public QuadHole(final BlockPos pos, final BlockPos pos1, final BlockPos pos2, final BlockPos pos3, final material mat, final EnumFacing dir) {
            this.pos = pos;
            this.pos1 = pos1;
            this.pos2 = pos2;
            this.pos3 = pos3;
            this.dir = dir;
        }
        
        public boolean contains(final BlockPos pos) {
            return this.pos == pos || this.pos1 == this.pos1 || this.pos2 == this.pos2 || this.pos3 == pos;
        }
        
        public boolean contains(final QuadHole pos) {
            return pos.pos.equals((Object)this.pos) || pos.pos.equals((Object)this.pos1) || pos.pos.equals((Object)this.pos2) || pos.pos3.equals((Object)this.pos) || pos.pos1.equals((Object)this.pos3);
        }
        
        public boolean equals(final QuadHole pos) {
            return pos.pos3.equals((Object)this.pos) || pos.pos3.equals((Object)this.pos3) || pos.pos2.equals((Object)this.pos) || pos.pos2.equals((Object)this.pos2) || pos.pos1.equals((Object)this.pos) || (pos.pos1.equals((Object)this.pos1) && (pos.pos.equals((Object)this.pos) || pos.pos.equals((Object)this.pos3) || pos.pos.equals((Object)this.pos2) || pos.pos.equals((Object)this.pos1)));
        }
    }
    
    public static final class DoubleHole extends Hole
    {
        public BlockPos pos;
        public BlockPos pos1;
        public EnumFacing dir;
        
        public DoubleHole(final BlockPos pos, final BlockPos pos1, final material mat, final EnumFacing dir) {
            this.pos = pos;
            this.pos1 = pos1;
            this.dir = dir;
        }
        
        public boolean contains(final BlockPos pos) {
            return this.pos == pos || this.pos1 == pos;
        }
        
        public boolean contains(final DoubleHole pos) {
            return pos.pos.equals((Object)this.pos) || pos.pos.equals((Object)this.pos1) || pos.pos1.equals((Object)this.pos) || pos.pos1.equals((Object)this.pos1);
        }
        
        public boolean equals(final DoubleHole pos) {
            return (pos.pos1.equals((Object)this.pos) || pos.pos1.equals((Object)this.pos1)) && (pos.pos.equals((Object)this.pos) || pos.pos.equals((Object)this.pos1));
        }
    }
    
    public static final class SingleHole extends Hole
    {
        public BlockPos pos;
        
        public SingleHole(final BlockPos pos, final material mat) {
            this.pos = pos;
        }
    }
    
    public static class Hole
    {
        public type type;
        public material mat;
        
        public Hole(final type type2, final material mat) {
            this.type = type2;
            this.mat = mat;
        }
        
        public Hole() {
        }
    }
    
    public enum material
    {
        BEDROCK, 
        OBSIDIAN;
    }
    
    public enum type
    {
        DOUBLE, 
        SINGLE, 
        QUAD;
    }
}
