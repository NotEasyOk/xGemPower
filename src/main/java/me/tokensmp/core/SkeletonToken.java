package me.tokensmp.core.token.abilities;

import me.tokensmp.core.token.CooldownManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class SkeletonToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownUtil.isOnCooldown("skeleton_rain", player.getUniqueId(), COOLDOWN)) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        Location loc = player.getLocation();

        for (int i = 0; i < 20; i++) {
            loc.getWorld().spawnArrow(
                    loc.clone().add(Math.random()*6-3, 10, Math.random()*6-3),
                    loc.getDirection(),
                    1.5f,
                    12
            );
        }

        player.sendMessage("§7Skeleton Arrow Rain!");
        CooldownUtil.setCooldown("skeleton_rain", player.getUniqueId());
    }
          }
