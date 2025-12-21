package me.tokensmp.core.cooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private static final Map<UUID, Long> cooldowns = new HashMap<>();

    // Check cooldown
    public static boolean onCooldown(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) return false;
        return cooldowns.get(uuid) > System.currentTimeMillis();
    }

    // Set cooldown (seconds)
    public static void set(UUID uuid, int seconds) {
        cooldowns.put(
                uuid,
                System.currentTimeMillis() + (seconds * 1000L)
        );
    }

    // Time left (seconds)
    public static long left(UUID uuid) {
        if (!cooldowns.containsKey(uuid)) return 0;
        return (cooldowns.get(uuid) - System.currentTimeMillis()) / 1000;
    }
}

