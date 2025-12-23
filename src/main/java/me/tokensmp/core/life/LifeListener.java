package me.tokensmp.core.life;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class LifeListener implements Listener {

    private final LifeManager lifeManager;

    public LifeListener(LifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }

    @EventHandler
public void onDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();

    // ❌ TOKEN DROP STOP
    event.getDrops().removeIf(item ->
            tokenManager.isTokenItem(item)
    );

    // Natural death → no life loss
    if (player.getKiller() == null) {
        return;
    }

    lifeManager.removeLife(player, 1);
}

