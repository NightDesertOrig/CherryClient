//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules.render;

public class ESSP extends RuntimeException
{
    public ESSP(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }
    
    @Override
    public String toString() {
        return "Fuck off nigga!";
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
