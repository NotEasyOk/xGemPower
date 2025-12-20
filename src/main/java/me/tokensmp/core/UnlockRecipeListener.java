package me.tokensmp.core.token;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class UnlockRecipeListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) return;

        ItemStack[] matrix = event.getInventory().getMatrix();

        int diamondBlocks = 0;
        int totems = 0;
        ItemStack tokenItem = null;

        for (ItemStack item : matrix) {
            if (item == null) continue;

            if (item.getType() == Material.DIAMOND_BLOCK) diamondBlocks++;
            else if (item.getType() == Material.TOTEM_OF_UNDYING) totems++;
            else if (item.getType() == Material.EMERALD) tokenItem = item;
        }

        if (diamondBlocks == 4 && totems == 4 && tokenItem != null) {
            event.setCancelled(true);

            TokenType token = TokenType.valueOf(
                    tokenItem.getItemMeta()
                            .getDisplayName()
                            .replace("§6", "")
                            .replace(" Token", "")
            );

            // Unlock Ability-2 first, then Ability-3
            if (!AbilityUnlockManager.isUnlocked(player, token, 2)) {
                AbilityUnlockManager.unlock(player, token, 2);
                player.sendMessage("§aAbility 2 unlocked for " + token.name());
            } else if (!AbilityUnlockManager.isUnlocked(player, token, 3)) {
                AbilityUnlockManager.unlock(player, token, 3);
                player.sendMessage("§aAbility 3 unlocked for " + token.name());
            } else {
                player.sendMessage("§cAll abilities already unlocked!");
            }
        }
    }
          }
