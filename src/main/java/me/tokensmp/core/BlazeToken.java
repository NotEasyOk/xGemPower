package me.tokensmp.core.token.abilities;

import me.tokensmp.core.util.CooldownUtil;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class BlazeToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownUtil.isOnCooldown("blaze_fire", player.getUniqueId(), COOLDOWN)) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setYield(3F);

        player.sendMessage("§6Blaze Fireball!");
        CooldownUtil.setCooldown("blaze_fire", player.getUniqueId());
    }
            }
          
