//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util.util2;

import net.minecraft.util.*;
import javax.swing.*;
import java.awt.*;

class JSample6_1 extends JFrame
{
    public static void main(final String[] args) {
        final JSample6_1 frame = new JSample6_1("MyTitle");
        frame.setVisible(true);
    }
    
    JSample6_1(final String title) {
        this.setTitle(title);
        this.setBounds(100, 100, 600, 400);
        this.setDefaultCloseOperation(3);
        final ResourceLocation icon1 = new ResourceLocation("textures/sweet.png");
        final ResourceLocation icon2 = new ResourceLocation("textures/sweet.png");
        final ImageIcon icon3 = new ImageIcon(String.valueOf(icon1));
        final ImageIcon icon4 = new ImageIcon(String.valueOf(icon2));
        final JLabel label1 = new JLabel(icon3);
        final JLabel label2 = new JLabel();
        label2.setIcon(icon4);
        final JPanel p = new JPanel();
        p.add(label1);
        p.add(label2);
        final Container contentPane = this.getContentPane();
        contentPane.add(p, "Center");
    }
}
