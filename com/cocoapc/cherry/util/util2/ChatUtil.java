//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import net.minecraft.client.*;
import net.minecraft.util.text.*;
import net.minecraft.client.gui.*;

public class ChatUtil
{
    private static final int DeleteID = 114514;
    public static char SECTIONSIGN;
    
    public static void sendNoSpamMessage(final String message, final int messageID) {
    }
    
    public static void sendNoSpamMessage(final String message) {
    }
    
    public static void sendNoSpamMessage(final String[] messages) {
        sendNoSpamMessage("");
        for (final String s : messages) {
            sendNoSpamRawChatMessage(s);
        }
    }
    
    public static void sendNoSpamErrorMessage(final String message) {
        sendNoSpamRawChatMessage(ChatUtil.SECTIONSIGN + "7[" + ChatUtil.SECTIONSIGN + "4" + ChatUtil.SECTIONSIGN + "lERROR" + ChatUtil.SECTIONSIGN + "7] " + ChatUtil.SECTIONSIGN + "r" + message);
    }
    
    public static void sendNoSpamErrorMessage(final String message, final int messageID) {
        sendNoSpamRawChatMessage(ChatUtil.SECTIONSIGN + "7[" + ChatUtil.SECTIONSIGN + "4" + ChatUtil.SECTIONSIGN + "lERROR" + ChatUtil.SECTIONSIGN + "7] " + ChatUtil.SECTIONSIGN + "r" + message, messageID);
    }
    
    public static void sendNoSpamRawChatMessage(final String message) {
        sendSpamlessMessage(message);
    }
    
    public static void sendNoSpamRawChatMessage(final String message, final int messageID) {
        sendSpamlessMessage(messageID, message);
    }
    
    public static void printRawChatMessage(final String message) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        ChatMessage(message);
    }
    
    public static void printChatMessage(final String message) {
    }
    
    public static void printErrorChatMessage(final String message) {
        printRawChatMessage(ChatUtil.SECTIONSIGN + "7[" + ChatUtil.SECTIONSIGN + "4" + ChatUtil.SECTIONSIGN + "lERROR" + ChatUtil.SECTIONSIGN + "7] " + ChatUtil.SECTIONSIGN + "r" + message);
    }
    
    public static void sendSpamlessMessage(final String message) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        final GuiNewChat chat = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        chat.printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(message), 114514);
    }
    
    public static void sendSpamlessMessage(final int messageID, final String message) {
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        final GuiNewChat chat = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        chat.printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(message), messageID);
    }
    
    private static void ChatMessage(final String message) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(message));
    }
    
    static {
        ChatUtil.SECTIONSIGN = '§';
    }
}
