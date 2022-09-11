//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.mixin;

import org.spongepowered.asm.mixin.*;
import java.util.concurrent.*;
import java.nio.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import java.nio.charset.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.launchwrapper.*;
import net.minecraft.util.*;
import net.minecraft.crash.*;
import org.lwjgl.opengl.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.*;
import org.apache.commons.lang3.*;
import java.io.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.client.renderer.texture.*;
import org.lwjgl.util.glu.*;
import java.util.concurrent.locks.*;
import net.minecraftforge.fml.common.asm.*;
import org.lwjgl.*;
import net.minecraft.client.gui.*;
import javax.annotation.*;
import net.minecraft.client.resources.data.*;
import net.minecraft.client.resources.*;
import javax.imageio.*;
import java.awt.image.*;
import org.apache.commons.io.*;
import javax.imageio.stream.*;

@Mixin({ net.minecraftforge.fml.client.SplashProgress.class })
public class SplashProgress
{
    private static Drawable d;
    private static volatile boolean pause;
    private static volatile boolean done;
    private static Thread thread;
    private static volatile Throwable threadError;
    private static int angle;
    private static final Lock lock;
    public static SplashFontRenderer fontRenderer;
    private static final IResourcePack mcPack;
    private static final IResourcePack fmlPack;
    private static IResourcePack miscPack;
    private static Texture fontTexture;
    private static Texture logoTexture;
    private static Texture forgeTexture;
    private static Properties config;
    private static boolean enabled;
    private static boolean rotate;
    private static int logoOffset;
    private static int backgroundColor;
    private static int fontColor;
    private static int barBorderColor;
    private static int barColor;
    private static int barBackgroundColor;
    private static boolean showMemory;
    private static int memoryGoodColor;
    private static int memoryWarnColor;
    private static int memoryLowColor;
    private static float memoryColorPercent;
    private static long memoryColorChangeTime;
    static boolean isDisplayVSyncForced;
    private static final int TIMING_FRAME_COUNT = 200;
    private static final int TIMING_FRAME_THRESHOLD = 1000000000;
    static final Semaphore mutex;
    private static int max_texture_size;
    private static final IntBuffer buf;
    
    private static String getString(final String name, final String def) {
        final String value = SplashProgress.config.getProperty(name, def);
        SplashProgress.config.setProperty(name, value);
        return value;
    }
    
    private static boolean getBool(final String name, final boolean def) {
        return Boolean.parseBoolean(getString(name, Boolean.toString(def)));
    }
    
    private static int getInt(final String name, final int def) {
        return Integer.decode(getString(name, Integer.toString(def)));
    }
    
    private static int getHex(final String name, final int def) {
        return Integer.decode(getString(name, "0x" + Integer.toString(def, 16).toUpperCase()));
    }
    
    @Inject(method = { "start()V" }, at = { @At(value = "INVOKE", target = "Ljava/lang/Thread;setUncaughtExceptionHandler(Ljava/lang/Thread$UncaughtExceptionHandler;)V") }, remap = false)
    public static void starts(final CallbackInfo ci) {
        final File configFile = new File(Minecraft.getMinecraft().gameDir, "config/splash.properties");
        final File parent = configFile.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        SplashProgress.config = new Properties();
        try (final Reader r = new InputStreamReader(new FileInputStream(configFile), StandardCharsets.UTF_8)) {
            SplashProgress.config.load(r);
        }
        catch (IOException var11) {
            FMLLog.log.info("Could not load splash.properties, will create a default one");
        }
        final boolean defaultEnabled = true;
        SplashProgress.enabled = (getBool("enabled", defaultEnabled) && (!FMLClientHandler.instance().hasOptifine() || Launch.blackboard.containsKey("optifine.ForgeSplashCompatible")));
        SplashProgress.rotate = getBool("rotate", false);
        SplashProgress.showMemory = getBool("showMemory", true);
        SplashProgress.logoOffset = getInt("logoOffset", 0);
        SplashProgress.backgroundColor = getHex("background", 16777215);
        SplashProgress.fontColor = getHex("font", 0);
        SplashProgress.barBorderColor = getHex("barBorder", 12632256);
        SplashProgress.barColor = getHex("bar", 13319477);
        SplashProgress.barBackgroundColor = getHex("barBackground", 16777215);
        SplashProgress.memoryGoodColor = getHex("memoryGood", 7916340);
        SplashProgress.memoryWarnColor = getHex("memoryWarn", 15132746);
        SplashProgress.memoryLowColor = getHex("memoryLow", 14954287);
        final ResourceLocation fontLoc = new ResourceLocation(getString("fontTexture", "textures/back.png"));
        final ResourceLocation logoLoc = new ResourceLocation("textures/back.png");
        final ResourceLocation forgeLoc = new ResourceLocation(getString("forgeTexture", "textures/back.png"));
        final ResourceLocation forgeFallbackLoc = new ResourceLocation("textures/back.png");
        final File miscPackFile = new File(Minecraft.getMinecraft().gameDir, getString("resourcePackPath", "resources"));
        try (final Writer w = new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8)) {
            SplashProgress.config.store(w, "Splash screen properties");
        }
        catch (IOException var9) {
            FMLLog.log.error("Could not save the splash.properties file", (Throwable)var9);
        }
        SplashProgress.miscPack = createResourcePack(miscPackFile);
        if (SplashProgress.enabled) {
            FMLCommonHandler.instance().registerCrashCallable((ICrashCallable)new ICrashCallable() {
                public String call() throws Exception {
                    return "' Vendor: '" + GL11.glGetString(7936) + "' Version: '" + GL11.glGetString(7938) + "' Renderer: '" + GL11.glGetString(7937) + "'";
                }
                
                public String getLabel() {
                    return "GL info";
                }
            });
            final CrashReport report = CrashReport.makeCrashReport(new Throwable(), "Loading screen debug info");
            final StringBuilder systemDetailsBuilder = new StringBuilder();
            report.getCategory().appendToStringBuilder(systemDetailsBuilder);
            FMLLog.log.info(systemDetailsBuilder.toString());
            try {
                SplashProgress.d = (Drawable)new SharedDrawable(Display.getDrawable());
                Display.getDrawable().releaseContext();
                SplashProgress.d.makeCurrent();
            }
            catch (LWJGLException var10) {
                FMLLog.log.error("Error starting SplashProgress:", (Throwable)var10);
                disableSplash((Exception)var10);
            }
            getMaxTextureSize();
            (SplashProgress.thread = new Thread(new Runnable() {
                private final int barWidth = 400;
                private final int barHeight = 20;
                private final int textHeight2 = 20;
                private final int barOffset = 55;
                private long updateTiming;
                private long framecount;
                
                @Override
                public void run() {
                    this.setGL();
                    SplashProgress.fontTexture = new Texture(fontLoc, null);
                    SplashProgress.logoTexture = new Texture(logoLoc, null, false);
                    SplashProgress.forgeTexture = new Texture(forgeLoc, forgeFallbackLoc);
                    GL11.glEnable(3553);
                    SplashProgress.fontRenderer = new SplashFontRenderer();
                    GL11.glDisable(3553);
                    while (!SplashProgress.done) {
                        ++this.framecount;
                        ProgressManager.ProgressBar first = null;
                        ProgressManager.ProgressBar penult = null;
                        ProgressManager.ProgressBar last = null;
                        final Iterator<ProgressManager.ProgressBar> i = (Iterator<ProgressManager.ProgressBar>)ProgressManager.barIterator();
                        while (i.hasNext()) {
                            if (first == null) {
                                first = i.next();
                            }
                            else {
                                penult = last;
                                last = i.next();
                            }
                        }
                        GL11.glClear(16384);
                        final int w = Display.getWidth();
                        final int h = Display.getHeight();
                        GL11.glViewport(0, 0, w, h);
                        GL11.glMatrixMode(5889);
                        GL11.glLoadIdentity();
                        GL11.glOrtho((double)(320 - w / 2), (double)(320 + w / 2), (double)(240 + h / 2), (double)(240 - h / 2), -1.0, 1.0);
                        GL11.glMatrixMode(5888);
                        GL11.glLoadIdentity();
                        this.setColor(SplashProgress.backgroundColor);
                        GL11.glEnable(3553);
                        SplashProgress.logoTexture.bind();
                        GL11.glBegin(7);
                        SplashProgress.logoTexture.texCoord(0, 0.0f, 0.0f);
                        GL11.glVertex2f(64.0f, -16.0f);
                        SplashProgress.logoTexture.texCoord(0, 0.0f, 1.0f);
                        GL11.glVertex2f(64.0f, 496.0f);
                        SplashProgress.logoTexture.texCoord(0, 1.0f, 1.0f);
                        GL11.glVertex2f(576.0f, 496.0f);
                        SplashProgress.logoTexture.texCoord(0, 1.0f, 0.0f);
                        GL11.glVertex2f(576.0f, -16.0f);
                        GL11.glEnd();
                        GL11.glDisable(3553);
                        if (SplashProgress.showMemory) {
                            GL11.glPushMatrix();
                            GL11.glTranslatef(120.0f, 20.0f, 0.0f);
                            final CallbackInfo CallbackInfo = null;
                            this.drawMemory(CallbackInfo);
                            GL11.glPopMatrix();
                        }
                        if (first != null) {
                            GL11.glPushMatrix();
                            GL11.glTranslatef(120.0f, 310.0f, 0.0f);
                            this.drawBar(first);
                            if (penult != null) {
                                GL11.glTranslatef(0.0f, 55.0f, 0.0f);
                                this.drawBar(penult);
                            }
                            if (last != null) {
                                GL11.glTranslatef(0.0f, 55.0f, 0.0f);
                                this.drawBar(last);
                            }
                            GL11.glPopMatrix();
                        }
                        ++SplashProgress.angle;
                        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                        final float fw = SplashProgress.forgeTexture.getWidth() / 2.0f;
                        final float fh = SplashProgress.forgeTexture.getHeight() / 2.0f;
                        if (SplashProgress.rotate) {
                            final float sh = Math.max(fw, fh);
                            GL11.glTranslatef(320 + w / 2 - sh - SplashProgress.logoOffset, 240 + h / 2 - sh - SplashProgress.logoOffset, 0.0f);
                            GL11.glRotatef((float)SplashProgress.angle, 0.0f, 0.0f, 1.0f);
                        }
                        else {
                            GL11.glTranslatef(320 + w / 2 - fw - SplashProgress.logoOffset, 240 + h / 2 - fh - SplashProgress.logoOffset, 0.0f);
                        }
                        final int f = SplashProgress.angle / 5 % SplashProgress.forgeTexture.getFrames();
                        GL11.glEnable(3553);
                        SplashProgress.forgeTexture.bind();
                        GL11.glBegin(7);
                        SplashProgress.forgeTexture.texCoord(f, 0.0f, 0.0f);
                        GL11.glVertex2f(-fw, -fh);
                        SplashProgress.forgeTexture.texCoord(f, 0.0f, 1.0f);
                        GL11.glVertex2f(-fw, fh);
                        SplashProgress.forgeTexture.texCoord(f, 1.0f, 1.0f);
                        GL11.glVertex2f(fw, fh);
                        SplashProgress.forgeTexture.texCoord(f, 1.0f, 0.0f);
                        GL11.glVertex2f(fw, -fh);
                        GL11.glEnd();
                        GL11.glDisable(3553);
                        SplashProgress.mutex.acquireUninterruptibly();
                        final long updateStart = System.nanoTime();
                        Display.update();
                        final long dur = System.nanoTime() - updateStart;
                        if (this.framecount < 200L) {
                            this.updateTiming += dur;
                        }
                        SplashProgress.mutex.release();
                        if (SplashProgress.pause) {
                            this.clearGL();
                            this.setGL();
                        }
                        if (this.framecount >= 200L && this.updateTiming > 1000000000L) {
                            if (!SplashProgress.isDisplayVSyncForced) {
                                SplashProgress.isDisplayVSyncForced = true;
                                FMLLog.log.info("Using alternative sync timing : {} frames of Display.update took {} nanos", (Object)200, (Object)this.updateTiming);
                            }
                            try {
                                Thread.sleep(16L);
                            }
                            catch (InterruptedException ex) {}
                        }
                        else {
                            if (this.framecount == 200L) {
                                FMLLog.log.info("Using sync timing. {} frames of Display.update took {} nanos", (Object)200, (Object)this.updateTiming);
                            }
                            Display.sync(100);
                        }
                    }
                    this.clearGL();
                }
                
                private void setColor(final int color) {
                    GL11.glColor3ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color & 0xFF));
                }
                
                private void drawBox(final int w, final int h) {
                    GL11.glBegin(7);
                    GL11.glVertex2f(0.0f, 0.0f);
                    GL11.glVertex2f(0.0f, (float)h);
                    GL11.glVertex2f((float)w, (float)h);
                    GL11.glVertex2f((float)w, 0.0f);
                    GL11.glEnd();
                }
                
                private void drawBar(final ProgressManager.ProgressBar b) {
                    GL11.glPushMatrix();
                    this.setColor(SplashProgress.fontColor);
                    GL11.glScalef(2.0f, 2.0f, 1.0f);
                    GL11.glEnable(3553);
                    SplashProgress.fontRenderer.drawString(b.getTitle() + " - " + b.getMessage(), 0, 0, 0);
                    GL11.glDisable(3553);
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 20.0f, 0.0f);
                    this.setColor(SplashProgress.barBorderColor);
                    this.drawBox(400, 20);
                    this.setColor(SplashProgress.barBackgroundColor);
                    GL11.glTranslatef(1.0f, 1.0f, 0.0f);
                    this.drawBox(398, 18);
                    this.setColor(SplashProgress.barColor);
                    this.drawBox(398 * (b.getStep() + 1) / (b.getSteps() + 1), 18);
                    final String progress = "" + b.getStep() + "/" + b.getSteps();
                    GL11.glTranslatef(199.0f - SplashProgress.fontRenderer.getStringWidth(progress), 2.0f, 0.0f);
                    this.setColor(SplashProgress.fontColor);
                    GL11.glScalef(2.0f, 2.0f, 1.0f);
                    GL11.glEnable(3553);
                    SplashProgress.fontRenderer.drawString(progress, 0, 0, 0);
                    GL11.glPopMatrix();
                }
                
                @Inject(method = { "drawMemoryBar()V" }, at = { @At(value = "INVOKE", target = "Ljava/lang/Thread;setUncaughtExceptionHandler(Ljava/lang/Thread$UncaughtExceptionHandler;)V") }, remap = false)
                private void drawMemory(final CallbackInfo ci) {
                    final int maxMemory = bytesToMb(Runtime.getRuntime().maxMemory());
                    final int totalMemory = bytesToMb(Runtime.getRuntime().totalMemory());
                    final int freeMemory = bytesToMb(Runtime.getRuntime().freeMemory());
                    final int usedMemory = totalMemory - freeMemory;
                    final float usedMemoryPercent = usedMemory / (float)maxMemory;
                    GL11.glPushMatrix();
                    this.setColor(SplashProgress.fontColor);
                    GL11.glScalef(2.0f, 2.0f, 1.0f);
                    GL11.glEnable(3553);
                    SplashProgress.fontRenderer.drawString("Memory Used / Total", 0, 0, 0);
                    GL11.glDisable(3553);
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glTranslatef(0.0f, 20.0f, 0.0f);
                    this.setColor(SplashProgress.barBorderColor);
                    this.drawBox(400, 20);
                    this.setColor(SplashProgress.barBackgroundColor);
                    GL11.glTranslatef(1.0f, 1.0f, 0.0f);
                    this.drawBox(398, 18);
                    final long time = System.currentTimeMillis();
                    if (usedMemoryPercent > SplashProgress.memoryColorPercent || time - SplashProgress.memoryColorChangeTime > 1000L) {
                        SplashProgress.memoryColorChangeTime = time;
                        SplashProgress.memoryColorPercent = usedMemoryPercent;
                    }
                    int memoryBarColor;
                    if (SplashProgress.memoryColorPercent < 0.75f) {
                        memoryBarColor = SplashProgress.memoryGoodColor;
                    }
                    else if (SplashProgress.memoryColorPercent < 0.85f) {
                        memoryBarColor = SplashProgress.memoryWarnColor;
                    }
                    else {
                        memoryBarColor = SplashProgress.memoryLowColor;
                    }
                    this.setColor(SplashProgress.memoryLowColor);
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)(398 * totalMemory / maxMemory - 2), 0.0f, 0.0f);
                    this.drawBox(2, 18);
                    GL11.glPopMatrix();
                    this.setColor(memoryBarColor);
                    this.drawBox(398 * usedMemory / maxMemory, 18);
                    final String progress = this.getMemoryString(usedMemory) + " / " + this.getMemoryString(maxMemory);
                    GL11.glTranslatef(199.0f - SplashProgress.fontRenderer.getStringWidth(progress), 2.0f, 0.0f);
                    this.setColor(SplashProgress.fontColor);
                    GL11.glScalef(2.0f, 2.0f, 1.0f);
                    GL11.glEnable(3553);
                    SplashProgress.fontRenderer.drawString(progress, 0, 0, 0);
                    GL11.glPopMatrix();
                }
                
                private String getMemoryString(final int memory) {
                    return StringUtils.leftPad(Integer.toString(memory), 4, ' ') + " MB";
                }
                
                private void setGL() {
                    SplashProgress.lock.lock();
                    try {
                        Display.getDrawable().makeCurrent();
                    }
                    catch (LWJGLException var2) {
                        FMLLog.log.error("Error setting GL context:", (Throwable)var2);
                        throw new RuntimeException((Throwable)var2);
                    }
                    GL11.glClearColor((SplashProgress.backgroundColor >> 16 & 0xFF) / 255.0f, (SplashProgress.backgroundColor >> 8 & 0xFF) / 255.0f, (SplashProgress.backgroundColor & 0xFF) / 255.0f, 1.0f);
                    GL11.glDisable(2896);
                    GL11.glDisable(2929);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                }
                
                private void clearGL() {
                    final Minecraft mc = Minecraft.getMinecraft();
                    mc.displayWidth = Display.getWidth();
                    mc.displayHeight = Display.getHeight();
                    mc.resize(mc.displayWidth, mc.displayHeight);
                    GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glEnable(2929);
                    GL11.glDepthFunc(515);
                    GL11.glEnable(3008);
                    GL11.glAlphaFunc(516, 0.1f);
                    try {
                        Display.getDrawable().releaseContext();
                    }
                    catch (LWJGLException var6) {
                        FMLLog.log.error("Error releasing GL context:", (Throwable)var6);
                        throw new RuntimeException((Throwable)var6);
                    }
                    finally {
                        SplashProgress.lock.unlock();
                    }
                }
            })).setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(final Thread t, final Throwable e) {
                    FMLLog.log.error("Splash thread Exception", e);
                    SplashProgress.threadError = e;
                }
            });
            SplashProgress.thread.start();
            checkThreadState();
        }
    }
    
    public static int getMaxTextureSize() {
        if (SplashProgress.max_texture_size != -1) {
            return SplashProgress.max_texture_size;
        }
        for (int i = 16384; i > 0; i >>= 1) {
            GlStateManager.glTexImage2D(32868, 0, 6408, i, i, 0, 6408, 5121, (IntBuffer)null);
            if (GlStateManager.glGetTexLevelParameteri(32868, 0, 4096) != 0) {
                return SplashProgress.max_texture_size = i;
            }
        }
        return -1;
    }
    
    private static void checkThreadState() {
        if (SplashProgress.thread.getState() == Thread.State.TERMINATED || SplashProgress.threadError != null) {
            throw new IllegalStateException("Splash thread", SplashProgress.threadError);
        }
    }
    
    @Deprecated
    public static void pause() {
        if (SplashProgress.enabled) {
            checkThreadState();
            SplashProgress.pause = true;
            SplashProgress.lock.lock();
            try {
                SplashProgress.d.releaseContext();
                Display.getDrawable().makeCurrent();
            }
            catch (LWJGLException var1) {
                FMLLog.log.error("Error setting GL context:", (Throwable)var1);
                throw new RuntimeException((Throwable)var1);
            }
        }
    }
    
    @Deprecated
    public static void resume() {
        if (SplashProgress.enabled) {
            checkThreadState();
            SplashProgress.pause = false;
            try {
                Display.getDrawable().releaseContext();
                SplashProgress.d.makeCurrent();
            }
            catch (LWJGLException var1) {
                FMLLog.log.error("Error releasing GL context:", (Throwable)var1);
                throw new RuntimeException((Throwable)var1);
            }
            SplashProgress.lock.unlock();
        }
    }
    
    public static void finish() {
        if (SplashProgress.enabled) {
            try {
                checkThreadState();
                SplashProgress.done = true;
                SplashProgress.thread.join();
                GL11.glFlush();
                SplashProgress.d.releaseContext();
                Display.getDrawable().makeCurrent();
                SplashProgress.fontTexture.delete();
                SplashProgress.logoTexture.delete();
                SplashProgress.forgeTexture.delete();
            }
            catch (Exception var1) {
                FMLLog.log.error("Error finishing SplashProgress:", (Throwable)var1);
                disableSplash(var1);
            }
        }
    }
    
    private static boolean disableSplash(final Exception e) {
        if (disableSplash()) {
            throw new EnhancedRuntimeException(e) {
                protected void printStackTrace(final EnhancedRuntimeException.WrappedPrintStream stream) {
                    stream.println("SplashProgress has detected a error loading Minecraft.");
                    stream.println("This can sometimes be caused by bad video drivers.");
                    stream.println("We have automatically disabled the new Splash Screen in config/splash.properties.");
                    stream.println("Try reloading minecraft before reporting any errors.");
                }
            };
        }
        throw new EnhancedRuntimeException(e) {
            protected void printStackTrace(final EnhancedRuntimeException.WrappedPrintStream stream) {
                stream.println("SplashProgress has detected a error loading Minecraft.");
                stream.println("This can sometimes be caused by bad video drivers.");
                stream.println("Please try disabling the new Splash Screen in config/splash.properties.");
                stream.println("After doing so, try reloading minecraft before reporting any errors.");
            }
        };
    }
    
    private static boolean disableSplash() {
        final File configFile = new File(Minecraft.getMinecraft().gameDir, "config/splash.properties");
        final File parent = configFile.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        SplashProgress.enabled = false;
        SplashProgress.config.setProperty("enabled", "false");
        try {
            try (final Writer w = new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8)) {
                SplashProgress.config.store(w, "Splash screen properties");
            }
            return true;
        }
        catch (IOException var6) {
            FMLLog.log.error("Could not save the splash.properties file", (Throwable)var6);
            return false;
        }
    }
    
    private static IResourcePack createResourcePack(final File file) {
        return (IResourcePack)(file.isDirectory() ? new FolderResourcePack(file) : new FileResourcePack(file));
    }
    
    public static void drawVanillaScreen(final TextureManager renderEngine) throws LWJGLException {
        if (!SplashProgress.enabled) {
            Minecraft.getMinecraft().drawSplashScreen(renderEngine);
        }
    }
    
    public static void clearVanillaResources(final TextureManager renderEngine, final ResourceLocation mojangLogo) {
        if (!SplashProgress.enabled) {
            renderEngine.deleteTexture(mojangLogo);
        }
    }
    
    public static void checkGLError(final String where) {
        final int err = GL11.glGetError();
        if (err != 0) {
            throw new IllegalStateException(where + ": " + GLU.gluErrorString(err));
        }
    }
    
    private static InputStream open(final ResourceLocation loc, @Nullable final ResourceLocation fallback, final boolean allowResourcePack) throws IOException {
        if (!allowResourcePack) {
            return SplashProgress.mcPack.getInputStream(loc);
        }
        if (SplashProgress.miscPack.resourceExists(loc)) {
            return SplashProgress.miscPack.getInputStream(loc);
        }
        if (SplashProgress.fmlPack.resourceExists(loc)) {
            return SplashProgress.fmlPack.getInputStream(loc);
        }
        return (!SplashProgress.mcPack.resourceExists(loc) && fallback != null) ? open(fallback, null, true) : SplashProgress.mcPack.getInputStream(loc);
    }
    
    private static int bytesToMb(final long bytes) {
        return (int)(bytes / 1024L / 1024L);
    }
    
    static {
        SplashProgress.pause = false;
        SplashProgress.done = false;
        SplashProgress.angle = 0;
        lock = new ReentrantLock(true);
        mcPack = (IResourcePack)Minecraft.getMinecraft().defaultResourcePack;
        fmlPack = createResourcePack(FMLSanityChecker.fmlLocation);
        SplashProgress.isDisplayVSyncForced = false;
        mutex = new Semaphore(1);
        SplashProgress.max_texture_size = -1;
        buf = BufferUtils.createIntBuffer(4194304);
    }
    
    private static class SplashFontRenderer extends FontRenderer
    {
        public SplashFontRenderer() {
            super(Minecraft.getMinecraft().gameSettings, SplashProgress.fontTexture.getLocation(), (TextureManager)null, false);
            super.onResourceManagerReload((IResourceManager)null);
        }
        
        protected void bindTexture(@Nonnull final ResourceLocation location) {
            if (location != this.locationFontTexture) {
                throw new IllegalArgumentException();
            }
            SplashProgress.fontTexture.bind();
        }
        
        @Nonnull
        protected IResource getResource(@Nonnull final ResourceLocation location) throws IOException {
            final DefaultResourcePack pack = Minecraft.getMinecraft().defaultResourcePack;
            return (IResource)new SimpleResource(pack.getPackName(), location, pack.getInputStream(location), (InputStream)null, (MetadataSerializer)null);
        }
    }
    
    private static class Texture
    {
        private final ResourceLocation location;
        private final int name;
        private final int width;
        private final int height;
        private final int frames;
        private final int size;
        
        public Texture(final ResourceLocation location, @Nullable final ResourceLocation fallback) {
            this(location, fallback, true);
        }
        
        public Texture(final ResourceLocation location, @Nullable final ResourceLocation fallback, final boolean allowRP) {
            InputStream s = null;
            try {
                this.location = location;
                s = open(location, fallback, allowRP);
                final ImageInputStream stream = ImageIO.createImageInputStream(s);
                final Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);
                if (!readers.hasNext()) {
                    throw new IOException("No suitable reader found for image" + location);
                }
                final ImageReader reader = readers.next();
                reader.setInput(stream);
                int frames = reader.getNumImages(true);
                BufferedImage[] images = new BufferedImage[frames];
                for (int height = 0; height < frames; ++height) {
                    images[height] = reader.read(height);
                }
                reader.dispose();
                this.width = images[0].getWidth();
                int height = images[0].getHeight();
                if (height > this.width && height % this.width == 0) {
                    frames = height / this.width;
                    final BufferedImage original = images[0];
                    height = this.width;
                    images = new BufferedImage[frames];
                    for (int i = 0; i < frames; ++i) {
                        images[i] = original.getSubimage(0, i * height, this.width, height);
                    }
                }
                this.frames = frames;
                this.height = height;
                int size;
                for (size = 1; size / this.width * (size / height) < frames; size *= 2) {}
                this.size = size;
                GL11.glEnable(3553);
                final Class var25 = SplashProgress.class;
                synchronized (SplashProgress.class) {
                    GL11.glBindTexture(3553, this.name = GL11.glGenTextures());
                }
                GL11.glTexParameteri(3553, 10241, 9728);
                GL11.glTexParameteri(3553, 10240, 9728);
                GL11.glTexImage2D(3553, 0, 6408, size, size, 0, 32993, 33639, (IntBuffer)null);
                SplashProgress.checkGLError("Texture creation");
                for (int i = 0; i * (size / this.width) < frames; ++i) {
                    for (int j = 0; i * (size / this.width) + j < frames && j < size / this.width; ++j) {
                        SplashProgress.buf.clear();
                        final BufferedImage image = images[i * (size / this.width) + j];
                        for (int k = 0; k < height; ++k) {
                            for (int l = 0; l < this.width; ++l) {
                                SplashProgress.buf.put(image.getRGB(l, k));
                            }
                        }
                        SplashProgress.buf.position(0).limit(this.width * height);
                        GL11.glTexSubImage2D(3553, 0, j * this.width, i * height, this.width, height, 32993, 33639, SplashProgress.buf);
                        SplashProgress.checkGLError("Texture uploading");
                    }
                }
                GL11.glBindTexture(3553, 0);
                GL11.glDisable(3553);
            }
            catch (IOException var26) {
                FMLLog.log.error("Error reading texture from file: {}", (Object)location, (Object)var26);
                throw new RuntimeException(var26);
            }
            finally {
                IOUtils.closeQuietly(s);
            }
        }
        
        public ResourceLocation getLocation() {
            return this.location;
        }
        
        public int getName() {
            return this.name;
        }
        
        public int getWidth() {
            return this.width;
        }
        
        public int getHeight() {
            return this.height;
        }
        
        public int getFrames() {
            return this.frames;
        }
        
        public int getSize() {
            return this.size;
        }
        
        public void bind() {
            GL11.glBindTexture(3553, this.name);
        }
        
        public void delete() {
            GL11.glDeleteTextures(this.name);
        }
        
        public float getU(final int frame, final float u) {
            return this.width * (frame % (this.size / this.width) + u) / this.size;
        }
        
        public float getV(final int frame, final float v) {
            return this.height * (frame / (this.size / this.width) + v) / this.size;
        }
        
        public void texCoord(final int frame, final float u, final float v) {
            GL11.glTexCoord2f(this.getU(frame, u), this.getV(frame, v));
        }
    }
}
