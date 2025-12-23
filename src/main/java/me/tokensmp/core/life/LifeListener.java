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
    Player dead = event.getEntity();
    Player killer = dead.getKiller();

    if (killer == null) return; // natural death

    lifeManager.removeLife(dead, 1);
    lifeManager.addLife(killer, 1);
}
