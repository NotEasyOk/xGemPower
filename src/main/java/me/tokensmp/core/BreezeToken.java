package me.tokensmp.core.token.abilities;

import me.tokensmp.core.token.CooldownManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BreezeToken {

    private static final int COOLDOWN = 60;

    public static void useAbility(Player player) {

        if (CooldownManager.isOnCooldown("breeze_jump", player.getUniqueId())) {
            player.sendMessage("§cAbility on cooldown!");
            return;
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 300, 3));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 300, 0));

        player.sendMessage("§bBreeze Lift!");
        CooldownManager.setCooldown("breeze_jump", player.getUniqueId(), COOLDOWN);
    }
}
