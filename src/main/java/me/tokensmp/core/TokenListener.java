package me.tokensmp.core.token;

import me.tokensmp.core.token.abilities.BlazeToken;
import me.tokensmp.core.token.abilities.EndermanToken;
import me.tokensmp.core.token.abilities.SkeletonToken;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import me.tokensmp.core.token.abilities.*;

public class TokenListener implements Listener {

    private final TokenManager tokenManager;

    public TokenListener(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    // 🎲 First join → random token
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            tokenManager.giveRandomToken(event.getPlayer());
        }
    }

    // 🔥 Ability use (Right Click)
    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        Action action = event.getAction();

        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;

        Player player = event.getPlayer();

        if (!tokenManager.isTokenItem(player.getInventory().getItemInMainHand())) return;

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
