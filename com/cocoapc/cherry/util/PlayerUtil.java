//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.cocoapc.cherry.util;

import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.net.*;
import java.nio.charset.*;
import com.google.common.collect.*;
import com.google.gson.*;
import javax.net.ssl.*;
import net.minecraft.advancements.*;
import net.minecraft.entity.*;
import net.minecraft.client.network.*;
import com.cocoapc.cherry.features.command.*;
import com.mojang.util.*;
import org.apache.commons.io.*;
import java.util.*;
import java.io.*;

public class PlayerUtil implements Util
{
    private static final JsonParser PARSER;
    
    public static String getNameFromUUID(final UUID uuid) {
        try {
            final lookUpName process = new lookUpName(uuid);
            final Thread thread = new Thread(process);
            thread.start();
            thread.join();
            return process.getName();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static BlockPos getPlayerPosFloored() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static EntityPlayer getLookingPlayer(final double range) {
        final List<EntityPlayer> players = new ArrayList<EntityPlayer>(PlayerUtil.mc.world.playerEntities);
        for (int i = 0; i < players.size(); ++i) {
            if (getDistance(players.get(i)) > range) {
                players.remove(i);
            }
        }
        players.remove(PlayerUtil.mc.player);
        EntityPlayer target = null;
        final Vec3d positionEyes = PlayerUtil.mc.player.getPositionEyes(PlayerUtil.mc.getRenderPartialTicks());
        final Vec3d rotationEyes = PlayerUtil.mc.player.getLook(PlayerUtil.mc.getRenderPartialTicks());
        final int precision = 2;
        for (int j = 0; j < (int)range; ++j) {
            for (int k = precision; k > 0; --k) {
                for (final EntityPlayer targetTemp : players) {
                    final AxisAlignedBB playerBox = targetTemp.getEntityBoundingBox();
                    final double xArray = positionEyes.x + rotationEyes.x * j + rotationEyes.x / k;
                    final double yArray = positionEyes.y + rotationEyes.y * j + rotationEyes.y / k;
                    final double zArray = positionEyes.z + rotationEyes.z * j + rotationEyes.z / k;
                    if (playerBox.maxY >= yArray && playerBox.minY <= yArray && playerBox.maxX >= xArray && playerBox.minX <= xArray && playerBox.maxZ >= zArray && playerBox.minZ <= zArray) {
                        target = targetTemp;
                    }
                }
            }
        }
        return target;
    }
    
    public static BlockPos getPlayerPos(final EntityPlayer entityPlayer) {
        return new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
    }
    
    public static EntityPlayer getNearestPlayer(final double range) {
        return (EntityPlayer)PlayerUtil.mc.world.playerEntities.stream().filter(p -> PlayerUtil.mc.player.getDistance(p) <= range).filter(p -> PlayerUtil.mc.player.getEntityId() != p.getEntityId()).min(Comparator.comparing(p -> PlayerUtil.mc.player.getDistance(p))).orElse(null);
    }
    
    public static double getDistance(final EntityPlayer pos) {
        return PlayerUtil.mc.player.getDistance(pos.posX, pos.posY, pos.posZ);
    }
    
    public static double getDistances(final BlockPos e) {
        return PlayerUtil.mc.player.getDistance((double)e.getX(), (double)e.getY(), (double)e.getZ());
    }
    
    public static double getDistances(final Vec3d e) {
        return PlayerUtil.mc.player.getDistance(e.x, e.y, e.z);
    }
    
    public static boolean canSeePos(final BlockPos pos) {
        return PlayerUtil.mc.world.rayTraceBlocks(new Vec3d(PlayerUtil.mc.player.posX, PlayerUtil.mc.player.posY + PlayerUtil.mc.player.getEyeHeight(), PlayerUtil.mc.player.posZ), new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), false, true, false) == null;
    }
    
    public static String getNameFromUUID(final String uuid) {
        try {
            final lookUpName process = new lookUpName(uuid);
            final Thread thread = new Thread(process);
            thread.start();
            thread.join();
            return process.getName();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static UUID getUUIDFromName(final String name) {
        try {
            final lookUpUUID process = new lookUpUUID(name);
            final Thread thread = new Thread(process);
            thread.start();
            thread.join();
            return process.getUUID();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static String requestIDs(final String data) {
        try {
            final String query = "https://api.mojang.com/profiles/minecraft";
            final URL url = new URL(query);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            final OutputStream os = conn.getOutputStream();
            os.write(data.getBytes(StandardCharsets.UTF_8));
            os.close();
            final InputStream in = new BufferedInputStream(conn.getInputStream());
            final String res = convertStreamToString(in);
            in.close();
            conn.disconnect();
            return res;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static String convertStreamToString(final InputStream is) {
        final Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "/";
    }
    
    public static List<String> getHistoryOfNames(final UUID id) {
        try {
            final JsonArray array = getResources(new URL("https://api.mojang.com/user/profiles/" + getIdNoHyphens(id) + "/names"), "GET").getAsJsonArray();
            final List<String> temp = (List<String>)Lists.newArrayList();
            for (final JsonElement e : array) {
                final JsonObject node = e.getAsJsonObject();
                final String name = node.get("name").getAsString();
                final long changedAt = node.has("changedToAt") ? node.get("changedToAt").getAsLong() : 0L;
                temp.add(name + "\uff83\u3064\uff678" + new Date(changedAt).toString());
            }
            Collections.sort(temp);
            return temp;
        }
        catch (Exception ignored) {
            return null;
        }
    }
    
    public static String getIdNoHyphens(final UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }
    
    private static JsonElement getResources(final URL url, final String request) throws Exception {
        return getResources(url, request, null);
    }
    
    private static JsonElement getResources(final URL url, final String request, final JsonElement element) throws Exception {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(request);
            connection.setRequestProperty("Content-Type", "application/json");
            if (element != null) {
                final DataOutputStream output = new DataOutputStream(connection.getOutputStream());
                output.writeBytes(AdvancementManager.GSON.toJson(element));
                output.close();
            }
            final Scanner scanner = new Scanner(connection.getInputStream());
            final StringBuilder builder = new StringBuilder();
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
                builder.append('\n');
            }
            scanner.close();
            final String json = builder.toString();
            final JsonElement data = PlayerUtil.PARSER.parse(json);
            return data;
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    static {
        PARSER = new JsonParser();
    }
    
    public static class lookUpUUID implements Runnable
    {
        private final String name;
        private volatile UUID uuid;
        
        public lookUpUUID(final String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            NetworkPlayerInfo profile;
            try {
                final ArrayList<NetworkPlayerInfo> infoMap = new ArrayList<NetworkPlayerInfo>(Objects.requireNonNull(Util.mc.getConnection()).getPlayerInfoMap());
                profile = infoMap.stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(this.name)).findFirst().orElse(null);
                assert profile != null;
                this.uuid = profile.getGameProfile().getId();
            }
            catch (Exception e2) {
                profile = null;
            }
            if (profile == null) {
                Command.sendMessage("Player isn't online. Looking up UUID..");
                final String s = PlayerUtil.requestIDs("[\"" + this.name + "\"]");
                if (s == null || s.isEmpty()) {
                    Command.sendMessage("Couldn't find player ID. Are you connected to the internet? (0)");
                }
                else {
                    final JsonElement element = new JsonParser().parse(s);
                    if (element.getAsJsonArray().size() == 0) {
                        Command.sendMessage("Couldn't find player ID. (1)");
                    }
                    else {
                        try {
                            final String id = element.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
                            this.uuid = UUIDTypeAdapter.fromString(id);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            Command.sendMessage("Couldn't find player ID. (2)");
                        }
                    }
                }
            }
        }
        
        public UUID getUUID() {
            return this.uuid;
        }
        
        public String getName() {
            return this.name;
        }
    }
    
    public static class lookUpName implements Runnable
    {
        private final String uuid;
        private final UUID uuidID;
        private volatile String name;
        
        public lookUpName(final String input) {
            this.uuid = input;
            this.uuidID = UUID.fromString(input);
        }
        
        public lookUpName(final UUID input) {
            this.uuidID = input;
            this.uuid = input.toString();
        }
        
        @Override
        public void run() {
            this.name = this.lookUpName();
        }
        
        public String lookUpName() {
            EntityPlayer player = null;
            if (Util.mc.world != null) {
                player = Util.mc.world.getPlayerEntityByUUID(this.uuidID);
            }
            if (player == null) {
                final String url = "https://api.mojang.com/user/profiles/" + this.uuid.replace("-", "") + "/names";
                try {
                    final String nameJson = IOUtils.toString(new URL(url));
                    if (nameJson.contains(",")) {
                        final List<String> names = Arrays.asList(nameJson.split(","));
                        Collections.reverse(names);
                        return names.get(1).replace("{\"name\":\"", "").replace("\"", "");
                    }
                    return nameJson.replace("[{\"name\":\"", "").replace("\"}]", "");
                }
                catch (IOException exception) {
                    exception.printStackTrace();
                    return null;
                }
            }
            return player.getName();
        }
        
        public String getName() {
            return this.name;
        }
    }
}
