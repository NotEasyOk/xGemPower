package me.tokensmp.core.token;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private static final Map<UUID, Long> cooldown = new HashMap<>();

    public static boolean onCooldown(UUID uuid) {
        return cooldown.containsKey(uuid) &&
                cooldown.get(uuid) > System.currentTimeMillis();
    }
  
    if (CooldownManager.onCooldown(player.getUniqueId())) {
    player.sendMessage("§cCooldown: " +
        CooldownManager.left(player.getUniqueId()) + "s");
    return;
}

CooldownManager.set(player.getUniqueId(), 60);

    public static void set(UUID uuid, int seconds) {
        cooldown.put(uuid, System.currentTimeMillis() + (seconds * 1000L));
    }

    public static long left(UUID uuid) {
        return (cooldown.get(uuid) - System.currentTimeMillis()) / 1000;
    }
}
