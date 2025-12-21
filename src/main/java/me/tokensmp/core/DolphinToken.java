package me.tokensmp.core.token.abilities;

import me.tokensmp.core.token.CooldownManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DolphinToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownManager.isOnCooldown("dolphin_swim", player.getUniqueId())) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 300, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 300, 0));

        player.sendMessage("§3Dolphin Speed!");
        CooldownManager.setCooldown("dolphin_swim", player.getUniqueId(), COOLDOWN);
    }
          }
