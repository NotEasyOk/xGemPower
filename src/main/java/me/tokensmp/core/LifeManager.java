package me.tokensmp.core.life;

import me.tokensmp.core.TokenSmpCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LifeManager {

    private final TokenSmpCore plugin;
    private final Map<UUID, Integer> lives = new HashMap<>();
    private final int MAX_LIFE = 5;

    public LifeManager(TokenSmpCore plugin) {
        this.plugin = plugin;
    }

    public int getLives(Player player) {
        return lives.getOrDefault(player.getUniqueId(), MAX_LIFE);
    }

    public void setLives(Player player, int amount) {
        lives.put(player.getUniqueId(), Math.max(0, amount));
    }

    public void addLife(Player player, int amount) {
        setLives(player, getLives(player) + amount);
    }

    public void removeLife(Player player, int amount) {
        setLives(player, getLives(player) - amount);
        if (getLives(player) <= 0) {
            handleZeroLife(player);
        }
    }

    private void handleZeroLife(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                "ban " + player.getName() + " 24h All lives lost"
        );
    }
                                  }
