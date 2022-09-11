//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry;

import net.minecraftforge.fml.common.*;
import com.cocoapc.cherry.util.util2.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.cocoapc.cherry.manager.*;
import java.awt.*;
import com.cocoapc.cherry.features.gui.custom.*;
import net.minecraftforge.client.event.*;
import com.cocoapc.cherry.util.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.common.*;
import org.lwjgl.opengl.*;
import com.cocoapc.cherry.fonts.*;
import java.io.*;
import org.apache.logging.log4j.*;

@Mod(modid = "cherry", name = "CherryClient", version = "5.0")
public class Cherry
{
    public static final String MODID = "cherry";
    public static final String MODNAME = "CherryClient";
    public static final String MODVER = "5.0";
    public static final String MODVER2 = "4";
    public static final Logger LOGGER;
    public static CommandManager commandManager;
    public static TextManager2 textManager2;
    public static FriendManager friendManager;
    public static FontManagers m_font;
    public static NotiManager m_notif;
    public static GuiFont GUI_FONT_MANAGER;
    public static MenuFont MENU_FONT_MANAGER;
    public static ModuleManager moduleManager;
    public static FriendManager FRIEND_MANAGER;
    public static PacketManager packetManager;
    public static ColorManager colorManager;
    public static DonatorFont DONATOR_FONT_MANAGER;
    public static HoleManager holeManager;
    public static InventoryManager inventoryManager;
    public static PotionManager potionManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ReloadManager reloadManager;
    public static SongManager SONG_MANAGER;
    public static SongManager2 SONG_MANAGER2;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static eventManager2 eventManager2;
    public static TextManager textManager;
    public static SafetyManager safetyManager;
    public static TrayIcon trayIcon;
    public static GuiCustomMainScreen customMainScreen;
    @Mod.Instance
    public static Cherry INSTANCE;
    private static boolean unloaded;
    private static Object SongManager;
    private static Object SongManager2;
    public static String IllllllllllllL;
    
    public static void load() {
        Cherry.LOGGER.info("");
        Cherry.unloaded = false;
        if (Cherry.reloadManager != null) {
            Cherry.reloadManager.unload();
            Cherry.reloadManager = null;
        }
        Cherry.SongManager = new SongManager();
        Cherry.SongManager2 = new SongManager2();
        Cherry.textManager = new TextManager();
        Cherry.commandManager = new CommandManager();
        Cherry.SONG_MANAGER = new SongManager();
        Cherry.SONG_MANAGER2 = new SongManager2();
        Cherry.friendManager = new FriendManager();
        Cherry.moduleManager = new ModuleManager();
        Cherry.m_font = new FontManagers();
        Cherry.m_notif = new NotiManager();
        Cherry.rotationManager = new RotationManager();
        Cherry.packetManager = new PacketManager();
        Cherry.eventManager = new EventManager();
        Cherry.eventManager2 = new eventManager2();
        Cherry.speedManager = new SpeedManager();
        Cherry.GUI_FONT_MANAGER = new GuiFont();
        Cherry.MENU_FONT_MANAGER = new MenuFont();
        Cherry.potionManager = new PotionManager();
        Cherry.inventoryManager = new InventoryManager();
        Cherry.DONATOR_FONT_MANAGER = new DonatorFont();
        Cherry.serverManager = new ServerManager();
        Cherry.FRIEND_MANAGER = new FriendManager();
        Cherry.fileManager = new FileManager();
        Cherry.colorManager = new ColorManager();
        Cherry.positionManager = new PositionManager();
        Cherry.configManager = new ConfigManager();
        Cherry.holeManager = new HoleManager();
        Cherry.safetyManager = new SafetyManager();
        Cherry.m_notif = new NotiManager();
        Cherry.LOGGER.info("Managers loaded.");
        Cherry.moduleManager.init();
        Cherry.LOGGER.info("Modules loaded.");
        Cherry.configManager.init();
        Cherry.eventManager.init();
        Cherry.LOGGER.info("EventManager loaded.");
        Cherry.textManager.init(true);
        Cherry.moduleManager.onLoad();
        Cherry.LOGGER.info("");
    }
    
    public static void unload(final boolean unload) {
        Cherry.LOGGER.info("");
        if (unload) {
            (Cherry.reloadManager = new ReloadManager()).init((Cherry.commandManager != null) ? Cherry.commandManager.getPrefix() : "-");
        }
        onUnload();
        Cherry.eventManager = null;
        Cherry.eventManager2 = null;
        Cherry.holeManager = null;
        Cherry.moduleManager = null;
        Cherry.serverManager = null;
        Cherry.colorManager = null;
        Cherry.speedManager = null;
        Cherry.rotationManager = null;
        Cherry.positionManager = null;
        Cherry.commandManager = null;
        Cherry.configManager = null;
        Cherry.textManager2 = null;
        Cherry.fileManager = null;
        Cherry.textManager = null;
        Cherry.friendManager = null;
        Cherry.potionManager = null;
        Cherry.inventoryManager = null;
        Cherry.safetyManager = null;
        Cherry.m_notif = null;
        Cherry.LOGGER.info("");
    }
    
    public static void reload() {
        unload(false);
        load();
    }
    
    public static void onUnload() {
        if (!Cherry.unloaded) {
            Cherry.eventManager.onUnload();
            Cherry.moduleManager.onUnload();
            Cherry.configManager.saveConfig(Cherry.configManager.config.replaceFirst("Cherry/", ""));
            Cherry.moduleManager.onUnloadPost();
            Cherry.unloaded = true;
        }
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGuiOpened(final GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu || (event.getGui() == null && Util.mc.player == null)) {
            event.setGui((GuiScreen)new GuiCustomMainScreen());
        }
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) throws IOException {
        MinecraftForge.EVENT_BUS.register((Object)this);
        Display.setTitle("Cherry Client 5.0 / Minecraft 1.12.2");
        load();
        Cherry.m_font.load();
        FontUtil.bootstrap();
    }
    
    static {
        LOGGER = LogManager.getLogger("Cherry");
        Cherry.unloaded = false;
        Cherry.IllllllllllllL = "t";
    }
}
