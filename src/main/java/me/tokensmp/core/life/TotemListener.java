package me.tokensmp.core.life;

import me.tokensmp.core.TokenSmpCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class TotemListener implements Listener {

    private final TokenSmpCore plugin;

    public TotemListener(TokenSmpCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTotem(EntityResurrectEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        LifeShieldManager lifeShieldManager = plugin.getLifeShieldManager();
        if (lifeShieldManager == null) return;

        if (!lifeShieldManager.hasShield(player)) return;

        lifeShieldManager.consumeTotem(player);
    }
}

