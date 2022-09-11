//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.client;

import com.cocoapc.cherry.features.modules.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.event.events.*;
import net.minecraft.client.gui.*;

public class Notifications extends Module
{
    public static String ontext_0;
    public static String offtext_0;
    public static String ontext_1;
    public static String offtext_1;
    public static String ontext_2;
    public static String offtext_2;
    public static String ontext_3;
    public static String offtext_3;
    public static String ontext_4;
    public static String offtext_4;
    public static int oncolor;
    public static int offcolor;
    public static int oncount;
    public static int offcount;
    public static int onXpos_0;
    public static int onYpos_0;
    public static int onXpos_1;
    public static int onYpos_1;
    public static int onXpos_2;
    public static int onYpos_2;
    public static int onXpos_3;
    public static int onYpos_3;
    public static int onXpos_4;
    public static int onYpos_4;
    public static int onLeft_0;
    public static int ontop_0;
    public static int onLeft_1;
    public static int ontop_1;
    public static int onLeft_2;
    public static int ontop_2;
    public static int onLeft_3;
    public static int ontop_3;
    public static int onLeft_4;
    public static int ontop_4;
    public static int onwait_0;
    public static int onwait_1;
    public static int onwait_2;
    public static int onwait_3;
    public static int onwait_4;
    public static int onLeftBack_0;
    public static int ontopBack_0;
    public static int onLeftBack_1;
    public static int ontopBack_1;
    public static int onLeftBack_2;
    public static int ontopBack_2;
    public static int onLeftBack_3;
    public static int ontopBack_3;
    public static int onLeftBack_4;
    public static int ontopBack_4;
    public static int onXposBack_0;
    public static int onYposBack_0;
    public static int onXposBack_1;
    public static int onYposBack_1;
    public static int onXposBack_2;
    public static int onYposBack_2;
    public static int onXposBack_3;
    public static int onYposBack_3;
    public static int onXposBack_4;
    public static int onYposBack_4;
    public static boolean ondone_0;
    public static boolean ondone_1;
    public static boolean ondone_2;
    public static boolean ondone_3;
    public static boolean ondone_4;
    public static int offXpos_0;
    public static int offYpos_0;
    public static int offXpos_1;
    public static int offYpos_1;
    public static int offXpos_2;
    public static int offYpos_2;
    public static int offXpos_3;
    public static int offYpos_3;
    public static int offXpos_4;
    public static int offYpos_4;
    public static int offLeft_0;
    public static int offtop_0;
    public static int offLeft_1;
    public static int offtop_1;
    public static int offLeft_2;
    public static int offtop_2;
    public static int offLeft_3;
    public static int offtop_3;
    public static int offLeft_4;
    public static int offtop_4;
    public static int offwait_0;
    public static int offwait_1;
    public static int offwait_2;
    public static int offwait_3;
    public static int offwait_4;
    public static int offLeftBack_0;
    public static int offtopBack_0;
    public static int offLeftBack_1;
    public static int offtopBack_1;
    public static int offLeftBack_2;
    public static int offtopBack_2;
    public static int offLeftBack_3;
    public static int offtopBack_3;
    public static int offLeftBack_4;
    public static int offtopBack_4;
    public static int offXposBack_0;
    public static int offYposBack_0;
    public static int offXposBack_1;
    public static int offYposBack_1;
    public static int offXposBack_2;
    public static int offYposBack_2;
    public static int offXposBack_3;
    public static int offYposBack_3;
    public static int offXposBack_4;
    public static int offYposBack_4;
    public static boolean offdone_0;
    public static boolean offdone_1;
    public static boolean offdone_2;
    public static boolean offdone_3;
    public static boolean offdone_4;
    
    public Notifications() {
        super("Notifications", "Notification", Category.CLIENT, false, false, false);
    }
    
    public static void onMessage(final String name) {
        Notifications.oncolor = -16777216;
        if (++Notifications.oncount == 1) {
            Notifications.ondone_0 = true;
            Notifications.ontext_0 = ChatFormatting.BLACK + "Enable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.oncount == 2) {
            Notifications.ondone_1 = true;
            Notifications.ontext_1 = ChatFormatting.BLACK + "Enable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.oncount == 3) {
            Notifications.ondone_2 = true;
            Notifications.ontext_2 = ChatFormatting.BLACK + "Enable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.oncount == 4) {
            Notifications.ondone_3 = true;
            Notifications.ontext_3 = ChatFormatting.BLACK + "Enable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.oncount == 5) {
            Notifications.ondone_4 = true;
            Notifications.ontext_4 = ChatFormatting.BLACK + "Enable Modules " + ChatFormatting.BLACK + name;
        }
        else {
            --Notifications.oncount;
        }
    }
    
    public static void offMessage(final String name) {
        Notifications.offcolor = -16777216;
        if (++Notifications.offcount == 1) {
            Notifications.offdone_0 = true;
            Notifications.offtext_0 = ChatFormatting.BLACK + "Disable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.offcount == 2) {
            Notifications.offdone_1 = true;
            Notifications.offtext_1 = ChatFormatting.BLACK + "Disable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.offcount == 3) {
            Notifications.offdone_2 = true;
            Notifications.offtext_2 = ChatFormatting.BLACK + "Disable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.offcount == 4) {
            Notifications.offdone_3 = true;
            Notifications.offtext_3 = ChatFormatting.BLACK + "Disable Modules " + ChatFormatting.BLACK + name;
        }
        else if (Notifications.offcount == 5) {
            Notifications.offdone_4 = true;
            Notifications.offtext_4 = ChatFormatting.BLACK + "Disable Modules " + ChatFormatting.BLACK + name;
        }
        else {
            --Notifications.offcount;
        }
    }
    
    @Override
    public void onRender2D(final Render2DEvent event) {
        if (Notifications.oncount == 0) {
            System.out.println(Notifications.oncount);
        }
        if (Notifications.ondone_0) {
            if (Notifications.onXpos_0 != 850) {
                GuiScreen.drawRect(Notifications.onXpos_0, Notifications.onYpos_0, Notifications.onXpos_0 + 120, Notifications.onYpos_0 + 25, Notifications.oncolor);
                System.out.println(Notifications.onXpos_0 -= 5);
            }
            else if (Notifications.onXpos_0 == 850 && Notifications.onLeft_0 != 850) {
                GuiScreen.drawRect(Notifications.onLeft_0 -= 5, Notifications.ontop_0, Notifications.onLeft_0 + 120, Notifications.ontop_0 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_0, Notifications.ontop_0, Notifications.onLeft_0 + 12, Notifications.ontop_0 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_0, Notifications.onLeft_0 + 16, Notifications.ontop_0 + 7, 0);
                GuiScreen.drawRect(850, Notifications.onYpos_0, Notifications.onLeft_0, Notifications.onYpos_0 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_0 == 850 && Notifications.onLeft_0 == 850 && Notifications.onwait_0 != 120) {
                GuiScreen.drawRect(Notifications.onLeft_0, Notifications.ontop_0, Notifications.onLeft_0 + 120, Notifications.ontop_0 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_0, Notifications.ontop_0, Notifications.onLeft_0 + 12, Notifications.ontop_0 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_0, Notifications.onLeft_0 + 16, Notifications.ontop_0 + 7, 0);
                GuiScreen.drawRect(Notifications.onwait_0 + Notifications.onLeft_0, Notifications.ontop_0 + 23, Notifications.onLeft_0 + 120, Notifications.ontop_0 + 25, -16777216);
                ++Notifications.onwait_0;
            }
            else if (Notifications.onXpos_0 == 850 && Notifications.onLeft_0 == 850 && Notifications.onwait_0 == 120 && Notifications.onLeftBack_0 != 970) {
                GuiScreen.drawRect(Notifications.onLeftBack_0, Notifications.ontop_0, Notifications.onLeftBack_0 + 120, Notifications.ontop_0 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeftBack_0, Notifications.ontop_0, Notifications.onLeftBack_0 + 12, Notifications.ontop_0 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_0, Notifications.onLeftBack_0 + 16, Notifications.ontop_0 + 7, 0);
                GuiScreen.drawRect(Notifications.onLeftBack_0 += 5, Notifications.onYpos_0, 850, Notifications.onYpos_0 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_0 == 850 && Notifications.onLeft_0 == 850 && Notifications.onwait_0 == 120 && Notifications.onLeftBack_0 == 970 && Notifications.onXposBack_0 != 970) {
                GuiScreen.drawRect(Notifications.onXposBack_0, Notifications.onYpos_0, Notifications.onXposBack_0 + 120, Notifications.onYpos_0 + 25, Notifications.oncolor);
                Notifications.onXposBack_0 += 5;
            }
            else {
                --Notifications.oncount;
                Notifications.onLeft_0 = 970;
                Notifications.onXpos_0 = 970;
                Notifications.onwait_0 = 0;
                Notifications.onLeftBack_0 = 850;
                Notifications.onXposBack_0 = 850;
                Notifications.ondone_0 = false;
            }
        }
        if (Notifications.ondone_2) {
            if (Notifications.onXpos_2 != 850) {
                GuiScreen.drawRect(Notifications.onXpos_2, Notifications.onYpos_2, Notifications.onXpos_2 + 120, Notifications.onYpos_2 + 25, Notifications.oncolor);
                System.out.println(Notifications.onXpos_2 -= 5);
            }
            else if (Notifications.onXpos_2 == 850 && Notifications.onLeft_2 != 850) {
                GuiScreen.drawRect(Notifications.onLeft_2 -= 5, Notifications.ontop_2, Notifications.onLeft_2 + 120, Notifications.ontop_2 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_2, Notifications.ontop_2, Notifications.onLeft_2 + 12, Notifications.ontop_2 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_2, Notifications.onLeft_2 + 16, Notifications.ontop_2 + 7, 0);
                GuiScreen.drawRect(850, Notifications.onYpos_2, Notifications.onLeft_2, Notifications.onYpos_2 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_2 == 850 && Notifications.onLeft_2 == 850 && Notifications.onwait_2 != 120) {
                GuiScreen.drawRect(Notifications.onLeft_2, Notifications.ontop_2, Notifications.onLeft_2 + 120, Notifications.ontop_2 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_2, Notifications.ontop_2, Notifications.onLeft_2 + 12, Notifications.ontop_2 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_2, Notifications.onLeft_2 + 16, Notifications.ontop_2 + 7, 0);
                GuiScreen.drawRect(Notifications.onwait_2 + Notifications.onLeft_2, Notifications.ontop_2 + 23, Notifications.onLeft_2 + 120, Notifications.ontop_2 + 25, -16777216);
                ++Notifications.onwait_2;
            }
            else if (Notifications.onXpos_2 == 850 && Notifications.onLeft_2 == 850 && Notifications.onwait_2 == 120 && Notifications.onLeftBack_2 != 970) {
                GuiScreen.drawRect(Notifications.onLeftBack_2, Notifications.ontop_2, Notifications.onLeftBack_2 + 120, Notifications.ontop_2 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeftBack_2, Notifications.ontop_2, Notifications.onLeftBack_2 + 12, Notifications.ontop_2 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_2, Notifications.onLeftBack_2 + 16, Notifications.ontop_2 + 7, 0);
                GuiScreen.drawRect(Notifications.onLeftBack_2 += 5, Notifications.onYpos_2, 850, Notifications.onYpos_2 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_2 == 850 && Notifications.onLeft_2 == 850 && Notifications.onwait_2 == 120 && Notifications.onLeftBack_2 == 970 && Notifications.onXposBack_2 != 970) {
                GuiScreen.drawRect(Notifications.onXposBack_2, Notifications.onYpos_2, Notifications.onXposBack_2 + 120, Notifications.onYpos_2 + 25, Notifications.oncolor);
                Notifications.onXposBack_2 += 5;
            }
            else {
                --Notifications.oncount;
                Notifications.onLeft_2 = 970;
                Notifications.onXpos_2 = 970;
                Notifications.onwait_2 = 0;
                Notifications.onLeftBack_2 = 850;
                Notifications.onXposBack_2 = 850;
                Notifications.ondone_2 = false;
            }
        }
        if (Notifications.ondone_3) {
            if (Notifications.onXpos_3 != 850) {
                GuiScreen.drawRect(Notifications.onXpos_3, Notifications.onYpos_3, Notifications.onXpos_3 + 120, Notifications.onYpos_3 + 25, Notifications.oncolor);
                System.out.println(Notifications.onXpos_3 -= 5);
            }
            else if (Notifications.onXpos_3 == 850 && Notifications.onLeft_3 != 850) {
                GuiScreen.drawRect(Notifications.onLeft_3 -= 5, Notifications.ontop_3, Notifications.onLeft_3 + 120, Notifications.ontop_3 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_3, Notifications.ontop_3, Notifications.onLeft_3 + 12, Notifications.ontop_3 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_3, Notifications.onLeft_3 + 16, Notifications.ontop_3 + 7, 0);
                GuiScreen.drawRect(850, Notifications.onYpos_3, Notifications.onLeft_3, Notifications.onYpos_3 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_3 == 850 && Notifications.onLeft_3 == 850 && Notifications.onwait_3 != 120) {
                GuiScreen.drawRect(Notifications.onLeft_3, Notifications.ontop_3, Notifications.onLeft_3 + 120, Notifications.ontop_3 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_3, Notifications.ontop_3, Notifications.onLeft_3 + 12, Notifications.ontop_3 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_3, Notifications.onLeft_3 + 16, Notifications.ontop_3 + 7, 0);
                GuiScreen.drawRect(Notifications.onwait_3 + Notifications.onLeft_3, Notifications.ontop_3 + 23, Notifications.onLeft_3 + 120, Notifications.ontop_3 + 25, -16777216);
                ++Notifications.onwait_3;
            }
            else if (Notifications.onXpos_3 == 850 && Notifications.onLeft_3 == 850 && Notifications.onwait_3 == 120 && Notifications.onLeftBack_3 != 970) {
                GuiScreen.drawRect(Notifications.onLeftBack_3, Notifications.ontop_3, Notifications.onLeftBack_3 + 120, Notifications.ontop_3 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeftBack_3, Notifications.ontop_3, Notifications.onLeftBack_3 + 12, Notifications.ontop_3 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_3, Notifications.onLeftBack_3 + 16, Notifications.ontop_3 + 7, 0);
                GuiScreen.drawRect(Notifications.onLeftBack_3 += 5, Notifications.onYpos_3, 850, Notifications.onYpos_3 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_3 == 850 && Notifications.onLeft_3 == 850 && Notifications.onwait_3 == 120 && Notifications.onLeftBack_3 == 970 && Notifications.onXposBack_3 != 970) {
                GuiScreen.drawRect(Notifications.onXposBack_3, Notifications.onYpos_3, Notifications.onXposBack_3 + 120, Notifications.onYpos_3 + 25, Notifications.oncolor);
                Notifications.onXposBack_3 += 5;
            }
            else {
                --Notifications.oncount;
                Notifications.onLeft_3 = 970;
                Notifications.onXpos_3 = 970;
                Notifications.onwait_3 = 0;
                Notifications.onLeftBack_3 = 850;
                Notifications.onXposBack_3 = 850;
                Notifications.ondone_3 = false;
            }
        }
        if (Notifications.ondone_4) {
            if (Notifications.onXpos_4 != 850) {
                GuiScreen.drawRect(Notifications.onXpos_4, Notifications.onYpos_4, Notifications.onXpos_4 + 120, Notifications.onYpos_4 + 25, Notifications.oncolor);
                System.out.println(Notifications.onXpos_4 -= 5);
            }
            else if (Notifications.onXpos_4 == 850 && Notifications.onLeft_4 != 850) {
                GuiScreen.drawRect(Notifications.onLeft_4 -= 5, Notifications.ontop_4, Notifications.onLeft_4 + 120, Notifications.ontop_4 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_4, Notifications.ontop_4, Notifications.onLeft_4 + 12, Notifications.ontop_4 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_4, Notifications.onLeft_4 + 16, Notifications.ontop_4 + 7, 0);
                GuiScreen.drawRect(850, Notifications.onYpos_4, Notifications.onLeft_4, Notifications.onYpos_4 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_4 == 850 && Notifications.onLeft_4 == 850 && Notifications.onwait_4 != 120) {
                GuiScreen.drawRect(Notifications.onLeft_4, Notifications.ontop_4, Notifications.onLeft_4 + 120, Notifications.ontop_4 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_4, Notifications.ontop_4, Notifications.onLeft_4 + 12, Notifications.ontop_4 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_4, Notifications.onLeft_4 + 16, Notifications.ontop_4 + 7, 0);
                GuiScreen.drawRect(Notifications.onwait_4 + Notifications.onLeft_4, Notifications.ontop_4 + 23, Notifications.onLeft_4 + 120, Notifications.ontop_4 + 25, -16777216);
                ++Notifications.onwait_4;
            }
            else if (Notifications.onXpos_4 == 850 && Notifications.onLeft_4 == 850 && Notifications.onwait_4 == 120 && Notifications.onLeftBack_4 != 970) {
                GuiScreen.drawRect(Notifications.onLeftBack_4, Notifications.ontop_4, Notifications.onLeftBack_4 + 120, Notifications.ontop_4 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeftBack_4, Notifications.ontop_4, Notifications.onLeftBack_4 + 12, Notifications.ontop_4 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_4, Notifications.onLeftBack_4 + 16, Notifications.ontop_4 + 7, 0);
                GuiScreen.drawRect(Notifications.onLeftBack_4 += 5, Notifications.onYpos_4, 850, Notifications.onYpos_4 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_4 == 850 && Notifications.onLeft_4 == 850 && Notifications.onwait_4 == 120 && Notifications.onLeftBack_4 == 970 && Notifications.onXposBack_4 != 970) {
                GuiScreen.drawRect(Notifications.onXposBack_4, Notifications.onYpos_4, Notifications.onXposBack_4 + 120, Notifications.onYpos_4 + 25, Notifications.oncolor);
                Notifications.onXposBack_4 += 5;
            }
            else {
                --Notifications.oncount;
                Notifications.onLeft_4 = 970;
                Notifications.onXpos_4 = 970;
                Notifications.onwait_4 = 0;
                Notifications.onLeftBack_4 = 850;
                Notifications.onXposBack_4 = 850;
                Notifications.ondone_4 = false;
            }
        }
        if (Notifications.ondone_1) {
            if (Notifications.onXpos_1 != 850) {
                GuiScreen.drawRect(Notifications.onXpos_1, Notifications.onYpos_1, Notifications.onXpos_1 + 120, Notifications.onYpos_1 + 25, Notifications.oncolor);
                System.out.println(Notifications.onXpos_1 -= 5);
            }
            else if (Notifications.onXpos_1 == 850 && Notifications.onLeft_1 != 850) {
                GuiScreen.drawRect(Notifications.onLeft_1 -= 5, Notifications.ontop_1, Notifications.onLeft_1 + 120, Notifications.ontop_1 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_1, Notifications.ontop_1, Notifications.onLeft_1 + 12, Notifications.ontop_1 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_1, Notifications.onLeft_1 + 16, Notifications.ontop_1 + 7, 0);
                GuiScreen.drawRect(850, Notifications.onYpos_1, Notifications.onLeft_1, Notifications.onYpos_1 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_1 == 850 && Notifications.onLeft_1 == 850 && Notifications.onwait_1 != 120) {
                GuiScreen.drawRect(Notifications.onLeft_1, Notifications.ontop_1, Notifications.onLeft_1 + 120, Notifications.ontop_1 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeft_1, Notifications.ontop_1, Notifications.onLeft_1 + 12, Notifications.ontop_1 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_1, Notifications.onLeft_1 + 16, Notifications.ontop_1 + 7, 0);
                GuiScreen.drawRect(Notifications.onwait_1 + Notifications.onLeft_1, Notifications.ontop_1 + 23, Notifications.onLeft_1 + 120, Notifications.ontop_1 + 25, -16777216);
                ++Notifications.onwait_1;
            }
            else if (Notifications.onXpos_1 == 850 && Notifications.onLeft_1 == 850 && Notifications.onwait_1 == 120 && Notifications.onLeftBack_1 != 970) {
                GuiScreen.drawRect(Notifications.onLeftBack_1, Notifications.ontop_1, Notifications.onLeftBack_1 + 120, Notifications.ontop_1 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.onLeftBack_1, Notifications.ontop_1, Notifications.onLeftBack_1 + 12, Notifications.ontop_1 + 25, Notifications.oncolor);
                Notifications.mc.fontRenderer.drawString(Notifications.ontext_1, Notifications.onLeftBack_1 + 16, Notifications.ontop_1 + 7, 0);
                GuiScreen.drawRect(Notifications.onLeftBack_1 += 5, Notifications.onYpos_1, 850, Notifications.onYpos_1 + 25, Notifications.oncolor);
            }
            else if (Notifications.onXpos_1 == 850 && Notifications.onLeft_1 == 850 && Notifications.onwait_1 == 120 && Notifications.onLeftBack_1 == 970 && Notifications.onXposBack_1 != 970) {
                GuiScreen.drawRect(Notifications.onXposBack_1, Notifications.onYpos_1, Notifications.onXposBack_1 + 120, Notifications.onYpos_1 + 25, Notifications.oncolor);
                Notifications.onXposBack_1 += 5;
            }
            else {
                --Notifications.oncount;
                Notifications.onLeft_1 = 970;
                Notifications.onXpos_1 = 970;
                Notifications.onwait_1 = 0;
                Notifications.onLeftBack_1 = 850;
                Notifications.onXposBack_1 = 850;
                Notifications.ondone_1 = false;
            }
        }
        if (Notifications.offdone_0) {
            if (Notifications.offXpos_0 != 850) {
                GuiScreen.drawRect(Notifications.offXpos_0, Notifications.offYpos_0, Notifications.offXpos_0 + 120, Notifications.offYpos_0 + 25, Notifications.offcolor);
                System.out.println(Notifications.offXpos_0 -= 5);
            }
            else if (Notifications.offXpos_0 == 850 && Notifications.offLeft_0 != 850) {
                GuiScreen.drawRect(Notifications.offLeft_0 -= 5, Notifications.offtop_0, Notifications.offLeft_0 + 120, Notifications.offtop_0 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_0, Notifications.offtop_0, Notifications.offLeft_0 + 12, Notifications.offtop_0 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_0, Notifications.offLeft_0 + 16, Notifications.offtop_0 + 7, 0);
                GuiScreen.drawRect(850, Notifications.offYpos_0, Notifications.offLeft_0, Notifications.offYpos_0 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_0 == 850 && Notifications.offLeft_0 == 850 && Notifications.offwait_0 != 120) {
                GuiScreen.drawRect(Notifications.offLeft_0, Notifications.offtop_0, Notifications.offLeft_0 + 120, Notifications.offtop_0 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_0, Notifications.offtop_0, Notifications.offLeft_0 + 12, Notifications.offtop_0 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_0, Notifications.offLeft_0 + 16, Notifications.offtop_0 + 7, 0);
                GuiScreen.drawRect(Notifications.offwait_0 + Notifications.offLeft_0, Notifications.offtop_0 + 23, Notifications.offLeft_0 + 120, Notifications.offtop_0 + 25, -16777216);
                ++Notifications.offwait_0;
            }
            else if (Notifications.offXpos_0 == 850 && Notifications.offLeft_0 == 850 && Notifications.offwait_0 == 120 && Notifications.offLeftBack_0 != 970) {
                GuiScreen.drawRect(Notifications.offLeftBack_0, Notifications.offtop_0, Notifications.offLeftBack_0 + 120, Notifications.offtop_0 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeftBack_0, Notifications.offtop_0, Notifications.offLeftBack_0 + 12, Notifications.offtop_0 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_0, Notifications.offLeftBack_0 + 16, Notifications.offtop_0 + 7, 0);
                GuiScreen.drawRect(Notifications.offLeftBack_0 += 5, Notifications.offYpos_0, 850, Notifications.offYpos_0 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_0 == 850 && Notifications.offLeft_0 == 850 && Notifications.offwait_0 == 120 && Notifications.offLeftBack_0 == 970 && Notifications.offXposBack_0 != 970) {
                GuiScreen.drawRect(Notifications.offXposBack_0, Notifications.offYpos_0, Notifications.offXposBack_0 + 120, Notifications.offYpos_0 + 25, Notifications.offcolor);
                Notifications.offXposBack_0 += 5;
            }
            else {
                --Notifications.offcount;
                Notifications.offLeft_0 = 970;
                Notifications.offXpos_0 = 970;
                Notifications.offwait_0 = 0;
                Notifications.offLeftBack_0 = 850;
                Notifications.offXposBack_0 = 850;
                Notifications.offdone_0 = false;
            }
        }
        if (Notifications.offdone_1) {
            if (Notifications.offXpos_1 != 850) {
                GuiScreen.drawRect(Notifications.offXpos_1, Notifications.offYpos_1, Notifications.offXpos_1 + 120, Notifications.offYpos_1 + 25, Notifications.offcolor);
                System.out.println(Notifications.offXpos_1 -= 5);
            }
            else if (Notifications.offXpos_1 == 850 && Notifications.offLeft_1 != 850) {
                GuiScreen.drawRect(Notifications.offLeft_1 -= 5, Notifications.offtop_1, Notifications.offLeft_1 + 120, Notifications.offtop_1 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_1, Notifications.offtop_1, Notifications.offLeft_1 + 12, Notifications.offtop_1 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_1, Notifications.offLeft_1 + 16, Notifications.offtop_1 + 7, 0);
                GuiScreen.drawRect(850, Notifications.offYpos_1, Notifications.offLeft_1, Notifications.offYpos_1 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_1 == 850 && Notifications.offLeft_1 == 850 && Notifications.offwait_1 != 120) {
                GuiScreen.drawRect(Notifications.offLeft_1, Notifications.offtop_1, Notifications.offLeft_1 + 120, Notifications.offtop_1 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_1, Notifications.offtop_1, Notifications.offLeft_1 + 12, Notifications.offtop_1 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_1, Notifications.offLeft_1 + 16, Notifications.offtop_1 + 7, 0);
                GuiScreen.drawRect(Notifications.offwait_1 + Notifications.offLeft_1, Notifications.offtop_1 + 23, Notifications.offLeft_1 + 120, Notifications.offtop_1 + 25, -16777216);
                ++Notifications.offwait_1;
            }
            else if (Notifications.offXpos_1 == 850 && Notifications.offLeft_1 == 850 && Notifications.offwait_1 == 120 && Notifications.offLeftBack_1 != 970) {
                GuiScreen.drawRect(Notifications.offLeftBack_1, Notifications.offtop_1, Notifications.offLeftBack_1 + 120, Notifications.offtop_1 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeftBack_1, Notifications.offtop_1, Notifications.offLeftBack_1 + 12, Notifications.offtop_1 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_1, Notifications.offLeftBack_1 + 16, Notifications.offtop_1 + 7, 0);
                GuiScreen.drawRect(Notifications.offLeftBack_1 += 5, Notifications.offYpos_1, 850, Notifications.offYpos_1 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_1 == 850 && Notifications.offLeft_1 == 850 && Notifications.offwait_1 == 120 && Notifications.offLeftBack_1 == 970 && Notifications.offXposBack_1 != 970) {
                GuiScreen.drawRect(Notifications.offXposBack_1, Notifications.offYpos_1, Notifications.offXposBack_1 + 120, Notifications.offYpos_1 + 25, Notifications.offcolor);
                Notifications.offXposBack_1 += 5;
            }
            else {
                --Notifications.offcount;
                Notifications.offLeft_1 = 970;
                Notifications.offXpos_1 = 970;
                Notifications.offwait_1 = 0;
                Notifications.offLeftBack_1 = 850;
                Notifications.offXposBack_1 = 850;
                Notifications.offdone_1 = false;
            }
        }
        if (Notifications.offdone_2) {
            if (Notifications.offXpos_2 != 850) {
                GuiScreen.drawRect(Notifications.offXpos_2, Notifications.offYpos_2, Notifications.offXpos_2 + 120, Notifications.offYpos_2 + 25, Notifications.offcolor);
                System.out.println(Notifications.offXpos_2 -= 5);
            }
            else if (Notifications.offXpos_2 == 850 && Notifications.offLeft_2 != 850) {
                GuiScreen.drawRect(Notifications.offLeft_2 -= 5, Notifications.offtop_2, Notifications.offLeft_2 + 120, Notifications.offtop_2 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_2, Notifications.offtop_2, Notifications.offLeft_2 + 12, Notifications.offtop_2 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_2, Notifications.offLeft_2 + 16, Notifications.offtop_2 + 7, 0);
                GuiScreen.drawRect(850, Notifications.offYpos_2, Notifications.offLeft_2, Notifications.offYpos_2 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_2 == 850 && Notifications.offLeft_2 == 850 && Notifications.offwait_2 != 120) {
                GuiScreen.drawRect(Notifications.offLeft_2, Notifications.offtop_2, Notifications.offLeft_2 + 120, Notifications.offtop_2 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_2, Notifications.offtop_2, Notifications.offLeft_2 + 12, Notifications.offtop_2 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_2, Notifications.offLeft_2 + 16, Notifications.offtop_2 + 7, 0);
                GuiScreen.drawRect(Notifications.offwait_2 + Notifications.offLeft_2, Notifications.offtop_2 + 23, Notifications.offLeft_2 + 120, Notifications.offtop_2 + 25, -16777216);
                ++Notifications.offwait_2;
            }
            else if (Notifications.offXpos_2 == 850 && Notifications.offLeft_2 == 850 && Notifications.offwait_2 == 120 && Notifications.offLeftBack_2 != 970) {
                GuiScreen.drawRect(Notifications.offLeftBack_2, Notifications.offtop_2, Notifications.offLeftBack_2 + 120, Notifications.offtop_2 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeftBack_2, Notifications.offtop_2, Notifications.offLeftBack_2 + 12, Notifications.offtop_2 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_2, Notifications.offLeftBack_2 + 16, Notifications.offtop_2 + 7, 0);
                GuiScreen.drawRect(Notifications.offLeftBack_2 += 5, Notifications.offYpos_2, 850, Notifications.offYpos_2 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_2 == 850 && Notifications.offLeft_2 == 850 && Notifications.offwait_2 == 120 && Notifications.offLeftBack_2 == 970 && Notifications.offXposBack_2 != 970) {
                GuiScreen.drawRect(Notifications.offXposBack_2, Notifications.offYpos_2, Notifications.offXposBack_2 + 120, Notifications.offYpos_2 + 25, Notifications.offcolor);
                Notifications.offXposBack_2 += 5;
            }
            else {
                --Notifications.offcount;
                Notifications.offLeft_2 = 970;
                Notifications.offXpos_2 = 970;
                Notifications.offwait_2 = 0;
                Notifications.offLeftBack_2 = 850;
                Notifications.offXposBack_2 = 850;
                Notifications.offdone_2 = false;
            }
        }
        if (Notifications.offdone_3) {
            if (Notifications.offXpos_3 != 850) {
                GuiScreen.drawRect(Notifications.offXpos_3, Notifications.offYpos_3, Notifications.offXpos_3 + 120, Notifications.offYpos_3 + 25, Notifications.offcolor);
                System.out.println(Notifications.offXpos_3 -= 5);
            }
            else if (Notifications.offXpos_3 == 850 && Notifications.offLeft_3 != 850) {
                GuiScreen.drawRect(Notifications.offLeft_3 -= 5, Notifications.offtop_3, Notifications.offLeft_3 + 120, Notifications.offtop_3 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_3, Notifications.offtop_3, Notifications.offLeft_3 + 12, Notifications.offtop_3 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_3, Notifications.offLeft_3 + 16, Notifications.offtop_3 + 7, 0);
                GuiScreen.drawRect(850, Notifications.offYpos_3, Notifications.offLeft_3, Notifications.offYpos_3 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_3 == 850 && Notifications.offLeft_3 == 850 && Notifications.offwait_3 != 120) {
                GuiScreen.drawRect(Notifications.offLeft_3, Notifications.offtop_3, Notifications.offLeft_3 + 120, Notifications.offtop_3 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_3, Notifications.offtop_3, Notifications.offLeft_3 + 12, Notifications.offtop_3 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_3, Notifications.offLeft_3 + 16, Notifications.offtop_3 + 7, 0);
                GuiScreen.drawRect(Notifications.offwait_3 + Notifications.offLeft_3, Notifications.offtop_3 + 23, Notifications.offLeft_3 + 120, Notifications.offtop_3 + 25, -16777216);
                ++Notifications.offwait_3;
            }
            else if (Notifications.offXpos_3 == 850 && Notifications.offLeft_3 == 850 && Notifications.offwait_3 == 120 && Notifications.offLeftBack_3 != 970) {
                GuiScreen.drawRect(Notifications.offLeftBack_3, Notifications.offtop_3, Notifications.offLeftBack_3 + 120, Notifications.offtop_3 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeftBack_3, Notifications.offtop_3, Notifications.offLeftBack_3 + 12, Notifications.offtop_3 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_3, Notifications.offLeftBack_3 + 16, Notifications.offtop_3 + 7, 0);
                GuiScreen.drawRect(Notifications.offLeftBack_3 += 5, Notifications.offYpos_3, 850, Notifications.offYpos_3 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_3 == 850 && Notifications.offLeft_3 == 850 && Notifications.offwait_3 == 120 && Notifications.offLeftBack_3 == 970 && Notifications.offXposBack_3 != 970) {
                GuiScreen.drawRect(Notifications.offXposBack_3, Notifications.offYpos_3, Notifications.offXposBack_3 + 120, Notifications.offYpos_3 + 25, Notifications.offcolor);
                Notifications.offXposBack_3 += 5;
            }
            else {
                --Notifications.offcount;
                Notifications.offLeft_3 = 970;
                Notifications.offXpos_3 = 970;
                Notifications.offwait_3 = 0;
                Notifications.offLeftBack_3 = 850;
                Notifications.offXposBack_3 = 850;
                Notifications.offdone_3 = false;
            }
        }
        if (Notifications.offdone_4) {
            if (Notifications.offXpos_4 != 850) {
                GuiScreen.drawRect(Notifications.offXpos_4, Notifications.offYpos_4, Notifications.offXpos_4 + 120, Notifications.offYpos_4 + 25, Notifications.offcolor);
                System.out.println(Notifications.offXpos_4 -= 5);
            }
            else if (Notifications.offXpos_4 == 850 && Notifications.offLeft_4 != 850) {
                GuiScreen.drawRect(Notifications.offLeft_4 -= 5, Notifications.offtop_4, Notifications.offLeft_4 + 120, Notifications.offtop_4 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_4, Notifications.offtop_4, Notifications.offLeft_4 + 12, Notifications.offtop_4 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_4, Notifications.offLeft_4 + 16, Notifications.offtop_4 + 7, 0);
                GuiScreen.drawRect(850, Notifications.offYpos_4, Notifications.offLeft_4, Notifications.offYpos_4 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_4 == 850 && Notifications.offLeft_4 == 850 && Notifications.offwait_4 != 120) {
                GuiScreen.drawRect(Notifications.offLeft_4, Notifications.offtop_4, Notifications.offLeft_4 + 120, Notifications.offtop_4 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeft_4, Notifications.offtop_4, Notifications.offLeft_4 + 12, Notifications.offtop_4 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_4, Notifications.offLeft_4 + 16, Notifications.offtop_4 + 7, 0);
                GuiScreen.drawRect(Notifications.offwait_4 + Notifications.offLeft_4, Notifications.offtop_4 + 23, Notifications.offLeft_4 + 120, Notifications.offtop_4 + 25, -16777216);
                ++Notifications.offwait_4;
            }
            else if (Notifications.offXpos_4 == 850 && Notifications.offLeft_4 == 850 && Notifications.offwait_4 == 120 && Notifications.offLeftBack_4 != 970) {
                GuiScreen.drawRect(Notifications.offLeftBack_4, Notifications.offtop_4, Notifications.offLeftBack_4 + 120, Notifications.offtop_4 + 25, -1140850689);
                GuiScreen.drawRect(Notifications.offLeftBack_4, Notifications.offtop_4, Notifications.offLeftBack_4 + 12, Notifications.offtop_4 + 25, Notifications.offcolor);
                Notifications.mc.fontRenderer.drawString(Notifications.offtext_4, Notifications.offLeftBack_4 + 16, Notifications.offtop_4 + 7, 0);
                GuiScreen.drawRect(Notifications.offLeftBack_4 += 5, Notifications.offYpos_4, 850, Notifications.offYpos_4 + 25, Notifications.offcolor);
            }
            else if (Notifications.offXpos_4 == 850 && Notifications.offLeft_4 == 850 && Notifications.offwait_4 == 120 && Notifications.offLeftBack_4 == 970 && Notifications.offXposBack_4 != 970) {
                GuiScreen.drawRect(Notifications.offXposBack_4, Notifications.offYpos_4, Notifications.offXposBack_4 + 120, Notifications.offYpos_4 + 25, Notifications.offcolor);
                Notifications.offXposBack_4 += 5;
            }
            else {
                --Notifications.offcount;
                Notifications.offLeft_4 = 970;
                Notifications.offXpos_4 = 970;
                Notifications.offwait_4 = 0;
                Notifications.offLeftBack_4 = 850;
                Notifications.offXposBack_4 = 850;
                Notifications.offdone_4 = false;
            }
        }
    }
    
    @Override
    public void onRender3D() {
    }
    
    static {
        Notifications.ontext_0 = "";
        Notifications.offtext_0 = "";
        Notifications.ontext_1 = "";
        Notifications.offtext_1 = "";
        Notifications.ontext_2 = "";
        Notifications.offtext_2 = "";
        Notifications.ontext_3 = "";
        Notifications.offtext_3 = "";
        Notifications.ontext_4 = "";
        Notifications.offtext_4 = "";
        Notifications.oncolor = -16777216;
        Notifications.offcolor = -16777216;
        Notifications.oncount = 0;
        Notifications.offcount = 0;
        Notifications.onXpos_0 = 970;
        Notifications.onYpos_0 = 300;
        Notifications.onXpos_1 = 970;
        Notifications.onYpos_1 = 275;
        Notifications.onXpos_2 = 970;
        Notifications.onYpos_2 = 250;
        Notifications.onXpos_3 = 970;
        Notifications.onYpos_3 = 225;
        Notifications.onXpos_4 = 970;
        Notifications.onYpos_4 = 200;
        Notifications.onLeft_0 = 970;
        Notifications.ontop_0 = 300;
        Notifications.onLeft_1 = 970;
        Notifications.ontop_1 = 275;
        Notifications.onLeft_2 = 970;
        Notifications.ontop_2 = 250;
        Notifications.onLeft_3 = 970;
        Notifications.ontop_3 = 225;
        Notifications.onLeft_4 = 970;
        Notifications.ontop_4 = 200;
        Notifications.onwait_0 = 0;
        Notifications.onwait_1 = 0;
        Notifications.onwait_2 = 0;
        Notifications.onwait_3 = 0;
        Notifications.onwait_4 = 0;
        Notifications.onLeftBack_0 = 850;
        Notifications.ontopBack_0 = 300;
        Notifications.onLeftBack_1 = 850;
        Notifications.ontopBack_1 = 275;
        Notifications.onLeftBack_2 = 850;
        Notifications.ontopBack_2 = 250;
        Notifications.onLeftBack_3 = 850;
        Notifications.ontopBack_3 = 225;
        Notifications.onLeftBack_4 = 850;
        Notifications.ontopBack_4 = 200;
        Notifications.onXposBack_0 = 850;
        Notifications.onYposBack_0 = 300;
        Notifications.onXposBack_1 = 850;
        Notifications.onYposBack_1 = 275;
        Notifications.onXposBack_2 = 850;
        Notifications.onYposBack_2 = 250;
        Notifications.onXposBack_3 = 850;
        Notifications.onYposBack_3 = 225;
        Notifications.onXposBack_4 = 850;
        Notifications.onYposBack_4 = 200;
        Notifications.ondone_0 = false;
        Notifications.ondone_1 = false;
        Notifications.ondone_2 = false;
        Notifications.ondone_3 = false;
        Notifications.ondone_4 = false;
        Notifications.offXpos_0 = 970;
        Notifications.offYpos_0 = 325;
        Notifications.offXpos_1 = 970;
        Notifications.offYpos_1 = 350;
        Notifications.offXpos_2 = 970;
        Notifications.offYpos_2 = 375;
        Notifications.offXpos_3 = 970;
        Notifications.offYpos_3 = 400;
        Notifications.offXpos_4 = 970;
        Notifications.offYpos_4 = 425;
        Notifications.offLeft_0 = 970;
        Notifications.offtop_0 = 325;
        Notifications.offLeft_1 = 970;
        Notifications.offtop_1 = 350;
        Notifications.offLeft_2 = 970;
        Notifications.offtop_2 = 375;
        Notifications.offLeft_3 = 970;
        Notifications.offtop_3 = 400;
        Notifications.offLeft_4 = 970;
        Notifications.offtop_4 = 425;
        Notifications.offwait_0 = 0;
        Notifications.offwait_1 = 0;
        Notifications.offwait_2 = 0;
        Notifications.offwait_3 = 0;
        Notifications.offwait_4 = 0;
        Notifications.offLeftBack_0 = 850;
        Notifications.offtopBack_0 = 325;
        Notifications.offLeftBack_1 = 850;
        Notifications.offtopBack_1 = 350;
        Notifications.offLeftBack_2 = 850;
        Notifications.offtopBack_2 = 375;
        Notifications.offLeftBack_3 = 850;
        Notifications.offtopBack_3 = 400;
        Notifications.offLeftBack_4 = 850;
        Notifications.offtopBack_4 = 425;
        Notifications.offXposBack_0 = 850;
        Notifications.offYposBack_0 = 300;
        Notifications.offXposBack_1 = 850;
        Notifications.offYposBack_1 = 275;
        Notifications.offXposBack_2 = 850;
        Notifications.offYposBack_2 = 250;
        Notifications.offXposBack_3 = 850;
        Notifications.offYposBack_3 = 225;
        Notifications.offXposBack_4 = 850;
        Notifications.offYposBack_4 = 200;
        Notifications.offdone_0 = false;
        Notifications.offdone_1 = false;
        Notifications.offdone_2 = false;
        Notifications.offdone_3 = false;
        Notifications.offdone_4 = false;
    }
}
