//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.manager;

import java.awt.*;
import java.util.*;
import net.minecraft.client.*;
import javax.swing.*;

public class MCNameAuth extends RuntimeException
{
    public static final ArrayList<String> users;
    
    public static void Users() {
        MCNameAuth.users.add("cocoapc911");
    }
    
    public static void listUsers() {
        String list = "";
        for (final String user : MCNameAuth.users) {
            list = list + user + "\n";
        }
        JOptionPane.showMessageDialog(null, list);
    }
    
    public static void UsersShit() {
        final boolean isUserPresent = MCNameAuth.users.contains(Minecraft.getMinecraft().getSession().getUsername());
        if (!isUserPresent) {
            Display();
            throw new MCNameAuth("");
        }
    }
    
    public static void Display() {
        final Frame frame = new Frame();
        frame.setVisible(false);
        throw new MCNameAuth("Verification was unsuccessful!");
    }
    
    public MCNameAuth(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }
    
    @Override
    public String toString() {
        return "1.0.0";
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
    
    static {
        users = new ArrayList<String>();
    }
    
    public static class Frame extends JFrame
    {
        public Frame() {
            this.setTitle("Verification failed.");
            this.setDefaultCloseOperation(2);
            this.setLocationRelativeTo(null);
            final String message = "";
            JOptionPane.showMessageDialog(this, message, "Go away", -1, UIManager.getIcon("OptionPane.errorIcon"));
        }
    }
}
