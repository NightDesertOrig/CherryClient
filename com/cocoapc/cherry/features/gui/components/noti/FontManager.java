//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.noti;

public class FontManager
{
    public static CFontRenderer guiFont;
    public static CFontRenderer sliderFont;
    public static CFontRenderer notifTitleFont;
    public static CFontRenderer notifMsgFont;
    public static CFontRenderer notifCounterFont;
    public static CFontRenderer helvetica1;
    public static CFontRenderer helvetica2;
    public static CFontRenderer helvetica3;
    public static CFontRenderer icon;
    
    public FontManager() {
        FontManager.guiFont = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/SF-UI-Display-Medium.ttf", 19.0f, 0), true, false);
        FontManager.sliderFont = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/SF-UI-Display-Medium.ttf", 15.0f, 0), true, false);
        FontManager.notifTitleFont = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/SF-UI-Display-Medium.ttf", 21.0f, 0), true, false);
        FontManager.notifMsgFont = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/SF-UI-Display-Medium.ttf", 18.0f, 0), true, false);
        FontManager.notifCounterFont = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/SF-UI-Display-Medium.ttf", 16.0f, 0), true, false);
        FontManager.helvetica1 = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/sw.ttf", 50.0f, 0), true, false);
        FontManager.helvetica2 = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/sw.ttf", 19.0f, 0), true, false);
        FontManager.helvetica3 = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/sw.ttf", 16.0f, 0), true, false);
        FontManager.icon = new CFontRenderer(new CFont.CustomFont("/assets/minecraft/font/icon.ttf", 19.0f, 0), true, false);
    }
}
