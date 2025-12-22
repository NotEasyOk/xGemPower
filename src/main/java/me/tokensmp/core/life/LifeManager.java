package me.tokensmp.core.life;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LifeManager {

    private final Map<UUID, Integer> lives = new HashMap<>();
    private static final int MAX_LIFE = 5;
    
    public LifeManager() {
    }
    
    // Get lives
    public int getLives(Player player) {
        return lives.getOrDefault(player.getUniqueId(), MAX_LIFE);
    }

    // Set lives
    public void setLives(Player player, int amount) {
        lives.put(player.getUniqueId(), Math.max(0, amount));
        applyLife(player);
    }

    // Add life
    public void addLife(Player player, int amount) {
        setLives(player, getLives(player) + amount);
    }

    // Remove life
    public void removeLife(Player player, int amount) {
        setLives(player, getLives(player) - amount);

        if (getLives(player) <= 0) {
            handleZeroLife(player);
        }
    }

    // Apply hearts based on life
    private void applyLife(Player player) {
        int life = getLives(player);
        double maxHealth = 20 + (life * 2);

        player.setMaxHealth(maxHealth);
        if (player.getHealth() > maxHealth) {
            player.setHealth(maxHealth);
        }
    }

    // Zero life logic
    private void handleZeroLife(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.kickPlayer("§cAll lives lost! Come back after 24 hours.");
    }
    }
