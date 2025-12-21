package me.tokensmp.core.token;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private static final Map<UUID, Long> cooldowns = new HashMap<>();

    public static boolean onCooldown(UUID uuid) {
        return cooldowns.containsKey(uuid)
                && cooldowns.get(uuid) > System.currentTimeMillis();
    }

    public static void set(UUID uuid, int seconds) {
        cooldowns.put(uuid, System.currentTimeMillis() + (seconds * 1000L));
    }

    public static long left(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) return 0;
        return (cooldowns.get(uuid) - System.currentTimeMillis()) / 1000;
    }
}

