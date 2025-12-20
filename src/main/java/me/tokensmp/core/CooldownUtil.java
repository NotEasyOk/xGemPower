package me.tokensmp.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownUtil {

    private static final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(String key, UUID uuid, int seconds) {
        if (!cooldowns.containsKey(key)) return false;

        long last = cooldowns.get(key).getOrDefault(uuid, 0L);
        return (System.currentTimeMillis() - last) < (seconds * 1000L);
    }

    public static void setCooldown(String key, UUID uuid) {
        cooldowns.computeIfAbsent(key, k -> new HashMap<>())
                 .put(uuid, System.currentTimeMillis());
    }
            }
