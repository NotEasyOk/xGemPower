package me.tokensmp.core.token;

import org.bukkit.entity.Player;

import java.util.*;

public class AbilityUnlockManager {

    // UUID -> Token -> unlocked abilities (2,3)
    private static final Map<UUID, Map<TokenType, Set<Integer>>> unlocked = new HashMap<>();

    public static boolean isUnlocked(Player player, TokenType token, int ability) {
        return unlocked
                .getOrDefault(player.getUniqueId(), Collections.emptyMap())
                .getOrDefault(token, Collections.emptySet())
                .contains(ability);
    }

    public static void unlock(Player player, TokenType token, int ability) {
        unlocked
            .computeIfAbsent(player.getUniqueId(), u -> new HashMap<>())
            .computeIfAbsent(token, t -> new HashSet<>())
            .add(ability);
    }
                              }
