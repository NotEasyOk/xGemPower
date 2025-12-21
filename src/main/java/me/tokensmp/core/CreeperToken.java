package me.tokensmp.core.token.abilities;

import me.tokensmp.core.token.CooldownManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

public class CreeperToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownUtil.isOnCooldown("creeper_orbit", player.getUniqueId(), COOLDOWN)) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        Location loc = player.getLocation().add(0, 15, 0);
        TNTPrimed tnt = loc.getWorld().spawn(loc, TNTPrimed.class);
        tnt.setFuseTicks(40);
        tnt.setYield(6F);

        player.sendMessage("§aCreeper Orbital Strike!");
        CooldownUtil.setCooldown("creeper_orbit", player.getUniqueId());
    }
            }
