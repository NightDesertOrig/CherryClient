//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.manager;

import com.cocoapc.cherry.features.*;
import net.minecraft.entity.player.*;
import com.cocoapc.cherry.features.setting.*;
import java.util.function.*;
import com.cocoapc.cherry.util.*;
import java.util.*;

public class FriendManager extends Feature
{
    private static List<Friend> friends;
    
    public FriendManager() {
        super("Friends");
    }
    
    public boolean isFriend(final String name) {
        this.cleanFriends();
        return FriendManager.friends.stream().anyMatch(friend -> friend.username.equalsIgnoreCase(name));
    }
    
    public boolean isFriend(final EntityPlayer player) {
        return this.isFriend(player.getName());
    }
    
    public boolean FE(final EntityPlayer player) {
        return FriendManager.friends.contains(player.getDisplayNameString().toLowerCase());
    }
    
    public void addFriend(final String name) {
        final Friend friend = this.getFriendByName(name);
        if (friend != null) {
            FriendManager.friends.add(friend);
        }
        this.cleanFriends();
    }
    
    public void removeFriend(final String name) {
        this.cleanFriends();
        for (final Friend friend : FriendManager.friends) {
            if (!friend.getUsername().equalsIgnoreCase(name)) {
                continue;
            }
            FriendManager.friends.remove(friend);
            break;
        }
    }
    
    public void onLoad() {
        FriendManager.friends = new ArrayList<Friend>();
        this.clearSettings();
    }
    
    public void saveFriends() {
        this.clearSettings();
        this.cleanFriends();
        for (final Friend friend : FriendManager.friends) {
            this.register(new Setting(friend.getUuid().toString(), (Object)friend.getUsername()));
        }
    }
    
    public void cleanFriends() {
        FriendManager.friends.stream().filter(Objects::nonNull).filter(friend -> friend.getUsername() != null);
    }
    
    public List<Friend> getFriends() {
        this.cleanFriends();
        return FriendManager.friends;
    }
    
    public Friend getFriendByName(final String input) {
        final UUID uuid = PlayerUtil.getUUIDFromName(input);
        if (uuid != null) {
            final Friend friend = new Friend(input, uuid);
            return friend;
        }
        return null;
    }
    
    public void addFriend(final Friend friend) {
        FriendManager.friends.add(friend);
    }
    
    static {
        FriendManager.friends = new ArrayList<Friend>();
    }
    
    public static class Friend
    {
        private final String username;
        private final UUID uuid;
        
        public Friend(final String username, final UUID uuid) {
            this.username = username;
            this.uuid = uuid;
        }
        
        public String getUsername() {
            return this.username;
        }
        
        public UUID getUuid() {
            return this.uuid;
        }
    }
}
