package me.pkt77.giants;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Permissions {
	@SuppressWarnings("unused")
	private Giants _giants;

	public Permissions(Giants giants) {
		_giants = giants;
	}

	public boolean hasPerm(CommandSender sender, String permission) {
		return (sender.hasPermission(permission));
	}

	public boolean hasAnyPerm(CommandSender sender) {
		return (hasPerm(sender, "giants.debug")) || (hasPerm(sender, "giants.*")) || (hasPerm(sender, "giants.reload"));
	}

	public boolean hasDubugPerm(Player player) {
		return (hasPerm(player, "giants.debug")) || (hasPerm(player, "giants.*"));
	}

	public boolean hasReloadPerm(CommandSender sender) {
		return (hasPerm(sender, "giants.reload")) || (hasPerm(sender, "giants.*"));
	}

	public void sendPermissionsMessage(CommandSender sender) {
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	}
}