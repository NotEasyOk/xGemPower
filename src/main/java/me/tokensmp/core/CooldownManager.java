package me.tokensmp.core.token;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private static final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(String key, UUID uuid) {
        if (!cooldowns.containsKey(key)) return false;
        return cooldowns.get(key).getOrDefault(uuid, 0L) > System.currentTimeMillis();
    }

    public static long getLeft(String key, UUID uuid) {
        if (!isOnCooldown(key, uuid)) return 0;
        return (cooldowns.get(key).get(uuid) - System.currentTimeMillis()) / 1000;
    }

    public static void setCooldown(String key, UUID uuid, int seconds) {
        cooldowns.computeIfAbsent(key, k -> new HashMap<>())
                .put(uuid, System.currentTimeMillis() + (seconds * 1000L));
    }
}

