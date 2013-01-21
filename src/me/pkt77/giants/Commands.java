package me.pkt77.giants;

import me.pkt77.giants.utils.API;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class Commands implements CommandExecutor {
	private Giants _giants;

	public Commands(Giants giants) {
		_giants = giants;
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("giants")) {
			if (args.length == 0) {
				if (sender.hasPermission("giants.reload") || sender.hasPermission("giants.*")) {
					sender.sendMessage(ChatColor.GREEN + "===== Giants Commands ===== \n" + "/giants reload   Reloads the config file.");
				} else {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
				}
				return true;
			} else {
				if (args[0].equalsIgnoreCase("reload")) {
					Player player = null;
					if (sender instanceof Player) {
						player = (Player) sender;
						if (player.hasPermission("giants.reload")) {
							API.getFileHandler().loadConfig();
							sender.sendMessage(ChatColor.GREEN + "Giants config file reloaded.");
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
						}
					} else {
						API.getFileHandler().loadConfig();
						_giants.log.info("Giants config file reloaded.");
					}
				}
			}
		}
		return true;
	}
}