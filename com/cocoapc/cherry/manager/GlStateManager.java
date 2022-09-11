//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.manager;

import org.lwjgl.opengl.*;
import java.nio.*;

public class GlStateManager
{
    private static AlphaState alphaState;
    private static BooleanState lightingState;
    private static BooleanState[] lightState;
    private static ColorMaterialState colorMaterialState;
    private static BlendState blendState;
    private static DepthState depthState;
    private static FogState fogState;
    private static CullState cullState;
    private static PolygonOffsetState polygonOffsetState;
    private static ColorLogicState colorLogicState;
    private static TexGenState texGenState;
    private static ClearState clearState;
    private static StencilState stencilState;
    private static BooleanState normalizeState;
    public static int activeTextureUnit;
    public static TextureState[] textureState;
    private static int activeShadeModel;
    private static BooleanState rescaleNormalState;
    private static ColorMask colorMaskState;
    private static Color colorState;
    private static final String __OBFID = "CL_00002558";
    public static boolean clearEnabled;
    
    public static void pushAttrib() {
        GL11.glPushAttrib(8256);
    }
    
    public static void popAttrib() {
        GL11.glPopAttrib();
    }
    
    public static void disableAlpha() {
        GlStateManager.alphaState.alphaTest.setDisabled();
    }
    
    public static void enableAlpha() {
        GlStateManager.alphaState.alphaTest.setEnabled();
    }
    
    public static void alphaFunc(final int func, final float ref) {
        if (func != GlStateManager.alphaState.func || ref != GlStateManager.alphaState.ref) {
            GL11.glAlphaFunc(GlStateManager.alphaState.func = func, GlStateManager.alphaState.ref = ref);
        }
    }
    
    public static void enableLighting() {
        GlStateManager.lightingState.setEnabled();
    }
    
    public static void disableLighting() {
        GlStateManager.lightingState.setDisabled();
    }
    
    public static void enableLight(final int light) {
        GlStateManager.lightState[light].setEnabled();
    }
    
    public static void disableLight(final int light) {
        GlStateManager.lightState[light].setDisabled();
    }
    
    public static void enableColorMaterial() {
        GlStateManager.colorMaterialState.colorMaterial.setEnabled();
    }
    
    public static void disableColorMaterial() {
        GlStateManager.colorMaterialState.colorMaterial.setDisabled();
    }
    
    public static void colorMaterial(final int face, final int mode) {
        if (face != GlStateManager.colorMaterialState.face || mode != GlStateManager.colorMaterialState.mode) {
            GL11.glColorMaterial(GlStateManager.colorMaterialState.face = face, GlStateManager.colorMaterialState.mode = mode);
        }
    }
    
    public static void disableDepth() {
        GlStateManager.depthState.depthTest.setDisabled();
    }
    
    public static void enableDepth() {
        GlStateManager.depthState.depthTest.setEnabled();
    }
    
    public static void depthFunc(final int depthFunc) {
        if (depthFunc != GlStateManager.depthState.depthFunc) {
            GL11.glDepthFunc(GlStateManager.depthState.depthFunc = depthFunc);
        }
    }
    
    public static void depthMask(final boolean flagIn) {
        if (flagIn != GlStateManager.depthState.maskEnabled) {
            GL11.glDepthMask(GlStateManager.depthState.maskEnabled = flagIn);
        }
    }
    
    public static void disableBlend() {
        GlStateManager.blendState.blend.setDisabled();
    }
    
    public static void enableBlend() {
        GlStateManager.blendState.blend.setEnabled();
    }
    
    public static void blendFunc(final int srcFactor, final int dstFactor) {
        if (srcFactor != GlStateManager.blendState.srcFactor || dstFactor != GlStateManager.blendState.dstFactor) {
            GL11.glBlendFunc(GlStateManager.blendState.srcFactor = srcFactor, GlStateManager.blendState.dstFactor = dstFactor);
        }
    }
    
    public static void enableFog() {
        GlStateManager.fogState.fog.setEnabled();
    }
    
    public static void disableFog() {
        GlStateManager.fogState.fog.setDisabled();
    }
    
    public static void setFog(final int param) {
        if (param != GlStateManager.fogState.mode) {
            GL11.glFogi(2917, GlStateManager.fogState.mode = param);
        }
    }
    
    public static void setFogDensity(final float param) {
        if (param != GlStateManager.fogState.density) {
            GL11.glFogf(2914, GlStateManager.fogState.density = param);
        }
    }
    
    public static void setFogStart(final float param) {
        if (param != GlStateManager.fogState.start) {
            GL11.glFogf(2915, GlStateManager.fogState.start = param);
        }
    }
    
    public static void setFogEnd(final float param) {
        if (param != GlStateManager.fogState.end) {
            GL11.glFogf(2916, GlStateManager.fogState.end = param);
        }
    }
    
    public static void enableCull() {
        GlStateManager.cullState.cullFace.setEnabled();
    }
    
    public static void disableCull() {
        GlStateManager.cullState.cullFace.setDisabled();
    }
    
    public static void cullFace(final int mode) {
        if (mode != GlStateManager.cullState.mode) {
            GL11.glCullFace(GlStateManager.cullState.mode = mode);
        }
    }
    
    public static void enablePolygonOffset() {
        GlStateManager.polygonOffsetState.polygonOffsetFill.setEnabled();
    }
    
    public static void disablePolygonOffset() {
        GlStateManager.polygonOffsetState.polygonOffsetFill.setDisabled();
    }
    
    public static void doPolygonOffset(final float factor, final float units) {
        if (factor != GlStateManager.polygonOffsetState.factor || units != GlStateManager.polygonOffsetState.units) {
            GL11.glPolygonOffset(GlStateManager.polygonOffsetState.factor = factor, GlStateManager.polygonOffsetState.units = units);
        }
    }
    
    public static void enableColorLogic() {
        GlStateManager.colorLogicState.colorLogicOp.setEnabled();
    }
    
    public static void disableColorLogic() {
        GlStateManager.colorLogicState.colorLogicOp.setDisabled();
    }
    
    public static void colorLogicOp(final int opcode) {
        if (opcode != GlStateManager.colorLogicState.opcode) {
            GL11.glLogicOp(GlStateManager.colorLogicState.opcode = opcode);
        }
    }
    
    public static void enableTexGenCoord(final TexGen texGen) {
        texGenCoord(texGen).textureGen.setEnabled();
    }
    
    public static void disableTexGenCoord(final TexGen texGen) {
        texGenCoord(texGen).textureGen.setDisabled();
    }
    
    public static void texGen(final TexGen texGen, final int param) {
        final TexGenCoord glstatemanager$texgencoord = texGenCoord(texGen);
        if (param != glstatemanager$texgencoord.param) {
            glstatemanager$texgencoord.param = param;
            GL11.glTexGeni(glstatemanager$texgencoord.coord, 9472, param);
        }
    }
    
    public static void texGen(final TexGen texGen, final int pname, final FloatBuffer params) {
        GL11.glTexGen(texGenCoord(texGen).coord, pname, params);
    }
    
    private static TexGenCoord texGenCoord(final TexGen texGen) {
        switch (1.field_179175_a[texGen.ordinal()]) {
            case 1: {
                return GlStateManager.texGenState.s;
            }
            case 2: {
                return GlStateManager.texGenState.t;
            }
            case 3: {
                return GlStateManager.texGenState.r;
            }
            case 4: {
                return GlStateManager.texGenState.q;
            }
            default: {
                return GlStateManager.texGenState.s;
            }
        }
    }
    
    public static void enableTexture2D() {
        GlStateManager.textureState[GlStateManager.activeTextureUnit].texture2DState.setEnabled();
    }
    
    public static void disableTexture2D() {
        GlStateManager.textureState[GlStateManager.activeTextureUnit].texture2DState.setDisabled();
    }
    
    public static int generateTexture() {
        return GL11.glGenTextures();
    }
    
    public static void deleteTexture(final int texture) {
        if (texture != 0) {
            GL11.glDeleteTextures(texture);
            for (final TextureState glstatemanager$texturestate : GlStateManager.textureState) {
                if (glstatemanager$texturestate.textureName == texture) {
                    glstatemanager$texturestate.textureName = 0;
                }
            }
        }
    }
    
    public static void bindTexture(final int texture) {
        if (texture != GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName) {
            GL11.glBindTexture(3553, GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = texture);
        }
    }
    
    public static void bindCurrentTexture() {
        GL11.glBindTexture(3553, GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName);
    }
    
    public static void enableNormalize() {
        GlStateManager.normalizeState.setEnabled();
    }
    
    public static void disableNormalize() {
        GlStateManager.normalizeState.setDisabled();
    }
    
    public static void shadeModel(final int mode) {
        if (mode != GlStateManager.activeShadeModel) {
            GL11.glShadeModel(GlStateManager.activeShadeModel = mode);
        }
    }
    
    public static void enableRescaleNormal() {
        GlStateManager.rescaleNormalState.setEnabled();
    }
    
    public static void disableRescaleNormal() {
        GlStateManager.rescaleNormalState.setDisabled();
    }
    
    public static void viewport(final int x, final int y, final int width, final int height) {
        GL11.glViewport(x, y, width, height);
    }
    
    public static void colorMask(final boolean red, final boolean green, final boolean blue, final boolean alpha) {
        if (red != GlStateManager.colorMaskState.red || green != GlStateManager.colorMaskState.green || blue != GlStateManager.colorMaskState.blue || alpha != GlStateManager.colorMaskState.alpha) {
            GL11.glColorMask(GlStateManager.colorMaskState.red = red, GlStateManager.colorMaskState.green = green, GlStateManager.colorMaskState.blue = blue, GlStateManager.colorMaskState.alpha = alpha);
        }
    }
    
    public static void clearDepth(final double depth) {
        if (depth != GlStateManager.clearState.depth) {
            GL11.glClearDepth(GlStateManager.clearState.depth = depth);
        }
    }
    
    public static void clearColor(final float red, final float green, final float blue, final float alpha) {
        if (red != GlStateManager.clearState.color.red || green != GlStateManager.clearState.color.green || blue != GlStateManager.clearState.color.blue || alpha != GlStateManager.clearState.color.alpha) {
            GL11.glClearColor(GlStateManager.clearState.color.red = red, GlStateManager.clearState.color.green = green, GlStateManager.clearState.color.blue = blue, GlStateManager.clearState.color.alpha = alpha);
        }
    }
    
    public static void clear(final int mask) {
        if (GlStateManager.clearEnabled) {
            GL11.glClear(mask);
        }
    }
    
    public static void matrixMode(final int mode) {
        GL11.glMatrixMode(mode);
    }
    
    public static void loadIdentity() {
        GL11.glLoadIdentity();
    }
    
    public static void pushMatrix() {
        GL11.glPushMatrix();
    }
    
    public static void popMatrix() {
        GL11.glPopMatrix();
    }
    
    public static void getFloat(final int pname, final FloatBuffer params) {
        GL11.glGetFloat(pname, params);
    }
    
    public static void ortho(final double left, final double right, final double bottom, final double top, final double zNear, final double zFar) {
        GL11.glOrtho(left, right, bottom, top, zNear, zFar);
    }
    
    public static void rotate(final float angle, final float x, final float y, final float z) {
        GL11.glRotatef(angle, x, y, z);
    }
    
    public static void scale(final float x, final float y, final float z) {
        GL11.glScalef(x, y, z);
    }
    
    public static void scale(final double x, final double y, final double z) {
        GL11.glScaled(x, y, z);
    }
    
    public static void translate(final float x, final float y, final float z) {
        GL11.glTranslatef(x, y, z);
    }
    
    public static void translate(final double x, final double y, final double z) {
        GL11.glTranslated(x, y, z);
    }
    
    public static void multMatrix(final FloatBuffer matrix) {
        GL11.glMultMatrix(matrix);
    }
    
    public static void color(final float colorRed, final float colorGreen, final float colorBlue, final float colorAlpha) {
        if (colorRed != GlStateManager.colorState.red || colorGreen != GlStateManager.colorState.green || colorBlue != GlStateManager.colorState.blue || colorAlpha != GlStateManager.colorState.alpha) {
            GL11.glColor4f(GlStateManager.colorState.red = colorRed, GlStateManager.colorState.green = colorGreen, GlStateManager.colorState.blue = colorBlue, GlStateManager.colorState.alpha = colorAlpha);
        }
    }
    
    public static void color(final float colorRed, final float colorGreen, final float colorBlue) {
        color(colorRed, colorGreen, colorBlue, 1.0f);
    }
    
    public static void resetColor() {
        final Color colorState = GlStateManager.colorState;
        final Color colorState2 = GlStateManager.colorState;
        final Color colorState3 = GlStateManager.colorState;
        final Color colorState4 = GlStateManager.colorState;
        final float n = -1.0f;
        colorState4.alpha = n;
        colorState3.blue = n;
        colorState2.green = n;
        colorState.red = n;
    }
    
    public static void callList(final int list) {
        GL11.glCallList(list);
    }
    
    public static void deleteTextures(final IntBuffer p_deleteTextures_0_) {
        p_deleteTextures_0_.rewind();
        while (p_deleteTextures_0_.position() < p_deleteTextures_0_.limit()) {
            final int i = p_deleteTextures_0_.get();
            deleteTexture(i);
        }
        p_deleteTextures_0_.rewind();
    }
    
    static {
        GlStateManager.alphaState = new AlphaState(null);
        GlStateManager.lightingState = new BooleanState(2896);
        GlStateManager.lightState = new BooleanState[8];
        GlStateManager.colorMaterialState = new ColorMaterialState(null);
        GlStateManager.blendState = new BlendState(null);
        GlStateManager.depthState = new DepthState(null);
        GlStateManager.fogState = new FogState(null);
        GlStateManager.cullState = new CullState(null);
        GlStateManager.polygonOffsetState = new PolygonOffsetState(null);
        GlStateManager.colorLogicState = new ColorLogicState(null);
        GlStateManager.texGenState = new TexGenState(null);
        GlStateManager.clearState = new ClearState(null);
        GlStateManager.stencilState = new StencilState(null);
        GlStateManager.normalizeState = new BooleanState(2977);
        GlStateManager.activeTextureUnit = 0;
        GlStateManager.textureState = new TextureState[32];
        GlStateManager.activeShadeModel = 7425;
        GlStateManager.rescaleNormalState = new BooleanState(32826);
        GlStateManager.colorMaskState = new ColorMask(null);
        GlStateManager.colorState = new Color();
        GlStateManager.clearEnabled = true;
        for (int i = 0; i < 8; ++i) {
            GlStateManager.lightState[i] = new BooleanState(16384 + i);
        }
        for (int j = 0; j < GlStateManager.textureState.length; ++j) {
            GlStateManager.textureState[j] = new TextureState(null);
        }
    }
    
    static final class 1
    {
        static final int[] field_179175_a;
        private static final String __OBFID = "CL_00002557";
        
        static {
            field_179175_a = new int[TexGen.values().length];
            try {
                1.field_179175_a[TexGen.S.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                1.field_179175_a[TexGen.T.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError2) {}
            try {
                1.field_179175_a[TexGen.R.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError3) {}
            try {
                1.field_179175_a[TexGen.Q.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError4) {}
        }
    }
    
    static class AlphaState
    {
        public BooleanState alphaTest;
        public int func;
        public float ref;
        private static final String __OBFID = "CL_00002556";
        
        private AlphaState() {
            this.alphaTest = new BooleanState(3008);
            this.func = 519;
            this.ref = -1.0f;
        }
        
        AlphaState(final 1 p_i46489_1_) {
            this();
        }
    }
    
    static class BlendState
    {
        public BooleanState blend;
        public int srcFactor;
        public int dstFactor;
        public int srcFactorAlpha;
        public int dstFactorAlpha;
        private static final String __OBFID = "CL_00002555";
        
        private BlendState() {
            this.blend = new BooleanState(3042);
            this.srcFactor = 1;
            this.dstFactor = 0;
            this.srcFactorAlpha = 1;
            this.dstFactorAlpha = 0;
        }
        
        BlendState(final 1 p_i46488_1_) {
            this();
        }
    }
    
    static class BooleanState
    {
        private final int capability;
        private boolean currentState;
        private static final String __OBFID = "CL_00002554";
        
        public BooleanState(final int capabilityIn) {
            this.currentState = false;
            this.capability = capabilityIn;
        }
        
        public void setDisabled() {
            this.setState(false);
        }
        
        public void setEnabled() {
            this.setState(true);
        }
        
        public void setState(final boolean state) {
            if (state != this.currentState) {
                this.currentState = state;
                if (state) {
                    GL11.glEnable(this.capability);
                }
                else {
                    GL11.glDisable(this.capability);
                }
            }
        }
    }
    
    static class ClearState
    {
        public double depth;
        public Color color;
        public int field_179204_c;
        private static final String __OBFID = "CL_00002553";
        
        private ClearState() {
            this.depth = 1.0;
            this.color = new Color(0.0f, 0.0f, 0.0f, 0.0f);
            this.field_179204_c = 0;
        }
        
        ClearState(final 1 p_i46487_1_) {
            this();
        }
    }
    
    static class Color
    {
        public float red;
        public float green;
        public float blue;
        public float alpha;
        private static final String __OBFID = "CL_00002552";
        
        public Color() {
            this.red = 1.0f;
            this.green = 1.0f;
            this.blue = 1.0f;
            this.alpha = 1.0f;
        }
        
        public Color(final float redIn, final float greenIn, final float blueIn, final float alphaIn) {
            this.red = 1.0f;
            this.green = 1.0f;
            this.blue = 1.0f;
            this.alpha = 1.0f;
            this.red = redIn;
            this.green = greenIn;
            this.blue = blueIn;
            this.alpha = alphaIn;
        }
    }
    
    static class ColorLogicState
    {
        public BooleanState colorLogicOp;
        public int opcode;
        private static final String __OBFID = "CL_00002551";
        
        private ColorLogicState() {
            this.colorLogicOp = new BooleanState(3058);
            this.opcode = 5379;
        }
        
        ColorLogicState(final 1 p_i46486_1_) {
            this();
        }
    }
    
    static class ColorMask
    {
        public boolean red;
        public boolean green;
        public boolean blue;
        public boolean alpha;
        private static final String __OBFID = "CL_00002550";
        
        private ColorMask() {
            this.red = true;
            this.green = true;
            this.blue = true;
            this.alpha = true;
        }
        
        ColorMask(final 1 p_i46485_1_) {
            this();
        }
    }
    
    static class ColorMaterialState
    {
        public BooleanState colorMaterial;
        public int face;
        public int mode;
        private static final String __OBFID = "CL_00002549";
        
        private ColorMaterialState() {
            this.colorMaterial = new BooleanState(2903);
            this.face = 1032;
            this.mode = 5634;
        }
        
        ColorMaterialState(final 1 p_i46484_1_) {
            this();
        }
    }
    
    static class CullState
    {
        public BooleanState cullFace;
        public int mode;
        private static final String __OBFID = "CL_00002548";
        
        private CullState() {
            this.cullFace = new BooleanState(2884);
            this.mode = 1029;
        }
        
        CullState(final 1 p_i46483_1_) {
            this();
        }
    }
    
    static class DepthState
    {
        public BooleanState depthTest;
        public boolean maskEnabled;
        public int depthFunc;
        private static final String __OBFID = "CL_00002547";
        
        private DepthState() {
            this.depthTest = new BooleanState(2929);
            this.maskEnabled = true;
            this.depthFunc = 513;
        }
        
        DepthState(final 1 p_i46482_1_) {
            this();
        }
    }
    
    static class FogState
    {
        public BooleanState fog;
        public int mode;
        public float density;
        public float start;
        public float end;
        private static final String __OBFID = "CL_00002546";
        
        private FogState() {
            this.fog = new BooleanState(2912);
            this.mode = 2048;
            this.density = 1.0f;
            this.start = 0.0f;
            this.end = 1.0f;
        }
        
        FogState(final 1 p_i46481_1_) {
            this();
        }
    }
    
    static class PolygonOffsetState
    {
        public BooleanState polygonOffsetFill;
        public BooleanState polygonOffsetLine;
        public float factor;
        public float units;
        private static final String __OBFID = "CL_00002545";
        
        private PolygonOffsetState() {
            this.polygonOffsetFill = new BooleanState(32823);
            this.polygonOffsetLine = new BooleanState(10754);
            this.factor = 0.0f;
            this.units = 0.0f;
        }
        
        PolygonOffsetState(final 1 p_i46480_1_) {
            this();
        }
    }
    
    static class StencilFunc
    {
        public int func;
        public int field_179079_b;
        public int mask;
        private static final String __OBFID = "CL_00002544";
        
        private StencilFunc() {
            this.func = 519;
            this.field_179079_b = 0;
            this.mask = -1;
        }
        
        StencilFunc(final 1 p_i46479_1_) {
            this();
        }
    }
    
    static class StencilState
    {
        public StencilFunc func;
        public int mask;
        public int fail;
        public int zfail;
        public int zpass;
        private static final String __OBFID = "CL_00002543";
        
        private StencilState() {
            this.func = new StencilFunc(null);
            this.mask = -1;
            this.fail = 7680;
            this.zfail = 7680;
            this.zpass = 7680;
        }
        
        StencilState(final 1 p_i46478_1_) {
            this();
        }
    }
    
    public enum TexGen
    {
        S("S", 0), 
        T("T", 1), 
        R("R", 2), 
        Q("Q", 3);
        
        private static final TexGen[] $VALUES;
        private static final String __OBFID = "CL_00002542";
        
        private TexGen(final String p_i3_3_, final int p_i3_4_) {
        }
        
        static {
            $VALUES = new TexGen[] { TexGen.S, TexGen.T, TexGen.R, TexGen.Q };
        }
    }
    
    static class TexGenCoord
    {
        public BooleanState textureGen;
        public int coord;
        public int param;
        private static final String __OBFID = "CL_00002541";
        
        public TexGenCoord(final int coordIn, final int capabilityIn) {
            this.param = -1;
            this.coord = coordIn;
            this.textureGen = new BooleanState(capabilityIn);
        }
    }
    
    static class TexGenState
    {
        public TexGenCoord s;
        public TexGenCoord t;
        public TexGenCoord r;
        public TexGenCoord q;
        private static final String __OBFID = "CL_00002540";
        
        private TexGenState() {
            this.s = new TexGenCoord(8192, 3168);
            this.t = new TexGenCoord(8193, 3169);
            this.r = new TexGenCoord(8194, 3170);
            this.q = new TexGenCoord(8195, 3171);
        }
        
        TexGenState(final 1 p_i46477_1_) {
            this();
        }
    }
    
    public static class TextureState
    {
        public BooleanState texture2DState;
        public int textureName;
        private static final String __OBFID = "CL_00002539";
        
        private TextureState() {
            this.texture2DState = new BooleanState(3553);
            this.textureName = 0;
        }
        
        TextureState(final 1 p_i46476_1_) {
            this();
        }
    }
}
