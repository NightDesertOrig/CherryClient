//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.manager;

import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.features.modules.*;
import com.cocoapc.cherry.features.modules.hub.*;
import com.cocoapc.cherry.util.util2.*;
import com.cocoapc.cherry.features.modules.render.*;
import com.cocoapc.cherry.features.modules.player.*;
import com.cocoapc.cherry.features.modules.misc.*;
import com.cocoapc.cherry.features.modules.movement.*;
import com.cocoapc.cherry.features.modules.client.*;
import com.cocoapc.cherry.features.modules.combat.*;
import net.minecraftforge.common.*;
import java.util.function.*;
import com.cocoapc.cherry.event.events.*;
import java.util.stream.*;
import java.util.*;
import org.lwjgl.input.*;
import com.cocoapc.cherry.features.gui.*;
import com.mojang.realmsclient.gui.*;
import com.cocoapc.cherry.util.*;
import com.cocoapc.cherry.*;
import java.util.concurrent.*;

public class ModuleManager extends Feature
{
    public ArrayList<Module> modules;
    public List<Module> sortedModules;
    public List<String> sortedModulesABC;
    public List<Module> alphabeticallySortedModules;
    
    public ModuleManager() {
        this.modules = new ArrayList<Module>();
        this.sortedModules = new ArrayList<Module>();
        this.sortedModulesABC = new ArrayList<String>();
        this.modules = new ArrayList<Module>();
        this.sortedModules = new ArrayList<Module>();
        this.alphabeticallySortedModules = new ArrayList<Module>();
    }
    
    public void init() {
        this.modules.add((Module)new ClickGui());
        this.modules.add((Module)new FontMod());
        this.modules.add((Module)new ExtraTab());
        this.modules.add((Module)new HUD());
        this.modules.add((Module)new HoleESP());
        this.modules.add((Module)new SmallShield());
        this.modules.add((Module)new HandChams());
        this.modules.add((Module)new FakePlayer());
        this.modules.add((Module)new MultiTask());
        this.modules.add((Module)new Speedmine());
        this.modules.add((Module)new ReverseStep());
        this.modules.add((Module)new NoHandShake());
        this.modules.add((Module)new BuildHeight());
        this.modules.add((Module)new MCF());
        this.modules.add((Module)new PearlNotify());
        this.modules.add((Module)new ToolTips());
        this.modules.add((Module)new PopCounter());
        this.modules.add((Module)new Offhand());
        this.modules.add((Module)new Surround());
        this.modules.add((Module)new AutoTrap());
        this.modules.add((Module)new AutoCrystal());
        this.modules.add((Module)new Killaura());
        this.modules.add((Module)new HoleFiller());
        this.modules.add((Module)new AutoArmor());
        this.modules.add((Module)new FastPlace());
        this.modules.add((Module)new Selftrap());
        this.modules.add((Module)new ViewModel());
        this.modules.add((Module)new Chat());
        this.modules.add((Module)new StepOld());
        this.modules.add((Module)new VanillaSpeed());
        this.modules.add((Module)new HoleTP());
        this.modules.add((Module)new Velocity());
        this.modules.add((Module)new WaterMarkNew());
        this.modules.add((Module)new Components());
        this.modules.add((Module)new SelfAnvil());
        this.modules.add((Module)new SilentXP());
        this.modules.add((Module)new Fullbright());
        this.modules.add((Module)new NoRender());
        this.modules.add((Module)new NoSlowDown());
        this.modules.add((Module)new MainMenu());
        this.modules.add((Module)new Kill());
        this.modules.add((Module)new AutoRespawn());
        this.modules.add((Module)new PopChams());
        this.modules.add((Module)new MoonBreak());
        this.modules.add((Module)new Tracer());
        this.modules.add((Module)new TestNametags());
        this.modules.add((Module)new CEV());
        this.modules.add((Module)new CIV());
        this.modules.add((Module)new Chams());
        this.modules.add((Module)new CevBlocker());
        this.modules.add((Module)new Logo());
        this.modules.add((Module)new TEST());
        this.modules.add((Module)new AirJump());
        this.modules.add((Module)new AimBug());
        this.modules.add((Module)new Kit());
        this.modules.add((Module)new NewBlocker());
        this.modules.add((Module)new PacketCanceller());
        this.modules.add((Module)new TrapPhaseRewrite());
        this.modules.add((Module)new HubEditor());
        this.modules.add((Module)new Welcomer());
        this.modules.add((Module)new SelfParticles());
        this.modules.add((Module)new FakePlayerPlus());
        this.modules.add((Module)new Bow32k());
        this.modules.add((Module)new CevBreaker());
        this.modules.add((Module)new NoteBotBug());
        this.modules.add((Module)new BurrowESP());
        this.modules.add((Module)new SwordRotator());
        this.modules.add((Module)new SussySkin());
        this.modules.add((Module)new AnvilTrap());
        this.modules.add((Module)new AutoAmogus());
        this.modules.add((Module)new AutoBedTrap());
        this.modules.add((Module)new PluginsGrabber());
        this.modules.add((Module)new AnvilAura());
        this.modules.add(new PlayerSpoofer());
        this.modules.add((Module)new TrapPhasee());
        this.modules.add((Module)new BowSpam());
        this.modules.add((Module)new AutoBuilder());
        this.modules.add((Module)new Flys());
        this.modules.add((Module)new NoFall());
        this.modules.add((Module)new BlockBug());
        this.modules.add((Module)new SlowSwing());
        this.modules.add((Module)new AutoChest());
        this.modules.add((Module)new Description());
        this.modules.add((Module)new ExplosionChams());
        this.modules.add((Module)new PhaseWalk());
        this.modules.add((Module)new Spammer());
        this.modules.add((Module)new Announcer());
        this.modules.add((Module)new CoordMiss());
        this.modules.add((Module)new FakeChat());
        this.modules.add((Module)new DiscordSend());
        this.modules.add((Module)new Slime());
        this.modules.add((Module)new DiscordRPC());
        this.modules.add((Module)new Civbreaker());
        this.modules.add((Module)new Hudtest());
        this.modules.add((Module)new Noti());
        this.modules.add((Module)new Targetfollow());
        this.modules.add((Module)new draw());
        this.modules.add((Module)new TextLogo());
        this.modules.add((Module)new Blocker());
        this.modules.add((Module)new AutoHoleFill());
        this.modules.add((Module)new Scafforld());
        this.modules.add((Module)new SelfBow());
        this.modules.add((Module)new InstantMine());
        this.modules.add((Module)new NewAutoCity());
        this.modules.add((Module)new SlientPix());
        this.modules.add((Module)new AutoObby());
        this.modules.add((Module)new Packetfly());
        this.modules.add((Module)new AutoTrapBurrow());
        this.modules.add((Module)new ArrayLists());
        this.modules.add((Module)new AutoCityRewrite());
    }
    
    public Module getModuleByName(final String name) {
        for (final Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    public <T extends Module> T getModuleByClass(final Class<T> clazz) {
        for (final Module module : this.modules) {
            if (!clazz.isInstance(module)) {
                continue;
            }
            return (T)module;
        }
        return null;
    }
    
    public void enableModule(final Class<Module> clazz) {
        final Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.enable();
        }
    }
    
    public void disableModule(final Class<Module> clazz) {
        final Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.disable();
        }
    }
    
    public void enableModule(final String name) {
        final Module module = this.getModuleByName(name);
        if (module != null) {
            module.enable();
        }
    }
    
    public void disableModule(final String name) {
        final Module module = this.getModuleByName(name);
        if (module != null) {
            module.disable();
        }
    }
    
    public boolean isModuleEnabled(final String name) {
        final Module module = this.getModuleByName(name);
        return module != null && module.isOn();
    }
    
    public Module getModuleByDisplayName(final String displayName) {
        for (final Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(displayName)) {
                continue;
            }
            return module;
        }
        return null;
    }
    
    public ArrayList<Module> getEnabledModules() {
        final ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (final Module module : this.modules) {
            if (!module.isEnabled()) {
                continue;
            }
            enabledModules.add(module);
        }
        return enabledModules;
    }
    
    public ArrayList<String> getEnabledModulesName() {
        final ArrayList<String> enabledModules = new ArrayList<String>();
        for (final Module module : this.modules) {
            if (module.isEnabled()) {
                if (!module.isDrawn()) {
                    continue;
                }
                enabledModules.add(module.getFullArrayString());
            }
        }
        return enabledModules;
    }
    
    public ArrayList<Module> getModulesByCategory(final Module.Category category) {
        final ArrayList<Module> modulesCategory = new ArrayList<Module>();
        final ArrayList<Module> list;
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                list.add(module);
            }
            return;
        });
        return modulesCategory;
    }
    
    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }
    
    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(MinecraftForge.EVENT_BUS::register);
        this.modules.forEach(Module::onLoad);
    }
    
    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }
    
    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }
    
    public void onRender2D(final Render2DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }
    
    public void onRender3D(final Render3DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }
    
    public void sortModules(final boolean reverse) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (reverse ? -1 : 1))).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
    }
    
    public void sortModulesABC() {
        (this.sortedModulesABC = new ArrayList<String>(this.getEnabledModulesName())).sort(String.CASE_INSENSITIVE_ORDER);
    }
    
    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }
    
    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }
    
    public void onUnload() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }
    
    public void onUnloadPost() {
        for (final Module module : this.modules) {
            module.enabled.setValue((Object)false);
        }
    }
    
    public void onKeyPressed(final int eventKey) {
        if (eventKey == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof CherryGui) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) {
                module.toggle();
            }
        });
    }
    
    private class Animation extends Thread
    {
        public Module module;
        public float offset;
        public float vOffset;
        ScheduledExecutorService service;
        
        public Animation() {
            super("Animation");
            this.service = Executors.newSingleThreadScheduledExecutor();
        }
        
        @Override
        public void run() {
            if (HUD.getInstance().renderingMode.getValue() == HUD.RenderingMode.Length) {
                for (final Module module : ModuleManager.this.sortedModules) {
                    final String text = module.getDisplayName() + ChatFormatting.GRAY + ((module.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                    module.offset = ModuleManager.this.renderer.getStringWidth(text) / (float)HUD.getInstance().animationHorizontalTime.getValue();
                    module.vOffset = ModuleManager.this.renderer.getFontHeight() / (float)HUD.getInstance().animationVerticalTime.getValue();
                    if (module.isEnabled() && (int)HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (module.arrayListOffset <= module.offset) {
                            continue;
                        }
                        if (Util.mc.world == null) {
                            continue;
                        }
                        final Module module3 = module;
                        module3.arrayListOffset -= module.offset;
                        module.sliding = true;
                    }
                    else {
                        if (!module.isDisabled()) {
                            continue;
                        }
                        if ((int)HUD.getInstance().animationHorizontalTime.getValue() == 1) {
                            continue;
                        }
                        if (module.arrayListOffset < ModuleManager.this.renderer.getStringWidth(text) && Util.mc.world != null) {
                            final Module module4 = module;
                            module4.arrayListOffset += module.offset;
                            module.sliding = true;
                        }
                        else {
                            module.sliding = false;
                        }
                    }
                }
            }
            else {
                for (final String e : ModuleManager.this.sortedModulesABC) {
                    final Module module2 = Cherry.moduleManager.getModuleByName(e);
                    final String text2 = module2.getDisplayName() + ChatFormatting.GRAY + ((module2.getDisplayInfo() != null) ? (" [" + ChatFormatting.WHITE + module2.getDisplayInfo() + ChatFormatting.GRAY + "]") : "");
                    module2.offset = ModuleManager.this.renderer.getStringWidth(text2) / (float)HUD.getInstance().animationHorizontalTime.getValue();
                    module2.vOffset = ModuleManager.this.renderer.getFontHeight() / (float)HUD.getInstance().animationVerticalTime.getValue();
                    if (module2.isEnabled() && (int)HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (module2.arrayListOffset <= module2.offset) {
                            continue;
                        }
                        if (Util.mc.world == null) {
                            continue;
                        }
                        final Module module5 = module2;
                        module5.arrayListOffset -= module2.offset;
                        module2.sliding = true;
                    }
                    else {
                        if (!module2.isDisabled()) {
                            continue;
                        }
                        if ((int)HUD.getInstance().animationHorizontalTime.getValue() == 1) {
                            continue;
                        }
                        if (module2.arrayListOffset < ModuleManager.this.renderer.getStringWidth(text2) && Util.mc.world != null) {
                            final Module module6 = module2;
                            module6.arrayListOffset += module2.offset;
                            module2.sliding = true;
                        }
                        else {
                            module2.sliding = false;
                        }
                    }
                }
            }
        }
        
        @Override
        public void start() {
            System.out.println("Starting animation thread.");
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
    }
}
