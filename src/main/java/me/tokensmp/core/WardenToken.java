package me.tokensmp.core.token.abilities;

import me.tokensmp.core.util.CooldownUtil;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WardenToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownUtil.isOnCooldown("warden_op", player.getUniqueId(), COOLDOWN)) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 200, 1));

        player.sendMessage("§4Warden Power Awakened!");
        CooldownUtil.setCooldown("warden_op", player.getUniqueId());
    }
}
