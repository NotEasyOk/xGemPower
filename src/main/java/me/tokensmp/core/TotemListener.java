package me.tokensmp.core.life;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.entity.Player;

public class TotemListener implements Listener {

    @EventHandler
    public void onTotem(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (lifeShieldManager.hasShield(player)) {
            lifeShieldManager.consumeTotem(player);
        }
    }
}
