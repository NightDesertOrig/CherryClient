//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.custom;

import net.minecraft.client.*;
import net.minecraft.util.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.client.gui.*;

public class GuiCustomMainScreen extends GuiScreen
{
    private int y;
    private int x;
    public static Minecraft mc;
    public static ParticleGenerator particleGenerator;
    private final ResourceLocation resourceLocation;
    
    public GuiCustomMainScreen() {
        this.resourceLocation = new ResourceLocation("textures/images.png");
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        System.out.println(mouseX + " " + mouseY);
        GuiCustomMainScreen.mc.getTextureManager().bindTexture(this.resourceLocation);
        RenderUtil.drawTexture(0.0f, 0.0f, (float)this.width, (float)this.height);
        Cherry.m_font.fontRenderer3.drawString("CHERRY CLIENT V5.0", 0.0f, 4.0f, ColorUtil.toRGBA(0, 0, 255, 100));
        RenderUtil.drawRect(0.0f, 0.0f, 200.0f, 2000.0f, ColorUtil.toRGBA(0, 0, 0, 70));
        if (mouseX > 0 && mouseX < 144 && mouseY > 100 && mouseY < 145) {
            RenderUtil.drawRect(0.0f, 100.0f, 145.0f, 145.0f, ColorUtil.toRGBA(0, 0, 0, 60));
            Cherry.m_font.fontRenderer4.drawString("b", 5.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("SinglePlayer", 30.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 150.0f, 145.0f, 195.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("bb", 5.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("MultiPlayer", 40.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 200.0f, 145.0f, 245.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("d", 5.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Mods", 40.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 250.0f, 145.0f, 295.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("c", 5.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Settings", 40.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 300.0f, 145.0f, 345.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("e", 5.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Exit", 40.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 150 && mouseY < 195) {
            RenderUtil.drawRect(0.0f, 100.0f, 145.0f, 145.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("b", 5.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("SinglePlayer", 30.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 150.0f, 145.0f, 195.0f, ColorUtil.toRGBA(0, 0, 0, 60));
            Cherry.m_font.fontRenderer4.drawString("bb", 5.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("MultiPlayer", 40.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 200.0f, 145.0f, 245.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("d", 5.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Mods", 40.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 250.0f, 145.0f, 295.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("c", 5.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Settings", 40.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 300.0f, 145.0f, 345.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("e", 5.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Exit", 40.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 200 && mouseY < 245) {
            RenderUtil.drawRect(0.0f, 100.0f, 145.0f, 145.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("b", 5.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("SinglePlayer", 30.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 150.0f, 145.0f, 195.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("bb", 5.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("MultiPlayer", 40.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 200.0f, 145.0f, 245.0f, ColorUtil.toRGBA(0, 0, 0, 60));
            Cherry.m_font.fontRenderer4.drawString("d", 5.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Mods", 40.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 250.0f, 145.0f, 295.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("c", 5.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Settings", 40.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 300.0f, 145.0f, 345.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("e", 5.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Exit", 40.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 250 && mouseY < 295) {
            RenderUtil.drawRect(0.0f, 100.0f, 145.0f, 145.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("b", 5.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("SinglePlayer", 30.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 150.0f, 145.0f, 195.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("bb", 5.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("MultiPlayer", 40.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 200.0f, 145.0f, 245.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("d", 5.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Mods", 40.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 250.0f, 145.0f, 295.0f, ColorUtil.toRGBA(0, 0, 0, 60));
            Cherry.m_font.fontRenderer4.drawString("c", 5.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Settings", 40.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 300.0f, 145.0f, 345.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("e", 5.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Exit", 40.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 300 && mouseY < 345) {
            RenderUtil.drawRect(0.0f, 100.0f, 145.0f, 145.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("b", 5.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("SinglePlayer", 30.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 150.0f, 145.0f, 195.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("bb", 5.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("MultiPlayer", 40.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 200.0f, 145.0f, 245.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("d", 5.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Mods", 40.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 250.0f, 145.0f, 295.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("c", 5.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Settings", 40.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 300.0f, 145.0f, 345.0f, ColorUtil.toRGBA(0, 0, 0, 60));
            Cherry.m_font.fontRenderer4.drawString("e", 5.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Exit", 40.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        }
        else {
            RenderUtil.drawRect(0.0f, 100.0f, 145.0f, 145.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("b", 5.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("SinglePlayer", 30.0f, 115.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 150.0f, 145.0f, 195.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("bb", 5.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("MultiPlayer", 40.0f, 165.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 200.0f, 145.0f, 245.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("d", 5.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Mods", 40.0f, 215.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 250.0f, 145.0f, 295.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("c", 5.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Settings", 40.0f, 265.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            RenderUtil.drawRect(0.0f, 300.0f, 145.0f, 345.0f, ColorUtil.toRGBA(0, 0, 0, 80));
            Cherry.m_font.fontRenderer4.drawString("e", 5.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
            Cherry.m_font.fontRenderer5.drawString("Exit", 40.0f, 315.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (mouseX > 0 && mouseX < 144 && mouseY > 100 && mouseY < 145) {
            GuiCustomMainScreen.mc.displayGuiScreen((GuiScreen)new GuiWorldSelection((GuiScreen)this));
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 150 && mouseY < 195) {
            GuiCustomMainScreen.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)this));
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 200 && mouseY < 245) {
            GuiCustomMainScreen.mc.displayGuiScreen((GuiScreen)new GuiModList((GuiScreen)this));
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 250 && mouseY < 295) {
            GuiCustomMainScreen.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, GuiCustomMainScreen.mc.gameSettings));
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
        }
        else if (mouseX > 0 && mouseX < 144 && mouseY > 300 && mouseY < 345) {
            Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
            GuiCustomMainScreen.mc.shutdown();
        }
    }
    
    static {
        GuiCustomMainScreen.mc = Minecraft.getMinecraft();
        GuiCustomMainScreen.particleGenerator = new ParticleGenerator(100, GuiCustomMainScreen.mc.displayWidth, GuiCustomMainScreen.mc.displayHeight);
    }
}
