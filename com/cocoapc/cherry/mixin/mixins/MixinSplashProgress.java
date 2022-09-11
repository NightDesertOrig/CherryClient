//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraftforge.fml.client.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraftforge.fml.common.*;
import java.util.concurrent.*;
import java.util.*;
import org.lwjgl.*;
import java.util.concurrent.locks.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;

@Mixin({ SplashProgress.class })
public class MixinSplashProgress
{
    private static volatile boolean pause;
    private static volatile boolean done;
    private static int backgroundColor;
    private static final int TIMING_FRAME_COUNT = 200;
    private static final int TIMING_FRAME_THRESHOLD = 1000000000;
    private static boolean isDisplayVSyncForced;
    
    @Inject(method = { "finish()V" }, at = { @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/client/SplashProgress$Texture;delete()V") }, cancellable = true, remap = false)
    private static void finish(final CallbackInfo ci) {
        ci.cancel();
    }
    
    @Inject(method = { "start()V" }, at = { @At(value = "INVOKE", target = "Ljava/lang/Thread;setUncaughtExceptionHandler(Ljava/lang/Thread$UncaughtExceptionHandler;)V") }, remap = false)
    private static void setUncaughtExceptionHandler(final CallbackInfo ci) {
        MixinSplashProgress.backgroundColor = ColorUtil.toRGBA(255, 255, 255);
        final Thread t = new Thread(new Runnable() {
            private long framecount;
            private long updateTiming;
            
            @Override
            public void run() {
                this.setGL();
                final SplashTexture background = new SplashTexture("/assets/minecraft/orangette/background/splashImage.png", null);
                final SplashTexture bar0 = new SplashTexture("/assets/minecraft/orangette/background/bar/bar0.png", null);
                final SplashTexture bar2 = new SplashTexture("/assets/minecraft/orangette/background/bar/bar1.png", null);
                final Semaphore mutex = AccessorSplashProgress.getMutex();
                GL11.glDisable(3553);
                while (!MixinSplashProgress.done) {
                    MixinSplashProgress.done = AccessorSplashProgress.getDone();
                    MixinSplashProgress.pause = AccessorSplashProgress.getPause();
                    ++this.framecount;
                    ProgressManager.ProgressBar first = null;
                    ProgressManager.ProgressBar penult = null;
                    ProgressManager.ProgressBar last = null;
                    final Iterator<ProgressManager.ProgressBar> i0 = (Iterator<ProgressManager.ProgressBar>)ProgressManager.barIterator();
                    while (i0.hasNext()) {
                        if (first == null) {
                            first = i0.next();
                        }
                        else {
                            penult = last;
                            last = i0.next();
                        }
                    }
                    GL11.glClear(16384);
                    int w = Display.getWidth();
                    int h = Display.getHeight();
                    GL11.glViewport(0, 0, w, h);
                    GL11.glMatrixMode(5889);
                    GL11.glLoadIdentity();
                    GL11.glOrtho((double)(320 - w / 2), (double)(320 + w / 2), (double)(240 + h / 2), (double)(240 - h / 2), -1.0, 1.0);
                    GL11.glMatrixMode(5888);
                    GL11.glLoadIdentity();
                    w /= 2;
                    h /= 2;
                    float p0 = w / 1400.0f;
                    float p2 = h / 788.0f;
                    float p3 = Math.max(p0, p2);
                    drawTexture(background, 0.0f, 0.0f, 1400.0f * p3, 788.0f * p3, new Color(255, 255, 255));
                    final float barScale = 0.12f;
                    p0 = w / 3565.0f;
                    p2 = h / 449.0f;
                    p3 = Math.max(p0, p2);
                    drawTexture(bar0, 0.0f, h - 449.0f * barScale - 20.0f, 3565.0f * barScale * p3, 449.0f * barScale * p3, new Color(255, 255, 255));
                    float progress = 0.0f;
                    try {
                        progress = first.getStep() * 1.0f / first.getSteps();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    scissor(0.0f, 0.0f, 3565.0f * barScale * p3 * progress, 9999.0f, 2.0f);
                    GL11.glEnable(3089);
                    drawTexture(bar2, 0.0f, h - 449.0f * barScale - 20.0f, 3565.0f * barScale * p3, 449.0f * barScale * p3, new Color(255, 255, 255));
                    GL11.glDisable(3089);
                    mutex.acquireUninterruptibly();
                    Display.update();
                    mutex.release();
                    mutex.acquireUninterruptibly();
                    final long updateStart = System.nanoTime();
                    final long dur = System.nanoTime() - updateStart;
                    if (this.framecount < 200L) {
                        this.updateTiming += dur;
                    }
                    mutex.release();
                    if (MixinSplashProgress.pause) {
                        this.clearGL();
                        this.setGL();
                    }
                    if (this.framecount >= 200L && this.updateTiming > 1000000000L) {
                        if (!MixinSplashProgress.isDisplayVSyncForced) {
                            MixinSplashProgress.isDisplayVSyncForced = true;
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
                background.delete();
                bar0.delete();
                bar2.delete();
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
            
            private void setGL() {
                final Lock lock = AccessorSplashProgress.getLock();
                lock.lock();
                try {
                    Display.getDrawable().makeCurrent();
                }
                catch (LWJGLException e) {
                    FMLLog.log.error("Error setting GL context:", (Throwable)e);
                    throw new RuntimeException((Throwable)e);
                }
                GL11.glClearColor((MixinSplashProgress.backgroundColor >> 16 & 0xFF) / 255.0f, (MixinSplashProgress.backgroundColor >> 8 & 0xFF) / 255.0f, (MixinSplashProgress.backgroundColor & 0xFF) / 255.0f, 1.0f);
                GL11.glDisable(2896);
                GL11.glDisable(2929);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
            }
            
            private void clearGL() {
                final Lock lock = AccessorSplashProgress.getLock();
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
                catch (LWJGLException e) {
                    FMLLog.log.error("Error releasing GL context:", (Throwable)e);
                    throw new RuntimeException((Throwable)e);
                }
                finally {
                    lock.unlock();
                }
            }
        });
        AccessorSplashProgress.setThread(t);
    }
    
    private static void drawTexture(final SplashTexture image, final float posX, final float posY, final float width, final float height, final Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(3553);
        GlStateManager.enableBlend();
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        image.bind();
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        image.texCoord(0, 0.0f, 0.0f);
        GL11.glVertex2f(320.0f - width, 240.0f - height);
        image.texCoord(0, 0.0f, 1.0f);
        GL11.glVertex2f(320.0f - width, 240.0f + height);
        image.texCoord(0, 1.0f, 1.0f);
        GL11.glVertex2f(320.0f + width, 240.0f + height);
        image.texCoord(0, 1.0f, 0.0f);
        GL11.glVertex2f(320.0f + width, 240.0f - height);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    private static void drawRect(final float posX, final float posY, final float width, final float height, final Color color) {
        GL11.glPushMatrix();
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glTranslatef(posX, posY, 0.0f);
        GL11.glBegin(7);
        GL11.glVertex2f(320.0f - width, 240.0f - height);
        GL11.glVertex2f(320.0f - width, 240.0f + height);
        GL11.glVertex2f(320.0f + width, 240.0f + height);
        GL11.glVertex2f(320.0f + width, 240.0f - height);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    private static void scissor(final float x, final float y, final float width, final float height, final float guiScale) {
        final float p = Display.getWidth() / 854.0f;
        GL11.glScissor(0, (int)(Display.getHeight() - (y + height) * p), (int)((width + x) * p * guiScale), (int)(height * p));
    }
    
    static {
        MixinSplashProgress.pause = false;
        MixinSplashProgress.done = false;
        MixinSplashProgress.isDisplayVSyncForced = false;
    }
}
