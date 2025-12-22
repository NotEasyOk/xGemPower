package me.tokensmp.core.life;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LifeShieldManager {

    private static final Map<UUID, Integer> activeTotems = new HashMap<>();

    public static void activate(Player player) {
        activeTotems.put(player.getUniqueId(), 5);
    }

    public static boolean hasShield(Player player) {
        return activeTotems.containsKey(player.getUniqueId());
    }

    public static void consumeTotem(Player player) {
        int left = activeTotems.get(player.getUniqueId()) - 1;

        if (left <= 0) {
            activeTotems.remove(player.getUniqueId());
            lifeManager.addLife(player, 1);
            player.sendMessage("§aLife returned automatically!");
        } else {
            activeTotems.put(player.getUniqueId(), left);
        }
    }
}
