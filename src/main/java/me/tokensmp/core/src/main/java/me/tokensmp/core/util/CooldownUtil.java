package me.tokensmp.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownUtil {

    private static final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();

    // check cooldown
    public static boolean isOnCooldown(String key, UUID uuid, int seconds) {
        if (!cooldowns.containsKey(key)) return false;

        Map<UUID, Long> map = cooldowns.get(key);
        if (!map.containsKey(uuid)) return false;

        long expireTime = map.get(uuid);
        return System.currentTimeMillis() < expireTime;
    }

    // set cooldown
    public static void setCooldown(String key, UUID uuid, int seconds) {
        cooldowns.putIfAbsent(key, new HashMap<>());
        cooldowns.get(key).put(uuid, System.currentTimeMillis() + (seconds * 1000L));
    }

    // get remaining time
    public static long getRemaining(String key, UUID uuid) {
        if (!cooldowns.containsKey(key)) return 0;
        if (!cooldowns.get(key).containsKey(uuid)) return 0;

        long time = cooldowns.get(key).get(uuid) - System.currentTimeMillis();
        return Math.max(0, time / 1000);
    }

}
