package me.tokensmp.core.token.abilities;

import me.tokensmp.core.token.CooldownManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WardenToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownManager.isOnCooldown("warden_op", player.getUniqueId(), COOLDOWN)) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 300, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 300, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 200, 1));

        player.sendMessage("§4Warden Power Awakened!");
        CooldownManager.setCooldown("warden_op", player.getUniqueId());
    }
}
