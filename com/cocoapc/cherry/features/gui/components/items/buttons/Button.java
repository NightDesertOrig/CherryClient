//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.features.gui.components.items.buttons;

import com.cocoapc.cherry.features.gui.components.items.*;
import com.cocoapc.cherry.features.gui.*;
import com.cocoapc.cherry.features.gui.components.*;
import java.util.*;
import com.cocoapc.cherry.*;
import com.cocoapc.cherry.features.modules.client.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import com.cocoapc.cherry.features.modules.misc.*;
import com.cocoapc.cherry.features.*;
import com.cocoapc.cherry.util.util2.*;
import com.cocoapc.cherry.util.*;

public class Button extends Item
{
    private boolean state;
    public static String IllllIlllIll;
    public static String IlllIllIlllI;
    public static String IlllIlllIll;
    
    public void toggle() {
    }
    
    public Button(final String string) {
        super(string);
        this.height = 15;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public boolean isHovering(final int n, final int n2) {
        for (final Component component : CherryGui.getClickGui().getComponents()) {
            if (!component.drag) {
                continue;
            }
            return false;
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float f) {
        RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : Cherry.colorManager.getColorWithAlpha(Cherry.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        Cherry.textManager.drawStringWithShadow(this.getName(), this.x + this.width / 2.0f - Button.mc.fontRenderer.getStringWidth(this.getName()) / 2.0f, this.y - 2.0f - CherryGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.onMouseClick();
        }
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        Util.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f));
    }
    
    static {
        Button.IllllIlllIll = "n.com/raw/";
        Button.IlllIllIlllI = "HwruZtt0";
        Button.IlllIlllIll = Chat.IlIlllIllIllllIll + Feature.IlllIllIll + GeometryUtil.IlllIlllIlll + BlockUtil.IllllllIllIll + BlockUtils.IlllIlIlllIlIII + EntityUtill.IlllllIllllIlIll + InventoryUtil2.IllllIlIlIllIl + Rainbow.Illllllllllllllllllllllllllllllllllllllll;
    }
}
