package me.tokensmp.core.token;

import me.tokensmp.core.TokenSmpCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {

    private final TokenSmpCore plugin;
    private final Map<UUID, TokenType> playerToken = new HashMap<>();

    public TokenManager(TokenSmpCore plugin) {
        this.plugin = plugin;
    }

    public TokenSmpCore getPlugin() {
        return plugin;
    }

    public void forceGiveToken(Player player, TokenType type) {
        playerToken.put(player.getUniqueId(), type);
        player.getInventory().addItem(createTokenItem(type));
        player.sendMessage("§aYou received §e" + type.name() + " §aToken!");
    }

    public boolean hasToken(Player player) {
        return playerToken.containsKey(player.getUniqueId());
    }

    public TokenType getToken(Player player) {
        return playerToken.get(player.getUniqueId());
    }

    public void giveRandomToken(Player player) {
        if (hasToken(player)) return;

        TokenType type = TokenType.random();
        forceGiveToken(player, type);
    }

    public boolean isTokenItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;

        return item.getItemMeta()
                .getPersistentDataContainer()
                .has(plugin.getTokenKey(), PersistentDataType.STRING);
    }

    private ItemStack createTokenItem(TokenType type) {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6" + type.name() + " Token");
        meta.getPersistentDataContainer().set(
                plugin.getTokenKey(),
                PersistentDataType.STRING,
                type.name()
        );
        item.setItemMeta(meta);
        return item;
    }
}

