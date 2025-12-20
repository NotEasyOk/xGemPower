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
@EventHandler
public void onUse(PlayerInteractEvent event) {
    if (!event.getAction().toString().contains("RIGHT")) return;

    Player player = event.getPlayer();

    if (!tokenManager.isTokenItem(player.getInventory().getItemInMainHand())) return;

    TokenType type = tokenManager.getToken(player);

    switch (type) {
        case ENDERMAN -> EndermanToken.useAbility(player);
        case SKELETON -> SkeletonToken.useAbility(player);
        case BLAZE -> BlazeToken.useAbility(player);
    }
}
