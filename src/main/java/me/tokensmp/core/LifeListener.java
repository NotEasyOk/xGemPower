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

        // ❌ Natural death → no life loss
        if (killer == null) return;

        // 🔻 Dead player loses 1 life
        lifeManager.removeLife(dead, 1);

        // 🔺 Killer gains 1 life
        lifeManager.addLife(killer, 1);
    }
   @EventHandler
public void onKill(PlayerDeathEvent e) {
    if (e.getEntity().getKiller() != null) {
        addLife(e.getEntity().getKiller(), 1);
    }
}  
