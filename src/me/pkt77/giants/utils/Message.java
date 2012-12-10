package me.pkt77.giants.utils;

import java.util.logging.Logger;
import me.pkt77.giants.Giants;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {
	private static final Logger log = Logger.getLogger("Minecraft");
	private static String logName;
	private static String prefix;

	public Message(Giants giants) {
		logName = "[Giants]";
		prefix = ChatColor.BLUE + logName + ChatColor.GRAY;
	}
	
	public static void logInfo(String message) {
		log.info(logName + message);
	}

	public static void logWarning(String message) {
		log.warning(logName + message);
	}

	public static void logSevere(String message) {
		log.severe(logName + message);
	}

	public static void sendMessage(CommandSender sender, String message) {
		sender.sendMessage(prefix + message);
	}
}