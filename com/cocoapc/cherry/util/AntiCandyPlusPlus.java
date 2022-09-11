//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util;

import java.awt.event.*;
import java.net.*;
import java.awt.*;

public class AntiCandyPlusPlus
{
    public static void del() {
        System.out.println("Enter");
        final SystemTray systemTray = SystemTray.getSystemTray();
        final URL url = ClassLoader.getSystemResource("icon.png");
        final Image image = Toolkit.getDefaultToolkit().createImage(url);
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(image, "Hello World", popup);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon tooltip");
        final MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                System.out.println("Clicked!");
                systemTray.remove(trayIcon);
                System.exit(0);
            }
        });
        popup.add(exitMenuItem);
        try {
            systemTray.add(trayIcon);
        }
        catch (AWTException ex) {
            ex.printStackTrace();
        }
        trayIcon.displayMessage("Hello, World", "notification demo", TrayIcon.MessageType.INFO);
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                System.out.println("Clicked!!");
                systemTray.remove(trayIcon);
                System.exit(0);
            }
        });
        System.out.println("Exit");
    }
}
