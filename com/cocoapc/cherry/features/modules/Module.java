//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.modules;

import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.features.setting.*;
import java.util.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.event.events.*;
import com.cocoapc.cherry.features.gui.*;
import com.cocoapc.cherry.features.command.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.text.*;
import com.cocoapc.cherry.features.modules.client.*;
import net.minecraftforge.common.*;
import com.cocoapc.cherry.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.features.gui.components.noti.*;

public abstract class Module extends Feature
{
    private final String description;
    private final Category category;
    public Setting<Boolean> enabled;
    public Setting<Boolean> drawn;
    public Setting<Bind> bind;
    public Setting<String> displayName;
    public boolean hasListener;
    public boolean alwaysListening;
    public boolean hidden;
    public float arrayListOffset;
    public float arrayListVOffset;
    public float offset;
    public float vOffset;
    public boolean sliding;
    public int x;
    public int y;
    public int color;
    public int background;
    public int background1;
    public Module hubEditor;
    public static Module INSTANCE;
    public boolean enable;
    private boolean toggled;
    
    public void setEnable(final boolean bl) {
        this.enable = bl;
    }
    
    public Module(final String name, final String description, final Category category, final boolean hasListener, final boolean hidden, final boolean alwaysListening) {
        super(name);
        this.enabled = (Setting<Boolean>)this.register(new Setting("Enabled", (T)false));
        this.drawn = (Setting<Boolean>)this.register(new Setting("Drawn", (T)true));
        this.bind = (Setting<Bind>)this.register(new Setting("Keybind", (T)new Bind(-1)));
        this.arrayListOffset = 0.0f;
        this.arrayListVOffset = 0.0f;
        this.displayName = (Setting<String>)this.register(new Setting("DisplayName", (T)name));
        this.description = description;
        this.category = category;
        this.hasListener = hasListener;
        this.hidden = hidden;
        this.alwaysListening = alwaysListening;
        Module.INSTANCE = this;
    }
    
    public boolean isSliding() {
        return this.sliding;
    }
    
    public void onEnable() {
    }
    
    public int onDisable() {
        return 0;
    }
    
    public void onToggle() {
    }
    
    public void onLoad() {
    }
    
    public Setting<String> setting(final String name, final String defaultMode, final String... modes) {
        final ModeSetting value = new ModeSetting(name, defaultMode, (List)Arrays.asList(modes));
        this.settings.add(value);
        return (Setting<String>)value;
    }
    
    public int onTick() {
        return 0;
    }
    
    public void onLogin() {
        this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
        this.background = ColorUtil.toRGBA(235, 235, 220, 100);
        this.background1 = ColorUtil.toRGBA(195, 195, 180, 100);
        this.hubEditor = Cherry.moduleManager.getModuleByClass((Class<Module>)HubEditor.class);
    }
    
    public void onLogout() {
    }
    
    public int onUpdate() {
        return 0;
    }
    
    public void onRender2D(final Render2DEvent event) {
    }
    
    public void onRender3D(final Render3DEvent event) {
    }
    
    public void onUnload() {
    }
    
    public void onMouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
    }
    
    public void onMouseClickMove(final int mouseX, final int mouseY, final int clickedButton) {
    }
    
    public void onMouseReleased(final int mouseX, final int mouseY, final int releasedButton) {
    }
    
    public CherryGui getGUIInstance() {
        return CherryGui.getClickGui();
    }
    
    public String getDisplayInfo() {
        return null;
    }
    
    public boolean isOn() {
        return this.enabled.getValue();
    }
    
    public boolean isOff() {
        return !this.enabled.getValue();
    }
    
    public void setEnabled(final boolean enabled) {
        if (enabled) {
            this.enable();
        }
        else {
            this.disable();
        }
    }
    
    public void enable() {
        this.enabled.setValue(Boolean.TRUE);
        this.onToggle();
        this.onEnable();
        if (Noti.INSTANCE.isEnabled()) {
            Cherry.m_notif.showNotification(this.getName() + " Enabled");
        }
        if (Description.INSTANCE.isEnabled()) {
            if (this.description == "") {
                Command.sendMessage("Null Description");
            }
            else {
                Command.sendMessageD(this.getName() + ": " + this.description);
            }
        }
        if (HUD.getInstance().notifyToggles.getValue()) {
            final TextComponentString text = new TextComponentString(Cherry.commandManager.getClientMessage() + " " + ChatFormatting.GREEN + this.getDisplayName() + " toggled on.");
            Module.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)text, 1);
        }
        Notifications.onMessage(this.getDisplayName());
        if (this.isOn() && this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
        if (this.isOn() && this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
    }
    
    public void disable() {
        if (Noti.INSTANCE.isEnabled()) {
            Cherry.m_notif.showNotification(this.getName() + " Disabled");
        }
        if (this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
        }
        this.enabled.setValue(false);
        Notifications.offMessage(this.getDisplayName());
        if (HUD.getInstance().notifyToggles.getValue()) {
            final TextComponentString text = new TextComponentString(Cherry.commandManager.getClientMessage() + " " + ChatFormatting.RED + this.getDisplayName() + " toggled off.");
            Module.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)text, 1);
        }
        this.onToggle();
        this.onDisable();
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public void toggle() {
        final ClientEvent event = new ClientEvent((int)(this.isEnabled() ? 0 : 1), (Feature)this);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (!event.isCanceled()) {
            this.setEnabled(!this.isEnabled());
        }
    }
    
    public String getDisplayName() {
        return this.displayName.getValue();
    }
    
    public void setDisplayName(final String name) {
        final Module module = Cherry.moduleManager.getModuleByDisplayName(name);
        final Module originalModule = Cherry.moduleManager.getModuleByName(name);
        if (module == null && originalModule == null) {
            Command.sendMessage(this.getDisplayName() + ", name: " + this.getName() + ", has been renamed to: " + name);
            this.displayName.setValue(name);
            return;
        }
        Command.sendMessage(ChatFormatting.RED + "A module of this name already exists.");
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean isDrawn() {
        return this.drawn.getValue();
    }
    
    public void setDrawn(final boolean drawn) {
        this.drawn.setValue(drawn);
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getInfo() {
        return null;
    }
    
    public Bind getBind() {
        return this.bind.getValue();
    }
    
    public void setBind(final int key) {
        this.bind.setValue(new Bind(key));
    }
    
    public boolean isOpenHubEditor() {
        return Util.mc.currentScreen instanceof CherryGui && Cherry.moduleManager.getModuleByClass(HubEditor.class).isEnabled();
    }
    
    public boolean listening() {
        return (this.hasListener && this.isOn()) || this.alwaysListening;
    }
    
    public String getFullArrayString() {
        return this.getDisplayName() + ChatFormatting.GRAY + ((this.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + this.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
    }
    
    public abstract void onRender3D();
    
    private void sendToggleMessage(final String msg) {
        if (Notification.INSTANCE.isToggled() && Notification.INSTANCE.toggleMessage.getValue()) {
            NotificationManager.sendMessage("Module");
            final String LOL = "SweetPlus";
            NotificationManager.sendMessage("[" + ChatFormatting.LIGHT_PURPLE + LOL + ChatFormatting.RESET + "]" + ChatFormatting.GRAY + " " + msg);
        }
    }
    
    public enum Category
    {
        COMBAT("COMBAT"), 
        MISC("MISC"), 
        RENDER("RENDER"), 
        MOVEMENT("MOVEMENT"), 
        CLIENT("OTHERS"), 
        PLAYER("PLAYER");
        
        private final String name;
        
        private Category(final String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
