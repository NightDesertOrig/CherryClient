//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import com.cocoapc.cherry.features.setting.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.features.*;
import net.minecraft.client.gui.*;
import com.cocoapc.cherry.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.client.*;
import net.minecraft.potion.*;
import java.text.*;
import java.util.*;
import net.minecraft.init.*;
import java.util.function.*;
import net.minecraft.client.renderer.*;
import com.cocoapc.cherry.util.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.event.*;

public class HUD extends Module
{
    private static final ResourceLocation box;
    private static final ItemStack totem;
    private static HUD INSTANCE;
    private final Setting<Boolean> grayNess;
    private final Setting<Boolean> renderingUp;
    private final Setting<Boolean> waterMark;
    private final Setting<Boolean> arrayList;
    private final Setting<Boolean> alphabeticalSorting;
    private final Setting<Boolean> rolling;
    private final Setting<Boolean> rainbow;
    private final Setting<Boolean> colorMap;
    private final Setting<Boolean> moduleColors;
    private final Setting<Boolean> coords;
    private final Setting<Boolean> direction;
    private final Setting<Boolean> armor;
    private final Setting<Boolean> totems;
    public Setting<Boolean> Notifications;
    private final Setting<Boolean> greeter;
    private final Setting<Boolean> speed;
    private final Setting<Boolean> potions;
    private final Setting<Boolean> ping;
    private final Setting<Boolean> tps;
    private final Setting<Boolean> fps;
    private final Setting<Boolean> lag;
    private final Timer timer;
    private final Map<String, Integer> players;
    public Setting<String> command;
    public Setting<TextUtil.Color> bracketColor;
    public Setting<TextUtil.Color> commandColor;
    public Setting<String> commandBracket;
    public Setting<String> commandBracket2;
    public Setting<Boolean> notifyToggles;
    public Setting<Boolean> magenDavid;
    public Setting<Integer> animationHorizontalTime;
    public Setting<Integer> animationVerticalTime;
    public Setting<RenderingMode> renderingMode;
    public Setting<Integer> waterMarkY;
    public Setting<Boolean> time;
    public Setting<Integer> lagTime;
    private int color;
    private boolean shouldIncrement;
    private int hitMarkerTimer;
    
    public HUD() {
        super("SimpleHud", "Hud\u306eModule", Category.CLIENT, true, false, false);
        this.grayNess = (Setting<Boolean>)this.register(new Setting("Gray", (T)true));
        this.renderingUp = (Setting<Boolean>)this.register(new Setting("RenderingUp", (T)false, "Orientation of the HUD-Elements."));
        this.waterMark = (Setting<Boolean>)this.register(new Setting("Watermark", (T)false, "displays watermark"));
        this.arrayList = (Setting<Boolean>)this.register(new Setting("Module", (T)false, "Lists the active modules."));
        this.alphabeticalSorting = new Setting<Boolean>("AlphabeticalSorting", false, v -> this.arrayList.getValue());
        this.rolling = new Setting<Boolean>("AlphabeticalSorting", false, v -> this.arrayList.getValue());
        this.rainbow = new Setting<Boolean>("AlphabeticalSorting", false, v -> this.arrayList.getValue());
        this.colorMap = new Setting<Boolean>("AlphabeticalSorting", false, v -> this.arrayList.getValue());
        this.moduleColors = new Setting<Boolean>("AlphabeticalSorting", false, v -> this.arrayList.getValue());
        this.coords = (Setting<Boolean>)this.register(new Setting("Coord", (T)false, "Your current coordinates"));
        this.direction = (Setting<Boolean>)this.register(new Setting("Direction", (T)false, "The Direction you are facing."));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (T)false, "ArmorHUD"));
        this.totems = (Setting<Boolean>)this.register(new Setting("Totems", (T)false, "TotemHUD"));
        this.Notifications = (Setting<Boolean>)this.register(new Setting("Notifications", (T)false, "Notifications"));
        this.greeter = (Setting<Boolean>)this.register(new Setting("WL", (T)false, "The time"));
        this.speed = (Setting<Boolean>)this.register(new Setting("Speed", (T)false, "Your Speed"));
        this.potions = (Setting<Boolean>)this.register(new Setting("Potions", (T)false, "Your Speed"));
        this.ping = (Setting<Boolean>)this.register(new Setting("Ping", (T)false, "Your response time to the server."));
        this.tps = (Setting<Boolean>)this.register(new Setting("TPS", (T)false, "Ticks per second of the server."));
        this.fps = (Setting<Boolean>)this.register(new Setting("FPS", (T)false, "Your frames per second."));
        this.lag = (Setting<Boolean>)this.register(new Setting("chat name", (T)false, "The time"));
        this.timer = new Timer();
        this.players = new HashMap<String, Integer>();
        this.command = (Setting<String>)this.register(new Setting("Command", (T)"SweetPlus Hack"));
        this.bracketColor = (Setting<TextUtil.Color>)this.register(new Setting("BracketColor", (T)TextUtil.Color.BLUE));
        this.commandColor = (Setting<TextUtil.Color>)this.register(new Setting("NameColor", (T)TextUtil.Color.BLUE));
        this.commandBracket = (Setting<String>)this.register(new Setting("Bracket", (T)"<"));
        this.commandBracket2 = (Setting<String>)this.register(new Setting("Bracket2", (T)">"));
        this.notifyToggles = (Setting<Boolean>)this.register(new Setting("ChatNotify", (T)false, "notifys in chat"));
        this.magenDavid = (Setting<Boolean>)this.register(new Setting("MagenDavid", (T)false, "draws magen david"));
        this.animationHorizontalTime = (Setting<Integer>)this.register(new Setting("AnimationHTime", (T)500, (T)1, (T)1000, v -> this.arrayList.getValue()));
        this.animationVerticalTime = (Setting<Integer>)this.register(new Setting("AnimationVTime", (T)50, (T)1, (T)500, v -> this.arrayList.getValue()));
        this.renderingMode = (Setting<RenderingMode>)this.register(new Setting("Ordering", (T)RenderingMode.ABC));
        this.waterMarkY = (Setting<Integer>)this.register(new Setting("WatermarkPosY", (T)2, (T)0, (T)20, v -> this.waterMark.getValue()));
        this.time = (Setting<Boolean>)this.register(new Setting("Time", (T)false, "The time"));
        this.lagTime = (Setting<Integer>)this.register(new Setting("LagTime", (T)1000, (T)0, (T)2000));
        this.setInstance();
    }
    
    public static HUD getInstance() {
        if (HUD.INSTANCE == null) {
            HUD.INSTANCE = new HUD();
        }
        return HUD.INSTANCE;
    }
    
    private void setInstance() {
        HUD.INSTANCE = this;
    }
    
    @Override
    public int onUpdate() {
        if (this.shouldIncrement) {
            ++this.hitMarkerTimer;
        }
        if (this.hitMarkerTimer == 10) {
            this.hitMarkerTimer = 0;
            this.shouldIncrement = false;
        }
        return 0;
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (!Feature.fullNullCheck()) {
            final int width = this.renderer.scaledWidth;
            final int height = this.renderer.scaledHeight;
            this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
            if (this.waterMark.getValue()) {
                final String counter1 = "SweetPlus Client 5.0";
                if (ClickGui.getInstance().rainbow.getValue()) {
                    if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                        this.renderer.drawString(counter1, 2.0f, this.waterMarkY.getValue(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    }
                    else {
                        final int[] j = { 1 };
                        final char[] grayString = counter1.toCharArray();
                        float i = 0.0f;
                        final char[] inHell = grayString;
                        for (int posX = grayString.length, posY = 0; posY < posX; ++posY) {
                            final char posZ = inHell[posY];
                            this.renderer.drawString(String.valueOf(posZ), 2.0f + i, this.waterMarkY.getValue(), ColorUtil.rainbow(j[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                            i += this.renderer.getStringWidth(String.valueOf(posZ));
                            final int[] array = j;
                            final int n = 0;
                            ++array[n];
                        }
                    }
                }
                else {
                    this.renderer.drawString(counter1, 2.0f, this.waterMarkY.getValue(), this.color, true);
                }
            }
            final int[] aint = { 1 };
            int k = (HUD.mc.currentScreen instanceof GuiChat && !this.renderingUp.getValue()) ? 14 : 0;
            if (this.arrayList.getValue()) {
                if (this.renderingUp.getValue()) {
                    if (this.renderingMode.getValue() == RenderingMode.ABC) {
                        for (int l = 0; l < Cherry.moduleManager.sortedModulesABC.size(); ++l) {
                            final String s1 = Cherry.moduleManager.sortedModulesABC.get(l);
                            this.renderer.drawString(s1, (float)(width - 2 - this.renderer.getStringWidth(s1)), (float)(2 + k * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                            ++k;
                            final int[] array2 = aint;
                            final int n2 = 0;
                            ++array2[n2];
                        }
                    }
                    else {
                        for (int l = 0; l < Cherry.moduleManager.sortedModules.size(); ++l) {
                            final Module module = Cherry.moduleManager.sortedModules.get(l);
                            final String s2 = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                            this.renderer.drawString(s2, (float)(width - 2 - this.renderer.getStringWidth(s2)), (float)(2 + k * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                            ++k;
                            final int[] array3 = aint;
                            final int n3 = 0;
                            ++array3[n3];
                        }
                    }
                }
                else if (this.renderingMode.getValue() == RenderingMode.ABC) {
                    for (int l = 0; l < Cherry.moduleManager.sortedModulesABC.size(); ++l) {
                        final String s1 = Cherry.moduleManager.sortedModulesABC.get(l);
                        k += 10;
                        this.renderer.drawString(s1, (float)(width - 9 - this.renderer.getStringWidth(s1)), (float)(height - k), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array4 = aint;
                        final int n4 = 0;
                        ++array4[n4];
                    }
                }
                else {
                    for (int l = 0; l < Cherry.moduleManager.sortedModules.size(); ++l) {
                        final Module module = Cherry.moduleManager.sortedModules.get(l);
                        final String s2 = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                        k += 10;
                        this.renderer.drawString(s2, (float)(width - 9 - this.renderer.getStringWidth(s2)), (float)(height - k), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array5 = aint;
                        final int n5 = 0;
                        ++array5[n5];
                    }
                }
            }
            final String s3 = this.grayNess.getValue() ? String.valueOf(ChatFormatting.GRAY) : "";
            int m = (HUD.mc.currentScreen instanceof GuiChat && this.renderingUp.getValue()) ? 13 : (this.renderingUp.getValue() ? -2 : 0);
            String s2;
            if (this.renderingUp.getValue()) {
                if (this.potions.getValue()) {
                    final ArrayList arraylist = new ArrayList(Minecraft.getMinecraft().player.getActivePotionEffects());
                    for (final PotionEffect potioneffect : arraylist) {
                        final String s4 = Cherry.potionManager.getColoredPotionString(potioneffect);
                        m += 10;
                        this.renderer.drawString(s4, (float)(width - this.renderer.getStringWidth(s4) - 2), (float)(height - 2 - m), potioneffect.getPotion().getLiquidColor(), true);
                    }
                }
                if (this.speed.getValue()) {
                    s2 = s3 + "Speed " + ChatFormatting.WHITE + Cherry.speedManager.getSpeedKpH() + " km/h";
                    m += 10;
                    this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(height - 2 - m), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    final int[] array6 = aint;
                    final int n6 = 0;
                    ++array6[n6];
                }
                if (this.time.getValue()) {
                    s2 = s3 + "Time " + ChatFormatting.WHITE + new SimpleDateFormat("h:mm a").format(new Date());
                    m += 10;
                    this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(height - 2 - m), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    final int[] array7 = aint;
                    final int n7 = 0;
                    ++array7[n7];
                }
                if (this.tps.getValue()) {
                    s2 = s3 + "TPS " + ChatFormatting.WHITE + Cherry.serverManager.getTPS();
                    m += 10;
                    this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(height - 2 - m), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    final int[] array8 = aint;
                    final int n8 = 0;
                    ++array8[n8];
                }
                s2 = s3 + "FPS " + ChatFormatting.WHITE + Minecraft.debugFPS;
                final String s5 = s3 + "Ping " + ChatFormatting.WHITE + Cherry.serverManager.getPing();
                if (this.renderer.getStringWidth(s5) > this.renderer.getStringWidth(s2)) {
                    if (this.ping.getValue()) {
                        m += 10;
                        this.renderer.drawString(s5, (float)(width - this.renderer.getStringWidth(s5) - 2), (float)(height - 2 - m), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array9 = aint;
                        final int n9 = 0;
                        ++array9[n9];
                    }
                    if (this.fps.getValue()) {
                        m += 10;
                        this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(height - 2 - m), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array10 = aint;
                        final int n10 = 0;
                        ++array10[n10];
                    }
                }
                else {
                    if (this.fps.getValue()) {
                        m += 10;
                        this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(height - 2 - m), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array11 = aint;
                        final int n11 = 0;
                        ++array11[n11];
                    }
                    if (this.ping.getValue()) {
                        m += 10;
                        this.renderer.drawString(s5, (float)(width - this.renderer.getStringWidth(s5) - 2), (float)(height - 2 - m), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array12 = aint;
                        final int n12 = 0;
                        ++array12[n12];
                    }
                }
            }
            else {
                if (this.potions.getValue()) {
                    final ArrayList arraylist = new ArrayList(Minecraft.getMinecraft().player.getActivePotionEffects());
                    for (final PotionEffect potioneffect : arraylist) {
                        final String s4 = Cherry.potionManager.getColoredPotionString(potioneffect);
                        this.renderer.drawString(s4, (float)(width - this.renderer.getStringWidth(s4) - 2), (float)(2 + m++ * 10), potioneffect.getPotion().getLiquidColor(), true);
                    }
                }
                if (this.speed.getValue()) {
                    s2 = s3 + "Speed " + ChatFormatting.WHITE + Cherry.speedManager.getSpeedKpH() + " km/h";
                    this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(2 + m++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    final int[] array13 = aint;
                    final int n13 = 0;
                    ++array13[n13];
                }
                if (this.time.getValue()) {
                    s2 = s3 + "Time " + ChatFormatting.WHITE + new SimpleDateFormat("h:mm a").format(new Date());
                    this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(2 + m++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    final int[] array14 = aint;
                    final int n14 = 0;
                    ++array14[n14];
                }
                if (this.tps.getValue()) {
                    s2 = s3 + "TPS " + ChatFormatting.WHITE + Cherry.serverManager.getTPS();
                    this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(2 + m++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    final int[] array15 = aint;
                    final int n15 = 0;
                    ++array15[n15];
                }
                s2 = s3 + "FPS " + ChatFormatting.WHITE + Minecraft.debugFPS;
                final String s5 = s3 + "Ping " + ChatFormatting.WHITE + Cherry.serverManager.getPing();
                if (this.renderer.getStringWidth(s5) > this.renderer.getStringWidth(s2)) {
                    if (this.ping.getValue()) {
                        this.renderer.drawString(s5, (float)(width - this.renderer.getStringWidth(s5) - 2), (float)(2 + m++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array16 = aint;
                        final int n16 = 0;
                        ++array16[n16];
                    }
                    if (this.fps.getValue()) {
                        this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(2 + m++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array17 = aint;
                        final int n17 = 0;
                        ++array17[n17];
                    }
                }
                else {
                    if (this.fps.getValue()) {
                        this.renderer.drawString(s2, (float)(width - this.renderer.getStringWidth(s2) - 2), (float)(2 + m++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array18 = aint;
                        final int n18 = 0;
                        ++array18[n18];
                    }
                    if (this.ping.getValue()) {
                        this.renderer.drawString(s5, (float)(width - this.renderer.getStringWidth(s5) - 2), (float)(2 + m++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(aint[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        final int[] array19 = aint;
                        final int n19 = 0;
                        ++array19[n19];
                    }
                }
            }
            final boolean flag = HUD.mc.world.getBiome(HUD.mc.player.getPosition()).getBiomeName().equals("Hell");
            final int posX = (int)HUD.mc.player.posX;
            int posY = (int)HUD.mc.player.posY;
            final int l2 = (int)HUD.mc.player.posZ;
            final float nether = flag ? 8.0f : 0.125f;
            final int hposX = (int)(HUD.mc.player.posX * nether);
            final int hposZ = (int)(HUD.mc.player.posZ * nether);
            m = ((HUD.mc.currentScreen instanceof GuiChat) ? 14 : 0);
            final String coordinates = ChatFormatting.WHITE + "XYZ " + ChatFormatting.RESET + (flag ? (posX + ", " + posY + ", " + l2 + ChatFormatting.WHITE + " [" + ChatFormatting.RESET + hposX + ", " + hposZ + ChatFormatting.WHITE + "]" + ChatFormatting.RESET) : (posX + ", " + posY + ", " + l2 + ChatFormatting.WHITE + " [" + ChatFormatting.RESET + hposX + ", " + hposZ + ChatFormatting.WHITE + "]"));
            final String direction = this.direction.getValue() ? Cherry.rotationManager.getDirection4D(false) : "";
            final String coords = this.coords.getValue() ? coordinates : "";
            m += 10;
            if (ClickGui.getInstance().rainbow.getValue()) {
                final String rainbowCoords = this.coords.getValue() ? ("XYZ " + (flag ? (posX + ", " + posY + ", " + l2 + " [" + hposX + ", " + hposZ + "]") : (posX + ", " + posY + ", " + l2 + " [" + hposX + ", " + hposZ + "]"))) : "";
                if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                    this.renderer.drawString(direction, 2.0f, (float)(height - m - 11), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    this.renderer.drawString(rainbowCoords, 2.0f, (float)(height - m), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                }
                else {
                    final int[] counter2 = { 1 };
                    final char[] counter3;
                    final char[] stringToCharArray = counter3 = direction.toCharArray();
                    for (int stringToCharArray2 = stringToCharArray.length, u = 0; u < stringToCharArray2; ++u) {
                        final char c = counter3[u];
                        this.renderer.drawString(String.valueOf(c), 2.0f, (float)(height - m - 11), ColorUtil.rainbow(counter2[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                        s2 += (float)this.renderer.getStringWidth(String.valueOf(c));
                        final int[] array20 = counter2;
                        final int n20 = 0;
                        ++array20[n20];
                    }
                    final int[] aint2 = { 1 };
                    final char[] achar = rainbowCoords.toCharArray();
                    float f = 0.0f;
                    final char[] achar2 = achar;
                    for (int i2 = achar.length, j2 = 0; j2 < i2; ++j2) {
                        final char c2 = achar2[j2];
                        this.renderer.drawString(String.valueOf(c2), 2.0f + f, (float)(height - m), ColorUtil.rainbow(aint2[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                        f += this.renderer.getStringWidth(String.valueOf(c2));
                        final int[] array21 = aint2;
                        final int n21 = 0;
                        ++array21[n21];
                    }
                }
            }
            else {
                this.renderer.drawString(direction, 2.0f, (float)(height - m - 11), this.color, true);
                this.renderer.drawString(coords, 2.0f, (float)(height - m), this.color, true);
            }
            if (this.armor.getValue()) {
                this.renderArmorHUD(true);
            }
            if (this.totems.getValue()) {
                this.renderTotemHUD();
            }
            if (this.greeter.getValue()) {
                this.renderGreeter();
            }
            if (this.lag.getValue()) {
                this.renderLag();
            }
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    private Object colorMap(final int clamp) {
        return null;
    }
    
    public Map<String, Integer> getTextRadarPlayers() {
        return EntityUtil.getTextRadarPlayers();
    }
    
    public void renderGreeter() {
        final int width = this.renderer.scaledWidth;
        String text = "";
        if (this.greeter.getValue()) {
            text = text + MathUtil.getTimeOfDay() + Util.mc.player.getDisplayNameString();
        }
        if (ClickGui.getInstance().rainbow.getValue()) {
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(text, width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f, 2.0f, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
            }
            else {
                final int[] counter1 = { 1 };
                final char[] stringToCharArray = text.toCharArray();
                float i = 0.0f;
                for (final char c : stringToCharArray) {
                    this.renderer.drawString(String.valueOf(c), width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f + i, 2.0f, ColorUtil.rainbow(counter1[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    i += this.renderer.getStringWidth(String.valueOf(c));
                    ++counter1[0];
                }
            }
        }
        else {
            this.renderer.drawString(text, width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f, 2.0f, this.color, true);
        }
    }
    
    public void renderLag() {
        final int width = this.renderer.scaledWidth;
        if (Cherry.serverManager.isServerNotResponding()) {
            final String text = ChatFormatting.RED + "Server not responding " + MathUtil.round(Cherry.serverManager.serverRespondingTime() / 1000.0f, 1) + "s.";
            this.renderer.drawString(text, width / 2.0f - this.renderer.getStringWidth(text) / 2.0f + 2.0f, 20.0f, this.color, true);
        }
    }
    
    public void renderTotemHUD() {
        final int width = this.renderer.scaledWidth;
        final int height = this.renderer.scaledHeight;
        int totems = Util.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (Util.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            totems += Util.mc.player.getHeldItemOffhand().getCount();
        }
        if (totems > 0) {
            GlStateManager.enableTexture2D();
            final int i = width / 2;
            final int iteration = 0;
            final int y = height - 55 - ((Util.mc.player.isInWater() && Util.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
            final int x = i - 189 + 180 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.totem, x, y);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(Util.mc.fontRenderer, HUD.totem, x, y, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(totems + "", (float)(x + 19 - 2 - this.renderer.getStringWidth(totems + "")), (float)(y + 9), 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    public void renderArmorHUD(final boolean percent) {
        final int width = this.renderer.scaledWidth;
        final int height = this.renderer.scaledHeight;
        GlStateManager.enableTexture2D();
        final int i = width / 2;
        int iteration = 0;
        final int y = height - 55 - ((Util.mc.player.isInWater() && Util.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
        for (final ItemStack is : Util.mc.player.inventory.armorInventory) {
            ++iteration;
            if (is.isEmpty()) {
                continue;
            }
            final int x = i - 90 + (9 - iteration) * 20 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(is, x, y);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(Util.mc.fontRenderer, is, x, y, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (is.getCount() > 1) ? (is.getCount() + "") : "";
            this.renderer.drawStringWithShadow(s, (float)(x + 19 - 2 - this.renderer.getStringWidth(s)), (float)(y + 9), 16777215);
            if (!percent) {
                continue;
            }
            final float green = (float)((is.getMaxDamage() - is.getItemDamage()) / is.getMaxDamage());
            final float red = 1.0f - green;
            final int dmg = 100 - (int)(red * 100.0f);
            this.renderer.drawStringWithShadow(dmg + "", (float)(x + 8 - this.renderer.getStringWidth(dmg + "") / 2), (float)(y - 11), ColorUtil.toRGBA((int)(red * 255.0f), (int)(green * 255.0f), 0));
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final AttackEntityEvent event) {
        this.shouldIncrement = true;
    }
    
    @Override
    public void onLoad() {
        Cherry.commandManager.setClientMessage(this.getCommandMessage());
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent event) {
        if (event.getStage() == 2 && this.equals(event.getSetting().getFeature())) {
            Cherry.commandManager.setClientMessage(this.getCommandMessage());
        }
    }
    
    public String getCommandMessage() {
        return TextUtil.coloredString(this.commandBracket.getPlannedValue(), this.bracketColor.getPlannedValue()) + TextUtil.coloredString(this.command.getPlannedValue(), this.commandColor.getPlannedValue()) + TextUtil.coloredString(this.commandBracket2.getPlannedValue(), this.bracketColor.getPlannedValue());
    }
    
    public void drawTextRadar(final int yOffset) {
        if (!this.players.isEmpty()) {
            int y = this.renderer.getFontHeight() + 7 + yOffset;
            for (final Map.Entry<String, Integer> player : this.players.entrySet()) {
                final String text = player.getKey() + " ";
                final int textheight = this.renderer.getFontHeight() + 1;
                this.renderer.drawString(text, 2.0f, (float)y, this.color, true);
                y += textheight;
            }
        }
    }
    
    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
        totem = new ItemStack(Items.TOTEM_OF_UNDYING);
        HUD.INSTANCE = new HUD();
    }
    
    public enum RenderingMode
    {
        Length, 
        ABC;
    }
}
