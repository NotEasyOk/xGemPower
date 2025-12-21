package me.tokensmp.core.token;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private static final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();

    // check cooldown
    public static boolean onCooldown(String key, UUID uuid) {
        if (!cooldowns.containsKey(key)) return false;
        return cooldowns.get(key).getOrDefault(uuid, 0L) > System.currentTimeMillis();
    }

    // seconds left
    public static long left(String key, UUID uuid) {
        if (!cooldowns.containsKey(key)) return 0;
        long time = cooldowns.get(key).getOrDefault(uuid, 0L) - System.currentTimeMillis();
        return Math.max(0, time / 1000);
    }

    // set cooldown
    public static void set(String key, UUID uuid, int seconds) {
        cooldowns.computeIfAbsent(key, k -> new HashMap<>())
                .put(uuid, System.currentTimeMillis() + (seconds * 1000L));
    }
}

