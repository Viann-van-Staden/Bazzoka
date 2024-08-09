package me.viann.bazzoka.commands;

import me.viann.bazzoka.Bazzoka;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BazzokaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }

        Player player = (Player) sender;
        ItemStack explosiveBow = Bazzoka.createExplosiveBow();
        player.getInventory().addItem(explosiveBow);
        player.sendMessage("You have received the Explosive Bow!");

        return true;
    }
}
