package me.tokensmp.core.life;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LifeCommand implements CommandExecutor {

    private final LifeManager lifeManager;

    public LifeCommand(LifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length == 2 && args[0].equalsIgnoreCase("life")
                && args[1].equalsIgnoreCase("withdraw")) {

            if (lifeManager.getLives(player) <= 0) {
                player.sendMessage("§cNo lives left!");
                return true;
            }

            // 🔻 remove 1 life
            lifeManager.removeLife(player, 1);

            // 🎁 give 5 totems
            ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 5);
            player.getInventory().addItem(totem);

            player.sendMessage("§a1 Life withdrawn → 5 Totems given!");
            return true;
        }

        player.sendMessage("§cUsage: /tokens life withdraw");
        return true;
    }
}
