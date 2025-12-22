package me.tokensmp.core.life;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LifeShieldManager {

    private final LifeManager lifeManager;
    private final Map<UUID, Integer> activeTotems = new HashMap<>();

    public LifeShieldManager(LifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }

    public void activate(Player player) {
        activeTotems.put(player.getUniqueId(), 5);
    }

    public boolean hasShield(Player player) {
        return activeTotems.containsKey(player.getUniqueId());
    }

    public void consumeTotem(Player player) {
        UUID uuid = player.getUniqueId();
        if (!activeTotems.containsKey(uuid)) return;
        int left = activeTotems.get(uuid) - 1;

        if (left <= 0) {
            activeTotems.remove(uuid);
            lifeManager.addLife(player, 1);
            player.sendMessage("§aLife returned automatically!");
        } else {
            activeTotems.put(uuid, left);
        }
    }
}
