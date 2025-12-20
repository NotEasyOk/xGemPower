package me.tokensmp.core.token;

import me.tokensmp.core.TokenSmpCore;
import org.bukkit.Bukkit;
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

    public boolean hasToken(Player player) {
        return playerToken.containsKey(player.getUniqueId());
    }

    public TokenType getToken(Player player) {
        return playerToken.get(player.getUniqueId());
    }

    public void giveRandomToken(Player player) {
        if (hasToken(player)) return;

        TokenType token = TokenType.random();
        playerToken.put(player.getUniqueId(), token);

        ItemStack item = createTokenItem(token);
        player.getInventory().addItem(item);

        player.sendMessage("§aYou received §e" + token.name() + " §aToken!");
    }

    private ItemStack createTokenItem(TokenType token) {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§6" + token.name() + " Token");

        meta.getPersistentDataContainer().set(
                plugin.getNamespacedKey(),
                PersistentDataType.STRING,
                token.name()
        );

        item.setItemMeta(meta);
        return item;
    }

    public boolean isTokenItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;

        return item.getItemMeta().getPersistentDataContainer()
                .has(plugin.getNamespacedKey(), PersistentDataType.STRING);
    }
  }
          
