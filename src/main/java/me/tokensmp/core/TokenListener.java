package me.tokensmp.core.token;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TokenListener implements Listener {

    private final TokenManager tokenManager;

    public TokenListener(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // 🎲 First join → random token
        if (!event.getPlayer().hasPlayedBefore()) {
            tokenManager.giveRandomToken(event.getPlayer());
        }
    }
}
