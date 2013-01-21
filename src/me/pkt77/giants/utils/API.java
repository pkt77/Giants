package me.pkt77.giants.utils;

import java.util.List;
import me.pkt77.giants.Commands;
import me.pkt77.giants.Giants;
import me.pkt77.giants.events.Listeners;
import me.pkt77.giants.file.Config;
import me.pkt77.giants.file.FileHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;

public class API {
	private static Giants _giants;
	private Commands commands;
	private static FileHandler fileHandler;

	public API(Giants giants) {
		_giants = giants;
		new Listeners(_giants);
		commands = new Commands(_giants);
		_giants.getCommand("giants").setExecutor(commands);
		fileHandler = new FileHandler(_giants);
	}

	public static boolean isGiant(Entity entity) {
		return entity instanceof Giant;
	}

	public static boolean isGiant(LivingEntity livingEntity) {
		return livingEntity instanceof Giant;
	}

	public static List<String> getGiantSpawnWorlds() {
		return getFileHandler().getPropertyList(Config.CONFIG, "Giants Configuration.Spawn Settings.Worlds");
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}
}