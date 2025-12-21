package me.tokensmp.core.token.abilities;

import me.tokensmp.core.token.CooldownManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitherToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownManager.isOnCooldown("wither_aura", player.getUniqueId(), COOLDOWN)) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 200, 2));

        player.sendMessage("§8Wither Aura Activated!");
        CooldownManager.setCooldown("wither_aura", player.getUniqueId());
    }
}
