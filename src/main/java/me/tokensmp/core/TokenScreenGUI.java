package me.tokensmp.core.token.gui;

import me.tokensmp.core.token.TokenManager;
import me.tokensmp.core.token.TokenType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TokenScreenGUI {

    private static final Random random = new Random();

    public static void open(Player player, TokenManager tokenManager) {

        Inventory inv = Bukkit.createInventory(null, 27, "§6Choose Your Token");

        for (int i = 0; i < 27; i++) {
            inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        }

        int slot = 10;
        for (TokenType type : TokenType.values()) {
            ItemStack item = new ItemStack(Material.EMERALD);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e" + type.name() + " Token");
            item.setItemMeta(meta);
            inv.setItem(slot, item);
            slot++;
        }

        player.openInventory(inv);

        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                ticks++;
                int randomSlot = 10 + random.nextInt(8);
                player.getOpenInventory().setItem(
                        randomSlot,
                        new ItemStack(Material.GLOWSTONE)
                );

                if (ticks >= 40) {
                    cancel();
                    TokenType selected = TokenType.random();
                    tokenManager.forceGiveToken(player, selected);
                    player.closeInventory();
                }
            }
        }.runTaskTimer(tokenManager.getPlugin(), 0L, 5L);
    }
              }
              
