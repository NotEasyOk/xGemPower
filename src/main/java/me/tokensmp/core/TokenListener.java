package me.tokensmp.core.token;

import me.tokensmp.core.token.abilities.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class TokenListener implements Listener {

    private final TokenManager tokenManager;

    public TokenListener(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    // 🎁 First join → random token
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            tokenManager.giveRandomToken(player);
        }
    }

    // ⚡ Ability use (Right click token)
    @EventHandler
    public void onUse(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_AIR &&
            event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = event.getPlayer();

        if (!tokenManager.isTokenItem(player.getInventory().getItemInMainHand()))
            return;

        TokenType type = tokenManager.getToken(player);
        if (type == null) return;

        switch (type) {
            case ENDERMAN -> EndermanToken.useAbility(player);
            case SKELETON -> SkeletonToken.useAbility(player);
            case BLAZE -> BlazeToken.useAbility(player);
            case CREEPER -> CreeperToken.useAbility(player);
            case WITHER -> WitherToken.useAbility(player);
            case WARDEN -> WardenToken.useAbility(player);
            case BREEZE -> BreezeToken.useAbility(player);
            case DOLPHIN -> DolphinToken.useAbility(player);
        }
    }
}

