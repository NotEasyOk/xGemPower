package me.tokensmp.core.token.abilities;

import me.tokensmp.core.token.CooldownManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class EndermanToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownManager.isOnCooldown("enderman_tp", player.getUniqueId(), COOLDOWN)) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        Location loc = player.getTargetBlockExact(40).getLocation();
        loc.add(0, 1, 0);

        player.teleport(loc);
        player.sendMessage("§5Enderman Teleport!");

        CooldownManager.setCooldown("enderman_tp", player.getUniqueId());
    }
          }
